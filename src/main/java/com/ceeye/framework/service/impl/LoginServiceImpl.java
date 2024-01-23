package com.ceeye.framework.service.impl;


import com.ceeye.framework.dto.LoginDto;
import com.ceeye.framework.enums.CommonCodeEnum;
import com.ceeye.framework.enums.ResultEnum;
import com.ceeye.framework.exception.CommonException;
import com.ceeye.framework.mapper.PersonMapper;
import com.ceeye.framework.mapper.RoleMapper;
import com.ceeye.framework.entity.Person;
import com.ceeye.framework.entity.Role;
import com.ceeye.framework.service.LoginService;
import com.ceeye.framework.util.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 登录管理
 *
 * @author Raven
 * @since 2020-02-12 15:26
 */
@Service
@Slf4j
public class LoginServiceImpl extends IBaseServiceImpl<PersonMapper, Person> implements LoginService {

    @Resource
    private PersonMapper personMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登陆
     *
     * @param person 参数
     * @return String
     */
    @Override
    public String doLogin(LoginDto person) throws NoSuchAlgorithmException {

        if (person == null || StringUtils.isEmpty(person.getUserName()) || StringUtils.isEmpty(person.getPassword())) {
            throw new CommonException(ResultEnum.PARSE_ERROR);
        }
        Person loginPerson = personMapper.selectUserName(person.getUserName());
        if (loginPerson == null) {
            throw new CommonException(ResultEnum.USER_FAILED);
        }
        // 前端传进来的密码(经过前端MD5混淆,但未加密）,将其加密
        String password = encryptPassword(person.getPassword(), loginPerson.getId());

        if (!loginPerson.getPassword().equals(password)) {
            throw new CommonException(ResultEnum.USER_PASSWORD_ERROR);
        }
        // 密码匹配
        return dealWithLoginSuccessInfo(loginPerson);
    }

    /**
     * 将token放入redis黑名单中
     * 主要用于手动过期token，例如退出登录、修改密码、重置密码等
     *
     * @param userName 登录用户名
     * @param token    要作废的token
     */
    @Override
    public void blacklist(String userName, String token) {
        int seconds = Integer.parseInt(CommonCodeEnum.LOGIN_TIMEOUT.getValue()) * 60;
        stringRedisTemplate.opsForValue().set(token, userName, seconds, TimeUnit.SECONDS);
    }

    /**
     * 登录成功后续处理
     *
     * @param loginSuccessPerson 登录成功的用户
     * @return token
     */
    public String dealWithLoginSuccessInfo(Person loginSuccessPerson) {
        Role role = roleMapper.selectById(loginSuccessPerson.getRole());
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(simpleGrantedAuthority);
        User user = new User(loginSuccessPerson.getUserName(), loginSuccessPerson.getPassword(), authorities);
        //生成token
        return JwtUtil.generateToken(user);
    }
}

package com.raven.framework.service;


import com.raven.framework.dto.LoginDto;

import java.security.NoSuchAlgorithmException;

/**
 * @author raven
 */
public interface LoginService {

    /**
     * 登录
     * @param person 登录信息
     * @return token
     * @throws NoSuchAlgorithmException 加密算法错误
     */
    String doLogin(LoginDto person) throws NoSuchAlgorithmException;

    /**
     * token黑名单
     * @param userName 用户名
     * @param token token
     */
    void blacklist(String userName, String token);
}

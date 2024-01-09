package com.ceeye.framework.config;


import com.ceeye.framework.enums.ResultEnum;
import com.ceeye.framework.exception.CommonException;
import com.ceeye.framework.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * jwt拦截器
 *
 * @author : raven
 * @since : 2020-02-12 15:26
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override

    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        //获取token，并且解析token，如果解析成功，则放入 SecurityContext
        if (token != null) {
            User user = JwtUtil.parseToken(token);
            //是否redis黑名单中
            String userName = stringRedisTemplate.opsForValue().get(token);
            if (user.getUsername().equals(userName)) {
                throw new CommonException(ResultEnum.LOGIN_IS_OVERDUE);
            }
            //解析成功
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                //我们依然使用原来filter中的token对象
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}


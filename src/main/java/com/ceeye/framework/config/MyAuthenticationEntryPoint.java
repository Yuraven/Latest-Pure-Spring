package com.ceeye.framework.config;

import com.alibaba.fastjson2.JSON;
import com.ceeye.framework.enums.ResultEnum;
import com.ceeye.framework.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用户未登录时返回给前端的数据
 * @author : raven
 * @since : 2020-02-12 15:26
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(new ResultVO<>(ResultEnum.USER_NEED_AUTHORITIES,false)));
    }
}
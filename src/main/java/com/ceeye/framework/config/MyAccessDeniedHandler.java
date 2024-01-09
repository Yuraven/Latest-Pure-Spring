package com.ceeye.framework.config;

import com.alibaba.fastjson2.JSON;
import com.ceeye.framework.enums.ResultEnum;
import com.ceeye.framework.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

/**
 * 处理无权登录的情况
 * @author : raven
 * @since : 2020-02-12 15:26
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException{
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(new ResultVO<>(ResultEnum.USER_NO_ACCESS,false)));
    }
}

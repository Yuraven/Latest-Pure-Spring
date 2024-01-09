package com.ceeye.framework.handler;

import com.ceeye.framework.exception.CommonException;
import com.ceeye.framework.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常捕获处理类
 * @author Raven
 * @since 2019/8/13
 */
@ResponseBody
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     *
     */
    @ResponseBody
    @ExceptionHandler(value = CommonException.class)
    public ResultVO<String> handlerCommonException(CommonException ce) {
        log.error("异常{}",ce.getCode(),ce.getE());
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(ce.getCode());
        resultVO.setMsg(ce.getResultMessage());
        return resultVO;
    }
}

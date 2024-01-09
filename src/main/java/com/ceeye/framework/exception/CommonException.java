package com.ceeye.framework.exception;

import com.ceeye.framework.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author Raven
 * @since 2019/8/6
 * @description 通用异常类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5757199067882270114L;

    private Integer code;

    private Exception e;

    private String resultMessage;

    public CommonException(ResultEnum resultEnum, Exception e) {
        super(e.getMessage());
        if(e instanceof CommonException ce){
            this.code = ce.getCode();
            this.resultMessage = ce.getMessage();
        }else {
            this.code = resultEnum.getCode();
            this.resultMessage = resultEnum.getMessage();
        }
        this.e = e;
    }

    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public CommonException(Integer code, String resultMessage) {
        super(resultMessage);
        this.code = code;
    }
}

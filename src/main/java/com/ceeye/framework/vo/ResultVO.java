package com.ceeye.framework.vo;

import com.ceeye.framework.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果VO
 * @author Raven
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 7941574207497841441L;

    /** 结果码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;

    public ResultVO(ResultEnum resultEnum, T data){
        code = resultEnum.getCode();
        msg = resultEnum.getMessage();
        this.data = data;
    }
}

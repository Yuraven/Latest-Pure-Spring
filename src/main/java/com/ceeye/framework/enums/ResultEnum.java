package com.ceeye.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 接口返回接口枚举类
 * @author Raven
 * @since 2020-02-12 15:26
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    //返回结果
    SUCCESS(0,"成功"),
    FAILURE(1,"失败"),
    USER_NEED_AUTHORITIES(10001,"用户未登录"),
    USER_PASSWORD_ERROR(10002,"密码错误"),
    USER_NO_ACCESS(10003,"用户无权访问"),
    LOGIN_IS_OVERDUE(10004,"登录已失效"),
    USER_FAILED(10005,"账号不存在"),
    PARSE_ERROR(20001,"参数错误"),
    COMMON_ERROR(20002,"发生错误"),
    SALT_ERROR(20003, "加密盐错误");

    private final Integer code;

    private final String message;
}


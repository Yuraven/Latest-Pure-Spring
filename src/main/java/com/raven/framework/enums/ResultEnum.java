package com.raven.framework.enums;

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
    SUCCESS(200,"成功"),
    FAILURE(500,"失败"),
    USER_NEED_AUTHORITIES(101,"用户未登录"),
    USER_PASSWORD_ERROR(102,"密码错误"),
    USER_NO_ACCESS(103,"用户无权访问"),
    LOGIN_IS_OVERDUE(104,"登录已失效"),
    USER_FAILED(105,"账号不存在"),
    PARSE_ERROR(201,"参数错误"),
    SQL_(202,"sql连接符错误"),
    COMMON_ERROR(203,"发生错误"),
    SALT_ERROR(20003, "加密盐错误");

    private final Integer code;

    private final String message;
}


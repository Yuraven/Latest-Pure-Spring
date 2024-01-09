package com.ceeye.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用枚举
 * @author raven
 * @since 2020-03-4 15:26
 */
@Getter
@AllArgsConstructor
public enum CommonCodeEnum {

    //默认密码
    DEFAULT_PASSWORD("123456"),

    //用户登录超时时间，单位：分钟
    LOGIN_TIMEOUT("30");

    private final String value;
}

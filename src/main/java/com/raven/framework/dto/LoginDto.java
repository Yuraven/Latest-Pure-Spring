package com.raven.framework.dto;

import lombok.Data;

/**
 * 用户登录dto
 * @author : raven
 * @since : 2020-02-21 17:04
 */
@Data
public class LoginDto {
    private String userName;
    private String password;
}

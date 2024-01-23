package com.ceeye.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户管理实体类
 * @author Raven
 * @since 2020-02-12 15:26
 */
@Data
@TableName
public class Person implements Serializable {

    private static final long serialVersionUID = 3966504008108600098L;

    /**
     * 唯一标识符
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号/工号
     */
    @TableField("userName")
    private String userName;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别（true：男性；false：女性）
     */
    private boolean gender;

    /**
     * 用户角色(0：管理员，1基地管理员，2基地教师，3学校用户，4家长)
     */
    @TableField
    private Long role;
}

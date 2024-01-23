package com.ceeye.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色管理实体类
 * @author Raven
 * @since 2020-02-12 15:26
 */
@Data
@TableName
public class Role implements Serializable {

    private static final long serialVersionUID = 7456428801743824432L;

    /**
     * 唯一标识符
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField
    private String name;
}

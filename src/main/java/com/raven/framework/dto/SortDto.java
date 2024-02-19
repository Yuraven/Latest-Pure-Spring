package com.raven.framework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 排序条件封装类
 * @author : raven
 * @since : 2020-02-12 15:26
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SortDto {

    /**
     * 排序字段 对象写法 eq: 'boy.name' 即为 boy对象的name字段, 'name' 即为该对象的name字段
     */
    private String prop;

    /**
     * 是否正序 true:正序 false：倒序
     */
    private boolean isAsc;
}

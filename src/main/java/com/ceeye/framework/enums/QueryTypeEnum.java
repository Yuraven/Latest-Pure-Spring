package com.ceeye.framework.enums;

/**
 * 查询类型枚举
 * @author raven
 * @since 2020-02-12 15:26
 */
public enum QueryTypeEnum {
    //等于
    eq,
    //不等于
    ne,
    //大于
    gt,
    //小于
    lt,
    //大于等于
    ge,
    //小于等于
    le,
    //等于多个值中其中某一个
    in,
    //不等于多个值中任意一个
    notIn,
    //模糊查
    like,
    //左模糊
    lLike,
    //右模糊
    rLike,
    //过滤模糊查的数据
    notLike,
    //介于两个值之间
    between,
    //介于两个值之外
    notBetween,
    //为空
    isNull,
    //不为空
    isNotNull
}

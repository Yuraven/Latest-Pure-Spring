package com.ceeye.framework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 分页信息封装类
 * @author : raven
 * @since : 2020-02-12 15:26
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {

    private Integer pageSize = 1;

    private Integer pageNum = 10;

}

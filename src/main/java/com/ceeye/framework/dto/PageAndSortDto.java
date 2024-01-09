package com.ceeye.framework.dto;

import lombok.Data;

import java.util.List;

/**
 * 通用分页查询dto
 * @author : raven
 * @since : 2020-02-12 15:26
 */
@Data
public class PageAndSortDto {

   /**
    * 排序条件
    */
   private List<SortDto> sorts;

   /**
    * 分页信息
    */
   private PageDto page;

   /**
    * 查询条件
    */
   private List<QueryDto> queryList;
}

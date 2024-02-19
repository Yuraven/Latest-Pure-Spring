package com.raven.framework.dto;


import com.raven.framework.enums.QueryTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 查询条件封装类
 * @author : raven
 * @since : 2020-02-12 15:26
 */
@Data
public class QueryDto {

    private String param;

    private QueryTypeEnum type;

    private List<String> value;

}

package com.raven.framework.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raven.framework.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户管理Mapper
 * @author Raven
 * @since 2020-02-12 15:26
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {
    /**
     * 查询所有
     * @return List<Person>
     */
    @Select("select * from person")
    List<Person> selectList();

    /**
     * 查询
     * @param userName
     * @return Person
     */
    @Select("select * from person where userName = #{userName}")
    Person selectUserName(String userName);
}

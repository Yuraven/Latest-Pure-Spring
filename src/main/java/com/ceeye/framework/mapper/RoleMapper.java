package com.ceeye.framework.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ceeye.framework.model.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理Mapper
 * @author Raven
 * @since 2020-02-12 15:26
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}

package com.raven.framework.service.impl;

import com.raven.framework.mapper.RoleMapper;
import com.raven.framework.entity.Role;
import com.raven.framework.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色管理 Service实现类
 * @author : raven
 * @since : 2020-02-21 15:52
 */
@Service
public class RoleServiceImpl extends IBaseServiceImpl<RoleMapper, Role> implements RoleService {
}

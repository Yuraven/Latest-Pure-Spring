package com.ceeye.framework.service.impl;

import com.ceeye.framework.mapper.RoleMapper;
import com.ceeye.framework.entity.Role;
import com.ceeye.framework.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色管理 Service实现类
 * @author : raven
 * @since : 2020-02-21 15:52
 */
@Service
public class RoleServiceImpl extends IBaseServiceImpl<RoleMapper, Role> implements RoleService {
}

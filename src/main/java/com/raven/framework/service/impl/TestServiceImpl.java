package com.raven.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raven.framework.mapper.TestMapper;
import com.raven.framework.entity.Test;
import com.raven.framework.service.TestService;
import org.springframework.stereotype.Service;

/**
 * 测试表 服务实现类
 *
 * @author raven
 * @since 2020-03-17
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}

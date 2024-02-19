package com.raven.framework.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.raven.framework.dto.PageAndSortDto;
import com.raven.framework.enums.CommonCodeEnum;
import com.raven.framework.mapper.PersonMapper;
import com.raven.framework.entity.Person;
import com.raven.framework.service.PersonService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * 用户管理 Service实现类
 *
 * @author : raven
 * @since : 2020-02-21 15:52
 */
@Service
@CacheConfig(cacheNames = "person")
public class PersonServiceImpl extends IBaseServiceImpl<PersonMapper, Person> implements PersonService {

    @Override
    @Cacheable(cacheNames = "personPage", key = "#pageAndSortDto")
    public IPage<Person> pagePerson(PageAndSortDto pageAndSortDto) {
        return super.getPageAndSortThis(pageAndSortDto);
    }

    @Override
    @Caching(
            //将修改或添加的对象缓存
            put = {
                    @CachePut(key = "#result.id", unless = "#result == null")
            },
            //清空分页缓存
            evict = {
                    @CacheEvict(cacheNames = "personPage", allEntries = true),
            }
    )
    public Person savePerson(Person person) throws NoSuchAlgorithmException {
        if (person.getId() == null) {
            if (super.save(person)) {
                String encryptPassword = encryptPassword(CommonCodeEnum.DEFAULT_PASSWORD.getValue(), person.getId());
                person.setPassword(encryptPassword);
                if (super.updateById(person)) {
                    return person;
                }
            }
        } else {
            if (super.updateById(person)) {
                return person;
            }
        }
        return null;
    }

    @Override
    @Caching(
            evict = {
                    //清空分页缓存
                    @CacheEvict(cacheNames = "personPage", allEntries = true),
                    //单查缓存
                    @CacheEvict(key = "#id")
            }
    )
    public boolean deletePerson(Long id) {
        return super.removeById(id);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Person getPerson(Long id) {
        return super.getById(id);
    }
}

package com.ceeye.framework.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceeye.framework.dto.PageAndSortDto;
import com.ceeye.framework.entity.Person;

import java.security.NoSuchAlgorithmException;

/**
 * 用户管理 Service接口
 *
 * @author : raven
 * @since : 2020-02-21 15:52
 */
public interface PersonService extends IService<Person> {

    /**
     * 分页
     *
     * @param pageAndSortDto 分页查询参数
     * @return IPage
     */
    IPage<Person> pagePerson(PageAndSortDto pageAndSortDto);

    /**
     * 保存用户
     *
     * @param person 用户对象
     * @return Person用户对象
     * @throws NoSuchAlgorithmException 加密算法错误
     */
    Person savePerson(Person person) throws NoSuchAlgorithmException;

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否成功
     */
    boolean deletePerson(Long id);

    /**
     * 通过id获取用户
     *
     * @param id 用户id
     * @return Person用户对象
     */
    Person getPerson(Long id);
}

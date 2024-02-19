package com.raven.framework.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.raven.framework.dto.PageAndSortDto;
import com.raven.framework.enums.ResultEnum;
import com.raven.framework.exception.CommonException;
import com.raven.framework.entity.Person;
import com.raven.framework.service.PersonService;
import com.raven.framework.vo.ResultVO;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户管理控制器
 *
 * @author Raven
 * @since 2020-02-12 15:26
 */
@RestController
@RequestMapping("person")
public class PersonController {

    @Resource
    private PersonService personService;

    @PostMapping(value = "/page")
    public ResultVO<IPage<Person>> page(@RequestBody PageAndSortDto pageAndSortDto) {
        try {
            return new ResultVO<>(ResultEnum.SUCCESS, personService.pagePerson(pageAndSortDto));
        } catch (Exception e) {
            throw new CommonException(ResultEnum.COMMON_ERROR, e);
        }
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<Person> save(@RequestBody Person person) {
        try {
            return new ResultVO<>(ResultEnum.SUCCESS, personService.savePerson(person));
        } catch (Exception e) {
            throw new CommonException(ResultEnum.COMMON_ERROR, e);
        }
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<Boolean> delete(@RequestBody Long id) {
        try {
            return new ResultVO<>(ResultEnum.SUCCESS, personService.deletePerson(id));
        } catch (Exception e) {
            throw new CommonException(ResultEnum.COMMON_ERROR, e);
        }
    }

    @PostMapping(value = "/getById")
    public ResultVO<Person> getById(@RequestBody Long id) {
        return new ResultVO<>(ResultEnum.SUCCESS, personService.getPerson(id));
    }
}

package com.ceeye.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import com.ceeye.framework.dto.PageAndSortDto;
import com.ceeye.framework.dto.QueryDto;
import com.ceeye.framework.dto.SortDto;
import com.ceeye.framework.enums.ResultEnum;
import com.ceeye.framework.exception.CommonException;
import com.ceeye.framework.util.Md5Util;
import com.ceeye.framework.util.PropertiesUtil;

/**
 * 通用 Service
 * @author : raven
 * @since : 2020-02-21 15:52
 */
public class IBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    /**
     * 通用分页查询
     *
     * @param pageAndSortDto 通用分页查询dto
     * @return IPage 分页内容
     */
    public IPage<T> getPageAndSortThis(PageAndSortDto pageAndSortDto) {
        Page<T> page = new Page<>();

        //分页信息
        page.setSize(pageAndSortDto.getPage().getPageSize());
        page.setCurrent(pageAndSortDto.getPage().getPageNum());

        //排序条件
        List<SortDto> sorts = pageAndSortDto.getSorts();
        List<OrderItem> orders = new LinkedList<>();
        for (SortDto sort : sorts) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAsc(sort.isAsc());
            orderItem.setColumn(sort.getProp());
            orders.add(orderItem);
        }
        page.setOrders(orders);

        //查询条件
        List<QueryDto> queryList = pageAndSortDto.getQueryList();
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (QueryDto queryDto : queryList) {
            switch (queryDto.getType()) {
                case eq:
                    wrapper.eq(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case ne:
                    wrapper.ne(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case gt:
                    wrapper.gt(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case lt:
                    wrapper.lt(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case ge:
                    wrapper.ge(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case le:
                    wrapper.le(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case like:
                    wrapper.like(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case lLike:
                    wrapper.likeLeft(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case rLike:
                    wrapper.likeRight(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case notLike:
                    wrapper.notLike(queryDto.getParam(), queryDto.getValue().get(0));
                    break;
                case isNull:
                    wrapper.isNull(queryDto.getParam());
                    break;
                case isNotNull:
                    wrapper.isNotNull(queryDto.getParam());
                    break;
                case in:
                    wrapper.in(queryDto.getParam(), queryDto.getValue());
                    break;
                case notIn:
                    wrapper.notIn(queryDto.getParam(), queryDto.getValue());
                    break;
                case between:
                    wrapper.between(queryDto.getParam(), queryDto.getValue().get(0), queryDto.getValue().get(1));
                    break;
                case notBetween:
                    wrapper.notBetween(queryDto.getParam(), queryDto.getValue().get(0), queryDto.getValue().get(1));
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return page(page, wrapper);
    }

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @param id       主键
     * @return 加密后的密码
     */
    public String encryptPassword(String password, Long id) throws NoSuchAlgorithmException {
        // 加密盐
        String saltValue = PropertiesUtil.get("MD5Str");
        if (org.springframework.util.StringUtils.isEmpty(saltValue)) {
            throw new CommonException(ResultEnum.SALT_ERROR);
        }
        String p = id + saltValue + password;
        // 加密后的串
        return Md5Util.getMd5String(p);
    }
}

package com.jf.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShoppingCartCustomMapper {

    /**
     * count最近加购的今日头条渠道用户
     */
    int countLatestAddShoppingCartHeadlineUser(Map<String, Object> params);


    /**
     * 查找最近加购的今日头条渠道用户
     */
    List<Integer> findLatestAddShoppingCartHeadlineUser(Map<String, Object> params);

}
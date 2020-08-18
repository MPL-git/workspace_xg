package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.OrderPreferentialInfoCustom;
import com.jf.entity.OrderPreferentialInfoExample;
@Repository
public interface OrderPreferentialInfoCustomMapper{
    int countByExample(OrderPreferentialInfoExample example);
    List<OrderPreferentialInfoCustom> selectByExample(OrderPreferentialInfoExample example);
    OrderPreferentialInfoCustom selectByPrimaryKey(Integer id);
    List<OrderPreferentialInfoCustom> selectByCombineOrder(Integer combineOrderId);
    List<OrderPreferentialInfoCustom> selectBySubOrder(Integer subOrderId);
}
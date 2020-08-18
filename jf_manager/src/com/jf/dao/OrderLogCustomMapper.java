package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.OrderLogCustom;
import com.jf.entity.OrderLogExample;
@Repository
public interface OrderLogCustomMapper{
    int countByExample(OrderLogExample example);
    List<OrderLogCustom> selectByExample(OrderLogExample example);
    OrderLogCustom selectByPrimaryKey(Integer id);
}
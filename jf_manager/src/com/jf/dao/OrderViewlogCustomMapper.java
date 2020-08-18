package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.OrderViewlogCustom;
import com.jf.entity.OrderViewlogCustomExample;

@Repository
public interface OrderViewlogCustomMapper {
    int orderViewlogExamplecountByExample(OrderViewlogCustomExample example);

    List<OrderViewlogCustom> orderViewlogCustomselectByExample(OrderViewlogCustomExample example);

    OrderViewlogCustom orderViewlogCustomselectByPrimaryKey(Integer id);
}
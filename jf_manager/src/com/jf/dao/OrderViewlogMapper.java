package com.jf.dao;

import com.jf.entity.OrderViewlog;
import com.jf.entity.OrderViewlogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderViewlogMapper extends BaseDao<OrderViewlog, OrderViewlogExample>{
    int countByExample(OrderViewlogExample example);

    int deleteByExample(OrderViewlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderViewlog record);

    int insertSelective(OrderViewlog record);

    List<OrderViewlog> selectByExample(OrderViewlogExample example);

    OrderViewlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderViewlog record, @Param("example") OrderViewlogExample example);

    int updateByExample(@Param("record") OrderViewlog record, @Param("example") OrderViewlogExample example);

    int updateByPrimaryKeySelective(OrderViewlog record);

    int updateByPrimaryKey(OrderViewlog record);
}
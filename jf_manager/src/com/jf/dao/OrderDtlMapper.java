package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
@Repository
public interface OrderDtlMapper extends BaseDao<OrderDtl, OrderDtlExample> {
    int countByExample(OrderDtlExample example);

    int deleteByExample(OrderDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderDtl record);

    int insertSelective(OrderDtl record);

    List<OrderDtl> selectByExample(OrderDtlExample example);

    OrderDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderDtl record, @Param("example") OrderDtlExample example);

    int updateByExample(@Param("record") OrderDtl record, @Param("example") OrderDtlExample example);

    int updateByPrimaryKeySelective(OrderDtl record);

    int updateByPrimaryKey(OrderDtl record);
}
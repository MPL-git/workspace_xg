package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.OrderPreferentialInfo;
import com.jf.entity.OrderPreferentialInfoExample;
@Repository
public interface OrderPreferentialInfoMapper extends BaseDao<OrderPreferentialInfo, OrderPreferentialInfoExample> {
    int countByExample(OrderPreferentialInfoExample example);

    int deleteByExample(OrderPreferentialInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderPreferentialInfo record);

    int insertSelective(OrderPreferentialInfo record);

    List<OrderPreferentialInfo> selectByExample(OrderPreferentialInfoExample example);

    OrderPreferentialInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderPreferentialInfo record, @Param("example") OrderPreferentialInfoExample example);

    int updateByExample(@Param("record") OrderPreferentialInfo record, @Param("example") OrderPreferentialInfoExample example);

    int updateByPrimaryKeySelective(OrderPreferentialInfo record);

    int updateByPrimaryKey(OrderPreferentialInfo record);
}
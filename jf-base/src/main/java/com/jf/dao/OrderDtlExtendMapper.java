package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.OrderDtlExtend;
import com.jf.entity.OrderDtlExtendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDtlExtendMapper extends BaseDao<OrderDtlExtend,OrderDtlExtendExample> {
    int countByExample(OrderDtlExtendExample example);

    int deleteByExample(OrderDtlExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderDtlExtend record);

    int insertSelective(OrderDtlExtend record);

    List<OrderDtlExtend> selectByExample(OrderDtlExtendExample example);

    OrderDtlExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderDtlExtend record, @Param("example") OrderDtlExtendExample example);

    int updateByExample(@Param("record") OrderDtlExtend record, @Param("example") OrderDtlExtendExample example);

    int updateByPrimaryKeySelective(OrderDtlExtend record);

    int updateByPrimaryKey(OrderDtlExtend record);
}
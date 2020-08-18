package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.OrderAbnormalLog;
import com.jf.entity.OrderAbnormalLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAbnormalLogMapper extends BaseDao<OrderAbnormalLog, OrderAbnormalLogExample> {
    int countByExample(OrderAbnormalLogExample example);

    int deleteByExample(OrderAbnormalLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderAbnormalLog record);

    int insertSelective(OrderAbnormalLog record);

    List<OrderAbnormalLog> selectByExample(OrderAbnormalLogExample example);

    OrderAbnormalLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderAbnormalLog record, @Param("example") OrderAbnormalLogExample example);

    int updateByExample(@Param("record") OrderAbnormalLog record, @Param("example") OrderAbnormalLogExample example);

    int updateByPrimaryKeySelective(OrderAbnormalLog record);

    int updateByPrimaryKey(OrderAbnormalLog record);
}

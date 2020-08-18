package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.OrderViewLog;
import com.jf.entity.OrderViewLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderViewLogMapper extends BaseDao<OrderViewLog, OrderViewLogExample> {
    int countByExample(OrderViewLogExample example);

    int deleteByExample(OrderViewLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderViewLog record);

    int insertSelective(OrderViewLog record);

    List<OrderViewLog> selectByExample(OrderViewLogExample example);

    OrderViewLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderViewLog record, @Param("example") OrderViewLogExample example);

    int updateByExample(@Param("record") OrderViewLog record, @Param("example") OrderViewLogExample example);

    int updateByPrimaryKeySelective(OrderViewLog record);

    int updateByPrimaryKey(OrderViewLog record);
}

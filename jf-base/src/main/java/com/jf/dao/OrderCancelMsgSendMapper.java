package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.OrderCancelMsgSend;
import com.jf.entity.OrderCancelMsgSendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCancelMsgSendMapper extends BaseDao<OrderCancelMsgSend, OrderCancelMsgSendExample> {
    int countByExample(OrderCancelMsgSendExample example);

    int deleteByExample(OrderCancelMsgSendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderCancelMsgSend record);

    int insertSelective(OrderCancelMsgSend record);

    List<OrderCancelMsgSend> selectByExample(OrderCancelMsgSendExample example);

    OrderCancelMsgSend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderCancelMsgSend record, @Param("example") OrderCancelMsgSendExample example);

    int updateByExample(@Param("record") OrderCancelMsgSend record, @Param("example") OrderCancelMsgSendExample example);

    int updateByPrimaryKeySelective(OrderCancelMsgSend record);

    int updateByPrimaryKey(OrderCancelMsgSend record);
}

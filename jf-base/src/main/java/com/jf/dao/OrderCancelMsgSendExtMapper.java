package com.jf.dao;

import com.jf.entity.OrderCancelMsgSendExt;
import com.jf.entity.OrderCancelMsgSendExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCancelMsgSendExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    OrderCancelMsgSendExt findById(int id);

    OrderCancelMsgSendExt find(OrderCancelMsgSendExtExample example);

    List<OrderCancelMsgSendExt> list(OrderCancelMsgSendExtExample example);

    List<Integer> listId(OrderCancelMsgSendExtExample example);

    int count(OrderCancelMsgSendExtExample example);

    long sum(@Param("field") String field, @Param("example") OrderCancelMsgSendExtExample example);

    int max(@Param("field") String field, @Param("example") OrderCancelMsgSendExtExample example);

    int min(@Param("field") String field, @Param("example") OrderCancelMsgSendExtExample example);

}


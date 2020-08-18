package com.jf.dao;

import com.jf.entity.OrderDtlExt;
import com.jf.entity.OrderDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    OrderDtlExt findById(int id);

    OrderDtlExt find(OrderDtlExtExample example);

    List<OrderDtlExt> list(OrderDtlExtExample example);

    List<Integer> listId(OrderDtlExtExample example);

    int count(OrderDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") OrderDtlExtExample example);

    int max(@Param("field") String field, @Param("example") OrderDtlExtExample example);

    int min(@Param("field") String field, @Param("example") OrderDtlExtExample example);

}


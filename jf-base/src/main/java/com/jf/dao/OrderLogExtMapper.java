package com.jf.dao;

import com.jf.entity.OrderLogExt;
import com.jf.entity.OrderLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    OrderLogExt findById(int id);

    OrderLogExt find(OrderLogExtExample example);

    List<OrderLogExt> list(OrderLogExtExample example);

    List<Integer> listId(OrderLogExtExample example);

    int count(OrderLogExtExample example);

    long sum(@Param("field") String field, @Param("example") OrderLogExtExample example);

    int max(@Param("field") String field, @Param("example") OrderLogExtExample example);

    int min(@Param("field") String field, @Param("example") OrderLogExtExample example);

}


package com.jf.dao;

import com.jf.entity.OrderViewLogExt;
import com.jf.entity.OrderViewLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderViewLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    OrderViewLogExt findById(int id);

    OrderViewLogExt find(OrderViewLogExtExample example);

    List<OrderViewLogExt> list(OrderViewLogExtExample example);

    List<Integer> listId(OrderViewLogExtExample example);

    int count(OrderViewLogExtExample example);

    long sum(@Param("field") String field, @Param("example") OrderViewLogExtExample example);

    int max(@Param("field") String field, @Param("example") OrderViewLogExtExample example);

    int min(@Param("field") String field, @Param("example") OrderViewLogExtExample example);

}


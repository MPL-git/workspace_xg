package com.jf.dao;

import com.jf.entity.OrderPreferentialInfoExt;
import com.jf.entity.OrderPreferentialInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPreferentialInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    OrderPreferentialInfoExt findById(int id);

    OrderPreferentialInfoExt find(OrderPreferentialInfoExtExample example);

    List<OrderPreferentialInfoExt> list(OrderPreferentialInfoExtExample example);

    List<Integer> listId(OrderPreferentialInfoExtExample example);

    int count(OrderPreferentialInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") OrderPreferentialInfoExtExample example);

    int max(@Param("field") String field, @Param("example") OrderPreferentialInfoExtExample example);

    int min(@Param("field") String field, @Param("example") OrderPreferentialInfoExtExample example);

}


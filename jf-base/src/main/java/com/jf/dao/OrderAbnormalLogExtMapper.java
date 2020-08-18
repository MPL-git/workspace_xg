package com.jf.dao;

import com.jf.entity.OrderAbnormalLogExt;
import com.jf.entity.OrderAbnormalLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAbnormalLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    OrderAbnormalLogExt findById(int id);

    OrderAbnormalLogExt find(OrderAbnormalLogExtExample example);

    List<OrderAbnormalLogExt> list(OrderAbnormalLogExtExample example);

    List<Integer> listId(OrderAbnormalLogExtExample example);

    int count(OrderAbnormalLogExtExample example);

    long sum(@Param("field") String field, @Param("example") OrderAbnormalLogExtExample example);

    int max(@Param("field") String field, @Param("example") OrderAbnormalLogExtExample example);

    int min(@Param("field") String field, @Param("example") OrderAbnormalLogExtExample example);

}


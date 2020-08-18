package com.jf.dao;

import com.jf.entity.OrderProductSnapshotExt;
import com.jf.entity.OrderProductSnapshotExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductSnapshotExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    OrderProductSnapshotExt findById(int id);

    OrderProductSnapshotExt find(OrderProductSnapshotExtExample example);

    List<OrderProductSnapshotExt> list(OrderProductSnapshotExtExample example);

    List<Integer> listId(OrderProductSnapshotExtExample example);

    int count(OrderProductSnapshotExtExample example);

    long sum(@Param("field") String field, @Param("example") OrderProductSnapshotExtExample example);

    int max(@Param("field") String field, @Param("example") OrderProductSnapshotExtExample example);

    int min(@Param("field") String field, @Param("example") OrderProductSnapshotExtExample example);

}


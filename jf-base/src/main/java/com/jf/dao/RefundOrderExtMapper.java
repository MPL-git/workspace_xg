package com.jf.dao;

import com.jf.entity.RefundOrderExt;
import com.jf.entity.RefundOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    RefundOrderExt findById(int id);

    RefundOrderExt find(RefundOrderExtExample example);

    List<RefundOrderExt> list(RefundOrderExtExample example);

    List<Integer> listId(RefundOrderExtExample example);

    int count(RefundOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") RefundOrderExtExample example);

    int max(@Param("field") String field, @Param("example") RefundOrderExtExample example);

    int min(@Param("field") String field, @Param("example") RefundOrderExtExample example);

}


package com.jf.dao;

import com.jf.entity.InterventionOrderLogExt;
import com.jf.entity.InterventionOrderLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionOrderLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    InterventionOrderLogExt findById(int id);

    InterventionOrderLogExt find(InterventionOrderLogExtExample example);

    List<InterventionOrderLogExt> list(InterventionOrderLogExtExample example);

    List<Integer> listId(InterventionOrderLogExtExample example);

    int count(InterventionOrderLogExtExample example);

    long sum(@Param("field") String field, @Param("example") InterventionOrderLogExtExample example);

    int max(@Param("field") String field, @Param("example") InterventionOrderLogExtExample example);

    int min(@Param("field") String field, @Param("example") InterventionOrderLogExtExample example);

}


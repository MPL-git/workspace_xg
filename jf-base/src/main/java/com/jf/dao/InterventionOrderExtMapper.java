package com.jf.dao;

import com.jf.entity.InterventionOrderExt;
import com.jf.entity.InterventionOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    InterventionOrderExt findById(int id);

    InterventionOrderExt find(InterventionOrderExtExample example);

    List<InterventionOrderExt> list(InterventionOrderExtExample example);

    List<Integer> listId(InterventionOrderExtExample example);

    int count(InterventionOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") InterventionOrderExtExample example);

    int max(@Param("field") String field, @Param("example") InterventionOrderExtExample example);

    int min(@Param("field") String field, @Param("example") InterventionOrderExtExample example);

}


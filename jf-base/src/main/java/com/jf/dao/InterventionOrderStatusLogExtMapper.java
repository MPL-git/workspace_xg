package com.jf.dao;

import com.jf.entity.InterventionOrderStatusLogExt;
import com.jf.entity.InterventionOrderStatusLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionOrderStatusLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    InterventionOrderStatusLogExt findById(int id);

    InterventionOrderStatusLogExt find(InterventionOrderStatusLogExtExample example);

    List<InterventionOrderStatusLogExt> list(InterventionOrderStatusLogExtExample example);

    List<Integer> listId(InterventionOrderStatusLogExtExample example);

    int count(InterventionOrderStatusLogExtExample example);

    long sum(@Param("field") String field, @Param("example") InterventionOrderStatusLogExtExample example);

    int max(@Param("field") String field, @Param("example") InterventionOrderStatusLogExtExample example);

    int min(@Param("field") String field, @Param("example") InterventionOrderStatusLogExtExample example);

}


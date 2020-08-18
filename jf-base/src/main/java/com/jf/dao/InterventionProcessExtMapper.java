package com.jf.dao;

import com.jf.entity.InterventionProcessExt;
import com.jf.entity.InterventionProcessExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionProcessExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    InterventionProcessExt findById(int id);

    InterventionProcessExt find(InterventionProcessExtExample example);

    List<InterventionProcessExt> list(InterventionProcessExtExample example);

    List<Integer> listId(InterventionProcessExtExample example);

    int count(InterventionProcessExtExample example);

    long sum(@Param("field") String field, @Param("example") InterventionProcessExtExample example);

    int max(@Param("field") String field, @Param("example") InterventionProcessExtExample example);

    int min(@Param("field") String field, @Param("example") InterventionProcessExtExample example);

}


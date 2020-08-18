package com.jf.dao;

import com.jf.entity.InterventionProcessPicExt;
import com.jf.entity.InterventionProcessPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionProcessPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    InterventionProcessPicExt findById(int id);

    InterventionProcessPicExt find(InterventionProcessPicExtExample example);

    List<InterventionProcessPicExt> list(InterventionProcessPicExtExample example);

    List<Integer> listId(InterventionProcessPicExtExample example);

    int count(InterventionProcessPicExtExample example);

    long sum(@Param("field") String field, @Param("example") InterventionProcessPicExtExample example);

    int max(@Param("field") String field, @Param("example") InterventionProcessPicExtExample example);

    int min(@Param("field") String field, @Param("example") InterventionProcessPicExtExample example);

}


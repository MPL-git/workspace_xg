package com.jf.dao;

import com.jf.entity.ActivityAreaTempletParamExt;
import com.jf.entity.ActivityAreaTempletParamExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAreaTempletParamExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityAreaTempletParamExt findById(int id);

    ActivityAreaTempletParamExt find(ActivityAreaTempletParamExtExample example);

    List<ActivityAreaTempletParamExt> list(ActivityAreaTempletParamExtExample example);

    List<Integer> listId(ActivityAreaTempletParamExtExample example);

    int count(ActivityAreaTempletParamExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityAreaTempletParamExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityAreaTempletParamExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityAreaTempletParamExtExample example);

}


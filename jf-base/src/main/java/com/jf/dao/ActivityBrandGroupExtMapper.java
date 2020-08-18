package com.jf.dao;

import com.jf.entity.ActivityBrandGroupExt;
import com.jf.entity.ActivityBrandGroupExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityBrandGroupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityBrandGroupExt findById(int id);

    ActivityBrandGroupExt find(ActivityBrandGroupExtExample example);

    List<ActivityBrandGroupExt> list(ActivityBrandGroupExtExample example);

    List<Integer> listId(ActivityBrandGroupExtExample example);

    int count(ActivityBrandGroupExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityBrandGroupExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityBrandGroupExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityBrandGroupExtExample example);

}


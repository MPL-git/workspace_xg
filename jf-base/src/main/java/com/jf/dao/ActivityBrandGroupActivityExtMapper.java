package com.jf.dao;

import com.jf.entity.ActivityBrandGroupActivityExt;
import com.jf.entity.ActivityBrandGroupActivityExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityBrandGroupActivityExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityBrandGroupActivityExt findById(int id);

    ActivityBrandGroupActivityExt find(ActivityBrandGroupActivityExtExample example);

    List<ActivityBrandGroupActivityExt> list(ActivityBrandGroupActivityExtExample example);

    List<Integer> listId(ActivityBrandGroupActivityExtExample example);

    int count(ActivityBrandGroupActivityExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityBrandGroupActivityExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityBrandGroupActivityExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityBrandGroupActivityExtExample example);

}


package com.jf.dao;

import com.jf.entity.ActivityAreaDecorateExt;
import com.jf.entity.ActivityAreaDecorateExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAreaDecorateExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityAreaDecorateExt findById(int id);

    ActivityAreaDecorateExt find(ActivityAreaDecorateExtExample example);

    List<ActivityAreaDecorateExt> list(ActivityAreaDecorateExtExample example);

    List<Integer> listId(ActivityAreaDecorateExtExample example);

    int count(ActivityAreaDecorateExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityAreaDecorateExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityAreaDecorateExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityAreaDecorateExtExample example);

}


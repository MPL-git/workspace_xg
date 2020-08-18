package com.jf.dao;

import com.jf.entity.ActivityProductExt;
import com.jf.entity.ActivityProductExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityProductExt findById(int id);

    ActivityProductExt find(ActivityProductExtExample example);

    List<ActivityProductExt> list(ActivityProductExtExample example);

    List<Integer> listId(ActivityProductExtExample example);

    int count(ActivityProductExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityProductExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityProductExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityProductExtExample example);

}


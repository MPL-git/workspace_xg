package com.jf.dao;

import com.jf.entity.MchtOptimizeExt;
import com.jf.entity.MchtOptimizeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtOptimizeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtOptimizeExt findById(int id);

    MchtOptimizeExt find(MchtOptimizeExtExample example);

    List<MchtOptimizeExt> list(MchtOptimizeExtExample example);

    List<Integer> listId(MchtOptimizeExtExample example);

    int count(MchtOptimizeExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtOptimizeExtExample example);

    int max(@Param("field") String field, @Param("example") MchtOptimizeExtExample example);

    int min(@Param("field") String field, @Param("example") MchtOptimizeExtExample example);

}


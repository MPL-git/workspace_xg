package com.jf.dao;

import com.jf.entity.AreaExt;
import com.jf.entity.AreaExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AreaExt findById(int id);

    AreaExt find(AreaExtExample example);

    List<AreaExt> list(AreaExtExample example);

    List<Integer> listId(AreaExtExample example);

    int count(AreaExtExample example);

    long sum(@Param("field") String field, @Param("example") AreaExtExample example);

    int max(@Param("field") String field, @Param("example") AreaExtExample example);

    int min(@Param("field") String field, @Param("example") AreaExtExample example);

}


package com.jf.dao;

import com.jf.entity.SpreadActivityGroupExt;
import com.jf.entity.SpreadActivityGroupExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadActivityGroupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpreadActivityGroupExt findById(int id);

    SpreadActivityGroupExt find(SpreadActivityGroupExtExample example);

    List<SpreadActivityGroupExt> list(SpreadActivityGroupExtExample example);

    List<Integer> listId(SpreadActivityGroupExtExample example);

    int count(SpreadActivityGroupExtExample example);

    long sum(@Param("field") String field, @Param("example") SpreadActivityGroupExtExample example);

    int max(@Param("field") String field, @Param("example") SpreadActivityGroupExtExample example);

    int min(@Param("field") String field, @Param("example") SpreadActivityGroupExtExample example);

}


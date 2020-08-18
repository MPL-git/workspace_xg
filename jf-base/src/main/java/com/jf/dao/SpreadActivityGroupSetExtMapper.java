package com.jf.dao;

import com.jf.entity.SpreadActivityGroupSetExt;
import com.jf.entity.SpreadActivityGroupSetExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadActivityGroupSetExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpreadActivityGroupSetExt findById(int id);

    SpreadActivityGroupSetExt find(SpreadActivityGroupSetExtExample example);

    List<SpreadActivityGroupSetExt> list(SpreadActivityGroupSetExtExample example);

    List<Integer> listId(SpreadActivityGroupSetExtExample example);

    int count(SpreadActivityGroupSetExtExample example);

    long sum(@Param("field") String field, @Param("example") SpreadActivityGroupSetExtExample example);

    int max(@Param("field") String field, @Param("example") SpreadActivityGroupSetExtExample example);

    int min(@Param("field") String field, @Param("example") SpreadActivityGroupSetExtExample example);

}


package com.jf.dao;

import com.jf.entity.SpreadActivityGroupSetDtlExt;
import com.jf.entity.SpreadActivityGroupSetDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadActivityGroupSetDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpreadActivityGroupSetDtlExt findById(int id);

    SpreadActivityGroupSetDtlExt find(SpreadActivityGroupSetDtlExtExample example);

    List<SpreadActivityGroupSetDtlExt> list(SpreadActivityGroupSetDtlExtExample example);

    List<Integer> listId(SpreadActivityGroupSetDtlExtExample example);

    int count(SpreadActivityGroupSetDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") SpreadActivityGroupSetDtlExtExample example);

    int max(@Param("field") String field, @Param("example") SpreadActivityGroupSetDtlExtExample example);

    int min(@Param("field") String field, @Param("example") SpreadActivityGroupSetDtlExtExample example);

}


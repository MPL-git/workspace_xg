package com.jf.dao;

import com.jf.entity.CutPriceOrderDtlExt;
import com.jf.entity.CutPriceOrderDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceOrderDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutPriceOrderDtlExt findById(int id);

    CutPriceOrderDtlExt find(CutPriceOrderDtlExtExample example);

    List<CutPriceOrderDtlExt> list(CutPriceOrderDtlExtExample example);

    List<Integer> listId(CutPriceOrderDtlExtExample example);

    int count(CutPriceOrderDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") CutPriceOrderDtlExtExample example);

    int max(@Param("field") String field, @Param("example") CutPriceOrderDtlExtExample example);

    int min(@Param("field") String field, @Param("example") CutPriceOrderDtlExtExample example);

}


package com.jf.dao;

import com.jf.entity.CutPriceCnfDtlExt;
import com.jf.entity.CutPriceCnfDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceCnfDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutPriceCnfDtlExt findById(int id);

    CutPriceCnfDtlExt find(CutPriceCnfDtlExtExample example);

    List<CutPriceCnfDtlExt> list(CutPriceCnfDtlExtExample example);

    List<Integer> listId(CutPriceCnfDtlExtExample example);

    int count(CutPriceCnfDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") CutPriceCnfDtlExtExample example);

    int max(@Param("field") String field, @Param("example") CutPriceCnfDtlExtExample example);

    int min(@Param("field") String field, @Param("example") CutPriceCnfDtlExtExample example);

}


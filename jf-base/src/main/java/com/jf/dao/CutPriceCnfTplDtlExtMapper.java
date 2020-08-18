package com.jf.dao;

import com.jf.entity.CutPriceCnfTplDtlExt;
import com.jf.entity.CutPriceCnfTplDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceCnfTplDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutPriceCnfTplDtlExt findById(int id);

    CutPriceCnfTplDtlExt find(CutPriceCnfTplDtlExtExample example);

    List<CutPriceCnfTplDtlExt> list(CutPriceCnfTplDtlExtExample example);

    List<Integer> listId(CutPriceCnfTplDtlExtExample example);

    int count(CutPriceCnfTplDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") CutPriceCnfTplDtlExtExample example);

    int max(@Param("field") String field, @Param("example") CutPriceCnfTplDtlExtExample example);

    int min(@Param("field") String field, @Param("example") CutPriceCnfTplDtlExtExample example);

}


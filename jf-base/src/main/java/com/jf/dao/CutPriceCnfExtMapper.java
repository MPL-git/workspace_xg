package com.jf.dao;

import com.jf.entity.CutPriceCnfExt;
import com.jf.entity.CutPriceCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutPriceCnfExt findById(int id);

    CutPriceCnfExt find(CutPriceCnfExtExample example);

    List<CutPriceCnfExt> list(CutPriceCnfExtExample example);

    List<Integer> listId(CutPriceCnfExtExample example);

    int count(CutPriceCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") CutPriceCnfExtExample example);

    int max(@Param("field") String field, @Param("example") CutPriceCnfExtExample example);

    int min(@Param("field") String field, @Param("example") CutPriceCnfExtExample example);

}


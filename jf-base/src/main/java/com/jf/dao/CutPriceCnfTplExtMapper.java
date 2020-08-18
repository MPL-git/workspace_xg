package com.jf.dao;

import com.jf.entity.CutPriceCnfTplExt;
import com.jf.entity.CutPriceCnfTplExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceCnfTplExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutPriceCnfTplExt findById(int id);

    CutPriceCnfTplExt find(CutPriceCnfTplExtExample example);

    List<CutPriceCnfTplExt> list(CutPriceCnfTplExtExample example);

    List<Integer> listId(CutPriceCnfTplExtExample example);

    int count(CutPriceCnfTplExtExample example);

    long sum(@Param("field") String field, @Param("example") CutPriceCnfTplExtExample example);

    int max(@Param("field") String field, @Param("example") CutPriceCnfTplExtExample example);

    int min(@Param("field") String field, @Param("example") CutPriceCnfTplExtExample example);

}


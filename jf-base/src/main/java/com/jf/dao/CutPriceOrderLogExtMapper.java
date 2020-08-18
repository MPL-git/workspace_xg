package com.jf.dao;

import com.jf.entity.CutPriceOrderLogExt;
import com.jf.entity.CutPriceOrderLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceOrderLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutPriceOrderLogExt findById(int id);

    CutPriceOrderLogExt find(CutPriceOrderLogExtExample example);

    List<CutPriceOrderLogExt> list(CutPriceOrderLogExtExample example);

    List<Integer> listId(CutPriceOrderLogExtExample example);

    int count(CutPriceOrderLogExtExample example);

    long sum(@Param("field") String field, @Param("example") CutPriceOrderLogExtExample example);

    int max(@Param("field") String field, @Param("example") CutPriceOrderLogExtExample example);

    int min(@Param("field") String field, @Param("example") CutPriceOrderLogExtExample example);

}


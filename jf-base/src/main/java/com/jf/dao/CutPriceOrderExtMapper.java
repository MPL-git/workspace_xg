package com.jf.dao;

import com.jf.entity.CutPriceOrderExt;
import com.jf.entity.CutPriceOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutPriceOrderExt findById(int id);

    CutPriceOrderExt find(CutPriceOrderExtExample example);

    List<CutPriceOrderExt> list(CutPriceOrderExtExample example);

    List<Integer> listId(CutPriceOrderExtExample example);

    int count(CutPriceOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") CutPriceOrderExtExample example);

    int max(@Param("field") String field, @Param("example") CutPriceOrderExtExample example);

    int min(@Param("field") String field, @Param("example") CutPriceOrderExtExample example);

}


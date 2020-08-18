package com.jf.dao;

import com.jf.entity.PvWeightCnfExt;
import com.jf.entity.PvWeightCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PvWeightCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PvWeightCnfExt findById(int id);

    PvWeightCnfExt find(PvWeightCnfExtExample example);

    List<PvWeightCnfExt> list(PvWeightCnfExtExample example);

    List<Integer> listId(PvWeightCnfExtExample example);

    int count(PvWeightCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") PvWeightCnfExtExample example);

    int max(@Param("field") String field, @Param("example") PvWeightCnfExtExample example);

    int min(@Param("field") String field, @Param("example") PvWeightCnfExtExample example);

}


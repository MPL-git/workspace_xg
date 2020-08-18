package com.jf.dao;

import com.jf.entity.SeasonWeightCnfExt;
import com.jf.entity.SeasonWeightCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonWeightCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SeasonWeightCnfExt findById(int id);

    SeasonWeightCnfExt find(SeasonWeightCnfExtExample example);

    List<SeasonWeightCnfExt> list(SeasonWeightCnfExtExample example);

    List<Integer> listId(SeasonWeightCnfExtExample example);

    int count(SeasonWeightCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") SeasonWeightCnfExtExample example);

    int max(@Param("field") String field, @Param("example") SeasonWeightCnfExtExample example);

    int min(@Param("field") String field, @Param("example") SeasonWeightCnfExtExample example);

}


package com.jf.dao;

import com.jf.entity.MchtSettleSituationExt;
import com.jf.entity.MchtSettleSituationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettleSituationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSettleSituationExt findById(int id);

    MchtSettleSituationExt find(MchtSettleSituationExtExample example);

    List<MchtSettleSituationExt> list(MchtSettleSituationExtExample example);

    List<Integer> listId(MchtSettleSituationExtExample example);

    int count(MchtSettleSituationExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSettleSituationExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSettleSituationExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSettleSituationExtExample example);

}


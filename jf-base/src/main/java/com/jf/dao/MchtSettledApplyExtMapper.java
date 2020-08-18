package com.jf.dao;

import com.jf.entity.MchtSettledApplyExt;
import com.jf.entity.MchtSettledApplyExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettledApplyExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSettledApplyExt findById(int id);

    MchtSettledApplyExt find(MchtSettledApplyExtExample example);

    List<MchtSettledApplyExt> list(MchtSettledApplyExtExample example);

    List<Integer> listId(MchtSettledApplyExtExample example);

    int count(MchtSettledApplyExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSettledApplyExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSettledApplyExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSettledApplyExtExample example);

}


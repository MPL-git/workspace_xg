package com.jf.dao;

import com.jf.entity.MchtPvStatisticalExt;
import com.jf.entity.MchtPvStatisticalExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtPvStatisticalExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtPvStatisticalExt findById(int id);

    MchtPvStatisticalExt find(MchtPvStatisticalExtExample example);

    List<MchtPvStatisticalExt> list(MchtPvStatisticalExtExample example);

    List<Integer> listId(MchtPvStatisticalExtExample example);

    int count(MchtPvStatisticalExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtPvStatisticalExtExample example);

    int max(@Param("field") String field, @Param("example") MchtPvStatisticalExtExample example);

    int min(@Param("field") String field, @Param("example") MchtPvStatisticalExtExample example);

}


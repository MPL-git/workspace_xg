package com.jf.dao;

import com.jf.entity.AdPvStatisticalExt;
import com.jf.entity.AdPvStatisticalExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdPvStatisticalExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AdPvStatisticalExt findById(int id);

    AdPvStatisticalExt find(AdPvStatisticalExtExample example);

    List<AdPvStatisticalExt> list(AdPvStatisticalExtExample example);

    List<Integer> listId(AdPvStatisticalExtExample example);

    int count(AdPvStatisticalExtExample example);

    long sum(@Param("field") String field, @Param("example") AdPvStatisticalExtExample example);

    int max(@Param("field") String field, @Param("example") AdPvStatisticalExtExample example);

    int min(@Param("field") String field, @Param("example") AdPvStatisticalExtExample example);

}


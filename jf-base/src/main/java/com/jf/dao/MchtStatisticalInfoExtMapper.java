package com.jf.dao;

import com.jf.entity.MchtStatisticalInfoExt;
import com.jf.entity.MchtStatisticalInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtStatisticalInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtStatisticalInfoExt findById(int id);

    MchtStatisticalInfoExt find(MchtStatisticalInfoExtExample example);

    List<MchtStatisticalInfoExt> list(MchtStatisticalInfoExtExample example);

    List<Integer> listId(MchtStatisticalInfoExtExample example);

    int count(MchtStatisticalInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtStatisticalInfoExtExample example);

    int max(@Param("field") String field, @Param("example") MchtStatisticalInfoExtExample example);

    int min(@Param("field") String field, @Param("example") MchtStatisticalInfoExtExample example);

}


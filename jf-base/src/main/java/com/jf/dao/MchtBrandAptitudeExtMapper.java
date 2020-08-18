package com.jf.dao;

import com.jf.entity.MchtBrandAptitudeExt;
import com.jf.entity.MchtBrandAptitudeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandAptitudeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandAptitudeExt findById(int id);

    MchtBrandAptitudeExt find(MchtBrandAptitudeExtExample example);

    List<MchtBrandAptitudeExt> list(MchtBrandAptitudeExtExample example);

    List<Integer> listId(MchtBrandAptitudeExtExample example);

    int count(MchtBrandAptitudeExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandAptitudeExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandAptitudeExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandAptitudeExtExample example);

}


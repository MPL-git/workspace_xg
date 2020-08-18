package com.jf.dao;

import com.jf.entity.MchtBrandAptitudeChgExt;
import com.jf.entity.MchtBrandAptitudeChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandAptitudeChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandAptitudeChgExt findById(int id);

    MchtBrandAptitudeChgExt find(MchtBrandAptitudeChgExtExample example);

    List<MchtBrandAptitudeChgExt> list(MchtBrandAptitudeChgExtExample example);

    List<Integer> listId(MchtBrandAptitudeChgExtExample example);

    int count(MchtBrandAptitudeChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandAptitudeChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandAptitudeChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandAptitudeChgExtExample example);

}


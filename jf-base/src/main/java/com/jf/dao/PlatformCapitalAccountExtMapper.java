package com.jf.dao;

import com.jf.entity.PlatformCapitalAccountExt;
import com.jf.entity.PlatformCapitalAccountExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformCapitalAccountExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PlatformCapitalAccountExt findById(int id);

    PlatformCapitalAccountExt find(PlatformCapitalAccountExtExample example);

    List<PlatformCapitalAccountExt> list(PlatformCapitalAccountExtExample example);

    List<Integer> listId(PlatformCapitalAccountExtExample example);

    int count(PlatformCapitalAccountExtExample example);

    long sum(@Param("field") String field, @Param("example") PlatformCapitalAccountExtExample example);

    int max(@Param("field") String field, @Param("example") PlatformCapitalAccountExtExample example);

    int min(@Param("field") String field, @Param("example") PlatformCapitalAccountExtExample example);

}


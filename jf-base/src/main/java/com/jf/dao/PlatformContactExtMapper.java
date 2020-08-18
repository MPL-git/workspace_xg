package com.jf.dao;

import com.jf.entity.PlatformContactExt;
import com.jf.entity.PlatformContactExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformContactExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PlatformContactExt findById(int id);

    PlatformContactExt find(PlatformContactExtExample example);

    List<PlatformContactExt> list(PlatformContactExtExample example);

    List<Integer> listId(PlatformContactExtExample example);

    int count(PlatformContactExtExample example);

    long sum(@Param("field") String field, @Param("example") PlatformContactExtExample example);

    int max(@Param("field") String field, @Param("example") PlatformContactExtExample example);

    int min(@Param("field") String field, @Param("example") PlatformContactExtExample example);

}


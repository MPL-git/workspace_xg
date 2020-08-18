package com.jf.dao;

import com.jf.entity.MchtPlatformContactExt;
import com.jf.entity.MchtPlatformContactExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtPlatformContactExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtPlatformContactExt findById(int id);

    MchtPlatformContactExt find(MchtPlatformContactExtExample example);

    List<MchtPlatformContactExt> list(MchtPlatformContactExtExample example);

    List<Integer> listId(MchtPlatformContactExtExample example);

    int count(MchtPlatformContactExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtPlatformContactExtExample example);

    int max(@Param("field") String field, @Param("example") MchtPlatformContactExtExample example);

    int min(@Param("field") String field, @Param("example") MchtPlatformContactExtExample example);

}


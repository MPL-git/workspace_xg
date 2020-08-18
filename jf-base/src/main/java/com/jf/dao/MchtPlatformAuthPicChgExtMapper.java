package com.jf.dao;

import com.jf.entity.MchtPlatformAuthPicChgExt;
import com.jf.entity.MchtPlatformAuthPicChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtPlatformAuthPicChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtPlatformAuthPicChgExt findById(int id);

    MchtPlatformAuthPicChgExt find(MchtPlatformAuthPicChgExtExample example);

    List<MchtPlatformAuthPicChgExt> list(MchtPlatformAuthPicChgExtExample example);

    List<Integer> listId(MchtPlatformAuthPicChgExtExample example);

    int count(MchtPlatformAuthPicChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtPlatformAuthPicChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtPlatformAuthPicChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtPlatformAuthPicChgExtExample example);

}


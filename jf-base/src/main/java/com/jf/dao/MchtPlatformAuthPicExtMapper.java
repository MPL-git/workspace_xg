package com.jf.dao;

import com.jf.entity.MchtPlatformAuthPicExt;
import com.jf.entity.MchtPlatformAuthPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtPlatformAuthPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtPlatformAuthPicExt findById(int id);

    MchtPlatformAuthPicExt find(MchtPlatformAuthPicExtExample example);

    List<MchtPlatformAuthPicExt> list(MchtPlatformAuthPicExtExample example);

    List<Integer> listId(MchtPlatformAuthPicExtExample example);

    int count(MchtPlatformAuthPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtPlatformAuthPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtPlatformAuthPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtPlatformAuthPicExtExample example);

}


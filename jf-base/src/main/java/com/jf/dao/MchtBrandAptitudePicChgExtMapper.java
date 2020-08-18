package com.jf.dao;

import com.jf.entity.MchtBrandAptitudePicChgExt;
import com.jf.entity.MchtBrandAptitudePicChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandAptitudePicChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandAptitudePicChgExt findById(int id);

    MchtBrandAptitudePicChgExt find(MchtBrandAptitudePicChgExtExample example);

    List<MchtBrandAptitudePicChgExt> list(MchtBrandAptitudePicChgExtExample example);

    List<Integer> listId(MchtBrandAptitudePicChgExtExample example);

    int count(MchtBrandAptitudePicChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandAptitudePicChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandAptitudePicChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandAptitudePicChgExtExample example);

}


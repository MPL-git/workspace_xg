package com.jf.dao;

import com.jf.entity.MchtBrandAptitudePicExt;
import com.jf.entity.MchtBrandAptitudePicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandAptitudePicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandAptitudePicExt findById(int id);

    MchtBrandAptitudePicExt find(MchtBrandAptitudePicExtExample example);

    List<MchtBrandAptitudePicExt> list(MchtBrandAptitudePicExtExample example);

    List<Integer> listId(MchtBrandAptitudePicExtExample example);

    int count(MchtBrandAptitudePicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandAptitudePicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandAptitudePicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandAptitudePicExtExample example);

}


package com.jf.dao;

import com.jf.entity.MchtBrandChgExt;
import com.jf.entity.MchtBrandChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandChgExt findById(int id);

    MchtBrandChgExt find(MchtBrandChgExtExample example);

    List<MchtBrandChgExt> list(MchtBrandChgExtExample example);

    List<Integer> listId(MchtBrandChgExtExample example);

    int count(MchtBrandChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandChgExtExample example);

}


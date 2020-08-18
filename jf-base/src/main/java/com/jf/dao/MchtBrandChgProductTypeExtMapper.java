package com.jf.dao;

import com.jf.entity.MchtBrandChgProductTypeExt;
import com.jf.entity.MchtBrandChgProductTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandChgProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandChgProductTypeExt findById(int id);

    MchtBrandChgProductTypeExt find(MchtBrandChgProductTypeExtExample example);

    List<MchtBrandChgProductTypeExt> list(MchtBrandChgProductTypeExtExample example);

    List<Integer> listId(MchtBrandChgProductTypeExtExample example);

    int count(MchtBrandChgProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandChgProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandChgProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandChgProductTypeExtExample example);

}


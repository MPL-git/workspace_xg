package com.jf.dao;

import com.jf.entity.SeckillBrandGroupProductExt;
import com.jf.entity.SeckillBrandGroupProductExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeckillBrandGroupProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SeckillBrandGroupProductExt findById(int id);

    SeckillBrandGroupProductExt find(SeckillBrandGroupProductExtExample example);

    List<SeckillBrandGroupProductExt> list(SeckillBrandGroupProductExtExample example);

    List<Integer> listId(SeckillBrandGroupProductExtExample example);

    int count(SeckillBrandGroupProductExtExample example);

    long sum(@Param("field") String field, @Param("example") SeckillBrandGroupProductExtExample example);

    int max(@Param("field") String field, @Param("example") SeckillBrandGroupProductExtExample example);

    int min(@Param("field") String field, @Param("example") SeckillBrandGroupProductExtExample example);

}


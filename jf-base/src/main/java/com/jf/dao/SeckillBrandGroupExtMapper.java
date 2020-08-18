package com.jf.dao;

import com.jf.entity.SeckillBrandGroupExt;
import com.jf.entity.SeckillBrandGroupExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeckillBrandGroupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SeckillBrandGroupExt findById(int id);

    SeckillBrandGroupExt find(SeckillBrandGroupExtExample example);

    List<SeckillBrandGroupExt> list(SeckillBrandGroupExtExample example);

    List<Integer> listId(SeckillBrandGroupExtExample example);

    int count(SeckillBrandGroupExtExample example);

    long sum(@Param("field") String field, @Param("example") SeckillBrandGroupExtExample example);

    int max(@Param("field") String field, @Param("example") SeckillBrandGroupExtExample example);

    int min(@Param("field") String field, @Param("example") SeckillBrandGroupExtExample example);

}


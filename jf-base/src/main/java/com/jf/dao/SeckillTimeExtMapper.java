package com.jf.dao;

import com.jf.entity.SeckillTimeExt;
import com.jf.entity.SeckillTimeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeckillTimeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SeckillTimeExt findById(int id);

    SeckillTimeExt find(SeckillTimeExtExample example);

    List<SeckillTimeExt> list(SeckillTimeExtExample example);

    List<Integer> listId(SeckillTimeExtExample example);

    int count(SeckillTimeExtExample example);

    long sum(@Param("field") String field, @Param("example") SeckillTimeExtExample example);

    int max(@Param("field") String field, @Param("example") SeckillTimeExtExample example);

    int min(@Param("field") String field, @Param("example") SeckillTimeExtExample example);

}


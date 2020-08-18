package com.jf.dao;

import com.jf.entity.SpreadNameExt;
import com.jf.entity.SpreadNameExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadNameExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpreadNameExt findById(int id);

    SpreadNameExt find(SpreadNameExtExample example);

    List<SpreadNameExt> list(SpreadNameExtExample example);

    List<Integer> listId(SpreadNameExtExample example);

    int count(SpreadNameExtExample example);

    long sum(@Param("field") String field, @Param("example") SpreadNameExtExample example);

    int max(@Param("field") String field, @Param("example") SpreadNameExtExample example);

    int min(@Param("field") String field, @Param("example") SpreadNameExtExample example);

}


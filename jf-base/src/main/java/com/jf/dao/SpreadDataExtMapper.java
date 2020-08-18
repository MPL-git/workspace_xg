package com.jf.dao;

import com.jf.entity.SpreadDataExt;
import com.jf.entity.SpreadDataExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadDataExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpreadDataExt findById(int id);

    SpreadDataExt find(SpreadDataExtExample example);

    List<SpreadDataExt> list(SpreadDataExtExample example);

    List<Integer> listId(SpreadDataExtExample example);

    int count(SpreadDataExtExample example);

    long sum(@Param("field") String field, @Param("example") SpreadDataExtExample example);

    int max(@Param("field") String field, @Param("example") SpreadDataExtExample example);

    int min(@Param("field") String field, @Param("example") SpreadDataExtExample example);

}


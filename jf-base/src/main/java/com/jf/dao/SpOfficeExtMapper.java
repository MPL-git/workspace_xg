package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SpOfficeExt;
import com.jf.entity.SpOfficeExtExample;

@Repository
public interface SpOfficeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpOfficeExt findById(int id);

    SpOfficeExt find(SpOfficeExtExample example);

    List<SpOfficeExt> list(SpOfficeExtExample example);

    List<Integer> listId(SpOfficeExtExample example);

    int count(SpOfficeExtExample example);

    long sum(@Param("field") String field, @Param("example") SpOfficeExtExample example);

    int max(@Param("field") String field, @Param("example") SpOfficeExtExample example);

    int min(@Param("field") String field, @Param("example") SpOfficeExtExample example);

}


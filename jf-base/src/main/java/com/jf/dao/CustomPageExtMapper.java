package com.jf.dao;

import com.jf.entity.CustomPageExt;
import com.jf.entity.CustomPageExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPageExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomPageExt findById(int id);

    CustomPageExt find(CustomPageExtExample example);

    List<CustomPageExt> list(CustomPageExtExample example);

    List<Integer> listId(CustomPageExtExample example);

    int count(CustomPageExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomPageExtExample example);

    int max(@Param("field") String field, @Param("example") CustomPageExtExample example);

    int min(@Param("field") String field, @Param("example") CustomPageExtExample example);

}


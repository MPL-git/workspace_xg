package com.jf.dao;

import com.jf.entity.CustomAdPageExt;
import com.jf.entity.CustomAdPageExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomAdPageExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomAdPageExt findById(int id);

    CustomAdPageExt find(CustomAdPageExtExample example);

    List<CustomAdPageExt> list(CustomAdPageExtExample example);

    List<Integer> listId(CustomAdPageExtExample example);

    int count(CustomAdPageExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomAdPageExtExample example);

    int max(@Param("field") String field, @Param("example") CustomAdPageExtExample example);

    int min(@Param("field") String field, @Param("example") CustomAdPageExtExample example);

}


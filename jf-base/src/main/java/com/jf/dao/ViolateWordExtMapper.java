package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.ViolateWordExt;
import com.jf.entity.ViolateWordExtExample;

@Repository
public interface ViolateWordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ViolateWordExt findById(int id);

    ViolateWordExt find(ViolateWordExtExample example);

    List<ViolateWordExt> list(ViolateWordExtExample example);

    List<Integer> listId(ViolateWordExtExample example);

    int count(ViolateWordExtExample example);

    long sum(@Param("field") String field, @Param("example") ViolateWordExtExample example);

    int max(@Param("field") String field, @Param("example") ViolateWordExtExample example);

    int min(@Param("field") String field, @Param("example") ViolateWordExtExample example);

}


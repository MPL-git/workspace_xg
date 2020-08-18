package com.jf.dao;

import com.jf.entity.IntellectualPropertyRightExt;
import com.jf.entity.IntellectualPropertyRightExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntellectualPropertyRightExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IntellectualPropertyRightExt findById(int id);

    IntellectualPropertyRightExt find(IntellectualPropertyRightExtExample example);

    List<IntellectualPropertyRightExt> list(IntellectualPropertyRightExtExample example);

    List<Integer> listId(IntellectualPropertyRightExtExample example);

    int count(IntellectualPropertyRightExtExample example);

    long sum(@Param("field") String field, @Param("example") IntellectualPropertyRightExtExample example);

    int max(@Param("field") String field, @Param("example") IntellectualPropertyRightExtExample example);

    int min(@Param("field") String field, @Param("example") IntellectualPropertyRightExtExample example);

}


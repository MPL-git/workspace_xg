package com.jf.dao;

import com.jf.entity.IntellectualPropertyRightPicExt;
import com.jf.entity.IntellectualPropertyRightPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntellectualPropertyRightPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IntellectualPropertyRightPicExt findById(int id);

    IntellectualPropertyRightPicExt find(IntellectualPropertyRightPicExtExample example);

    List<IntellectualPropertyRightPicExt> list(IntellectualPropertyRightPicExtExample example);

    List<Integer> listId(IntellectualPropertyRightPicExtExample example);

    int count(IntellectualPropertyRightPicExtExample example);

    long sum(@Param("field") String field, @Param("example") IntellectualPropertyRightPicExtExample example);

    int max(@Param("field") String field, @Param("example") IntellectualPropertyRightPicExtExample example);

    int min(@Param("field") String field, @Param("example") IntellectualPropertyRightPicExtExample example);

}


package com.jf.dao;

import com.jf.entity.IntellectualPropertyRightAppealExt;
import com.jf.entity.IntellectualPropertyRightAppealExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntellectualPropertyRightAppealExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IntellectualPropertyRightAppealExt findById(int id);

    IntellectualPropertyRightAppealExt find(IntellectualPropertyRightAppealExtExample example);

    List<IntellectualPropertyRightAppealExt> list(IntellectualPropertyRightAppealExtExample example);

    List<Integer> listId(IntellectualPropertyRightAppealExtExample example);

    int count(IntellectualPropertyRightAppealExtExample example);

    long sum(@Param("field") String field, @Param("example") IntellectualPropertyRightAppealExtExample example);

    int max(@Param("field") String field, @Param("example") IntellectualPropertyRightAppealExtExample example);

    int min(@Param("field") String field, @Param("example") IntellectualPropertyRightAppealExtExample example);

}


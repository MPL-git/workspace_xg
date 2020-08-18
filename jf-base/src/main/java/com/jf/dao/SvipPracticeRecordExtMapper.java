package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SvipPracticeRecordExt;
import com.jf.entity.SvipPracticeRecordExtExample;

@Repository
public interface SvipPracticeRecordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SvipPracticeRecordExt findById(int id);

    SvipPracticeRecordExt find(SvipPracticeRecordExtExample example);

    List<SvipPracticeRecordExt> list(SvipPracticeRecordExtExample example);

    List<Integer> listId(SvipPracticeRecordExtExample example);

    int count(SvipPracticeRecordExtExample example);

    long sum(@Param("field") String field, @Param("example") SvipPracticeRecordExtExample example);

    int max(@Param("field") String field, @Param("example") SvipPracticeRecordExtExample example);

    int min(@Param("field") String field, @Param("example") SvipPracticeRecordExtExample example);

}


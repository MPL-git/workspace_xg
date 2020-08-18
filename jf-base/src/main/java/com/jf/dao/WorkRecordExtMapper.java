package com.jf.dao;

import com.jf.entity.WorkRecordExt;
import com.jf.entity.WorkRecordExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRecordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WorkRecordExt findById(int id);

    WorkRecordExt find(WorkRecordExtExample example);

    List<WorkRecordExt> list(WorkRecordExtExample example);

    List<Integer> listId(WorkRecordExtExample example);

    int count(WorkRecordExtExample example);

    long sum(@Param("field") String field, @Param("example") WorkRecordExtExample example);

    int max(@Param("field") String field, @Param("example") WorkRecordExtExample example);

    int min(@Param("field") String field, @Param("example") WorkRecordExtExample example);

}


package com.jf.dao;

import com.jf.entity.MchtSettledApplyRecordExt;
import com.jf.entity.MchtSettledApplyRecordExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettledApplyRecordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSettledApplyRecordExt findById(int id);

    MchtSettledApplyRecordExt find(MchtSettledApplyRecordExtExample example);

    List<MchtSettledApplyRecordExt> list(MchtSettledApplyRecordExtExample example);

    List<Integer> listId(MchtSettledApplyRecordExtExample example);

    int count(MchtSettledApplyRecordExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSettledApplyRecordExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSettledApplyRecordExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSettledApplyRecordExtExample example);

}


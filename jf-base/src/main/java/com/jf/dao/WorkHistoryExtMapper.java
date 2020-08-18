package com.jf.dao;

import com.jf.entity.WorkHistoryExt;
import com.jf.entity.WorkHistoryExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkHistoryExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WorkHistoryExt findById(int id);

    WorkHistoryExt find(WorkHistoryExtExample example);

    List<WorkHistoryExt> list(WorkHistoryExtExample example);

    List<Integer> listId(WorkHistoryExtExample example);

    int count(WorkHistoryExtExample example);

    long sum(@Param("field") String field, @Param("example") WorkHistoryExtExample example);

    int max(@Param("field") String field, @Param("example") WorkHistoryExtExample example);

    int min(@Param("field") String field, @Param("example") WorkHistoryExtExample example);

}


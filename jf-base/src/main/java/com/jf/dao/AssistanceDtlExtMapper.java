package com.jf.dao;

import com.jf.entity.AssistanceDtlExt;
import com.jf.entity.AssistanceDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistanceDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AssistanceDtlExt findById(int id);

    AssistanceDtlExt find(AssistanceDtlExtExample example);

    List<AssistanceDtlExt> list(AssistanceDtlExtExample example);

    List<Integer> listId(AssistanceDtlExtExample example);

    int count(AssistanceDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") AssistanceDtlExtExample example);

    int max(@Param("field") String field, @Param("example") AssistanceDtlExtExample example);

    int min(@Param("field") String field, @Param("example") AssistanceDtlExtExample example);

}


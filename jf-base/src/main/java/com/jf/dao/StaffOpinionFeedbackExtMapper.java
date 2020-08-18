package com.jf.dao;

import com.jf.entity.StaffOpinionFeedbackExt;
import com.jf.entity.StaffOpinionFeedbackExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffOpinionFeedbackExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    StaffOpinionFeedbackExt findById(int id);

    StaffOpinionFeedbackExt find(StaffOpinionFeedbackExtExample example);

    List<StaffOpinionFeedbackExt> list(StaffOpinionFeedbackExtExample example);

    List<Integer> listId(StaffOpinionFeedbackExtExample example);

    int count(StaffOpinionFeedbackExtExample example);

    long sum(@Param("field") String field, @Param("example") StaffOpinionFeedbackExtExample example);

    int max(@Param("field") String field, @Param("example") StaffOpinionFeedbackExtExample example);

    int min(@Param("field") String field, @Param("example") StaffOpinionFeedbackExtExample example);

}


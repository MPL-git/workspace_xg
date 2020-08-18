package com.jf.dao;

import com.jf.entity.StaffOpinionFeedbackPicExt;
import com.jf.entity.StaffOpinionFeedbackPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffOpinionFeedbackPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    StaffOpinionFeedbackPicExt findById(int id);

    StaffOpinionFeedbackPicExt find(StaffOpinionFeedbackPicExtExample example);

    List<StaffOpinionFeedbackPicExt> list(StaffOpinionFeedbackPicExtExample example);

    List<Integer> listId(StaffOpinionFeedbackPicExtExample example);

    int count(StaffOpinionFeedbackPicExtExample example);

    long sum(@Param("field") String field, @Param("example") StaffOpinionFeedbackPicExtExample example);

    int max(@Param("field") String field, @Param("example") StaffOpinionFeedbackPicExtExample example);

    int min(@Param("field") String field, @Param("example") StaffOpinionFeedbackPicExtExample example);

}


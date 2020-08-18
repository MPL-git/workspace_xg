package com.jf.dao;

import com.jf.entity.MemberFeedbackExt;
import com.jf.entity.MemberFeedbackExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFeedbackExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberFeedbackExt findById(int id);

    MemberFeedbackExt find(MemberFeedbackExtExample example);

    List<MemberFeedbackExt> list(MemberFeedbackExtExample example);

    List<Integer> listId(MemberFeedbackExtExample example);

    int count(MemberFeedbackExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberFeedbackExtExample example);

    int max(@Param("field") String field, @Param("example") MemberFeedbackExtExample example);

    int min(@Param("field") String field, @Param("example") MemberFeedbackExtExample example);

}


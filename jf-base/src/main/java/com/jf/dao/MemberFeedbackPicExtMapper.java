package com.jf.dao;

import com.jf.entity.MemberFeedbackPicExt;
import com.jf.entity.MemberFeedbackPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFeedbackPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberFeedbackPicExt findById(int id);

    MemberFeedbackPicExt find(MemberFeedbackPicExtExample example);

    List<MemberFeedbackPicExt> list(MemberFeedbackPicExtExample example);

    List<Integer> listId(MemberFeedbackPicExtExample example);

    int count(MemberFeedbackPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberFeedbackPicExtExample example);

    int max(@Param("field") String field, @Param("example") MemberFeedbackPicExtExample example);

    int min(@Param("field") String field, @Param("example") MemberFeedbackPicExtExample example);

}


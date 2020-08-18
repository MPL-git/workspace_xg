package com.jf.dao;

import com.jf.entity.MchtFeedbackPicExt;
import com.jf.entity.MchtFeedbackPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtFeedbackPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtFeedbackPicExt findById(int id);

    MchtFeedbackPicExt find(MchtFeedbackPicExtExample example);

    List<MchtFeedbackPicExt> list(MchtFeedbackPicExtExample example);

    List<Integer> listId(MchtFeedbackPicExtExample example);

    int count(MchtFeedbackPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtFeedbackPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtFeedbackPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtFeedbackPicExtExample example);

}


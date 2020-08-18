package com.jf.dao;

import com.jf.entity.MchtFeedbackExt;
import com.jf.entity.MchtFeedbackExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtFeedbackExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtFeedbackExt findById(int id);

    MchtFeedbackExt find(MchtFeedbackExtExample example);

    List<MchtFeedbackExt> list(MchtFeedbackExtExample example);

    List<Integer> listId(MchtFeedbackExtExample example);

    int count(MchtFeedbackExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtFeedbackExtExample example);

    int max(@Param("field") String field, @Param("example") MchtFeedbackExtExample example);

    int min(@Param("field") String field, @Param("example") MchtFeedbackExtExample example);

}


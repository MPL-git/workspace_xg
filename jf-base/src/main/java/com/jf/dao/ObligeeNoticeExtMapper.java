package com.jf.dao;

import com.jf.entity.ObligeeNoticeExt;
import com.jf.entity.ObligeeNoticeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObligeeNoticeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ObligeeNoticeExt findById(int id);

    ObligeeNoticeExt find(ObligeeNoticeExtExample example);

    List<ObligeeNoticeExt> list(ObligeeNoticeExtExample example);

    List<Integer> listId(ObligeeNoticeExtExample example);

    int count(ObligeeNoticeExtExample example);

    long sum(@Param("field") String field, @Param("example") ObligeeNoticeExtExample example);

    int max(@Param("field") String field, @Param("example") ObligeeNoticeExtExample example);

    int min(@Param("field") String field, @Param("example") ObligeeNoticeExtExample example);

}


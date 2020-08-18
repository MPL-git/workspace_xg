package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoCommentExt;
import com.jf.entity.VideoCommentExtExample;

@Repository
public interface VideoCommentExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoCommentExt findById(int id);

    VideoCommentExt find(VideoCommentExtExample example);

    List<VideoCommentExt> list(VideoCommentExtExample example);

    List<Integer> listId(VideoCommentExtExample example);

    int count(VideoCommentExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoCommentExtExample example);

    int max(@Param("field") String field, @Param("example") VideoCommentExtExample example);

    int min(@Param("field") String field, @Param("example") VideoCommentExtExample example);

}


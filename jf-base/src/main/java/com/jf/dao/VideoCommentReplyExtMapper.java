package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoCommentReplyExt;
import com.jf.entity.VideoCommentReplyExtExample;

@Repository
public interface VideoCommentReplyExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoCommentReplyExt findById(int id);

    VideoCommentReplyExt find(VideoCommentReplyExtExample example);

    List<VideoCommentReplyExt> list(VideoCommentReplyExtExample example);

    List<Integer> listId(VideoCommentReplyExtExample example);

    int count(VideoCommentReplyExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoCommentReplyExtExample example);

    int max(@Param("field") String field, @Param("example") VideoCommentReplyExtExample example);

    int min(@Param("field") String field, @Param("example") VideoCommentReplyExtExample example);

}


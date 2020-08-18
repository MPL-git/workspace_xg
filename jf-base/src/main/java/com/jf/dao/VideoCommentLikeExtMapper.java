package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoCommentLikeExt;
import com.jf.entity.VideoCommentLikeExtExample;

@Repository
public interface VideoCommentLikeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoCommentLikeExt findById(int id);

    VideoCommentLikeExt find(VideoCommentLikeExtExample example);

    List<VideoCommentLikeExt> list(VideoCommentLikeExtExample example);

    List<Integer> listId(VideoCommentLikeExtExample example);

    int count(VideoCommentLikeExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoCommentLikeExtExample example);

    int max(@Param("field") String field, @Param("example") VideoCommentLikeExtExample example);

    int min(@Param("field") String field, @Param("example") VideoCommentLikeExtExample example);

}


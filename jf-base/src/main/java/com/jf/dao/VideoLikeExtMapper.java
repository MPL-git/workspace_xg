package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoLikeExt;
import com.jf.entity.VideoLikeExtExample;

@Repository
public interface VideoLikeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoLikeExt findById(int id);

    VideoLikeExt find(VideoLikeExtExample example);

    List<VideoLikeExt> list(VideoLikeExtExample example);

    List<Integer> listId(VideoLikeExtExample example);

    int count(VideoLikeExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoLikeExtExample example);

    int max(@Param("field") String field, @Param("example") VideoLikeExtExample example);

    int min(@Param("field") String field, @Param("example") VideoLikeExtExample example);

}


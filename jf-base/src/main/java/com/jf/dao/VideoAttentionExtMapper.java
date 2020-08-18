package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoAttentionExt;
import com.jf.entity.VideoAttentionExtExample;

@Repository
public interface VideoAttentionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoAttentionExt findById(int id);

    VideoAttentionExt find(VideoAttentionExtExample example);

    List<VideoAttentionExt> list(VideoAttentionExtExample example);

    List<Integer> listId(VideoAttentionExtExample example);

    int count(VideoAttentionExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoAttentionExtExample example);

    int max(@Param("field") String field, @Param("example") VideoAttentionExtExample example);

    int min(@Param("field") String field, @Param("example") VideoAttentionExtExample example);

}


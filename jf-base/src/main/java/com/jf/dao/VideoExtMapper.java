package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoExt;
import com.jf.entity.VideoExtExample;

@Repository
public interface VideoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoExt findById(int id);

    VideoExt find(VideoExtExample example);

    List<VideoExt> list(VideoExtExample example);

    List<Integer> listId(VideoExtExample example);

    int count(VideoExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoExtExample example);

    int max(@Param("field") String field, @Param("example") VideoExtExample example);

    int min(@Param("field") String field, @Param("example") VideoExtExample example);

}


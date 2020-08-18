package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoExtendExt;
import com.jf.entity.VideoExtendExtExample;

@Repository
public interface VideoExtendExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoExtendExt findById(int id);

    VideoExtendExt find(VideoExtendExtExample example);

    List<VideoExtendExt> list(VideoExtendExtExample example);

    List<Integer> listId(VideoExtendExtExample example);

    int count(VideoExtendExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoExtendExtExample example);

    int max(@Param("field") String field, @Param("example") VideoExtendExtExample example);

    int min(@Param("field") String field, @Param("example") VideoExtendExtExample example);

}


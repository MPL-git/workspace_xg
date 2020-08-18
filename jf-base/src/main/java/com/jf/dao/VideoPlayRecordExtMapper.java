package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoPlayRecordExt;
import com.jf.entity.VideoPlayRecordExtExample;

@Repository
public interface VideoPlayRecordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoPlayRecordExt findById(int id);

    VideoPlayRecordExt find(VideoPlayRecordExtExample example);

    List<VideoPlayRecordExt> list(VideoPlayRecordExtExample example);

    List<Integer> listId(VideoPlayRecordExtExample example);

    int count(VideoPlayRecordExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoPlayRecordExtExample example);

    int max(@Param("field") String field, @Param("example") VideoPlayRecordExtExample example);

    int min(@Param("field") String field, @Param("example") VideoPlayRecordExtExample example);

}


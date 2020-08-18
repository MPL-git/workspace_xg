package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoTipOffExt;
import com.jf.entity.VideoTipOffExtExample;

@Repository
public interface VideoTipOffExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoTipOffExt findById(int id);

    VideoTipOffExt find(VideoTipOffExtExample example);

    List<VideoTipOffExt> list(VideoTipOffExtExample example);

    List<Integer> listId(VideoTipOffExtExample example);

    int count(VideoTipOffExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoTipOffExtExample example);

    int max(@Param("field") String field, @Param("example") VideoTipOffExtExample example);

    int min(@Param("field") String field, @Param("example") VideoTipOffExtExample example);

}


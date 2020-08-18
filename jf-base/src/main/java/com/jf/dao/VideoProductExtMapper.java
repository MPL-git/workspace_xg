package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoProductExt;
import com.jf.entity.VideoProductExtExample;

@Repository
public interface VideoProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoProductExt findById(int id);

    VideoProductExt find(VideoProductExtExample example);

    List<VideoProductExt> list(VideoProductExtExample example);

    List<Integer> listId(VideoProductExtExample example);

    int count(VideoProductExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoProductExtExample example);

    int max(@Param("field") String field, @Param("example") VideoProductExtExample example);

    int min(@Param("field") String field, @Param("example") VideoProductExtExample example);

}


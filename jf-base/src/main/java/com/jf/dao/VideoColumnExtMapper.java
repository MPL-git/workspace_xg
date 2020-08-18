package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoColumnExt;
import com.jf.entity.VideoColumnExtExample;

@Repository
public interface VideoColumnExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoColumnExt findById(int id);

    VideoColumnExt find(VideoColumnExtExample example);

    List<VideoColumnExt> list(VideoColumnExtExample example);

    List<Integer> listId(VideoColumnExtExample example);

    int count(VideoColumnExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoColumnExtExample example);

    int max(@Param("field") String field, @Param("example") VideoColumnExtExample example);

    int min(@Param("field") String field, @Param("example") VideoColumnExtExample example);

}


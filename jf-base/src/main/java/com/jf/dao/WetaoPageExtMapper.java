package com.jf.dao;

import com.jf.entity.WetaoPageExt;
import com.jf.entity.WetaoPageExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WetaoPageExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WetaoPageExt findById(int id);

    WetaoPageExt find(WetaoPageExtExample example);

    List<WetaoPageExt> list(WetaoPageExtExample example);

    List<Integer> listId(WetaoPageExtExample example);

    int count(WetaoPageExtExample example);

    long sum(@Param("field") String field, @Param("example") WetaoPageExtExample example);

    int max(@Param("field") String field, @Param("example") WetaoPageExtExample example);

    int min(@Param("field") String field, @Param("example") WetaoPageExtExample example);

}


package com.jf.dao;

import com.jf.entity.WetaoPageAdInfoExt;
import com.jf.entity.WetaoPageAdInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WetaoPageAdInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WetaoPageAdInfoExt findById(int id);

    WetaoPageAdInfoExt find(WetaoPageAdInfoExtExample example);

    List<WetaoPageAdInfoExt> list(WetaoPageAdInfoExtExample example);

    List<Integer> listId(WetaoPageAdInfoExtExample example);

    int count(WetaoPageAdInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") WetaoPageAdInfoExtExample example);

    int max(@Param("field") String field, @Param("example") WetaoPageAdInfoExtExample example);

    int min(@Param("field") String field, @Param("example") WetaoPageAdInfoExtExample example);

}


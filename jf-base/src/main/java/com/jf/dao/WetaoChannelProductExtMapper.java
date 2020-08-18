package com.jf.dao;

import com.jf.entity.WetaoChannelProductExt;
import com.jf.entity.WetaoChannelProductExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WetaoChannelProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WetaoChannelProductExt findById(int id);

    WetaoChannelProductExt find(WetaoChannelProductExtExample example);

    List<WetaoChannelProductExt> list(WetaoChannelProductExtExample example);

    List<Integer> listId(WetaoChannelProductExtExample example);

    int count(WetaoChannelProductExtExample example);

    long sum(@Param("field") String field, @Param("example") WetaoChannelProductExtExample example);

    int max(@Param("field") String field, @Param("example") WetaoChannelProductExtExample example);

    int min(@Param("field") String field, @Param("example") WetaoChannelProductExtExample example);

}


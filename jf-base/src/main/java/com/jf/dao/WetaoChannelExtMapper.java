package com.jf.dao;

import com.jf.entity.WetaoChannelExt;
import com.jf.entity.WetaoChannelExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WetaoChannelExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WetaoChannelExt findById(int id);

    WetaoChannelExt find(WetaoChannelExtExample example);

    List<WetaoChannelExt> list(WetaoChannelExtExample example);

    List<Integer> listId(WetaoChannelExtExample example);

    int count(WetaoChannelExtExample example);

    long sum(@Param("field") String field, @Param("example") WetaoChannelExtExample example);

    int max(@Param("field") String field, @Param("example") WetaoChannelExtExample example);

    int min(@Param("field") String field, @Param("example") WetaoChannelExtExample example);

}


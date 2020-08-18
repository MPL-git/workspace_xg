package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WetaoChannel;
import com.jf.entity.WetaoChannelExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WetaoChannelMapper extends BaseDao<WetaoChannel, WetaoChannelExample> {
    int countByExample(WetaoChannelExample example);

    int deleteByExample(WetaoChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WetaoChannel record);

    int insertSelective(WetaoChannel record);

    List<WetaoChannel> selectByExample(WetaoChannelExample example);

    WetaoChannel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WetaoChannel record, @Param("example") WetaoChannelExample example);

    int updateByExample(@Param("record") WetaoChannel record, @Param("example") WetaoChannelExample example);

    int updateByPrimaryKeySelective(WetaoChannel record);

    int updateByPrimaryKey(WetaoChannel record);
}

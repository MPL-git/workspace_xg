package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WetaoChannelProduct;
import com.jf.entity.WetaoChannelProductExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WetaoChannelProductMapper extends BaseDao<WetaoChannelProduct, WetaoChannelProductExample> {
    int countByExample(WetaoChannelProductExample example);

    int deleteByExample(WetaoChannelProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WetaoChannelProduct record);

    int insertSelective(WetaoChannelProduct record);

    List<WetaoChannelProduct> selectByExample(WetaoChannelProductExample example);

    WetaoChannelProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WetaoChannelProduct record, @Param("example") WetaoChannelProductExample example);

    int updateByExample(@Param("record") WetaoChannelProduct record, @Param("example") WetaoChannelProductExample example);

    int updateByPrimaryKeySelective(WetaoChannelProduct record);

    int updateByPrimaryKey(WetaoChannelProduct record);
}

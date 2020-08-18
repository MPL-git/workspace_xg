package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WetaoPage;
import com.jf.entity.WetaoPageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WetaoPageMapper extends BaseDao<WetaoPage, WetaoPageExample> {
    int countByExample(WetaoPageExample example);

    int deleteByExample(WetaoPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WetaoPage record);

    int insertSelective(WetaoPage record);

    List<WetaoPage> selectByExample(WetaoPageExample example);

    WetaoPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WetaoPage record, @Param("example") WetaoPageExample example);

    int updateByExample(@Param("record") WetaoPage record, @Param("example") WetaoPageExample example);

    int updateByPrimaryKeySelective(WetaoPage record);

    int updateByPrimaryKey(WetaoPage record);
}

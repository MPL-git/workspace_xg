package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.VideoTipOff;
import com.jf.entity.VideoTipOffExample;

@Repository
public interface VideoTipOffMapper extends BaseDao<VideoTipOff, VideoTipOffExample> {
    int countByExample(VideoTipOffExample example);

    int deleteByExample(VideoTipOffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoTipOff record);

    int insertSelective(VideoTipOff record);

    List<VideoTipOff> selectByExample(VideoTipOffExample example);

    VideoTipOff selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoTipOff record, @Param("example") VideoTipOffExample example);

    int updateByExample(@Param("record") VideoTipOff record, @Param("example") VideoTipOffExample example);

    int updateByPrimaryKeySelective(VideoTipOff record);

    int updateByPrimaryKey(VideoTipOff record);
}

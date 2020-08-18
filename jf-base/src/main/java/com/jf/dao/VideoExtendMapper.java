package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.VideoExtend;
import com.jf.entity.VideoExtendExample;

@Repository
public interface VideoExtendMapper extends BaseDao<VideoExtend, VideoExtendExample> {
    int countByExample(VideoExtendExample example);

    int deleteByExample(VideoExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoExtend record);

    int insertSelective(VideoExtend record);

    List<VideoExtend> selectByExample(VideoExtendExample example);

    VideoExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoExtend record, @Param("example") VideoExtendExample example);

    int updateByExample(@Param("record") VideoExtend record, @Param("example") VideoExtendExample example);

    int updateByPrimaryKeySelective(VideoExtend record);

    int updateByPrimaryKey(VideoExtend record);
}

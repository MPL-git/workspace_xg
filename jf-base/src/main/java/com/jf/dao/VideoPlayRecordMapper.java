package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.VideoPlayRecord;
import com.jf.entity.VideoPlayRecordExample;

@Repository
public interface VideoPlayRecordMapper extends BaseDao<VideoPlayRecord, VideoPlayRecordExample> {
    int countByExample(VideoPlayRecordExample example);

    int deleteByExample(VideoPlayRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoPlayRecord record);

    int insertSelective(VideoPlayRecord record);

    List<VideoPlayRecord> selectByExample(VideoPlayRecordExample example);

    VideoPlayRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoPlayRecord record, @Param("example") VideoPlayRecordExample example);

    int updateByExample(@Param("record") VideoPlayRecord record, @Param("example") VideoPlayRecordExample example);

    int updateByPrimaryKeySelective(VideoPlayRecord record);

    int updateByPrimaryKey(VideoPlayRecord record);
}

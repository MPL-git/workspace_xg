package com.jf.dao;

import com.jf.entity.VideoPlayRecord;
import com.jf.entity.VideoPlayRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

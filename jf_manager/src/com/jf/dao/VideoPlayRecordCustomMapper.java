package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoPlayRecordCustom;
import com.jf.entity.VideoPlayRecordCustomExample;
@Repository
public interface VideoPlayRecordCustomMapper{

   public List<VideoPlayRecordCustom> selectByVideoPlayRecordCustomExample(VideoPlayRecordCustomExample example);
   
   public Integer countByVideoPlayRecordCustomExample(VideoPlayRecordCustomExample example);
   
   VideoPlayRecordCustom selectByVideoPlayRecordCustomPrimaryKey(Integer id);
   
}
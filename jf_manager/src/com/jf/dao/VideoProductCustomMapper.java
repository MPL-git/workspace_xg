package com.jf.dao;

import com.jf.entity.VideoProductCustom;
import com.jf.entity.VideoProductCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VideoProductCustomMapper {

   public List<VideoProductCustom> selectByVideoProductCustomExample(VideoProductCustomExample example);
   
   public Integer countByVideoProductCustomExample(VideoProductCustomExample example);
   
   VideoProductCustom selectByVideoProductCustomPrimaryKey(Integer id);

   public List<Map<String, Object>> selectCurrentTimeStatistical(Map<String, Object> paramMap);

   public List<Map<String, Object>> selectHistoryStatistical(Map<String, Object> paramMap);

}
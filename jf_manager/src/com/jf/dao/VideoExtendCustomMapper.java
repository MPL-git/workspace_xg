package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoExtendCustom;
import com.jf.entity.VideoExtendCustomExample;
@Repository
public interface VideoExtendCustomMapper{

   public List<VideoExtendCustom> selectByVideoExtendCustomExample(VideoExtendCustomExample example);
   
   public Integer countByVideoExtendCustomExample(VideoExtendCustomExample example);
   
   VideoExtendCustom selectByVideoExtendCustomPrimaryKey(Integer id);
   
}
package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoLikeCustom;
import com.jf.entity.VideoLikeCustomExample;
@Repository
public interface VideoLikeCustomMapper{

   public List<VideoLikeCustom> selectByVideoLikeCustomExample(VideoLikeCustomExample example);
   
   public Integer countByVideoLikeCustomExample(VideoLikeCustomExample example);
   
   VideoLikeCustom selectByVideoLikeCustomPrimaryKey(Integer id);
   
}
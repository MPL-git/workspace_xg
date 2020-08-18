package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoAttentionCustom;
import com.jf.entity.VideoAttentionCustomExample;
@Repository
public interface VideoAttentionCustomMapper{

   public List<VideoAttentionCustom> selectByVideoAttentionCustomExample(VideoAttentionCustomExample example);
   
   public Integer countByVideoAttentionCustomExample(VideoAttentionCustomExample example);
   
   VideoAttentionCustom selectByVideoAttentionCustomPrimaryKey(Integer id);
   
}
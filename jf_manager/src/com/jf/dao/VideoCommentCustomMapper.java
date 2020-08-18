package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoCommentCustom;
import com.jf.entity.VideoCommentCustomExample;
@Repository
public interface VideoCommentCustomMapper{

   public List<VideoCommentCustom> selectByVideoCommentCustomExample(VideoCommentCustomExample example);
   
   public Integer countByVideoCommentCustomExample(VideoCommentCustomExample example);
   
   VideoCommentCustom selectByVideoCommentCustomPrimaryKey(Integer id);
   
}
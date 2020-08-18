package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoCommentLikeCustom;
import com.jf.entity.VideoCommentLikeCustomExample;
@Repository
public interface VideoCommentLikeCustomMapper{

   public List<VideoCommentLikeCustom> selectByVideoCommentLikeCustomExample(VideoCommentLikeCustomExample example);
   
   public Integer countByVideoCommentLikeCustomExample(VideoCommentLikeCustomExample example);
   
   VideoCommentLikeCustom selectByVideoCommentLikeCustomPrimaryKey(Integer id);
   
}
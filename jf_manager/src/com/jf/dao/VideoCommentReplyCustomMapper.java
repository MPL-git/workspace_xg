package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoCommentReplyCustom;
import com.jf.entity.VideoCommentReplyCustomExample;
@Repository
public interface VideoCommentReplyCustomMapper{

   public List<VideoCommentReplyCustom> selectByVideoCommentReplyCustomExample(VideoCommentReplyCustomExample example);
   
   public Integer countByVideoCommentReplyCustomExample(VideoCommentReplyCustomExample example);
   
   VideoCommentReplyCustom selectByVideoCommentReplyCustomPrimaryKey(Integer id);
   
}
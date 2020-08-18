package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoCustom;
import com.jf.entity.VideoCustomExample;
@Repository
public interface VideoCustomMapper{

   public List<VideoCustom> selectByVideoCustomExample(VideoCustomExample example);
   
   public Integer countByVideoCustomExample(VideoCustomExample example);
   
   VideoCustom selectByVideoCustomPrimaryKey(Integer id);
   
}
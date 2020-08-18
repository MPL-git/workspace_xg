package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoTipOffCustom;
import com.jf.entity.VideoTipOffCustomExample;
@Repository
public interface VideoTipOffCustomMapper{

   public List<VideoTipOffCustom> selectByVideoTipOffCustomExample(VideoTipOffCustomExample example);
   
   public Integer countByVideoTipOffCustomExample(VideoTipOffCustomExample example);
   
   VideoTipOffCustom selectByVideoTipOffCustomPrimaryKey(Integer id);
   
}
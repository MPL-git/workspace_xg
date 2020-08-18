package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoColumnCustom;
import com.jf.entity.VideoColumnCustomExample;
@Repository
public interface VideoColumnCustomMapper{

   public List<VideoColumnCustom> selectByVideoColumnCustomExample(VideoColumnCustomExample example);
   
   public Integer countByVideoColumnCustomExample(VideoColumnCustomExample example);
   
   VideoColumnCustom selectByVideoColumnCustomPrimaryKey(Integer id);
   
}
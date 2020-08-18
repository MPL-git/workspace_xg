package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.VideoPvStatisticalCustom;
import com.jf.entity.VideoPvStatisticalCustomExample;
@Repository
public interface VideoPvStatisticalCustomMapper{

   public List<VideoPvStatisticalCustom> selectByVideoPvStatisticalCustomExample(VideoPvStatisticalCustomExample example);
   
   public Integer countByVideoPvStatisticalCustomExample(VideoPvStatisticalCustomExample example);
   
   VideoPvStatisticalCustom selectByVideoPvStatisticalCustomPrimaryKey(Integer id);
   
}
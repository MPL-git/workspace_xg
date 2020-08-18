package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomAdPageCustom;
import com.jf.entity.CustomAdPageCustomExample;
@Repository
public interface CustomAdPageCustomMapper{

   public List<CustomAdPageCustom> selectByCustomExample(CustomAdPageCustomExample example);
   
   public Integer countByCustomExample(CustomAdPageCustomExample example);
   
   CustomAdPageCustom selectByCustomPrimaryKey(Integer id);
   
}
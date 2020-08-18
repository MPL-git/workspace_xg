package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ImpeachHandleLogCustom;
import com.jf.entity.ImpeachHandleLogCustomExample;
@Repository
public interface ImpeachHandleLogCustomMapper{

   public List<ImpeachHandleLogCustom> selectByImpeachHandleLogCustomExample(ImpeachHandleLogCustomExample example);
   
   public Integer countByImpeachHandleLogCustomExample(ImpeachHandleLogCustomExample example);
   
   ImpeachHandleLogCustom selectByImpeachHandleLogCustomPrimaryKey(Integer id);
   
}
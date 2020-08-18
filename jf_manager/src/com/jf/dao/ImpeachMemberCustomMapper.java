package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ImpeachMemberCustom;
import com.jf.entity.ImpeachMemberCustomExample;
@Repository
public interface ImpeachMemberCustomMapper{

   public List<ImpeachMemberCustom> selectByImpeachMemberCustomExample(ImpeachMemberCustomExample example);
   
   public Integer countByImpeachMemberCustomExample(ImpeachMemberCustomExample example);
   
   ImpeachMemberCustom selectByImpeachMemberCustomPrimaryKey(Integer id);
   
   public List<Map<String, Object>> getCommissionerauditbyList();
   
   public List<Map<String, Object>> getFwauditbyList();
   
   public List<Map<String, Object>> getReceiverbyList();
   
}
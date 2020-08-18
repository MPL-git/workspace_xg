package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ImpeachMemberProofCustom;
import com.jf.entity.ImpeachMemberProofCustomExample;
@Repository
public interface ImpeachMemberProofCustomMapper{

   public List<ImpeachMemberProofCustom> selectByImpeachMemberProofCustomExample(ImpeachMemberProofCustomExample example);
   
   public Integer countByImpeachMemberProofCustomExample(ImpeachMemberProofCustomExample example);
   
   ImpeachMemberProofCustom selectByImpeachMemberProofCustomPrimaryKey(Integer id);
   
}
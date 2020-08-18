package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberLabelCustom;
import com.jf.entity.MemberLabelCustomExample;
@Repository
public interface MemberLabelCustomMapper{

   public List<MemberLabelCustom> selectMemberLabelCustomExample(MemberLabelCustomExample example);
   
   public Integer countMemberLabelCustomExample(MemberLabelCustomExample example);
   
   MemberLabelCustom selectMemberLabelCustomPrimaryKey(Integer id);
      
}
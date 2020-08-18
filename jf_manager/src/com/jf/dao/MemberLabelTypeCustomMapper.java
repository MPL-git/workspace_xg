package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberLabelTypeCustom;
import com.jf.entity.MemberLabelTypeCustomExample;
@Repository
public interface MemberLabelTypeCustomMapper{

   public List<MemberLabelTypeCustom> selectMemberLabelTypeCustomExample(MemberLabelTypeCustomExample example);
   
   public Integer countMemberLabelTypeCustomExample(MemberLabelTypeCustomExample example);
   
   MemberLabelTypeCustom selectMemberLabelTypeCustomPrimaryKey(Integer id);
      
}
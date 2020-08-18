package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberLabelRelationCustom;
import com.jf.entity.MemberLabelRelationCustomExample;
@Repository
public interface MemberLabelRelationCustomMapper{

   public List<MemberLabelRelationCustom> selectMemberLabelRelationCustomExample(MemberLabelRelationCustomExample example);
   
   public Integer countMemberLabelRelationCustomExample(MemberLabelRelationCustomExample example);
   
   MemberLabelRelationCustom selectMemberLabelRelationCustomPrimaryKey(Integer id);
   
   public Integer selectMemberLabelRelationCount(Map<String, Object> paramMap);
   
   public List<MemberLabelRelationCustom> getMemberLabelRelationList(Map<String, Object> paramMap);
   
   public Integer getMemberLabelRelationCount(Map<String, Object> paramMap);
   
      
}
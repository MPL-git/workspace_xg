package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberLabelRuleCustom;
import com.jf.entity.MemberLabelRuleCustomExample;
@Repository
public interface MemberLabelRuleCustomMapper{

   public List<MemberLabelRuleCustom> selectMemberLabelRuleCustomExample(MemberLabelRuleCustomExample example);
   
   public Integer countMemberLabelRuleCustomExample(MemberLabelRuleCustomExample example);
   
   MemberLabelRuleCustom selectMemberLabelRuleCustomPrimaryKey(Integer id);
   
    public List<MemberLabelRuleCustom> getMemberLabelList(Map<String, Object> paramMap);
   
   public Integer getMemberLabelCount(Map<String, Object> paramMap);
      
}
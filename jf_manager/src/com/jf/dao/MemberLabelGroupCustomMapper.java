package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberLabelGroupCustom;
import com.jf.entity.MemberLabelGroupCustomExample;
@Repository
public interface MemberLabelGroupCustomMapper {

   public List<MemberLabelGroupCustom> selectMemberLabelGroupCustomExample(MemberLabelGroupCustomExample example);
   
   public Integer countMemberLabelGroupCustomExample(MemberLabelGroupCustomExample example);
   
   MemberLabelGroupCustom selectMemberLabelGroupCustomPrimaryKey(Integer id);
   
   
   public Integer selectMemberCount(Map<String, Object> paramMap);
      
}
package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberLabelGroupRelationCustom;
import com.jf.entity.MemberLabelGroupRelationCustomExample;
@Repository
public interface MemberLabelGroupRelationCustomMapper{

   public List<MemberLabelGroupRelationCustom> selectMemberLabelGroupRelationCustomExample(MemberLabelGroupRelationCustomExample example);
   
   public Integer countMemberLabelGroupRelationCustomExample(MemberLabelGroupRelationCustomExample example);
   
   MemberLabelGroupRelationCustom selectMemberLabelGroupRelationCustomPrimaryKey(Integer id);
      
   public List<Map<String, Object>> getMemberLabelGroupRelationMap(@Param("labelGroupId") Integer labelGroupId);
   
   
}
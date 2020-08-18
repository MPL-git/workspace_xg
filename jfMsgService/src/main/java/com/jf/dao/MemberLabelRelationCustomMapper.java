package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberLabelRule;
@Repository
public interface MemberLabelRelationCustomMapper{
    //批量插入标签会员数据
	int insertionMemberLabelRelation(@Param("memberInfoCustoms")List<MemberInfoCustom> memberInfoCustoms, @Param("memberLabelRule")MemberLabelRule memberLabelRule);
}
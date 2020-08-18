package com.jf.dao;

import com.jf.entity.MemberLabelRule;
import com.jf.entity.MemberLabelRuleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberLabelRuleMapper extends BaseDao<MemberLabelRule, MemberLabelRuleExample>{
    int countByExample(MemberLabelRuleExample example);

    int deleteByExample(MemberLabelRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLabelRule record);

    int insertSelective(MemberLabelRule record);

    List<MemberLabelRule> selectByExample(MemberLabelRuleExample example);

    MemberLabelRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLabelRule record, @Param("example") MemberLabelRuleExample example);

    int updateByExample(@Param("record") MemberLabelRule record, @Param("example") MemberLabelRuleExample example);

    int updateByPrimaryKeySelective(MemberLabelRule record);

    int updateByPrimaryKey(MemberLabelRule record);
}
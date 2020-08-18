package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberLabelRuleCustomMapper;
import com.jf.dao.MemberLabelRuleMapper;
import com.jf.entity.MemberLabelRule;
import com.jf.entity.MemberLabelRuleCustom;
import com.jf.entity.MemberLabelRuleCustomExample;
import com.jf.entity.MemberLabelRuleExample;

@Service
@Transactional
public class MemberLabelRuleService extends BaseService<MemberLabelRule,MemberLabelRuleExample> {
	@Autowired
	private MemberLabelRuleMapper memberLabelRuleMapper;
	@Autowired
	private MemberLabelRuleCustomMapper memberLabelRuleCustomMapper;
	
	@Autowired
	public void setMemberLabelRuleMapper(MemberLabelRuleMapper memberLabelRuleMapper) {
		super.setDao(memberLabelRuleMapper);
		this.memberLabelRuleMapper = memberLabelRuleMapper;
	}
	
	public int countMemberLabelRuleCustomExample(MemberLabelRuleCustomExample example){
		return memberLabelRuleCustomMapper.countMemberLabelRuleCustomExample(example);
	}
    public List<MemberLabelRuleCustom> selectMemberLabelRuleCustomExample(MemberLabelRuleCustomExample example){
    	return memberLabelRuleCustomMapper.selectMemberLabelRuleCustomExample(example);
    }
    public MemberLabelRuleCustom selectMemberLabelRuleCustomPrimaryKey(Integer id){
    	return memberLabelRuleCustomMapper.selectMemberLabelRuleCustomPrimaryKey(id);
    }

    public List<MemberLabelRuleCustom> getMemberLabelList(Map<String, Object> paramMap) {
    	return memberLabelRuleCustomMapper.getMemberLabelList(paramMap);
    }

    public Integer getMemberLabelCount(Map<String, Object> paramMap) {
    	return memberLabelRuleCustomMapper.getMemberLabelCount(paramMap);
    }
    
}

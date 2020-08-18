package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberLabelRelationCustomMapper;
import com.jf.dao.MemberLabelRelationMapper;
import com.jf.entity.MemberLabelRelation;
import com.jf.entity.MemberLabelRelationCustom;
import com.jf.entity.MemberLabelRelationCustomExample;
import com.jf.entity.MemberLabelRelationExample;

@Service
@Transactional
public class MemberLabelRelationService extends BaseService<MemberLabelRelation,MemberLabelRelationExample> {
	@Autowired
	private MemberLabelRelationMapper memberLabelRelationMapper;
	@Autowired
	private MemberLabelRelationCustomMapper memberLabelRelationCustomMapper;
	
	@Autowired
	public void setMemberLabelRelationMapper(MemberLabelRelationMapper memberLabelRelationMapper) {
		super.setDao(memberLabelRelationMapper);
		this.memberLabelRelationMapper = memberLabelRelationMapper;
	}
	
	public int countMemberLabelRelationCustomExample(MemberLabelRelationCustomExample example){
		return memberLabelRelationCustomMapper.countMemberLabelRelationCustomExample(example);
	}
    public List<MemberLabelRelationCustom> selectMemberLabelRelationCustomExample(MemberLabelRelationCustomExample example){
    	return memberLabelRelationCustomMapper.selectMemberLabelRelationCustomExample(example);
    }
    public MemberLabelRelationCustom selectMemberLabelRelationCustomPrimaryKey(Integer id){
    	return memberLabelRelationCustomMapper.selectMemberLabelRelationCustomPrimaryKey(id);
    }
   
    public Integer selectMemberLabelRelationCount(Map<String, Object> paramMap) {
    	return memberLabelRelationCustomMapper.selectMemberLabelRelationCount(paramMap);
    }
    
    public List<MemberLabelRelationCustom> getMemberLabelRelationList(Map<String, Object> paramMap) {
    	return memberLabelRelationCustomMapper.getMemberLabelRelationList(paramMap);
    }

    public Integer getMemberLabelRelationCount(Map<String, Object> paramMap) {
    	return memberLabelRelationCustomMapper.getMemberLabelRelationCount(paramMap);
    }
    
}

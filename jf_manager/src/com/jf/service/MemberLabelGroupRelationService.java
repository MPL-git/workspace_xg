package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberLabelGroupRelationCustomMapper;
import com.jf.dao.MemberLabelGroupRelationMapper;
import com.jf.entity.MemberLabelGroupRelation;
import com.jf.entity.MemberLabelGroupRelationCustom;
import com.jf.entity.MemberLabelGroupRelationCustomExample;
import com.jf.entity.MemberLabelGroupRelationExample;

@Service
@Transactional
public class MemberLabelGroupRelationService extends BaseService<MemberLabelGroupRelation,MemberLabelGroupRelationExample> {
	@Autowired
	private MemberLabelGroupRelationMapper memberLabelGroupRelationMapper;
	@Autowired
	private MemberLabelGroupRelationCustomMapper memberLabelGroupRelationCustomMapper;
	
	@Autowired
	public void setMemberLabelGroupRelationMapper(MemberLabelGroupRelationMapper memberLabelGroupRelationMapper) {
		super.setDao(memberLabelGroupRelationMapper);
		this.memberLabelGroupRelationMapper = memberLabelGroupRelationMapper;
	}
	
	public int countMemberLabelGroupRelationCustomExample(MemberLabelGroupRelationCustomExample example){
		return memberLabelGroupRelationCustomMapper.countMemberLabelGroupRelationCustomExample(example);
	}
    public List<MemberLabelGroupRelationCustom> selectMemberLabelGroupRelationCustomExample(MemberLabelGroupRelationCustomExample example){
    	return memberLabelGroupRelationCustomMapper.selectMemberLabelGroupRelationCustomExample(example);
    }
    public MemberLabelGroupRelationCustom selectMemberLabelGroupRelationCustomPrimaryKey(Integer id){
    	return memberLabelGroupRelationCustomMapper.selectMemberLabelGroupRelationCustomPrimaryKey(id);
    }

    public List<Map<String, Object>> getMemberLabelGroupRelationMap(Integer labelGroupId) {
    	return memberLabelGroupRelationCustomMapper.getMemberLabelGroupRelationMap(labelGroupId);
    }
    
}

package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberLabelTypeCustomMapper;
import com.jf.dao.MemberLabelTypeMapper;
import com.jf.entity.MemberLabelType;
import com.jf.entity.MemberLabelTypeCustom;
import com.jf.entity.MemberLabelTypeCustomExample;
import com.jf.entity.MemberLabelTypeExample;

@Service
@Transactional
public class MemberLabelTypeService extends BaseService<MemberLabelType,MemberLabelTypeExample> {
	@Autowired
	private MemberLabelTypeMapper memberLabelTypeMapper;
	@Autowired
	private MemberLabelTypeCustomMapper memberLabelTypeCustomMapper;
	
	@Autowired
	public void setMemberLabelTypeMapper(MemberLabelTypeMapper memberLabelTypeMapper) {
		super.setDao(memberLabelTypeMapper);
		this.memberLabelTypeMapper = memberLabelTypeMapper;
	}
	
	public int countMemberLabelTypeCustomExample(MemberLabelTypeCustomExample example){
		return memberLabelTypeCustomMapper.countMemberLabelTypeCustomExample(example);
	}
    public List<MemberLabelTypeCustom> selectMemberLabelTypeCustomExample(MemberLabelTypeCustomExample example){
    	return memberLabelTypeCustomMapper.selectMemberLabelTypeCustomExample(example);
    }
    public MemberLabelTypeCustom selectMemberLabelTypeCustomPrimaryKey(Integer id){
    	return memberLabelTypeCustomMapper.selectMemberLabelTypeCustomPrimaryKey(id);
    }

}

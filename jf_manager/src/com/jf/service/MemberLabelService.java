package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberLabelCustomMapper;
import com.jf.dao.MemberLabelMapper;
import com.jf.entity.MemberLabel;
import com.jf.entity.MemberLabelCustom;
import com.jf.entity.MemberLabelCustomExample;
import com.jf.entity.MemberLabelExample;

@Service
@Transactional
public class MemberLabelService extends BaseService<MemberLabel,MemberLabelExample> {
	@Autowired
	private MemberLabelMapper memberLabelMapper;
	@Autowired
	private MemberLabelCustomMapper memberLabelCustomMapper;
	
	@Autowired
	public void setMemberLabelMapper(MemberLabelMapper memberLabelMapper) {
		super.setDao(memberLabelMapper);
		this.memberLabelMapper = memberLabelMapper;
	}
	
	public int countMemberLabelCustomExample(MemberLabelCustomExample example){
		return memberLabelCustomMapper.countMemberLabelCustomExample(example);
	}
    public List<MemberLabelCustom> selectMemberLabelCustomExample(MemberLabelCustomExample example){
    	return memberLabelCustomMapper.selectMemberLabelCustomExample(example);
    }
    public MemberLabelCustom selectMemberLabelCustomPrimaryKey(Integer id){
    	return memberLabelCustomMapper.selectMemberLabelCustomPrimaryKey(id);
    }

}

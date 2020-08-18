package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberLabelGroupCustomMapper;
import com.jf.dao.MemberLabelGroupMapper;
import com.jf.entity.MemberLabelGroup;
import com.jf.entity.MemberLabelGroupCustom;
import com.jf.entity.MemberLabelGroupCustomExample;
import com.jf.entity.MemberLabelGroupExample;

@Service
@Transactional
public class MemberLabelGroupService extends BaseService<MemberLabelGroup,MemberLabelGroupExample> {
	@Autowired
	private MemberLabelGroupMapper memberLabelGroupMapper;
	@Autowired
	private MemberLabelGroupCustomMapper memberLabelGroupCustomMapper;
	
	@Autowired
	public void setMemberLabelGroupMapper(MemberLabelGroupMapper memberLabelGroupMapper) {
		super.setDao(memberLabelGroupMapper);
		this.memberLabelGroupMapper = memberLabelGroupMapper;
	}
	
	public int countMemberLabelGroupCustomExample(MemberLabelGroupCustomExample example){
		return memberLabelGroupCustomMapper.countMemberLabelGroupCustomExample(example);
	}
    public List<MemberLabelGroupCustom> selectMemberLabelGroupCustomExample(MemberLabelGroupCustomExample example){
    	return memberLabelGroupCustomMapper.selectMemberLabelGroupCustomExample(example);
    }
    public MemberLabelGroupCustom selectMemberLabelGroupCustomPrimaryKey(Integer id){
    	return memberLabelGroupCustomMapper.selectMemberLabelGroupCustomPrimaryKey(id);
    }
    
    public Integer selectMemberCount(Map<String, Object> paramMap) {
    	return memberLabelGroupCustomMapper.selectMemberCount(paramMap);
    }

}

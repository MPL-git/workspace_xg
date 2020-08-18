package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.MemberActivityFootprintCustomMapper;
import com.jf.dao.MemberActivityFootprintMapper;
import com.jf.entity.MemberActivityFootprint;
import com.jf.entity.MemberActivityFootprintCustom;
import com.jf.entity.MemberActivityFootprintExample;

@Service
@Transactional
public class MemberActivityFootprintService extends BaseService<MemberActivityFootprint,MemberActivityFootprintExample> {
	@Autowired
	private MemberActivityFootprintMapper memberactivityFootprintMapper;
	@Autowired
	private MemberActivityFootprintCustomMapper memberactivityFootprintCustomMapper;	
	@Autowired
	public void setMemberActivityFootprintMapper(MemberActivityFootprintMapper memberactivityFootprintMapper) {
		super.setDao(memberactivityFootprintMapper);
		this.memberactivityFootprintMapper = memberactivityFootprintMapper;
	}
	
	public int countMemberActivityFootprintCustomByExample(MemberActivityFootprintExample example){
		return memberactivityFootprintCustomMapper.countByExample(example);
	}
    public List<MemberActivityFootprintCustom> selectMemberActivityFootprintCustomByExample(MemberActivityFootprintExample example){
    	return memberactivityFootprintCustomMapper.selectByExample(example);
    }
    public MemberActivityFootprintCustom selectMemberActivityFootprintCustomByPrimaryKey(Integer id){
    	return memberactivityFootprintCustomMapper.selectByPrimaryKey(id);
    }
}

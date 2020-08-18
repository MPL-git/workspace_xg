package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberFootprintCustomMapper;
import com.jf.dao.MemberFootprintMapper;
import com.jf.entity.MemberFootprint;
import com.jf.entity.MemberFootprintCustom;
import com.jf.entity.MemberFootprintExample;

@Service
@Transactional
public class MemberFootprintService extends BaseService<MemberFootprint,MemberFootprintExample> {
	@Autowired
	private MemberFootprintMapper memberFootprintMapper;
	@Autowired
	private MemberFootprintCustomMapper memberFootprintCustomMapper;	
	@Autowired
	public void setMemberFootprintMapper(MemberFootprintMapper memberFootprintMapper) {
		super.setDao(memberFootprintMapper);
		this.memberFootprintMapper = memberFootprintMapper;
	}
	
	public int countMemberFootprintCustomByExample(MemberFootprintExample example){
		return memberFootprintCustomMapper.countByExample(example);
	}
    public List<MemberFootprintCustom> selectMemberFootprintCustomByExample(MemberFootprintExample example){
    	return memberFootprintCustomMapper.selectByExample(example);
    }
    public MemberFootprintCustom selectMemberFootprintCustomByPrimaryKey(Integer id){
    	return memberFootprintCustomMapper.selectByPrimaryKey(id);
    }
}

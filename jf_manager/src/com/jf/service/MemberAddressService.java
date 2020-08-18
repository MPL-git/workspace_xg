package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberAddressCustomMapper;
import com.jf.dao.MemberAddressMapper;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberAddressExample;

@Service
@Transactional
public class MemberAddressService extends BaseService<MemberAddress,MemberAddressExample> {
	@Autowired
	private MemberAddressMapper memberAddressMapper;
	
	@Autowired
	private MemberAddressCustomMapper memberAddressCustomMapper;
	
	@Autowired
	public void setMemberAddressMapper(MemberAddressMapper memberAddressMapper) {
		super.setDao(memberAddressMapper);
		this.memberAddressMapper = memberAddressMapper;
	}
	
	public int countMemberAddressCustomByExample(MemberAddressExample example){
		return memberAddressCustomMapper.countByExample(example);
	}
    public List<MemberAddressCustom> selectMemberAddressCustomByExample(MemberAddressExample example){
    	return memberAddressCustomMapper.selectByExample(example);
    }
    public MemberAddressCustom selectMemberAddressCustomByPrimaryKey(Integer id){
    	return memberAddressCustomMapper.selectByPrimaryKey(id);
    }
}

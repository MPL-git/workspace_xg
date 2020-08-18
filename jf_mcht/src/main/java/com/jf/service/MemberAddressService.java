package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberAddressMapper;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberAddressService extends BaseService<MemberAddress, MemberAddressExample> {
	@Autowired
	private MemberAddressMapper memberAddressMapper;
	
	@Autowired
	public void setMemberAddressMapper(MemberAddressMapper memberAddressMapper) {
		super.setDao(memberAddressMapper);
		this.memberAddressMapper = memberAddressMapper;
	}
	
}

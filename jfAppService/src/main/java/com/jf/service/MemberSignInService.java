package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberSignInMapper;
import com.jf.entity.MemberSignIn;
import com.jf.entity.MemberSignInExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:20:41 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberSignInService extends BaseService<MemberSignIn, MemberSignInExample> {
	@Autowired
	private MemberSignInMapper memberSignInMapper;

	@Autowired
	public void setMemberSignInMapper(MemberSignInMapper memberSignInMapper) {
		this.setDao(memberSignInMapper);
		this.memberSignInMapper = memberSignInMapper;
	}
	
	
}

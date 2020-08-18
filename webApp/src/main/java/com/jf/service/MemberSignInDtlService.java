package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.dao.MemberSignInDtlCustomMapper;
import com.jf.dao.MemberSignInDtlMapper;
import com.jf.entity.MemberSignInDtl;
import com.jf.entity.MemberSignInDtlCustom;
import com.jf.entity.MemberSignInDtlExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:19:05 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberSignInDtlService extends BaseService<MemberSignInDtl, MemberSignInDtlExample> {
	@Autowired
	private MemberSignInDtlMapper memberSignInDtlMapper;
	@Autowired
	private MemberSignInDtlCustomMapper memberSignInDtlCustomMapper;

	@Autowired
	public void setMemberSignInDtlMapper(MemberSignInDtlMapper memberSignInDtlMapper) {
		this.setDao(memberSignInDtlMapper);
		this.memberSignInDtlMapper = memberSignInDtlMapper;
	}

	public int updateReceiveExtraAmount(MemberSignInDtlCustom memberSignInDtlCustom) {
		
		int count = memberSignInDtlCustomMapper.updateReceiveExtraAmount(memberSignInDtlCustom);
		if(count <= 0){
			throw new ArgException("未有可领取的红包");
		}
		return count;
	}
	
	
}

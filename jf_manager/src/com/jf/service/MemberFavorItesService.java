package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberFavorItesCustomMapper;
import com.jf.dao.MemberFavorItesMapper;
import com.jf.entity.MemberFavorItes;
import com.jf.entity.MemberFavorItesCustom;
import com.jf.entity.MemberFavorItesCustomExample;
import com.jf.entity.MemberFavorItesExample;

@Service
@Transactional
public class MemberFavorItesService extends BaseService<MemberFavorItes, MemberFavorItesExample> {
	@Autowired
	private MemberFavorItesMapper memberFavorItesMapper;
	@Autowired
	private MemberFavorItesCustomMapper memberfavorItesCustomMapper;

	@Autowired
	public void setMemberFavorItesMapper(MemberFavorItesMapper memberFavorItesMapper) {
		super.setDao(memberFavorItesMapper);
		this.memberFavorItesMapper = memberFavorItesMapper;
	}
	
	public List<MemberFavorItesCustom> selectByCustomExample(MemberFavorItesCustomExample example){
		return memberfavorItesCustomMapper.selectByExample(example);
	}
	   
   public Integer countByCustomExample(MemberFavorItesCustomExample example){
	   return memberfavorItesCustomMapper.countByCustomExample(example);
   }
   
	
}

package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberDynamicCustomMapper;
import com.jf.dao.MemberDynamicMapper;
import com.jf.entity.MemberDynamic;
import com.jf.entity.MemberDynamicCustom;
import com.jf.entity.MemberDynamicCustomExample;
import com.jf.entity.MemberDynamicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MemberDynamicService extends BaseService<MemberDynamic,MemberDynamicExample> {
	@Autowired
	private MemberDynamicMapper dao;
	
	@Autowired
	private MemberDynamicCustomMapper MemberDynamicCustomMapper;

	@Autowired
	public void setMemberDynamicMapper(MemberDynamicMapper MemberDynamicMapper) {
		super.setDao(MemberDynamicMapper);
		this.dao = MemberDynamicMapper;
	}
	
	public int countShopDynamicCustomByExample(MemberDynamicCustomExample example){
		return MemberDynamicCustomMapper.countByExample(example);
	}
	
	public List<MemberDynamicCustom> selectShopDynamicCustomByExample(MemberDynamicCustomExample example){
		return MemberDynamicCustomMapper.selectByExample(example);
	}

}

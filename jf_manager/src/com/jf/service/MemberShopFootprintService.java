package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.MemberShopFootprintCustomMapper;
import com.jf.dao.MemberShopFootprintMapper;
import com.jf.entity.MemberShopFootprint;
import com.jf.entity.MemberShopFootprintCustom;
import com.jf.entity.MemberShopFootprintExample;

@Service
@Transactional
public class MemberShopFootprintService extends BaseService<MemberShopFootprint,MemberShopFootprintExample> {
	@Autowired
	private MemberShopFootprintMapper membershopFootprintMapper;
	@Autowired
	private MemberShopFootprintCustomMapper membershopFootprintCustomMapper;	
	@Autowired
	public void setMemberShopFootprintMapper(MemberShopFootprintMapper membershopFootprintMapper) {
		super.setDao(membershopFootprintMapper);
		this.membershopFootprintMapper = membershopFootprintMapper;
	}
	
	public int countMemberShopFootprintCustomByExample(MemberShopFootprintExample example){
		return membershopFootprintCustomMapper.countByExample(example);
	}
    public List<MemberShopFootprintCustom> selectMemberShopFootprintCustomByExample(MemberShopFootprintExample example){
    	return membershopFootprintCustomMapper.selectByExample(example);
    }
    public MemberShopFootprintCustom selectMemberShopFootprintCustomByPrimaryKey(Integer id){
    	return membershopFootprintCustomMapper.selectByPrimaryKey(id);
    }
}

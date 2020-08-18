package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSupplierUserCustomMapper;
import com.jf.dao.MchtSupplierUserMapper;
import com.jf.entity.MchtSupplierUser;
import com.jf.entity.MchtSupplierUserCustom;
import com.jf.entity.MchtSupplierUserExample;

@Service
@Transactional
public class MchtSupplierUserService extends BaseService<MchtSupplierUser,MchtSupplierUserExample> {
	@Autowired
	private MchtSupplierUserMapper mchtSupplierUserMapper;
	@Autowired
	private MchtSupplierUserCustomMapper mchtSupplierUserCustomMapper;
	@Autowired
	public void setMchtSupplierUserMapper(MchtSupplierUserMapper mchtSupplierUserMapper) {
		super.setDao(mchtSupplierUserMapper);
		this.mchtSupplierUserMapper = mchtSupplierUserMapper;
	}
	
	public int countMchtSupplierUserCustomByExample(MchtSupplierUserExample example){
		return mchtSupplierUserCustomMapper.countByExample(example);
	}
    public List<MchtSupplierUserCustom> selectMchtSupplierUserCustomByExample(MchtSupplierUserExample example){
    	return mchtSupplierUserCustomMapper.selectByExample(example);
    }
}

package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSupplierUserMapper;
import com.jf.entity.MchtSupplierUser;
import com.jf.entity.MchtSupplierUserExample;

@Service
@Transactional
public class MchtSupplierUserService extends BaseService<MchtSupplierUser, MchtSupplierUserExample> {
	@Autowired
	private MchtSupplierUserMapper mchtSupplierUserMapper;
	@Autowired
	public void setMchtSupplierUserMapper(MchtSupplierUserMapper mchtSupplierUserMapper) {
		this.setDao(mchtSupplierUserMapper);
		this.mchtSupplierUserMapper = mchtSupplierUserMapper;
	}
	public List<MchtSupplierUser> findModel(Integer spOfficeId, Integer mchtId) {
		MchtSupplierUserExample mchtSupplierUserExample = new MchtSupplierUserExample();
		mchtSupplierUserExample.createCriteria().andSpOfficeIdEqualTo(spOfficeId).andMchtIdEqualTo(mchtId).andDelFlagEqualTo("0").andStatusEqualTo("1");
		return selectByExample(mchtSupplierUserExample);
	}
	
	
}

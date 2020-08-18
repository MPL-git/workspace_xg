package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtSupplierUserMapper;
import com.jf.entity.MchtSupplierUser;
import com.jf.entity.MchtSupplierUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

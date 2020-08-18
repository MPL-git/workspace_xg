package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtLicenseChgMapper;
import com.jf.entity.MchtLicenseChg;
import com.jf.entity.MchtLicenseChgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtLicenseChgService extends BaseService<MchtLicenseChg,MchtLicenseChgExample> {
	@Autowired
	private MchtLicenseChgMapper mchtLicenseChgMapper;
	
	@Autowired
	public void setMchtLicenseChgMapper(MchtLicenseChgMapper mchtLicenseChgMapper) {
		super.setDao(mchtLicenseChgMapper);
		this.mchtLicenseChgMapper = mchtLicenseChgMapper;
	}
}

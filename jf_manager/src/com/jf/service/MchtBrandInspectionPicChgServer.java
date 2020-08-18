package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandInspectionPicChgMapper;
import com.jf.entity.MchtBrandInspectionPicChg;
import com.jf.entity.MchtBrandInspectionPicChgExample;

@Service
@Transactional
public class MchtBrandInspectionPicChgServer extends BaseService<MchtBrandInspectionPicChg, MchtBrandInspectionPicChgExample>{
	@Autowired
	private MchtBrandInspectionPicChgMapper mchtBrandInspectionPicChgMapper;
	
	@Autowired
	public void setMchtBrandInspectionPicChgMapper(MchtBrandInspectionPicChgMapper mchtBrandInspectionPicChgMapper) {
		super.setDao(mchtBrandInspectionPicChgMapper);
		this.mchtBrandInspectionPicChgMapper = mchtBrandInspectionPicChgMapper;
	}
	
}

package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandInspectionPicMapper;
import com.jf.entity.MchtBrandInspectionPic;
import com.jf.entity.MchtBrandInspectionPicExample;

@Service
@Transactional
public class MchtBrandInspectionPicServer extends BaseService<MchtBrandInspectionPic, MchtBrandInspectionPicExample>{
	@Autowired
	private MchtBrandInspectionPicMapper mchtBrandInspectionPicMapper;
	
	@Autowired
	public void setMchtBrandInspectionPicMapper(MchtBrandInspectionPicMapper mchtBrandInspectionPicMapper) {
		super.setDao(mchtBrandInspectionPicMapper);
		this.mchtBrandInspectionPicMapper = mchtBrandInspectionPicMapper;
	}
	
}

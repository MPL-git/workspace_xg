package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandInvoicePicMapper;
import com.jf.entity.MchtBrandInvoicePic;
import com.jf.entity.MchtBrandInvoicePicExample;

@Service
@Transactional
public class MchtBrandInvoicePicServer extends BaseService<MchtBrandInvoicePic, MchtBrandInvoicePicExample>{
	@Autowired
	private MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper;
	
	@Autowired
	public void setMchtBrandInvoicePicMapper(MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper) {
		super.setDao(mchtBrandInvoicePicMapper);
		this.mchtBrandInvoicePicMapper = mchtBrandInvoicePicMapper;
	}
	
}

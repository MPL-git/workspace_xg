package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandInvoicePicChgMapper;
import com.jf.entity.MchtBrandInvoicePicChg;
import com.jf.entity.MchtBrandInvoicePicChgExample;

@Service
@Transactional
public class MchtBrandInvoicePicChgServer extends BaseService<MchtBrandInvoicePicChg, MchtBrandInvoicePicChgExample>{
	@Autowired
	private MchtBrandInvoicePicChgMapper mchtBrandInvoicePicChgMapper;
	
	@Autowired
	public void setMchtBrandInvoicePicChgMapper(MchtBrandInvoicePicChgMapper mchtBrandInvoicePicChgMapper) {
		super.setDao(mchtBrandInvoicePicChgMapper);
		this.mchtBrandInvoicePicChgMapper = mchtBrandInvoicePicChgMapper;
	}
	
}

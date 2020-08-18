package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBrandInvoicePicChgMapper;
import com.jf.entity.MchtBrandInvoicePicChg;
import com.jf.entity.MchtBrandInvoicePicChgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtBrandInvoicePicChgServer extends BaseService<MchtBrandInvoicePicChg, MchtBrandInvoicePicChgExample> {
	@Autowired
	private MchtBrandInvoicePicChgMapper mchtBrandInvoicePicChgMapper;
	
	@Autowired
	public void setMchtBrandInvoicePicChgMapper(MchtBrandInvoicePicChgMapper mchtBrandInvoicePicChgMapper) {
		super.setDao(mchtBrandInvoicePicChgMapper);
		this.mchtBrandInvoicePicChgMapper = mchtBrandInvoicePicChgMapper;
	}
	
}

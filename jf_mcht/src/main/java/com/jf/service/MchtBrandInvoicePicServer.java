package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBrandInvoicePicMapper;
import com.jf.entity.MchtBrandInvoicePic;
import com.jf.entity.MchtBrandInvoicePicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtBrandInvoicePicServer extends BaseService<MchtBrandInvoicePic, MchtBrandInvoicePicExample> {
	@Autowired
	private MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper;
	
	@Autowired
	public void setMchtBrandInvoicePicMapper(MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper) {
		super.setDao(mchtBrandInvoicePicMapper);
		this.mchtBrandInvoicePicMapper = mchtBrandInvoicePicMapper;
	}
	
}

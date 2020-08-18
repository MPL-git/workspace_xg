package com.jf.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtTaxInvoiceInfoChgCustomMapper;
import com.jf.dao.MchtTaxInvoiceInfoChgMapper;
import com.jf.entity.MchtTaxInvoiceInfoChg;
import com.jf.entity.MchtTaxInvoiceInfoChgCustom;
import com.jf.entity.MchtTaxInvoiceInfoChgExample;

@Service
@Transactional
public class MchtTaxInvoiceInfoChgService extends BaseService<MchtTaxInvoiceInfoChg,MchtTaxInvoiceInfoChgExample> {
	@Autowired
	private MchtTaxInvoiceInfoChgMapper mchtTaxInvoiceInfoChgMapper;
	
	@Resource
	private MchtTaxInvoiceInfoChgCustomMapper mchtTaxInvoiceInfoChgCustomMapper;
	
	@Autowired
	public void setMchtTaxInvoiceInfoChgMapper(MchtTaxInvoiceInfoChgMapper mchtTaxInvoiceInfoChgMapper) {
		super.setDao(mchtTaxInvoiceInfoChgMapper);
		this.mchtTaxInvoiceInfoChgMapper = mchtTaxInvoiceInfoChgMapper;
	}
	
	public List<MchtTaxInvoiceInfoChgCustom> mchtTaxInvoiceInfoChgCustomList(HashMap<String, Object> paramMap)
	{
		List<MchtTaxInvoiceInfoChgCustom> list = mchtTaxInvoiceInfoChgCustomMapper.mchtTaxInvoiceInfoChgCustomList(paramMap);
		return list;
	}
	
	public int mchtTaxInvoiceInfoChgCustomListCount(HashMap<String, Object> paramMap)
	{
		return mchtTaxInvoiceInfoChgCustomMapper.mchtTaxInvoiceInfoChgCustomListCount(paramMap);
	}
}

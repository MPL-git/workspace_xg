package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtTaxInvoiceInfoChgCustom;
@Repository
public interface MchtTaxInvoiceInfoChgCustomMapper{
	public List<MchtTaxInvoiceInfoChgCustom> mchtTaxInvoiceInfoChgCustomList(Map<String,Object> map);
	public int mchtTaxInvoiceInfoChgCustomListCount(Map<String,Object> map);
}
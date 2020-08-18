package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtTaxInvoiceInfoCustom;
@Repository
public interface MchtTaxInvoiceInfoCustomMapper{
	public List<MchtTaxInvoiceInfoCustom> mchtTaxInvoiceInfoCustomList(Map<String,Object> map);
	public int mchtTaxInvoiceInfoCustomListCount(Map<String,Object> map);
}
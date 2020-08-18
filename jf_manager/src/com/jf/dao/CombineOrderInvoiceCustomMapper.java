package com.jf.dao;

import com.jf.entity.CombineOrderInvoiceCustom;
import com.jf.entity.CombineOrderInvoiceExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombineOrderInvoiceCustomMapper {
	
	public int countByCustomExample(CombineOrderInvoiceExample example);

	public List<CombineOrderInvoiceCustom> selectByCustomExample(CombineOrderInvoiceExample example);
}
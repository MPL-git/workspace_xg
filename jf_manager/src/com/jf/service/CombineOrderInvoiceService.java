package com.jf.service;

import com.jf.dao.CombineOrderInvoiceCustomMapper;
import com.jf.dao.CombineOrderInvoiceMapper;
import com.jf.entity.CombineOrderInvoice;
import com.jf.entity.CombineOrderInvoiceCustom;
import com.jf.entity.CombineOrderInvoiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombineOrderInvoiceService extends BaseService<CombineOrderInvoice, CombineOrderInvoiceExample>{
	@Autowired
	private CombineOrderInvoiceMapper combineOrderInvoiceMapper;

	@Autowired
	private CombineOrderInvoiceCustomMapper combineOrderInvoiceCustomMapper;
	
	@Autowired
	public void setcombineOrderInvoiceMapper(CombineOrderInvoiceMapper combineOrderInvoiceMapper) {
		super.setDao(combineOrderInvoiceMapper);
		this.combineOrderInvoiceMapper = combineOrderInvoiceMapper;
	}

	public Integer countByCustomExample(CombineOrderInvoiceExample example){
		return combineOrderInvoiceCustomMapper.countByCustomExample(example);
	}

	public List<CombineOrderInvoiceCustom> selectByCustomExample(CombineOrderInvoiceExample example){
		return combineOrderInvoiceCustomMapper.selectByCustomExample(example);
	}

}

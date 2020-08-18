package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CutPriceCnfDtlMapper;
import com.jf.dao.CutPriceCnfMapper;
import com.jf.dao.SingleProductActivityCustomMapper;
import com.jf.dao.SingleProductActivityMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.Product;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import com.jf.entity.SingleProductActivityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SingleProductActivityService extends BaseService<SingleProductActivity,SingleProductActivityExample> {
	@Autowired
	private SingleProductActivityMapper dao;
	
	@Autowired
	private SingleProductActivityCustomMapper singleProductActivityCustomMapper;
	
	@Autowired
	private ProductItemService productItemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CutPriceCnfMapper cutPriceCnfMapper;
	
	@Autowired
	private CutPriceCnfDtlMapper cutPriceCnfDtlMapper;
	
	@Autowired
	public void setSingleProductActivityMapper(SingleProductActivityMapper singleProductActivityMapper) {
		super.setDao(singleProductActivityMapper);
		this.dao = singleProductActivityMapper;
	}
	
	public List<SingleProductActivityCustom> selectSingleProductActivityCustomByExample(SingleProductActivityCustomExample example){
		return singleProductActivityCustomMapper.selectByExample(example);
	}
	
	public int countSingleProductActivityCustomByExample(SingleProductActivityCustomExample example) {
		return singleProductActivityCustomMapper.countByExample(example);
	}

	public void update(SingleProductActivity singleProductActivity,Product product) {
		this.insertSelective(singleProductActivity);
		productService.updateByPrimaryKeySelective(product);
	}

	public void update(SingleProductActivity singleProductActivity,Product product, CutPriceCnf cpc, CutPriceCnfDtl cpcd) {
		this.insertSelective(singleProductActivity);
		productService.updateByPrimaryKeySelective(product);
		cpc.setSingleProductActivityId(singleProductActivity.getId());
		cutPriceCnfMapper.insertSelective(cpc);
		cpcd.setCutPriceCnfId(cpc.getId());
		cutPriceCnfDtlMapper.insertSelective(cpcd);
	}

	public int getEnrollCount1(Map<String,Object> paramMap) {
		return singleProductActivityCustomMapper.getEnrollCount1(paramMap);
	}

	public int getEnrollCount2(Map<String, Object> paramMap) {
		return singleProductActivityCustomMapper.getEnrollCount2(paramMap);
	}
}

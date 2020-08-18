package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductAfterTempletCustomMapper;
import com.jf.dao.ProductAfterTempletMapper;
import com.jf.entity.ProductAfterTemplet;
import com.jf.entity.ProductAfterTempletCustom;
import com.jf.entity.ProductAfterTempletCustomExample;
import com.jf.entity.ProductAfterTempletExample;

@Service
@Transactional
public class ProductAfterTempletService extends BaseService<ProductAfterTemplet,ProductAfterTempletExample> {
	@Autowired
	private ProductAfterTempletMapper productAfterTempletMapper;
	@Autowired
	private ProductAfterTempletCustomMapper productAfterTempletCustomMapper;
	@Autowired
	public void setProductAfterTempletMapper(ProductAfterTempletMapper productAfterTempletMapper) {
		super.setDao(productAfterTempletMapper);
		this.productAfterTempletMapper = productAfterTempletMapper;
	}
	
	public int countProductAfterTempletCustomByExample(ProductAfterTempletCustomExample example){
		return productAfterTempletCustomMapper.countByExample(example);
	}
    public List<ProductAfterTempletCustom> selectProductAfterTempletCustomByExample(ProductAfterTempletCustomExample example){
    	return productAfterTempletCustomMapper.selectByExample(example);
    }
    public ProductAfterTempletCustom selectProductAfterTempletCustomByPrimaryKey(Integer id){
    	return productAfterTempletCustomMapper.selectByPrimaryKey(id);
    }
}

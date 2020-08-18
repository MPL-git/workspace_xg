package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.ProductAfterTempletCustomMapper;
import com.jf.dao.ProductAfterTempletMapper;
import com.jf.entity.ProductAfterTemplet;
import com.jf.entity.ProductAfterTempletCustom;
import com.jf.entity.ProductAfterTempletCustomExample;
import com.jf.entity.ProductAfterTempletExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    
    public void productAfterTempletSave(ProductAfterTemplet productAfterTemplet) throws ArgException{
    	if(productAfterTemplet.getId()==null){
    		productAfterTempletMapper.insertSelective(productAfterTemplet);
    	}else{
    		productAfterTempletMapper.updateByPrimaryKeySelective(productAfterTemplet);
    	}
    }
    
    public List<ProductAfterTempletCustom> selectProductAfterTempletCustom4DefaultBrand(Integer brandId,Integer mchtId){
    	return productAfterTempletCustomMapper.selectProductAfterTempletCustom4DefaultBrand(brandId,mchtId);
    }
}

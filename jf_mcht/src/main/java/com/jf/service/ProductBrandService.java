package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductBrandCustomMapper;
import com.jf.dao.ProductBrandMapper;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandCustomExample;
import com.jf.entity.ProductBrandExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ProductBrandService extends BaseService<ProductBrand, ProductBrandExample> {
	@Autowired
	private ProductBrandMapper productBrandMapper;
	
	@Autowired
	private ProductBrandCustomMapper productBrandCustomMapper;
	
	@Autowired
	public void setProductBrandMapper(ProductBrandMapper ProductBrandMapper) {
		super.setDao(ProductBrandMapper);
		this.productBrandMapper = ProductBrandMapper;
	}
	
	
    public int countProductBrandCustomByExample(ProductBrandCustomExample example){
    	return productBrandCustomMapper.countByExample(example);
    }

    public List<ProductBrandCustom> selectProductBrandCustomByExample(ProductBrandExample example){
    	return productBrandCustomMapper.selectByExample(example);
    }

    public ProductBrandCustom selectProductBrandCustomByPrimaryKey(Integer id){
    	return productBrandCustomMapper.selectByPrimaryKey(id);
    }


	public List<ProductBrand> getMchtProductBrands(HashMap<String, Object> paramMap) {
		return productBrandCustomMapper.getMchtProductBrands(paramMap);
	}

	public ProductBrand findById(int id) {
		return dao.selectByPrimaryKey(id);
	}
}


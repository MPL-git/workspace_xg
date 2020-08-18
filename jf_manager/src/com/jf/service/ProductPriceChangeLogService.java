package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductPriceChangeLogCustomMapper;
import com.jf.dao.ProductPriceChangeLogMapper;
import com.jf.entity.ProductPriceChangeLog;
import com.jf.entity.ProductPriceChangeLogCustom;
import com.jf.entity.ProductPriceChangeLogCustomExample;
import com.jf.entity.ProductPriceChangeLogExample;

@Service
@Transactional
public class ProductPriceChangeLogService extends BaseService<ProductPriceChangeLog, ProductPriceChangeLogExample> {

	@Autowired
	private ProductPriceChangeLogMapper productPriceChangeLogMapper;
	
	@Autowired
	private ProductPriceChangeLogCustomMapper productPriceChangeLogCustomMapper;
	
	@Autowired
	public void setProductPriceChangeLogMapper(ProductPriceChangeLogMapper productPriceChangeLogMapper) {
		super.setDao(productPriceChangeLogMapper);
		this.productPriceChangeLogMapper = productPriceChangeLogMapper;
	}
	
	public int countByCustomExample(ProductPriceChangeLogCustomExample example) {
		return productPriceChangeLogCustomMapper.countByCustomExample(example);
	}

    public List<ProductPriceChangeLogCustom> selectByCustomExample(ProductPriceChangeLogCustomExample example) {
    	return productPriceChangeLogCustomMapper.selectByCustomExample(example);
    }

    public ProductPriceChangeLogCustom selectByCustomPrimaryKey(Integer id) {
    	return productPriceChangeLogCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(@Param("record") ProductPriceChangeLog record, @Param("example") ProductPriceChangeLogCustomExample example) {
    	return productPriceChangeLogCustomMapper.updateByCustomExampleSelective(record, example);
    }
	
	
}

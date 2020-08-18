package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductFeeRateMapper;
import com.jf.entity.ProductFeeRate;
import com.jf.entity.ProductFeeRateExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductFeeRateService extends BaseService<ProductFeeRate, ProductFeeRateExample> {
	@Autowired
	private ProductFeeRateMapper productFeeRateMapper;
	
	@Autowired
	public void setProductFeeRateMapper(ProductFeeRateMapper productFeeRateMapper) {
		this.setDao(productFeeRateMapper);
		this.productFeeRateMapper = productFeeRateMapper;
	}

	public ProductFeeRate findModelByProductId(Integer productId) {
		ProductFeeRate productFeeRate = null;
		ProductFeeRateExample productFeeRateExample = new ProductFeeRateExample();
		productFeeRateExample.createCriteria().andProductIdEqualTo(productId).andDelFlagEqualTo("0");
		List<ProductFeeRate> productFeeRates = selectByExample(productFeeRateExample);
		if(CollectionUtils.isNotEmpty(productFeeRates)){
			productFeeRate = productFeeRates.get(0);
		}
		return productFeeRate;
	}
	
}

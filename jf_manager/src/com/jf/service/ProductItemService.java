package com.jf.service;

import com.jf.dao.ProductItemCustomMapper;
import com.jf.dao.ProductItemMapper;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductItemService extends BaseService<ProductItem, ProductItemExample> {
	@Autowired
	private ProductItemMapper productItemMapper;
	
	@Autowired
	private ProductItemCustomMapper productItemCustomMapper;

	@Autowired
	public void setProductItemMapper(ProductItemMapper productItemMapper) {
		super.setDao(productItemMapper);
		this.productItemMapper = productItemMapper;
	}
	
    public List<ProductItemCustom> selectProductItemCustomByExample(ProductItemExample example){
    	return productItemCustomMapper.selectByExample(example);
    }
    public ProductItemCustom selectByPrimaryKey(Integer id){
    	return productItemCustomMapper.selectByPrimaryKey(id);
    }
    public void updateByProductId(BigDecimal seckillPrice,Integer productId){
    	productItemCustomMapper.updateByProductId(seckillPrice,productId);
    }

	public ProductItem getMinMallPriceItem(Integer productId) {
		ProductItem minMallPriceItem = getMinPriceItem(productId, 10);
		if (minMallPriceItem == null) {
			minMallPriceItem = getMinPriceItem(productId, 11);
		}
		return minMallPriceItem;
	}

	public ProductItem getMinSalePriceItem(Integer productId) {
		ProductItem minSalePriceItem = getMinPriceItem(productId, 20);
		if (minSalePriceItem == null) {
			minSalePriceItem = getMinPriceItem(productId, 21);
		}
		return minSalePriceItem;
	}

	public ProductItem getMaxSalePriceItem(Integer productId) {
		ProductItem maxSalePriceItem = getMaxPriceItem(productId, 20);
		if (maxSalePriceItem == null) {
			maxSalePriceItem = getMaxPriceItem(productId, 21);
		}
		return maxSalePriceItem;
	}

	/**
	 * 查询商品最低价格的规格,searchType:
	 * 10 持有库存且商城价最低的规格
	 * 11 商城价最低的规格
	 * 20 持有库存且活动价最低的规格
	 * 21 活动价最低的规格
	 */
	public ProductItem getMinPriceItem(Integer productId, Integer searchType) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("searchType", searchType);
		return productItemCustomMapper.getMinPriceItem(params);
	}

	public ProductItem getMaxPriceItem(Integer productId, Integer searchType) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("searchType", searchType);
		return productItemCustomMapper.getMaxPriceItem(params);
	}
}

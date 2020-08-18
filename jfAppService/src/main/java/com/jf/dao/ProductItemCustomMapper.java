package com.jf.dao;

import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ProductItemCustomMapper{

	ProductItemCustom getProductInfoByItemId(Integer productItemId);

	ProductItemCustom getProductInfoByActivityId(Map<String, Object> productItemParamsMap);

	Integer updateSkuLockedAmount(Map<String, Integer> params);

	Integer deductSkuLockedAmount(Map<String, Object> params);

	ProductItem findIdByForUpdate(Integer productItemId);

	ProductItem getSpecPic(Map<String, Object> specPicMap);

	String getProductItemPropDesc(Map<String, Object> paramsMap);

	List<ProductItem> getSkuByProductId(Integer productId);
	/**
	 *获取总库存信息 
	 */
	ProductItem getProductStockInfo(Map<String, Integer> params);

	List<ProductItemCustom> getCloudSellingPrice(Integer cloudProductItemId);

	List<ProductItemCustom> getCodeBreakingPreferenceSize(Map<String, Object> paramsMap);

	List<ProductItem> findSkuByProductId(Integer productId);

}
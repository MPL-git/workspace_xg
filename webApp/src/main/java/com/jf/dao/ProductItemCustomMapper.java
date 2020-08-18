package com.jf.dao;

import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import org.apache.ibatis.annotations.Param;
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

	ProductItem getProductStockInfo(Map<String, Integer> params);
	/**
	 * 获取供应商经销代码
	 */
	List<ProductItemCustom> getCloudSellingPrice(Integer cloudProductItemId);

    List<ProductItem> findSkuByProductId(Integer productId);

	int decreaseStock(@Param("productItemId") Integer productItemId, @Param("decrement")Integer decrement);

	int getProductStock(@Param("productId") Integer productId);

}
package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.entity.ProductCustomExample;
import org.springframework.stereotype.Repository;



import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductProp;
@Repository
public interface ProductCustomMapper{
    int countByExample(ProductExample example);
    List<ProductCustom> selectByExample(ProductExample example);
    ProductCustom selectByPrimaryKey(Integer id);
    List<ProductProp> getProductPropByProductId(Integer productId);
    List<?> getProductPropNameByProductId(Integer productId);
	List<ProductCustom> getProductsByActivityId(Integer activityId);
	List<ProductCustom> getProductsByParamMap(Map<String, Object> paramMap);
	String getProductActivityStatus(Integer id);
	String getShopProductActivityStatus(Integer id) ;
	String getSingleProductActivityStatus(Integer id);
	void outFreightTemplate(List<Integer> productIdList);
	void selectFreightTemplate(HashMap<String,Object> map);
	int countCurrentTemplate(Integer freightTemplateId);
	int countNoTemplate(Integer mchtId);
	int countOtherTemplateCount(HashMap<String, Object> map);

	List<HashMap<String, Object>> getProductSaleData(HashMap<String, Object> paramMap);
	int countProductsByParamMap(Map<String, Object> paramMap);

	/**
	 * 获取店铺装修展示数据
	 *
	 * @return
	 */
	List<ProductCustom> selectShopDecorateList(Integer mchtId);
	
	void updateSvipDiscount(Integer id);
	
	Product getLastProduct4UsebleProductType(Integer mchtId);
	int countProductSaleData(HashMap<String, Object> paramMap);

	List<ProductCustom> selectProductAndPropValue(ProductCustomExample productCustomExample);
}
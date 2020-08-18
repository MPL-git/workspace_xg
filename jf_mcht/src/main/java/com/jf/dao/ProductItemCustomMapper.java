package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemCustomExample;
import com.jf.entity.ProductItemExample;
@Repository
public interface ProductItemCustomMapper  {
    List<ProductItemCustom> selectByExample(ProductItemExample example);
    ProductItemCustom selectByPrimaryKey(Integer id);

    int queryStockByExample(ProductItemExample example);
    
    /**
     *   <!--此方法慎用慎用慎用慎用！！！！：因为此方法未过滤删除的sku-->
     * @param paramMap
     * @return
     */
    ProductItem queryProductItemByProductIdAndPropValueIds(Map<String, Object> paramMap);
    List<ProductItem> selectProductItemOrderByPropValue(Map<String, Object> paramMap);
	List<ProductItemCustom> getExportProductItemCustom(int activityId);
	List<HashMap<String, Object>> getPropDescList(Map<String, Object> paramMap);
	void setCloudProductItemIdNull(Integer id);

    ProductItem getMinPriceItem(Map<String, Object> params);
    
    List<ProductItemCustom> selectBySkuExample(ProductItemCustomExample example);
}
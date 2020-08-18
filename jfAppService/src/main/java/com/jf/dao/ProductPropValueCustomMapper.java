package com.jf.dao;

import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ProductPropValueCustomMapper {

	List<ProductPropValueCustom> getProductPropdesc(Map<String, Object> productParams);

	List<ProductPropValue> getProductPropValueModelByIdsNew(Map<String, Object> map);

	List<ProductPropValue> getProductPropValueModelByIdsOld(Map<String, Object> map);
	
	/**
	 * 
	 * @Title getProductPropValueAliasList   
	 * @Description TODO(获取商家尺码)   
	 * @author pengl
	 * @date 2018年9月17日 下午3:41:06
	 */
	List<String> getProductPropValueAliasList(Map<String, Object> paramsMap);

}
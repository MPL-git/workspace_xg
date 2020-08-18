package com.jf.dao;

import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ProductPropValueCustomMapper {

	List<ProductPropValueCustom> getProductPropdesc(Map<String, Object> productParams);

	List<ProductPropValue> getProductPropValueModelByIds(Map<String, Object> map);

}
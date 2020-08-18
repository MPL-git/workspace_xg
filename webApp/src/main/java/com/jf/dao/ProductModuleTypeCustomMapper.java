package com.jf.dao;

import com.jf.entity.ProductModuleTypeCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductModuleTypeCustomMapper {

	List<ProductModuleTypeCustom> getProductModuleTypeCustomList(Map<String, Object> paramsMap);

}
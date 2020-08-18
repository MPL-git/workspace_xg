package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductCustom;

@Repository
public interface SourceNicheProductCustomMapper {

	List<ProductCustom> getEveryDayShopProduct(Map<String, Object> map);

	List<ProductCustom> getCollegeStudentProductList(Map<String, Object> paramMap);
	
}

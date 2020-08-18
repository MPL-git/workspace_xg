package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductCustom;
import com.jf.entity.ProductType;

@Repository
public interface SvipProductRecommendCustomMapper {

	List<ProductType> getProductTypeList();

	List<ProductCustom> getSvipProductRecommendList(Map<String, Object> paramMap);

}

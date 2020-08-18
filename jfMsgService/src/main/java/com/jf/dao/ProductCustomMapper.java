package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.Product;
@Repository
public interface ProductCustomMapper{

	String getProductactivityStatus(Integer id);

	List<Product> getActivityEndProduct(Integer activityAreaId);

	List<Product> getSingleActivityEndProduct(Integer singleActivityId);

	List<Product> getActivityAreaEndProduct(Integer activityAreaId);

	void updateProductActivityStatus(List<Integer> productIds);
	
	List<Map<String, Object>> getNoWatermarkProductPic(Map<String, Object> params);
	List<Map<String, Object>> getNoWatermarkSingleActivityProductPic(Map<String, Object> params);
	int batchUpdateSingleActivityWatermark(Map<String, Object> params);
	int offProductWhileNoStock();

	Integer updateSkuLockedAmount(Map<String, Integer> params);

}
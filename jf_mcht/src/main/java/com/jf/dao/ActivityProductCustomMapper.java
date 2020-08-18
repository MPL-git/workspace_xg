package com.jf.dao;

import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductCustomExample;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ActivityProductCustomMapper {

	List<ActivityProduct> selectActivityProduct(ActivityProductCustomExample example);

	List<Integer> getProductIdsByActivityId(Integer activityId);

	void saveSort(HashMap<String, Object> map);

	int getEffectiveProductCount(Map<String, Object> paramMap);

	List<Integer> getProductIds(Map<String, Object> paramMap);

	List<ActivityProduct> checkSvipDiscount(Integer activityId);
}
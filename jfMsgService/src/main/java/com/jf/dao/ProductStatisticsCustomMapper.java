package com.jf.dao;

import com.jf.entity.ProductStatistics;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductStatisticsCustomMapper {

	Integer countNewProduct();

	List<Map<String, Object>> selectIdProductStatistics(Map<String, Object> paramMap);

	Integer countProductStatistics();

	List<Map<String, Object>> selectProductIdProductStatistics(Map<String, Object> paramMap);

	Integer insertProductStatisticsList(List<ProductStatistics> productStatisticsList);

	Integer updateProductStatisticsList(List<ProductStatistics> productStatisticsList);


}
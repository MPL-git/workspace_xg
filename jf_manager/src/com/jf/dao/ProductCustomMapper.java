package com.jf.dao;

import com.jf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductCustomMapper {
    int countByExample(ProductExample example);
	//爆款推荐列表
    List<ProductCustom> selectRecommendedProductCustomByExample(ProductExample example);
    List<ProductCustom> selectByExample(ProductExample example);
    ProductCustom selectByPrimaryKey(Integer id);
    List<ProductProp> getProductPropByProductId(Integer productId);
    List<?> getProductPropNameByProductId(Integer productId);
	String getActivityIdsByProductId(Integer productId);
	List<HashMap<String, Object>> getLastActivityIdByProductId(Integer productId);
	List<HashMap<String, Object>> getLastSingleProductActivityIdByProductId(Integer productId);
	List<Integer> getProductIds(HashMap<String, Object> paramMap);
	List<Integer> getProductIdsByCode(String codeStr);
	String getProductTypeAllChild(Integer productTypeId);
	List<HashMap<String, Object>> getProductSortList(ProductCustomExample pce);
	List<Integer> selectProdutCopyLog(@Param("targetMchtId") Integer targetMchtId, @Param("productIds") List<Integer> productIds);

	int countMchtProductForm(Map<String, Object> map);
	List<Map<String, Object>> selectMchtProductForm(Map<String, Object> map);

	//批量插入审核流水表日志
	void insertProductList(@Param("productList") List<Product> productList);

	//查询商品的主要状态
	List<HashMap<String, Object>> selectProductStatusByCode(@Param("productCode") String productCode);

	//查询商品流量报表数据
	List<ProductCustom> getCommodityFlowReportData(Map<String, Object> map);

	int countCommodityStatisticsList(Map<String, Object> map);

	List<Map<String, Object>> selectCommodityStatisticsList(Map<String, Object> map);

	//查找商品销量在0-50的商家ids
	List<Map<String, Object>> selectCommodityStatisticsIds(Map<String, Object> map);


	List<Map<String, Object>> selectCommoditySaleStatisticsList(Map<String, Object> map);

	int countCommoditySaleStatisticsList(Map<String, Object> map);


}
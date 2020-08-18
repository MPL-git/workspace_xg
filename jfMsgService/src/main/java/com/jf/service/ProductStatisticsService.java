package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.dao.ProductStatisticsCustomMapper;
import com.jf.dao.ProductStatisticsMapper;
import com.jf.entity.ProductStatistics;
import com.jf.entity.ProductStatisticsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-02-24 下午 5:22
 */
@Service
@Transactional
public class ProductStatisticsService extends BaseService<ProductStatistics, ProductStatisticsExample> {

	@Autowired
	private ProductStatisticsMapper productStatisticsMapper;

	@Autowired
	private ProductStatisticsCustomMapper productStatisticsCustomMapper;

	@Autowired
	public void setProductStatisticsMapper(ProductStatisticsMapper productStatisticsMapper) {
		this.setDao(productStatisticsMapper);
		this.productStatisticsMapper = productStatisticsMapper;
	}

	public Integer countNewProduct() {
		return productStatisticsCustomMapper.countNewProduct();
	}

	public List<Map<String, Object>> selectIdProductStatistics(Map<String, Object> paramMap) {
		return productStatisticsCustomMapper.selectIdProductStatistics(paramMap);
	}

	public Integer countProductStatistics() {
		return productStatisticsCustomMapper.countProductStatistics();
	}

	public List<Map<String, Object>> selectProductIdProductStatistics(Map<String, Object> paramMap) {
		return productStatisticsCustomMapper.selectProductIdProductStatistics(paramMap);
	}

	public Integer insertProductStatisticsList(List<Map<String, Object>> mapList) {
		List<ProductStatistics> productStatisticsList = new ArrayList<ProductStatistics>();
		for (Map<String, Object> map : mapList) {
			ProductStatistics productStatistics = new ProductStatistics();
			productStatistics.setProductId(Integer.parseInt(map.get("product_id").toString()));
			String[] sold_7_days = map.get("sold_7_days").toString().split(",");
			productStatistics.setSoldCount7Days(Integer.parseInt(sold_7_days[0]));
			productStatistics.setSoldAmount7Days(new BigDecimal(sold_7_days[1]));
			String[] refund_7_days = map.get("refund_7_days").toString().split(",");
			productStatistics.setRefundCount7Days(Integer.parseInt(refund_7_days[0]));
			productStatistics.setRefundAmount7Days(new BigDecimal(refund_7_days[1]));
			productStatistics.setSubOrderCount7Days(Integer.parseInt(refund_7_days[2]));
			String[] sold_30_days = map.get("sold_30_days").toString().split(",");
			productStatistics.setSoldCount30Days(Integer.parseInt(sold_30_days[0]));
			productStatistics.setSoldAmount30Days(new BigDecimal(sold_30_days[1]));
			String[] refund_30_days = map.get("refund_30_days").toString().split(",");
			productStatistics.setRefundCount30Days(Integer.parseInt(refund_30_days[0]));
			productStatistics.setRefundAmount30Days(new BigDecimal(refund_30_days[1]));
			productStatistics.setSubOrderCount30Days(Integer.parseInt(refund_30_days[2]));
			String[] sold_90_days = map.get("sold_90_days").toString().split(",");
			productStatistics.setSoldCount90Days(Integer.parseInt(sold_90_days[0]));
			productStatistics.setSoldAmount90Days(new BigDecimal(sold_90_days[1]));
			String[] refund_90_days = map.get("refund_90_days").toString().split(",");
			productStatistics.setRefundCount90Days(Integer.parseInt(refund_90_days[0]));
			productStatistics.setRefundAmount90Days(new BigDecimal(refund_90_days[1]));
			productStatistics.setSubOrderCount90Days(Integer.parseInt(refund_90_days[2]));
			productStatistics.setApplauseRate(map.get("applause_rate").toString().equals("")?null:new BigDecimal(map.get("applause_rate").toString()));
			productStatistics.setCreateBy(1);
			productStatistics.setCreateDate(DateUtil.getDate());
			productStatistics.setUpdateDate(DateUtil.getDate());
			productStatisticsList.add(productStatistics);
		}
		return productStatisticsCustomMapper.insertProductStatisticsList(productStatisticsList);
	}

	public Integer updateProductStatisticsList(List<Map<String, Object>> mapList) {
		List<ProductStatistics> productStatisticsList = new ArrayList<ProductStatistics>();
		for (Map<String, Object> map : mapList) {
			ProductStatistics productStatistics = new ProductStatistics();
			productStatistics.setProductId(Integer.parseInt(map.get("product_id").toString()));
			String[] sold_7_days = map.get("sold_7_days").toString().split(",");
			productStatistics.setSoldCount7Days(Integer.parseInt(sold_7_days[0]));
			productStatistics.setSoldAmount7Days(new BigDecimal(sold_7_days[1]));
			String[] refund_7_days = map.get("refund_7_days").toString().split(",");
			productStatistics.setRefundCount7Days(Integer.parseInt(refund_7_days[0]));
			productStatistics.setRefundAmount7Days(new BigDecimal(refund_7_days[1]));
			productStatistics.setSubOrderCount7Days(Integer.parseInt(refund_7_days[2]));
			String[] sold_30_days = map.get("sold_30_days").toString().split(",");
			productStatistics.setSoldCount30Days(Integer.parseInt(sold_30_days[0]));
			productStatistics.setSoldAmount30Days(new BigDecimal(sold_30_days[1]));
			String[] refund_30_days = map.get("refund_30_days").toString().split(",");
			productStatistics.setRefundCount30Days(Integer.parseInt(refund_30_days[0]));
			productStatistics.setRefundAmount30Days(new BigDecimal(refund_30_days[1]));
			productStatistics.setSubOrderCount30Days(Integer.parseInt(refund_30_days[2]));
			String[] sold_90_days = map.get("sold_90_days").toString().split(",");
			productStatistics.setSoldCount90Days(Integer.parseInt(sold_90_days[0]));
			productStatistics.setSoldAmount90Days(new BigDecimal(sold_90_days[1]));
			String[] refund_90_days = map.get("refund_90_days").toString().split(",");
			productStatistics.setRefundCount90Days(Integer.parseInt(refund_90_days[0]));
			productStatistics.setRefundAmount90Days(new BigDecimal(refund_90_days[1]));
			productStatistics.setSubOrderCount90Days(Integer.parseInt(refund_90_days[2]));
			productStatistics.setApplauseRate(map.get("applause_rate").toString().equals("")?null:new BigDecimal(map.get("applause_rate").toString()));
			productStatistics.setUpdateDate(DateUtil.getDate());
			productStatisticsList.add(productStatistics);
		}
		return productStatisticsCustomMapper.updateProductStatisticsList(productStatisticsList);
	}


}

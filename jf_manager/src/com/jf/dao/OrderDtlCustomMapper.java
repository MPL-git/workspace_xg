package com.jf.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.OperateData;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlExample;
@Repository
public interface OrderDtlCustomMapper{
    int countByExample(OrderDtlExample example);
    List<OrderDtlCustom> selectByExample(OrderDtlExample example);
    List<OrderDtlCustom> selectByExampleSource(OrderDtlExample example);
    OrderDtlCustom selectByPrimaryKey(Integer id);
	List<OrderDtlCustom> getOrderDtlsBySubOrderId(Integer subOrderId);
	List<OrderDtlCustom> platformPreferentialEachDayCountList(HashMap<String, Object> paramMap);
	BigDecimal countCompletePayAmount(HashMap<String, Object> paramMap);
	BigDecimal countCompletePreferentialAmount(HashMap<String, Object> paramMap);
    int countByExample2(OrderDtlExample example);
    List<OrderDtlCustom> selectByExample2(OrderDtlExample example);
	public List<?> getSumOrderDtlByExample(OrderDtlExample example);
	List<OrderDtlCustom> eachDayNeedPayCount(HashMap<String, String> paramMap);
	List<OrderDtlCustom> dayReport(HashMap<String, Object> paramMap);
	List<OrderDtlCustom> weekReport(HashMap<String, Object> paramMap);
	List<OrderDtlCustom> productTypeReport(HashMap<String, Object> paramMap);
	List<OrderDtlCustom> orderCount(HashMap<String, Object> paramMap);
	List<OrderDtlCustom> totalOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> activityTypeSaleCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> allOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> activityOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> singleProductActivityOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> productTypeOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> getOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> getBrandCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> getEachHourCount(HashMap<String, Object> paramMap);
	
	/**
	 * 
	 * @Title getSubOrderDtlList   
	 * @Description TODO(母订单详情)   
	 * @author pengl
	 * @date 2018年7月13日 下午5:06:23
	 */
	public List<Map<String, Object>> getSubOrderDtlList(Map<String, Object> map);
	
	/**
	 * 
	 * @Title selectOrderDtlPayAmounList   
	 * @Description TODO(一级类目销售报表)   
	 * @author pengl
	 * @date 2018年10月19日 下午12:47:19
	 */
	public List<Map<String, Object>> selectOrderDtlPayAmounList(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectOrderDtlPayAmounSum(Map<String, Object> paramMap);
	
	
	/**
	 * 获取月报表数据
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<OperateData> getMonthDatas(Map<String, Object> paramMap);
	
	
}
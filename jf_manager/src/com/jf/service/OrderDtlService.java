package com.jf.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.OrderDtlCustomMapper;
import com.jf.dao.OrderDtlMapper;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlCustomExample;
import com.jf.entity.OrderDtlExample;

@Service
@Transactional
public class OrderDtlService extends BaseService<OrderDtl,OrderDtlExample> {
	@Autowired
	private OrderDtlMapper orderDtlMapper;
	@Autowired
	private OrderDtlCustomMapper orderDtlCustomMapper;
	
	@Autowired
	public void setOrderDtlMapper(OrderDtlMapper orderDtlMapper) {
		super.setDao(orderDtlMapper);
		this.orderDtlMapper = orderDtlMapper;
	}
	
	public int countOrderDtlCustomByExample(OrderDtlExample example){
		return orderDtlCustomMapper.countByExample(example);
	}
    public List<OrderDtlCustom> selectOrderDtlCustomByExample(OrderDtlExample example){
    	return orderDtlCustomMapper.selectByExample(example);
    }
    public List<OrderDtlCustom> selectOrderDtlCustomByExampleSource(OrderDtlExample example){
    	return orderDtlCustomMapper.selectByExampleSource(example);
    }
    public OrderDtlCustom selectOrderDtlCustomByPrimaryKey(Integer id){
    	return orderDtlCustomMapper.selectByPrimaryKey(id);
    }

	public List<OrderDtlCustom> getOrderDtlsBySubOrderId(Integer subOrderId) {
		return orderDtlCustomMapper.getOrderDtlsBySubOrderId(subOrderId);
	}

	public List<OrderDtlCustom> platformPreferentialEachDayCountList(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.platformPreferentialEachDayCountList(paramMap);
	}

	public BigDecimal countCompletePayAmount(HashMap<String, Object> pMap) {
		return orderDtlCustomMapper.countCompletePayAmount(pMap);
	}

	public BigDecimal countCompletePreferentialAmount(HashMap<String, Object> pMap) {
		return orderDtlCustomMapper.countCompletePreferentialAmount(pMap);
	}
	
	public int countOrderDtlCustomByExample2(OrderDtlExample example){
		return orderDtlCustomMapper.countByExample2(example);
	}
    public List<OrderDtlCustom> selectOrderDtlCustomByExample2(OrderDtlExample example){
    	return orderDtlCustomMapper.selectByExample2(example);
    }
	
	public List<?> getSumOrderDtlByExample(OrderDtlCustomExample example){
		List<?> list=orderDtlCustomMapper.getSumOrderDtlByExample(example);
		return list;
	}
	
	public List<OrderDtlCustom> eachDayNeedPayCount(HashMap<String,String> map){
		return orderDtlCustomMapper.eachDayNeedPayCount(map);
	}

	public List<OrderDtlCustom> dayReport(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.dayReport(paramMap);
	}
	
	public List<OrderDtlCustom> weekReport(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.weekReport(paramMap);
	}

	public List<OrderDtlCustom> productTypeReport(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.productTypeReport(paramMap);
	}

	public List<OrderDtlCustom> orderCount(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.orderCount(paramMap);
	}

	public List<OrderDtlCustom> totalOrderCount(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.totalOrderCount(paramMap);
	}

	public List<HashMap<String, Object>> activityTypeSaleCount(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.activityTypeSaleCount(paramMap);
	}

	public List<HashMap<String, Object>> getOrderCount(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.getOrderCount(paramMap);
	}

	public List<HashMap<String, Object>> getBrandCount(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.getBrandCount(paramMap);
	}

	public List<HashMap<String, Object>> getEachHourCount(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.getEachHourCount(paramMap);
	}

	/**
	 * 
	 * @Title getSubOrderDtlList   
	 * @Description TODO(母订单详情)   
	 * @author pengl
	 * @date 2018年7月13日 下午5:07:18
	 */
	public List<Map<String, Object>> getSubOrderDtlList(Map<String, Object> map) {
		return orderDtlCustomMapper.getSubOrderDtlList(map);
	}
	
	/**
	 * 
	 * @Title selectOrderDtlPayAmounList   
	 * @Description TODO(一级类目销售报表)   
	 * @author pengl
	 * @date 2018年10月19日 下午12:48:17
	 */
	public List<Map<String, Object>> selectOrderDtlPayAmounList(Map<String, Object> paramMap) {
		return orderDtlCustomMapper.selectOrderDtlPayAmounList(paramMap);
	}
	public List<Map<String, Object>> selectOrderDtlPayAmounSum(Map<String, Object> paramMap) {
		return orderDtlCustomMapper.selectOrderDtlPayAmounSum(paramMap);
	}
	
	
	
	
	
	
	
}

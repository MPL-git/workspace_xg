package com.jf.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.exception.ArgException;
import com.jf.common.utils.StringUtils;
import com.jf.dao.OrderDtlCustomMapper;
import com.jf.entity.OperateData;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;

/**
 * 
 * @Description: 运营数据业务层
 * @Author: zhen.li
 * @Date: 2019/01/02 10:08
 * 
 */
@Service
@Transactional
public class OperateDataService {

	@Autowired
	private OrderDtlCustomMapper orderDtlCustomMapper;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private CommonService commonService;

	/**
	 * 获取月报表数据
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> getMonthDatas(String begin, String end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultData = new ArrayList<Map<String,Object>>();
		paramMap.put("listSpecMchtCode", commonService.listSpecMchtCode());
		this.setParameter(begin, end, paramMap, resultData);
		List<OperateData> datas = orderDtlCustomMapper.getMonthDatas(paramMap);
		
		for (Map<String, Object> resultMap: resultData) {
			String date = resultMap.get("date").toString();
			String type = resultMap.get("type").toString();
			
			BigDecimal allPrograma = BigDecimal.ZERO;
			BigDecimal allPay = BigDecimal.ZERO;
			BigDecimal allSettle = BigDecimal.ZERO;
			// 判断当前类型是属于哪种 填充相对应的数据 （1.总收款金额 2.实收金额（除退款） 3.应付结算（除退款） 4.毛利率）
			if (type.equals("1")) {
				for (OperateData operateData: datas) {
					if (!date.equals(operateData.getPayDate())){
						continue;
					}
					resultMap.put("column_" + operateData.getProductType1Id(), operateData.getAllPayAmount());
					allPrograma = allPrograma.add(operateData.getAllPayAmount());
				}
				resultMap.put("totalPrograma", allPrograma);
			} else if (type.equals("2")) {
				for (OperateData operateData: datas) {
					if (!date.equals(operateData.getPayDate())){
						continue;
					}
					resultMap.put("column_" + operateData.getProductType1Id(), operateData.getPayAmount());
					allPay = allPay.add(operateData.getPayAmount());
				}
				resultMap.put("totalPrograma", allPay);
			} else if (type.equals("3")) {
				for (OperateData operateData: datas) {
					if (!date.equals(operateData.getPayDate())){
						continue;
					}
					resultMap.put("column_" + operateData.getProductType1Id(), operateData.getSettleAmount());
					allSettle = allSettle.add(operateData.getSettleAmount());
				}
				resultMap.put("totalPrograma", allSettle);
			} else {
				BigDecimal allPayForRate = BigDecimal.ZERO;
				BigDecimal allSettleForRate = BigDecimal.ZERO;
				for (OperateData operateData: datas) {
					if (!date.equals(operateData.getPayDate())){
						continue;
					}
					BigDecimal payAmount = operateData.getPayAmount();
					BigDecimal settleAmount = operateData.getSettleAmount();
					int value = payAmount.compareTo(BigDecimal.ZERO);
					BigDecimal rate = value <= 0 ? BigDecimal.ZERO : BigDecimal.ONE.subtract(settleAmount.divide(payAmount, 2, BigDecimal.ROUND_HALF_UP));
					resultMap.put("column_" + operateData.getProductType1Id(), rate);
					allPayForRate = allPayForRate.add(operateData.getPayAmount());
					allSettleForRate = allSettleForRate.add(operateData.getSettleAmount());
				}
				int compareValue = allPayForRate.compareTo(BigDecimal.ZERO);
				BigDecimal allRate = compareValue <= 0 ? BigDecimal.ZERO : BigDecimal.ONE.subtract(allSettleForRate.divide(allPayForRate, 2, BigDecimal.ROUND_HALF_UP));
				resultMap.put("totalPrograma", allRate);
			}
		}
		
		return resultData;
	}

	private void setParameter(String begin, String end,
			Map<String, Object> paramMap, List<Map<String, Object>> resultData) {
		
		if (StringUtils.isEmpty(begin) || StringUtils.isEmpty(end)) {
			throw new ArgException("请填写时间");
		}
		
		String[] beginArr = begin.split("-");
		String b1 = beginArr[0];
		String b2 = beginArr[1];
		int beginYear = Integer.parseInt(b1);
		int beginMonth = Integer.parseInt(b2);

		String[] endArr = end.split("-");
		String e1 = endArr[0];
		String e2 = endArr[1];
		int endYear = Integer.parseInt(e1);
		int endMonth = Integer.parseInt(e2);

		if (beginYear > endYear
				|| (beginYear == endYear && beginMonth > endMonth)) {
			throw new ArgException("开始时间大于结束时间");
		}

		int month = Math.abs(endYear - beginYear) * 12 + endMonth - beginMonth;
		if (month > 2) {
			throw new ArgException("搜索范围不能超过三个月");
		}

		Calendar beginCal = Calendar.getInstance();
		// 设置年份
		beginCal.set(Calendar.YEAR, beginYear);
		// 设置月份
		beginCal.set(Calendar.MONTH, beginMonth - 1);
		// 获取某月最小天数
		int firstDay = beginCal.getMinimum(Calendar.DATE);
		// 设置日历中月份的最小天数
		beginCal.set(Calendar.DAY_OF_MONTH, firstDay);

		Calendar endCal = Calendar.getInstance();
		// 设置年份
		endCal.set(Calendar.YEAR, endYear);
		// 设置月份
		endCal.set(Calendar.MONTH, endMonth - 1);
		// 获取某月最大天数
		int lastDay = endCal.getActualMaximum(Calendar.DATE);
		// 设置日历中月份的最大天数
		endCal.set(Calendar.DAY_OF_MONTH, lastDay);

		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		paramMap.put("beginTime", sdf.format(beginCal.getTime()) + " 00:00:00");
		paramMap.put("endTime", sdf.format(endCal.getTime()) + " 23:59:59");
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        Date endDate = endCal.getTime();
        
        // 查询所有以及类目 组装参数
        ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria typeCriteria = productTypeExample.createCriteria();
		typeCriteria.andDelFlagEqualTo("0");
		typeCriteria.andTLevelEqualTo(1);
        List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
        
        for (int i = 0; i <= month; i++) {
            Map<String, Object> totalMap = new HashMap<String, Object>();
            String dateStr = monthFormat.format(endDate);
            totalMap.put("date", dateStr);
            totalMap.put("type", "1");
            totalMap.put("programa", "总收款金额");
            for (int j = 0; j < productTypes.size(); j++){
                ProductType productType = productTypes.get(j);
                totalMap.put("column_" + productType.getId(), "");
            }
            resultData.add(totalMap);

            Map<String, Object> payMap = new HashMap<String, Object>();
            payMap.put("date", dateStr);
            payMap.put("type", "2");
            payMap.put("programa", "实收金额（除退款）");
            for (int j = 0; j < productTypes.size(); j++){
                ProductType productType = productTypes.get(j);
                payMap.put("column_" + productType.getId(), "");
            }
            resultData.add(payMap);

            Map<String, Object> settleMap = new HashMap<String, Object>();
            settleMap.put("date", dateStr);
            settleMap.put("type", "3");
            settleMap.put("programa", "应付结算（除退款）");
            for (int j = 0; j < productTypes.size(); j++){
                ProductType productType = productTypes.get(j);
                settleMap.put("column_" + productType.getId(), "");
            }
            resultData.add(settleMap);

            Map<String, Object> rateMap = new HashMap<String, Object>();
            rateMap.put("date", dateStr);
            rateMap.put("type", "4");
            rateMap.put("programa", "毛利率");
            for (int j = 0; j < productTypes.size(); j++){
                ProductType productType = productTypes.get(j);
                rateMap.put("column_" + productType.getId(), "");
            }
            resultData.add(rateMap);
            if (i != month) {
                endDate = DateUtils.addMonths(endDate, -1);
            }
        }
	}

}

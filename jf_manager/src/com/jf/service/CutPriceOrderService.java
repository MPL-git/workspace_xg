package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DateUtil;
import com.jf.dao.CutPriceOrderCustomMapper;
import com.jf.dao.CutPriceOrderMapper;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderCustom;
import com.jf.entity.CutPriceOrderCustomExample;
import com.jf.entity.CutPriceOrderExample;

@Service
@Transactional
public class CutPriceOrderService extends BaseService<CutPriceOrder, CutPriceOrderExample> {

	@Autowired
	private CutPriceOrderMapper cutPriceOrderMapperl;
	
	@Autowired
	private CutPriceOrderCustomMapper cutPriceOrderCustomMapperl;
	
	@Autowired
	public void setCutPriceOrderMapper(CutPriceOrderMapper cutPriceOrderMapperl) {
		super.setDao(cutPriceOrderMapperl);
		this.cutPriceOrderMapperl = cutPriceOrderMapperl;
	}
	
	public int countByCustomExample(CutPriceOrderCustomExample example) {
		return cutPriceOrderCustomMapperl.countByCustomExample(example);
	}

	public List<CutPriceOrderCustom> selectByCustomExample(CutPriceOrderCustomExample example) {
		return cutPriceOrderCustomMapperl.selectByCustomExample(example);
	}

	public CutPriceOrderCustom selectByCustomPrimaryKey(Integer id) {
		return cutPriceOrderCustomMapperl.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") CutPriceOrder record, @Param("example") CutPriceOrderCustomExample example) {
		return cutPriceOrderCustomMapperl.updateByCustomExampleSelective(record, example);
	}
	
	/**
     * 
     * @Title cutPriceOrderStatisticsList   
     * @Description TODO(免费砍价拿数据统计 或  邀请免费拿数据统计)   
     * @author pengl
     * @date 2018年6月13日 上午11:07:05
     */
    public List<Map<String, Object>> cutPriceOrderStatisticsList(List<String> dates, String type, String dateFlag) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String beginDate = "";
		String endDate = "";
		Map<String, String> mapParam = new HashMap<String, String>();
		if("day".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + " 00:00:00";
				endDate = str + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				mapParam.put("type", type);
				Map<String, Object> map = cutPriceOrderCustomMapperl.cutPriceOrderStatisticsList(mapParam);
				map.put("date", str);
				if("0.00".equals(map.get("yg_commission_amount_sum").toString()) || "0.00".equals(map.get("sale_price_sum").toString())) {
					map.put("con_percent", "0.00");
				}else {
					map.put("con_percent", new BigDecimal(map.get("yg_commission_amount_sum").toString())
						.divide(new BigDecimal(map.get("sale_price_sum").toString()), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				}
				listMap.add(map);
			}
		}else if("month".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + "-01 00:00:00";
				String[] ss = str.split("-");
				endDate = DateUtil.getLastDayOfMonth(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])) + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				mapParam.put("type", type);
				Map<String, Object> map = cutPriceOrderCustomMapperl.cutPriceOrderStatisticsList(mapParam);
				map.put("date", str);
				if("0.00".equals(map.get("yg_commission_amount_sum").toString()) || "0.00".equals(map.get("sale_price_sum").toString())) {
					map.put("con_percent", "0.00");
				}else {
					map.put("con_percent", new BigDecimal(map.get("yg_commission_amount_sum").toString())
						.divide(new BigDecimal(map.get("sale_price_sum").toString()), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				}
				listMap.add(map);
			}
		}else if("year".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + "-01-01 00:00:00";
				endDate = DateUtil.getLastDayOfMonth(Integer.parseInt(str), 12) + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				mapParam.put("type", type);
				Map<String, Object> map = cutPriceOrderCustomMapperl.cutPriceOrderStatisticsList(mapParam);
				map.put("date", str);
				if("0.00".equals(map.get("yg_commission_amount_sum").toString()) || "0.00".equals(map.get("sale_price_sum").toString())) {
					map.put("con_percent", "0.00");
				}else {
					map.put("con_percent", new BigDecimal(map.get("yg_commission_amount_sum").toString())
						.divide(new BigDecimal(map.get("sale_price_sum").toString()), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				}
				listMap.add(map);
			}
		}
		return listMap;
	}
    
    /**
     * 
     * @MethodName: cutPriceOrderNewStatisticsList
     * @Description: (邀请免费拿数据统计（STORY#1328优化）)
     * @author Pengl
     * @date 2019年4月22日 上午10:39:47
     */
    public List<Map<String, Object>> cutPriceOrderNewStatisticsList(List<String> dates, String type, String dateFlag) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String beginDate = "";
		String endDate = "";
		Map<String, String> mapParam = new HashMap<String, String>();
		if("day".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + " 00:00:00";
				endDate = str + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				mapParam.put("type", type);
				Map<String, Object> map = cutPriceOrderCustomMapperl.cutPriceOrderNewStatisticsList(mapParam);
				map.put("date", str);
				if(map.get("download_rate") == null ) {
					map.put("download_rate", "0.00%");
				}
				if(map.get("buy_rate") == null ) {
					map.put("buy_rate", "0.00%");
				}
				if(map.get("success_rate") == null ) {
					map.put("success_rate", "0.00%");
				}
				if(map.get("expense_rate") == null ) {
					map.put("expense_rate", "0.00%");
				}
				listMap.add(map);
			}
		}else if("month".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + "-01 00:00:00";
				String[] ss = str.split("-");
				endDate = DateUtil.getLastDayOfMonth(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])) + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				mapParam.put("type", type);
				Map<String, Object> map = cutPriceOrderCustomMapperl.cutPriceOrderNewStatisticsList(mapParam);
				map.put("date", str);
				if(map.get("download_rate") == null ) {
					map.put("download_rate", "0.00%");
				}
				if(map.get("buy_rate") == null ) {
					map.put("buy_rate", "0.00%");
				}
				if(map.get("success_rate") == null ) {
					map.put("success_rate", "0.00%");
				}
				if(map.get("expense_rate") == null ) {
					map.put("expense_rate", "0.00%");
				}
				listMap.add(map);
			}
		}else if("year".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + "-01-01 00:00:00";
				endDate = DateUtil.getLastDayOfMonth(Integer.parseInt(str), 12) + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				mapParam.put("type", type);
				Map<String, Object> map = cutPriceOrderCustomMapperl.cutPriceOrderNewStatisticsList(mapParam);
				map.put("date", str);
				if(map.get("download_rate") == null ) {
					map.put("download_rate", "0.00%");
				}
				if(map.get("buy_rate") == null ) {
					map.put("buy_rate", "0.00%");
				}
				if(map.get("success_rate") == null ) {
					map.put("success_rate", "0.00%");
				}
				if(map.get("expense_rate") == null ) {
					map.put("expense_rate", "0.00%");
				}
				listMap.add(map);
			}
		}
		return listMap;
	}
	
    public void updateCutPriceOrderListSelective(List<CutPriceOrder> cutPriceOrderList) {
    	cutPriceOrderCustomMapperl.updateCutPriceOrderListSelective(cutPriceOrderList);
    }
    
    /**
     * 
     * @Title cutPriceOrderAssistStatisticsList   
     * @Description TODO(助力减价数据分析)   
     * @author pengl
     * @date 2019年2月15日 上午10:33:40
     */
    public List<Map<String, Object>> cutPriceOrderAssistStatisticsList(String beginDate, String endDate, String productCode, List<String> dates, String type, String dateFormat) {
    	List<Map<String, Object>> returnListMap = new ArrayList<Map<String, Object>>();
    	Map<String, Object> mapParam = new HashMap<String, Object>();
    	mapParam.put("beginDate", beginDate);
    	mapParam.put("endDate", endDate);
    	mapParam.put("productCode", productCode);
    	mapParam.put("type", type);
    	mapParam.put("dateFormat", dateFormat);
    	List<Map<String, Object>> listMap = cutPriceOrderCustomMapperl.cutPriceOrderAssistStatisticsList(mapParam);
    	for(String date : dates) {
    		boolean flag = true;
    		for(Map<String, Object> map : listMap) {
    			if(date.equals(map.get("create_date"))) {
    				Map<String, Object> paramMap = new HashMap<String, Object>();
    				paramMap.put("dateFormat", dateFormat);
    				paramMap.put("createDate", map.get("create_date"));
    				map.put("cut_link_click_log_sum", cutPriceOrderCustomMapperl.getCutLinkClickLogMemberCount(paramMap));
    				returnListMap.add(map);
    				flag = false;
    				break;
    			}
    		}
    		if(flag) {
    			Map<String, Object> dataMap = new HashMap<String, Object>();
    			dataMap.put("create_date", date);
    	    	dataMap.put("member_id_count", "0");
    	    	dataMap.put("cut_link_click_log_sum", "0");
    	    	dataMap.put("cut_new_member_sum", "0");
    	    	dataMap.put("cut_price_order_dtl_sum", "0");
    	    	dataMap.put("cut_price_order_success_sum", "0");
    	    	dataMap.put("combine_order_success_sum", "0");
    	    	dataMap.put("total_pay_amount_sum", "0");
    	    	dataMap.put("pop_rate_amount_sum", "0");
    			returnListMap.add(dataMap);
    		}
    	}
    	Collections.sort(returnListMap, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> c1, Map<String, Object> c2) {
                //升序
                return (c1.get("create_date").toString()).compareTo(c2.get("create_date").toString());
            }
        });
		return returnListMap;
	}
	
}

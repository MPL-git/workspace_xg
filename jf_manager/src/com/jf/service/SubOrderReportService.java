package com.jf.service;

import com.jf.dao.SubOrderReportMapper;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单排行Service
 */
@Service
@Transactional
public class SubOrderReportService extends BaseService<SubOrder, SubOrderExample> {

	@Autowired
	private SubOrderReportMapper subOrderReportMapper;
	
	/**
	 * 
	* @Title selectSubOrder   
	* @Description TODO(获取订单报表统计)   
	* @author 彭立
	* @date 2017年9月8日 上午9:15:35
	 */
//	public List<Map<String, Object>> selectSubOrder(Map<String, Object> map) {
//		return subOrderReportMapper.selectSubOrder(map);
//	}

	public List<Map<String, Object>> selectSubOrder(Map<String, Object> map) {
		List<Map<String, Object>> result = new ArrayList<>();
		result.add(null); //占位 总量应该在第一条
		if(org.apache.commons.lang.StringUtils.equals((String) map.get("row_type"),"product")){
			result.addAll(subOrderReportMapper.selectProductSubOrder(map));
		}else {
			result.addAll(subOrderReportMapper.selectSubOrder(map));
		}
		Map<String, Object> tem = null;
		try {
			tem = new HashMap<>();
			for (int i = 1 ;i < result.size(); i++) {
				tem.put("QUANTITY_SUM", pasrsInt(tem.get("QUANTITY_SUM")) + pasrsInt(result.get(i).get("QUANTITY_SUM")));
				tem.put("SALE_PRICE_SUM", pasrsDouble((pasrsDouble(tem.get("SALE_PRICE_SUM")) + pasrsDouble(result.get(i).get("SALE_PRICE_SUM")))));
				tem.put("MCHT_PREFERENTIAL_SUM", pasrsDouble((pasrsDouble(tem.get("MCHT_PREFERENTIAL_SUM")) + pasrsDouble(result.get(i).get("MCHT_PREFERENTIAL_SUM")))));
				tem.put("PLATFORM_PREFERENTIAL_SUM", pasrsDouble((pasrsDouble(tem.get("PLATFORM_PREFERENTIAL_SUM")) + pasrsDouble(result.get(i).get("PLATFORM_PREFERENTIAL_SUM")))));
				tem.put("PAY_AMOUNT_SUM", pasrsDouble((pasrsDouble(tem.get("PAY_AMOUNT_SUM")) + pasrsDouble(result.get(i).get("PAY_AMOUNT_SUM")))));
                tem.put("AVG_NUM",pasrsDouble(pasrsDouble(((pasrsDouble(tem.get("PAY_AMOUNT_SUM")))/pasrsInt(tem.get("QUANTITY_SUM"))))));
                tem.put("MCHT_RATE",pasrsString(paDouble(((paDouble(tem.get("MCHT_PREFERENTIAL_SUM")))/paDouble(tem.get("SALE_PRICE_SUM"))))));
                tem.put("PLATFORM_RATE",pasrsString(paDouble(((paDouble(tem.get("PLATFORM_PREFERENTIAL_SUM")))/paDouble(tem.get("SALE_PRICE_SUM"))))));
                tem.put("SETTLE_AMOUNT", pasrsDouble((pasrsDouble(tem.get("SETTLE_AMOUNT")) + pasrsDouble(result.get(i).get("SETTLE_AMOUNT")))));
                tem.put("M_RATE",pasrsString(paDouble(((1-(paDouble(tem.get("SETTLE_AMOUNT")))/paDouble(tem.get("PAY_AMOUNT_SUM")))))));
			}
			tem.put("NAME", "总计");
		}catch (Exception e){
			e.printStackTrace();
		}
		if (tem != null){
			result.set(0, tem);
		}
		return result;
	}


	//自营商家类型筛选及汇总数据
	public List<Map<String, Object>> ManageSelfSubOrder(Map<String, Object> map) {
		List<Map<String, Object>> result = new ArrayList<>();
		result.add(null); //占位 总量应该在第一条
		result.addAll(subOrderReportMapper.ManageSelfSubOrder(map));

		Map<String, Object> tem = null;
		try {
			tem = new HashMap<>();
			for (int i = 1 ;i < result.size(); i++) {
				tem.put("QUANTITY_SUM", pasrsInt(tem.get("QUANTITY_SUM")) + pasrsInt(result.get(i).get("QUANTITY_SUM")));
				tem.put("SALE_PRICE_SUM", pasrsDouble((pasrsDouble(tem.get("SALE_PRICE_SUM")) + pasrsDouble(result.get(i).get("SALE_PRICE_SUM")))));
				tem.put("MCHT_PREFERENTIAL_SUM", pasrsDouble((pasrsDouble(tem.get("MCHT_PREFERENTIAL_SUM")) + pasrsDouble(result.get(i).get("MCHT_PREFERENTIAL_SUM")))));
				tem.put("PLATFORM_PREFERENTIAL_SUM", pasrsDouble((pasrsDouble(tem.get("PLATFORM_PREFERENTIAL_SUM")) + pasrsDouble(result.get(i).get("PLATFORM_PREFERENTIAL_SUM")))));
				tem.put("PAY_AMOUNT_SUM", pasrsDouble((pasrsDouble(tem.get("PAY_AMOUNT_SUM")) + pasrsDouble(result.get(i).get("PAY_AMOUNT_SUM")))));
				tem.put("AVG_NUM",pasrsDouble(pasrsDouble(((pasrsDouble(tem.get("PAY_AMOUNT_SUM")))/pasrsInt(tem.get("QUANTITY_SUM"))))));
				tem.put("MCHT_RATE",pasrsString(paDouble(((paDouble(tem.get("MCHT_PREFERENTIAL_SUM")))/paDouble(tem.get("SALE_PRICE_SUM"))))));
				tem.put("PLATFORM_RATE",pasrsString(paDouble(((paDouble(tem.get("PLATFORM_PREFERENTIAL_SUM")))/paDouble(tem.get("SALE_PRICE_SUM"))))));
				tem.put("SETTLE_AMOUNT", pasrsDouble((pasrsDouble(tem.get("SETTLE_AMOUNT")) + pasrsDouble(result.get(i).get("SETTLE_AMOUNT")))));
				//毛利
				tem.put("GROSS_MARGIN", pasrsDouble((pasrsDouble(tem.get("GROSS_MARGIN")) + pasrsDouble(result.get(i).get("GROSS_MARGIN")))));
				//毛利率-测试环境判断
				if ("0.0".equals(tem.get("PAY_AMOUNT_SUM").toString())){
					tem.put("M_RATE","0.00");
				}else {
					tem.put("M_RATE",pasrsString(paDouble(((1-(paDouble(tem.get("SETTLE_AMOUNT")))/paDouble(tem.get("PAY_AMOUNT_SUM")))))));
				}
			}
			tem.put("NAME", "总计");
		}catch (Exception e){
			e.printStackTrace();
		}
		if (tem != null){
			result.set(0, tem);
		}
		return result;
	}





	public List<Map<String, Object>> selectProductSubOrder(Map<String, Object> map) {
		return subOrderReportMapper.selectProductSubOrder(map);
	}

	private Integer pasrsInt(Object o){
		if (o == null){
			return 0;
		}
		Integer val = 0;
		if (o instanceof BigDecimal){
			BigDecimal b = (BigDecimal)o;
			val=Integer.parseInt(b.toString());
		}else {
			val = (Integer) o;
		}
		return val;
	}

	private Double pasrsDouble(Object o){
		if (o == null){
			return 0d;
		}
		Double val = 0d;
		if (o instanceof BigDecimal){
			BigDecimal b = (BigDecimal)o;
			val=Double.parseDouble(b.toString());
		}else {
			val = (Double)o ;
		}
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(val));
	}

    private Double paDouble(Object o){
        if (o == null){
            return 0d;
        }
        Double val = 0d;
        if (o instanceof BigDecimal){
            BigDecimal b = (BigDecimal)o;
            val=Double.parseDouble(b.toString());
        }else {
            val = (Double)o ;
        }
        DecimalFormat df = new DecimalFormat("#.0000");
        return Double.valueOf(df.format(val));
    }

	public String pasrsString(Object o){
		if (o==null || o.equals(0)){
			return "0.00%";
		}
		NumberFormat percentInstance = NumberFormat.getPercentInstance();
		percentInstance.setMinimumFractionDigits(2); // 保留小数两位
		String format = percentInstance.format(o);
		return format;
	}


	/**
	 * 
	 * @Title selectReQuantityRate   
	 * @Description TODO(获取退货率)   
	 * @author pengl
	 * @date 2018年10月25日 下午5:53:29
	 */
	public String selectReQuantityRate(Map<String, Object> map) {
		return subOrderReportMapper.selectReQuantityRate(map);
	}
	
	/**
	 * 
	 * @Title selectGoodCommentRate   
	 * @Description TODO(获取好评率)   
	 * @author pengl
	 * @date 2018年10月25日 下午5:53:32
	 */
	public String selectGoodCommentRate(Map<String, Object> map) {
		return subOrderReportMapper.selectGoodCommentRate(map);
	}

}

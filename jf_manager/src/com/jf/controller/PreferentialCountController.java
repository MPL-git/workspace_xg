package com.jf.controller;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.IntegralDailyStatistics;
import com.jf.entity.IntegralDailyStatisticsExample;
import com.jf.entity.OrderDtlCustom;
import com.jf.service.IntegralDailyStatisticsService;
import com.jf.service.MchtInfoService;
import com.jf.service.OrderDtlService;
import com.jf.service.SubOrderService;
import com.jf.vo.Page;

@Controller
public class PreferentialCountController extends BaseController {
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private IntegralDailyStatisticsService integralDailyStatisticsService;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 平台优惠每日汇总
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/preferentialCount/list.shtml")
	public ModelAndView countList(HttpServletRequest request) {
		String rtPage = "/preferentialCount/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(request.getParameter("date_begin")) && StringUtils.isEmpty(request.getParameter("date_end"))){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date_end = df.format(new Date());
			String date_begin = date_end.substring(0,7)+"-01";
			resMap.put("date_end", date_end);
			resMap.put("date_begin", date_begin);
		}else{
			resMap.put("date_end", request.getParameter("date_end"));
			resMap.put("date_begin", request.getParameter("date_begin"));
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每日退款汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/preferentialCount/data.shtml")
	@ResponseBody
	public Map<String, Object> data(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			paramMap.put("dateBegin", dateBegin);
			paramMap.put("dateEnd", dateEnd);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.platformPreferentialEachDayCountList(paramMap);
			HashMap<String, OrderDtlCustom> map = new HashMap<String, OrderDtlCustom>();
			List<String> containDays = new ArrayList<String>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				BigDecimal sum1 = orderDtlCustom.getEachDayPayAmount().add(orderDtlCustom.getEachDayPreferentialAmount());
				if(sum1.compareTo(new BigDecimal(0)) == 0){
					orderDtlCustom.setEachDayPreferentialRate(new BigDecimal(0));
				}else{
					orderDtlCustom.setEachDayPreferentialRate(orderDtlCustom.getEachDayPreferentialAmount().divide(sum1,2));
				}
				BigDecimal sum2 = orderDtlCustom.getEachDayCompletePayAmount().add(orderDtlCustom.getEachDayCompletePreferentialAmount());
				if(sum2.compareTo(new BigDecimal(0)) == 0){
					orderDtlCustom.setEachDayCompletePreferentialRate(new BigDecimal(0));
				}else{
					orderDtlCustom.setEachDayCompletePreferentialRate(orderDtlCustom.getEachDayCompletePreferentialAmount().divide(sum2,2));
				}
				containDays.add(orderDtlCustom.getEachDay());
				map.put(orderDtlCustom.getEachDay(), orderDtlCustom);
			}
			List<String> betweenDays = this.getBetweenDays(dateBegin, dateEnd);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					HashMap<String, Object> pMap = new HashMap<String, Object>();
					pMap.put("dateStrStart", betweenDays.get(i)+" 00:00:00");
					pMap.put("dateStrEnd", betweenDays.get(i)+" 23:59:59");
					OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
					orderDtlCustom.setEachDay(betweenDays.get(i));
					orderDtlCustom.setEachDayPayAmount(new BigDecimal(0));
					orderDtlCustom.setEachDayPreferentialAmount(new BigDecimal(0));
					orderDtlCustom.setEachDayPreferentialRate(new BigDecimal(0));
					BigDecimal completePayAmount = orderDtlService.countCompletePayAmount(pMap);
					orderDtlCustom.setEachDayCompletePayAmount(completePayAmount);
					BigDecimal completePreferentialAmount = orderDtlService.countCompletePreferentialAmount(pMap);
					orderDtlCustom.setEachDayCompletePreferentialAmount(completePreferentialAmount);
					BigDecimal sum = completePayAmount.add(completePreferentialAmount);
					if(sum.compareTo(new BigDecimal(0)) == 0){
						orderDtlCustom.setEachDayCompletePreferentialRate(new BigDecimal(0));
					}else{
						orderDtlCustom.setEachDayCompletePreferentialRate(completePreferentialAmount.divide(sum,2));
					}
					orderDtlCustoms.add(orderDtlCustom);
					map.put(betweenDays.get(i), orderDtlCustom);
				}
			}
			
			Collections.sort(orderDtlCustoms, new Comparator<OrderDtlCustom>() {
	            @Override
	            public int compare(OrderDtlCustom c1, OrderDtlCustom c2) {
	                //升序
	                return c1.getEachDay().compareTo(c2.getEachDay());
	            }
	        });
			resMap.put("Rows", orderDtlCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	public List<String> getBetweenDays(String stime,String etime){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=null;
        Date eDate=null;
        try {
             sdate=df.parse(stime);
             eDate=df.parse(etime);
        } catch (Exception e) {
              e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
              list.add(df.format(sdate));
              c.setTime(sdate);
              c.add(Calendar.DATE, 1); // 日期加1天
              sdate = c.getTime();
              }
        return list;
  }
	
	/**
	 * 导出汇总表excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/preferentialCount/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			paramMap.put("dateBegin", dateBegin);
			paramMap.put("dateEnd", dateEnd);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.platformPreferentialEachDayCountList(paramMap);
			HashMap<String, OrderDtlCustom> map = new HashMap<String, OrderDtlCustom>();
			List<String> containDays = new ArrayList<String>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				BigDecimal sum1 = orderDtlCustom.getEachDayPayAmount().add(orderDtlCustom.getEachDayPreferentialAmount());
				if(sum1.compareTo(new BigDecimal(0)) == 0){
					orderDtlCustom.setEachDayPreferentialRate(new BigDecimal(0));
				}else{
					orderDtlCustom.setEachDayPreferentialRate(orderDtlCustom.getEachDayPreferentialAmount().divide(sum1,2));
				}
				BigDecimal sum2 = orderDtlCustom.getEachDayCompletePayAmount().add(orderDtlCustom.getEachDayCompletePreferentialAmount());
				if(sum2.compareTo(new BigDecimal(0)) == 0){
					orderDtlCustom.setEachDayCompletePreferentialRate(new BigDecimal(0));
				}else{
					orderDtlCustom.setEachDayCompletePreferentialRate(orderDtlCustom.getEachDayCompletePreferentialAmount().divide(sum2,2));
				}
				containDays.add(orderDtlCustom.getEachDay());
				map.put(orderDtlCustom.getEachDay(), orderDtlCustom);
			}
			List<String> betweenDays = this.getBetweenDays(dateBegin, dateEnd);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					HashMap<String, Object> pMap = new HashMap<String, Object>();
					pMap.put("dateStrStart", betweenDays.get(i)+" 00:00:00");
					pMap.put("dateStrEnd", betweenDays.get(i)+" 23:59:59");
					OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
					orderDtlCustom.setEachDay(betweenDays.get(i));
					orderDtlCustom.setEachDayPayAmount(new BigDecimal(0));
					orderDtlCustom.setEachDayPreferentialAmount(new BigDecimal(0));
					orderDtlCustom.setEachDayPreferentialRate(new BigDecimal(0));
					BigDecimal completePayAmount = orderDtlService.countCompletePayAmount(pMap);
					orderDtlCustom.setEachDayCompletePayAmount(completePayAmount);
					BigDecimal completePreferentialAmount = orderDtlService.countCompletePreferentialAmount(pMap);
					orderDtlCustom.setEachDayCompletePreferentialAmount(completePreferentialAmount);
					BigDecimal sum = completePayAmount.add(completePreferentialAmount);
					if(sum.compareTo(new BigDecimal(0)) == 0){
						orderDtlCustom.setEachDayCompletePreferentialRate(new BigDecimal(0));
					}else{
						orderDtlCustom.setEachDayCompletePreferentialRate(completePreferentialAmount.divide(sum,2));
					}
					orderDtlCustoms.add(orderDtlCustom);
					map.put(betweenDays.get(i), orderDtlCustom);
				}
			}
			
			Collections.sort(orderDtlCustoms, new Comparator<OrderDtlCustom>() {
	            @Override
	            public int compare(OrderDtlCustom c1, OrderDtlCustom c2) {
	                //升序
	                return c1.getEachDay().compareTo(c2.getEachDay());
	            }
	        });
			String[] titles = { "日期", "日付款单金额", "日付款单优惠","付款单优惠比例","日完成单金额","日完成单优惠","完成单优惠比例"};
			ExcelBean excelBean = new ExcelBean("导出汇总表.xls",
					"导出汇总表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				String[] data = {
						orderDtlCustom.getEachDay(),
						orderDtlCustom.getEachDayPayAmount()==null?"":orderDtlCustom.getEachDayPayAmount().toString(),
						orderDtlCustom.getEachDayPreferentialAmount()==null?"":orderDtlCustom.getEachDayPreferentialAmount().toString(),
						orderDtlCustom.getEachDayPreferentialRate()==null?"":orderDtlCustom.getEachDayPreferentialRate().toString(),
						orderDtlCustom.getEachDayCompletePayAmount()==null?"":orderDtlCustom.getEachDayCompletePayAmount().toString(),
						orderDtlCustom.getEachDayCompletePreferentialAmount()==null?"":orderDtlCustom.getEachDayCompletePreferentialAmount().toString(),
						orderDtlCustom.getEachDayCompletePreferentialRate()==null?"":orderDtlCustom.getEachDayCompletePreferentialRate().toString()
			};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 金币每日汇总列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/preferentialCount/integralDailyStatistics/list.shtml")
	public ModelAndView integralDailyStatisticsList(HttpServletRequest request) {
		String rtPage = "/preferentialCount/integralDailyStatistics/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(request.getParameter("date_begin")) && StringUtils.isEmpty(request.getParameter("date_end"))){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//获取当前月第一天
			Calendar calendarNowDay = Calendar.getInstance();
			calendarNowDay.add(Calendar.MONTH, 0);
			calendarNowDay.set(Calendar.DAY_OF_MONTH, 1); //设置为1号，当前日期既为本月第一天 
			Calendar calendarStartDay = Calendar.getInstance();
			if(sdf.format(date).equals(sdf.format(calendarNowDay.getTime()))) {
				//获取前月
				calendarStartDay.add(Calendar.MONTH, -1);
			}
			calendarStartDay.set(Calendar.DAY_OF_MONTH, 1);
			Calendar calendarEndDay = Calendar.getInstance();
			calendarEndDay.add(Calendar.DATE, -1);
			resMap.put("date_begin", sdf.format(calendarStartDay.getTime()));
			resMap.put("date_end", sdf.format(calendarEndDay.getTime()));
		}else{
			resMap.put("date_end", request.getParameter("date_end"));
			resMap.put("date_begin", request.getParameter("date_begin"));
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 金币每日汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/preferentialCount/integralDailyStatistics/data.shtml")
	@ResponseBody
	public Map<String, Object> integralDailyStatisticsData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<IntegralDailyStatistics> integralDailyStatisticsList = null;
		Integer totalCount = 0;
		try {
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			IntegralDailyStatisticsExample integralDailyStatisticsExample = new IntegralDailyStatisticsExample();
			IntegralDailyStatisticsExample.Criteria criteria = integralDailyStatisticsExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(dateBegin)){
				criteria.andStatisticDateGreaterThanOrEqualTo(dateBegin);
			}
			if(!StringUtils.isEmpty(dateEnd)){
				criteria.andStatisticDateLessThanOrEqualTo(dateEnd);
			}
			integralDailyStatisticsExample.setLimitStart(page.getLimitStart());
			integralDailyStatisticsExample.setLimitSize(page.getLimitSize());
			integralDailyStatisticsList = integralDailyStatisticsService.selectByExample(integralDailyStatisticsExample);
			totalCount = integralDailyStatisticsService.countByExample(integralDailyStatisticsExample);
			for(IntegralDailyStatistics integralDailyStatistics : integralDailyStatisticsList) {
				Map<String, Object> map = new HashMap<String, Object>();
				String startStatisticDate = integralDailyStatistics.getStatisticDate()+" 00:00:00";
				String endStatisticDate = integralDailyStatistics.getStatisticDate()+" 23:59:59";
				map.put("startStatisticDate", startStatisticDate);
				map.put("endStatisticDate", endStatisticDate);
				
				//统计积分超时失效(目前没有使用到，默认为0)
				integralDailyStatistics.setInvalidIntegral(0);
				
				//统计购物赠送
				map.put("tallyMode_1", "1");
				map.put("type_1", 1);
				//统计手机认证
				map.put("tallyMode_2", "1");
				map.put("type_2", 2);
				//统计完善资料
				map.put("tallyMode_3", "1");
				map.put("type_3", 3);
				//统计购物抵扣
				map.put("tallyMode_4", "2");
				map.put("type_4", 4);
				//统计积分兑换
				map.put("tallyMode_5", "2");
				map.put("type_5", 5);
				//统计系统赠送
				map.put("tallyMode_6", "1");
				map.put("type_6", 6);
				//统计抵扣返还
				map.put("tallyMode_7", "1");
				map.put("type_7", 7);
				//统计违规补偿
				map.put("tallyMode_8", "1");
				map.put("type_8", 8);
				//竞猜扣除
				map.put("tallyMode_9", "2");
				map.put("type_9", 9);
				//竞猜奖励
				map.put("tallyMode_10", "1");
				map.put("type_10", 10);
				
				//输入邀请码
				map.put("tallyMode_17", "1");
				map.put("type_17", 17);
				
				//查看新星攻略
				map.put("tallyMode_18", "1");
				map.put("type_18", 18);
				
				Map<String, Object> returnMap = integralDailyStatisticsService.statisticsIntegral(map);
				
				integralDailyStatistics.setIntegralType1(Integer.parseInt(returnMap.get("total_integral_1")==null?"0":returnMap.get("total_integral_1").toString()));
				integralDailyStatistics.setIntegralType2(Integer.parseInt(returnMap.get("total_integral_2")==null?"0":returnMap.get("total_integral_2").toString()));
				integralDailyStatistics.setIntegralType3(Integer.parseInt(returnMap.get("total_integral_3")==null?"0":returnMap.get("total_integral_3").toString()));
				integralDailyStatistics.setIntegralType4(Integer.parseInt(returnMap.get("total_integral_4")==null?"0":returnMap.get("total_integral_4").toString()));
				integralDailyStatistics.setIntegralType5(Integer.parseInt(returnMap.get("total_integral_5")==null?"0":returnMap.get("total_integral_5").toString()));
				integralDailyStatistics.setIntegralType6(Integer.parseInt(returnMap.get("total_integral_6")==null?"0":returnMap.get("total_integral_6").toString()));
				integralDailyStatistics.setIntegralType7(Integer.parseInt(returnMap.get("total_integral_7")==null?"0":returnMap.get("total_integral_7").toString()));
				integralDailyStatistics.setIntegralType8(Integer.parseInt(returnMap.get("total_integral_8")==null?"0":returnMap.get("total_integral_8").toString()));
				integralDailyStatistics.setIntegralType9(Integer.parseInt(returnMap.get("total_integral_9")==null?"0":returnMap.get("total_integral_9").toString()));
				integralDailyStatistics.setIntegralType10(Integer.parseInt(returnMap.get("total_integral_10")==null?"0":returnMap.get("total_integral_10").toString()));
				integralDailyStatistics.setIntegralType17(Integer.parseInt(returnMap.get("total_integral_17")==null?"0":returnMap.get("total_integral_17").toString()));
				integralDailyStatistics.setIntegralType18(Integer.parseInt(returnMap.get("total_integral_18")==null?"0":returnMap.get("total_integral_18").toString()));
				
				integralDailyStatistics.setEndIntegral(integralDailyStatistics.getBeginIntegral()
						+ integralDailyStatistics.getIntegralType1()
						+ integralDailyStatistics.getIntegralType2()
						+ integralDailyStatistics.getIntegralType3()
						- integralDailyStatistics.getIntegralType4()
						- integralDailyStatistics.getIntegralType5()
						+ integralDailyStatistics.getIntegralType6()
						+ integralDailyStatistics.getIntegralType7()
						+ integralDailyStatistics.getIntegralType8()
						- integralDailyStatistics.getIntegralType9()
						+ integralDailyStatistics.getIntegralType10()
						+ integralDailyStatistics.getIntegralType17()
						+ integralDailyStatistics.getIntegralType18()
						- integralDailyStatistics.getInvalidIntegral());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", integralDailyStatisticsList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 导出金币每日汇总表excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/preferentialCount/integralDailyStatistics/export.shtml")
	public void exportIntegralDailyStatistics(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			
			IntegralDailyStatisticsExample integralDailyStatisticsExample = new IntegralDailyStatisticsExample();
			IntegralDailyStatisticsExample.Criteria criteria = integralDailyStatisticsExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(dateBegin)){
				criteria.andStatisticDateGreaterThanOrEqualTo(dateBegin);
			}
			if(!StringUtils.isEmpty(dateEnd)){
				criteria.andStatisticDateLessThanOrEqualTo(dateEnd);
			}
			List<IntegralDailyStatistics> integralDailyStatisticsList = integralDailyStatisticsService.selectByExample(integralDailyStatisticsExample);
			for(IntegralDailyStatistics integralDailyStatistics : integralDailyStatisticsList) {
				Map<String, Object> map = new HashMap<String, Object>();
				String startStatisticDate = integralDailyStatistics.getStatisticDate()+" 00:00:00";
				String endStatisticDate = integralDailyStatistics.getStatisticDate()+" 23:59:59";
				map.put("startStatisticDate", startStatisticDate);
				map.put("endStatisticDate", endStatisticDate);
				
				//统计积分超时失效(目前没有使用到，默认为0)
				integralDailyStatistics.setInvalidIntegral(0);
				
				//统计购物赠送
				map.put("tallyMode_1", "1");
				map.put("type_1", 1);
				//统计手机认证
				map.put("tallyMode_2", "1");
				map.put("type_2", 2);
				//统计完善资料
				map.put("tallyMode_3", "1");
				map.put("type_3", 3);
				//统计购物抵扣
				map.put("tallyMode_4", "2");
				map.put("type_4", 4);
				//统计积分兑换
				map.put("tallyMode_5", "2");
				map.put("type_5", 5);
				//统计系统赠送
				map.put("tallyMode_6", "1");
				map.put("type_6", 6);
				//统计抵扣返还
				map.put("tallyMode_7", "1");
				map.put("type_7", 7);
				//统计违规补偿
				map.put("tallyMode_8", "1");
				map.put("type_8", 8);
				//竞猜扣除
				map.put("tallyMode_9", "2");
				map.put("type_9", 9);
				//竞猜奖励
				map.put("tallyMode_10", "1");
				map.put("type_10", 10);
				
				//输入邀请码
				map.put("tallyMode_17", "1");
				map.put("type_17", 17);
				
				//查看新星攻略
				map.put("tallyMode_18", "1");
				map.put("type_18", 18);
				
				Map<String, Object> returnMap = integralDailyStatisticsService.statisticsIntegral(map);
				
				integralDailyStatistics.setIntegralType1(Integer.parseInt(returnMap.get("total_integral_1")==null?"0":returnMap.get("total_integral_1").toString()));
				integralDailyStatistics.setIntegralType2(Integer.parseInt(returnMap.get("total_integral_2")==null?"0":returnMap.get("total_integral_2").toString()));
				integralDailyStatistics.setIntegralType3(Integer.parseInt(returnMap.get("total_integral_3")==null?"0":returnMap.get("total_integral_3").toString()));
				integralDailyStatistics.setIntegralType4(Integer.parseInt(returnMap.get("total_integral_4")==null?"0":returnMap.get("total_integral_4").toString()));
				integralDailyStatistics.setIntegralType5(Integer.parseInt(returnMap.get("total_integral_5")==null?"0":returnMap.get("total_integral_5").toString()));
				integralDailyStatistics.setIntegralType6(Integer.parseInt(returnMap.get("total_integral_6")==null?"0":returnMap.get("total_integral_6").toString()));
				integralDailyStatistics.setIntegralType7(Integer.parseInt(returnMap.get("total_integral_7")==null?"0":returnMap.get("total_integral_7").toString()));
				integralDailyStatistics.setIntegralType8(Integer.parseInt(returnMap.get("total_integral_8")==null?"0":returnMap.get("total_integral_8").toString()));
				integralDailyStatistics.setIntegralType9(Integer.parseInt(returnMap.get("total_integral_9")==null?"0":returnMap.get("total_integral_9").toString()));
				integralDailyStatistics.setIntegralType10(Integer.parseInt(returnMap.get("total_integral_10")==null?"0":returnMap.get("total_integral_10").toString()));
				integralDailyStatistics.setIntegralType17(Integer.parseInt(returnMap.get("total_integral_17")==null?"0":returnMap.get("total_integral_17").toString()));
				integralDailyStatistics.setIntegralType18(Integer.parseInt(returnMap.get("total_integral_18")==null?"0":returnMap.get("total_integral_18").toString()));
				
				
				integralDailyStatistics.setEndIntegral(integralDailyStatistics.getBeginIntegral()
						+ integralDailyStatistics.getIntegralType1()
						+ integralDailyStatistics.getIntegralType2()
						+ integralDailyStatistics.getIntegralType3()
						- integralDailyStatistics.getIntegralType4()
						- integralDailyStatistics.getIntegralType5()
						+ integralDailyStatistics.getIntegralType6()
						+ integralDailyStatistics.getIntegralType7()
						+ integralDailyStatistics.getIntegralType8()
						- integralDailyStatistics.getIntegralType9()
						+ integralDailyStatistics.getIntegralType10()
						+ integralDailyStatistics.getIntegralType17()
						+ integralDailyStatistics.getIntegralType18()
						- integralDailyStatistics.getInvalidIntegral());
				
			}
			
			String[] titles = { "日期", "期初余额", "购物赠送（+）","手机认证（+）","完善资料（+）","购物抵扣（-）",
					"积分兑换（-）","系统赠送（+）","抵扣返还（+）","售后补偿（+）","输入邀请码（+）","查看新星攻略（+）","参与游戏（-）","参与游戏（-）","积分超时失效（-）",
					"其它赠送","其它消费","期末余额"};
			ExcelBean excelBean = new ExcelBean("积分每日汇总表.xls",
					"积分每日汇总表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(IntegralDailyStatistics integralDailyStatistics:integralDailyStatisticsList){
				String[] data = {
						integralDailyStatistics.getStatisticDate(),
						integralDailyStatistics.getBeginIntegral()==null?"":integralDailyStatistics.getBeginIntegral().toString(),
						integralDailyStatistics.getIntegralType1()==null?"":integralDailyStatistics.getIntegralType1().toString(),
						integralDailyStatistics.getIntegralType2()==null?"":integralDailyStatistics.getIntegralType2().toString(),
						integralDailyStatistics.getIntegralType3()==null?"":integralDailyStatistics.getIntegralType3().toString(),
						integralDailyStatistics.getIntegralType4()==null?"":integralDailyStatistics.getIntegralType4().toString(),
						integralDailyStatistics.getIntegralType5()==null?"":integralDailyStatistics.getIntegralType5().toString(),
						integralDailyStatistics.getIntegralType6()==null?"":integralDailyStatistics.getIntegralType6().toString(),
						integralDailyStatistics.getIntegralType7()==null?"":integralDailyStatistics.getIntegralType7().toString(),
						integralDailyStatistics.getIntegralType8()==null?"":integralDailyStatistics.getIntegralType8().toString(),
						integralDailyStatistics.getIntegralType17()==null?"":integralDailyStatistics.getIntegralType17().toString(),
						integralDailyStatistics.getIntegralType18()==null?"":integralDailyStatistics.getIntegralType18().toString(),
						integralDailyStatistics.getIntegralType9()==null?"":integralDailyStatistics.getIntegralType9().toString(),
						integralDailyStatistics.getIntegralType10()==null?"":integralDailyStatistics.getIntegralType10().toString(),
						integralDailyStatistics.getInvalidIntegral()==null?"":integralDailyStatistics.getInvalidIntegral().toString(),
						integralDailyStatistics.getOtherGive()==null?"":integralDailyStatistics.getOtherGive().toString(),
						integralDailyStatistics.getOtherUse()==null?"":integralDailyStatistics.getOtherUse().toString(),
						integralDailyStatistics.getEndIntegral()==null?"":integralDailyStatistics.getEndIntegral().toString()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

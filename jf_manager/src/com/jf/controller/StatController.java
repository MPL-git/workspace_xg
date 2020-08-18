package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseController;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.AreaUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MemberInfoCustomAreaCount;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.service.CombineOrderService;
import com.jf.service.GrowthDtlService;
import com.jf.service.IntegralDtlService;
import com.jf.service.MemberInfoService;
import com.jf.service.SysAppLoginLogService;
import com.jf.vo.MemberPayDataVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
public class StatController extends ABaseController {

	private static Logger logger = LoggerFactory.getLogger(StatController.class);

	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private SysAppLoginLogService sysAppLoginLogService;
	@Resource
	private CombineOrderService combineOrderService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private GrowthDtlService growthDtlService;


	/**
	 * 会员数据统计
     */
	@RequestMapping(value = "/stat/memberCountIndex.shtml")
	public ModelAndView memberCountIndex() {
		Date now = new Date();
		QueryObject queryObject = new QueryObject();
		int allCount = memberInfoService.count(queryObject);	// 会员总数
		queryObject = new QueryObject();
		queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(now));
		queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(now));
		int todayIncreaseCount = memberInfoService.count(queryObject);	// 今天新增会员数
		int todayLoginCount = sysAppLoginLogService.countMember(queryObject);	// 今天活跃会员数
		queryObject = new QueryObject();
		queryObject.addQuery("status", Const.COMBINE_ORDER_STATUS_PAID);
		queryObject.addQuery("payStatus", Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
		queryObject.addQuery("payDateAfter", DateTimeUtil.getStartTimeOfDate(now));
		queryObject.addQuery("payDateBefore", DateTimeUtil.getEndTimeOfDate(now));
		int todayOrderPaidCount = combineOrderService.countMember(queryObject);	// 今天消费会员数
		queryObject.addQuery("repeatOrder", true);
		int todayOrderRepeatCount = combineOrderService.countMember(queryObject);	// 今天复购会员数

		Date yesterday = DateTimeUtil.plusDays(now, -1);
		queryObject = new QueryObject();
		queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(yesterday));
		queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(yesterday));
		int yesterdayIncreaseCount = memberInfoService.count(queryObject);	// 昨天新增会员数
		int yesterdayLoginCount = sysAppLoginLogService.countMember(queryObject);	// 昨天活跃会员数
		queryObject = new QueryObject();
		queryObject.addQuery("status", Const.COMBINE_ORDER_STATUS_PAID);
		queryObject.addQuery("payStatus", Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
		queryObject.addQuery("payDateAfter", DateTimeUtil.getStartTimeOfDate(yesterday));
		queryObject.addQuery("payDateBefore", DateTimeUtil.getEndTimeOfDate(yesterday));
		int yesterdayOrderPaidCount = combineOrderService.countMember(queryObject);	// 昨天消费会员数
		queryObject.addQuery("repeatOrder", true);
		int yesterdayOrderRepeatCount = combineOrderService.countMember(queryObject);	// 昨天复购会员数

		queryObject = new QueryObject();
		queryObject.addQuery("createDateAfter", DateTimeUtil.getFirstdayOfMonth(now));
		queryObject.addQuery("createDateBefore", DateTimeUtil.getLastdayOfMonth(now));
		int monthIncreaseCount = memberInfoService.count(queryObject);	// 本月新增会员数
		int monthLoginCount = sysAppLoginLogService.countMember(queryObject);	// 本月活跃会员数
		queryObject = new QueryObject();
		queryObject.addQuery("status", Const.COMBINE_ORDER_STATUS_PAID);
		queryObject.addQuery("payStatus", Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
		queryObject.addQuery("payDateAfter", DateTimeUtil.getFirstdayOfMonth(now));
		queryObject.addQuery("payDateBefore",  DateTimeUtil.getLastdayOfMonth(now));
		int monthOrderPaidCount = combineOrderService.countMember(queryObject);	// 本月消费会员数
		queryObject.addQuery("repeatOrder", true);
		int monthOrderRepeatCount = combineOrderService.countMember(queryObject);	// 本月复购会员数

		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("allCount", allCount);
		
		resMap.put("todayIncreaseCount", todayIncreaseCount);
		resMap.put("todayLoginCount", todayLoginCount);
		resMap.put("todayOrderPaidCount", todayOrderPaidCount);
		resMap.put("todayOrderRepeatCount", todayOrderRepeatCount);
		
		resMap.put("yesterdayIncreaseCount", yesterdayIncreaseCount);
		resMap.put("yesterdayLoginCount", yesterdayLoginCount);
		resMap.put("yesterdayOrderPaidCount", yesterdayOrderPaidCount);
		resMap.put("yesterdayOrderRepeatCount", yesterdayOrderRepeatCount);
		
		resMap.put("monthIncreaseCount", monthIncreaseCount);
		resMap.put("monthLoginCount", monthLoginCount);
		resMap.put("monthOrderPaidCount", monthOrderPaidCount);
		resMap.put("monthOrderRepeatCount", monthOrderRepeatCount);

		return new ModelAndView("/stat/member_count", resMap);
	}

	/**
	 * 统计会员数
     */
	@ResponseBody
	@RequestMapping(value = "/stat/memberCountStat.shtml")
	public String memberCountStat() {
		// 1：新增会员  2：活跃会员  3：消费会员
		int type = getParaToInt("type", 1);
		// 默认取本月数据
		String beginDate1 = getPara("beginDate1", DateTimeUtil.formatDate(DateTimeUtil.getFirstdayOfMonth(new Date()), "yyyy-MM-dd"));
		String endDate1 = getPara("endDate1", DateTimeUtil.formatDate(DateTimeUtil.getLastdayOfMonth(new Date()), "yyyy-MM-dd"));
		String beginDate2 = getPara("beginDate2");
		String endDate2 = getPara("endDate2");

		if(DateTimeUtil.parseDate(beginDate1, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate1, "yyyy-MM-dd"))){
			throw new BizException("开始时间不能大于结束时间");
		}
		if(StrKit.notBlank(beginDate2) && StrKit.notBlank(endDate2) && DateTimeUtil.parseDate(beginDate2, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate2, "yyyy-MM-dd"))){
			throw new BizException("第二时间段开始时间不能大于结束时间");
		}

		List<Map<String, Object>> serieList = new ArrayList<Map<String, Object>>();
		serieList.add(getMemberCountSerie(beginDate1, endDate1, type));
		if(StrKit.notBlank(beginDate2) && StrKit.notBlank(endDate2)){
			serieList.add(getMemberCountSerie(beginDate2, endDate2, type));
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("serieList", serieList);

		return json(data);
	}

	/**
	 * 计算会员数
     */
	private Map<String, Object> getMemberCountSerie(String beginDate1, String endDate1, int type){
		Map<String, Object> serie = new HashMap<String, Object>();
		serie.put("type", "line");
		//serie.put("name", beginDate1 + "至" + endDate1);

		List<String[]> dataList = new ArrayList<String[]>();
		QueryObject queryObject;
		int y = 0;
		List<Date> listDate = DateTimeUtil.segmentDateWithDay(DateTimeUtil.parseDate(beginDate1), DateTimeUtil.parseDate(endDate1), 8);
		for(Date date : listDate){
			if(type == 1){
				queryObject = new QueryObject();
				queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(date));
				queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(date));
				y = memberInfoService.count(queryObject);	// 新增会员数
			}else if(type == 2){
				queryObject = new QueryObject();
				queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(date));
				queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(date));
				y = sysAppLoginLogService.countMember(queryObject);	//活跃会员数
			}else if(type == 3){
				queryObject = new QueryObject();
				queryObject.addQuery("status", Const.COMBINE_ORDER_STATUS_PAID);
				queryObject.addQuery("payStatus", Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
				queryObject.addQuery("payDateAfter", DateTimeUtil.getStartTimeOfDate(date));
				queryObject.addQuery("payDateBefore", DateTimeUtil.getEndTimeOfDate(date));
				y = combineOrderService.countMember(queryObject);	// 消费会员数
			}
			dataList.add(new String[]{DateTimeUtil.formatDate(date, "yyyy-MM-dd"), String.valueOf(y)});
		}
		serie.put("data", dataList);
		return serie;
	}


	/**
	 * 金币数据统计
	 */
	@RequestMapping(value = "/stat/integralIndex.shtml")
	public ModelAndView integralIndex() {
		Map<String,Object> resMap = new HashMap<String,Object>();

		Date now =new Date();

		// 总数
		QueryObject queryObject = new QueryObject();
		resMap.put("integralCount", integralDtlService.selectTotalIntegral());	// 金币总数
		resMap.put("growthCount", growthDtlService.selectTotalGValue());	// 成长值总数

		// 今天
		queryObject = new QueryObject();
		queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_PLUS);
		queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(now));
		queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(now));
		int todayIntegralPlusCount = integralDtlService.sumIntegral(queryObject);
		resMap.put("todayIntegralPlusCount", todayIntegralPlusCount);	// 今天新增金币数
		queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_MINUS);
		int todayIntegralMinusCount = integralDtlService.sumIntegral(queryObject);	// 今天消耗金币数
		resMap.put("todayIntegralMinusCount", todayIntegralMinusCount);	// 今天消耗金币数
		resMap.put("todayIntegralNetPlusCount", todayIntegralPlusCount - todayIntegralMinusCount);	// 今天净增金币数
		resMap.put("todayGrowthCount", growthDtlService.sumGValue(queryObject));	// 今天新增成长值

		// 昨天
        Date yesterday = DateTimeUtil.plusDays(now, -1);
		queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_PLUS);
		queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(yesterday));
		queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(yesterday));
		int yesterdayIntegralPlusCount = integralDtlService.sumIntegral(queryObject);
		resMap.put("yesterdayIntegralPlusCount", yesterdayIntegralPlusCount);	// 昨天新增金币数
		queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_MINUS);
		int yesterdayIntegralMinusCount = integralDtlService.sumIntegral(queryObject);	// 昨天消耗金币数
		resMap.put("yesterdayIntegralMinusCount", yesterdayIntegralMinusCount);	// 昨天消耗金币数
		resMap.put("yesterdayIntegralNetPlusCount", yesterdayIntegralPlusCount - yesterdayIntegralMinusCount);	// 昨天净增金币数
		resMap.put("yesterdayGrowthCount", growthDtlService.sumGValue(queryObject));	// 昨天新增成长值

		// 本月
		queryObject = new QueryObject();
		queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_PLUS);
		queryObject.addQuery("createDateAfter", DateTimeUtil.getFirstdayOfMonth(now));
		queryObject.addQuery("createDateBefore", DateTimeUtil.getLastdayOfMonth(now));
		int monthIntegralPlusCount = integralDtlService.sumIntegral(queryObject);
		resMap.put("monthIntegralPlusCount", monthIntegralPlusCount);	//本月新增金币数
		queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_MINUS);
		int monthIntegralMinusCount = integralDtlService.sumIntegral(queryObject);	// 本月消耗金币数
		resMap.put("monthIntegralMinusCount", monthIntegralMinusCount);	// 本月消耗金币数
		resMap.put("monthIntegralNetPlusCount", monthIntegralPlusCount - monthIntegralMinusCount);	// 本月净增金币数
		resMap.put("monthGrowthCount", growthDtlService.sumGValue(queryObject));	// 本月新增成长值

        resMap.put("beginDate1", DateTimeUtil.getFirstdayOfMonth(now));
        resMap.put("endDate1", DateTimeUtil.getLastdayOfMonth(now));
        resMap.put("beginDate2", DateTimeUtil.getFirstdayOfMonth(DateTimeUtil.plusMonths(now, -1)));
        resMap.put("endDate2", DateTimeUtil.getLastdayOfMonth(DateTimeUtil.plusMonths(now, -1)));
		return new ModelAndView("/stat/integral", resMap);
	}

	/**
	 * 统计金币
     */
	@ResponseBody
	@RequestMapping(value = "/stat/integralStat.shtml")
	public String integralStat() {
		// 1：新增金币  2：消耗金币  3：净增金币  4：新增成长值
		int type = getParaToInt("type", 1);
		// 默认取本月数据
		String beginDate1 = getPara("beginDate1", DateTimeUtil.formatDate(DateTimeUtil.getFirstdayOfMonth(new Date()), "yyyy-MM-dd"));
		String endDate1 = getPara("endDate1", DateTimeUtil.formatDate(DateTimeUtil.getLastdayOfMonth(new Date()), "yyyy-MM-dd"));
		String beginDate2 = getPara("beginDate2");
		String endDate2 = getPara("endDate2");

        if(DateTimeUtil.parseDate(beginDate1, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate1, "yyyy-MM-dd"))){
            throw new BizException("开始时间不能大于结束时间");
        }
        if(StrKit.notBlank(beginDate2) && StrKit.notBlank(endDate2) && DateTimeUtil.parseDate(beginDate2, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate2, "yyyy-MM-dd"))){
            throw new BizException("第二时间段开始时间不能大于结束时间");
        }

		List<Map<String, Object>> serieList = new ArrayList<Map<String, Object>>();
		serieList.add(getIntegralCountSerie(beginDate1, endDate1, type));
		if(StrKit.notBlank(beginDate2) && StrKit.notBlank(endDate2)){
			serieList.add(getIntegralCountSerie(beginDate2, endDate2, type));
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("serieList", serieList);

		return json(data);
	}

	/**
	 * 计算金币数
	 */
	private Map<String, Object> getIntegralCountSerie(String beginDate, String endDate, int type){
		Map<String, Object> serie = new HashMap<String, Object>();
		serie.put("type", "line");
		//serie.put("name", beginDate1 + "至" + endDate1);

		List<String[]> dataList = new ArrayList<String[]>();
		QueryObject queryObject;
		int y = 0;
		List<Date> listDate = DateTimeUtil.segmentDateWithDay(DateTimeUtil.parseDate(beginDate), DateTimeUtil.parseDate(endDate), 8);
		for(Date date : listDate){
			if(type == 1){
				// 新增金币
				queryObject = new QueryObject();
				queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_PLUS);
				queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(date));
				queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(date));
				y = integralDtlService.sumIntegral(queryObject);
			}else if(type == 2){
				// 消耗金币
				queryObject = new QueryObject();
				queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_MINUS);
				queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(date));
				queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(date));
				y = integralDtlService.sumIntegral(queryObject);
			}else if(type == 3){
				//  净增金币
				queryObject = new QueryObject();
				queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_PLUS);
				queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(date));
				queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(date));
				int plus = integralDtlService.sumIntegral(queryObject);
				queryObject.addQuery("tallyMode", Const.INTEGRAL_TALLY_MODE_MINUS);
				int minus = integralDtlService.sumIntegral(queryObject);
				y = plus - minus;
			}else if(type == 4){
				//  新增成长值
				queryObject = new QueryObject();
				queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(date));
				queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(date));
				y = growthDtlService.sumGValue(queryObject);
			}
			dataList.add(new String[]{DateTimeUtil.formatDate(date, "yyyy-MM-dd"), String.valueOf(y)});
		}
		serie.put("data", dataList);
		return serie;
	}


	/**
	 * 会员属性统计
	 */
	@RequestMapping(value = "/stat/memberAttrIndex.shtml")
	public ModelAndView memberAttrIndex() {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Date now = new Date();
		resMap.put("beginDate", DateTimeUtil.getFirstdayOfMonth(DateTimeUtil.plusYears(now, -1)));
		resMap.put("endDate", DateTimeUtil.getLastdayOfMonth(now));
		return new ModelAndView("/stat/member_attr", resMap);
	}

	/**
	 * 会员性别分析
     */
	@ResponseBody
	@RequestMapping(value = "/stat/memberGenderStat.shtml")
	public String memberGenderStat() {
		// 1：注册时间  2：最后登录时间
		int timeType = getParaToInt("timeType", 1);
		// 默认取本月数据
        String beginDate = getPara("beginDate", DateTimeUtil.formatDate(DateTimeUtil.getFirstdayOfMonth(new Date()), "yyyy-MM-dd"));
        String endDate = getPara("endDate", DateTimeUtil.formatDate(DateTimeUtil.getLastdayOfMonth(new Date()), "yyyy-MM-dd"));
        if(DateTimeUtil.parseDate(beginDate, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate, "yyyy-MM-dd"))){
            throw new BizException("开始时间不能大于结束时间");
        }

		QueryObject queryObject = new QueryObject();
		if(timeType == 1){
			queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(DateTimeUtil.parseDate(beginDate)));
			queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(DateTimeUtil.parseDate(endDate)));
		}else{
			queryObject.addQuery("loginDateAfter", beginDate + " 00:00:00");
			queryObject.addQuery("loginDateBefore", endDate + " 23:59:59");
		}

		Map<String, Object> data = new HashMap<String, Object>();
		queryObject.addQuery("sexType", Const.SET_TYPE_MAN);
		data.put("manCount", memberInfoService.count(queryObject));
		queryObject.addQuery("sexType", Const.SET_TYPE_WOMAN);
		data.put("womanCount", memberInfoService.count(queryObject));
		queryObject.addQuery("sexType", Const.SET_TYPE_UNKNOW);
		data.put("otherCount", memberInfoService.count(queryObject));

		return json(data);
	}

	/**
	 * 会员地区分析
     */
	@ResponseBody
	@RequestMapping(value = "/stat/memberAreaStat.shtml")
	public Map<String, Object> memberAreaStat() {
		// 1：注册时间  2：最后登录时间
		int timeType = getParaToInt("timeType", 1);
		// 默认取本月数据
        String beginDate = getPara("beginDate", DateTimeUtil.formatDate(DateTimeUtil.getFirstdayOfMonth(new Date()), "yyyy-MM-dd"));
        String endDate = getPara("endDate", DateTimeUtil.formatDate(DateTimeUtil.getLastdayOfMonth(new Date()), "yyyy-MM-dd"));
        if(DateTimeUtil.parseDate(beginDate, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate, "yyyy-MM-dd"))){
            throw new BizException("开始时间不能大于结束时间");
        }

		QueryObject queryObject = new QueryObject();
		if(timeType == 1){
			queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(DateTimeUtil.parseDate(beginDate)));
			queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(DateTimeUtil.parseDate(endDate)));
		}else{
			queryObject.addQuery("loginDateAfter", beginDate + " 00:00:00");
			queryObject.addQuery("loginDateBefore", endDate + " 23:59:59");
		}
		List<MemberInfoCustomAreaCount> areaCountList = memberInfoService.selectByExampleGroupByProvince(queryObject);

		List<Map<String, Object>> serieDataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for(MemberInfoCustomAreaCount info : areaCountList){
			map = new HashMap<String, Object>();
			map.put("name", AreaUtil.getShortProvinceName(info.getAreaName()));
			map.put("value", info.getCount());
			serieDataList.add(map);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("serieDataList", serieDataList);
		return data;
	}

	/**
	 * 会员地区数量列表
	 */
	@ResponseBody
	@RequestMapping(value = "/stat/memberAreaList.shtml")
	public Map<String, Object> memberAreaList() {
		// 1：注册时间  2：最后登录时间
		int timeType = getParaToInt("timeType", 1);
		// 1：按省份  2：按城市
		int areaType = getParaToInt("areaType", 1);
		// 默认取本月数据
        String beginDate = getPara("beginDate", DateTimeUtil.formatDate(DateTimeUtil.getFirstdayOfMonth(new Date()), "yyyy-MM-dd"));
        String endDate = getPara("endDate", DateTimeUtil.formatDate(DateTimeUtil.getLastdayOfMonth(new Date()), "yyyy-MM-dd"));
        if(DateTimeUtil.parseDate(beginDate, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate, "yyyy-MM-dd"))){
            throw new BizException("开始时间不能大于结束时间");
        }

		QueryObject queryObject = new QueryObject();
        if(timeType == 1){
            queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(DateTimeUtil.parseDate(beginDate)));
            queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(DateTimeUtil.parseDate(endDate)));
        }else{
            queryObject.addQuery("loginDateAfter", beginDate + " 00:00:00");
            queryObject.addQuery("loginDateBefore", endDate + " 23:59:59");
        }
		int allCount = memberInfoService.count(queryObject);	//会员总数

		queryObject.addSort("count", QueryObject.SORT_DESC);
		queryObject.setLimitSize(100);
		List<MemberInfoCustomAreaCount> areaCountList;
		if(areaType == 2){
			areaCountList = memberInfoService.selectByExampleGroupByCity(queryObject);
		}else{
			areaCountList = memberInfoService.selectByExampleGroupByProvince(queryObject);
		}

		for(MemberInfoCustomAreaCount info : areaCountList){
			NumberFormat nt = NumberFormat.getPercentInstance();
			nt.setMinimumFractionDigits(2);
			info.setPercent(nt.format(new Float(info.getCount()) / allCount));
		}

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows", areaCountList);
		resMap.put("Total", areaCountList.size());
		return resMap;
	}

	/**
	 * 会员地区-导出
	 */
	@RequestMapping(value = "/stat/memberAreaExport.shtml")
	public void export(HttpServletResponse response) throws Exception {
		// 1：注册时间  2：最后登录时间
		int timeType = getParaToInt("timeType", 1);
		// 1：按省份  2：按城市
		int areaType = getParaToInt("areaType", 1);
		// 默认取本月数据
        String beginDate = getPara("beginDate", DateTimeUtil.formatDate(DateTimeUtil.getFirstdayOfMonth(new Date()), "yyyy-MM-dd"));
        String endDate = getPara("endDate", DateTimeUtil.formatDate(DateTimeUtil.getLastdayOfMonth(new Date()), "yyyy-MM-dd"));
        if(DateTimeUtil.parseDate(beginDate, "yyyy-MM-dd").after(DateTimeUtil.parseDate(endDate, "yyyy-MM-dd"))){
            throw new BizException("开始时间不能大于结束时间");
        }

		QueryObject queryObject = new QueryObject();
        if(timeType == 1){
            queryObject.addQuery("createDateAfter", DateTimeUtil.getStartTimeOfDate(DateTimeUtil.parseDate(beginDate)));
            queryObject.addQuery("createDateBefore", DateTimeUtil.getEndTimeOfDate(DateTimeUtil.parseDate(endDate)));
        }else{
            queryObject.addQuery("loginDateAfter", beginDate + " 00:00:00");
            queryObject.addQuery("loginDateBefore", endDate + " 23:59:59");
        }
		int allCount = memberInfoService.count(queryObject);	//会员总数

		queryObject.addSort("count", QueryObject.SORT_DESC);
		queryObject.setLimitSize(100);
		List<MemberInfoCustomAreaCount> areaCountList;
		if(areaType == 2){
			areaCountList = memberInfoService.selectByExampleGroupByCity(queryObject);
		}else{
			areaCountList = memberInfoService.selectByExampleGroupByProvince(queryObject);
		}

		String[] titles = {"地区", "会员数量", "总数占比"};
		ExcelBean excelBean = new ExcelBean("会员地区分布.xls", "会员地区分布", titles);
		List<String[]> datas = new ArrayList<String[]>();
		for (MemberInfoCustomAreaCount info : areaCountList) {
			NumberFormat nt = NumberFormat.getPercentInstance();
			nt.setMinimumFractionDigits(2);
			String[] data = {
					info.getAreaName(),
					info.getCount() + "",
					nt.format(new Float(info.getCount()) / allCount)
			};
			datas.add(data);
		}

		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean, response);
	}


	/**
	 * 会员消费数据
	 */
	@RequestMapping(value = "/stat/memberPayData.shtml")
	public ModelAndView memberPayData(HttpServletRequest request) {
		String dateBegin =request.getParameter("statDate");
		String dateEnd =request.getParameter("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isEmpty(dateBegin) || StringUtils.isEmpty(dateEnd)){
			return new ModelAndView("/stat/member_pay_data", new HashMap<String,Object>());
		}

		// 统计日期内用户消费数据
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("status", "1");
		queryObject.addQuery("payDateGreaterThanOrEqualTo", dateBegin + " 00:00:00");
		queryObject.addQuery("payDateLessThanOrEqualTo", dateEnd + " 23:23:59");
		List<MemberPayDataVo> list = combineOrderService.statMemberPayData(queryObject);

		// 统计日期内注册用户
		MemberInfoCustomExample example = new MemberInfoCustomExample();
		MemberInfoCustomExample.MemberInfoCustomCriteria criteria = example.createCriteria();
		try {
			criteria.andCreateDateBetween(sdf.parse(dateBegin + " 00:00:00"), sdf.parse(dateEnd + " 23:23:59"));
		}catch (Exception e){
			return new ModelAndView("/stat/member_pay_data", new HashMap<String,Object>());
		}
		Set<Integer> currentMonthMemberIdList = memberInfoService.listIdOfSet(example);


		//统计日期内SVIP用户消费数据
		HashMap<String, Object> paramMap = new HashMap<>(2);
		paramMap.put("payDateGreaterThanOrEqualTo", dateBegin + " 00:00:00");
		paramMap.put("payDateLessThanOrEqualTo", dateEnd + " 23:23:59");
		List<MemberPayDataVo> sviplist = combineOrderService.statSvipMemberPayData(paramMap);

		//统计日期内注册用户消费金额
		int regMemberCountOfPayAmountLessThen100 = 0;	// 消费金额 < 100
		int regMemberCountOfPayAmountMoreThen100AndLessThen200 = 0;	//100 <= 消费金额 < 200
		int regMemberCountOfPayAmountMoreThen200AndLessThen300 = 0;	//200 <= 消费金额 < 300
		int regMemberCountOfPayAmountMoreThen300AndLessThen400 = 0;	//300 <= 消费金额 < 400
		int regMemberCountOfPayAmountMoreThen400AndLessThen800 = 0;	//400 <= 消费金额 < 800
		int regMemberCountOfPayAmountMoreThen800AndLessThen1600 = 0;	//800 <= 消费金额 < 1600
		int regMemberCountOfPayAmountMoreOrEquals1600 = 0;	//消费金额 >= 1600

		int regMemberCountOfOrderCountEquals1 = 0;	//消费次数 = 1
		int regMemberCountOfOrderCountEquals2 = 0;	//消费次数 = 2
		int regMemberCountOfOrderCountEquals3 = 0;	//消费次数 = 3
		int regMemberCountOfOrderCountMoreOrEquals4 = 0;	//消费次数 >= 4

		//历史用户消费金额
		int hisMemberCountOfPayAmountLessThen100 = 0;	// 消费金额 < 100
		int hisMemberCountOfPayAmountMoreThen100AndLessThen200 = 0;	//100 <= 消费金额 < 200
		int hisMemberCountOfPayAmountMoreThen200AndLessThen300 = 0;	//200 <= 消费金额 < 300
		int hisMemberCountOfPayAmountMoreThen300AndLessThen400 = 0;	//300 <= 消费金额 < 400
		int hisMemberCountOfPayAmountMoreThen400AndLessThen800 = 0;	//400 <= 消费金额 < 800
		int hisMemberCountOfPayAmountMoreThen800AndLessThen1600 = 0;	//800 <= 消费金额 < 1600
		int hisMemberCountOfPayAmountMoreOrEquals1600 = 0;	//消费金额 >= 1600

		int hisMemberCountOfOrderCountEquals1 = 0;	//消费次数 = 1
		int hisMemberCountOfOrderCountEquals2 = 0;	//消费次数 = 2
		int hisMemberCountOfOrderCountEquals3 = 0;	//消费次数 = 3
		int hisMemberCountOfOrderCountMoreOrEquals4 = 0;	//消费次数 >= 4

		//SVIP用户消费金额
		int svipMemberCountOfPayAmountLessThen100 = 0;	// 消费金额 < 100
		int svipMemberCountOfPayAmountMoreThen100AndLessThen200 = 0;	//100 <= 消费金额 < 200
		int svipMemberCountOfPayAmountMoreThen200AndLessThen300 = 0;	//200 <= 消费金额 < 300
		int svipMemberCountOfPayAmountMoreThen300AndLessThen400 = 0;	//300 <= 消费金额 < 400
		int svipMemberCountOfPayAmountMoreThen400AndLessThen800 = 0;	//400 <= 消费金额 < 800
		int svipMemberCountOfPayAmountMoreThen800AndLessThen1600 = 0;	//800 <= 消费金额 < 1600
		int svipMemberCountOfPayAmountMoreOrEquals1600 = 0;	//消费金额 >= 1600

		int svipMemberCountOfOrderCountEquals1 = 0;	//消费次数 = 1
		int svipMemberCountOfOrderCountEquals2 = 0;	//消费次数 = 2
		int svipMemberCountOfOrderCountEquals3 = 0;	//消费次数 = 3
		int svipMemberCountOfOrderCountMoreOrEquals4 = 0;	//消费次数 >= 4

		int memberId;
		long orderCount;
		float totalPayAmount;
		for(MemberPayDataVo item:list){
			memberId = item.getMemberId();
			orderCount = item.getOrderCount();
			totalPayAmount = item.getTotalPayAmount();

			if(currentMonthMemberIdList.contains(memberId)){
				if(totalPayAmount < 100){
					regMemberCountOfPayAmountLessThen100 ++;
				}else if(totalPayAmount < 200){
					regMemberCountOfPayAmountMoreThen100AndLessThen200 ++;
				}else if(totalPayAmount < 300){
					regMemberCountOfPayAmountMoreThen200AndLessThen300 ++;
				}else if(totalPayAmount < 400){
					regMemberCountOfPayAmountMoreThen300AndLessThen400 ++;
				}else if(totalPayAmount < 800){
					regMemberCountOfPayAmountMoreThen400AndLessThen800 ++;
				}else if(totalPayAmount < 1600){
					regMemberCountOfPayAmountMoreThen800AndLessThen1600 ++;
				}else{
					regMemberCountOfPayAmountMoreOrEquals1600 ++;
				}

				if(orderCount == 1){
					regMemberCountOfOrderCountEquals1 ++;
				}else if(orderCount == 2){
					regMemberCountOfOrderCountEquals2 ++;
				}else if(orderCount == 3){
					regMemberCountOfOrderCountEquals3 ++;
				}else if(orderCount >= 4){
					regMemberCountOfOrderCountMoreOrEquals4 ++;
				}
			}else{
				if(totalPayAmount < 100){
					hisMemberCountOfPayAmountLessThen100 ++;
				}else if(totalPayAmount < 200){
					hisMemberCountOfPayAmountMoreThen100AndLessThen200 ++;
				}else if(totalPayAmount < 300){
					hisMemberCountOfPayAmountMoreThen200AndLessThen300 ++;
				}else if(totalPayAmount < 400){
					hisMemberCountOfPayAmountMoreThen300AndLessThen400 ++;
				}else if(totalPayAmount < 800){
					hisMemberCountOfPayAmountMoreThen400AndLessThen800 ++;
				}else if(totalPayAmount < 1600){
					hisMemberCountOfPayAmountMoreThen800AndLessThen1600 ++;
				}else{
					hisMemberCountOfPayAmountMoreOrEquals1600 ++;
				}

				if(orderCount == 1){
					hisMemberCountOfOrderCountEquals1 ++;
				}else if(orderCount == 2){
					hisMemberCountOfOrderCountEquals2 ++;
				}else if(orderCount == 3){
					hisMemberCountOfOrderCountEquals3 ++;
				}else if(orderCount >= 4){
					hisMemberCountOfOrderCountMoreOrEquals4 ++;
				}
			}
		}

		for (MemberPayDataVo svipMember : sviplist) {
			if(svipMember.getMemberId()>0){
				orderCount = svipMember.getOrderCount();
				totalPayAmount = svipMember.getTotalPayAmount();
				if(totalPayAmount < 100){
					svipMemberCountOfPayAmountLessThen100 ++;
				}else if(totalPayAmount < 200){
					svipMemberCountOfPayAmountMoreThen100AndLessThen200 ++;
				}else if(totalPayAmount < 300){
					svipMemberCountOfPayAmountMoreThen200AndLessThen300 ++;
				}else if(totalPayAmount < 400){
					svipMemberCountOfPayAmountMoreThen300AndLessThen400 ++;
				}else if(totalPayAmount < 800){
					svipMemberCountOfPayAmountMoreThen400AndLessThen800 ++;
				}else if(totalPayAmount < 1600){
					svipMemberCountOfPayAmountMoreThen800AndLessThen1600 ++;
				}else{
					svipMemberCountOfPayAmountMoreOrEquals1600 ++;
				}

				if(orderCount == 1){
					svipMemberCountOfOrderCountEquals1 ++;
				}else if(orderCount == 2){
					svipMemberCountOfOrderCountEquals2 ++;
				}else if(orderCount == 3){
					svipMemberCountOfOrderCountEquals3 ++;
				}else if(orderCount >= 4){
					svipMemberCountOfOrderCountMoreOrEquals4 ++;
				}
			}
		}

		Map<String,Object> data = new HashMap<String,Object>();
		//data.put("list", list);
		//data.put("currentMonthMemberIdList", currentMonthMemberIdList);
		//data.put("svipMemberIdList", svipMemberIdList);

		data.put("regMemberCountOfPayAmountLessThen100", regMemberCountOfPayAmountLessThen100);
		data.put("regMemberCountOfPayAmountMoreThen100AndLessThen200", regMemberCountOfPayAmountMoreThen100AndLessThen200);
		data.put("regMemberCountOfPayAmountMoreThen200AndLessThen300", regMemberCountOfPayAmountMoreThen200AndLessThen300);
		data.put("regMemberCountOfPayAmountMoreThen300AndLessThen400", regMemberCountOfPayAmountMoreThen300AndLessThen400);
		data.put("regMemberCountOfPayAmountMoreThen400AndLessThen800", regMemberCountOfPayAmountMoreThen400AndLessThen800);
		data.put("regMemberCountOfPayAmountMoreThen800AndLessThen1600", regMemberCountOfPayAmountMoreThen800AndLessThen1600);
		data.put("regMemberCountOfPayAmountMoreOrEquals1600", regMemberCountOfPayAmountMoreOrEquals1600);
		data.put("regMemberCountOfOrderCountEquals1", regMemberCountOfOrderCountEquals1);
		data.put("regMemberCountOfOrderCountEquals2", regMemberCountOfOrderCountEquals2);
		data.put("regMemberCountOfOrderCountEquals3", regMemberCountOfOrderCountEquals3);
		data.put("regMemberCountOfOrderCountMoreOrEquals4", regMemberCountOfOrderCountMoreOrEquals4);

		data.put("hisMemberCountOfPayAmountLessThen100", hisMemberCountOfPayAmountLessThen100);
		data.put("hisMemberCountOfPayAmountMoreThen100AndLessThen200", hisMemberCountOfPayAmountMoreThen100AndLessThen200);
		data.put("hisMemberCountOfPayAmountMoreThen200AndLessThen300", hisMemberCountOfPayAmountMoreThen200AndLessThen300);
		data.put("hisMemberCountOfPayAmountMoreThen300AndLessThen400", hisMemberCountOfPayAmountMoreThen300AndLessThen400);
		data.put("hisMemberCountOfPayAmountMoreThen400AndLessThen800", hisMemberCountOfPayAmountMoreThen400AndLessThen800);
		data.put("hisMemberCountOfPayAmountMoreThen800AndLessThen1600", hisMemberCountOfPayAmountMoreThen800AndLessThen1600);
		data.put("hisMemberCountOfPayAmountMoreOrEquals1600", hisMemberCountOfPayAmountMoreOrEquals1600);
		data.put("hisMemberCountOfOrderCountEquals1", hisMemberCountOfOrderCountEquals1);
		data.put("hisMemberCountOfOrderCountEquals2", hisMemberCountOfOrderCountEquals2);
		data.put("hisMemberCountOfOrderCountEquals3", hisMemberCountOfOrderCountEquals3);
		data.put("hisMemberCountOfOrderCountMoreOrEquals4", hisMemberCountOfOrderCountMoreOrEquals4);

		data.put("svipMemberCountOfPayAmountLessThen100", svipMemberCountOfPayAmountLessThen100);
		data.put("svipMemberCountOfPayAmountMoreThen100AndLessThen200", svipMemberCountOfPayAmountMoreThen100AndLessThen200);
		data.put("svipMemberCountOfPayAmountMoreThen200AndLessThen300", svipMemberCountOfPayAmountMoreThen200AndLessThen300);
		data.put("svipMemberCountOfPayAmountMoreThen300AndLessThen400", svipMemberCountOfPayAmountMoreThen300AndLessThen400);
		data.put("svipMemberCountOfPayAmountMoreThen400AndLessThen800", svipMemberCountOfPayAmountMoreThen400AndLessThen800);
		data.put("svipMemberCountOfPayAmountMoreThen800AndLessThen1600", svipMemberCountOfPayAmountMoreThen800AndLessThen1600);
		data.put("svipMemberCountOfPayAmountMoreOrEquals1600", svipMemberCountOfPayAmountMoreOrEquals1600);
		data.put("svipMemberCountOfOrderCountEquals1", svipMemberCountOfOrderCountEquals1);
		data.put("svipMemberCountOfOrderCountEquals2", svipMemberCountOfOrderCountEquals2);
		data.put("svipMemberCountOfOrderCountEquals3", svipMemberCountOfOrderCountEquals3);
		data.put("svipMemberCountOfOrderCountMoreOrEquals4", svipMemberCountOfOrderCountMoreOrEquals4);

		data.put("statDate", dateBegin);
		data.put("endDate", dateEnd);
		return new ModelAndView("/stat/member_pay_data", data);
	}



}

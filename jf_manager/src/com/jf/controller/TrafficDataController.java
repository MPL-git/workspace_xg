package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.ActivityAreaPvStatisticalCustom;
import com.jf.entity.ActivityAreaPvStatisticalCustomExample;
import com.jf.entity.ActivityAreaPvStatisticalCustomExample.ActivityAreaPvStatisticalCustomCriteria;
import com.jf.entity.ActivityPvStatisticalCustom;
import com.jf.entity.AdInfo;
import com.jf.entity.AdPvStatisticalCustom;
import com.jf.entity.AdPvStatisticalCustomExample;
import com.jf.entity.BrandteamTypeadInfo;
import com.jf.entity.MchtPvStatisticalCustom;
import com.jf.entity.MchtPvStatisticalCustomExample;
import com.jf.entity.MchtPvStatisticalCustomExample.MchtPvStatisticalCustomCriteria;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.WetaoPageAdInfo;
import com.jf.service.ActivityAreaPvStatisticalService;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityPvStatisticalService;
import com.jf.service.AdInfoService;
import com.jf.service.AdPvStatisticalService;
import com.jf.service.BrandteamTypeadInfoService;
import com.jf.service.MchtPvStatisticalService;
import com.jf.service.ProductTypeService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.WetaoPageAdInfoService;
import com.jf.vo.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("serial")
@Controller
public class TrafficDataController extends BaseController{
	
	@Autowired
	private AdPvStatisticalService adPvStatisticalService;	
	@Autowired
	private ActivityPvStatisticalService activityPvStatisticalService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ActivityAreaPvStatisticalService activityAreaPvStatisticalService;
	@Autowired
	private ActivityAreaService activityAreaService;
	@Autowired
	private SysStaffProductTypeService sysStaffProductTypeService;
	@Autowired
	private MchtPvStatisticalService mchtPvStatisticalService;
	@Autowired
	private BrandteamTypeadInfoService brandteamTypeadInfoService;
	@Autowired
	private WetaoPageAdInfoService wetaoPageAdInfoService;
	@Autowired
	private AdInfoService adInfoService;



	
	//商家流量统计
	@RequestMapping(value = "/trafficData/merchantTraffic.shtml")
	public ModelAndView merchantTraffic(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();	
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		map.put("productTypeList", productTypeList);

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		map.put("beginDate", format.format(DateUtil.getDateAfter(date, -1)));
		map.put("endDate", format.format(DateUtil.getDateAfter(date, -1)));
		return new ModelAndView("/dataCenter/trafficData/merchantTraffic",map);
	}

	//商家流量统计列表
	@ResponseBody
	@RequestMapping(value = "/trafficData/merchantTrafficList.shtml")
	public Map<String, Object> getMerchantTrafficList(HttpServletRequest request,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		MchtPvStatisticalCustomExample mchtPvStatisticalCustomExample = new MchtPvStatisticalCustomExample();
		MchtPvStatisticalCustomCriteria criteria = mchtPvStatisticalCustomExample.createCriteria();
		mchtPvStatisticalCustomExample.setLimitSize(page.getLimitSize());
		mchtPvStatisticalCustomExample.setLimitStart(page.getLimitStart());
		criteria.andDelFlagEqualTo("0");
		mchtPvStatisticalCustomExample.setOrderByClause(" a.statistical_date asc, a.total_pv_count desc");
		//商家序号
		if (StringUtils.isNotBlank(request.getParameter("mchtCode"))){
			criteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
		}
		//商家名称
		if (StringUtils.isNotBlank(request.getParameter("shortName"))) {
			criteria.andShortNameEqualTo(request.getParameter("shortName"));
		}
		//主营类目
		if (StringUtils.isNotBlank(request.getParameter("productTypeId"))) {
			criteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
		}
		//统计日期
		if (StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {
			criteria.andStatisticalDateGreaterThanOrEqualTo(request.getParameter("createDateBegin"));
		}
		if (StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
			criteria.andStatisticalDateLessThanOrEqualTo(request.getParameter("createDateEnd"));
		}
		
		Integer totalCount = mchtPvStatisticalService.countCustomByExample(mchtPvStatisticalCustomExample);
		List<MchtPvStatisticalCustom> list = mchtPvStatisticalService.selectMchtPvStatisticalCustomByExample(mchtPvStatisticalCustomExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	//每日特卖统计
	@RequestMapping(value = "/trafficData/beOnSale.shtml")
	public ModelAndView beOnSale(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		map.put("productTypeList", productTypeList); //1级类目
		
		//历史页面分析详情跳转到特卖
		String status = request.getParameter("status");
		map.put("activityId", request.getParameter("activityId"));
		map.put("status", status);
		if("1".equals(status) ) {
			map.put("beginDate", request.getParameter("statisticsBeginDate"));
			map.put("endDate", request.getParameter("statisticsEndDate"));
		}else {
			Date date = new Date();
			map.put("beginDate", DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd"));
			map.put("endDate", DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd"));
		}
		return new ModelAndView("/dataCenter/trafficData/beOnSale",map);
	}
	
	//每日特卖统计列表
	@ResponseBody
	@RequestMapping(value = "/trafficData/beOnSaleList.shtml")
	public Map<String, Object> getBeOnSaleList(HttpServletRequest request,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("limitStart",page.getLimitStart());
		paraMap.put("limitSize",page.getLimitSize());

		//活动状态
		String activityStatus = request.getParameter("activityStatus");
		if (!StringUtil.isEmpty(activityStatus) ) {
			if("1".equals(activityStatus) ) { //预热中
				paraMap.put("activityStatus"," EXISTS(select b.id from bu_activity b, bu_activity_area ba where b.activity_area_id = ba.id and b.del_flag = '0' and ba.del_flag = '0' and ba.preheat_time <= now() and ba.activity_begin_time > now() and b.id = a.activity_id)");
			}else if("2".equals(activityStatus) ) { //活动中
				paraMap.put("activityStatus"," EXISTS(select b.id from bu_activity b, bu_activity_area ba where b.activity_area_id = ba.id and b.del_flag = '0' and ba.del_flag = '0' and ba.activity_begin_time <= now() and ba.activity_end_time >= now() and b.id = a.activity_id)");
			}else if("3".equals(activityStatus)) { //已结束
				paraMap.put("activityStatus"," EXISTS(select b.id from bu_activity b, bu_activity_area ba where b.activity_area_id = ba.id and b.del_flag = '0' and ba.del_flag = '0' and ba.activity_end_time < now() and b.id = a.activity_id)");
			}
		}
		//特卖ID
		if (StringUtils.isNotBlank(request.getParameter("activityId"))) {
			paraMap.put("activityId",request.getParameter("activityId"));
		}
		//特卖名称
		if (StringUtils.isNotBlank(request.getParameter("name"))) {
			paraMap.put("name"," EXISTS(select b.id from bu_activity b where b.del_flag='0' and b.id = a.activity_id and b.name like'%"+request.getParameter("name")+"%')");
		}
		//一级类目
		if (StringUtils.isNotBlank(request.getParameter("productTypeName"))) {
			paraMap.put("productTypeName"," EXISTS(select b.id from bu_activity b where a.activity_id = b.id and b.del_flag = '0' and b.product_type_id = '"+request.getParameter("productTypeName")+"')");
		}
		//商家序号 或 商家名称
		if (!StringUtil.isEmpty(request.getParameter("mchtCode")) || !StringUtil.isEmpty(request.getParameter("mchtName")) ) {
			String mchtCode = request.getParameter("mchtCode");
			String mchtName = request.getParameter("mchtName");
			StringBuffer stringBuffer = new StringBuffer("EXISTS(select m.id from bu_activity b, bu_mcht_info m where b.mcht_id = m.id and b.del_flag = '0' and m.del_flag = '0' and b.id = a.activity_id");
			if(!StringUtil.isEmpty(mchtCode) ) {
				stringBuffer.append(" and m.mcht_code = '" + mchtCode + "'");
			}
			if(!StringUtil.isEmpty(mchtName) ) {
				stringBuffer.append(" and (m.company_name like concat('%','" + mchtName + "','%') or m.shop_name like concat('%','" + mchtName + "','%') )" );
			}
			stringBuffer.append(")");
			paraMap.put("mchtCodeOrMchtName",stringBuffer);
		}
		//统计日期
		if (StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {
			paraMap.put("createDateBegin",request.getParameter("createDateBegin") + " 00:00:00");
		}
		if (StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
			paraMap.put("createDateEnd",request.getParameter("createDateEnd") + " 23:59:59");
		}

		Integer totalCount = activityPvStatisticalService.countActivityPvStatistical(paraMap);
		List<ActivityPvStatisticalCustom> list = activityPvStatisticalService.selectActivityPvStatistical(paraMap);
		ArrayList<Integer> activityIds = new ArrayList<>();
		for (ActivityPvStatisticalCustom activityPvStatisticalCustom : list) {
			activityIds.add(activityPvStatisticalCustom.getActivityId());
		}
		paraMap.put("activityIds",activityIds);
		List<HashMap<String,Object>> resList = activityPvStatisticalService.selectBrowseNumberByActivityId(paraMap);
		for (HashMap<String, Object> stringObjectHashMap : resList) {
			for (ActivityPvStatisticalCustom activityPvStatisticalCustom1 : list) {
				if(Objects.equals(activityPvStatisticalCustom1.getActivityId(), stringObjectHashMap.get("valueId"))) {
					Number totalVisitorCountNumber = (Number) stringObjectHashMap.get("totalVisitorCount");
					Number totalVisitorCountTourist = (Number)stringObjectHashMap.get("totalVisitorCountTourist");
					activityPvStatisticalCustom1.setTotalVisitorCount(totalVisitorCountNumber.intValue());
					activityPvStatisticalCustom1.setTotalVisitorCountTourist(totalVisitorCountTourist.intValue());
					break;
				}
			}
		}
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}	
	
	//每日会场统计页面
	@RequestMapping(value = "/trafficData/hall.shtml")
	public ModelAndView hall(HttpServletRequest request) {
		//历史页面分析详情跳转到会场
		map.put("activityAreaId", request.getParameter("activityAreaId"));
		String status = request.getParameter("status");
		map.put("status", status);
		if("1".equals(status) ) {
			map.put("beginDate", request.getParameter("statisticsBeginDate"));
			map.put("endDate", request.getParameter("statisticsEndDate"));
		}else {
			Date date = new Date();
			map.put("beginDate", DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd"));
			map.put("endDate", DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd"));
		}
		return new ModelAndView("/dataCenter/trafficData/hall",map);
	}
	
	//每日会场统计列表
	@ResponseBody
	@RequestMapping(value = "/trafficData/hallList.shtml")
	public Map<String, Object> getHallList(HttpServletRequest request,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		ActivityAreaPvStatisticalCustomExample activityAreaPvStatisticalCustomExample = new ActivityAreaPvStatisticalCustomExample();
		ActivityAreaPvStatisticalCustomCriteria criteria = activityAreaPvStatisticalCustomExample.createCriteria();
		activityAreaPvStatisticalCustomExample.setLimitSize(page.getLimitSize());
		activityAreaPvStatisticalCustomExample.setLimitStart(page.getLimitStart());
		activityAreaPvStatisticalCustomExample.setOrderByClause(" a.statistical_date, (a.home_page_exposure_count+a.classify_page_exposure_count) desc, a.total_pv_count desc");
		criteria.andDelFlagEqualTo("0");
		//会场形式：会场  会场类型：1，2，3
		criteria.andSource();
		//会场类型
		if (StringUtils.isNotBlank(request.getParameter("type"))) {
			criteria.andTypeEqualTo(request.getParameter("type"));
		}
		//会场ID
		if (StringUtils.isNotBlank(request.getParameter("activityAreaId"))) {
			criteria.andActivityAreaIdEqualTo(Integer.valueOf(request.getParameter("activityAreaId")));
		}
		//会场名称
		if (StringUtils.isNotBlank(request.getParameter("name"))) {
			criteria.andNameEqualTo(request.getParameter("name"));
		}
		//统计日期
		if (StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {
			criteria.andStatisticalDateGreaterThanOrEqualTo(request.getParameter("createDateBegin"));
		}
		if (StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
			criteria.andStatisticalDateLessThanOrEqualTo(request.getParameter("createDateEnd"));
		}
		Integer totalCount = activityAreaPvStatisticalService.countCustomByExample(activityAreaPvStatisticalCustomExample);
		List<ActivityAreaPvStatisticalCustom> list = activityAreaPvStatisticalService.selectActivityAreaPvStatisticalCustomByExample(activityAreaPvStatisticalCustomExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}	
	
	//每日会场统计会场详情
	@ResponseBody
	@RequestMapping(value = "/trafficData/meetingDetail.shtml")
	public ModelAndView meetingDetail(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();	
		map.put("date", request.getParameter("date"));
		map.put("activityAreaId", request.getParameter("activityAreaId"));
		return new ModelAndView("/dataCenter/trafficData/meetingDetail",map);
	}	
	
	//每日会场统计会场详情列表
	@ResponseBody
	@RequestMapping(value = "/trafficData/meetingDetailList.shtml")
	public Map<String, Object> getMeetingDetailList(HttpServletRequest request,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String beginDate = request.getParameter("date")+" 00:00:00";
		String endDate = request.getParameter("date")+" 23:59:59";
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("activityAreaId", request.getParameter("activityAreaId"));
		String type = request.getParameter("type");
		if (StringUtils.isNotBlank(request.getParameter("productActivityId"))) {
			map.put("productActivityId",request.getParameter("productActivityId"));
		}
		if (StringUtils.isNotBlank(request.getParameter("productActivityName"))) {
			map.put("productActivityName",request.getParameter("productActivityName"));
		}
		int limitStart = (page.getPage()-1)*page.getLimitSize();
		int limitSize = page.getLimitSize();
		map.put("limitStart", limitStart);
		map.put("limitSize", limitSize);
		//商品
		if("1".equals(type) ) {
			Integer totalCount = activityAreaPvStatisticalService.countCustomSingle(map);
			List<Map<String, Object>> list = activityAreaPvStatisticalService.selectActivityAreaPvStatisticalSingle(map);
			map.put("Rows", list);
			map.put("Total", totalCount);
		}
		//特卖
		if("2".equals(type) ){
			Integer totalCount = activityAreaPvStatisticalService.countCustomDouble(map);
			List<Map<String, Object>> list = activityAreaPvStatisticalService.selectActivityAreaPvStatisticalDouble(map);
			map.put("Rows", list);
			map.put("Total", totalCount);
		}
		return map;
	}	
	
	//每日广告位统计页面
	@RequestMapping(value = "/trafficData/adsense.shtml")
	public ModelAndView adsense(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adTypeList", DataDicUtil.getStatusList("BU_AD_PV_STATISTICAL", "AD_TYPE"));
		map.put("adSourceTypeList", DataDicUtil.getStatusList("BU_AD_PV_STATISTICAL", "AD_SOURCE_TYPE"));
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		map.put("beginDate", format.format(DateUtil.getDateAfter(date, -1)));
		map.put("endDate", format.format(DateUtil.getDateAfter(date, -1)));
		return new ModelAndView("/dataCenter/trafficData/adsense",map);
	}
	
	//每日广告位统计列表
	@ResponseBody
	@RequestMapping(value = "/trafficData/adsenseList.shtml")
	public Map<String, Object> getAdsenseList(HttpServletRequest request,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		AdPvStatisticalCustomExample adPvStatisticalCustomExample = new AdPvStatisticalCustomExample();
		AdPvStatisticalCustomExample.AdPvStatisticalCustomCriteria adPvStatisticalCustomCriteria = adPvStatisticalCustomExample.createCriteria();
		adPvStatisticalCustomExample.setOrderByClause(" exposure_count desc");
		adPvStatisticalCustomExample.setLimitSize(page.getLimitSize());
		adPvStatisticalCustomExample.setLimitStart(page.getLimitStart());
		adPvStatisticalCustomCriteria.andDelFlagEqualTo("0");
		//广告类目
		String adType = request.getParameter("adType");
		String adSourceType = "";
		if (StringUtils.isNotBlank(adType) ) {
			adPvStatisticalCustomCriteria.andAdTypeEqualTo(adType);
			//广告类型
			if("2".equals(adType) || "3".equals(adType) || "4".equals(adType) || "5".equals(adType) || "6".equals(adType) ||
					"7".equals(adType) || "8".equals(adType) || "9".equals(adType) || "10".equals(adType) ) {
				adSourceType = request.getParameter("adSourceType");
			}
			if (!StringUtil.isEmpty(adSourceType) ) {
				adPvStatisticalCustomCriteria.andAdSourceTypeEqualTo(adSourceType);
			}
		}
		//广告状态
		if(!StringUtil.isEmpty(request.getParameter("adStatus")) ) {
			adPvStatisticalCustomCriteria.andAdStatusEqualTo(adSourceType, request.getParameter("adStatus"));
		}
		//广告ID
		if (StringUtils.isNotBlank(request.getParameter("adInfoId"))) {
			adPvStatisticalCustomCriteria.andAdInfoIdEqualTo(Integer.valueOf(request.getParameter("adInfoId")));
		}
		//统计日期
		if (StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {
			adPvStatisticalCustomCriteria.andStatisticalDateGreaterThanOrEqualTo(request.getParameter("createDateBegin"));
		}
		if (StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
			adPvStatisticalCustomCriteria.andStatisticalDateLessThanOrEqualTo(request.getParameter("createDateEnd"));
		}
		Integer totalCount = adPvStatisticalService.countCustomByExample(adPvStatisticalCustomExample);
		List<AdPvStatisticalCustom> list = adPvStatisticalService.selectAdPvStatisticalCustomByExample(adPvStatisticalCustomExample);
		for(AdPvStatisticalCustom adPvStatisticalCustom : list ) {
			if(!StringUtil.isEmpty(adPvStatisticalCustom.getAdSourceType()) && adPvStatisticalCustom.getAdInfoId() != null ) {
				if("1".equals(adPvStatisticalCustom.getAdSourceType()) ) {
					BrandteamTypeadInfo brandteamTypeadInfo = brandteamTypeadInfoService.selectByPrimaryKey(adPvStatisticalCustom.getAdInfoId());
					if(brandteamTypeadInfo != null && "1".equals(brandteamTypeadInfo.getStatus()) ) {
						adPvStatisticalCustom.setAdStatusDesc("上架");
					}else {
						adPvStatisticalCustom.setAdStatusDesc("下架");
					}
				}else if("2".equals(adPvStatisticalCustom.getAdSourceType()) ) {
					WetaoPageAdInfo wetaoPageAdInfo = wetaoPageAdInfoService.selectByPrimaryKey(adPvStatisticalCustom.getAdInfoId());
					if(wetaoPageAdInfo != null && "1".equals(wetaoPageAdInfo.getStatus()) ) {
						adPvStatisticalCustom.setAdStatusDesc("上架");
					}else {
						adPvStatisticalCustom.setAdStatusDesc("下架");
					}
				}else if("3".equals(adPvStatisticalCustom.getAdSourceType()) ) {
					AdInfo adInfo = adInfoService.selectByPrimaryKey(adPvStatisticalCustom.getAdInfoId());
					if(adInfo != null && "1".equals(adInfo.getStatus()) ) {
						adPvStatisticalCustom.setAdStatusDesc("上架");
					}else {
						adPvStatisticalCustom.setAdStatusDesc("下架");
					}
				}
			}else {
				adPvStatisticalCustom.setAdStatusDesc("下架");
			}
		}
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
}
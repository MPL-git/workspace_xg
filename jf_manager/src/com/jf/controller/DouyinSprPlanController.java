package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.DouyinSprChnl;
import com.jf.entity.DouyinSprChnlExample;
import com.jf.entity.DouyinSprPlan;
import com.jf.entity.DouyinSprPlanCustom;
import com.jf.entity.DouyinSprPlanCustomExample;
import com.jf.entity.StateCode;
import com.jf.service.DouyinSprChnlService;
import com.jf.service.DouyinSprPlanService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class DouyinSprPlanController extends BaseController {

	@Autowired
	private DouyinSprPlanService douyinSprPlanService;
	
	@Autowired
	private DouyinSprChnlService douyinSprChnlService;
	
	/**
	 * 
	 * @MethodName: douyinSprPlanManager
	 * @Description: (推广链接管理)
	 * @author Pengl
	 * @date 2019年5月15日 下午2:39:37
	 */
	@RequestMapping("/douyinSprPlan/douyinSprPlanManager.shtml")
	public ModelAndView douyinSprPlanManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/douyin/douyinSprPlan/getDouyinSprPlanList");
		DouyinSprChnlExample douyinSprChnlExample = new DouyinSprChnlExample();
		douyinSprChnlExample.createCriteria().andDelFlagEqualTo("0");
		List<DouyinSprChnl> douyinSprChnlList = douyinSprChnlService.selectByExample(douyinSprChnlExample);
		ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
		String mUrl = resource.getString("mUrl");
		m.addObject("mUrl", mUrl);
		m.addObject("douyinSprChnlList", douyinSprChnlList);
		m.addObject("sprPlanTypeList", DataDicUtil.getTableStatus("DOUYIN_SPR_PLAN", "SPR_PLAN_TYPE"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: getDouyinSprPlanList
	 * @Description: (推广链接管理)
	 * @author Pengl
	 * @date 2019年5月15日 下午2:56:17
	 */
	@ResponseBody
	@RequestMapping("/douyinSprPlan/getDouyinSprPlanList.shtml")
	public Map<String, Object> getDouyinSprPlanList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<DouyinSprPlanCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DouyinSprPlanCustomExample douyinSprPlanCustomExample = new DouyinSprPlanCustomExample();
			DouyinSprPlanCustomExample.DouyinSprPlanCustomCriteria douyinSprPlanCustomCriteria = douyinSprPlanCustomExample.createCriteria();
			douyinSprPlanCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("douyinSprPlanId")) ) {
				douyinSprPlanCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("douyinSprPlanId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("sprChnlId"))) {
				douyinSprPlanCustomCriteria.andSprChnlIdEqualTo(Integer.parseInt(request.getParameter("sprChnlId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("sprPlanSite"))) {
				douyinSprPlanCustomCriteria.andSprPlanSiteLike("%"+request.getParameter("sprPlanSite")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("sprPlanType"))) {
				douyinSprPlanCustomCriteria.andSprPlanTypeEqualTo(request.getParameter("sprPlanType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("linkValue"))) {
				douyinSprPlanCustomCriteria.andLinkValueEqualTo(request.getParameter("linkValue"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				douyinSprPlanCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				douyinSprPlanCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			douyinSprPlanCustomExample.setOrderByClause(" id desc");
			douyinSprPlanCustomExample.setLimitStart(page.getLimitStart());
			douyinSprPlanCustomExample.setLimitSize(page.getLimitSize());
			totalCount = douyinSprPlanService.countByCustomExample(douyinSprPlanCustomExample);
			dataList = douyinSprPlanService.selectByCustomExample(douyinSprPlanCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: addDouyinSprPlanManager
	 * @Description: (添加链接)
	 * @author Pengl
	 * @date 2019年5月15日 下午3:03:07
	 */
	@RequestMapping("/douyinSprPlan/addDouyinSprPlanManager.shtml")
	public ModelAndView addDouyinSprPlanManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/douyin/douyinSprPlan/addDouyinSprPlan");
		DouyinSprChnlExample douyinSprChnlExample = new DouyinSprChnlExample();
		douyinSprChnlExample.createCriteria().andDelFlagEqualTo("0");
		List<DouyinSprChnl> douyinSprChnlList = douyinSprChnlService.selectByExample(douyinSprChnlExample);
		m.addObject("douyinSprChnlList", douyinSprChnlList);
		m.addObject("sprPlanTypeList", DataDicUtil.getTableStatus("DOUYIN_SPR_PLAN", "SPR_PLAN_TYPE"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: validateLinkValue
	 * @Description: (校验页面ID)
	 * @author Pengl
	 * @date 2019年5月28日 下午10:19:49
	 */
	@ResponseBody
	@RequestMapping("/douyinSprPlan/validateLinkValue.shtml")
	public Map<String, Object> validateLinkValue(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "成功";
		String linkId = "";
		String sprPlanType = request.getParameter("sprPlanType");
		String linkValue = request.getParameter("linkValue");
		try {
			if(!StringUtil.isEmpty(linkValue)) {
				linkId = douyinSprPlanService.validateLinkValue(sprPlanType, linkValue);
			}else {
				code = "400";
				msg = "页面ID为空";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "500";
			msg = "系统错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		map.put("linkId", linkId);
		return map;
	}
	
	/**
	 * 
	 * @MethodName: editDouyinSprChnl
	 * @Description: (添加链接)
	 * @author Pengl
	 * @date 2019年5月15日 下午3:08:17
	 */
	@RequestMapping("/douyinSprPlan/addDouyinSprPlan.shtml")
	public ModelAndView editDouyinSprChnl(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/success/success");
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			DouyinSprPlan douyinSprPlan = new DouyinSprPlan();
			douyinSprPlan.setSprChnlId(Integer.parseInt(request.getParameter("sprChnlId")));
			douyinSprPlan.setSprPlanSite(request.getParameter("sprPlanSite"));
			douyinSprPlan.setConvertId2(request.getParameter("convertId2"));
			douyinSprPlan.setConvertId(request.getParameter("convertId"));
			douyinSprPlan.setTrackingId(request.getParameter("trackingId"));
			douyinSprPlan.setSprPlanType(request.getParameter("sprPlanType"));
			if(!StringUtil.isEmpty(request.getParameter("linkValue"))) {
				if(!"8".equals(request.getParameter("sprPlanType")) ) { //会场详情页， linkId：1 品牌会场  2 单品会场   商品详情页 linkId 是商品系统ID
					douyinSprPlan.setLinkValue(request.getParameter("linkValue"));
				}else { //特卖详情页， linkId 是ID
					douyinSprPlan.setLinkValue(request.getParameter("linkId"));
				}
			}
			douyinSprPlan.setCreateBy(staffID);
			douyinSprPlan.setCreateDate(date);
			douyinSprPlanService.editDouyinSprChnl(douyinSprPlan, request.getParameter("linkId"));
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		m.addObject(this.JSON_RESULT_CODE, code);
		m.addObject(this.JSON_RESULT_MESSAGE, msg);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: delDouyinSprPlan
	 * @Description: (删除链接)
	 * @author Pengl
	 * @date 2019年5月15日 下午3:10:55
	 */
	@ResponseBody
	@RequestMapping("/douyinSprPlan/delDouyinSprPlan.shtml")
	public Map<String, Object> delDouyinSprPlan(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "成功";
		String douyinSprPlanId = request.getParameter("douyinSprPlanId");
		try {
			if(!StringUtil.isEmpty(douyinSprPlanId)) {
				Date date = new Date();
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				DouyinSprPlan douyinSprPlan = new DouyinSprPlan();
				douyinSprPlan.setId(Integer.parseInt(douyinSprPlanId));
				douyinSprPlan.setDelFlag("1");
				douyinSprPlan.setUpdateBy(staffID);
				douyinSprPlan.setUpdateDate(date);
				douyinSprPlanService.updateByPrimaryKeySelective(douyinSprPlan);
			}else {
				code = "400";
				msg = "链接ID为空";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "500";
			msg = "系统错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @MethodName: douyinSprPlanStatisticsManager
	 * @Description: (抖音推广统计)
	 * @author Pengl
	 * @date 2019年5月17日 上午9:48:01
	 */
	@RequestMapping("/douyinSprPlan/douyinSprPlanStatisticsManager.shtml")
	public ModelAndView douyinSprPlanStatisticsManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/douyin/douyinSprPlan/getDouyinSprPlanStatisticsList");
		DouyinSprChnlExample douyinSprChnlExample = new DouyinSprChnlExample();
		douyinSprChnlExample.createCriteria().andDelFlagEqualTo("0");
		List<DouyinSprChnl> douyinSprChnlList = douyinSprChnlService.selectByExample(douyinSprChnlExample);
		m.addObject("douyinSprChnlList", douyinSprChnlList);
		m.addObject("sprPlanTypeList", DataDicUtil.getTableStatus("DOUYIN_SPR_PLAN", "SPR_PLAN_TYPE"));
		m.addObject("beginDate", DateUtil.getMonthFirstDay());
		m.addObject("endDate", DateUtil.formatDateByFormat(new Date(), "yyyy-MM-dd"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: getDouyinSprPlanStatisticsList
	 * @Description: (抖音推广统计)
	 * @author Pengl
	 * @date 2019年5月17日 上午11:24:59
	 */
	@ResponseBody
	@RequestMapping("/douyinSprPlan/getDouyinSprPlanStatisticsList.shtml")
	public Map<String, Object> getDouyinSprPlanStatisticsList(HttpServletRequest request, Page page ) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sprPlanId = request.getParameter("sprPlanId");
			String sprChnlId = request.getParameter("sprChnlId");
			String sprPlanSite = request.getParameter("sprPlanSite");
			String sprPlanType = request.getParameter("sprPlanType");
			String linkValue = request.getParameter("linkValue");
			String accessType = request.getParameter("accessType");
			String beginCreateDate = request.getParameter("beginCreateDate");
			String endCreateDate = request.getParameter("endCreateDate");
			String beginCountDate = request.getParameter("beginCountDate");
			String endCountDate = request.getParameter("endCountDate");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sprPlanId", sprPlanId);
			paramMap.put("sprChnlId", sprChnlId);
			paramMap.put("sprPlanSite", sprPlanSite);
			paramMap.put("sprPlanType", sprPlanType);
			paramMap.put("linkValue", linkValue);
			paramMap.put("accessType", accessType);
			if(!StringUtil.isEmpty(beginCreateDate)) {
				paramMap.put("beginCreateDate", sdf.parse(beginCreateDate+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(endCreateDate)) {
				paramMap.put("endCreateDate", sdf.parse(endCreateDate+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(beginCountDate)) {
				paramMap.put("beginCountDate", sdf.parse(beginCountDate+" 00:00:00"));
			}else {
				paramMap.put("beginCountDate", sdf.parse(DateUtil.getMonthFirstDay()+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(endCountDate)) {
				paramMap.put("endCountDate", sdf.parse(endCountDate+" 23:59:59"));
			}else {
				paramMap.put("endCountDate", sdf.parse(DateUtil.formatDateByFormat(date, "yyyy-MM-dd")+" 23:59:59"));
			}
			paramMap.put("orderByClause", " t.id desc");
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			dataList = douyinSprPlanService.getDouyinSprPlanList(paramMap);
			if(dataList != null && dataList.size() > 0 ) {
				Map<String, Object> parMap = new HashMap<String, Object>();
				List<Integer> sprPlanIdList = new ArrayList<Integer>();
				for(Map<String, Object> map : dataList) {
					sprPlanIdList.add(Integer.parseInt(map.get("id").toString()));
				}
				parMap.put("sprPlanIdList", sprPlanIdList);
				parMap.put("beginCountDate", paramMap.get("beginCountDate"));
				parMap.put("endCountDate", paramMap.get("endCountDate"));
				parMap.put("accessType", accessType);
				List<Map<String, Object>> sprPlanMapList = douyinSprPlanService.getDouyinSprPlanStatisticsList(parMap);
				for(Map<String, Object> map : dataList) {
					String[] ios_count_amount = map.get("ios_count_amount").toString().split(",");
					String[] android_count_amount = map.get("android_count_amount").toString().split(",");
					String new_guest_combine_order_count = "0";
					String new_guest_consume_count = "0";
					String new_guest_pay_amount = "0";
					String old_guest_combine_order_count = "0";
					String old_guest_consume_count = "0";
					String old_guest_pay_amount = "0";
					String total_pay_amount = "0";
					for(Map<String, Object> m :  sprPlanMapList) {
						if(map.get("id").toString().equals(m.get("id").toString())) {
							new_guest_combine_order_count = m.get("new_guest_combine_order_count").toString();
							new_guest_consume_count = m.get("new_guest_consume_count").toString();
							new_guest_pay_amount = m.get("new_guest_pay_amount").toString();
							old_guest_combine_order_count = m.get("old_guest_combine_order_count").toString();
							old_guest_consume_count = m.get("old_guest_consume_count").toString();
							old_guest_pay_amount = m.get("old_guest_pay_amount").toString();
							total_pay_amount = m.get("total_pay_amount").toString();
						}
					}
					map.put("ios_count", ios_count_amount[0]);
					map.put("ios_amount", ios_count_amount[1]);
					map.put("android_count", android_count_amount[0]);
					map.put("android_amount", android_count_amount[1]);
					map.put("new_guest_combine_order_count", new_guest_combine_order_count);
					map.put("new_guest_consume_count", new_guest_consume_count);
					map.put("new_guest_pay_amount", new_guest_pay_amount);
					map.put("old_guest_combine_order_count", old_guest_combine_order_count);
					map.put("old_guest_consume_count", old_guest_consume_count);
					map.put("old_guest_pay_amount", old_guest_pay_amount);
					map.put("total_pay_amount", total_pay_amount);
				}
			}
			totalCount = douyinSprPlanService.countDouyinSprPlanList(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
}

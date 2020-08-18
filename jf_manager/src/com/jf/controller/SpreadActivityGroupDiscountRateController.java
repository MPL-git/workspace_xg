package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.entity.*;
import com.jf.service.SpreadActivityGroupDiscountRateService;
import com.jf.service.SpreadActivityGroupService;
import com.jf.vo.Page;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Pengl
 * @create 2019-12-04 下午 5:11
 */
@Controller
public class SpreadActivityGroupDiscountRateController extends BaseController {

	@Autowired
	private SpreadActivityGroupDiscountRateService spreadActivityGroupDiscountRateService;

	@Autowired
	private SpreadActivityGroupService spreadActivityGroupService;

	@RequestMapping("/spreadActivityGroupDiscountRate/spreadActivityGroupDiscountRateManager.shtml")
	public ModelAndView spreadActivityGroupDiscountRateManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/groupDiscountRate/spreadActivityGroupDiscountRate/selectSpreadActivityGroupDiscountRateList");
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
		spreadActivityGroupExample.setOrderByClause(" id asc");
		m.addObject("spreadActivityGroupList", spreadActivityGroupService.selectByExample(spreadActivityGroupExample));
		return m;
	}

	@ResponseBody
	@RequestMapping("/spreadActivityGroupDiscountRate/selectSpreadActivityGroupDiscountRateList.shtml")
	public Map<String, Object> selectSpreadActivityGroupDiscountRateList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SpreadActivityGroupDiscountRateCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SpreadActivityGroupDiscountRateCustomExample spreadActivityGroupDiscountRateCustomExample = new SpreadActivityGroupDiscountRateCustomExample();
			SpreadActivityGroupDiscountRateCustomExample.SpreadActivityGroupDiscountRateCustomCriteria spreadActivityGroupDiscountRateCustomCriteria = spreadActivityGroupDiscountRateCustomExample.createCriteria();
			spreadActivityGroupDiscountRateCustomCriteria.andDelFlagEqualTo("0");
			spreadActivityGroupDiscountRateCustomCriteria.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			if(!StringUtil.isEmpty(request.getParameter("discountRateDateBegin")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andDateGreaterThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateBegin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("discountRateDateEnd")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andDateLessThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateEnd")));
			}
			if(!StringUtil.isEmpty(request.getParameter("spreadActivityGroupId")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andSpreadActivityGroupIdEqualTo(Integer.parseInt(request.getParameter("spreadActivityGroupId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("groupNameId")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andSpreadActivityGroupIdEqualTo(Integer.parseInt(request.getParameter("groupNameId")));
			}
			spreadActivityGroupDiscountRateCustomExample.setOrderByClause(" t.date desc, t.spread_activity_group_id desc");
			spreadActivityGroupDiscountRateCustomExample.setLimitStart(page.getLimitStart());
			spreadActivityGroupDiscountRateCustomExample.setLimitSize(page.getLimitSize());
			dataList = spreadActivityGroupDiscountRateService.selectByCustomExample(spreadActivityGroupDiscountRateCustomExample);
			totalCount = spreadActivityGroupDiscountRateService.countByCustomExample(spreadActivityGroupDiscountRateCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	@ResponseBody
	@RequestMapping("/spreadActivityGroupDiscountRate/updateDiscountRate.shtml")
	public Map<String, Object> updateDiscountRate(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String id = request.getParameter("id");
			String discountRate = request.getParameter("discountRate");
			if(!StringUtil.isEmpty(id)) {
				SpreadActivityGroupDiscountRate spreadActivityGroupDiscountRate = new SpreadActivityGroupDiscountRate();
				spreadActivityGroupDiscountRate.setId(Integer.parseInt(id));
				spreadActivityGroupDiscountRate.setDiscountRate(new BigDecimal(discountRate));
				spreadActivityGroupDiscountRate.setUpdateDate(new Date());
				spreadActivityGroupDiscountRateService.updateByPrimaryKeySelective(spreadActivityGroupDiscountRate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

	@RequestMapping("/spreadActivityGroupDiscountRate/updateDiscountRateBatchManager.shtml")
	public ModelAndView updateDiscountRateBatchManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/groupDiscountRate/spreadActivityGroupDiscountRate/newUpdateDiscountRateBatch");
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0").andDeviceTypeEqualTo(request.getParameter("deviceType"));
		spreadActivityGroupExample.setOrderByClause(" id desc");
		List<SpreadActivityGroup> spreadActivityGroupList = spreadActivityGroupService.selectByExample(spreadActivityGroupExample);
		List<JSONObject> spreadActivityGroupJson = new ArrayList();
		JSONObject listBoxJson;
		for(SpreadActivityGroup spreadActivityGroup : spreadActivityGroupList ) {
			listBoxJson = new JSONObject();
			listBoxJson.put("id", spreadActivityGroup.getId());
			listBoxJson.put("text", spreadActivityGroup.getActivityGroup());
			spreadActivityGroupJson.add(listBoxJson);
		}
		m.addObject("spreadActivityGroupJson", spreadActivityGroupJson);
		return m;
	}

	@RequestMapping("/spreadActivityGroupDiscountRate/updateDiscountRateBatch.shtml")
	public ModelAndView updateDiscountRateBatch(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("discountRate")) && !StringUtil.isEmpty(request.getParameter("groupNameId")) ) {
				String staffId = this.getSessionStaffBean(request).getStaffID();
				SpreadActivityGroupDiscountRateExample spreadActivityGroupDiscountRateExample = new SpreadActivityGroupDiscountRateExample();
				SpreadActivityGroupDiscountRateExample.Criteria spreadActivityGroupDiscountRateCriteria = spreadActivityGroupDiscountRateExample.createCriteria();
				if(!StringUtil.isEmpty(request.getParameter("discountRateDateBegin")) ) {
					spreadActivityGroupDiscountRateCriteria.andDateGreaterThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateBegin")));
				}
				if(!StringUtil.isEmpty(request.getParameter("discountRateDateEnd")) ) {
					spreadActivityGroupDiscountRateCriteria.andDateLessThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateEnd")));
				}
				String[] groupNameIds = request.getParameter("groupNameId").split(",");
				List<Integer> groupNameIdList = new ArrayList<>();
				for(String groupNameId : groupNameIds ) {
					groupNameIdList.add(Integer.parseInt(groupNameId));
				}
				spreadActivityGroupDiscountRateCriteria.andSpreadActivityGroupIdIn(groupNameIdList);
				SpreadActivityGroupDiscountRate spreadActivityGroupDiscountRate = new SpreadActivityGroupDiscountRate();
				spreadActivityGroupDiscountRate.setDiscountRate(new BigDecimal(request.getParameter("discountRate")));
				spreadActivityGroupDiscountRate.setUpdateBy(Integer.parseInt(staffId));
				spreadActivityGroupDiscountRate.setUpdateDate(new Date());
				spreadActivityGroupDiscountRateService.updateByExampleSelective(spreadActivityGroupDiscountRate, spreadActivityGroupDiscountRateExample);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}

	@RequestMapping("/spreadActivityGroupDiscountRate/addSpreadActivityGroupDiscountRateManager.shtml")
	public ModelAndView addSpreadActivityGroupDiscountRateManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/groupDiscountRate/spreadActivityGroupDiscountRate/addSelectSpreadActivityGroupDiscountRate");
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0").andDeviceTypeEqualTo(request.getParameter("deviceType"));
		spreadActivityGroupExample.setOrderByClause(" id asc");
		m.addObject("spreadActivityGroupList", spreadActivityGroupService.selectByExample(spreadActivityGroupExample));
		return m;
	}

	@ResponseBody
	@RequestMapping("/spreadActivityGroupDiscountRate/addSpreadActivityGroupDiscountRate.shtml")
	public ModelAndView addSpreadActivityGroupDiscountRate(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String groupNameId = request.getParameter("groupNameId");
			String discountRateDateBegin = request.getParameter("discountRateDateBegin");
			String discountRateDateEnd = request.getParameter("discountRateDateEnd");
			String discountRate = request.getParameter("discountRate");
			if(!StringUtil.isEmpty(groupNameId) && !StringUtil.isEmpty(discountRateDateBegin) && !StringUtil.isEmpty(discountRateDateEnd) && !StringUtil.isEmpty(discountRate)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				spreadActivityGroupDiscountRateService.addSpreadActivityGroupDiscountRate(Integer.parseInt(groupNameId), sdf.parse(discountRateDateBegin), sdf.parse(discountRateDateEnd), new BigDecimal(discountRate));
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 安卓活动组优惠率管理列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/androidGroupDiscountRate/list.shtml")
	public ModelAndView androidGroupDiscountRateList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/groupDiscountRate/spreadActivityGroupDiscountRate/androidGroupDiscountRateList");
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
		spreadActivityGroupExample.setOrderByClause(" id asc");
		m.addObject("spreadActivityGroupList", spreadActivityGroupService.selectByExample(spreadActivityGroupExample));
		return m;
	}

	/**
	 * 安卓活动组优惠率管理列表数据
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/androidGroupDiscountRate/data.shtml")
	public Map<String, Object> androidGroupDiscountRateData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SpreadActivityGroupDiscountRateCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SpreadActivityGroupDiscountRateCustomExample spreadActivityGroupDiscountRateCustomExample = new SpreadActivityGroupDiscountRateCustomExample();
			SpreadActivityGroupDiscountRateCustomExample.SpreadActivityGroupDiscountRateCustomCriteria spreadActivityGroupDiscountRateCustomCriteria = spreadActivityGroupDiscountRateCustomExample.createCriteria();
			spreadActivityGroupDiscountRateCustomCriteria.andDelFlagEqualTo("0");
			spreadActivityGroupDiscountRateCustomCriteria.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			if(!StringUtil.isEmpty(request.getParameter("discountRateDateBegin")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andDateGreaterThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateBegin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("discountRateDateEnd")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andDateLessThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateEnd")));
			}
			if(!StringUtil.isEmpty(request.getParameter("spreadActivityGroupId")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andSpreadActivityGroupIdEqualTo(Integer.parseInt(request.getParameter("spreadActivityGroupId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("groupNameId")) ) {
				spreadActivityGroupDiscountRateCustomCriteria.andSpreadActivityGroupIdEqualTo(Integer.parseInt(request.getParameter("groupNameId")));
			}
			spreadActivityGroupDiscountRateCustomExample.setOrderByClause(" t.date desc, t.spread_activity_group_id desc");
			spreadActivityGroupDiscountRateCustomExample.setLimitStart(page.getLimitStart());
			spreadActivityGroupDiscountRateCustomExample.setLimitSize(page.getLimitSize());
			dataList = spreadActivityGroupDiscountRateService.selectByCustomExample(spreadActivityGroupDiscountRateCustomExample);
			totalCount = spreadActivityGroupDiscountRateService.countByCustomExample(spreadActivityGroupDiscountRateCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}


}

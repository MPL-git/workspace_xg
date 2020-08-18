package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.*;
import com.jf.service.AndroidChannelGroupDiscountRateService;
import com.jf.service.AndroidChannelGroupService;
import com.jf.vo.Page;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Pengl
 * @create 2019-12-04 下午 5:13
 */
@Controller
public class AndroidChannelGroupDiscountRateController extends BaseController {

	@Autowired
	private AndroidChannelGroupDiscountRateService androidChannelGroupDiscountRateService;

	@Autowired
	private AndroidChannelGroupService androidChannelGroupService;

	@RequestMapping("/androidChannelGroupDiscountRate/androidChannelGroupDiscountRateManager.shtml")
	public ModelAndView androidChannelGroupDiscountRateManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/groupDiscountRate/androidChannelGroupDiscountRate/selectAndroidChannelGroupDiscountRateList");
		AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
		androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
		androidChannelGroupExample.setOrderByClause(" id asc");
		m.addObject("androidChannelGroupList", androidChannelGroupService.selectByExample(androidChannelGroupExample));
		return m;
	}

	@ResponseBody
	@RequestMapping("/androidChannelGroupDiscountRate/selectAndroidChannelGroupDiscountRateList.shtml")
	public Map<String, Object> selectAndroidChannelGroupDiscountRateList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<AndroidChannelGroupDiscountRateCustom> dataList = null;
		Integer totalCount = 0;
		try {
			AndroidChannelGroupDiscountRateCustomExample androidChannelGroupDiscountRateCustomExample = new AndroidChannelGroupDiscountRateCustomExample();
			AndroidChannelGroupDiscountRateCustomExample.AndroidChannelGroupDiscountRateCustomCriteria androidChannelGroupDiscountRateCustomCriteria = androidChannelGroupDiscountRateCustomExample.createCriteria();
			androidChannelGroupDiscountRateCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("discountRateDateBegin")) ) {
				androidChannelGroupDiscountRateCustomCriteria.andDateGreaterThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateBegin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("discountRateDateEnd")) ) {
				androidChannelGroupDiscountRateCustomCriteria.andDateLessThanOrEqualTo(DateUtil.StringToDate(request.getParameter("discountRateDateEnd")));
			}
			if(!StringUtil.isEmpty(request.getParameter("androidChannelGroupId")) ) {
				androidChannelGroupDiscountRateCustomCriteria.andAndroidChannelGroupIdEqualTo(Integer.parseInt(request.getParameter("androidChannelGroupId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("groupNameId")) ) {
				androidChannelGroupDiscountRateCustomCriteria.andAndroidChannelGroupIdEqualTo(Integer.parseInt(request.getParameter("groupNameId")));
			}
			androidChannelGroupDiscountRateCustomExample.setOrderByClause(" t.date desc, t.android_channel_group_id desc");
			androidChannelGroupDiscountRateCustomExample.setLimitStart(page.getLimitStart());
			androidChannelGroupDiscountRateCustomExample.setLimitSize(page.getLimitSize());
			dataList = androidChannelGroupDiscountRateService.selectByCustomExample(androidChannelGroupDiscountRateCustomExample);
			totalCount = androidChannelGroupDiscountRateService.countByCustomExample(androidChannelGroupDiscountRateCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	@ResponseBody
	@RequestMapping("/androidChannelGroupDiscountRate/updateDiscountRate.shtml")
	public Map<String, Object> updateDiscountRate(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String id = request.getParameter("id");
			String discountRate = request.getParameter("discountRate");
			if(!StringUtil.isEmpty(id)) {
				AndroidChannelGroupDiscountRate androidChannelGroupDiscountRate = new AndroidChannelGroupDiscountRate();
				androidChannelGroupDiscountRate.setId(Integer.parseInt(id));
				androidChannelGroupDiscountRate.setDiscountRate(new BigDecimal(discountRate));
				androidChannelGroupDiscountRate.setUpdateDate(new Date());
				androidChannelGroupDiscountRateService.updateByPrimaryKeySelective(androidChannelGroupDiscountRate);
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

	@RequestMapping("/androidChannelGroupDiscountRate/updateDiscountRateBatchManager.shtml")
	public ModelAndView updateDiscountRateBatchManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/groupDiscountRate/androidChannelGroupDiscountRate/newUpdateDiscountRateBatch");
		AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
		androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
		androidChannelGroupExample.setOrderByClause(" id desc");
		List<AndroidChannelGroup> androidChannelGroupList = androidChannelGroupService.selectByExample(androidChannelGroupExample);
		List<JSONObject> androidChannelGroupJson = new ArrayList();
		JSONObject listBoxJson;
		for(AndroidChannelGroup androidChannelGroup : androidChannelGroupList ) {
			listBoxJson = new JSONObject();
			listBoxJson.put("id", androidChannelGroup.getId());
			listBoxJson.put("text", androidChannelGroup.getGroupName());
			androidChannelGroupJson.add(listBoxJson);
		}
		m.addObject("androidChannelGroupJson", androidChannelGroupJson);
		return m;
	}

	@RequestMapping("/androidChannelGroupDiscountRate/updateDiscountRateBatch.shtml")
	public ModelAndView updateDiscountRateBatch(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("discountRate")) ) {
				String staffId = this.getSessionStaffBean(request).getStaffID();
				AndroidChannelGroupDiscountRateExample androidChannelGroupDiscountRateExample = new AndroidChannelGroupDiscountRateExample();
				AndroidChannelGroupDiscountRateExample.Criteria androidChannelGroupDiscountRateExampleCriteria = androidChannelGroupDiscountRateExample.createCriteria();
				androidChannelGroupDiscountRateExampleCriteria.andDelFlagEqualTo("0");
				if(!StringUtil.isEmpty(request.getParameter("groupNameId")) ) {
					String[] groupNameIds = request.getParameter("groupNameId").split(",");
					List<Integer> groupNameIdList = new ArrayList<>();
					for(String groupNameId : groupNameIds ) {
						groupNameIdList.add(Integer.parseInt(groupNameId));
					}
					androidChannelGroupDiscountRateExampleCriteria.andAndroidChannelGroupIdIn(groupNameIdList);
				}
				if(!StringUtil.isEmpty(request.getParameter("discountRateDateBegin")) ) {
					androidChannelGroupDiscountRateExampleCriteria.andDateGreaterThanOrEqualTo(DateUtil.getDateByFormat(request.getParameter("discountRateDateBegin")));
				}
				if(!StringUtil.isEmpty(request.getParameter("discountRateDateEnd")) ) {
					androidChannelGroupDiscountRateExampleCriteria.andDateLessThanOrEqualTo(DateUtil.getDateByFormat(request.getParameter("discountRateDateEnd")));
				}
				AndroidChannelGroupDiscountRate androidChannelGroupDiscountRate = new AndroidChannelGroupDiscountRate();
				androidChannelGroupDiscountRate.setDiscountRate(new BigDecimal(request.getParameter("discountRate")));
				androidChannelGroupDiscountRate.setUpdateBy(Integer.parseInt(staffId));
				androidChannelGroupDiscountRate.setUpdateDate(new Date());
				androidChannelGroupDiscountRateService.updateByExampleSelective(androidChannelGroupDiscountRate, androidChannelGroupDiscountRateExample);
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

	@RequestMapping("/androidChannelGroupDiscountRate/addAndroidChannelGroupDiscountRateManager.shtml")
	public ModelAndView addAndroidChannelGroupDiscountRateManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/groupDiscountRate/androidChannelGroupDiscountRate/addAndroidChannelGroupDiscountRate");
		AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
		androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
		androidChannelGroupExample.setOrderByClause(" id asc");
		m.addObject("androidChannelGroupList", androidChannelGroupService.selectByExample(androidChannelGroupExample));
		return m;
	}

	@RequestMapping("/androidChannelGroupDiscountRate/addAndroidChannelGroupDiscountRate")
	public ModelAndView addAndroidChannelGroupDiscountRate(HttpServletRequest request) {
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
				androidChannelGroupDiscountRateService.addAndroidChannelGroupDiscountRate(Integer.parseInt(groupNameId), sdf.parse(discountRateDateBegin), sdf.parse(discountRateDateEnd), new BigDecimal(discountRate));
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


}

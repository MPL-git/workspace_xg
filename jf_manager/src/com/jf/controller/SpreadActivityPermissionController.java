package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.entity.*;
import com.jf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jf.common.utils.StringUtils;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SpreadActivityPermissionController extends BaseController {

	@Autowired
	private SpreadActivityPermissionService spreadActivityPermissionService;
	
	@Autowired
	private OrgMngService orgMngService;
	
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	@Autowired
	private SpreadActivityGroupSetService spreadActivityGroupSetService;
	
	@Autowired
	private AndroidChannelGroupSetService androidChannelGroupSetService;
	
	
	/**
	 * 
	 * @MethodName: spreadActivityPermissionManager
	 * @Description: (人员权限设置)
	 * @author Pengl
	 * @date 2019年7月18日 下午2:48:27
	 */
	@RequestMapping("/spreadActivityPermission/spreadActivityPermissionManager.shtml")
	public ModelAndView spreadActivityPermissionManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/spreadActivityPermission/getSpreadActivityPermissionList");
		return m;
	}
	
	/**
	 * 
	 * @MethodName: getSpreadActivityPermissionList
	 * @Description: (人员权限设置)
	 * @author Pengl
	 * @date 2019年7月18日 下午4:19:26
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityPermission/getSpreadActivityPermissionList.shtml")
	public Map<String, Object> getSpreadActivityPermissionList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount ="0";
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("MIN_NUM", page.getLimitStart());
			paramMap.put("MAX_NUM", page.getLimitSize());
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
    	    sysParamCfgExample.createCriteria().andParamCodeEqualTo("SPREAD_ACTIVITY_PERMISSION_ORG_ID");
    	    List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
			if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
				paramMap.put("ORG_ID", sysParamCfgList.get(0).getParamValue());
			}
			totalCount = orgMngService.queryDataCount(paramMap);
			dataList = orgMngService.queryDataList(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: addOrUpdatePermissionManager
	 * @Description: (iOS活动组权限设置  or 安卓渠道集合权限设置)
	 * @author Pengl
	 * @date 2019年7月18日 下午4:19:35
	 */
	@RequestMapping("/spreadActivityPermission/addOrUpdatePermissionManager.shtml")
	public ModelAndView addOrUpdatePermissionManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String staffId = request.getParameter("staffId");
		String type = request.getParameter("type");
		if("1".equals(type) || "3".equals(type)) {
			m.setViewName("/dataCenter/spreadActivityPermission/getSpreadActivityGroupList");
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			String deviceType = Const.DEVICE_TYPE_IOS;
			if("1".equals(type)) {
				spreadActivityGroupSetCustomCriteria.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			}else if("3".equals(type)){
				spreadActivityGroupSetCustomCriteria.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
				deviceType = Const.DEVICE_TYPE_ANDROID;
			}
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
			m.addObject("deviceType", deviceType);
			m.addObject("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);
		}else {
			m.setViewName("/dataCenter/spreadActivityPermission/getAndroidChannelGroupList");
			AndroidChannelGroupSetExample androidChannelGroupSetExample = new AndroidChannelGroupSetExample();
			androidChannelGroupSetExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			androidChannelGroupSetExample.setOrderByClause(" ifnull(seq_no, 999999), id desc");
			List<AndroidChannelGroupSet> androidChannelGroupSetList = androidChannelGroupSetService.selectByExample(androidChannelGroupSetExample);
			m.addObject("androidChannelGroupSetList", androidChannelGroupSetList);
		}
		m.addObject("staffId", staffId);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: getSpreadActivityGroupList
	 * @Description: (iOS活动组权限设置)
	 * @author Pengl
	 * @date 2019年7月18日 下午4:20:54
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityPermission/getSpreadActivityGroupList.shtml")
	public Map<String, Object> getSpreadActivityGroupList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(request.getParameter("staffId")) ) {
				paramMap.put("staffId", request.getParameter("staffId"));
			}
			if(!StringUtils.isEmpty(request.getParameter("deviceType")) ) {
				paramMap.put("deviceType", request.getParameter("deviceType"));
			}
			if(!StringUtils.isEmpty(request.getParameter("groupSetId")) ) {
				paramMap.put("groupSetId", request.getParameter("groupSetId"));
			}
			if(!StringUtils.isEmpty(request.getParameter("id")) ) {
				paramMap.put("id", request.getParameter("id"));
			}
			if(!StringUtils.isEmpty(request.getParameter("activityGroup")) ) {
				paramMap.put("activityGroup", request.getParameter("activityGroup"));
			}
			if(!StringUtils.isEmpty(request.getParameter("isEffect")) ) {
				paramMap.put("isEffect", request.getParameter("isEffect"));
			}
			if(!StringUtils.isEmpty(request.getParameter("permissionStatus")) ) {
				paramMap.put("permissionStatus", request.getParameter("permissionStatus"));
			}
			paramMap.put("orderBy", " t.id asc");
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			totalCount = spreadActivityPermissionService.getSpreadActivityGroupCount(paramMap);
			dataList = spreadActivityPermissionService.getSpreadActivityGroupList(paramMap);
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
	 * @MethodName: getAndroidChannelGroupList
	 * @Description: (安卓渠道集合权限设置)
	 * @author Pengl
	 * @date 2019年7月18日 下午4:20:54
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityPermission/getAndroidChannelGroupList.shtml")
	public Map<String, Object> getAndroidChannelGroupList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(request.getParameter("staffId")) ) {
				paramMap.put("staffId", request.getParameter("staffId"));
			}
			if(!StringUtils.isEmpty(request.getParameter("id")) ) {
				paramMap.put("id", request.getParameter("id"));
			}
			if(!StringUtils.isEmpty(request.getParameter("groupName")) ) {
				paramMap.put("groupName", request.getParameter("groupName"));
			}
			if(!StringUtils.isEmpty(request.getParameter("status")) ) {
				paramMap.put("status", request.getParameter("status"));
			}
			if(!StringUtils.isEmpty(request.getParameter("permissionStatus")) ) {
				paramMap.put("permissionStatus", request.getParameter("permissionStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("androidChannelGroupSetId")) ) {
				paramMap.put("androidChannelGroupSetId", request.getParameter("androidChannelGroupSetId"));
			}
			paramMap.put("orderBy", " t.id asc");
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			totalCount = spreadActivityPermissionService.getAndroidChannelGroupCount(paramMap);
			dataList = spreadActivityPermissionService.getAndroidChannelGroupList(paramMap);
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
	 * @MethodName: updateIOSPermission
	 * @Description: (IOS 添加权限 or 移除权限)
	 * @author Pengl
	 * @date 2019年7月19日 上午10:54:50
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityPermission/updateIOSPermission.shtml")
	public Map<String, Object> updateIOSPermission(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			spreadActivityPermissionService.updateIOSPermission(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statusCode = StateCode.JSON_AJAX_ERROR.getStateCode();
			message = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @MethodName: updateAndroidPermission
	 * @Description: (Android 添加权限 or 移除权限)
	 * @author Pengl
	 * @date 2019年7月19日 上午10:54:55
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityPermission/updateAndroidPermission.shtml")
	public Map<String, Object> updateAndroidPermission(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			spreadActivityPermissionService.updateAndroidPermission(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statusCode = StateCode.JSON_AJAX_ERROR.getStateCode();
			message = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
}

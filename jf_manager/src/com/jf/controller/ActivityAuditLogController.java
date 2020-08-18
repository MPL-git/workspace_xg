package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.service.ActivityAuditLogService;
/**
 * 审核流水表controller
 * @author luoyl
 *
 */
@Controller
public class ActivityAuditLogController {

	@Autowired
	private ActivityAuditLogService activityAuditLogService;
	
	
	/**
	 * 列表
	 * @param model
	 * @param request
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value="/activityAuditLog/activityAuditLogList.shtml")
	public String activityAuditLogList(Model model,HttpServletRequest request,Integer activityId){
		model.addAttribute("activityId", activityId);
		return "/activity/activityLogList";
	}
	
	@RequestMapping(value="/activityAuditLog/activityAuditLogListData.shtml")
	@ResponseBody
	public Map<String, Object> activityAuditLogListData(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount =0;
		dataList=activityAuditLogService.selectActivityAuditLogCustomList(Integer.valueOf(request.getParameter("activityId")));
		totalCount=activityAuditLogService.selectActivityAuditLogCustomCount(Integer.valueOf(request.getParameter("activityId")));
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
}

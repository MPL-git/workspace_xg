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

import com.jf.entity.ActivityProductAuditLogCustom;
import com.jf.service.ActivityProductAuditLogService;
/**
 * 单品商品审核流水表controller
 * @author luoyl
 *
 */
@Controller
public class ActivityProductAuditLogController {

	@Autowired
	private ActivityProductAuditLogService activityProductAuditLogService;
	
	
	/**
	 * 列表
	 * @param model
	 * @param request
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value="/activityProductAuditLog/activityProductAuditLogList.shtml")
	public String activityAuditLogList(Model model,HttpServletRequest request,Integer activityProductId){
		model.addAttribute("activityProductId", activityProductId);
		return "/activity/activityProductAuditLogList";
	}
	
	@RequestMapping(value="/activityProductAuditLog/activityProductAuditLogListData.shtml")
	@ResponseBody
	public Map<String, Object> activityAuditLogListData(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount =0;
		dataList=activityProductAuditLogService.selectActivityProductAuditLogCustomList(Integer.valueOf(request.getParameter("activityProductId")));
		totalCount=activityProductAuditLogService.selectActivityProductAuditLogCustomCount(Integer.valueOf(request.getParameter("activityProductId")));
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
}

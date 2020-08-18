package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.IntellectualPropertyRightCustom;
import com.jf.entity.IntellectualPropertyRightCustomExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.IntellectualPropertyRightCustomExample.IntellectualPropertyRightCustomCriteria;
import com.jf.service.IntellectualPropertyRightService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
@RequestMapping("/intellectualPropertyRight")
public class IntellectualPropertyRightController extends BaseController {

	@Autowired
	private IntellectualPropertyRightService service;
	
	
	/**
	 * 知识产权审核列表页
	 * 
	 * @return
	 */
	@RequestMapping("/auditManageList.shtml")
	public ModelAndView auditManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/intellectualProperty/propertyAuditList");
		m.addObject("rightTypeList", DataDicUtil.getTableStatus("BU_INTELLECTUAL_PROPERTY_RIGHT", "PROPERTY_RIGHT_TYPE"));
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_INTELLECTUAL_PROPERTY_RIGHT", "STATUS"));
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		m.addObject("canOperate", service.getCanOperate(staffId));// 是否为允许操作人员
		return m;
	}
	
	/**
	 * 获取知识产权审核列表页数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/auditManageData.shtml")
	public Map<String, Object> auditManageData(HttpServletRequest request, Page page){

		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			IntellectualPropertyRightCustomExample rightCustomExample = new IntellectualPropertyRightCustomExample();
			IntellectualPropertyRightCustomCriteria rightCustomCriteria = rightCustomExample.createCriteria();
			rightCustomCriteria.andDelFlagEqualTo("0");
			rightCustomExample.setOrderByClause("id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status = request.getParameter("status");
				rightCustomCriteria.andStatusEqualTo(status);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("obligeeName"))){
				String obligeeName = request.getParameter("obligeeName");
				rightCustomCriteria.andObligeeNameLike("%"+obligeeName+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("rightName")) ){
				String rightName = request.getParameter("rightName");
				rightCustomCriteria.andPropertyRightNameLike("%"+rightName+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("rightType"))){
				String rightType = request.getParameter("rightType");
				rightCustomCriteria.andPropertyRightTypeEqualTo(rightType);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ){
				rightCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				rightCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			
			totalCount = service.countCustomByExample(rightCustomExample);
			
			rightCustomExample.setLimitStart(page.getLimitStart());
			rightCustomExample.setLimitSize(page.getLimitSize());
			List<IntellectualPropertyRightCustom> rightCustoms = service.selectCustomByExample(rightCustomExample);
		
			resMap.put("Rows", rightCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 知识产权审核编辑页
	 * 
	 * @return
	 */
	@RequestMapping("/auditManageDetail.shtml")
	public ModelAndView auditManageDetail(HttpServletRequest request, Integer id) {
		String page = "/intellectualProperty/propertyAuditDetail";
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		Map<String, Object> resultMap = service.getPageData(id, staffId);
		return new ModelAndView(page, resultMap);
	}
	
	/**
	 * 产权审核
	 * 
	 * @param request
	 * @param rightCustom
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateStatus.shtml")
	public Map<String, Object> submitRightAudit(HttpServletRequest request,
			IntellectualPropertyRightCustom rightCustom) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			rightCustom.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			service.updateStatus(rightCustom);
			map.put("code", "200");
		} catch (ArgException e) {
			e.printStackTrace();
			map.put("code", "999");
			map.put("message", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "999");
		}
		return map;
	}
	
}

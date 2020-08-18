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
import com.jf.entity.IntellectualPropertyRightAppealCustom;
import com.jf.entity.IntellectualPropertyRightAppealCustomExample;
import com.jf.entity.IntellectualPropertyRightAppealCustomExample.IntellectualPropertyRightAppealCustomCriteria;
import com.jf.entity.IntellectualPropertyRightCustom;
import com.jf.entity.IntellectualPropertyRightCustomExample;
import com.jf.entity.IntellectualPropertyRightCustomExample.IntellectualPropertyRightCustomCriteria;
import com.jf.service.IntellectualPropertyRightAppealService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
@RequestMapping("/propertyRightAppeal")
public class IntellectualPropertyRightAppealController extends BaseController {

	@Autowired
	private IntellectualPropertyRightAppealService service;
	
	
	/**
	 * 投诉受理列表页
	 * 
	 * @return
	 */
	@RequestMapping("/appealManageList.shtml")
	public ModelAndView appealManageList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/intellectualProperty/propertyAppealList");
		m.addObject("appealTypeList", DataDicUtil.getTableStatus("PROPERTY_RIGHT_APPEAL", "APPEAL_TYPE"));
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		m.addObject("canOperate", service.getCanOperate(staffId));// 是否为允许操作人员
		return m;
	}
	
	/**
	 * 获取投诉受理列表页数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/appealManageData.shtml")
	public Map<String, Object> appealManageData(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			IntellectualPropertyRightAppealCustomExample rightAppealCustomExample = new IntellectualPropertyRightAppealCustomExample();
			IntellectualPropertyRightAppealCustomCriteria rightAppealCustomCriteria = rightAppealCustomExample.createCriteria();
			rightAppealCustomCriteria.andDelFlagEqualTo("0");
			rightAppealCustomExample.setOrderByClause("a.id asc");
			// 列表只展示待受理数据
			rightAppealCustomCriteria.andAcceptStatusEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("rightAppealId")) ){
				String rightAppealId = request.getParameter("rightAppealId");
				rightAppealCustomCriteria.andIdEqualTo(Integer.parseInt(rightAppealId));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("propertyRightId"))){
				String propertyRightId = request.getParameter("propertyRightId");
				rightAppealCustomCriteria.andIntellectualPropertyRightIdEqualTo(Integer.parseInt(propertyRightId));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("appealType")) ){
				String appealType = request.getParameter("appealType");
				rightAppealCustomCriteria.andAppealTypeEqualTo(appealType);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mobile"))){
				String mobile = request.getParameter("mobile");
				rightAppealCustomExample.setMobile(mobile);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ){
				rightAppealCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				rightAppealCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			
			totalCount = service.countCustomByExample(rightAppealCustomExample);
			
			rightAppealCustomExample.setLimitStart(page.getLimitStart());
			rightAppealCustomExample.setLimitSize(page.getLimitSize());
			List<IntellectualPropertyRightAppealCustom> rightAppealCustoms = service.selectCustomByExample(rightAppealCustomExample);
		
			resMap.put("Rows", rightAppealCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 更新领取人
	 * 
	 * @param request
	 * @param rightAppealCustom
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateStaffId.shtml")
	public Map<String, Object> updateStaffId(HttpServletRequest request,
			IntellectualPropertyRightAppealCustom rightAppealCustom) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			rightAppealCustom.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			rightAppealCustom.setStaffId(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			service.updateStaffId(rightAppealCustom);
			map.put("code", "200");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "999");
		}
		return map;
	}
	
	/**
	 * 投诉受理编辑页
	 * 
	 * @return
	 */
	@RequestMapping("/appealManageDetail.shtml")
	public ModelAndView auditManageDetail(Integer id) {
		String page = "/intellectualProperty/propertyAppealDetail";
		Map<String, Object> resultMap = service.getPageData(id);
		return new ModelAndView(page, resultMap);
	}
	
	/**
	 * 商家序号检测
	 * 
	 * @return
	 */
	@RequestMapping("/checkMchtInfo.shtml")
	public ModelAndView checkMchtInfo(String mchtCode) {
		String page = "/intellectualProperty/checkMchtInfo";
		Map<String, Object> resultMap = service.checkMchtInfo(mchtCode);
		return new ModelAndView(page, resultMap);
	}
	
	/**
	 * 知识产权投诉受理审核
	 * 
	 * @param request
	 * @param rightCustom
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateAcceptStatus.shtml")
	public Map<String, Object> updateAcceptStatus(HttpServletRequest request,
			IntellectualPropertyRightAppealCustom rightAppealCustom) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			rightAppealCustom.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			service.updateAcceptStatus(rightAppealCustom);
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
	
	
	/**
	 * 全部产权投诉列表页
	 * 
	 * @return
	 */
	@RequestMapping("/allAppealList.shtml")
	public ModelAndView allAppealList() {
		ModelAndView m = new ModelAndView("/intellectualProperty/allPropertyAppealList");
		m.addObject("rightTypeList", DataDicUtil.getTableStatus("BU_INTELLECTUAL_PROPERTY_RIGHT", "PROPERTY_RIGHT_TYPE"));
		m.addObject("acceptStatusList", DataDicUtil.getTableStatus("PROPERTY_RIGHT_APPEAL", "ACCEPT_STATUS"));
		m.addObject("complainStatusList", DataDicUtil.getTableStatus("PROPERTY_RIGHT_COMPLAIN", "STATUS"));
		m.addObject("prosecutionStatusList", DataDicUtil.getTableStatus("PROPERTY_RIGHT_PROSECUTION", "STATUS"));
		return m;
	}
	
	/**
	 * 获取全部产权列表页数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/allAppealData.shtml")
	public Map<String, Object> allAppealData(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			IntellectualPropertyRightAppealCustomExample rightAppealCustomExample = new IntellectualPropertyRightAppealCustomExample();
			IntellectualPropertyRightAppealCustomExample.IntellectualPropertyRightAppealCustomCriteria rightAppealCustomCriteria = rightAppealCustomExample.createCriteria();
			rightAppealCustomCriteria.andDelFlagEqualTo("0");
			rightAppealCustomExample.setOrderByClause("a.id asc");
			if(!StringUtil.isEmpty(request.getParameter("acceptStatus")) ){
				String acceptStatus = request.getParameter("acceptStatus");
				rightAppealCustomCriteria.andAcceptStatusEqualTo(acceptStatus);
			}
			rightAppealCustomExample.setPresentTime(new Date());
			if(!StringUtil.isEmpty(request.getParameter("mobile"))){
				String mobile = request.getParameter("mobile");
				rightAppealCustomExample.setMobile(mobile);
			}
			if(!StringUtil.isEmpty(request.getParameter("propertyRightType")) ){
				String propertyRightType = request.getParameter("propertyRightType");
				rightAppealCustomExample.setPropertyRightType(propertyRightType);
			}
			if(!StringUtil.isEmpty(request.getParameter("complainStatus")) ){
				String complainStatus = request.getParameter("complainStatus");
				rightAppealCustomExample.setComplainStatus(complainStatus);
			}
			if(!StringUtil.isEmpty(request.getParameter("prosecutionStatus")) ){
				String prosecutionStatus = request.getParameter("prosecutionStatus");
				rightAppealCustomExample.setProsecutionStatus(prosecutionStatus);
			}
			if(!StringUtil.isEmpty(request.getParameter("transmitStatus")) ){
				String transmitStatus = request.getParameter("transmitStatus");
				rightAppealCustomExample.setTransmitStatus(transmitStatus);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ){
				rightAppealCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				rightAppealCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			
			totalCount = service.countAllByExample(rightAppealCustomExample);
			
			rightAppealCustomExample.setLimitStart(page.getLimitStart());
			rightAppealCustomExample.setLimitSize(page.getLimitSize());
			List<IntellectualPropertyRightAppealCustom> rightAppealCustoms = service.selectAllCustomByExample(rightAppealCustomExample);
		
			resMap.put("Rows", rightAppealCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 更改处理人弹框
	 * 
	 * @return
	 */
	@RequestMapping("/changeStaffDialog.shtml")
	public ModelAndView changeStaff(String id) {
		ModelAndView m = new ModelAndView("/intellectualProperty/changeStaff");
		m.addObject("staffList", service.getStaffRoleList());
		m.addObject("id", id);
		return m;
	}
	
	/**
	 * 修改领取人
	 * 
	 * @param request
	 * @param rightAppealCustom
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeStaffId.shtml")
	public Map<String, Object> changeStaffId(HttpServletRequest request,
			IntellectualPropertyRightAppealCustom rightAppealCustom) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			rightAppealCustom.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			service.updateStaffId(rightAppealCustom);
			map.put("code", "200");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "999");
		}
		return map;
	}
}

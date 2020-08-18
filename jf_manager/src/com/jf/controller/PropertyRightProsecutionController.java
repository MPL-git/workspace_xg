package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.jf.entity.PropertyRightComplainCustom;
import com.jf.entity.PropertyRightProsecutionCustom;
import com.jf.entity.PropertyRightProsecutionCustomExample;
import com.jf.entity.PropertyRightProsecutionCustomExample.PropertyRightProsecutionCustomCriteria;
import com.jf.service.PropertyRightProsecutionService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
@RequestMapping("/propertyRightProsecution")
public class PropertyRightProsecutionController extends BaseController {

	@Autowired
	private PropertyRightProsecutionService service;
	
	
	/**
	 * 起诉跟进列表页
	 * 
	 * @return
	 */
	@RequestMapping("/prosecutionManageList.shtml")
	public ModelAndView prosecutionManageList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/intellectualProperty/propertyProsecutionList");
		m.addObject("statusList", DataDicUtil.getTableStatus("PROPERTY_RIGHT_PROSECUTION", "STATUS"));
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		m.addObject("staffList", service.getStaffList(staffId));
		return m;
	}
	
	/**
	 * 获取起诉跟进列表页数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/prosecutionManageData.shtml")
	public Map<String, Object> prosecutionManageData(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			PropertyRightProsecutionCustomExample rightProsecutionCustomExample = new PropertyRightProsecutionCustomExample();
			PropertyRightProsecutionCustomCriteria rightProsecutionCustomCriteria = rightProsecutionCustomExample.createCriteria();
			rightProsecutionCustomCriteria.andDelFlagEqualTo("0");
			// 列表只展示已提交和起诉中的数据   列表只展示已截止的数据
			rightProsecutionCustomExample.setListFlag(1);
			rightProsecutionCustomExample.setProsecutionEndDate(new Date());
			rightProsecutionCustomExample.setOrderByClause("a.id asc");
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status = request.getParameter("status");
				rightProsecutionCustomCriteria.andStatusEqualTo(status);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ){
				rightProsecutionCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				rightProsecutionCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile = request.getParameter("mobile");
				rightProsecutionCustomExample.setMobile(mobile);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("staffId")) ){
				String staffId = request.getParameter("staffId");
				rightProsecutionCustomExample.setStaffId(Integer.parseInt(staffId));
			}
			
			
			totalCount = service.countCustomByExample(rightProsecutionCustomExample);
			
			rightProsecutionCustomExample.setLimitStart(page.getLimitStart());
			rightProsecutionCustomExample.setLimitSize(page.getLimitSize());
			List<PropertyRightProsecutionCustom> rightProsecutionCustoms = service.selectCustomByExample(rightProsecutionCustomExample);
		
			resMap.put("Rows", rightProsecutionCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 起诉编辑页
	 * 
	 * @return
	 */
	@RequestMapping("/prosecutionManageDetail.shtml")
	public ModelAndView auditManageDetail(Integer id) {
		String page = "/intellectualProperty/PropertyRightProsecutionDetail";
		Map<String, Object> resultMap = service.getPageData(id);
		return new ModelAndView(page, resultMap);
	}
	
	/**
	 * 平台起诉跟进
	 * 
	 * @param request
	 * @param rightCustom
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request,
			PropertyRightProsecutionCustom prosecutionCustom) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			prosecutionCustom.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			service.updateStatus(prosecutionCustom);
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
	 * 平台起诉结果提交
	 * 
	 * @param request
	 * @param rightCustom
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/submitResult.shtml")
	public Map<String, Object> submitResult(HttpServletRequest request,
			PropertyRightProsecutionCustom prosecutionCustom) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			prosecutionCustom.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			service.submitResult(prosecutionCustom);
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

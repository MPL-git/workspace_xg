package com.jf.controller;

import java.text.SimpleDateFormat;
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
import com.jf.entity.PropertyRightComplainCustom;
import com.jf.entity.PropertyRightComplainCustomExample;
import com.jf.entity.PropertyRightComplainCustomExample.PropertyRightComplainCustomCriteria;
import com.jf.service.PropertyRightComplainService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
@RequestMapping("/propertyRightComplain")
public class PropertyRightComplainController extends BaseController {

	@Autowired
	private PropertyRightComplainService service;
	
	
	/**
	 * 商家声明列表页
	 * 
	 * @return
	 */
	@RequestMapping("/complainManageList.shtml")
	public ModelAndView complainManageList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/intellectualProperty/propertyComplainList");
		m.addObject("appealTypeList", DataDicUtil.getTableStatus("PROPERTY_RIGHT_APPEAL", "APPEAL_TYPE"));
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		m.addObject("staffList", service.getStaffList(staffId));
		return m;
	}
	
	/**
	 * 获取商家声明列表页数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/complainManageData.shtml")
	public Map<String, Object> complainManageData(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			PropertyRightComplainCustomExample rightComplainCustomExample = new PropertyRightComplainCustomExample();
			PropertyRightComplainCustomCriteria rightComplainCustomCriteria = rightComplainCustomExample.createCriteria();
			rightComplainCustomCriteria.andDelFlagEqualTo("0");
			rightComplainCustomExample.setOrderByClause("a.id asc");
			// 列表只展示待审核数据 即申诉中数据
			rightComplainCustomCriteria.andStatusEqualTo("1");
			if(!StringUtil.isEmpty(request.getParameter("rightAppealId")) ){
				String rightAppealId = request.getParameter("rightAppealId");
				rightComplainCustomExample.setRightAppealId(Integer.parseInt(rightAppealId));
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ){
				rightComplainCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				rightComplainCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("appealType")) ){
				String appealType = request.getParameter("appealType");
				rightComplainCustomExample.setAppealType(appealType);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("relevantValue")) ){
				String relevantValue = request.getParameter("relevantValue");
				rightComplainCustomExample.setRelevantValue(relevantValue);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
				String companyName = request.getParameter("companyName");
				rightComplainCustomExample.setCompanyName(companyName);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("staffId")) ){
				String staffId = request.getParameter("staffId");
				rightComplainCustomExample.setStaffId(Integer.parseInt(staffId));
			}
			
			totalCount = service.countCustomByExample(rightComplainCustomExample);
			
			rightComplainCustomExample.setLimitStart(page.getLimitStart());
			rightComplainCustomExample.setLimitSize(page.getLimitSize());
			List<PropertyRightComplainCustom> rightComplainCustoms = service.selectCustomByExample(rightComplainCustomExample);
		
			resMap.put("Rows", rightComplainCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 商家声明编辑页
	 * 
	 * @return
	 */
	@RequestMapping("/complainManageDetail.shtml")
	public ModelAndView auditManageDetail(Integer id) {
		String page = "/intellectualProperty/propertyComplainDetail";
		Map<String, Object> resultMap = service.getPageData(id);
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
	@RequestMapping("/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request,
			PropertyRightComplainCustom rightComplainCustom) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			rightComplainCustom.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			service.updateStatus(rightComplainCustom);
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

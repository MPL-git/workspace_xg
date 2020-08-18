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
import com.jf.entity.StateCode;
import com.jf.entity.WeixinXcxSprChnl;
import com.jf.entity.WeixinXcxSprChnlCustom;
import com.jf.entity.WeixinXcxSprChnlCustomExample;
import com.jf.entity.WeixinXcxSprChnlExample;
import com.jf.service.WeixinXcxSprChnlService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class WeixinXcxSprChnlController extends BaseController {

	@Autowired
	private WeixinXcxSprChnlService weixinXcxSprChnlService;
	
	/**
	 * 
	 * @Title weixinXcxSprChnlManager   
	 * @Description TODO(渠道管理)   
	 * @author pengl
	 * @date 2018年11月6日 下午1:55:46
	 */
	@RequestMapping("/weixinXcxSprChnl/weixinXcxSprChnlManager.shtml")
	public ModelAndView weixinXcxSprChnlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/weixinXcxSpr/weixinXcxSprChnl/getWeixinXcxSprChnlList");
		return m;
	}
	
	/**
	 * 
	 * @Title getWeixinXcxSprChnlList   
	 * @Description TODO(渠道管理)   
	 * @author pengl
	 * @date 2018年11月6日 下午1:55:14
	 */
	@ResponseBody
	@RequestMapping("/weixinXcxSprChnl/getWeixinXcxSprChnlList.shtml")
	public Map<String, Object> getWeixinXcxSprChnlList(HttpServletRequest request, Page page, Integer weixinXcxSprChnlId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<WeixinXcxSprChnlCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beginCreateDate = request.getParameter("beginCreateDate");
			String endCreateDate = request.getParameter("endCreateDate");
			WeixinXcxSprChnlCustomExample weixinXcxSprChnlCustomExample = new WeixinXcxSprChnlCustomExample();
			WeixinXcxSprChnlCustomExample.WeixinXcxSprChnlCustomCriteria weixinXcxSprChnlCustomCriteria = weixinXcxSprChnlCustomExample.createCriteria();
			weixinXcxSprChnlCustomCriteria.andDelFlagEqualTo("0");
			if(weixinXcxSprChnlId != null) {
				weixinXcxSprChnlCustomCriteria.andIdEqualTo(weixinXcxSprChnlId);
			}
			if(!StringUtil.isEmpty(beginCreateDate)) {
				weixinXcxSprChnlCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(beginCreateDate+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(endCreateDate)) {
				weixinXcxSprChnlCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(endCreateDate+" 23:59:59"));
			}
			weixinXcxSprChnlCustomExample.setOrderByClause(" id desc");
			weixinXcxSprChnlCustomExample.setLimitStart(page.getLimitStart());
			weixinXcxSprChnlCustomExample.setLimitSize(page.getLimitSize());
			totalCount = weixinXcxSprChnlService.countByCustomExample(weixinXcxSprChnlCustomExample);
			dataList = weixinXcxSprChnlService.selectByCustomExample(weixinXcxSprChnlCustomExample);
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
	 * @Title editWeixinXcxSprChnlManager   
	 * @Description TODO(编辑渠道)   
	 * @author pengl
	 * @date 2018年11月6日 下午2:11:44
	 */
	@RequestMapping("/weixinXcxSprChnl/editWeixinXcxSprChnlManager.shtml")
	public ModelAndView editWeixinXcxSprChnlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/weixinXcxSpr/weixinXcxSprChnl/editWeixinXcxSprChnl");
		String weixinXcxSprChnlId = request.getParameter("weixinXcxSprChnlId");
		if(!StringUtil.isEmpty(weixinXcxSprChnlId)) {
			WeixinXcxSprChnl weixinXcxSprChnl = weixinXcxSprChnlService.selectByPrimaryKey(Integer.parseInt(weixinXcxSprChnlId));
			m.addObject("weixinXcxSprChnl", weixinXcxSprChnl);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title editWeixinXcxSprChnl   
	 * @Description TODO(编辑渠道)   
	 * @author pengl
	 * @date 2018年11月6日 下午2:11:48
	 */
	@RequestMapping("/weixinXcxSprChnl/editWeixinXcxSprChnl.shtml")
	public ModelAndView editWeixinXcxSprChnl(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/success/success");
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String weixinXcxSprChnlId = request.getParameter("weixinXcxSprChnlId");
			WeixinXcxSprChnl weixinXcxSprChnl = new WeixinXcxSprChnl();
			weixinXcxSprChnl.setSprChnlName(request.getParameter("sprChnlName"));
			weixinXcxSprChnl.setRemarks(request.getParameter("remarks"));
			if(!StringUtil.isEmpty(weixinXcxSprChnlId)) {
				weixinXcxSprChnl.setId(Integer.parseInt(weixinXcxSprChnlId));
				weixinXcxSprChnl.setUpdateBy(staffID);
				weixinXcxSprChnl.setUpdateDate(date);
				weixinXcxSprChnlService.updateByPrimaryKeySelective(weixinXcxSprChnl);
			}else {
				weixinXcxSprChnl.setCreateBy(staffID);
				weixinXcxSprChnl.setCreateDate(date);
				weixinXcxSprChnlService.insertSelective(weixinXcxSprChnl);
			}
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
	 * @Title validateSprChnlName   
	 * @Description TODO(渠道名称验证)   
	 * @author pengl
	 * @date 2018年11月6日 下午2:28:56
	 */
	@ResponseBody
	@RequestMapping("/weixinXcxSprChnl/validateSprChnlName.shtml")
	public Map<String, Object> validateSprChnlName(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "成功";
		boolean flag = true;
		try {
			String sprChnlName = request.getParameter("sprChnlName");
			String weixinXcxSprChnlId = request.getParameter("weixinXcxSprChnlId");
			if(!StringUtil.isEmpty(sprChnlName)) {
				WeixinXcxSprChnlExample weixinXcxSprChnlExample = new WeixinXcxSprChnlExample();
				WeixinXcxSprChnlExample.Criteria weixinXcxSprChnlCriteria = weixinXcxSprChnlExample.createCriteria();
				weixinXcxSprChnlCriteria.andDelFlagEqualTo("0").andSprChnlNameEqualTo(sprChnlName);
				if(!StringUtil.isEmpty(weixinXcxSprChnlId)) {
					weixinXcxSprChnlCriteria.andIdNotEqualTo(Integer.parseInt(weixinXcxSprChnlId));
				}
				List<WeixinXcxSprChnl> weixinXcxSprChnlsList = weixinXcxSprChnlService.selectByExample(weixinXcxSprChnlExample);
				if(weixinXcxSprChnlsList != null && weixinXcxSprChnlsList.size() > 0) {
					flag = false;
				}
			}else {
				code = "400";
				msg = "渠道名称为空";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "500";
			msg = "系统错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		map.put("flag", flag);
		return map;
	}
	
	/**
	 * 
	 * @Title delWeixinXcxSprChnl   
	 * @Description TODO(删除渠道)   
	 * @author pengl
	 * @date 2018年11月6日 下午2:38:24
	 */
	@ResponseBody
	@RequestMapping("/weixinXcxSprChnl/delWeixinXcxSprChnl.shtml")
	public Map<String, Object> delWeixinXcxSprChnl(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "成功";
		String weixinXcxSprChnlId = request.getParameter("weixinXcxSprChnlId");
		try {
			if(!StringUtil.isEmpty(weixinXcxSprChnlId)) {
				Date date = new Date();
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				WeixinXcxSprChnl weixinXcxSprChnl = new WeixinXcxSprChnl();
				weixinXcxSprChnl.setId(Integer.parseInt(weixinXcxSprChnlId));
				weixinXcxSprChnl.setDelFlag("1");
				weixinXcxSprChnl.setUpdateBy(staffID);
				weixinXcxSprChnl.setUpdateDate(date);
				weixinXcxSprChnlService.updateByPrimaryKeySelective(weixinXcxSprChnl);
			}else {
				code = "400";
				msg = "渠道ID为空";
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
	
	
	
}

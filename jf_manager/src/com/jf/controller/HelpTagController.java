package com.jf.controller;

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
import com.jf.entity.HelpItemExample;
import com.jf.entity.HelpTag;
import com.jf.entity.HelpTagExample;
import com.jf.entity.StateCode;
import com.jf.service.HelpItemService;
import com.jf.service.HelpTagService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class HelpTagController extends BaseController {

	@Autowired
	private HelpTagService helpTagService;
	
	@Autowired
	private HelpItemService helpItemService;
	
	/**
	 * 
	 * @Title helpTagManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午2:45:05
	 */
	@RequestMapping("/helpTag/helpTagManager.shtml")
	public ModelAndView helpTagManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/help/helpTag/getHelpTagList");
		return m;
	}
	
	/**
	 * 
	 * @Title getHelpTagList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午2:54:38
	 */
	@ResponseBody
	@RequestMapping("/helpTag/getHelpTagList.shtml")
	public Map<String, Object> getHelpTagList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<HelpTag> dataList = null;
		Integer totalCount = 0;
		try {
			HelpTagExample helpTagExample = new HelpTagExample();
			HelpTagExample.Criteria helpTagCriteria = helpTagExample.createCriteria();
			helpTagCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("helpTagName"))) {
				helpTagCriteria.andNameLike("%"+request.getParameter("helpTagName")+"%");
			}
			helpTagExample.setOrderByClause(" seq_no desc, id asc");
			helpTagExample.setLimitStart(page.getLimitStart());
			helpTagExample.setLimitSize(page.getLimitSize());
			totalCount = helpTagService.countByExample(helpTagExample);
			dataList = helpTagService.selectByExample(helpTagExample);
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
	 * @Title addOrUpdateHelpTagManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午3:01:21
	 */
	@RequestMapping("/helpTag/addOrUpdateHelpTagManager.shtml")
	public ModelAndView addOrUpdateHelpTagManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/help/helpTag/addOrUpdateHelpTag");
		String helpTagId = request.getParameter("helpTagId");
		if(!StringUtil.isEmpty(helpTagId)) {
			HelpTag helpTag = helpTagService.selectByPrimaryKey(Integer.parseInt(helpTagId));
			m.addObject("helpTag", helpTag);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateHelpTag   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午3:17:31
	 */
	@RequestMapping("/helpTag/addOrUpdateHelpTag.shtml")
	public ModelAndView addOrUpdateHelpTag(HttpServletRequest request, HelpTag helpTag) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			if(helpTag.getId() != null) {
				helpTag.setUpdateBy(Integer.parseInt(staffId));
				helpTag.setUpdateDate(date);
				helpTagService.updateByPrimaryKeySelective(helpTag);
			}else {
				helpTag.setCreateBy(Integer.parseInt(staffId));
				helpTag.setCreateDate(date);
				helpTagService.insertSelective(helpTag);
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
	 * 
	 * @Title helpItemCount   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午4:11:17
	 */
	@ResponseBody
	@RequestMapping("/helpTag/helpItemCount.shtml")
	public Map<String, Object> helpItemCount(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String helpTagId = request.getParameter("helpTagId");
			if(!StringUtil.isEmpty(helpTagId)) {
				HelpItemExample helpItemExample = new HelpItemExample();
				helpItemExample.createCriteria().andDelFlagEqualTo("0")
					.andHelpTagIdEqualTo(Integer.parseInt(helpTagId));
				int helpItemCount = helpItemService.countByExample(helpItemExample);
				map.put("helpItemCount", helpItemCount);
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
	
	/**
	 * 
	 * @Title delHelpTag   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午3:29:33
	 */
	@ResponseBody
	@RequestMapping("/helpTag/delHelpTag.shtml")
	public Map<String, Object> delHelpTag(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String helpTagId = request.getParameter("helpTagId");
			if(!StringUtil.isEmpty(helpTagId)) {
				HelpTag helpTag = new HelpTag();
				helpTag.setId(Integer.parseInt(helpTagId));
				helpTag.setUpdateBy(Integer.parseInt(staffID));
				helpTag.setUpdateDate(date);
				helpTag.setDelFlag("1");
				helpTagService.updateByPrimaryKeySelective(helpTag);
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
	
	/**
	 * 
	 * @Title updateSeqNo   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午3:53:33
	 */
	@ResponseBody
	@RequestMapping("/helpTag/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String helpTagId = request.getParameter("helpTagId");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(helpTagId)) {
				HelpTag helpTag = new HelpTag();
				helpTag.setId(Integer.parseInt(helpTagId));
				helpTag.setSeqNo(Integer.parseInt(seqNo));
				helpTagService.updateByPrimaryKeySelective(helpTag);
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
	
}

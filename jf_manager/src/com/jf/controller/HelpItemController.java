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
import com.jf.entity.HelpItem;
import com.jf.entity.HelpItemCustom;
import com.jf.entity.HelpItemCustomExample;
import com.jf.entity.HelpTag;
import com.jf.entity.HelpTagExample;
import com.jf.entity.StateCode;
import com.jf.service.HelpItemService;
import com.jf.service.HelpTagService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class HelpItemController extends BaseController {

	@Autowired
	private HelpItemService helpItemService;
	
	@Autowired
	private HelpTagService helpTagService;
	
	/**
	 * 
	 * @Title helpItemManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午5:20:28
	 */
	@RequestMapping("/helpItem/helpItemManager.shtml")
	public ModelAndView helpItemManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/help/helpItem/getHelpItemList");
		HelpTagExample helpTagExample = new HelpTagExample();
		helpTagExample.createCriteria().andDelFlagEqualTo("0");
		List<HelpTag> helpTagList = helpTagService.selectByExample(helpTagExample);
		m.addObject("helpTagList", helpTagList);
		return m;
	}
	
	/**
	 * 
	 * @Title getHelpTagList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午5:26:05
	 */
	@ResponseBody
	@RequestMapping("/helpItem/helpItemList.shtml")
	public Map<String, Object> getHelpTagList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<HelpItemCustom> dataList = null;
		Integer totalCount = 0;
		try {
			HelpItemCustomExample helpItemCustomExample = new HelpItemCustomExample();
			HelpItemCustomExample.Criteria helpItemCustomCriteria = helpItemCustomExample.createCriteria();
			helpItemCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("helpItemTitle"))) {
				helpItemCustomCriteria.andTitleLike("%"+request.getParameter("helpItemTitle")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("helpTagId"))) {
				helpItemCustomCriteria.andHelpTagIdEqualTo(Integer.parseInt(request.getParameter("helpTagId")));
			}
			helpItemCustomExample.setOrderByClause(" seq_no desc, id asc");
			helpItemCustomExample.setLimitStart(page.getLimitStart());
			helpItemCustomExample.setLimitSize(page.getLimitSize());
			totalCount = helpItemService.countByExample(helpItemCustomExample);
			dataList = helpItemService.selectByCustomExample(helpItemCustomExample);
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
	 * @Title addOrUpdateHelpItemManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午5:31:54
	 */
	@RequestMapping("/helpItem/addOrUpdateHelpItemManager.shtml")
	public ModelAndView addOrUpdateHelpItemManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/help/helpItem/addOrUpdateHelpItem");
		String helpItemId = request.getParameter("helpItemId");
		if(!StringUtil.isEmpty(helpItemId)) {
			HelpItem helpItem = helpItemService.selectByPrimaryKey(Integer.parseInt(helpItemId));
			m.addObject("helpItem", helpItem);
		}
		HelpTagExample helpTagExample = new HelpTagExample();
		helpTagExample.createCriteria().andDelFlagEqualTo("0");
		List<HelpTag> helpTagList = helpTagService.selectByExample(helpTagExample);
		m.addObject("helpTagList", helpTagList);
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateHelpItem   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午5:33:31
	 */
	@RequestMapping("/helpItem/addOrUpdateHelpItem.shtml")
	public ModelAndView addOrUpdateHelpItem(HttpServletRequest request, HelpItem helpItem) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			if(helpItem.getId() != null) {
				helpItem.setUpdateBy(Integer.parseInt(staffId));
				helpItem.setUpdateDate(date);
				helpItemService.updateByPrimaryKeySelective(helpItem);
			}else {
				helpItem.setCreateBy(Integer.parseInt(staffId));
				helpItem.setCreateDate(date);
				helpItemService.insertSelective(helpItem);
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
	 * @Title delHelpItem   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月1日 下午5:35:10
	 */
	@ResponseBody
	@RequestMapping("/helpItem/delHelpItem.shtml")
	public Map<String, Object> delHelpItem(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String helpItemId = request.getParameter("helpItemId");
			if(!StringUtil.isEmpty(helpItemId)) {
				HelpItem helpItem = new HelpItem();
				helpItem.setId(Integer.parseInt(helpItemId));
				helpItem.setUpdateBy(Integer.parseInt(staffID));
				helpItem.setUpdateDate(date);
				helpItem.setDelFlag("1");
				helpItemService.updateByPrimaryKeySelective(helpItem);
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
	 * @date 2018年6月1日 下午5:40:24
	 */
	@ResponseBody
	@RequestMapping("/helpItem/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String helpItemId = request.getParameter("helpItemId");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(helpItemId)) {
				HelpItem helpItem = new HelpItem();
				helpItem.setId(Integer.parseInt(helpItemId));
				helpItem.setSeqNo(Integer.parseInt(seqNo));
				helpItemService.updateByPrimaryKeySelective(helpItem);
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

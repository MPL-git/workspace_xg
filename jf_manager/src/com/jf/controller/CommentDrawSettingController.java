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
import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingCustomExample;
import com.jf.entity.CommentDrawSettingExample;
import com.jf.entity.StateCode;
import com.jf.service.CommentDrawSettingService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class CommentDrawSettingController extends BaseController {

	@Autowired
	private CommentDrawSettingService commentDrawSettingService;
	
	/**
	 * 
	 * @Title commentDrawSettingManager   
	 * @Description TODO(评价刮奖活动参数设置)   
	 * @author pengl
	 * @date 2018年6月26日 下午4:47:25
	 */
	@RequestMapping("/commentDrawSetting/commentDrawSettingManager.shtml")
	public ModelAndView commentDrawSettingManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/comment/commentDrawSetting/getCommentDrawSettingList");
		return m;
	}
	
	/**
	 * 
	 * @Title getCommentDrawSettingList   
	 * @Description TODO(评价刮奖活动参数设置)   
	 * @author pengl
	 * @date 2018年6月26日 下午5:05:35
	 */
	@ResponseBody
	@RequestMapping("/commentDrawSetting/getCommentDrawSettingList.shtml")
	public Map<String, Object> getCommentDrawSettingList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CommentDrawSetting> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CommentDrawSettingExample commentDrawSettingExample = new CommentDrawSettingExample();
			CommentDrawSettingExample.Criteria commentDrawSettingCriteria = commentDrawSettingExample.createCriteria();
			commentDrawSettingCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				commentDrawSettingCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				commentDrawSettingCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			commentDrawSettingExample.setOrderByClause(" id desc");
			commentDrawSettingExample.setLimitStart(page.getLimitStart());
			commentDrawSettingExample.setLimitSize(page.getLimitSize());
			totalCount = commentDrawSettingService.countByExample(commentDrawSettingExample);
			dataList = commentDrawSettingService.selectByExample(commentDrawSettingExample);
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
	 * @Title addOrUpdateCommentDrawSettingManager   
	 * @Description TODO(添加或修改)   
	 * @author pengl
	 * @date 2018年6月26日 下午5:09:15
	 */
	@RequestMapping("/commentDrawSetting/addOrUpdateCommentDrawSettingManager.shtml")
	public ModelAndView addOrUpdateCommentDrawSettingManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/comment/commentDrawSetting/addOrUpdateCommentDrawSetting");
		if(!StringUtil.isEmpty(request.getParameter("commentDrawSettingId"))) {
			CommentDrawSetting commentDrawSetting = commentDrawSettingService.selectByPrimaryKey(Integer.parseInt(request.getParameter("commentDrawSettingId")));
			m.addObject("commentDrawSetting", commentDrawSetting);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateCommentDrawSetting   
	 * @Description TODO(添加或修改)   
	 * @author pengl
	 * @date 2018年6月26日 下午5:15:07
	 */
	@RequestMapping("/commentDrawSetting/addOrUpdateCommentDrawSetting.shtml")
	public ModelAndView addOrUpdateCommentDrawSetting(HttpServletRequest request, CommentDrawSetting commentDrawSetting) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(commentDrawSetting.getId() != null) {
				commentDrawSetting.setBeginDate(sdf.parse(request.getParameter("beginDate")));
				commentDrawSetting.setEndDate(sdf.parse(request.getParameter("endDate")));
				commentDrawSetting.setUpdateBy(Integer.parseInt(staffId));
				commentDrawSetting.setUpdateDate(date);
				commentDrawSettingService.updateByPrimaryKeySelective(commentDrawSetting);
			}else {
				commentDrawSetting.setBeginDate(sdf.parse(request.getParameter("beginDate")));
				commentDrawSetting.setEndDate(sdf.parse(request.getParameter("endDate")));
				commentDrawSetting.setCreateBy(Integer.parseInt(staffId));
				commentDrawSetting.setCreateDate(date);
				commentDrawSetting.setDelFlag("0");
				commentDrawSettingService.insertSelective(commentDrawSetting);
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
	 * @Title validateDate   
	 * @Description TODO(验证时间不能重叠)   
	 * @author pengl
	 * @date 2018年6月27日 上午9:52:42
	 */
	@ResponseBody
	@RequestMapping("/commentDrawSetting/validateDate.shtml")
	public Map<String, Object> validateDate(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			CommentDrawSettingCustomExample commentDrawSettingCustomExample = new CommentDrawSettingCustomExample();
			CommentDrawSettingCustomExample.CommentDrawSettingCustomCriteria commentDrawSettingCustomCriteria = commentDrawSettingCustomExample.createCriteria();
			commentDrawSettingCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("id"))) {
				commentDrawSettingCustomCriteria.andIdNotEqualTo(Integer.parseInt(request.getParameter("id")));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginDate")) && !StringUtil.isEmpty(request.getParameter("endDate"))) {
				commentDrawSettingCustomCriteria.validateDate(request.getParameter("beginDate"), request.getParameter("endDate"));
			}
			int totalCount = commentDrawSettingService.countByCustomExample(commentDrawSettingCustomExample);
			if(totalCount > 0) {
				map.put("status", false);
			} else {
				map.put("status", true);
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
	 * @Title updateCommentDrawSetting   
	 * @Description TODO(上架或下架)   
	 * @author pengl
	 * @date 2018年6月26日 下午5:21:28
	 */
	@ResponseBody
	@RequestMapping("/commentDrawSetting/updateCommentDrawSetting.shtml")
	public Map<String, Object> updateCommentDrawSetting(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			if(!StringUtil.isEmpty(request.getParameter("commentDrawSettingId"))) {
				CommentDrawSetting commentDrawSetting = new CommentDrawSetting();
				commentDrawSetting.setId(Integer.parseInt(request.getParameter("commentDrawSettingId")));
				commentDrawSetting.setStatus(request.getParameter("status"));
				commentDrawSetting.setUpdateBy(Integer.parseInt(staffID));
				commentDrawSetting.setUpdateDate(date);
				commentDrawSettingService.updateByPrimaryKeySelective(commentDrawSetting);
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
	 * @Title delCommentDrawSetting   
	 * @Description TODO(删除)   
	 * @author pengl
	 * @date 2018年6月26日 下午5:22:23
	 */
	@ResponseBody
	@RequestMapping("/commentDrawSetting/delCommentDrawSetting.shtml")
	public Map<String, Object> delCommentDrawSetting(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			if(!StringUtil.isEmpty(request.getParameter("commentDrawSettingId"))) {
				CommentDrawSetting commentDrawSetting = new CommentDrawSetting();
				commentDrawSetting.setId(Integer.parseInt(request.getParameter("commentDrawSettingId")));
				commentDrawSetting.setUpdateBy(Integer.parseInt(staffID));
				commentDrawSetting.setUpdateDate(date);
				commentDrawSetting.setDelFlag("1");
				commentDrawSettingService.updateByPrimaryKeySelective(commentDrawSetting);
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

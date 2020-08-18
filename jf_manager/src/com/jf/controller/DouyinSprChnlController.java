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
import com.jf.entity.DouyinSprChnl;
import com.jf.entity.DouyinSprChnlCustom;
import com.jf.entity.DouyinSprChnlCustomExample;
import com.jf.entity.DouyinSprChnlExample;
import com.jf.entity.StateCode;
import com.jf.service.DouyinSprChnlService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class DouyinSprChnlController extends BaseController {

	@Autowired
	private DouyinSprChnlService douyinSprChnlService;
	
	/**
	 * 
	 * @MethodName: douyinSprChnlManager
	 * @Description: (渠道管理)
	 * @author Pengl
	 * @date 2019年5月15日 上午10:51:16
	 */
	@RequestMapping("/douyinSprChnl/douyinSprChnlManager.shtml")
	public ModelAndView douyinSprChnlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/douyin/douyinSprChnl/getDouyinSprChnlList");
		return m;
	}
	
	/**
	 * 
	 * @MethodName: douyinSprChnlList
	 * @Description: (渠道管理)
	 * @author Pengl
	 * @date 2019年5月15日 上午11:22:48
	 */
	@ResponseBody
	@RequestMapping("/douyinSprChnl/getDouyinSprChnlList.shtml")
	public Map<String, Object> getDouyinSprChnlList(HttpServletRequest request, Page page ) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<DouyinSprChnlCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beginCreateDate = request.getParameter("beginCreateDate");
			String endCreateDate = request.getParameter("endCreateDate");
			DouyinSprChnlCustomExample douyinSprChnlCustomExample = new DouyinSprChnlCustomExample();
			DouyinSprChnlCustomExample.DouyinSprChnlCustomCriteria douyinSprChnlCustomCriteria = douyinSprChnlCustomExample.createCriteria();
			douyinSprChnlCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("douyinSprChnlId")) ) {
				douyinSprChnlCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("douyinSprChnlId")));
			}
			if(!StringUtil.isEmpty(beginCreateDate)) {
				douyinSprChnlCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(beginCreateDate+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(endCreateDate)) {
				douyinSprChnlCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(endCreateDate+" 23:59:59"));
			}
			douyinSprChnlCustomExample.setOrderByClause(" id desc");
			douyinSprChnlCustomExample.setLimitStart(page.getLimitStart());
			douyinSprChnlCustomExample.setLimitSize(page.getLimitSize());
			totalCount = douyinSprChnlService.countByCustomExample(douyinSprChnlCustomExample);
			dataList = douyinSprChnlService.selectByCustomExample(douyinSprChnlCustomExample);
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
	 * @MethodName: editDouyinSprChnlManager
	 * @Description: (编辑渠道)
	 * @author Pengl
	 * @date 2019年5月15日 上午11:26:00
	 */
	@RequestMapping("/douyinSprChnl/editDouyinSprChnlManager.shtml")
	public ModelAndView editDouyinSprChnlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/douyin/douyinSprChnl/editDouyinSprChnl");
		String douyinSprChnlId = request.getParameter("douyinSprChnlId");
		if(!StringUtil.isEmpty(douyinSprChnlId)) {
			DouyinSprChnl douyinSprChnl = douyinSprChnlService.selectByPrimaryKey(Integer.parseInt(douyinSprChnlId));
			m.addObject("douyinSprChnl", douyinSprChnl);
		}
		return m;
	}
	
	/**
	 * 
	 * @MethodName: editDouyinSprChnl
	 * @Description: (编辑渠道)
	 * @author Pengl
	 * @date 2019年5月15日 上午11:28:20
	 */
	@RequestMapping("/douyinSprChnl/editDouyinSprChnl.shtml")
	public ModelAndView editDouyinSprChnl(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/success/success");
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String douyinSprChnlId = request.getParameter("douyinSprChnlId");
			DouyinSprChnl douyinSprChnl = new DouyinSprChnl();
			douyinSprChnl.setSprChnlName(request.getParameter("sprChnlName"));
			douyinSprChnl.setRemarks(request.getParameter("remarks"));
			if(!StringUtil.isEmpty(douyinSprChnlId)) {
				douyinSprChnl.setId(Integer.parseInt(douyinSprChnlId));
				douyinSprChnl.setUpdateBy(staffID);
				douyinSprChnl.setUpdateDate(date);
				douyinSprChnlService.updateByPrimaryKeySelective(douyinSprChnl);
			}else {
				douyinSprChnl.setCreateBy(staffID);
				douyinSprChnl.setCreateDate(date);
				douyinSprChnlService.insertSelective(douyinSprChnl);
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
	 * @MethodName: validateSprChnlName
	 * @Description: (渠道名称验证)
	 * @author Pengl
	 * @date 2019年5月15日 上午11:32:12
	 */
	@ResponseBody
	@RequestMapping("/douyinSprChnl/validateSprChnlName.shtml")
	public Map<String, Object> validateSprChnlName(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "成功";
		boolean flag = true;
		try {
			String sprChnlName = request.getParameter("sprChnlName");
			String douyinSprChnlId = request.getParameter("douyinSprChnlId");
			if(!StringUtil.isEmpty(sprChnlName)) {
				DouyinSprChnlExample douyinSprChnlExample = new DouyinSprChnlExample();
				DouyinSprChnlExample.Criteria douyinSprChnlCriteria = douyinSprChnlExample.createCriteria();
				douyinSprChnlCriteria.andDelFlagEqualTo("0").andSprChnlNameEqualTo(sprChnlName);
				if(!StringUtil.isEmpty(douyinSprChnlId)) {
					douyinSprChnlCriteria.andIdNotEqualTo(Integer.parseInt(douyinSprChnlId));
				}
				List<DouyinSprChnl> douyinSprChnlList = douyinSprChnlService.selectByExample(douyinSprChnlExample);
				if(douyinSprChnlList != null && douyinSprChnlList.size() > 0) {
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
	 * @MethodName: delDouyinSprChnl
	 * @Description: (删除渠道)
	 * @author Pengl
	 * @date 2019年5月15日 上午11:34:15
	 */
	@ResponseBody
	@RequestMapping("/douyinSprChnl/delDouyinSprChnl.shtml")
	public Map<String, Object> delDouyinSprChnl(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "成功";
		String douyinSprChnlId = request.getParameter("douyinSprChnlId");
		try {
			if(!StringUtil.isEmpty(douyinSprChnlId)) {
				Date date = new Date();
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				DouyinSprChnl douyinSprChnl = new DouyinSprChnl();
				douyinSprChnl.setId(Integer.parseInt(douyinSprChnlId));
				douyinSprChnl.setDelFlag("1");
				douyinSprChnl.setUpdateBy(staffID);
				douyinSprChnl.setUpdateDate(date);
				douyinSprChnlService.updateByPrimaryKeySelective(douyinSprChnl);
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

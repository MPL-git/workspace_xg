package com.jf.controller;

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
import com.jf.entity.ActivityAreaTempletPparam;
import com.jf.entity.ActivityAreaTempletPparamExample;
import com.jf.entity.StateCode;
import com.jf.service.ActivityAreaTempletPparamService;

/**
 * 
 * @ClassName ActivityAreaTempletPparamController
 * @Description TODO(这里用一句话描述这个方法的作用)
 * @author pengl
 * @date 2017年11月9日 上午10:30:27
 */
@SuppressWarnings("serial")
@Controller
public class ActivityAreaTempletPparamController extends BaseController {

	@Autowired
	private ActivityAreaTempletPparamService activityAreaTempletPparamService;
	
	/**
	 * 
	 * @Title activityAreaTempletPparamManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2017年11月9日 上午10:30:32
	 */
	@RequestMapping(value = "/activityAreaTempletPparam/activityAreaTempletPparamManager.shtml")
	public ModelAndView activityAreaTempletPparamManager(HttpServletRequest request, String flag) {
		ModelAndView m = new ModelAndView();
		if(flag.equals("1")) { //列表页面
			m.setViewName("/activityAreaTempletPparam/activityAreaTempletPparamList");
		}
		if(flag.equals("2")) { //修改页面
			m.setViewName("/activityAreaTempletPparam/updateActivityAreaTempletPparam");
			if(!StringUtil.isEmpty(request.getParameter("id"))) {
				ActivityAreaTempletPparamExample activityAreaTempletPparamExample = new ActivityAreaTempletPparamExample();
				activityAreaTempletPparamExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("id")));
				List<ActivityAreaTempletPparam> activityAreaTempletPparamList = activityAreaTempletPparamService.selectByExample(activityAreaTempletPparamExample);
				if(activityAreaTempletPparamList != null && activityAreaTempletPparamList.size() > 0) 
					m.addObject("activityAreaTempletPparam", activityAreaTempletPparamList.get(0));
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getActivityAreaTempletPparamList   
	 * @Description TODO(获取会场模板参数列表)   
	 * @author pengl
	 * @date 2017年11月9日 上午10:30:36
	 */
	@ResponseBody
	@RequestMapping(value = "/activityAreaTempletPparam/getActivityAreaTempletPparamList.shtml")
	public Map<String, Object> getActivityAreaTempletPparamList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount = 0;
		try {
			ActivityAreaTempletPparamExample activityAreaTempletPparamExample = new ActivityAreaTempletPparamExample();
			activityAreaTempletPparamExample.createCriteria().andDelFlagEqualTo("0");
			activityAreaTempletPparamExample.setOrderByClause(" id desc");
			dataList = activityAreaTempletPparamService.selectByExample(activityAreaTempletPparamExample);
			totalCount = activityAreaTempletPparamService.countByExample(activityAreaTempletPparamExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateActivityAreaTempletPparam   
	 * @Description TODO(修改会场模板参数)   
	 * @author pengl
	 * @date 2017年11月9日 上午10:31:19
	 */
	@RequestMapping(value = "/activityAreaTempletPparam/updateActivityAreaTempletPparam.shtml")
	public ModelAndView updateActivityAreaTempletPparam(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			if(!StringUtil.isEmpty(request.getParameter("id"))) {
				ActivityAreaTempletPparamExample activityAreaTempletPparamExample = new ActivityAreaTempletPparamExample();
				activityAreaTempletPparamExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("id")));
				ActivityAreaTempletPparam activityAreaTempletPparam = new ActivityAreaTempletPparam();
				if(!StringUtil.isEmpty(request.getParameter("activityAreaId")))
					activityAreaTempletPparam.setActivityAreaId(Integer.parseInt(request.getParameter("activityAreaId")));
				if(!StringUtil.isEmpty(request.getParameter("code")))
					activityAreaTempletPparam.setCode(request.getParameter("code"));
				activityAreaTempletPparam.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				activityAreaTempletPparam.setUpdateDate(new Date());
				activityAreaTempletPparamService.updateByExampleSelective(activityAreaTempletPparam, activityAreaTempletPparamExample);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}else {
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
}

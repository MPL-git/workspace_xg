package com.jf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgCustomExample;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysParamCfgExample.Criteria;
import com.jf.service.SysParamCfgService;
import com.jf.service.WebcommonService;
import com.jf.vo.Page;

@Controller
public class navigatiionController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
	private WebcommonService webcommonService;

	/**
	 * APP首页导航方案列表界面
	 */
	@RequestMapping(value = "/service/navigation/navigationIndex.shtml")
	public ModelAndView navigationIndex(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		String resPage = "/navigation/navigationIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", "SYS_PARAM_CFG");
		paramMap.put("COL_NAME", "STATUS");
		List<?> navigationStatus = webcommonService.sysStatusList(paramMap);
		resMap.put("navigationStatus", navigationStatus);
		return new ModelAndView(resPage, resMap);

	}

	/**
	 * \ APP首页导航方案数据列表
	 */
	@RequestMapping(value = "/service/navigation/navigationData.shtml")
	@ResponseBody
	public Map<String, Object> initData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysParamCfg> dataList = null;
		Integer totalCount = 0;
		String paramCode = "APP_HOME_NAVIGATION_PLAN";
		SysParamCfgCustomExample example = new SysParamCfgCustomExample();
		Criteria criteria = example.createCriteria();
		criteria.andParamCodeEqualTo(paramCode);
		try {
			dataList = sysParamCfgService.selectByExample(example);
			totalCount = sysParamCfgService.countByExample(example);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 修改编辑页面
	 * 
	 * @param sysParamCfg
	 */
	@RequestMapping(value = "/service/navigation/navigationEdit.shtml")
	public ModelAndView editNavigation(HttpServletRequest request,
			@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/navigation/navigationEdit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", "SYS_PARAM_CFG");
		paramMap.put("COL_NAME", "STATUS");
		if (!StringUtil.isEmpty(request.getParameter("paramId"))) {
			SysParamCfg sysParamCfg = sysParamCfgService
					.selectByPrimaryKey(Integer.valueOf(request
							.getParameter("paramId")));
			resMap.put("sysParamCfg", sysParamCfg);

		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 保存数据
	 */
	@ResponseBody
	@RequestMapping(value = "/service/navigation/savenavigation.shtml")
	public Map<String, Object> navigationSave(HttpServletRequest request,
			SysParamCfg sysParamCfg) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		SysParamCfgExample example = new SysParamCfgExample();
		Criteria criteria = example.createCriteria();
		criteria.andParamIdEqualTo(Integer.valueOf(request
				.getParameter("paramId")));
		try {
			if (request.getParameter("paramId") != null) {
				sysParamCfg.setParamValue(request.getParameter("paramValue"));
				sysParamCfg.setParamOrder(request.getParameter("paramOrder"));
				sysParamCfgService.updateByExampleSelective(sysParamCfg,
						example);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
}

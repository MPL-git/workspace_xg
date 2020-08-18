package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgCustomExample;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysParamCfgExample.Criteria;
import com.jf.service.SysParamCfgService;
import com.jf.service.WebcommonService;
import com.jf.vo.Page;

@Controller
public class ImageStarController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
	private WebcommonService webcommonService;

	/**
	 * APP启动图列表界面
	 */
	@RequestMapping(value = "/service/imageStar/imageStarIndex.shtml")
	public ModelAndView navigationIndex(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		String resPage = "/imageStar/imageStarIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", "SYS_PARAM_CFG");
		paramMap.put("COL_NAME", "STATUS");
		List<?> imageStarStatus = webcommonService.sysStatusList(paramMap);
		resMap.put("imageStarStatus", imageStarStatus);
		return new ModelAndView(resPage, resMap);

	}

	/**
	 * \ APP启动图数据列表
	 */
	@RequestMapping(value = "/service/imageStar/imageStarData.shtml")
	@ResponseBody
	public Map<String, Object> initData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysParamCfg> dataList = null;
		Integer totalCount = 0;
		List<String> list=new ArrayList<String>();
		String paramCode1="APP_START_IMAGE_ANDROID";
		String paramCode2="APP_START_IMAGE_IOS";
		list.add(paramCode1);
		list.add(paramCode2);
		//String paramCode = "APP_HOME_NAVIGATION_PLAN";
		SysParamCfgCustomExample example = new SysParamCfgCustomExample();
		Criteria criteria = example.createCriteria();
		criteria.andParamCodeIn(list);
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

/*	*//**
	 * 启动图修改页面
	 * 
	 * @param sysParamCfg
	 */
	@RequestMapping(value = "/service/imageStar/imageStarEdit.shtml")
	public ModelAndView editImageStar(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/imageStar/imageStarEdit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", "SYS_PARAM_CFG");
		paramMap.put("COL_NAME", "STATUS");
		if (!StringUtil.isEmpty(request.getParameter("paramId"))) {
			SysParamCfg sysParamCfg = sysParamCfgService.selectByPrimaryKey(Integer.valueOf(request.getParameter("paramId")));
			resMap.put("sysParamCfg", sysParamCfg);

		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 保存数据
	 */
	@ResponseBody
	@RequestMapping(value = "/service/imageStar/editImageStarSave.shtml")
	public Map<String,Object> imageStarSave(HttpServletRequest request,SysParamCfg sysParamCfg,String logo ) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		SysParamCfgExample SysParamCfgExample=new SysParamCfgExample();
		try {
			sysParamCfg.setParamValue(logo);
			sysParamCfgService.updateByPrimaryKeySelective(sysParamCfg);
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


package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.TreeUtils;
import com.jf.entity.ResultBean;
import com.jf.entity.StateCode;
import com.jf.service.BottomService;


@Controller
public class BottomController extends BaseController{
	@Resource
	private BottomService bottomFacade;
	@RequestMapping(value = "/service/bottom/index.shtml")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/bottom/index";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> selectBottom = bottomFacade.selectBottom(new HashMap<String, Object>());
			resMap.put("bottom", JSONArray.fromObject(TreeUtils.buildTree(selectBottom, "0")).toString());
			
		} catch (Exception e) {
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/service/bottom/edit.shtml")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/bottom/edit";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> selectBottom = bottomFacade.selectBottom(paramMap);
			for(Object obj:selectBottom){
				Map bottomMap=(Map)obj;
				String bottomId=StringUtil.valueOf(bottomMap.get("id"));
				String bottomName=StringUtil.valueOf(bottomMap.get("text"));
				String bottomCon=StringUtil.valueOf(bottomMap.get("BOTTOM_CONTENT"));
				resMap.put("BOTTOM_ID", bottomId);
				resMap.put("BOTTOM_NAME", bottomName);
				resMap.put("BOTTOM_CONTENT", bottomCon);
			}
		} catch (Exception e) {
		}
		return new ModelAndView(rtPage,resMap);
	}
	@RequestMapping(value = "/service/bottom/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			//参数不能为空验证
			String [] paramStr={"BOTTOM_CONTENT:栏目内容,BOTTOM_ID:栏目ID"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				//return new ModelAndView(rtPage,resMap);
				return resMap;
			}
				bottomFacade.updateBottom(paramMap);
			
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

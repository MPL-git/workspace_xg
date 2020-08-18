package com.jf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.service.WebcommonService;


@Controller
public class WebcommonController extends BaseController{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private WebcommonService webcommonService;
	 
	@RequestMapping(value = "/webcommon/areadata.shtml")
	@ResponseBody
	public List<Map<String, Object>> areadata(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {	
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		if(paramMap.get("id") == null || "".equals(paramMap.get("id"))) return new ArrayList<Map<String, Object>>();
		resMap.put("id", paramMap.get("id"));
		List areadataList = webcommonService.selectAreaLd(resMap);
	  	return areadataList; 
	}    
	
	@RequestMapping(value = "/webcommon/prodata.shtml")
	@ResponseBody
	public List<Map<String, Object>> prodata(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {	
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.putAll(paramMap);
		List prodataList = webcommonService.selectProLd();
	  	return prodataList; 
	}  

}

package com.jf.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.service.FreightTemplateService;
import com.jf.service.ProvinceFreightService;

import net.sf.json.JSONObject;

@Controller
public class FreightController extends BaseController{
	@Resource
	private FreightTemplateService freightTemplateService;
	@Resource
	private ProvinceFreightService provinceFreightService;
	/**
	 * 获取商品运费模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductFreightTemplate")
	@ResponseBody
	public ResponseMsg getProductFreightTemplate(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
		
			Map<String, Object> map = freightTemplateService.getProductFreightTemplate(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
}

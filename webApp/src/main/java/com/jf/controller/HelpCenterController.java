package com.jf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.entity.HelpItem;
import com.jf.entity.HelpTag;
import com.jf.service.HelpTagService;

import net.sf.json.JSONObject;

@Controller
public class HelpCenterController extends BaseController{
	
	@Resource
	private HelpTagService helpTagService;
	
	/**
	 * 
	 * 方法描述 ：获取帮助标签
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月10日 下午3:08:25 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getHelpTagList")
	@ResponseBody
	public ResponseMsg getHelpTagList(HttpServletRequest request){
		try {
			List<HelpTag> helpTags = helpTagService.getHelpTagList();
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,helpTags);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取帮助标题
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月10日 下午3:08:25 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getHelpItemList")
	@ResponseBody
	public ResponseMsg getHelpItemList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"helpTagId"};
			this.requiredStr(obj,request);
			Integer helpTagId = reqDataJson.getInt("helpTagId");
			List<HelpItem> helpItems = helpTagService.getHelpItemList(helpTagId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,helpItems);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：搜索帮助标题
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月10日 下午3:08:25 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/searchHelpItemList")
	@ResponseBody
	public ResponseMsg searchHelpItemList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"helpName"};
			this.requiredStr(obj,request);
			
			String helpName = reqDataJson.getString("helpName");
			List<HelpItem> helpItems = helpTagService.searchHelpItemList(helpName);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,helpItems);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}

package com.jf.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.controller.base.BaseController;
import com.jf.service.InterventionOrderService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:32:18 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class InterventionOrderController extends BaseController{
	@Resource
	private InterventionOrderService interventionOrderService;
	
	/**
	 * 
	 * 方法描述 ：新增平台介入单
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月3日 上午11:11:13 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addInterventionOrder")
	@ResponseBody
	public ResponseMsg addInterventionOrder(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"serviceOrderId","memberId","reason","contacts","tel","message"};
			this.requiredStr(obj,request);
			interventionOrderService.addInterventionOrderProcess(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：修改介入单
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月9日 下午6:29:27 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateInterventionOrder")
	@ResponseBody
	public ResponseMsg updateInterventionOrder(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"interventionOrderId","memberId","reason","contacts","tel","message"};
			this.requiredStr(obj,request);
			interventionOrderService.updateInterventionOrderProcess(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：撤销介入单
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月9日 下午6:29:38 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateInterventionOrderByCancel")
	@ResponseBody
	public ResponseMsg updateInterventionOrderByCancel(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"interventionOrderId"};
			this.requiredStr(obj,request);
			interventionOrderService.updateInterventionOrderByCancel(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：介入详情
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月3日 下午4:01:01 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getInterventionOrderList")
	@ResponseBody
	public ResponseMsg getInterventionOrderList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"interventionOrderId"};
			this.requiredStr(obj,request);
			Map<String,Object> map = interventionOrderService.getInterventionOrderList(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：留言列表
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月4日 上午9:30:17 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getInterventionDetailMessageList")
	@ResponseBody
	public ResponseMsg getInterventionDetailMessageList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"interventionOrderId","currentPage"};
			this.requiredStr(obj,request);
			Map<String,Object> map = interventionOrderService.getInterventionDetailMessageList(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,Const.RETURN_SIZE_10,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：新增用户留言
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月4日 上午9:52:25 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addProcessMsg")
	@ResponseBody
	public ResponseMsg addProcessMsg(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"interventionOrderId","memberId","message"};
			this.requiredStr(obj,request);
			interventionOrderService.addProcessMsg(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
}

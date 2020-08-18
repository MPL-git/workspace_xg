package com.jf.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.controller.base.BaseController;
import com.jf.service.SobotCustomerServiceService;
import com.jf.service.XiaonengCustomerServiceService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月27日 下午2:10:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class SobotCustomerServiceController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(SobotCustomerServiceController.class);
	@Resource
	private SobotCustomerServiceService sobotCustomerServiceService;
	@Resource
	private XiaonengCustomerServiceService xiaonengCustomerServiceService;
	/**
	 * 
	 * 方法描述 ：获取客服信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月27日 下午2:12:51 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getSobotInfo")
	@ResponseBody
	public ResponseMsg getSobotInfo(HttpServletRequest request){
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		try {
			Object[] obj = {"type","id"};
			this.requiredStr(obj,request);
			String type = reqDataJson.getString("type");//客服入口 1 商品详情 2订单详情3平台客服
			Integer id = reqDataJson.getInt("id");//业务id 商品id 子订单iD 客服指定id
			String customerServiceSoftType = "1";//兼容旧版本，初始化为1
			if(reqDataJson.containsKey("customerServiceSoftType")){
				customerServiceSoftType = reqDataJson.getString("customerServiceSoftType");
			}
			//查询需要调用哪个客服软件 1：智齿  2：消能
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("customerServiceSoftType", customerServiceSoftType);
			if(customerServiceSoftType.equals("1")){
				map = sobotCustomerServiceService.getSobotInfoProduct(id,type,map);
			}else if(customerServiceSoftType.equals("2")){
				map = xiaonengCustomerServiceService.getXiaoNengInfo(id,type,map);	
			}
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			logger.info("聚买客服idA"+reqDataJson.getInt("id"));
			logger.info("聚买客服类型B"+reqDataJson.getString("type"));
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/n/getProductBasicsInfo")
	@ResponseBody
	public Map<String, Object> getProductBasicsInfo(HttpServletRequest request){
		Map<String,Object> map = null;
		try {
			String code = request.getParameter("itemid");
			
			map = sobotCustomerServiceService.getProductBasicsInfo(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}

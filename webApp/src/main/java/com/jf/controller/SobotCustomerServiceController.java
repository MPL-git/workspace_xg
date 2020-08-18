package com.jf.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.service.SobotCustomerServiceService;

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
	
	@Resource
	private SobotCustomerServiceService sobotCustomerServiceService;
	
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
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"type","id"};
			this.requiredStr(obj,request);
			//客服入口 1 商品详情 2订单详情3平台客服
			String type = reqDataJson.getString("type");
			//业务id 商品id 子订单iD 客服指定id
			Integer id = reqDataJson.getInt("id");
			
			Map<String,Object> map = sobotCustomerServiceService.getSobotInfoProduct(id,type);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}

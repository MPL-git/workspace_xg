package com.jf.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.OrderDtl;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.OrderDtlService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月28日 下午3:44:32 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class CustomerServiceOrderController extends BaseController{
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	@Resource
	private OrderDtlService orderDtlService;
	
	/**
	 * 
	 * 方法描述 ：获取售后信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月28日 下午3:54:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getCustomerServiceInfo")
	@ResponseBody
	public ResponseMsg getCustomerServiceInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"serviceOrderId","serviceType"};
			this.requiredStr(obj,request);
			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");//售后id
			String serviceType = reqDataJson.getString("serviceType");//售后类型
			BigDecimal payAmount = new BigDecimal(0);
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.findListById(serviceOrderId);
			if(!serviceType.equals(Const.CUSTOMER_ORDER_TYPE_C)){
				OrderDtl orderDtl = orderDtlService.findModelById(customerServiceOrder.getOrderDtlId());
				payAmount = orderDtl.getPayAmount();
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("payAmount", payAmount);
				
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}

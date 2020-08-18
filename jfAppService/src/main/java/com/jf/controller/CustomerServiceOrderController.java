package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.OrderDtl;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.OrderDtlService;
import com.jf.service.SubDepositOrderService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Resource
	private SubDepositOrderService subDepositOrderService;
	
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
	
	/**
	 * 
	 * 方法描述 ：获取部分售后金额
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月28日 下午3:54:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getCustomerServiceAmount")
	@ResponseBody
	public ResponseMsg getCustomerServiceAmount(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"orderDtlId"};
			this.requiredStr(obj,request);
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			BigDecimal amount = new BigDecimal("0");
			String serviceType = "A";//没有传就是退款的操作
			if(reqDataJson.containsKey("serviceType") && !StringUtil.isBlank(reqDataJson.getString("serviceType"))){
				serviceType = reqDataJson.getString("serviceType");
				if("退款单".equals(serviceType) || "A".equals(serviceType)){
					serviceType = "A";
				}else if("退货单".equals(serviceType) || "B".equals(serviceType)){
					serviceType = "B";
				}else if("换货单".equals(serviceType) || "C".equals(serviceType)){
					serviceType = "C";
				}else{
					throw new ArgException("请填写售后类型!");
				}
			}
			Integer orderDtlId = reqDataJson.getInt("orderDtlId");
			if(system.equals(Const.IOS) && version <= 55){
				CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
				customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0");
				List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(customerServiceOrderExample);
				if(CollectionUtils.isNotEmpty(customerServiceOrders) && StringUtil.isBlank(serviceType)){
					serviceType = customerServiceOrders.get(0).getServiceType();
				}
			}
			if(!"C".equals(serviceType)){
				Map<String, BigDecimal> map = customerServiceOrderService.getCustomerServiceAmount(reqDataJson.getInt("orderDtlId"),reqDataJson.getInt("quantity"),null,serviceType,null);
				amount = map.get("amount");
			}
				
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,amount);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
}

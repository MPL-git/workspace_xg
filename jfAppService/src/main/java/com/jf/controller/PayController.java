package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineOrder;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import com.jf.service.CombineDepositOrderService;
import com.jf.service.CombineOrderService;
import com.jf.service.RefundOrderService;
import com.jf.service.SysPaymentService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月21日 下午5:02:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class PayController extends BaseController{
	
	@Resource
	private SysPaymentService sysPaymentService;
	
	@Resource
	private CombineOrderService combineOrderService;
	@Resource
	private RefundOrderService refundOrderService;
	@Resource
	private CombineDepositOrderService combineDepositOrderService;
	/**
	 * 
	 * 方法描述 ：获取支付方式
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月21日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getPayType")
	@ResponseBody
	public ResponseMsg getPayType(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer combineOrderId = null;
			Integer combineDepositOrderId = null;
			String paymentId = "";
			if(reqDataJson.containsKey("combineOrderId") && !StringUtil.isBlank(reqDataJson.getString("combineOrderId"))){
				combineOrderId = reqDataJson.getInt("combineOrderId");
				CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(combineOrderId);
				paymentId = combineOrder.getPaymentId().toString();
			}else if(reqDataJson.containsKey("combineDepositOrderId") && !StringUtil.isBlank(reqDataJson.getString("combineDepositOrderId"))){
				combineDepositOrderId = reqDataJson.getInt("combineDepositOrderId");
				CombineDepositOrder combineDepositOrder = combineDepositOrderService.selectByPrimaryKey(combineDepositOrderId);
				paymentId = combineDepositOrder.getPaymentId().toString();
			}
			// 支付方式
			List<Map<String, Object>> payMapList = sysPaymentService.getPayMethod(paymentId,reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,payMapList);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	
	/**
	 * 
	 * 方法描述 ：获取支付方式
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月21日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getRefundInfo")
	@ResponseBody
	public ResponseMsg getRefundInfo(HttpServletRequest request){
		try {
			RefundOrderExample refundOrderExample = new RefundOrderExample();
			refundOrderExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("6");
			List<RefundOrder> refundOrders = refundOrderService.selectByExample(refundOrderExample);
			if(CollectionUtils.isNotEmpty(refundOrders)){
				for (RefundOrder refundOrder : refundOrders) {
					CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(refundOrder.getCombineOrderId());
					
					if(combineOrder.getPaymentId().toString().equals("1")){
						//支付宝
						refundOrderService.getZfbRefundQuery(refundOrder,combineOrder);
					}else if(combineOrder.getPaymentId().toString().equals("2")){
						refundOrderService.getWxRefundQuery(refundOrder,combineOrder);
					}
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
}

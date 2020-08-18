package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineOrder;
import com.jf.service.CombineDepositOrderService;
import com.jf.service.CombineOrderService;
import com.jf.service.SysPaymentService;

import net.sf.json.JSONObject;

/**
 * @author chenwf:
 * @date 创建时间：2017年6月21日 下午5:02:43
 * @version 1.0
 * @parameter
 * @return
 */
@Controller
public class PayController extends BaseController {

	@Resource
	private SysPaymentService sysPaymentService;

	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private CombineDepositOrderService combineDepositOrderService;

	/**
	 * 
	 * 方法描述 ：获取支付方式
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年6月21日 下午5:03:20
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getPayType")
	@ResponseBody
	public ResponseMsg getPayType(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer combineOrderId = null;
			Integer combineDepositOrderId = null;
			String paymentId = "";
			String type = "";
			if(!JsonUtils.isEmpty(reqDataJson, "type")){//1店长权益声明
				type = "1";
			}
			String statement = "";
			if(reqDataJson.containsKey("combineOrderId") && !StringUtil.isBlank(reqDataJson.getString("combineOrderId"))){
				combineOrderId = reqDataJson.getInt("combineOrderId");
				CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(combineOrderId);
				paymentId = combineOrder.getPaymentId().toString();
			}else if(reqDataJson.containsKey("combineDepositOrderId") && !StringUtil.isBlank(reqDataJson.getString("combineDepositOrderId"))){
				combineDepositOrderId = reqDataJson.getInt("combineDepositOrderId");
				CombineDepositOrder combineDepositOrder = combineDepositOrderService.selectByPrimaryKey(combineDepositOrderId);
				paymentId = combineDepositOrder.getPaymentId().toString();
			}
			if("1".equals(type)){
				statement = "店长权益服务一经开通不支持取消/退款，敬请谅解";
			}
			List<Map<String, Object>> payMapList = sysPaymentService.getPayMethod(paymentId,reqPRM);
			Map<String, Object> map = new HashMap<>();
			map.put("payMapList", payMapList);
			map.put("statement", statement);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}
}

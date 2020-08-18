package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.controller.base.BaseController;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import com.jf.entity.TmpOrderDtl;
import com.jf.entity.TmpOrderDtlExample;
import com.jf.entity.TmpSubOrder;
import com.jf.entity.TmpSubOrderExample;
import com.jf.service.OrderDtlService;
import com.jf.service.SubOrderService;
import com.jf.service.TmpOrderDtlService;
import com.jf.service.TmpSubOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProcessingDataController extends BaseController{
	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private TmpOrderDtlService tmpOrderDtlService;
	@Resource
	private TmpSubOrderService tmpSubOrderService;
	
	@RequestMapping(value = "/api2/n/getCombineOrderData")
	@ResponseBody
	public ResponseMsg getCombineOrderData(HttpServletRequest request) {
		try {
//			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
//			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

//			Object[] obj = { "memberId","productItemId","productId","quantity"};
//			this.requiredStr(obj, request);
			
			Integer cid = null;
//			if(reqDataJson.containsKey("combineOrderId") && !StringUtil.isBlank(reqDataJson.getString("combineOrderId"))){
//				cid = reqDataJson.getInt("combineOrderId");
//			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cid", cid);
			List<OrderDtlCustom> customs = orderDtlService.getCombineOrderData(map);
			if(CollectionUtils.isNotEmpty(customs)){
				updateOrderData(customs);
			}
			
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	public void updateOrderData(List<OrderDtlCustom> customs) {
		for (OrderDtlCustom dtl : customs) {
			int i = 0;
			BigDecimal taotalPayAmountAdd = new BigDecimal("0");//每只商品的实付金额相加
			List<Integer> subOrderList = new ArrayList<Integer>();
			Integer coId = dtl.getCombineOrderId();
			BigDecimal combinePayAmount = dtl.getCombinePayAmount();//总实付金额
			BigDecimal combineAmount = dtl.getCombineAmount();//总销售金额
			BigDecimal money = dtl.getMoney();
			BigDecimal combineIntegralAmount = dtl.getCombineIntegralAmount();
			BigDecimal afterPlatPayMoney = combinePayAmount.add(money).add(combineIntegralAmount);//+上平台优惠券，重新分配
			BigDecimal platformAmount = new BigDecimal("0");
			BigDecimal platformAmountAdd = new BigDecimal("0");
			BigDecimal integralAmount = new BigDecimal("0");
			BigDecimal integralAmountAdd = new BigDecimal("0");
			SubOrderExample subOrderExample = new SubOrderExample();
			subOrderExample.createCriteria().andCombineOrderIdEqualTo(coId);
			List<SubOrder> subOrders = subOrderService.selectByExample(subOrderExample);
			for (SubOrder subOrder : subOrders) {
				subOrderList.add(subOrder.getId());
			}
			OrderDtlExample orderDtlExample = new OrderDtlExample();
			orderDtlExample.createCriteria().andSubOrderIdIn(subOrderList);
			List<OrderDtl> dtls = orderDtlService.selectByExample(orderDtlExample);
			for (OrderDtl orderDtl : dtls) {
				TmpOrderDtlExample dtlExample = new TmpOrderDtlExample();
				dtlExample.createCriteria().andOrderDtlIdEqualTo(orderDtl.getId());
				int count = tmpOrderDtlService.countByExample(dtlExample);
				if(count > 0){
					continue;
				}
				i++;
				if (dtls.size() == i) {
					platformAmount = money.subtract(platformAmountAdd);
					integralAmount = combineIntegralAmount.subtract(integralAmountAdd);
				} else {
					//商品的优惠金额
					platformAmount = (orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity()))
							.subtract(orderDtl.getMchtPreferential())).multiply(money).divide(afterPlatPayMoney, 2, BigDecimal.ROUND_HALF_UP);
					
					integralAmount = orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity()));
					integralAmount = integralAmount.subtract(orderDtl.getMchtPreferential()).subtract(platformAmount);
					integralAmount = integralAmount.multiply(combineIntegralAmount).divide(afterPlatPayMoney.subtract(money), 2, BigDecimal.ROUND_HALF_UP);
				}
				platformAmountAdd = platformAmountAdd.add(platformAmount);
				integralAmountAdd = integralAmountAdd.add(integralAmount);
				BigDecimal payAmount = integralAmount.add(orderDtl.getMchtPreferential()).add(platformAmount);
				TmpOrderDtl tmpOrderDtl = new TmpOrderDtl();
				tmpOrderDtl.setOrderDtlId(orderDtl.getId());
				if(dtls.size() == i){
					//最后一轮用总的实付金额-每只商品的实付金额 = 最后一只商品的实付金额
					tmpOrderDtl.setPayAmount(combinePayAmount.subtract(taotalPayAmountAdd));
				}else{
					taotalPayAmountAdd = taotalPayAmountAdd.add(orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity())).subtract(payAmount));
					tmpOrderDtl.setPayAmount(orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity())).subtract(payAmount));
				}
				
				tmpOrderDtl.setPlatAmount(platformAmount);
				tmpOrderDtl.setIntegralAmount(integralAmount);
				tmpOrderDtl.setSubOrderId(orderDtl.getSubOrderId());
				tmpOrderDtl.setCombineOrderId(coId);
				tmpOrderDtlService.insertSelective(tmpOrderDtl);
				
				TmpSubOrderExample tmpSubOrderExample = new TmpSubOrderExample();
				tmpSubOrderExample.createCriteria().andSubOrderIdEqualTo(orderDtl.getSubOrderId());
				List<TmpSubOrder> tmpSubOrders = tmpSubOrderService.selectByExample(tmpSubOrderExample);
				TmpSubOrder ts = null;
				if(CollectionUtils.isNotEmpty(tmpSubOrders)){
					ts = tmpSubOrders.get(0);
					ts.setPayAmount(ts.getPayAmount().add(tmpOrderDtl.getPayAmount()));
					ts.setPlatAmount(ts.getPlatAmount().add(tmpOrderDtl.getPlatAmount()));
					ts.setIntegralAmount(ts.getIntegralAmount().add(tmpOrderDtl.getIntegralAmount()));
					tmpSubOrderService.updateBySubOrderId(ts);
				}else{
					ts = new TmpSubOrder();
					ts.setSubOrderId(orderDtl.getSubOrderId());
					ts.setPayAmount(tmpOrderDtl.getPayAmount());
					ts.setPlatAmount(tmpOrderDtl.getPlatAmount());
					ts.setIntegralAmount(integralAmount);
					ts.setCombineOrderId(coId);
					tmpSubOrderService.insertSelective(ts);
				}
			}
		}
	}
}

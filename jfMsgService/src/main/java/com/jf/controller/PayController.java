package com.jf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.CombineOrder;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import com.jf.service.CombineOrderService;
import com.jf.service.RefundOrderService;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月21日 下午5:02:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class PayController{
	
	@Resource
	private CombineOrderService combineOrderService;
	@Resource
	private RefundOrderService refundOrderService;
	
	@RequestMapping(value = "/app/getRefundInfo")
	@ResponseBody
	public ResponseMsg getRefundInfo(HttpServletRequest request){
		try {
			String payType = request.getParameter("payType");
			RefundOrderExample refundOrderExample = new RefundOrderExample();
			RefundOrderExample.Criteria criteria = refundOrderExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andStatusEqualTo("6");
			if(!StringUtil.isBlank(payType)){
				criteria.andRefundCodeLike(payType+"%");
			}
			List<RefundOrder> refundOrders = refundOrderService.selectByExample(refundOrderExample);
			if(CollectionUtils.isNotEmpty(refundOrders)){
				for (RefundOrder refundOrder : refundOrders) {
					System.out.println(refundOrder.getCombineOrderId());
					CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(refundOrder.getCombineOrderId());
					
					if(combineOrder.getPaymentId() == 1 
							|| combineOrder.getPaymentId() == 6){
						//支付宝
						refundOrderService.getZfbRefundQuery(refundOrder,combineOrder);
					}else if(combineOrder.getPaymentId() == 2 
							|| combineOrder.getPaymentId() == 4 
									||combineOrder.getPaymentId() == 5){
						refundOrderService.getWxRefundQuery(refundOrder,combineOrder);
					}
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
}

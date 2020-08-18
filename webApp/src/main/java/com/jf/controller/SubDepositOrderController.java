package com.jf.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderExample;
import com.jf.service.CombineDepositOrderService;
import com.jf.service.DepositOrderStatusLogService;
import com.jf.service.ProductItemService;
import com.jf.service.SubDepositOrderService;

import net.sf.json.JSONObject;

@Controller
public class SubDepositOrderController extends BaseController {

	@Autowired
	private SubDepositOrderService subDepositOrderService;
	
	@Autowired
	private DepositOrderStatusLogService depositOrderStatusLogService;
	
	@Autowired
	private CombineDepositOrderService combineDepositOrderService;
	
	@Autowired
	private ProductItemService productItemService;
	
	/**
	 * 
	 * @Title getSubDepositOrderList   
	 * @Description TODO(预售定金列表)   
	 * @author pengl
	 * @date 2018年11月10日 下午8:49:28
	 */
	@RequestMapping(value = "/api/y/getSubDepositOrderList")
	@ResponseBody
	public ResponseMsg getSubDepositOrderList(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
			List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
			String memberId = getMemberIdStr(request);//会员标识id
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = reqDataJson.getInt("currentPage");//页码
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("memberId", memberId);
			paramMap.put("subDepositOrderStatusList", Arrays.asList("1", "2", "8"));
			paramMap.put("groupBy", "combine_deposit_order_id"); //分组
			paramMap.put("currentPage", currentPage*pageSize);
			paramMap.put("pageSize", pageSize);
			List<SubDepositOrderCustom> subDepositOrderCustomList = subDepositOrderService.getSubDepositOrderList(paramMap);
			for(SubDepositOrderCustom subDepositOrderCustom : subDepositOrderCustomList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("combineDepositOrderId", subDepositOrderCustom.getCombineDepositOrderId());
				dataMap.put("subDepositOrderCode", subDepositOrderCustom.getSubDepositOrderCode());
				dataMap.put("productId", subDepositOrderCustom.getProductId());
				dataMap.put("productItemId", subDepositOrderCustom.getProductItemId());
				dataMap.put("skuPic", StringUtil.getPic(subDepositOrderCustom.getSkuPic(), "s"));
				dataMap.put("productPropDesc", subDepositOrderCustom.getProductPropDesc());
				dataMap.put("productName", subDepositOrderCustom.getProductName());
				dataMap.put("salePrice", subDepositOrderCustom.getSalePriceSum());
				dataMap.put("deposit", subDepositOrderCustom.getDepositSum());
				dataMap.put("deductAmount", subDepositOrderCustom.getDeductAmountSum());
				dataMap.put("quantity", subDepositOrderCustom.getQuantitySum());
				//尾款显示时间
				String endPaymentDateStr = "";
				if("1".equals(subDepositOrderCustom.getStatus())) {
					dataMap.put("cancelButton", true);
					dataMap.put("titleStr", "定金等待买家付款");
				}else {
					dataMap.put("cancelButton", false);
					dataMap.put("titleStr", "尾款等待买家付款");
					if(subDepositOrderCustom.getActivityBeginTime().compareTo(date) <= 0 ) {
						endPaymentDateStr = sdf.format(subDepositOrderCustom.getActivityEndTime())+"结束尾款支付";
					}else {
						endPaymentDateStr = sdf.format(subDepositOrderCustom.getActivityBeginTime())+"开始尾款支付";
					}
				}
				dataMap.put("endPaymentDateStr", endPaymentDateStr);
				dataMap.put("activityBeginTime", subDepositOrderCustom.getActivityBeginTime().getTime());
				dataMap.put("activityEndTime", subDepositOrderCustom.getActivityEndTime().getTime());
				//尾款
				BigDecimal endPayment = subDepositOrderCustom.getSalePriceSum().subtract(subDepositOrderCustom.getDeductAmountSum());
				dataMap.put("endPayment", endPayment);
				returnData.add(dataMap);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @Title updateSubDepositOrder   
	 * @Description TODO(取消预售子订单)   
	 * @author pengl
	 * @date 2018年11月14日 下午2:18:09
	 */
	@RequestMapping(value = "/api/y/updateSubDepositOrder")
	@ResponseBody
	public ResponseMsg updateSubDepositOrder(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"combineDepositOrderId"};
			this.requiredStr(obj,request);
			
			Date date = new Date();
			Integer memberId = getMemberId(request);//会员标识id
			Integer combineDepositOrderId = Integer.parseInt(reqDataJson.getString("combineDepositOrderId"));//预售总订单ID
			Integer lockedAmount = 0;
			//更新总订单
			CombineDepositOrder combineDepositOrder = new CombineDepositOrder();
			combineDepositOrder.setId(combineDepositOrderId);
			combineDepositOrder.setStatus(Const.COMBINE_DEPOSIT_ORDER_STATUS_CANCEL);
			combineDepositOrder.setCancelType("1");
			combineDepositOrder.setCancelDate(date);
			combineDepositOrder.setUpdateBy(memberId);
			combineDepositOrder.setUpdateDate(date);
			combineDepositOrderService.updateByPrimaryKeySelective(combineDepositOrder);
			//更新子订单   与   保存订单日志
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo(Const.SUB_DEPOSIT_STATUS_NO_PAID)
				.andCombineDepositOrderIdEqualTo(combineDepositOrderId).andMemberIdEqualTo(memberId);
			List<SubDepositOrder> subDepositOrderList = subDepositOrderService.selectByExample(subDepositOrderExample);
			for(SubDepositOrder subDepositOrder : subDepositOrderList) {
				depositOrderStatusLogService.addLog(subDepositOrder.getId(), Const.SUB_DEPOSIT_STATUS_NO_PAID_DEPOSIT_CANCEL, null, memberId);
				lockedAmount += subDepositOrder.getQuantity();
			}
			SubDepositOrder subDepositOrder = new SubDepositOrder();
			subDepositOrder.setStatus(Const.SUB_DEPOSIT_STATUS_NO_PAID_DEPOSIT_CANCEL);
			subDepositOrder.setUpdateBy(memberId);
			subDepositOrder.setUpdateDate(date);
			subDepositOrderService.updateByExampleSelective(subDepositOrder, subDepositOrderExample);
			
			//释放冻结数量
			if(lockedAmount > 0) {
				Integer productItemId = subDepositOrderList.get(0).getProductItemId();
				productItemService.updateSkuLockedAmount(productItemId, -lockedAmount,subDepositOrder.getMchtId(),"1");
			}
			
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}
	
	
}

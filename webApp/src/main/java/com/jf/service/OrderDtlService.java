package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.OrderDtlCustomMapper;
import com.jf.dao.OrderDtlMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.IntegralType;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import com.jf.service.allowance.MemberAllowanceUsageService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月22日 下午5:07:59 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class OrderDtlService extends BaseService<OrderDtl,OrderDtlExample> {
	
	@Autowired
	private OrderDtlMapper orderDtlMapper;
	
	@Autowired
	private OrderDtlCustomMapper orderDtlCustomMapper;
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private OrderLogService orderLogService;
	@Resource
	private MemberCouponService memberCouponService;
	@Autowired
	private DepositOrderStatusLogService depositOrderStatusLogService;
	@Autowired
	private SubDepositOrderService subDepositOrderService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private MemberAllowanceUsageService memberAllowanceUsageService;

	@Autowired
	public void setOrderDtlMapper(OrderDtlMapper orderDtlMapper) {
		super.setDao(orderDtlMapper);
		this.orderDtlMapper = orderDtlMapper;
	}

	public Integer getOrderDtlCount(Map<String, Object> params) {

		return orderDtlCustomMapper.getOrderDtlCount(params);
	}

	public List<OrderDtlCustom> getOrderDtlList(Map<String, Object> params) {

		return orderDtlCustomMapper.getOrderDtlList(params);
	}

	public List<OrderDtlCustom> getOrderCode(Map<String, Object> params) {
		
		return orderDtlCustomMapper.getOrderCode(params);
	}

	public List<OrderDtlCustom> getOrderDtlInfoList(Map<String, Object> params) {
		
		return orderDtlCustomMapper.getOrderDtlInfoList(params);
	}

	public List<OrderDtlCustom> getCustomerServiceList(Map<String, Object> params) {
		
		return orderDtlCustomMapper.getCustomerServiceList(params);
	}

	public Integer getCustomerServiceCount(Map<String, Object> params) {
		
		return orderDtlCustomMapper.getCustomerServiceCount(params);
	}

	public List<OrderDtlCustom> getProductInfoById(Integer orderDtlId) {
		
		return orderDtlCustomMapper.getProductInfoById(orderDtlId);
	}

	public List<OrderDtlCustom> getOrderProductInfoList(Map<String, Object> params) {
		
		return orderDtlCustomMapper.getOrderProductInfoList(params);
	}

	public List<OrderDtlCustom> findMchtAddress(Integer orderDtlId) {
		
		return orderDtlCustomMapper.findMchtAddress(orderDtlId);
	}

	public OrderDtl findModelById(Integer id) {
		
		return orderDtlMapper.selectByPrimaryKey(id);
	}
	

	public Integer getPayQuantity(Map<String, Integer> qMap) {
		
		return orderDtlCustomMapper.getPayQuantity(qMap);
	}

	public Integer getAlreadyPaidCount(Map<String, Object> params) {
		
		return orderDtlCustomMapper.getAlreadyPaidCount(params);
	}

	public void updateCancelOrderById(JSONObject reqDataJson, Integer memberId) {
		Integer combineOrderId = reqDataJson.getInt("combineOrderId");//总订单id
		//总订单状态0 待付款1 待发货2 待收货3 完成4 取消(未付款客户取消,平台取消)5 关闭(退款后关闭)
		String cancelReason = "客服取消";
		if(reqDataJson.containsKey("cancelReason")){
			if(StringUtil.isBlank(reqDataJson.getString("cancelReason"))){
				throw new ArgException("请选择取消原因");
			}
			cancelReason = reqDataJson.getString("cancelReason");
		}
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(combineOrderId);
		if(combineOrder == null){
			throw new ArgException("异常数据，找不到该笔订单!");
		}
		CombineOrderExample combineOrderExample = new CombineOrderExample();
		combineOrderExample.createCriteria().andStatusEqualTo(Const.COMBINE_ORDER_STATUS_NOT_PAID).andIdEqualTo(combineOrderId).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		CombineOrder combineOrderUpdate = new CombineOrder();
		combineOrderUpdate.setStatus("4");//4 取消订单标识
		combineOrderUpdate.setCancelReason(cancelReason);
		combineOrderUpdate.setCancelType("1");
		combineOrderUpdate.setCancelDate(new Date());
		int count = combineOrderService.updateByExampleSelective(combineOrderUpdate, combineOrderExample);
		if(count <= 0){
			throw new ArgException("订单"+combineOrder.getCombineOrderCode()+"已被取消");
		}
		//积分优惠金额
		BigDecimal totalIntegralPreferential = combineOrder.getTotalIntegralPreferential();
		//购物抵扣积分比例
		Integer iChargrShop = 0;
		//获取积分转化比例
		IntegralType shoppingOffset = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_ORDER_PAYMENT));
		if(shoppingOffset != null){
			if(shoppingOffset.getIntType().equals("2")){
				iChargrShop = shoppingOffset.getiCharge() == null ? 0 : shoppingOffset.getiCharge();
			}
		}
		//使用的积分数量(购物抵扣)
		Integer useIntegral = 0;
		if(iChargrShop != 0){
			useIntegral = totalIntegralPreferential.multiply(new BigDecimal(iChargrShop)).intValue();
		}
		if(useIntegral > 0){
			MemberAccountExample memberAccountExample = new MemberAccountExample();
			memberAccountExample.createCriteria().andMemberIdEqualTo(combineOrder.getCreateBy());
			List<MemberAccount> memberAccounts = memberAccountService.selectByExample(memberAccountExample);
			if(CollectionUtils.isNotEmpty(memberAccounts)){
				MemberAccount memberAccount = memberAccounts.get(0);
				//会员账户积分
				Integer memberIntegral = memberAccount.getIntegral();
				memberAccount.setIntegral(memberIntegral+useIntegral);
				memberAccount.setUpdateBy(memberId);
				memberAccountService.updateModel(memberAccount);
				integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME,
						Const.INTEGRAL_TYPE_DEDUCT_RETURN, useIntegral, memberIntegral + useIntegral,
						combineOrder.getId(), memberId,"1");
			}
		}
		Date date = new Date();
		//子订单也要跟着修改标识为4
		SubOrderExample subOrderExample = new SubOrderExample();
		subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrderId);
		List<SubOrder> SubOrderList = subOrderService.selectByExample(subOrderExample);
		for (SubOrder subOrder : SubOrderList) {
			subOrder.setStatus("4");
			subOrderService.updateByPrimaryKeySelective(subOrder);
			//插入订单日志
			orderLogService.insertOrderLog(subOrder.getId(),"4",memberId);
			//待付款订单取消了  库存冻结要释放
			OrderDtlExample orderDtlExample = new OrderDtlExample();
			orderDtlExample.createCriteria().andSubOrderIdEqualTo(subOrder.getId());
			List<OrderDtl> orderDtlList = orderDtlService.selectByExample(orderDtlExample);
			for (OrderDtl orderDtl : orderDtlList) {
				//预售商品定金
				int subDepositOrderQuantity = 0; //预售商品数量，取消订单预售商品不解冻数量
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("memberId", memberId);
				paramMap.put("subDepositOrderStatusList", Arrays.asList(Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER));
				paramMap.put("orderDtlId", orderDtl.getId());
				List<SubDepositOrderCustom> subDepositOrderCustomList = subDepositOrderService.getSubDepositOrderList(paramMap);
				for(SubDepositOrderCustom subDepositOrderCustom : subDepositOrderCustomList) {
					String status = "";
					if(subDepositOrderCustom.getActivityEndTime().compareTo(date) <= 0) {
						status = Const.SUB_DEPOSIT_STATUS_NO_PAID_TAIL;
					}else {
						status = Const.SUB_DEPOSIT_STATUS_TAIL_NO_PAY_CANCEL;
						subDepositOrderQuantity += subDepositOrderCustom.getQuantity(); //冻结数
					}
					depositOrderStatusLogService.addLog(subDepositOrderCustom.getId(), status, 
							subDepositOrderCustom.getOrderDtlId(), memberId); //定金订单状态日志
					SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
					subDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
						.andIdEqualTo(subDepositOrderCustom.getId())
						.andStatusEqualTo(Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER)
						.andMemberIdEqualTo(memberId).andOrderDtlIdEqualTo(orderDtl.getId());
					SubDepositOrder subDepositOrder = new SubDepositOrder();
					subDepositOrder.setStatus(status);
					subDepositOrder.setUpdateBy(memberId);
					subDepositOrder.setUpdateDate(date);
					subDepositOrderService.updateByExampleSelective(subDepositOrder, subDepositOrderExample); //更改定金子订单状态
				}
				if(subDepositOrderCustomList != null && subDepositOrderCustomList.size() > 0) {
					SubDepositOrderCustom subDepositOrderCustom = subDepositOrderCustomList.get(0);
					if(subDepositOrderCustom.getActivityEndTime().compareTo(date) > 0) { //活动未结束
						ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
						shoppingCartExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0")
							.andMemberIdEqualTo(memberId).andMchtIdEqualTo(subOrder.getMchtId())
							.andProductItemIdEqualTo(orderDtl.getProductItemId());
						shoppingCartExample.setOrderByClause(" id desc");
						List<ShoppingCart> shoppingCartList = shoppingCartService.selectByExample(shoppingCartExample);
						if(shoppingCartList != null && shoppingCartList.size() > 0) { //相同SKU购物车存在，数量增加
							ShoppingCart shoppingCart = shoppingCartList.get(0);
							int shoppingCartQuantity = shoppingCart.getQuantity();
							shoppingCart.setQuantity(shoppingCartQuantity + subDepositOrderQuantity);
							shoppingCartService.updateByPrimaryKeySelective(shoppingCart);
						}else {
							ShoppingCart cart = new ShoppingCart();
							cart.setActivityId(subDepositOrderCustom.getActivityId());
							cart.setActivityAreaId(orderDtl.getActivityAreaId());
							cart.setMchtId(subDepositOrderCustom.getMchtId());
							cart.setMemberId(subDepositOrderCustom.getMemberId());
							cart.setActivityType(Const.PRODUCT_ACTIVITY_TYPE_AREA);
							cart.setProductItemId(subDepositOrderCustom.getProductItemId());
							cart.setAddSalePrice(subDepositOrderCustom.getSalePrice());
							cart.setAddTagPrice(subDepositOrderCustom.getTagPrice());
							cart.setQuantity(subDepositOrderQuantity);
							cart.setStatus("0");
							cart.setCreateDate(date);
							cart.setDelFlag("0");
							shoppingCartService.insertSelective(cart);
						} 
					}
				}
				//购买数量
				Integer quantity = orderDtl.getQuantity();
				productItemService.cancelReleaseProductLockedAmount(orderDtl.getProductItemId(), quantity, null);
			}
		}	
		//优惠券还还给用户
		memberCouponService.updateMemberCouponUseInfo(memberId,combineOrderId,"2");

		//删除津贴使用记录
		memberAllowanceUsageService.deleteLog(combineOrderId);
	}

	public Map<String, Object> getSubMemberHistoryOrderList(JSONObject reqPRM, JSONObject reqDataJson) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		Integer memberId = reqDataJson.getInt("memberId");//会员id
		Integer currentPage = reqDataJson.getInt("currentPage");//当前页码
		Integer pageSize = reqDataJson.getInt("pageSize");//当前页数
		String type = reqDataJson.getString("type");//0全部 1已付款 2已收货 3已结算 4已失效
		List<Integer> memberIds = memberInfoService.getMemberSubUser(memberId);//找出当前会员的子集
		if(CollectionUtils.isNotEmpty(memberIds)){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("memberIds", memberIds);
			paramMap.put("type", type);
			paramMap.put("currentPage", currentPage * pageSize);
			paramMap.put("pageSize", pageSize);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlCustomMapper.getSubMemberHistoryOrderList(paramMap);
			if(CollectionUtils.isNotEmpty(orderDtlCustoms)){
				for (OrderDtlCustom dtl : orderDtlCustoms) {
					Map<String, Object> dataMap = new HashMap<String, Object>();
					String subOrderStatus = dtl.getStatus().toString();
					String proStatus = dtl.getProductStatus() == null ? "" : dtl.getProductStatus();
					String distributionSettleStatus = dtl.getDistributionSettleStatus();
					StringBuilder subOrderCode = new StringBuilder(dtl.getSubOrderCode());
					String subOrderStatusStr = "";
					String status = "";
					if("2".equals(proStatus) || "3".equals(proStatus)){
						subOrderStatusStr = "已失效";
						status = "4";
					}else{
						if(Const.ORDER_STATUS_PAID.equals(subOrderStatus) || Const.ORDER_STATUS_SHIPPED.equals(subOrderStatus)){
							subOrderStatusStr = "已付款";
							status = "1";
						}else if((Const.ORDER_STATUS_SUCCESS.equals(subOrderStatus) || Const.ORDER_STATUS_RECEIVED_GOODS.equals(subOrderStatus))
								&& ("0".equals(distributionSettleStatus) || StringUtil.isBlank(distributionSettleStatus))){
							subOrderStatusStr = "已收货";
							status = "2";
						}else if(Const.ORDER_STATUS_SUCCESS.equals(subOrderStatus) && "1".equals(distributionSettleStatus)){
							subOrderStatusStr = "已结算";
							status = "3";
						}
					}
					dataMap.put("memberPic", StringUtil.getPic(dtl.getPic(), ""));
					dataMap.put("memberNick", StringUtil.getNickRule(dtl.getNick(),""));
					dataMap.put("payDateStr", DateUtil.getFormatDate(dtl.getPayDate(), "yyyy-MM-dd HH:mm:ss"));
					dataMap.put("subOrderCode", subOrderCode.replace(11,16, "******").toString());
					dataMap.put("quantity", dtl.getQuantity());
					dataMap.put("payAmount", dtl.getPayAmount().setScale(2, BigDecimal.ROUND_DOWN));
					dataMap.put("distributionAmountStr", "预估赚"+dtl.getDistributionAmount()+"元");
					dataMap.put("subOrderStatusStr", subOrderStatusStr);
					dataMap.put("status", status);
					dataList.add(dataMap);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", dataList);
		return map;
	}

	public List<OrderDtlCustom> findByCombineOrderId(Integer combineOrderId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("combineOrderId", combineOrderId);
		return orderDtlCustomMapper.getOrderDtlInfoList(params);
	}
}

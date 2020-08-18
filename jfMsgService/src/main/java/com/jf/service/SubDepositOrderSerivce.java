package com.jf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.dao.CombineDepositOrderMapper;
import com.jf.dao.DepositOrderStatusLogMapper;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductItemMapper;
import com.jf.dao.ShoppingCartMapper;
import com.jf.dao.SubDepositOrderCustomMapper;
import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineDepositOrderExample;
import com.jf.entity.DepositOrderStatusLog;
import com.jf.entity.OrderDtl;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderExample;

@Service
@Transactional
public class SubDepositOrderSerivce extends BaseService<SubDepositOrder, SubDepositOrderExample> {

	@Autowired
	private SubDepositOrderMapper subDepositOrderMapper;
	
	@Autowired
	private CombineDepositOrderMapper combineDepositOrderMapper;
	
	@Autowired
	private DepositOrderStatusLogMapper depositOrderStatusLogMapper;
	
	@Autowired
	private ProductItemMapper productItemMapper;
	
	@Autowired
	private ProductCustomMapper productCustomMapper;
	
	@Autowired
	private DepositOrderStatusLogService depositOrderStatusLogService;
	
	@Autowired
	private SubDepositOrderCustomMapper subDepositOrderCustomMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	public void setSubDepositOrderMapper(SubDepositOrderMapper subDepositOrderMapper) {
		this.setDao(subDepositOrderMapper);
		this.subDepositOrderMapper = subDepositOrderMapper;
	}
	
	public void updateSubDepositOrder(SubDepositOrder subDepositOrder) {
		Date date = new Date();
		SubDepositOrder upSubDepositOrder = new SubDepositOrder();
		upSubDepositOrder.setId(subDepositOrder.getId());
		upSubDepositOrder.setStatus(Const.SUB_DEPOSIT_STATUS_NO_PAID_DEPOSIT_CANCEL);
		upSubDepositOrder.setUpdateDate(date);
		subDepositOrderMapper.updateByPrimaryKeySelective(upSubDepositOrder); //更新定金子订单状态
		CombineDepositOrderExample combineDepositOrderExample = new CombineDepositOrderExample();
		combineDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo(Const.COMBINE_DEPOSIT_ORDER_STATUS_NO_PAID)
			.andIdEqualTo(subDepositOrder.getCombineDepositOrderId());
		CombineDepositOrder combineDepositOrder = new CombineDepositOrder();
		combineDepositOrder.setStatus(Const.COMBINE_DEPOSIT_ORDER_STATUS_CANCEL);
		combineDepositOrder.setCancelType("2");
		combineDepositOrder.setCancelDate(date);
		combineDepositOrder.setStatusDate(date);
		combineDepositOrder.setUpdateDate(date);
		combineDepositOrderMapper.updateByExampleSelective(combineDepositOrder, combineDepositOrderExample); //更新定金总订单状态
		DepositOrderStatusLog depositOrderStatusLog = new DepositOrderStatusLog();
		depositOrderStatusLog.setSubDepositOrderId(subDepositOrder.getId());
		depositOrderStatusLog.setStatus(Const.SUB_DEPOSIT_STATUS_NO_PAID_DEPOSIT_CANCEL);
		depositOrderStatusLog.setCreateDate(date);
		depositOrderStatusLog.setDelFlag("0");
		depositOrderStatusLogMapper.insertSelective(depositOrderStatusLog); //添加定金子订单日志
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("productItemId", subDepositOrder.getProductItemId());
		params.put("quantity", -subDepositOrder.getQuantity());
		productCustomMapper.updateSkuLockedAmount(params); //解冻库存
	}
	
	public void closeSubDepositOrder(List<Map<String, Object>> mapList) {
		Date date = new Date();
		List<Integer> subDepositOrderIdList = new ArrayList<Integer>();
		for(Map<String, Object> map : mapList) {
			Integer subDepositOrderId = Integer.parseInt(map.get("id").toString());
			Integer productItemId = Integer.parseInt(map.get("product_item_id").toString());
			Integer quantity = Integer.parseInt(map.get("quantity").toString());
			subDepositOrderIdList.add(subDepositOrderId);
			DepositOrderStatusLog depositOrderStatusLog = new DepositOrderStatusLog();
			depositOrderStatusLog.setSubDepositOrderId(subDepositOrderId);
			depositOrderStatusLog.setStatus(Const.SUB_DEPOSIT_STATUS_NO_PAID_TAIL);
			depositOrderStatusLog.setCreateDate(date);
			depositOrderStatusLog.setDelFlag("0");
			depositOrderStatusLogMapper.insertSelective(depositOrderStatusLog); //添加定金子订单日志
			Map<String, Integer> params = new HashMap<String, Integer>();
			params.put("productItemId", productItemId);
			params.put("quantity", -quantity);
			productCustomMapper.updateSkuLockedAmount(params); //解冻库存
		}
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andIdIn(subDepositOrderIdList)
			.andDelFlagEqualTo("0").andStatusIn(Arrays.asList("2", "8"));
		SubDepositOrder subDepositOrder = new SubDepositOrder();
		subDepositOrder.setStatus(Const.SUB_DEPOSIT_STATUS_NO_PAID_TAIL);
		subDepositOrder.setCompleteDate(date);
		subDepositOrder.setUpdateDate(date);
		subDepositOrderMapper.updateByExampleSelective(subDepositOrder, subDepositOrderExample); //更新定金子订单状态
	}
	
	public int updateSubDepositOrder(OrderDtl orderDtl) {
		Date date = new Date();
		//预售商品定金
		int subDepositOrderQuantity = 0; //预售商品数量，取消订单预售商品不解冻数量
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subDepositOrderStatusList", Arrays.asList(Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER));
		paramMap.put("orderDtlId", orderDtl.getId());
		List<SubDepositOrderCustom> subDepositOrderCustomList = subDepositOrderCustomMapper.getSubDepositOrderList(paramMap);
		for(SubDepositOrderCustom subDepositOrderCustom : subDepositOrderCustomList) {
			String status = "";
			if(subDepositOrderCustom.getActivityEndTime().compareTo(date) <= 0) {
				status = Const.SUB_DEPOSIT_STATUS_NO_PAID_TAIL;
			}else {
				status = Const.SUB_DEPOSIT_STATUS_TAIL_NO_PAY_CANCEL;
				subDepositOrderQuantity += subDepositOrderCustom.getQuantity(); //冻结数
			}
			depositOrderStatusLogService.addLog(subDepositOrderCustom.getId(), status, 
					subDepositOrderCustom.getOrderDtlId(), subDepositOrderCustom.getMemberId()); //定金订单状态日志
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
				.andIdEqualTo(subDepositOrderCustom.getId())
				.andStatusEqualTo(Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER)
				.andMemberIdEqualTo(subDepositOrderCustom.getMemberId()).andOrderDtlIdEqualTo(orderDtl.getId());
			SubDepositOrder subDepositOrder = new SubDepositOrder();
			subDepositOrder.setStatus(status);
			subDepositOrder.setUpdateDate(date);
			subDepositOrderMapper.updateByExampleSelective(subDepositOrder, subDepositOrderExample); //更改定金子订单状态
		}
		if(subDepositOrderCustomList != null && subDepositOrderCustomList.size() > 0) {
			SubDepositOrderCustom subDepositOrderCustom = subDepositOrderCustomList.get(0);
			if(subDepositOrderCustom.getActivityEndTime().compareTo(date) > 0) { //活动未结束
				ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
				shoppingCartExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0")
					.andMemberIdEqualTo(subDepositOrderCustom.getMemberId()).andMchtIdEqualTo(subDepositOrderCustom.getMchtId())
					.andProductItemIdEqualTo(orderDtl.getProductItemId());
				shoppingCartExample.setOrderByClause(" id desc");
				List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByExample(shoppingCartExample);
				if(shoppingCartList != null && shoppingCartList.size() > 0) { //相同SKU购物车存在，数量增加
					ShoppingCart shoppingCart = shoppingCartList.get(0);
					int shoppingCartQuantity = shoppingCart.getQuantity();
					shoppingCart.setQuantity(shoppingCartQuantity + subDepositOrderQuantity);
					shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
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
					shoppingCartMapper.insertSelective(cart);
				}
			}
		}
		return subDepositOrderQuantity;
	} 
	
	
}

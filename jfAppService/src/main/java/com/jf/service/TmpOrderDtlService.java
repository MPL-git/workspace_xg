package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.TmpOrderDtlMapper;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import com.jf.entity.TmpOrderDtl;
import com.jf.entity.TmpOrderDtlExample;
import com.jf.entity.TmpSubOrder;
import com.jf.entity.TmpSubOrderExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TmpOrderDtlService extends BaseService<TmpOrderDtl, TmpOrderDtlExample> {
	@Autowired
	private TmpOrderDtlMapper tmpOrderDtlMapper;
	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private TmpOrderDtlService tmpOrderDtlService;
	@Resource
	private TmpSubOrderService tmpSubOrderService;
	@Autowired
	public void setTmpOrderDtlMapper(TmpOrderDtlMapper tmpOrderDtlMapper) {
		this.setDao(tmpOrderDtlMapper);
		this.tmpOrderDtlMapper = tmpOrderDtlMapper;
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
			BigDecimal afterPlatPayMoney = combinePayAmount.add(money).add(dtl.getCombineIntegralAmount());//+上平台优惠券，重新分配
			BigDecimal platformAmount = new BigDecimal("0");
			BigDecimal platformAmountAdd = new BigDecimal("0");
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
				i++;
//				System.out.println(i);
				if (dtls.size() == i) {
					platformAmount = money.subtract(platformAmountAdd);
				} else {
					//商品的优惠金额
					BigDecimal x = orderDtl.getPlatformPreferential().multiply(combineAmount).divide(money,2,BigDecimal.ROUND_HALF_UP);
					BigDecimal disAmount = orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity().toString())).subtract(x);
					platformAmount = (orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity()))
							.subtract(disAmount)).multiply(money).divide(afterPlatPayMoney, 2, BigDecimal.ROUND_HALF_UP);
				}
				platformAmountAdd = platformAmountAdd.add(platformAmount);
				BigDecimal payAmount = orderDtl.getIntegralPreferential().add(orderDtl.getMchtPreferential()).add(platformAmount);
				TmpOrderDtl tmpOrderDtl = new TmpOrderDtl();
				tmpOrderDtl.setOrderDtlId(orderDtl.getId());
				if(dtls.size() == i){
					//最后一轮用总的实付金额-每只商品的实付金额 = 最后一只商品的实付金额
					tmpOrderDtl.setPayAmount(combinePayAmount.subtract(taotalPayAmountAdd));
				}else{
					taotalPayAmountAdd = taotalPayAmountAdd.add(orderDtl.getSalePrice().subtract(payAmount));
					tmpOrderDtl.setPayAmount(orderDtl.getSalePrice().subtract(payAmount));
				}
				tmpOrderDtl.setPlatAmount(platformAmount);
				tmpOrderDtlService.insertSelective(tmpOrderDtl);
				
				TmpSubOrderExample tmpSubOrderExample = new TmpSubOrderExample();
				tmpSubOrderExample.createCriteria().andSubOrderIdEqualTo(orderDtl.getSubOrderId());
				List<TmpSubOrder> tmpSubOrders = tmpSubOrderService.selectByExample(tmpSubOrderExample);
				TmpSubOrder ts = null;
				if(CollectionUtils.isNotEmpty(tmpSubOrders)){
					ts = tmpSubOrders.get(0);
					ts.setPayAmount(ts.getPayAmount().add(tmpOrderDtl.getPayAmount()));
					ts.setPlatAmount(ts.getPlatAmount().add(tmpOrderDtl.getPlatAmount()));
					tmpSubOrderService.updateBySubOrderId(ts);
				}else{
					ts = new TmpSubOrder();
					ts.setSubOrderId(orderDtl.getSubOrderId());
					ts.setPayAmount(tmpOrderDtl.getPayAmount());
					ts.setPlatAmount(tmpOrderDtl.getPlatAmount());
					tmpSubOrderService.insertSelective(ts);
				}
			}
		}
	}
	
	
}

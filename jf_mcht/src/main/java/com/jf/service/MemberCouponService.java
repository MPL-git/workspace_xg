package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.MemberCouponLogMapper;
import com.jf.dao.MemberCouponMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.MemberCouponLog;
import com.jf.entity.SubOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月19日 下午6:51:03 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberCouponService extends BaseService<MemberCoupon, MemberCouponExample> {
	@Autowired
	private MemberCouponMapper memberCouponMapper;
	
	@Autowired
	private MemberCouponLogMapper memberCouponLogMapper;
	
	@Autowired
	private SubOrderMapper subOrderMapper;
	
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	
	@Autowired
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Autowired
	private OrderDtlService orderDtlService;
	
	@Autowired
	public void setMemberCouponMapper(MemberCouponMapper memberCouponMapper) {
		this.setDao(memberCouponMapper);
		this.memberCouponMapper = memberCouponMapper;
	}

	public void changeStatusByCustomerServiceOrder(CustomerServiceOrder customerServiceOrder) {
		SubOrder subOrder = subOrderMapper.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
		CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());
		List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.getByCombineOrderId(combineOrder.getId());
		boolean toChange = true;
		Integer orderDtlCount = orderDtlService.countByCombineOrderId(combineOrder.getId());
		if(orderDtlCount!=customerServiceOrders.size()){
			toChange = false;
		}else{
			for(CustomerServiceOrder cso:customerServiceOrders){
				if(!cso.getProStatus().equals("A2")){
					toChange = false;
					break;
				}
			}
		}
		if(toChange){
			MemberCouponExample e = new MemberCouponExample();
			MemberCouponExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMemberIdEqualTo(combineOrder.getMemberId());
			c.andOrderIdEqualTo(combineOrder.getId());
			List<MemberCoupon> memberCoupons = this.selectByExample(e);
			for(MemberCoupon memberCoupon:memberCoupons){
				MemberCouponLog mcl = new MemberCouponLog();
				mcl.setCreateDate(new Date());
				mcl.setDelFlag("0");
				mcl.setMemberCouponId(memberCoupon.getId());
				mcl.setLogType("2");
				mcl.setOrderId(combineOrder.getId());
				memberCouponLogMapper.insertSelective(mcl);
			}
			MemberCoupon mc = new MemberCoupon();
			mc.setStatus("0");
			mc.setUpdateDate(new Date());
			this.updateByExampleSelective(mc, e);
		}
	}
}

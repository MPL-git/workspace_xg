package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberCouponCustomMapper;
import com.jf.entity.MemberCouponCustom;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.JgContentCustomMapper;
import com.jf.dao.MemberCouponLogMapper;
import com.jf.dao.MemberCouponMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.Coupon;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.JgContent;
import com.jf.entity.JgContentCustom;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.MemberCouponLog;
import com.jf.entity.SubOrder;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderStatusLog;

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
	private static Logger logger = LoggerFactory.getLogger(MemberCouponService.class);
	@Autowired
	private MemberCouponMapper memberCouponMapper;
	
	@Autowired
	private JgContentCustomMapper memberCouponCustomMapper;
	
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
	@Resource
	private CouponService couponService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private WithdrawOrderService withdrawOrderService;
	@Resource
	private MemberAccountDtlService memberAccountDtlService;
	@Resource
	private WithdrawOrderStatusLogService withdrawOrderStatusLogService;
	@Resource
	private MemberCouponCustomMapper memberCouponCustomMappers;
	@Autowired
	public void setMemberCouponMapper(MemberCouponMapper memberCouponMapper) {
		this.setDao(memberCouponMapper);
		this.memberCouponMapper = memberCouponMapper;
	}

	public List<JgContentCustom> getMemberCouponInfoForJG() {
		
		return memberCouponCustomMapper.getMemberCouponInfoForJG();
	}

	public void insertBatchJg(List<JgContent> list) {
		
		memberCouponCustomMapper.insertBatchJg(list);
	}

	public void changeStatusByCombineOrder(CombineOrder combineOrder) {
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
	
	
	
	public void addReceiveWithdrawCoupon(Integer memberId, Integer couponId, WithdrawOrder withdrawOrder) {
		Date currentDate = new Date();
		Coupon coupon = couponService.selectByPrimaryKey(couponId);
		Map<String,Object> map = getMemberIsReceiveCoupon(coupon, memberId);
		boolean isHasCoupon = (boolean) map.get("isHasCoupon");
		if(isHasCoupon){
			BigDecimal acount = withdrawOrder.getAmount();
			//1:解冻,扣除余额
			MemberAccount account = memberAccountService.updateMemberAccountBalanceAndFreeze(withdrawOrder.getAccId(),acount.multiply(new BigDecimal("-1")),currentDate);
			if(account == null){
				logger.info("更新会员账户出错："+withdrawOrder.getId());
				return;
			}
			
			Date expiryBeginDate = null;
			Date expiryEndDate = null;
			Date date = new Date();
			if(coupon.getExpiryType().equals("2")){
				expiryBeginDate = date;
				expiryEndDate = DateUtil.addDay(date, coupon.getExpiryDays());
			}else{
				expiryBeginDate = coupon.getExpiryBeginDate();
				expiryEndDate = coupon.getExpiryEndDate();
			}
			MemberCoupon memberCoupon = new MemberCoupon();
			memberCoupon.setMemberId(Integer.valueOf(memberId));
			memberCoupon.setCouponId(Integer.valueOf(couponId));
			memberCoupon.setRecDate(new Date());
			memberCoupon.setExpiryBeginDate(expiryBeginDate);
			memberCoupon.setExpiryEndDate(expiryEndDate);
			memberCoupon.setDelFlag("0");
			memberCoupon.setCreateBy(Integer.valueOf(memberId));
			memberCoupon.setCreateDate(new Date());
			insertSelective(memberCoupon);
			//更改优惠券的领取数量
			Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
			coupon.setRecQuantity(recQuantity+1);
			couponService.updateByPrimaryKeySelective(coupon);
			
			//2:账户明细新增(解冻和兑换优惠券)
			//2_1:先解冻（入账）
			MemberAccountDtl unFreezeAccountDtl = new MemberAccountDtl();
			unFreezeAccountDtl.setAccId(account.getId());
			unFreezeAccountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			unFreezeAccountDtl.setFreezeAmount(acount);
			unFreezeAccountDtl.setTotalFreeze(account.getFreeze().subtract(acount));
			unFreezeAccountDtl.setBalance(account.getBalance());
			unFreezeAccountDtl.setBizType("7");//业务类型1 签到2 提现3 兑换优惠券4 冻结 5好友签到助力6 连签红包7解冻
			unFreezeAccountDtl.setBizId(withdrawOrder.getId());
			unFreezeAccountDtl.setCreateBy(memberId);
			unFreezeAccountDtl.setCreateDate(currentDate);
			unFreezeAccountDtl.setDelFlag("0");
			unFreezeAccountDtl.setRemarks("兑换优惠券成功,解冻金额");
			memberAccountDtlService.insertSelective(unFreezeAccountDtl);
			//2_2在兑换优惠券（出账）
			MemberAccountDtl accountDtl = new MemberAccountDtl();
			accountDtl.setAccId(account.getId());
			accountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_ACCOUNT);
			accountDtl.setAmount(acount);
			accountDtl.setBalance(account.getBalance().subtract(acount));
			accountDtl.setBizType("3");//业务类型1 签到2 提现3 兑换优惠券4 冻结 5好友签到助力6 连签红包7解冻
			accountDtl.setBizId(withdrawOrder.getId());
			accountDtl.setCreateBy(memberId);
			accountDtl.setCreateDate(currentDate);
			accountDtl.setDelFlag("0");
			accountDtl.setRemarks("兑换优惠券成功,余额扣除");
			memberAccountDtlService.insertSelective(accountDtl);
			//3：更新提现单状态
			withdrawOrder.setStatus("4");
			withdrawOrder.setUpdateDate(currentDate);
			withdrawOrder.setMemberCouponId(memberCoupon.getId());
			withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
			//4:新增流水日志
			WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
			log.setStatus("4");
			log.setWithdrawOrderId(withdrawOrder.getId());
			log.setCreateBy(memberId);
			log.setCreateDate(currentDate);
			log.setDelFlag("0");
			withdrawOrderStatusLogService.insertSelective(log);
		}else{
			//不满足优惠券规则，解冻用户账户
			String remarks = map.get("couponReceiveMsg").toString();
			MemberAccount account = memberAccountService.updateMemberAccountFreeze(withdrawOrder.getAccId(),withdrawOrder.getAmount().multiply(new BigDecimal("-1")),currentDate);
			if(account == null){
				logger.info("更新会员账户出错："+withdrawOrder.getId());
				return;
			}
			BigDecimal balance = account.getBalance();
			BigDecimal freeze = account.getFreeze();
			MemberAccountDtl accountDtl = new MemberAccountDtl();
			accountDtl.setAccId(account.getId());
			accountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			accountDtl.setBalance(balance);
			accountDtl.setTotalFreeze(freeze.subtract(withdrawOrder.getAmount()));
			accountDtl.setFreezeAmount(withdrawOrder.getAmount());
			accountDtl.setBizType("7");
			accountDtl.setBizId(withdrawOrder.getId());
			accountDtl.setUpdateDate(currentDate);
			accountDtl.setRemarks("领取优惠券失败,解冻金额："+remarks);
			memberAccountDtlService.insertSelective(accountDtl);
			
			withdrawOrder.setStatus("6");
			withdrawOrder.setUpdateDate(currentDate);
			withdrawOrder.setRemarks(remarks);
			withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
		
			WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
			log.setStatus("6");
			log.setWithdrawOrderId(withdrawOrder.getId());
			log.setRemarks(remarks);
			log.setCreateBy(memberId);
			log.setCreateDate(currentDate);
			log.setDelFlag("0");
			withdrawOrderStatusLogService.insertSelective(log);
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：判断用户是否领取过优惠券
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月31日 下午4:00:04 
	 * @version
	 * @param coupon
	 * @param memberId
	 * @return
	 */
	public Map<String,Object> getMemberIsReceiveCoupon(Coupon coupon, Integer memberId) {
		//优惠券发行数量
		Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
		//优惠券已领数量
		Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
		//优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
		String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
		//优惠券 限领数量
		Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
		boolean isHasCoupon = true;
		String msg = "";
		if(grantQuantity > 0 && grantQuantity > recQuantity){
			if(memberId != null){
				MemberCouponExample memberCouponExample = new MemberCouponExample();
				memberCouponExample.createCriteria().andMemberIdEqualTo(memberId)
				.andCouponIdEqualTo(coupon.getId()).andDelFlagEqualTo("0");
				memberCouponExample.setOrderByClause("rec_date desc");
				List<MemberCoupon> memberCoupons = selectByExample(memberCouponExample);
				if(CollectionUtils.isNotEmpty(memberCoupons)){
					String recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMMdd");
					String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
					//限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
					if(recLimitType.equals("1")){
						if(recDate.equals(currentDate)){
							isHasCoupon = false;
							msg = "每人每天限领1张 ";
						}
					}else if(recLimitType.equals("2")){
						if(memberCoupons.size() < recEach){
							isHasCoupon = false;
							msg = "每人限领 "+recEach+"张";
						}
					}else if(recLimitType.equals("3")){
					}else{
						isHasCoupon = false;
						msg = "优惠券已抢光";
					}
				}
			}
		}else{
			isHasCoupon = false;
			msg = "优惠券已抢光";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isHasCoupon", isHasCoupon);
		map.put("couponReceiveMsg", msg);
		return map;
	}

    public List<Integer> selectByExampleCustom(List<Integer> couponIdList,Integer limitNumber) {
		return memberCouponCustomMappers.selectByCustomExample(couponIdList,limitNumber);
    }
}

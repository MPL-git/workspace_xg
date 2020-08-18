package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.jf.common.constant.Const;
import com.jf.dao.IntegralDtlMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlExample;
import com.jf.entity.IntegralType;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月1日 上午12:30:47 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class IntegralDtlService extends BaseService<IntegralDtl, IntegralDtlExample> {
	
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private MemberAccountService memberAccountService;

	@Autowired
	public void setIntegralDtlMapper(IntegralDtlMapper integralDtlMapper) {
		this.setDao(integralDtlMapper);
		this.integralDtlMapper = integralDtlMapper;
	}

	public void addIntegralDtl(CombineOrder combineOrder) {
		//积分优惠金额
		BigDecimal totalIntegralPreferential = combineOrder.getTotalIntegralPreferential();
		String remarks = null;
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
				memberAccount.setUpdateBy(combineOrder.getMemberId());
				memberAccountService.updateModel(memberAccount);
				integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME,
						Const.INTEGRAL_TYPE_DEDUCT_RETURN, useIntegral, memberIntegral + useIntegral,
						combineOrder.getId(), combineOrder.getMemberId(),remarks,"1");
			}
		}
	}

	public void addIntegralDtl(Integer accId, String tallyMode, String type, Integer integral, Integer balance,Integer orderId , Integer memberId, String remarks, String bizType) {
		IntegralDtl integralDtl = new IntegralDtl();
		integralDtl.setAccId(accId);
		integralDtl.setIntegral(integral);
		integralDtl.setBalance(balance);
		integralDtl.setBizType(bizType);
		integralDtl.setTallyMode(tallyMode);
		integralDtl.setType(Integer.valueOf(type));
		integralDtl.setOrderId(orderId);
		integralDtl.setCreateBy(memberId);
		integralDtl.setDelFlag("0");
		integralDtl.setRemarks(remarks);
		saveModel(integralDtl);
	}
	
	public void saveModel(IntegralDtl integralDtl) {
		integralDtl.setCreateDate(new Date());
		integralDtlMapper.insertSelective(integralDtl);
	}
	
	
}

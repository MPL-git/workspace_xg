package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;

import com.jf.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.dao.IntegralDtlMapper;
import com.jf.dao.MemberAccountCustomMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.dao.ViolateOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.IntegralDtl;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.SubOrder;
import com.jf.entity.ViolateOrder;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月1日 上午12:41:35 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberAccountService extends BaseService<MemberAccount, MemberAccountExample> {
	private static Logger logger = LoggerFactory.getLogger(MemberAccountService.class);
	
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	@Autowired
	private MemberAccountCustomMapper memberAccountCustomMapper;
	
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	
	@Autowired
	private ViolateOrderMapper violateOrderMapper;
	@Autowired
	private MemberAccountDtlService memberAccountDtlService;
	@Autowired
	private CombineOrderService combineOrderService;
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	public void setMemberAccountMapper(MemberAccountMapper memberAccountMapper) {
		this.setDao(memberAccountMapper);
		this.memberAccountMapper = memberAccountMapper;
	}

	public void updateModel(MemberAccount memberAccount) {
		memberAccount.setUpdateDate(new Date());
		memberAccountMapper.updateByPrimaryKeySelective(memberAccount);
	}

	public void update(ViolateOrder violateOrder,MemberAccount memberAccount, IntegralDtl integralDtl) {
		violateOrderMapper.updateByPrimaryKeySelective(violateOrder);
		integralDtlMapper.insertSelective(integralDtl);
		this.updateByPrimaryKeySelective(memberAccount);
	}

	public MemberAccount updateMemberAccountFreeze(Integer accId, BigDecimal amount, Date date) {
		MemberAccount account = selectByPrimaryKey(accId);
		
		MemberAccount newAccount = new MemberAccount();
		newAccount.setId(accId);
		newAccount.setFreeze(amount);//setFreeze为该次增加或减小的冻结金额，最终计算余额要用取出来的余额+该次增加或减小的冻结金额
		newAccount.setUpdateDate(date);
		int count = memberAccountCustomMapper.updateMemberAccountFreeze(newAccount);
		if(count <= 0){
			return null;
		}
		return account;
	}

	public MemberAccount updateMemberAccountBalanceAndFreeze(Integer accId, BigDecimal amount, Date date) {
		//amount 前面传过来的是正负数代表新增或扣除
		MemberAccount account = selectByPrimaryKey(accId);
		
		MemberAccount newAccount = new MemberAccount();
		newAccount.setId(accId);
		newAccount.setBalance(amount);//setBalance为该次增加或减小的余额，最终计算余额要用取出来的余额+该次增加或减小的余额
		newAccount.setFreeze(amount);//setFreeze为该次增加或减小的冻结金额，最终计算余额要用取出来的余额+该次增加或减小的冻结金额
		newAccount.setUpdateDate(date);
		int count = memberAccountCustomMapper.updateMemberAccountBalanceAndFreeze(newAccount);
		if(count <= 0){
			return null;
		}
		return account;
	}
	
	public MemberAccount selectMemberIdKey(Integer memberId){
	   	return memberAccountCustomMapper.selectMemberIdKey(memberId);
	}


	public void rewardSuperiorUser1(SubOrder model) {
		Integer combineOrderId = model.getCombineOrderId();
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(combineOrderId);
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(combineOrder.getMemberId());
		Integer invitationMemberId =  memberInfo.getInvitationMemberId();
		Date invitationCodeBindTime = memberInfo.getInvitationCodeBindTime();
		if(invitationCodeBindTime == null || invitationMemberId == null){
			return;
		}
		BigDecimal amount = new BigDecimal("3");
		MemberAccountExample memberAccountExample = new MemberAccountExample();
		memberAccountExample.createCriteria().andMemberIdEqualTo(invitationMemberId).andDelFlagEqualTo("0");
		MemberAccount memberAccount = selectByExample(memberAccountExample).get(0);
		//查询账户明细是否有赠送过
		boolean isReceive = memberAccountDtlService.isReceiveSubFristReward(memberAccount.getId(),combineOrderId,combineOrder.getMemberId());
		if(isReceive){
			logger.info("账户:"+memberAccount.getId()+"已领取首单奖励");
			return;
		}
		memberAccount.setProfitInviteBalance(memberAccount.getProfitInviteBalance().add(amount));
		memberAccount.setUpdateDate(new Date());
		updateByPrimaryKeySelective(memberAccount);
		//添加日志
		MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
		memberAccountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
		memberAccountDtl.setAccId(memberAccount.getId());
		memberAccountDtl.setAmount(amount);
		memberAccountDtl.setBalance(memberAccount.getProfitInviteBalance());
		memberAccountDtl.setBizType("8");
		memberAccountDtl.setBizId(combineOrderId);
		memberAccountDtl.setCreateDate(new Date());
		memberAccountDtl.setDelFlag("0");
		memberAccountDtlService.insertSelective(memberAccountDtl);
	}

}

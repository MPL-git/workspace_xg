package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralDtlMapper;
import com.jf.dao.MemberAccountCustomMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.ViolateOrder;

@Service
@Transactional
public class MemberAccountService extends BaseService<MemberAccount,MemberAccountExample> {
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	@Autowired
	private MemberAccountCustomMapper memberAccountCustomMapper;
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	@Autowired
	private ViolateOrderService violateOrderService;
	
	@Autowired
	public void setMemberAccountMapper(MemberAccountMapper memberAccountMapper) {
		super.setDao(memberAccountMapper);
		this.memberAccountMapper = memberAccountMapper;
	}

	public void update(MemberAccount memberAccount, IntegralDtl integralDtl,ViolateOrder violateOrder) {
		integralDtlMapper.insertSelective(integralDtl);
		this.updateByPrimaryKeySelective(memberAccount);
		violateOrderService.updateByPrimaryKeySelective(violateOrder);
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
}

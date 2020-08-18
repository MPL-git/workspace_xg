package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.CashTransferCustomMapper;
import com.jf.dao.CashTransferMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.dao.WithdrawOrderMapper;
import com.jf.dao.WithdrawOrderStatusLogMapper;
import com.jf.entity.CashTransfer;
import com.jf.entity.CashTransferCustom;
import com.jf.entity.CashTransferCustomExample;
import com.jf.entity.CashTransferExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderStatusLog;

@Service
@Transactional
public class CashTransferService extends BaseService<CashTransfer, CashTransferExample> {

	@Autowired
	private CashTransferMapper cashTransferMapper;

	@Autowired
	private CashTransferCustomMapper cashTransferCustomMapper;

	@Autowired
	private WithdrawOrderMapper withdrawOrderMapper;

	@Autowired
	private MemberAccountService memberAccountService;

	@Autowired
	private MemberAccountDtlService memberAccountDtlService;

	@Autowired
	private WithdrawOrderStatusLogMapper withdrawOrderStatusLogMapper;

	@Autowired
	public void setCashTransferMapper(CashTransferMapper cashTransferMapper) {
		super.setDao(cashTransferMapper);
		this.cashTransferMapper = cashTransferMapper;
	}

	public int countByCustomExample(CashTransferCustomExample example) {
		return cashTransferCustomMapper.countByCustomExample(example);
	}

	public List<CashTransferCustom> selectByCustomExample(
			CashTransferCustomExample example) {
		return cashTransferCustomMapper.selectByCustomExample(example);
	}

	public String update(String ids, Integer staffID) {
		String returnStr = "";
		Date date = new Date();
		String[] idStr = ids.split(",");
		for(String id : idStr) {
			CashTransfer cashTransfer = cashTransferMapper.selectByPrimaryKey(Integer.parseInt(id));
			WithdrawOrder withdrawOrder = withdrawOrderMapper.selectByPrimaryKey(cashTransfer.getWithdrawOrderId());
			// 会员账户，冻结金额不能小于零
			MemberAccount account = memberAccountService.updateMemberAccountBalanceAndFreeze(withdrawOrder.getAccId(), withdrawOrder.getAmount().multiply(new BigDecimal("-1")), date);
			if(account == null) {
				if(StringUtil.isEmpty(returnStr)) {
					returnStr = withdrawOrder.getMemberId().toString();
				}else {
					returnStr += "," + withdrawOrder.getMemberId().toString();
				}
			}else {
				BigDecimal balance = account.getBalance();
				BigDecimal freeze = account.getFreeze();
				cashTransfer.setId(Integer.parseInt(id));
				cashTransfer.setStatus("1");
				cashTransfer.setUpdateBy(staffID);
				cashTransfer.setUpdateDate(date);
				cashTransferMapper.updateByPrimaryKeySelective(cashTransfer);// 修改提现信息
				withdrawOrder.setUpdateBy(staffID);
				withdrawOrder.setStatus("4");
				withdrawOrder.setUpdateDate(date);
				withdrawOrderMapper.updateByPrimaryKeySelective(withdrawOrder);// 修改提现单信息
				WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
				log.setStatus("4");
				log.setWithdrawOrderId(withdrawOrder.getId());
				log.setUpdateDate(date);
				withdrawOrderStatusLogMapper.insertSelective(log);// 添加日志
				MemberAccountDtl thawAccountDtl = new MemberAccountDtl();
				thawAccountDtl.setAccId(account.getId());
				thawAccountDtl.setTallyMode("1");// 1.入账2.出账
				thawAccountDtl.setTotalFreeze(freeze.subtract(withdrawOrder.getAmount()));
				thawAccountDtl.setFreezeAmount(withdrawOrder.getAmount());
				thawAccountDtl.setBalance(balance);
				thawAccountDtl.setBizType("7");
				thawAccountDtl.setBizId(withdrawOrder.getId());
				thawAccountDtl.setUpdateDate(date);
				thawAccountDtl.setRemarks("线下转账成功，解冻金额");
				memberAccountDtlService.insertSelective(thawAccountDtl);// 解冻明细
				// 提现明细
				MemberAccountDtl withdrawAccountDtl = new MemberAccountDtl();
				withdrawAccountDtl.setAccId(account.getId());
				withdrawAccountDtl.setTallyMode("2");// 1.入账2.出账
				withdrawAccountDtl.setAmount(withdrawOrder.getAmount());
				withdrawAccountDtl.setBalance(balance.subtract(withdrawOrder.getAmount()));
				withdrawAccountDtl.setBizType("2");
				withdrawAccountDtl.setBizId(withdrawOrder.getId());
				withdrawAccountDtl.setUpdateDate(date);
				withdrawAccountDtl.setRemarks("线下转账成功，扣除余额");
				memberAccountDtlService.insertSelective(withdrawAccountDtl);
			}
		}
		return returnStr;
	}
	
	public String updateStatus(String id, Integer staffID, String rejectReason) {
		String returnStr = null;
		Date date = new Date();
		CashTransfer cashTransfer = cashTransferMapper.selectByPrimaryKey(Integer.parseInt(id));
		WithdrawOrder withdrawOrder = withdrawOrderMapper.selectByPrimaryKey(cashTransfer.getWithdrawOrderId());
		// 解冻金额
		MemberAccountExample memberAccountExample = new MemberAccountExample();
		memberAccountExample.createCriteria().andDelFlagEqualTo("0").andIsEffectEqualTo("1").andIdEqualTo(withdrawOrder.getAccId());
		List<MemberAccount> memberAccountList = memberAccountService.selectByExample(memberAccountExample);
		if(memberAccountList != null && memberAccountList.size() > 0) {
			MemberAccount memberAccount = memberAccountList.get(0);
			if(memberAccount.getFreeze().compareTo(withdrawOrder.getAmount()) != -1) {
				BigDecimal fze = memberAccount.getFreeze().subtract(withdrawOrder.getAmount());
				MemberAccount mAccount = new MemberAccount();
				mAccount.setId(memberAccount.getId());
				mAccount.setFreeze(fze);
				mAccount.setUpdateDate(date);
				memberAccountService.updateByPrimaryKeySelective(mAccount);
				
				BigDecimal balance = memberAccount.getBalance();
				BigDecimal freeze = memberAccount.getFreeze();
				cashTransfer.setId(Integer.parseInt(id));
				cashTransfer.setStatus("2");
				cashTransfer.setRejectReason(rejectReason);
				cashTransfer.setUpdateBy(staffID);
				cashTransfer.setUpdateDate(date);
				cashTransferMapper.updateByPrimaryKeySelective(cashTransfer);// 修改提现信息
				withdrawOrder.setUpdateBy(staffID);
				withdrawOrder.setStatus("7");
				withdrawOrder.setUpdateDate(date);
				withdrawOrderMapper.updateByPrimaryKeySelective(withdrawOrder);// 修改提现单信息
				WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
				log.setStatus("7");
				log.setWithdrawOrderId(withdrawOrder.getId());
				log.setUpdateDate(date);
				withdrawOrderStatusLogMapper.insertSelective(log);// 添加日志
				MemberAccountDtl thawAccountDtl = new MemberAccountDtl();
				thawAccountDtl.setAccId(memberAccount.getId());
				thawAccountDtl.setTallyMode("1");// 1.入账2.出账
				thawAccountDtl.setTotalFreeze(freeze.subtract(withdrawOrder.getAmount()));
				thawAccountDtl.setFreezeAmount(withdrawOrder.getAmount());
				thawAccountDtl.setBalance(balance);
				thawAccountDtl.setBizType("7");
				thawAccountDtl.setBizId(withdrawOrder.getId());
				thawAccountDtl.setUpdateDate(date);
				thawAccountDtl.setRemarks("线下转账驳回，解冻金额");
				memberAccountDtlService.insertSelective(thawAccountDtl);// 解冻明细
			}else {
				returnStr = "会员账户冻结为负数";
			}
		}else {
			returnStr = "会员账户已冻结";
		}
		return returnStr;
	}

}

package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WithdrawOrderMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderExample;
import com.jf.entity.WithdrawOrderStatusLog;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:27:16 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WithdrawOrderService extends BaseService<WithdrawOrder,WithdrawOrderExample> {
	private static Logger logger = LoggerFactory.getLogger(WithdrawOrderService.class);
	@Autowired
	private WithdrawOrderMapper withdrawOrderMapper;
	@Autowired
	private WithdrawOrderStatusLogService withdrawOrderStatusLogService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private MemberAccountDtlService memberAccountDtlService;

	@Autowired
	public void setWithdrawOrderMapper(WithdrawOrderMapper withdrawOrderMapper) {
		this.setDao(withdrawOrderMapper);
		this.withdrawOrderMapper = withdrawOrderMapper;
	}

	public void batchUpdate(List<Integer> idList,String status,List<WithdrawOrder> withdrawOrderList) {
		Date now = new Date();
		if(idList!=null && idList.size()>0){
			WithdrawOrder wo = new WithdrawOrder();
			wo.setStatus(status);//3.提现中 6.提现失败
			wo.setUpdateDate(now);
			WithdrawOrderExample example = new WithdrawOrderExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
			this.updateByExampleSelective(wo, example);
			List<WithdrawOrderStatusLog> withdrawOrderStatusLogs = new ArrayList<WithdrawOrderStatusLog>();
			for(Integer withdrawOrderId:idList){
				WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
				log.setDelFlag("0");
				log.setCreateDate(now);
				log.setWithdrawOrderId(withdrawOrderId);
				log.setStatus(status);//3.提现中 6.提现失败
				withdrawOrderStatusLogs.add(log);
			}
			withdrawOrderStatusLogService.batchInsert(withdrawOrderStatusLogs);
		}
    	if(status.equals("6")){//6.提现失败
    		for(WithdrawOrder withdrawOrder:withdrawOrderList){
    			MemberAccount memberAccount = memberAccountService.selectByPrimaryKey(withdrawOrder.getAccId());
    			MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
    			memberAccountDtl.setAccId(withdrawOrder.getAccId());
    			memberAccountDtl.setTallyMode("1");
    			memberAccountDtl.setFreezeAmount(withdrawOrder.getAmount());
    			memberAccountDtl.setTotalFreeze(memberAccount.getProfitInviteFreeze().subtract(withdrawOrder.getAmount()));
    			memberAccountDtl.setBalance(memberAccount.getProfitInviteBalance());
    			memberAccountDtl.setBizType("11");
    			memberAccountDtl.setBizId(withdrawOrder.getId());
    			memberAccountDtl.setCreateDate(now);
    			memberAccountDtl.setDelFlag("0");
    			
    			memberAccount.setProfitInviteFreeze(memberAccount.getProfitInviteFreeze().subtract(withdrawOrder.getAmount()));
    			memberAccount.setUpdateDate(now);
    			memberAccountService.updateByPrimaryKeySelective(memberAccount);
    			memberAccountDtlService.insertSelective(memberAccountDtl);
    		}
    	}
	}
	
}

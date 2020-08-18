package com.jf.service;

import java.util.HashMap;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberAccountDtlCustomMapper;
import com.jf.dao.MemberAccountDtlMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountDtlExample;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderStatusLog;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:17:41 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberAccountDtlService extends BaseService<MemberAccountDtl, MemberAccountDtlExample> {
	@Autowired
	private MemberAccountDtlMapper memberAccountDtlMapper;
	
	@Autowired
	private MemberAccountDtlCustomMapper memberAccountDtlCustomMapper;
	
	@Autowired
	private WithdrawOrderService withdrawOrderService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private WithdrawOrderStatusLogService withdrawOrderStatusLogService;
	
	@Autowired
	public void setMemberAccountDtlMapper(MemberAccountDtlMapper memberAccountDtlMapper) {
		this.setDao(memberAccountDtlMapper);
		this.memberAccountDtlMapper = memberAccountDtlMapper;
	}

	public boolean isReceiveSubFristReward(Integer accId, Integer combineOrderId, Integer subMemberId) {
		boolean isReceive = true;
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("accId", accId);
		paramsMap.put("combineOrderId", combineOrderId);
		paramsMap.put("subMemberId", subMemberId);
		int count = memberAccountDtlCustomMapper.getIsReceiveSubFristReward(paramsMap);
		if(count <= 0){
			isReceive = false;
		}
		return isReceive;
	}

	public void save(WithdrawOrder withdrawOrder,MemberAccountDtl memberAccountDtl, MemberAccountDtl mad,MemberAccount memberAccount,WithdrawOrderStatusLog log) {
		if(withdrawOrder!=null){
			withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
		}
		if(memberAccountDtl!=null){
			this.insertSelective(memberAccountDtl);
		}
		if(mad!=null){
			this.insertSelective(mad);
		}
		if(memberAccount!=null){
			memberAccountService.updateByPrimaryKeySelective(memberAccount);
		}
		if(log!=null){
			withdrawOrderStatusLogService.insertSelective(log);
		}
	}
	
}

package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberAccountDtlMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountDtlExample;
import com.jf.entity.MemberInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private MemberAccountService memberAccountService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private BankService bankService;

	@Autowired
	public void setMemberAccountDtlMapper(MemberAccountDtlMapper memberAccountDtlMapper) {
		this.setDao(memberAccountDtlMapper);
		this.memberAccountDtlMapper = memberAccountDtlMapper;
	}

	public void addAccountDtl(Integer accId, BigDecimal freezeAmount, BigDecimal totalFreeze, Integer memberId,
			String tallyMode, String bizType, Integer bizId, BigDecimal amount) {
		MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
		memberAccountDtl.setAccId(accId);
		memberAccountDtl.setAmount(amount);
		memberAccountDtl.setTallyMode(tallyMode);
		memberAccountDtl.setFreezeAmount(freezeAmount);
		memberAccountDtl.setTotalFreeze(totalFreeze);
		memberAccountDtl.setBizType(bizType);
		memberAccountDtl.setBizId(bizId);
		memberAccountDtl.setCreateBy(memberId);
		memberAccountDtl.setCreateDate(new Date());
		memberAccountDtl.setDelFlag("0");;
		insertSelective(memberAccountDtl);
	}

	public List<Map<String, Object>> getMemberBalanceDtl(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = reqDataJson.getInt("pageSize");
		List<Map<String, Object>> dataList = new ArrayList<>();
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		MemberAccountDtlExample memberAccountDtlExample = new MemberAccountDtlExample();
		memberAccountDtlExample.createCriteria().andAccIdEqualTo(memberAccount.getId()).andBizTypeIn(Arrays.asList("12","8","9")).andDelFlagEqualTo("0");
		memberAccountDtlExample.setOrderByClause("id desc");
		memberAccountDtlExample.setLimitStart(currentPage * pageSize);
		memberAccountDtlExample.setLimitSize(pageSize);
		List<MemberAccountDtl> accountDtls = selectByExample(memberAccountDtlExample);
		if(CollectionUtils.isNotEmpty(accountDtls)){
			for (MemberAccountDtl memberAccountDtl : accountDtls) {
				Map<String, Object> dataMap = new HashMap<>();
				String bizType = memberAccountDtl.getBizType();
				String title = "邀请奖励";
				String createDateStr = DateUtil.getFormatDate(memberAccountDtl.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
				String tallyMode = memberAccountDtl.getTallyMode();
				String tallyModeStr = tallyMode.equals("1") ? "+" : "-";
				BigDecimal amount = memberAccountDtl.getAmount();
				String remarks = "";
				if("8".equals(bizType)){
					title = "邀新奖励";
				}else if("9".equals(bizType)){
					title = "订单分润";
				}else if("12".equals(bizType)){
					title = "提现支出";
					remarks = "(含手续费1元)";
				}
				dataMap.put("title", title);
				dataMap.put("createDateStr", "("+createDateStr+")");
				dataMap.put("amount", tallyModeStr+amount+"元");
				dataMap.put("tallyModel", tallyMode);
				dataMap.put("remarks", remarks);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	public Map<String, Object> getMemberAccountInfo(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		List<Map<String, Object>> bankList = bankService.getBankInfo();
		Map<String, Object> map = new HashMap<>();
		map.put("memberPic", StringUtil.getPic(memberInfo.getPic(), ""));
		map.put("nick", memberInfo.getNick());
		map.put("profitInviteBalance", memberAccount.getProfitInviteBalance().subtract(memberAccount.getProfitInviteFreeze()));
		map.put("minWithdrawAmount", 50);
		map.put("remarks", "50元起提，手续费1元/笔");
		map.put("bankList", bankList);
		map.put("serviceCharge", "1");
		return map;
	}

	
}

package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.dao.WithdrawOrderMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberInfo;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderExample;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:27:16 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WithdrawOrderService extends BaseService<WithdrawOrder, WithdrawOrderExample> {
	@Autowired
	private WithdrawOrderMapper withdrawOrderMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private WithdrawOrderStatusLogService withdrawOrderStatusLogService;

	public static final BigDecimal MIN_WITHDRAW_AMOUMT = new BigDecimal("50");//分润金额最低提现金额
	private static final BigDecimal MAX_WITHDRAW_AMOUNT = new BigDecimal("10000");//分润金额最高提现金额

	@Autowired
	public void setWithdrawOrderMapper(WithdrawOrderMapper withdrawOrderMapper) {
		this.setDao(withdrawOrderMapper);
		this.withdrawOrderMapper = withdrawOrderMapper;
	}
	
	/**
	 * 分润金额申请提现
	 * @param reqPRM
	 * @param reqDataJson
	 */
	public void applyCashWithdraw(JSONObject reqPRM, JSONObject reqDataJson) {
		String alipayAccount = reqDataJson.getString("alipayAccount");
		String alipayName = reqDataJson.getString("alipayName");
		Integer memberId = reqDataJson.getInt("memberId");
		Integer bankBranchId = null;
		Integer bankId = null;
		if(!JsonUtils.isEmpty(reqDataJson, "bankBranchId")){
			bankBranchId = reqDataJson.getInt("bankBranchId");
		}
		if(!JsonUtils.isEmpty(reqDataJson, "bankId")){
			bankId = reqDataJson.getInt("bankId");
		}
		String orderCode = "W"+CommonUtil.getOrderCode();
		BigDecimal amount = new BigDecimal(reqDataJson.getString("amount"));
	    boolean b = StringUtil.checkBankCard(alipayAccount);
	    if(!b){
    		throw new ArgException("请输入正确的银行卡号");
    	}
		if(amount.compareTo(MIN_WITHDRAW_AMOUMT) < 0){
			throw new ArgException("50元起提，请重新输入~");
		}
		if (amount.compareTo(MAX_WITHDRAW_AMOUNT) >= 0) {
			throw new ArgException("单次提现要小于10000元，请重新输入~");
		}
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		if(StringUtil.isBlank(memberInfo.getMobile()) || !"1".equals(memberInfo.getmVerfiyFlag())){
			throw new ArgException("请先绑定手机");
		}
		WithdrawOrderExample withdrawOrderExample = new WithdrawOrderExample();
		withdrawOrderExample.createCriteria().andMemberIdEqualTo(memberId).andWithdrawTypeEqualTo("3").andDelFlagEqualTo("0");
		withdrawOrderExample.setOrderByClause("id desc");
		withdrawOrderExample.setLimitStart(0);
		withdrawOrderExample.setLimitSize(1);
		List<WithdrawOrder> withdrawOrders = selectByExample(withdrawOrderExample);
		if(CollectionUtils.isNotEmpty(withdrawOrders)){
			WithdrawOrder withdrawOrder = withdrawOrders.get(0);
			if(DateUtil.addDay(withdrawOrder.getCreateDate(), 1).after(new Date())){
				throw new ArgException("每24小时只能提现一次哦~");
			}
		}
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		//订单
		WithdrawOrder withdrawOrder = new WithdrawOrder();
		withdrawOrder.setOrderCode(orderCode);
		withdrawOrder.setMemberId(memberId);
		withdrawOrder.setAccId(memberAccount.getId());
		withdrawOrder.setBankBranchId(bankBranchId);
		withdrawOrder.setBankId(bankId);
		withdrawOrder.setAmount(amount);
		withdrawOrder.setStatus("1");
		withdrawOrder.setWithdrawType("3");
		withdrawOrder.setRealName(alipayName);
		withdrawOrder.setAlipayAccount(alipayAccount);
		withdrawOrder.setNovaBalance(memberAccount.getProfitInviteBalance().subtract(memberAccount.getProfitInviteFreeze()));
		withdrawOrder.setCreateBy(memberId);
		withdrawOrder.setCreateDate(new Date());
		withdrawOrder.setDelFlag("0");
		insertSelective(withdrawOrder);
		//日志
		withdrawOrderStatusLogService.addLog(withdrawOrder.getId(),"1",memberId);
		//判断余额是否足够 且更新冻结金额
		memberAccountService.updateAccountFreece("1", memberAccount,amount,withdrawOrder.getId());
	}
	//TODO 提现记录
	public List<Map<String, Object>> getMemberWithdrawOrderDtl(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = reqDataJson.getInt("pageSize");
		List<Map<String, Object>> dataList = new ArrayList<>();
		WithdrawOrderExample withdrawOrderExample = new WithdrawOrderExample();
		withdrawOrderExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0").andWithdrawTypeEqualTo("3");
		withdrawOrderExample.setOrderByClause("id desc");
		withdrawOrderExample.setLimitStart(currentPage * pageSize);
		withdrawOrderExample.setLimitSize(pageSize);
		List<WithdrawOrder> withdrawOrders = selectByExample(withdrawOrderExample);
		if(CollectionUtils.isNotEmpty(withdrawOrders)){
			for (WithdrawOrder withdrawOrder : withdrawOrders) {
				Map<String, Object> dataMap = new HashMap<>();
				String status = withdrawOrder.getStatus();
				String title = "";
				String createDateStr = DateUtil.getFormatDate(withdrawOrder.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
				BigDecimal amount = withdrawOrder.getAmount();
				String innerRemarks = "";
				String yyRejectReason = withdrawOrder.getYyRejectReason();
				String cwRejectReason = withdrawOrder.getCwRejectReason();
				String remarks = "(含手续费1元)";
				String bankCard = withdrawOrder.getAlipayAccount();
				if("1".equals(status)){
					title += "(申请中)";
				}else if("2".equals(status)){
					title += "(转账中)";
				}else if("3".equals(status)){
					title += "(转账中)";
				}else if("4".equals(status)){
					title += "(提现成功)";
				}else if("5".equals(status)){
					title += "(审核不通过？)";
				}else if("6".equals(status)){
					title += "(提现失败)";
				}
				if(StringUtil.isBlank(cwRejectReason)){
					innerRemarks = yyRejectReason;
				}else{
					innerRemarks = cwRejectReason;
				}
				dataMap.put("title", title);
				dataMap.put("status", status);
				dataMap.put("createDateStr", createDateStr);
				dataMap.put("amount", amount+"元");
				dataMap.put("innerRemarks", StringUtil.isBlank(innerRemarks) ? "" : innerRemarks);
				dataMap.put("remarks", remarks);
				dataMap.put("bankCard", bankCard);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	
}

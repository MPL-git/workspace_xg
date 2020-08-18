package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberAccountCustomMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月22日 下午6:47:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberAccountService extends BaseService<MemberAccount, MemberAccountExample> {
	
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	@Autowired
	private MemberAccountCustomMapper memberAccountCustomMapper;
	@Resource
	private MemberGroupService memberGroupService;
	@Resource
	private MemberAccountDtlService memberAccountDtlService;
	
	@Autowired
	public void setMemberAccountMapper(MemberAccountMapper memberAccountMapper) {
		this.setDao(memberAccountMapper);
		this.memberAccountMapper = memberAccountMapper;
	}
	@Resource
	private GrowthDtlService growthDtlService;
	@Resource
	private IntegralDtlService integralDtlService;

	public MemberAccount findAccountByMemberId(Integer memberId) {
		MemberAccountExample memberAccountExample = new MemberAccountExample();
		memberAccountExample.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberAccount> memberAccounts = memberAccountMapper.selectByExample(memberAccountExample);
		if(CollectionUtils.isNotEmpty(memberAccounts)){
			return memberAccounts.get(0);
		}
		return null;
	}

	public void updateMemberGroupInfo(Integer integral, Integer gValue, Integer id) {
		MemberAccount memberAccount = findAccountByMemberId(id);
		if(memberAccount != null){
			memberAccount.setIntegral(memberAccount.getIntegral()+integral);
			memberAccount.setgValue(memberAccount.getgValue()+gValue);
			updateModel(memberAccount);
			//成长值
			growthDtlService.addGrowthDtl(memberAccount.getgValue(), gValue, memberAccount.getId(), id, null, Const.INTEGRAL_TYPE_DATA_PERFECT);
			//积分
			integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, Const.INTEGRAL_TYPE_DATA_PERFECT, 
					integral, memberAccount.getIntegral(), null, id,"1");
			memberGroupService.updateMemberGroup(memberAccount.getgValue(), id);
		}
	}

	public void updateModel(MemberAccount memberAccount) {
		memberAccount.setUpdateDate(new Date());
		memberAccountMapper.updateByPrimaryKeySelective(memberAccount);
	}

	public List<MemberAccount> findListQuery(QueryObject queryObject) {
		
		return memberAccountMapper.selectByExample(builderQuery(queryObject));
	}

	private MemberAccountExample builderQuery(QueryObject queryObject) {
		MemberAccountExample example = new MemberAccountExample();
		MemberAccountExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("memberId") != null){
			criteria.andMemberIdEqualTo(queryObject.getQueryToInt("memberId"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}

	
	/**
	 * 申请时只要冻结金额
	 * @param bizId 
	 */
	public void updateAccountFreece(String type, MemberAccount memberAccount, BigDecimal freezeAmount, Integer bizId) {
		//type 1分润冻结金额 2 账户冻结金额
		if("1".equals(type)){
			BigDecimal profitInviteBalance = memberAccount.getProfitInviteBalance();
			BigDecimal profitInviteFreeze = memberAccount.getProfitInviteFreeze();
			if(freezeAmount.compareTo(profitInviteBalance.subtract(profitInviteFreeze)) > 0){
				throw new ArgException("您的余额不足，请重新输入~");
			}
			memberAccount.setFreeze(freezeAmount);
			memberAccount.setUpdateDate(new Date());
			int count = memberAccountCustomMapper.updateAccountProfitInviteFreece(memberAccount);
			if(count <= 0){
				throw new ArgException("您的余额不足，请重新输入~");
			}
			//日志
			memberAccountDtlService.addAccountDtl(memberAccount.getId(),freezeAmount,profitInviteFreeze.add(freezeAmount),memberAccount.getMemberId(),Const.INTEGRAL_TALLY_MODE_ACCOUNT,"10",bizId,null);
		}else{
			throw new ArgException("类型异常~");
		}
	}

	public void addGiftIntegral(Integer integral, MemberInfo memberInfo, Integer memberId, String type) {
		MemberAccount memberAccount = findAccountByMemberId(memberId);
		memberAccount.setIntegral(memberAccount.getIntegral()+integral);
		memberAccount.setUpdateDate(new Date());
		updateByPrimaryKeySelective(memberAccount);
		integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, type, integral, memberAccount.getIntegral(), null, memberId, "");
	}

	public void addGiftMemberAmount(BigDecimal amount, MemberInfo memberInfo, Integer memberId) {
		MemberAccount memberAccount = findAccountByMemberId(memberId);
		memberAccount.setProfitInviteBalance(memberAccount.getProfitInviteBalance().add(amount));
		memberAccount.setUpdateDate(new Date());
		updateByPrimaryKeySelective(memberAccount);
		
		memberAccountDtlService.addAccountDtl(memberAccount.getId(), null, null, memberId, Const.INTEGRAL_TALLY_MODE_INCOME, "8", null,amount);
	}
	
}

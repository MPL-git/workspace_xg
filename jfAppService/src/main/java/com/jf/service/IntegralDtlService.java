package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.dao.IntegralDtlCustomMapper;
import com.jf.dao.IntegralDtlMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlCustom;
import com.jf.entity.IntegralDtlExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.SvipMemberSetting;
import com.jf.entity.SvipOrder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午6:32:37 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class IntegralDtlService extends BaseService<IntegralDtl, IntegralDtlExample> {
	
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	@Autowired
	private IntegralDtlCustomMapper integralDtlCustomMapper;
	@Autowired
	private MemberControlService memberControlService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private SvipOrderService svipOrderService;
	@Autowired
	private SvipMemberSettingService svipMemberSettingService;
	
	@Autowired
	public void setIntegralDtlMapper(IntegralDtlMapper integralDtlMapper) {
		this.setDao(integralDtlMapper);
		this.integralDtlMapper = integralDtlMapper;
	}
	

	public List<IntegralDtlCustom> getIntegralDtlList(Map<String, Object> params) {

		return integralDtlCustomMapper.getIntegralDtlList(params);
	}

	public Integer getIntegralDtlCount(Map<String, Object> params) {
		
		return integralDtlCustomMapper.getIntegralDtlCount(params);
	}


	public void addIntegralDtl(Integer accId, String tallyMode, String type, Integer integral, Integer balance,Integer orderId , Integer memberId, String bizType) {
		IntegralDtl integralDtl = new IntegralDtl();
		integralDtl.setAccId(accId);
		integralDtl.setBizType(bizType);
		integralDtl.setIntegral(integral);
		integralDtl.setBalance(balance);
		integralDtl.setTallyMode(tallyMode);
		integralDtl.setType(Integer.valueOf(type));
		integralDtl.setOrderId(orderId);
		integralDtl.setCreateBy(memberId);
		integralDtl.setDelFlag("0");
		saveModel(integralDtl);
		
	}


	public void saveModel(IntegralDtl integralDtl) {
		integralDtl.setCreateDate(new Date());
		integralDtlMapper.insertSelective(integralDtl);
	}

	/**
	 * 
	 * 方法描述 ：获取进出账积分
	 * @author  chenwf: 
	 * @date 创建时间：2017年12月18日 下午5:07:19 
	 * @version
	 * @param params
	 * @return
	 */
	public Integer getCurrentYearIntegral(Map<String, Object> params) {
		
		return integralDtlCustomMapper.getCurrentYearIntegral(params);
	}


	public boolean getIsRecMonthIntegral(Integer memberId) {
		boolean isRecMonthIntegral = false;
		Date currentDate = new Date();
		Date beginDate = DateUtil.getMonthBeginData(currentDate);
		Date endDate = DateUtil.getMonthEndData(currentDate);
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("beginDate", beginDate);
		paramsMap.put("endDate", endDate);
		paramsMap.put("memberId", memberId);
		paramsMap.put("type", Const.INTEGRAL_TYPE_SVIP_MONTH_INTEGRAL);
		int count = integralDtlCustomMapper.getIntegralDtlTypeCount(paramsMap);
		if(count > 0){
			isRecMonthIntegral = true;
		}
		return isRecMonthIntegral;
	}


	public void svipRecMonthIntegral(Integer memberId) {
		Date currentDate = new Date();
		Integer integral = 0;
		boolean isSVip = memberInfoService.isRealSVip(null, memberId);
		if(!isSVip){
			throw new ArgException("您的会员已过期，请续费SVIP超级会员!");
		}
		boolean isRecMonthIntegral = getIsRecMonthIntegral(memberId);
		if(isRecMonthIntegral){
			throw new ArgException("您当月已领取过积分，请等下月再次领取!");
		}
		memberControlService.findMemberIsBlack(memberId, "6", null);//并发控制处理
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		if(memberAccount == null){
			throw new ArgException("非法用户!");
		}
		List<SvipOrder> svipOrders = svipOrderService.findPaySuccessOrder(memberId);
		if(CollectionUtils.isNotEmpty(svipOrders)){
			SvipMemberSetting svipMemberSetting;
			if (svipOrders.get(0).getSvipMemberSettingId() == 0) {
				svipMemberSetting = svipMemberSettingService.getByYear(svipOrders.get(0).getYearsOfPurchase());
			}else{
				svipMemberSetting = svipMemberSettingService.selectByPrimaryKey(svipOrders.get(0).getSvipMemberSettingId());
			}
			if(svipMemberSetting != null){
				integral = svipMemberSetting.getMonthIntegral();
			}
		}
		memberAccount.setIntegral(memberAccount.getIntegral() + integral);
		memberAccount.setUpdateBy(memberId);
		memberAccount.setUpdateDate(currentDate);
		memberAccountService.updateByPrimaryKeySelective(memberAccount);
		addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, Const.INTEGRAL_TYPE_SVIP_MONTH_INTEGRAL,
				integral, memberAccount.getIntegral(), svipOrders.get(0).getId(), memberId, "6");
	}

	/**
	 * 是否领取新星攻略积分
	 * @param accId
	 * @param integralType 
	 * @return
	 */
	public boolean getIsNoviceIntegral(Integer accId, Integer integralType) {
		IntegralDtlExample integralDtlExample = new IntegralDtlExample();
		integralDtlExample.createCriteria().andAccIdEqualTo(accId).andTypeEqualTo(integralType).andDelFlagEqualTo("0");
		return countByExample(integralDtlExample) > 0 ? true : false;
	}


	public void save(IntegralDtl integralDtl, MemberAccount memberAccount) {
		this.insertSelective(integralDtl);
		memberAccountService.updateByPrimaryKeySelective(memberAccount);
	}
		
}

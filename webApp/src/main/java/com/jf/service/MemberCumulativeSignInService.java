package com.jf.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberCumulativeSignInMapper;
import com.jf.entity.CumulativeSignInSetting;
import com.jf.entity.CumulativeSignInSettingExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberCumulativeSignIn;
import com.jf.entity.MemberCumulativeSignInExample;
import com.jf.entity.MemberMonthSignIn;
import com.jf.entity.MemberMonthSignInExample;

import net.sf.json.JSONObject;

@Service
@Transactional
public class MemberCumulativeSignInService extends BaseService<MemberCumulativeSignIn, MemberCumulativeSignInExample> {
	@Autowired
	private MemberCumulativeSignInMapper memberCumulativeSignInMapper;
	@Autowired
	private CumulativeSignInSettingService cumulativeSignInSettingService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private IntegralDtlService integralDtlService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private CombineOrderService combineOrderService;
	@Autowired
	private MemberMonthSignInService memberMonthSignInService;
	@Autowired
	public void setMemberCumulativeSignInMapper(MemberCumulativeSignInMapper memberCumulativeSignInMapper) {
		this.setDao(memberCumulativeSignInMapper);
		this.memberCumulativeSignInMapper = memberCumulativeSignInMapper;
	}
	public Map<String, Object> addTiredSignGift(JSONObject reqPRM, JSONObject reqDataJson, Integer memberId, HttpServletRequest request) {
		Integer cumulativeSignInSettingId = reqDataJson.getInt("cumulativeSignInSettingId");
		String type = reqDataJson.getString("type");//1 直接领取 2结算页面领取
		Date currentDate = new Date();
		CumulativeSignInSettingExample cumulativeSignInSettingExample = new CumulativeSignInSettingExample();
		cumulativeSignInSettingExample.createCriteria().andIdEqualTo(cumulativeSignInSettingId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<CumulativeSignInSetting> inSettings = cumulativeSignInSettingService.selectByExample(cumulativeSignInSettingExample);
		if(CollectionUtils.isNotEmpty(inSettings)){
			CumulativeSignInSetting setting = inSettings.get(0);
			Integer integral = setting.getIntegral();
			String productCode = setting.getProductCode();
			String couponIds = setting.getCouponIds();
			Integer signInManageId = setting.getSignInManageId();
			Integer monthSignInCount = 0;
			Integer cumulativeSignInCount = setting.getCumulativeSignInCount();
			MemberMonthSignInExample memberMonthSignInExample = new MemberMonthSignInExample();
			memberMonthSignInExample.createCriteria().andMemberIdEqualTo(memberId).andSignInManageIdEqualTo(signInManageId).andDelFlagEqualTo("0");
			List<MemberMonthSignIn> memberMonthSignIns = memberMonthSignInService.selectByExample(memberMonthSignInExample);
			if(CollectionUtils.isNotEmpty(memberMonthSignIns)){
				monthSignInCount = memberMonthSignIns.get(0).getSignInCount();
			}
			if(monthSignInCount < cumulativeSignInCount){
				throw new ArgException("您好，还未达到累签次数，请保持每天签到");
			}
			MemberCumulativeSignInExample memberCumulativeSignInExample = new MemberCumulativeSignInExample();
			memberCumulativeSignInExample.createCriteria().andCumulativeSignInSettingIdEqualTo(cumulativeSignInSettingId).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
			List<MemberCumulativeSignIn> cumulativeSignIns = selectByExample(memberCumulativeSignInExample);
			if(CollectionUtils.isNotEmpty(cumulativeSignIns)){
				throw new ArgException("您已领取该累签奖品，请勿重复领取");
			}
			//先插入数据，数据库有控制并发处理
			MemberCumulativeSignIn memberCumulativeSignIn = new MemberCumulativeSignIn();
			memberCumulativeSignIn.setCumulativeSignInSettingId(setting.getId());
			memberCumulativeSignIn.setMemberId(memberId);
			memberCumulativeSignIn.setReceiveTime(currentDate);
			memberCumulativeSignIn.setCreateBy(memberId);
			memberCumulativeSignIn.setCreateDate(currentDate);
			memberCumulativeSignIn.setDelFlag("0");
			insertSelective(memberCumulativeSignIn);
			
			if(!StringUtil.isBlank(productCode) && "2".equals(type)){
				combineOrderService.addSignInOrder(currentDate, request,memberId,type,productCode);
			}
			if(integral != null){
				//赠送积分给用户
				MemberAccount account = memberAccountService.findAccountByMemberId(memberId);
				Integer balance = account.getIntegral() + integral;
				integralDtlService.addIntegralDtl(account.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, Const.INTEGRAL_TYPE_TIRED_SIGN_GIFT, integral, balance, memberCumulativeSignIn.getId(), memberId, "7");
				memberAccountService.updateModelByEx(account, null,balance,null, currentDate);
			}
			
			if(!StringUtil.isBlank(couponIds)){
				couponService.addSignInCoupon(memberId, couponIds, currentDate);
			}
		}
		return null;
	}
	
	
}

package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberMobileWeixinMapMapper;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberMobileWeixinMap;
import com.jf.entity.MemberMobileWeixinMapExample;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MemberMobileWeixinMapService extends BaseService<MemberMobileWeixinMap, MemberMobileWeixinMapExample> {
	@Autowired
	private MemberMobileWeixinMapMapper memberMobileWeixinMapMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MobileVerificationCodeService mobileVerificationCodeService;
	@Autowired
	public void setMemberMobileWeixinMapMapper(MemberMobileWeixinMapMapper memberMobileWeixinMapMapper) {
		this.setDao(memberMobileWeixinMapMapper);
		this.memberMobileWeixinMapMapper = memberMobileWeixinMapMapper;
	}
	
	/**
	 * 需求930，关联微信或关联手机用户
	 * @return 
	 */
	public Map<String, Object> getMemberRelation(JSONObject reqPRM,JSONObject reqDataJson){
		boolean isSuccess = false;
		String msg = "";
		String type = reqDataJson.getString("type");
		Integer memberId = reqDataJson.getInt("memberId");
		Date currentDate = new Date();
		if("1".equals(type)){//微信用户，未绑定手机
			String mobileVerificationCode = reqDataJson.getString("mobileVerificationCode");
			String mobile = reqDataJson.getString("mobile");
			if(!mobileVerificationCodeService.verify(mobile,mobileVerificationCode)){
				throw new ArgException("验证码错误!");
			}
			if(!StringUtil.isBlank(mobile)){
				MemberInfo memberInfo = memberInfoService.findModelByMobile(mobile);
				if(memberInfo != null){
					String weixinUnionid = memberInfo.getWeixinUnionid();
					if(StringUtil.isBlank(weixinUnionid)){
						//判断是否已被关联过
						boolean isMemberRelation = true;
						Integer mobileMemberId = memberInfo.getId();
						int count = getIsMemberRelation(memberId,mobileMemberId);
						if(count <= 0){
							isMemberRelation = false;
						}
						if(isMemberRelation){
							msg = "该用户已被关联!";
						}else{
							isSuccess = true;
							msg = "验证成功";
							MemberMobileWeixinMap weixinMap = new MemberMobileWeixinMap();
							weixinMap.setMobileMemberId(memberId);
							weixinMap.setWeixinMemberId(mobileMemberId);
							weixinMap.setCreateBy(memberId);
							weixinMap.setCreateDate(currentDate);
							weixinMap.setDelFlag("0");
							insertSelective(weixinMap);
						}
					}else{
						msg = "该手机已注册!";
					}
				}else{
					isSuccess = true;
					msg = "手机绑定成功";
					MemberInfo m = memberInfoService.findMemberById(memberId);
					m.setType("1");
					m.setMobile(mobile); 
					m.setmVerfiyFlag("1");
					m.setUpdateBy(memberId);
					m.setUpdateDate(currentDate);
					memberInfoService.updateByPrimaryKeySelective(m);
				}
			}else{
				msg = "手机验证失败!";
			}
		}else if("2".equals(type)){
			String weixinUnionid = reqDataJson.getString("weixinUnionid");
			if(!StringUtil.isBlank(weixinUnionid)){
				List<MemberInfo> memberInfos = memberInfoService.findModelByUnionId(weixinUnionid);
				if(CollectionUtils.isNotEmpty(memberInfos)){
					MemberInfo memberInfo = memberInfos.get(0);
					String mobile = memberInfo.getMobile();
					if(StringUtil.isBlank(mobile)){
						//判断是否已被关联过
						boolean isMemberRelation = true;
						Integer mobileMemberId = memberInfo.getId();
						int count = getIsMemberRelation(memberId,mobileMemberId);
						if(count <= 0){
							isMemberRelation = false;
						}
						if(isMemberRelation){
							msg = "该用户已被关联!";
						}else{
							isSuccess = true;
							msg = "微信验证成功";
							MemberMobileWeixinMap weixinMap = new MemberMobileWeixinMap();
							weixinMap.setMobileMemberId(mobileMemberId);
							weixinMap.setWeixinMemberId(memberId);
							weixinMap.setCreateBy(memberId);
							weixinMap.setCreateDate(currentDate);
							weixinMap.setDelFlag("0");
							insertSelective(weixinMap);
						}
					}else{
						msg = "该微信用户已注册!";
					}
				}else{
					isSuccess = true;
					msg = "微信绑定成功";
					MemberInfo m = memberInfoService.findMemberById(memberId);
					m.setWeixinUnionid(weixinUnionid);;
					m.setUpdateBy(memberId);
					m.setUpdateDate(currentDate);
					memberInfoService.updateByPrimaryKeySelective(m);
				}
			}else{
				msg = "微信验证失败!";
			}
		}else{
			msg = "微信类型异常!";
		}
		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", isSuccess);
		map.put("msg", msg);
		return map;
	}

	public Integer getIsMemberRelation(Integer memberId, Integer relationMemberId) {
		List<Integer> mId = new ArrayList<>();
		if(memberId != null){
			mId.add(memberId);
		}
		if(relationMemberId != null){
			mId.add(relationMemberId);
		}
		MemberMobileWeixinMapExample memberMobileWeixinMapExample = new MemberMobileWeixinMapExample();
		memberMobileWeixinMapExample.or().andMobileMemberIdIn(mId).andDelFlagEqualTo("0");
		memberMobileWeixinMapExample.or().andWeixinMemberIdIn(mId).andDelFlagEqualTo("0");
		return countByExample(memberMobileWeixinMapExample);
	}
}

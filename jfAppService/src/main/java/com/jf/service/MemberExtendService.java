package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberExtendMapper;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendExample;
import com.jf.entity.MemberInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberExtendService extends BaseService<MemberExtend, MemberExtendExample> {
	@Autowired
	private MemberExtendMapper memberExtendMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	public void setMemberExtendMapper(MemberExtendMapper memberExtendMapper) {
		this.setDao(memberExtendMapper);
		this.memberExtendMapper = memberExtendMapper;
	}
	public MemberInfo thirdExtendLogin(String thirdPartyMark, String thirdPartyId) {
		MemberInfo memberInfo = null;
		List<MemberExtend> memberExtends = findExtendMember(thirdPartyMark,thirdPartyId);
		if(CollectionUtils.isNotEmpty(memberExtends)){
			memberInfo = memberInfoService.selectByPrimaryKey(memberExtends.get(0).getMemberId());
		}
		return memberInfo;
	}
	private List<MemberExtend> findExtendMember(String thirdPartyMark, String thirdPartyId) {
		MemberExtendExample memberExtendExample = new MemberExtendExample();
		MemberExtendExample.Criteria criteria = memberExtendExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if("WB".equalsIgnoreCase(thirdPartyMark)){
			criteria.andWeiboIdEqualTo(thirdPartyId);
		}else if("QQ".equalsIgnoreCase(thirdPartyMark)){
			criteria.andQqIdEqualTo(thirdPartyId);
		}
		return selectByExample(memberExtendExample);
	}
	public void addModel(Integer memberId, String thirdPartyMark, String thirdPartyId, String sprChnl) {
		MemberExtend memberExtend = new MemberExtend();
		memberExtend.setMemberId(memberId);
		memberExtend.setCreateDate(new Date());
		memberExtend.setDelFlag("0");
		if("WB".equalsIgnoreCase(thirdPartyMark)){
			memberExtend.setWeiboId(thirdPartyId);
		}else if("QQ".equalsIgnoreCase(thirdPartyMark)){
			memberExtend.setQqId(thirdPartyId);
		}
		memberExtend.setSprChnl(sprChnl);
		memberExtendMapper.insertSelective(memberExtend);
	}
	public List<MemberExtend> findModelByMemberId(Integer memberId) {
		MemberExtendExample memberExtendExample = new MemberExtendExample();
		memberExtendExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		return selectByExample(memberExtendExample);
	}
	public void updateMemberExtendInfo(Integer memberId) {
		List<MemberExtend> memberExtends = findModelByMemberId(memberId);
		if(CollectionUtils.isNotEmpty(memberExtends)){
			MemberExtend memberExtend = memberExtends.get(0);
			if("1".equals(memberExtend.getSignInRemind())){
				memberExtend.setSignInRemind("0");
			}else{
				memberExtend.setSignInRemind("1");
			}
			updateByPrimaryKeySelective(memberExtend);
		}
		
	}
	
}

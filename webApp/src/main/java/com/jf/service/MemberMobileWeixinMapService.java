package com.jf.service;

import java.util.ArrayList;
import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberMobileWeixinMapMapper;
import com.jf.entity.MemberMobileWeixinMap;
import com.jf.entity.MemberMobileWeixinMapExample;

@Service
@Transactional
public class MemberMobileWeixinMapService extends BaseService<MemberMobileWeixinMap, MemberMobileWeixinMapExample> {
	@Autowired
	private MemberMobileWeixinMapMapper memberMobileWeixinMapMapper;
	@Autowired
	public void setMemberMobileWeixinMapMapper(MemberMobileWeixinMapMapper memberMobileWeixinMapMapper) {
		this.setDao(memberMobileWeixinMapMapper);
		this.memberMobileWeixinMapMapper = memberMobileWeixinMapMapper;
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

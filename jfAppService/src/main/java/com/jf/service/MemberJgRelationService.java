package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberJgRelationMapper;
import com.jf.entity.MemberJgRelation;
import com.jf.entity.MemberJgRelationExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberJgRelationService extends BaseService<MemberJgRelation, MemberJgRelationExample> {
	@Autowired
	private MemberJgRelationMapper memberJgRelationMapper;
	@Autowired
	public void setMemberJgRelationMapper(MemberJgRelationMapper memberJgRelationMapper) {
		this.setDao(memberJgRelationMapper);
		this.memberJgRelationMapper = memberJgRelationMapper;
	}
	
	public void addJgRelation(Integer memberId,String registrationId) {
		if(!StringUtil.isBlank(registrationId)){
			MemberJgRelation memberJgRelation = null;
			Date currentDate = new Date();
			MemberJgRelationExample memberJgRelationExample = new MemberJgRelationExample();
			memberJgRelationExample.createCriteria().andRegistrationIdEqualTo(registrationId).andDelFlagEqualTo("0");
			List<MemberJgRelation> jgRelations = this.selectByExample(memberJgRelationExample);
			if(CollectionUtils.isNotEmpty(jgRelations)){
				memberJgRelation = jgRelations.get(0);
				if(memberJgRelation.getMemberId() != memberId){
					memberJgRelation.setMemberId(memberId);
					memberJgRelation.setUpdateBy(memberId);
					memberJgRelation.setUpdateDate(currentDate);
					memberJgRelationMapper.updateByPrimaryKeySelective(memberJgRelation);
				}
			}else{
				memberJgRelation = new MemberJgRelation();
				memberJgRelation.setMemberId(memberId);
				memberJgRelation.setRegistrationId(registrationId);
				memberJgRelation.setCreateBy(memberId);
				memberJgRelation.setCreateDate(currentDate);
				memberJgRelation.setUpdateDate(currentDate);
				memberJgRelation.setDelFlag("0");
				memberJgRelationMapper.insertSelective(memberJgRelation);
			}
		}
	}
}

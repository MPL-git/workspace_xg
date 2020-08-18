package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AssistanceDtlCustomMapper;
import com.jf.dao.AssistanceDtlMapper;
import com.jf.entity.AssistanceDtl;
import com.jf.entity.AssistanceDtlCustom;
import com.jf.entity.AssistanceDtlExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberSupplementarySignIn;
import com.jf.entity.MemberSupplementarySignInExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AssistanceDtlService extends BaseService<AssistanceDtl, AssistanceDtlExample> {
	
	@Autowired
	private AssistanceDtlMapper assistanceDtlMapper;
	@Autowired
	private AssistanceDtlCustomMapper assistanceDtlCustomMapper;
	@Autowired
	private MemberSupplementarySignInService memberSupplementarySignInService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	public void setAssistanceDtlMapper(AssistanceDtlMapper assistanceDtlMapper) {
		this.setDao(assistanceDtlMapper);
		this.assistanceDtlMapper = assistanceDtlMapper;
	}
	
	
	public void updateAssistanceDtlInfo(MemberInfo memberInfo) {
		List<AssistanceDtlCustom> assistanceDtlCustoms = getMemberAssistanceInfo(memberInfo.getId());
		if(CollectionUtils.isNotEmpty(assistanceDtlCustoms)){
			AssistanceDtlCustom custom = assistanceDtlCustoms.get(0);
			Integer invitationCount = custom.getInvitationCount();//已邀请人数
			Integer invitationCountSetting = custom.getInvitationCountSetting();//设置的需邀请人数
			Integer memberSupplementarySignInId = custom.getMemberSupplementarySignInId();
			Integer supplementaryCardCount = custom.getSupplementaryCardCount();//获得的补签卡数量
			AssistanceDtlExample assistanceDtlExample = new AssistanceDtlExample();
			assistanceDtlExample.createCriteria().andIdEqualTo(custom.getId()).andProStatusEqualTo("0").andDelFlagEqualTo("0");
			AssistanceDtl assistanceDtl = new AssistanceDtl();
			assistanceDtl.setProStatus("1");
			assistanceDtl.setUpdateBy(memberInfo.getId());
			assistanceDtl.setUpdateDate(new Date());
			int assistanceDtlUpdateCount = updateByExampleSelective(assistanceDtl, assistanceDtlExample);
			if(assistanceDtlUpdateCount <= 0){
				return;
			}
			if(invitationCountSetting != null){
				if(invitationCountSetting > invitationCount){
					MemberSupplementarySignInExample memberSupplementarySignInExample = new MemberSupplementarySignInExample();
					memberSupplementarySignInExample.createCriteria().andIdEqualTo(memberSupplementarySignInId).andDelFlagEqualTo("0");
					List<MemberSupplementarySignIn> memberSupplementarySignIns = memberSupplementarySignInService.selectByExample(memberSupplementarySignInExample);
					if(CollectionUtils.isNotEmpty(memberSupplementarySignIns)){
						MemberSupplementarySignIn memberSupplementarySignInUpdate = memberSupplementarySignIns.get(0);
						memberSupplementarySignInUpdate.setInvitationCount(memberSupplementarySignInUpdate.getInvitationCount()+1);
						if(memberSupplementarySignInUpdate.getInvitationCount() == invitationCountSetting){
							memberSupplementarySignInUpdate.setStatus("2");
						}
						int count = memberSupplementarySignInService.updateByExampleSelective(memberSupplementarySignInUpdate, memberSupplementarySignInExample);
						if(count <= 0){
							return;
						}
						//赠送补签卡给用户
						if(memberSupplementarySignInUpdate.getInvitationCount() == invitationCountSetting){
							MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberSupplementarySignInUpdate.getMemberId());
							MemberAccount memberAccountUpdate = new MemberAccount();
							memberAccountUpdate.setId(memberAccount.getId());
							memberAccountUpdate.setSupplementaryCard(memberAccount.getSupplementaryCard()+supplementaryCardCount);
							memberAccountService.updateByPrimaryKeySelective(memberAccountUpdate);
						}
					}
					
				}
			}
		}
	}
	public List<AssistanceDtlCustom> getMemberAssistanceInfo(Integer memberId) {
		
		return assistanceDtlCustomMapper.getMemberAssistanceInfo(memberId);
	}
	
	
}

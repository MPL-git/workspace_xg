package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SupplementarySignInSettingMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.SupplementarySignInSetting;
import com.jf.entity.SupplementarySignInSettingExample;

@Service
@Transactional
public class SupplementarySignInSettingService extends BaseService<SupplementarySignInSetting, SupplementarySignInSettingExample> {
	@Autowired
	private SupplementarySignInSettingMapper supplementarySignInSettingMapper;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	public void setSupplementarySignInSettingMapper(SupplementarySignInSettingMapper supplementarySignInSettingMapper) {
		this.setDao(supplementarySignInSettingMapper);
		this.supplementarySignInSettingMapper = supplementarySignInSettingMapper;
	}
	/**
	 * 补签任务
	 * @param memberId
	 * @param pageSize 
	 * @param currentPage 
	 * @return
	 */
	public Map<String, Object> getSupplementarySignTaskList(Integer memberId, Integer currentPage, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Integer memberSupplementaryCardNum = 0;
		SupplementarySignInSettingExample supplementarySignInSettingExample = new SupplementarySignInSettingExample();
		supplementarySignInSettingExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		supplementarySignInSettingExample.setLimitStart(currentPage * pageSize);
		supplementarySignInSettingExample.setLimitSize(pageSize);
		supplementarySignInSettingExample.setOrderByClause("invitation_count,id desc");
		List<SupplementarySignInSetting> supplementarySignInSettings = selectByExample(supplementarySignInSettingExample);
		if(CollectionUtils.isNotEmpty(supplementarySignInSettings)){
			for (SupplementarySignInSetting supplementarySignInSetting : supplementarySignInSettings) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("supplementarySignInSettingId", supplementarySignInSetting.getId());
				dataMap.put("invitationCount", supplementarySignInSetting.getInvitationCount());
				dataMap.put("supplementaryCardCount", supplementarySignInSetting.getSupplementaryCardCount());
				dataList.add(dataMap);
			}
		}
		if(memberId != null){
			MemberAccount account = memberAccountService.findAccountByMemberId(memberId);
			memberSupplementaryCardNum = account.getSupplementaryCard() == null ? 0 : account.getSupplementaryCard();
		}
		map.put("dataList", dataList);
		map.put("memberSupplementaryCardNum", memberSupplementaryCardNum);
		return map;
	}
	
	
}

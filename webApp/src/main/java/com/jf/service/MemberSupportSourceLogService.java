package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberSupportSourceLogMapper;
import com.jf.entity.MemberSupportSourceLog;
import com.jf.entity.MemberSupportSourceLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberSupportSourceLogService extends BaseService<MemberSupportSourceLog, MemberSupportSourceLogExample> {
	@Autowired
	private MemberSupportSourceLogMapper memberSupportSourceLogMapper;
	@Autowired
	public void setMemberSupportSourceLogMapper(MemberSupportSourceLogMapper MemberSupportSourceLogMapper) {
		this.setDao(MemberSupportSourceLogMapper);
		this.memberSupportSourceLogMapper = MemberSupportSourceLogMapper;
	}
	public List<MemberSupportSourceLog> findModesBySourceNicheId(Integer memberId, Integer sourceNicheId) {
		MemberSupportSourceLogExample MemberSupportSourceLogExample = new MemberSupportSourceLogExample();
		MemberSupportSourceLogExample.createCriteria().andMemberIdEqualTo(memberId).andSourceNicheIdEqualTo(sourceNicheId).andDelFlagEqualTo("0");
		
		return selectByExample(MemberSupportSourceLogExample);
	}
	
	
}

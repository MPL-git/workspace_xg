package com.jf.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberLabelRelationMapper;
import com.jf.entity.MemberLabelRelation;
import com.jf.entity.MemberLabelRelationExample;

@Service
@Transactional
public class MemberLabelRelationService extends BaseService<MemberLabelRelation,MemberLabelRelationExample> {
	
	
	private static final Log logger = LogFactory.getLog(MemberLabelRelationService.class);
	
	@Autowired
	private MemberLabelRelationMapper memberLabelRelationMapper;

	@Autowired
	public void setMemberLabelRelationMapper(MemberLabelRelationMapper memberLabelRelationMapper) {
		super.setDao(memberLabelRelationMapper);
		this.memberLabelRelationMapper = memberLabelRelationMapper;
	}
 
	
}

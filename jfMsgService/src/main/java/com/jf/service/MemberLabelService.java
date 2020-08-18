package com.jf.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberLabelMapper;
import com.jf.entity.MemberLabel;
import com.jf.entity.MemberLabelExample;

@Service
@Transactional
public class MemberLabelService extends BaseService<MemberLabel,MemberLabelExample> {
	
	
	private static final Log logger = LogFactory.getLog(MemberLabelService.class);
	
	@Autowired
	private MemberLabelMapper memberLabelMapper;

	@Autowired
	public void setMemberLabelMapper(MemberLabelMapper memberLabelMapper) {
		super.setDao( memberLabelMapper);
		this.memberLabelMapper = memberLabelMapper;
	}
 
	
}

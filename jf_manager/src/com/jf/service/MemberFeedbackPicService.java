package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberFeedbackPicMapper;
import com.jf.entity.MemberFeedbackPic;
import com.jf.entity.MemberFeedbackPicExample;


@Service
@Transactional
public class MemberFeedbackPicService extends BaseService<MemberFeedbackPic,MemberFeedbackPicExample> {
	@Autowired
	private MemberFeedbackPicMapper memberFeedbackPicMapper;

	@Autowired
	public void setMemberFeedbackPicMapper(MemberFeedbackPicMapper memberFeedbackPicMapper) {
		super.setDao(memberFeedbackPicMapper);
		this.memberFeedbackPicMapper = memberFeedbackPicMapper;
	}
}

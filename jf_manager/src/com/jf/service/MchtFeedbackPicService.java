package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtFeedbackPicMapper;
import com.jf.entity.MchtFeedbackPic;
import com.jf.entity.MchtFeedbackPicExample;

@Service
@Transactional
public class MchtFeedbackPicService extends BaseService<MchtFeedbackPic,MchtFeedbackPicExample> {
	@Autowired
	private MchtFeedbackPicMapper mchtFeedbackPicMapper;

	@Autowired
	public void setmchtFeedbackPicMapper(MchtFeedbackPicMapper mchtFeedbackPicMapper) {
		super.setDao(mchtFeedbackPicMapper);
		this.mchtFeedbackPicMapper = mchtFeedbackPicMapper;
	}
}

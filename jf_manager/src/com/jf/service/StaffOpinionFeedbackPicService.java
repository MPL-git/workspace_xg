package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.StaffOpinionFeedbackPicMapper;
import com.jf.entity.StaffOpinionFeedbackPic;
import com.jf.entity.StaffOpinionFeedbackPicExample;

@Service
@Transactional
public class StaffOpinionFeedbackPicService extends BaseService<StaffOpinionFeedbackPic, StaffOpinionFeedbackPicExample> {

	@Autowired
	private StaffOpinionFeedbackPicMapper staffOpinionFeedbackPicMapper;
	
	@Autowired
	public void setStaffOpinionFeedbackPicMapper(StaffOpinionFeedbackPicMapper staffOpinionFeedbackPicMapper) {
		super.setDao(staffOpinionFeedbackPicMapper);
		this.staffOpinionFeedbackPicMapper = staffOpinionFeedbackPicMapper;
	}
	
}

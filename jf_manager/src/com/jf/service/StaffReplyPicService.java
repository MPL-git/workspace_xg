package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.StaffReplyPicMapper;
import com.jf.entity.StaffReplyPic;
import com.jf.entity.StaffReplyPicExample;

@Service
@Transactional
public class StaffReplyPicService extends BaseService<StaffReplyPic, StaffReplyPicExample> {

	@Autowired
	private StaffReplyPicMapper staffReplypicMapper;
	
	@Autowired
	public void setStaffReplyPicMapperMapper(StaffReplyPicMapper staffReplypicMapper) {
		super.setDao(staffReplypicMapper);
		this.staffReplypicMapper = staffReplypicMapper;
	}
	
}

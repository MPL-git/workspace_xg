package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberFeedbackPicMapper;
import com.jf.entity.MemberFeedbackPic;
import com.jf.entity.MemberFeedbackPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月11日 下午6:28:12 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberFeedbackPicService extends BaseService<MemberFeedbackPic, MemberFeedbackPicExample> {
	
	@Autowired
	private MemberFeedbackPicMapper memberFeedbackPicMapper;

	@Autowired
	public void setMemberFeedbackPicMapper(MemberFeedbackPicMapper memberFeedbackPicMapper) {
		this.setDao(memberFeedbackPicMapper);
		this.memberFeedbackPicMapper = memberFeedbackPicMapper;
	}
	
}

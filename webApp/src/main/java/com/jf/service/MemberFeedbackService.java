package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberFeedbackMapper;
import com.jf.entity.MemberFeedback;
import com.jf.entity.MemberFeedbackExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月11日 下午6:05:00 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberFeedbackService extends BaseService<MemberFeedback, MemberFeedbackExample> {
	
	@Autowired
	private MemberFeedbackMapper memberFeedbackMapper;

	@Autowired
	public void setMemberFeedbackMapper(MemberFeedbackMapper memberFeedbackMapper) {
		this.setDao(memberFeedbackMapper);
		this.memberFeedbackMapper = memberFeedbackMapper;
	}
	
}

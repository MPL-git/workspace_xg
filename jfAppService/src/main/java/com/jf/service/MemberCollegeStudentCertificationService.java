package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberCollegeStudentCertificationMapper;
import com.jf.entity.MemberCollegeStudentCertification;
import com.jf.entity.MemberCollegeStudentCertificationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pengl
 * @create 2020-03-31 上午 9:41
 */
@Service
@Transactional
public class MemberCollegeStudentCertificationService extends BaseService<MemberCollegeStudentCertification, MemberCollegeStudentCertificationExample> {

	@Autowired
	private MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper;

	@Autowired
	public void setMemberCollegeStudentCertificationMapper(MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper) {
		this.setDao(memberCollegeStudentCertificationMapper);
		this.memberCollegeStudentCertificationMapper = memberCollegeStudentCertificationMapper;
	}


}

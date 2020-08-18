package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.MemberCollegeStudentCertificationCustomMapper;
import com.jf.dao.MemberCollegeStudentCertificationMapper;
import com.jf.entity.MemberCollegeStudentCertification;
import com.jf.entity.MemberCollegeStudentCertificationCustom;
import com.jf.entity.MemberCollegeStudentCertificationCustomExample;
import com.jf.entity.MemberCollegeStudentCertificationExample;
@Service
@Transactional
public class MemberCollegeStudentCertificationService extends BaseService<MemberCollegeStudentCertification,MemberCollegeStudentCertificationExample> {
	@Autowired
	private MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper;
	@Autowired
	private MemberCollegeStudentCertificationCustomMapper memberCollegeStudentCertificationCustomMapper;
	
	@Autowired
	public void setMemberCollegeStudentCertificationMapper(MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper) {
		super.setDao(memberCollegeStudentCertificationMapper);
		this.memberCollegeStudentCertificationMapper = memberCollegeStudentCertificationMapper;
	}
	
	public int memberCollegeStudentCertificationCustomCountByExample(MemberCollegeStudentCertificationCustomExample example){
		return memberCollegeStudentCertificationCustomMapper.memberCollegeStudentCertificationCustomCountByExample(example);
	}
    public List<MemberCollegeStudentCertificationCustom> memberCollegeStudentCertificationCustomSelectByExample(MemberCollegeStudentCertificationCustomExample example){
    	return memberCollegeStudentCertificationCustomMapper.memberCollegeStudentCertificationCustomSelectByExample(example);
    }

}

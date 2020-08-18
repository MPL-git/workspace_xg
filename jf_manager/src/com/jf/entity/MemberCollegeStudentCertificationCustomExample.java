package com.jf.entity;

public class MemberCollegeStudentCertificationCustomExample extends MemberCollegeStudentCertificationExample{
	
	@Override
    public MemberCollegeStudentCertificationCustomCriteria createCriteria() {
		MemberCollegeStudentCertificationCustomCriteria criteria = new MemberCollegeStudentCertificationCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberCollegeStudentCertificationCustomCriteria extends MemberCollegeStudentCertificationExample.Criteria{
		public Criteria andMemberNickLike(String memberNick) {//模糊搜索会员昵称
			addCriterion(" mcs.member_id in(select mi.id from bu_member_info mi where mi.del_flag='0' and mi.nick like CONCAT('%', '"+memberNick+"', '%'))");
			return this;
		}
		
		public Criteria andMemberMobileEqualTo(String memberMobile) {
			addCriterion(" mcs.member_id in(select mi.id from bu_member_info mi where mi.del_flag='0' and mi.mobile='"+memberMobile+"')");
			return this;
		}
		
	}
}
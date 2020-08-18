package com.jf.entity;


public class MemberLabelRuleCustomExample extends MemberLabelRuleExample{
	
	@Override
    public MemberLabelRuleCustomCriteria createCriteria() {
		MemberLabelRuleCustomCriteria criteria = new MemberLabelRuleCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberLabelRuleCustomCriteria extends MemberLabelRuleExample.Criteria{
		
		public Criteria andMemberLabelStatusEqualTo(String status) {//用户标签执行状态
			addCriterion("EXISTS (select ml.id from bu_member_label ml where ml.id=mlr.label_id and ml.del_flag='0' and ml.status='"+status+"')");
	        return this;
	    }
		
		public Criteria andMemberlabelTypeNameLikeTo(String memberlabelTypeName) {
			addCriterion("EXISTS (select mlt.id from bu_member_label_type mlt where mlt.del_flag='0' and mlr.label_type_id=mlt.id and mlt.del_flag='0' and  mlt.label_type_name like '%"+memberlabelTypeName+"%')");
	        return this;
	    }
	}
}
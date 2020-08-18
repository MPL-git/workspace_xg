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
		
		public void andMemberlabelStatus() {
			addCriterion(" EXISTS(select ml.id from bu_member_label ml where label_id= ml.id and ml.del_flag = '0' and ml.status='1')");
		}
		
		
	}
}
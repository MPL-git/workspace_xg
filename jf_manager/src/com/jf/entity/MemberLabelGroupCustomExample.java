package com.jf.entity;


public class MemberLabelGroupCustomExample extends MemberLabelGroupExample{
	
	@Override
    public MemberLabelGroupCustomCriteria createCriteria() {
		MemberLabelGroupCustomCriteria criteria = new MemberLabelGroupCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberLabelGroupCustomCriteria extends MemberLabelGroupExample.Criteria{
		
		public Criteria andMemberLabelStatusEqualTo(String status) {
			addCriterion("t.id in(select gr.label_group_id from bu_member_label_type mlt,bu_member_label_group_relation gr where gr.label_group_id=t.id and gr.del_flag='0' and gr.label_type_id=mlt.id and mlt.del_flag='0' and mlt.status='"+status+"')");
	        return this;
	    }
	}
}
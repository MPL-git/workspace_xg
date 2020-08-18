package com.jf.entity;


public class MemberLabelRelationCustomExample extends MemberLabelRelationExample{
	
	@Override
    public MemberLabelRelationCustomCriteria createCriteria() {
		MemberLabelRelationCustomCriteria criteria = new MemberLabelRelationCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberLabelRelationCustomCriteria extends MemberLabelRelationExample.Criteria{
		
		public Criteria andMemberIdNotEqualTo(String labelGroupIds) {
			addCriterion(" NOT EXISTS(SELECT d.memeber_id FROM bu_member_label_group_relation c, bu_member_label_relation d "
					+ "WHERE c.label_id = d.label_id AND c.del_flag = '0' AND c.type = '1' AND d.del_flag = '0' "
					+ "AND d.memeber_id = mll.memeber_id AND c.label_group_id IN ("+ labelGroupIds +"))");
			return this;
		}
		
	}
}
package com.jf.entity;



public class MemberFavorItesCustomExample extends MemberFavorItesExample{
	@Override
	public MemberFavorItesCustomCriteria createCriteria() {
		MemberFavorItesCustomCriteria criteria = new MemberFavorItesCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class MemberFavorItesCustomCriteria extends MemberFavorItesCustomExample.Criteria{
		
		public Criteria andstylenameEqualTo(String stylename) {
			addCriterion(" EXISTS(SELECT s.id FROM bu_style s WHERE s.del_flag = '0' AND s.id=t.style_id_group AND s.name = '" + stylename + "')");			
			return this;
		}
		
	}

}
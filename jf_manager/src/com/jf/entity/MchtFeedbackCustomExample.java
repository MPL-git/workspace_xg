package com.jf.entity;



public class MchtFeedbackCustomExample extends MchtFeedbackExample{
	
	@Override
    public MchtFeedbackCustomCriteria createCriteria() {
		MchtFeedbackCustomCriteria criteria = new MchtFeedbackCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtFeedbackCustomCriteria extends MchtFeedbackExample.Criteria{
		public Criteria andMchtCodeEqualTo(String mchtCode) {//商家序号
			addCriterion(" EXISTS(select b.id from bu_mcht_info b where a.mcht_id=b.id and b.mcht_code='"+mchtCode+"')");
	        return this;
	    }	
	}
}
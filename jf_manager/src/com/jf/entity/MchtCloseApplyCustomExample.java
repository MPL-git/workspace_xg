package com.jf.entity;

public class MchtCloseApplyCustomExample extends MchtCloseApplyExample {

	@Override
	public MchtCloseApplyCustomCriteria createCriteria() {
		MchtCloseApplyCustomCriteria criteria = new MchtCloseApplyCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class MchtCloseApplyCustomCriteria extends MchtCloseApplyExample.Criteria {
		
		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and (x.short_name like '%" + mchtName + "%' or x.shop_name like '%" + mchtName + "%' or x.company_name like '%" + mchtName + "%' ))");
	    	return this;
		}
		
		public Criteria andMchtStatusByEqualTo(String status) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.status = '" + status + "')");
			return this;
		} 
		
		public Criteria andMchtStatusIn(String statusIn) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.status in(" + statusIn + "))");
			return this;
		}
		
		public Criteria andMchtCodeByEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.mcht_code = '" + mchtCode + "')");
			return this;
		}
		
		public Criteria andMchtTypeByEqualTo(String mchtType) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.mcht_type = '" + mchtType + "')");
			return this;
		}
		
	}
	
}

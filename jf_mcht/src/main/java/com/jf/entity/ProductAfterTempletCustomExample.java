package com.jf.entity;


public class ProductAfterTempletCustomExample extends ProductAfterTempletExample{
	
	@Override
    public ProductAfterTempletCustomCriteria createCriteria() {
		ProductAfterTempletCustomCriteria criteria = new ProductAfterTempletCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	
	public class ProductAfterTempletCustomCriteria extends ProductAfterTempletExample.Criteria{
		//搜索功能
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
			return this;
		}
		public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" mcht_id in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' ))");
	        return this;
	    }
	    

	}
}
package com.jf.entity;

import com.jf.entity.ProductExample.Criteria;


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
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and ( mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' ))");
	        return this;
	    }
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS(select mpc.mcht_id from bu_mcht_platform_contact mpc where mpc.del_flag = '0' and mpc.status = '1' and mpc.mcht_id = a.mcht_id and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
	    
	}
}
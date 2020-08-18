package com.jf.entity;

import com.jf.entity.MchtContractExample.Criteria;





public class MchtLicenseChgCustomExample extends MchtLicenseChgExample {

	@Override
    public MchtLicenseChgCustomCriteria createCriteria() {
		MchtLicenseChgCustomCriteria criteria = new MchtLicenseChgCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtLicenseChgCustomCriteria extends MchtLicenseChgExample.Criteria{
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = t.mcht_id and x.mcht_code = '" + mchtCode + "')");
			return this;
		}
		
		public Criteria andNameLike(String mchtName) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = t.mcht_id and (x.short_name like '" + mchtName + "' or x.shop_name like '" + mchtName + "' or x.company_name like '" + mchtName + "' ))");
	    	return this;
		}
		
		public Criteria andProductTypeIdTo(int productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.mcht_id = t.mcht_id and mpt.del_flag = '0' and mpt.product_type_id = "+productTypeId+" and mpt.is_main='1' and mpt.status='1')");
	    	return this;
		}
		
		public Criteria andPlatContactStaffIdEqualTo(Integer platContactStaffId) {
			addCriterion(" t.mcht_id IN(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' and (pc.staff_id = "+platContactStaffId+" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = "+platContactStaffId+")) and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id )");
			return this;
		}
	}
	
}

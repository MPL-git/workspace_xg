package com.jf.entity;

import com.jf.entity.MchtInfoExample.Criteria;

public class MchtProductBrandCustomExample extends MchtProductBrandExample {

	@Override
	public MchtProductBrandCustomCriteria createCriteria() {
		MchtProductBrandCustomCriteria criteria = new MchtProductBrandCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	public class MchtProductBrandCustomCriteria extends MchtProductBrandExample.Criteria{
		
		public Criteria andProductBrandHaveExpired(String nowDate) {
			addCriterion(" ('"+nowDate+"' > platform_auth_exp_date OR '"+nowDate+"' > other_exp_date ) ");
	        return this;
		}
		
		public Criteria andProductBrandAboutToExpire(String nowDate,String afterThirtyDays) {
			addCriterion(" ('"+nowDate+"' < platform_auth_exp_date and platform_auth_exp_date <= '"+afterThirtyDays+"' OR '"+nowDate+"' < other_exp_date and other_exp_date <= '"+afterThirtyDays+"' ) ");
			return this;
		}
		
		public Criteria andNameEqualTo(String name) {
			addCriterion("EXISTS(select m.id from bu_mcht_info m where m.id = t.mcht_id and (m.company_name = '" + name + "' or m.shop_name = '" + name + "'))");
			return this;
		}
		public Criteria andCompanyNameEqualTo(String companyName) {
			addCriterion("EXISTS(select m.id from bu_mcht_info m where m.id = t.mcht_id and m.company_name = '" + companyName + "')");
			return this;
		}
		public Criteria andshopNameEqualTo(String shopName) {
			addCriterion("EXISTS(select m.id from bu_mcht_info m where m.id = t.mcht_id and m.shop_name = '" + shopName + "')");
	        return this;
		}
		public Criteria andmchtCodeEqualTo(String mchtCode) {
			addCriterion("EXISTS(select m.id from bu_mcht_info m where m.id = t.mcht_id and m.mcht_code = '" + mchtCode + "')");
			return this;
		}
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" t.mcht_id in (SELECT m.id FROM bu_mcht_info m WHERE m.del_flag = '0' AND m.id = t.mcht_id AND m.status='1' AND m.mcht_code = '" + mchtCode + "' )");
			return this;
		}
		
		public Criteria andMchtStatusEqualTo(String status) {
			addCriterion("EXISTS(select m.id from bu_mcht_info m where m.id = t.mcht_id and m.status = '" + status + "')");
	        return this;
		}
		
		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = t.mcht_id and (x.short_name like '%" + mchtName + "%' or x.shop_name like '%" + mchtName + "%' or x.company_name like '%" + mchtName + "%' ))");
	    	return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion("EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.mcht_id = t.mcht_id and mpt.product_type_id = " + productTypeId + " and mpt.is_main='1' and mpt.status='1')");
			return this;
		}
		
		public Criteria andfwStaffIdEqualTo(String fwStaffId) {
			addCriterion("t.mcht_id in (select b.mcht_id from bu_platform_contact c,bu_mcht_platform_contact b where c.id = b.platform_contact_id and b.mcht_id = t.mcht_id and c.status='0' and b.status='1' and b.del_flag='0' and c.del_flag='0' and c.staff_id ='" + fwStaffId + "')");
			return this;
		}
	}
	
}

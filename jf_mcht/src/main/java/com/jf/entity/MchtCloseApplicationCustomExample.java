package com.jf.entity;



public class MchtCloseApplicationCustomExample extends MchtCloseApplicationExample {

	@Override
    public MchtCloseApplicationCriteria createCriteria() {
		MchtCloseApplicationCriteria criteria = new MchtCloseApplicationCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtCloseApplicationCriteria extends MchtCloseApplicationExample.Criteria{

		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and (mi.short_name like '%" + mchtName + "%' or mi.shop_name like '%" + mchtName + "%' or mi.company_name like '%" + mchtName + "%' ))");
	    	return this;
		}

		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and mi.mcht_code='"+mchtCode+"')");
	    	return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = t.mcht_id and mpt.product_type_id="+productTypeId+")");
			return this;
		}

		public Criteria andProductTypeIdsIn(String productTypeIdsStr) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = t.mcht_id and mpt.product_type_id in ("+productTypeIdsStr+"))");
			return this;
		}
	}
	
	
}

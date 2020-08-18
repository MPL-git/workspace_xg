package com.jf.entity;

import com.jf.entity.MchtInfoExample.Criteria;



public class MchtShopDynamicCustomExample extends MchtShopDynamicExample{
	
	@Override
    public MchtShopDynamicCustomCriteria createCriteria() {
		MchtShopDynamicCustomCriteria criteria = new MchtShopDynamicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtShopDynamicCustomCriteria extends MchtShopDynamicExample.Criteria{
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion("t.mcht_id = (select mi.id FROM bu_mcht_info mi WHERE mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
			return this;
		}
		
		public Criteria andNameLikeTo(String name) {
			addCriterion("t.mcht_id in (select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and (mi.short_name like '%" + name + "%' or mi.shop_name like '%" + name + "%' or mi.company_name like '%" + name + "%' ))");
			return this;
		}
		
		public Criteria andPaymentIdEqualTo(Integer paymentId) {
			addCriterion("EXISTS(select id FROM bu_combine_order a WHERE a.id = t.combine_order_id and a.payment_id = "+paymentId+")");
	        return this;
	    }
		
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = t.mcht_id and mpt.product_type_id = " + productTypeId + ")" );
			return this;
		}
		
	}
}
package com.jf.entity;


public class OrderAbnormalLogCustomExample extends OrderAbnormalLogExample{
	
	@Override
    public OrderAbnormalLogCustomCriteria createCriteria() {
		OrderAbnormalLogCustomCriteria criteria = new OrderAbnormalLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class OrderAbnormalLogCustomCriteria extends OrderAbnormalLogExample.Criteria{
		public Criteria andShopNameEqualTo(String shopName) {
			addCriterion("EXISTS(select so.id from bu_sub_order so,bu_mcht_info mi where so.id = t.sub_order_id and mi.id=so.mcht_id and mi.shop_name='"+shopName+"')");
	        return this;
	    }
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion("EXISTS(select so.id from bu_sub_order so,bu_mcht_info mi where so.id = t.sub_order_id and mi.id=so.mcht_id and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }

		public Criteria andOrderPayDateGreaterThanOrEqualTo(String payDateBegin) {
			addCriterion(" EXISTS(select so.id from bu_sub_order so, bu_combine_order co where so.id = t.sub_order_id and co.id=so.combine_order_id and co.pay_date >= '"+payDateBegin+"')");
			return this;
		}
        
		public Criteria andOrderPayDateLessThanOrEqualTo(String payDateEnd) {
			addCriterion(" EXISTS(select so.id from bu_sub_order so, bu_combine_order co where so.id = t.sub_order_id and co.id=so.combine_order_id and co.pay_date <= '"+payDateEnd+"')");
			return this;
		}
		
		public Criteria andBrandIdEqualTo(Integer brandId) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od,bu_product p where od.sub_order_id=t.sub_order_id and od.product_id = p.id and od.del_flag='0' and p.brand_id="+brandId+")");
	        return this;
	    }
		
		public Criteria andFollowStatusIsNullOrNoDeal() {
			addCriterion(" t.follow_status is null or t.follow_status = '1' ");
			return this;
		}
		//对接人的商家
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS (select mpc.mcht_id from bu_mcht_platform_contact mpc, bu_sub_order so where mpc.del_flag = '0' and so.del_flag='0' and t.sub_order_id=so.id and so.mcht_id=mpc.mcht_id and mpc.status = '1' and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		//订单状态待发货
		public Criteria andSubOrderStatusEqualTo(String status) {
			addCriterion("EXISTS(select so.id from bu_sub_order so where so.id = t.sub_order_id and so.status='"+status+"' and so.del_flag='0')");
	        return this;
	    }
	}
}
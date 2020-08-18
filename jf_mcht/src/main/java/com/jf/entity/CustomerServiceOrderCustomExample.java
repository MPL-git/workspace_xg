package com.jf.entity;


public class CustomerServiceOrderCustomExample extends CustomerServiceOrderExample{
	
	@Override
    public CustomerServiceOrderCustomCriteria createCriteria() {
		CustomerServiceOrderCustomCriteria criteria = new CustomerServiceOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CustomerServiceOrderCustomCriteria extends CustomerServiceOrderExample.Criteria{
		public Criteria andMchtIdEqualTo(Integer mchtId) {
			addCriterion("t.sub_order_id in (select a.id FROM bu_sub_order a WHERE a.mcht_id = "+mchtId+")");
			return this;
		}
		
		public Criteria andReceiverNameEqualTo(String receiverName) {
			addCriterion("t.sub_order_id in (select a.id FROM bu_sub_order a,bu_combine_order b WHERE a.combine_order_id = b.id and b.receiver_name = '"+receiverName+"')");
			return this;
		}
		
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion("t.sub_order_id in (select a.id FROM bu_sub_order a WHERE a.sub_order_code = '"+subOrderCode+"')");
			return this;
		}
		
		public Criteria andReceiverPhoneEqualTo(String receiverPhone) {
			addCriterion("t.sub_order_id in (select a.id FROM bu_sub_order a,bu_combine_order b WHERE a.combine_order_id = b.id and b.receiver_phone = '"+receiverPhone+"')");
			return this;
		}
		
		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion("EXISTS(select a.id FROM bu_order_dtl a,bu_product p WHERE a.id = t.order_dtl_id and p.id = a.product_id and p.code = '"+productCode+"')");
			return this;
		}
		
		public Criteria andProductIdEqualTo(Integer productId) {
			addCriterion("EXISTS(select id FROM bu_order_dtl a WHERE a.id = t.order_dtl_id and a.product_id = "+productId+")");
			return this;
		}
		
		public Criteria andBrandIdEqualTo(String brandId) {
			addCriterion("EXISTS(select a.id FROM bu_order_dtl a,bu_product p WHERE a.id = t.order_dtl_id and a.product_id = p.id and p.brand_id = "+brandId+")");
			return this;
		}
		
		public Criteria andExpressNoEqualTo(String expressNo) {
			addCriterion("t.sub_order_id in (select a.id FROM bu_sub_order a WHERE a.express_no = '"+expressNo+"')");
			return this;
		}
		
		public Criteria andActivityIdEqualTo(Integer activityId) {
			addCriterion("EXISTS(select id FROM bu_order_dtl a WHERE a.id = t.order_dtl_id and a.activity_id = "+activityId+")");
			return this;
		}
	}
}
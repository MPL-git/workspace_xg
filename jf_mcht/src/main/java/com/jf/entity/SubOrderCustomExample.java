package com.jf.entity;




public class SubOrderCustomExample extends SubOrderExample{
	@Override
    public SubOrderCustomCriteria createCriteria() {
		SubOrderCustomCriteria criteria = new SubOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SubOrderCustomCriteria extends SubOrderExample.Criteria{
		public Criteria andServiceTypeNotEqualTo(String serviceType) {
			addCriterion("not exists(select a.id FROM bu_customer_service_order a WHERE a.sub_order_id = t.id and a.service_type = '"+serviceType+"')");
	        return this;
	    }
		
		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion("EXISTS(select a.id FROM bu_order_dtl a,bu_product p WHERE a.sub_order_id = t.id and p.id = a.product_id and p.code = '"+productCode+"')");
	        return this;
	    }
		
		public Criteria andProductIdEqualTo(Integer productId) {
			addCriterion("EXISTS(select id FROM bu_order_dtl a WHERE a.sub_order_id = t.id and a.product_id = "+productId+")");
			return this;
		}
		
		public Criteria andBrandIdEqualTo(String brandId) {
			addCriterion("EXISTS(select a.id FROM bu_order_dtl a,bu_product p WHERE a.sub_order_id = t.id and a.product_id = p.id and p.brand_id = "+Integer.parseInt(brandId)+")");
			return this;
		}
		
		public Criteria andActivityIdEqualTo(Integer activityId) {
			addCriterion("EXISTS(select id FROM bu_order_dtl a WHERE a.sub_order_id = t.id and a.activity_id = "+activityId+")");
			return this;
		}
		
		public Criteria andReceiverNameEqualTo(String receiverName) {
			addCriterion("t.combine_order_id in (select a.id FROM bu_combine_order a WHERE a.receiver_name = '"+receiverName+"')");
			return this;
		}
		
		public Criteria andReceiverPhoneEqualTo(String receiverPhone) {
			addCriterion("t.combine_order_id in (select a.id FROM bu_combine_order a WHERE a.receiver_phone = '"+receiverPhone+"')");
			return this;
		}
		
		public Criteria andOrderPayDateGreaterThanOrEqualTo(String orderPayDateBegin) {
			addCriterion(" EXISTS(select id from bu_combine_order mi where mi.id=t.combine_order_id and mi.pay_date >= '"+orderPayDateBegin+"')");
			return this;
		}
		
		public Criteria andOrderPayDateLessThanOrEqualTo(String orderPayDateEnd) {
			addCriterion(" EXISTS(select id from bu_combine_order mi where mi.id=t.combine_order_id and mi.pay_date <= '"+orderPayDateEnd+"')");
			return this;
		}

		public Criteria andProductNameLikeTo(String productName) {
			addCriterion("EXISTS(select a.id FROM bu_order_dtl a,bu_product p WHERE a.sub_order_id = t.id and p.id = a.product_id and p.name like '%"+productName+"%')");
			return this;
		}

		public Criteria andProductArtNoLikeTo(String productArtNo) {
			addCriterion("EXISTS(select a.id FROM bu_order_dtl a,bu_product p WHERE a.sub_order_id = t.id and p.id = a.product_id and p.art_no like '%"+productArtNo+"%')");
			return this;
		}

		public Criteria andAuditDefautStauts() {
			addCriterion("audit_status in ('1','3')");
			return this;
		}

	}
}
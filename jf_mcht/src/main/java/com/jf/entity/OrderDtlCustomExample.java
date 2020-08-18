package com.jf.entity;

public class OrderDtlCustomExample extends OrderDtlExample{
	
	@Override
    public OrderDtlCustomCriteria createCriteria() {
		OrderDtlCustomCriteria criteria = new OrderDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class OrderDtlCustomCriteria extends OrderDtlExample.Criteria{
		public Criteria andProductCodeEqualTo(String productCode) {
        	addCriterion(" (select p.code from bu_product p where p.id = product_id)="+productCode+"");
        	return this;
	    }
		
		public Criteria andBrandIdEqualTo(Integer brandId) {
			addCriterion(" (select p.brand_id from bu_product p where p.id = product_id)="+brandId+"");
			return this;
		}
		
		public Criteria andOrderDtlDelFlagEqualTo(String flag) {
			addCriterion(" t.del_flag = "+flag+" ");
			return this;
		}
		
		public Criteria andOrderDtlDeliveryDateGreaterThanOrEqualTo(String date) {
			addCriterion(" t.delivery_date >= '"+date+"' ");
			return this;
		}
		
		public Criteria andOrderDtlDeliveryDateLessThanOrEqualTo(String date) {
			addCriterion(" t.delivery_date <= '"+date+"' ");
			return this;
		}
		
		public Criteria andOrderDtlIdEqualTo(Integer i) {
			addCriterion(" t.id = "+i);
			return this;
		}
		
		public Criteria andSubOrderMchtIdEqualTo(Integer mchtId) {
			addCriterion(" t.sub_order_id = so.id AND so.mcht_id = "+mchtId+" AND so.`status` IN ('1', '2', '6') AND t.id NOT IN (SELECT tt.id FROM bu_order_dtl tt,bu_customer_service_order c WHERE tt.del_flag = '0' AND tt.id = c.order_dtl_id AND c.`status` = '1')");
			return this;
		}
		
		
		public Criteria andServiceTypeNotEqualTo(String serviceType) {
			addCriterion("NOT EXISTS(select a.id FROM bu_sub_order a,bu_customer_service_order b WHERE a.id = t.sub_order_id and a.id = b.sub_order_id and b.service_type = '"+serviceType+"')");
			return this;
		}
		
		public Criteria andSubOrderStatusEqualTo(String status) {
			addCriterion("t.sub_order_id in (select id FROM bu_sub_order a WHERE a.status = "+status+")");
	        return this;
	    }
		
		public Criteria andSubOrderStatusIn(String status1,String status2) {
			addCriterion("t.sub_order_id in (select id FROM bu_sub_order a WHERE a.status in ("+status1+","+status2+"))");
			return this;
		}
		
		public Criteria andSubOrderExpressIdEqualTo(String expressId) {
			addCriterion("EXISTS(select id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.express_id = "+expressId+")");
			return this;
		}
		
		public Criteria andSubOrderExpressNoEqualTo(String expressNo) {
			addCriterion("EXISTS(select id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.express_no = '"+expressNo+"')");
			return this;
		}
		
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion("EXISTS(select id FROM bu_sub_order a WHERE a.id = sub_order_id and a.sub_order_code = '"+subOrderCode+"')");
			return this;
		}
		
		public Criteria andReceiverNameEqualTo(String receiverName) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a,bu_combine_order b WHERE a.id = sub_order_id and a.combine_order_id = b.id and b.receiver_name = '"+receiverName+"')");
			return this;
		}
		
		public Criteria andReceiverPhoneEqualTo(String receiverPhone) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a,bu_combine_order b WHERE a.id = sub_order_id and a.combine_order_id = b.id and b.receiver_phone = '"+receiverPhone+"')");
			return this;
		}
		public Criteria andRemarksColorEqualTo(String remarksColor) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a,bu_combine_order b WHERE a.id = sub_order_id and a.combine_order_id = b.id and a.remarks_color = '"+remarksColor+"')");
			return this;
		}
		public Criteria andSubOrderCreateDateGreaterThanOrEqualTo(String createTimeBegin) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.create_date >= '"+createTimeBegin+"')");
			return this;
		}
		
		public Criteria andSubOrderCreateDateLessThanOrEqualTo(String createTimeEnd) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.create_date <= '"+createTimeEnd+"')");
			return this;
		}
		
		public Criteria andMchtIdEqualTo(Integer mchtId) {
			addCriterion("t.sub_order_id in (select id FROM bu_sub_order a WHERE a.mcht_id = "+mchtId+")");
			return this;
		}
		
		public Criteria andPayDateGreaterThanOrEqualTo(String payDateBegin) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a,bu_combine_order b WHERE a.id = sub_order_id and a.combine_order_id = b.id and b.pay_date >= '"+payDateBegin+"')");
			return this;
		}
		
		public Criteria andPayDateLessThanOrEqualTo(String payDateEnd) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a,bu_combine_order b WHERE a.id = sub_order_id and a.combine_order_id = b.id and b.pay_date <= '"+payDateEnd+"')");
			return this;
		}
		
		public Criteria andDeliveryDateGreaterThanOrEqualTo(String deliveryDateBegin) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.delivery_date >= '"+deliveryDateBegin+"')");
			return this;
		}
		
		public Criteria andDeliveryDateLessThanOrEqualTo(String deliveryDateEnd) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.delivery_date <= '"+deliveryDateEnd+"')");
			return this;
		}
		
		public Criteria andSubOrderExpressNoIsNull() {
			addCriterion("t.sub_order_id in (select a.id FROM bu_sub_order a WHERE a.express_no is null)");
			return this;
		}
		
		public Criteria andSubOrderExpressNoIsNotNull() {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.express_no is not null)");
			return this;
		}
		
		public Criteria andCombineOrderStatusEqualTo(String status) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a,bu_combine_order co WHERE a.id = t.sub_order_id and a.combine_order_id = co.id and co.status ="+status+" and co.del_flag='0')");
			return this;
		}
		
		public Criteria andNotCustomerServiceOrder() {
			addCriterion("NOT EXISTS(select b.id FROM bu_customer_service_order b WHERE t.id = b.order_dtl_id and b.status in ('0','1') and b.del_flag='0')");
			return this;
		}

		public Criteria andAuditDefautStauts() {
			addCriterion("EXISTS(select a.id from bu_sub_order a where a.id = t.sub_order_id and a.audit_status in ('1','3') and a.del_flag='0')");
			return this;
		}

		public Criteria andAuditDateGreaterThanOrEqualTo(String payDateBegin) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a where a.id = t.sub_order_id and a.audit_date >= '"+payDateBegin+"')");
			return this;
		}

		public Criteria andAuditDateLessThanOrEqualTo(String payDateEnd) {
			addCriterion("EXISTS(select a.id FROM bu_sub_order a where a.id = t.sub_order_id and a.audit_date <= '"+payDateEnd+"')");
			return this;
		}
	}
}
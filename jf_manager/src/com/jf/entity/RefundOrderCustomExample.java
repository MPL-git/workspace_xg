package com.jf.entity;



public class RefundOrderCustomExample extends RefundOrderExample{
	
	@Override
    public RefundOrderCustomCriteria createCriteria() {
		RefundOrderCustomCriteria criteria = new RefundOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class RefundOrderCustomCriteria extends RefundOrderExample.Criteria{
		public Criteria andPaymentIdEqualTo(Integer paymentId) {
			addCriterion("EXISTS(select id FROM bu_combine_order a WHERE a.id = t.combine_order_id and a.payment_id = "+paymentId+")");
	        return this;
	    }
		
		public Criteria andPaymentIdIn(String paymentId) {
			addCriterion("EXISTS(select id FROM bu_combine_order a WHERE a.id = t.combine_order_id and a.payment_id in ("+paymentId+"))");
	        return this;
	    }
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion("EXISTS(select b.id FROM bu_mcht_info a,bu_customer_service_order b,bu_sub_order c WHERE t.service_order_id = b.id and b.sub_order_id = c.id and c.mcht_id = a.id and a.mcht_code = '"+mchtCode+"')");
			return this;
		}
		
		public Criteria andCombineOrderCodeEqualTo(String combineOrderCode) { 
			addCriterion("t.combine_order_id in (select a.id FROM bu_combine_order a WHERE a.combine_order_code = '"+combineOrderCode+"')");
			return this;
		}
		
		public Criteria andServiceOrderCodeEqualTo(String serviceOrderCode) { 
			addCriterion("t.service_order_id in (select a.id FROM bu_customer_service_order a WHERE a.order_code = '"+serviceOrderCode+"')");
			return this;
		}

		public Criteria andOrderDtlIdEqualTo(Integer orderDtlId) {
			addCriterion("t.service_order_id in (select a.id FROM bu_customer_service_order a WHERE a.order_dtl_id = "+orderDtlId+")");
			return this;
		}
		
		public Criteria andDepositPaymentIdEqualTo(Integer paymentId) {
			addCriterion("EXISTS(select id FROM bu_combine_deposit_order a WHERE a.id = t.combine_deposit_order_id and a.payment_id = "+paymentId+")");
	        return this;
	    }
		
		public Criteria andDepositPaymentIdIn(String paymentId) {
			addCriterion("EXISTS(select id FROM bu_combine_deposit_order a WHERE a.id = t.combine_deposit_order_id and a.payment_id in ("+paymentId+"))");
	        return this;
	    }
		
		public Criteria andCombineDepositOrderCodeEqualTo(String combineDepositOrderCode) { 
			addCriterion("t.combine_deposit_order_id in (select a.id FROM bu_combine_deposit_order a WHERE a.combine_deposit_order_code = '"+combineDepositOrderCode+"')");
			return this;
		}
	}
}
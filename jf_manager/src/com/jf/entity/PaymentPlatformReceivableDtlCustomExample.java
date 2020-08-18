package com.jf.entity;

public class PaymentPlatformReceivableDtlCustomExample extends PaymentPlatformReceivableDtlExample{
	
	@Override
    public PaymentPlatformReceivableDtlCustomCriteria createCriteria() {
		PaymentPlatformReceivableDtlCustomCriteria criteria = new PaymentPlatformReceivableDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class PaymentPlatformReceivableDtlCustomCriteria extends PaymentPlatformReceivableDtlExample.Criteria{
		
		public Criteria andReceivedDateEqualToCustomerPayDate() {
			addCriterion("t.received_date = t.customer_pay_date");
	        return this;
		}

		public Criteria andReceivedDateNotEqualToCustomerPayDate() {
			addCriterion("t.received_date != t.customer_pay_date");
	        return this;
		}
	}
}
package com.jf.entity;




public class DepositOrderCustomExample extends DepositOrderExample{
	
	@Override
    public DepositOrderCustomCriteria createCriteria() {
		DepositOrderCustomCriteria criteria = new DepositOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DepositOrderCustomCriteria extends DepositOrderExample.Criteria{
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andCompanyNameEqualTo(String companyName) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.company_name='"+companyName+"')");
			return this;
		}
		
		public Criteria andBankTypeEqualTo(String paymentName) {
			addCriterion(" EXISTS(select id from bu_platform_capital_account pca where pca.id=t.account_id and pca.del_flag='0' and pca.payment_name='"+paymentName+"')");
			return this;
		}
	}
}
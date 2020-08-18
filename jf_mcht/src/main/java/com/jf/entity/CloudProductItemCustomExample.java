package com.jf.entity;



public class CloudProductItemCustomExample extends CloudProductItemExample{
	
	@Override
    public CloudProductItemCustomCriteria createCriteria() {
		CloudProductItemCustomCriteria criteria = new CloudProductItemCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CloudProductItemCustomCriteria extends CloudProductItemExample.Criteria{

		public Criteria andSupplierUserStatusEqualTo(String status) {
			addCriterion("EXISTS(select id FROM jybao.sp_office a WHERE a.id = t.sp_office_id and a.status = '"+status+"')");
			return this;
		}
		
	}
}
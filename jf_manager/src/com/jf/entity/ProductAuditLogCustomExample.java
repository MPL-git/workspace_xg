package com.jf.entity;

public class ProductAuditLogCustomExample extends ProductAuditLogExample {

	@Override
    public ProductAuditLogCustomExampleCriteria createCriteria() {
		ProductAuditLogCustomExampleCriteria criteria = new ProductAuditLogCustomExampleCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ProductAuditLogCustomExampleCriteria extends ProductAuditLogExample.Criteria{
		
	}

}
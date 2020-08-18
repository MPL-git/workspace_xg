package com.jf.entity;




public class ZsproductTypeCustomExample extends ZsProductTypeExample{
	
	@Override
    public ZsproductTypeCustomCriteria createCriteria() {
		ZsproductTypeCustomCriteria criteria = new ZsproductTypeCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ZsproductTypeCustomCriteria extends ZsProductTypeExample.Criteria{
		
	}
}
package com.jf.entity;



public class ComplainOrderPicCustomExample extends ComplainOrderPicExample{
	
	@Override
    public ComplainOrderPicCustomCriteria createCriteria() {
		ComplainOrderPicCustomCriteria criteria = new ComplainOrderPicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ComplainOrderPicCustomCriteria extends ComplainOrderPicExample.Criteria{
		
		
	}
}
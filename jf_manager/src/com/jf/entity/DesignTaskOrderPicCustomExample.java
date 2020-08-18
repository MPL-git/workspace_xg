package com.jf.entity;




public class DesignTaskOrderPicCustomExample extends DesignTaskOrderPicExample{
	
	@Override
    public DesignTaskOrderPicCustomCriteria createCriteria() {
		DesignTaskOrderPicCustomCriteria criteria = new DesignTaskOrderPicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DesignTaskOrderPicCustomCriteria extends DesignTaskOrderPicExample.Criteria{
		
	}
}
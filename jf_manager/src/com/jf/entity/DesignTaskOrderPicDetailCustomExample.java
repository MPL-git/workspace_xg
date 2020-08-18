package com.jf.entity;




public class DesignTaskOrderPicDetailCustomExample extends DesignTaskOrderPicDetailExample{
	
	@Override
    public DesignTaskOrderPicDetailCustomCriteria createCriteria() {
		DesignTaskOrderPicDetailCustomCriteria criteria = new DesignTaskOrderPicDetailCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DesignTaskOrderPicDetailCustomCriteria extends DesignTaskOrderPicDetailExample.Criteria{
		
	}
}
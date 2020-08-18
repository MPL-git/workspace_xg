package com.jf.entity;


public class IndexPopupAdCustomExample extends IndexPopupAdExample {

	@Override
	public IndexPopupAdCustomCriteria createCriteria() {
		IndexPopupAdCustomCriteria criteria = new IndexPopupAdCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class IndexPopupAdCustomCriteria extends IndexPopupAdExample.Criteria {
		

    }
}

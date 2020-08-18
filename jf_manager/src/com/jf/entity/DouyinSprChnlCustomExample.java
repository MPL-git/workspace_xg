package com.jf.entity;


public class DouyinSprChnlCustomExample extends DouyinSprChnlExample {

	@Override
    public DouyinSprChnlCustomCriteria createCriteria() {
		DouyinSprChnlCustomCriteria criteria = new DouyinSprChnlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DouyinSprChnlCustomCriteria extends DouyinSprChnlExample.Criteria {
		
		
		
	}
	
}

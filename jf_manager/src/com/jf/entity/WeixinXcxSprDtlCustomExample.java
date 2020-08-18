package com.jf.entity;


public class WeixinXcxSprDtlCustomExample extends WeixinXcxSprDtlExample {

	@Override
    public WeixinXcxSprDtlCustomCriteria createCriteria() {
		WeixinXcxSprDtlCustomCriteria criteria = new WeixinXcxSprDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class WeixinXcxSprDtlCustomCriteria extends WeixinXcxSprDtlExample.Criteria{
		
	}
	
}

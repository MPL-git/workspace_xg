package com.jf.entity;


public class WeixinXcxSprChnlCustomExample extends WeixinXcxSprChnlExample {

	@Override
    public WeixinXcxSprChnlCustomCriteria createCriteria() {
		WeixinXcxSprChnlCustomCriteria criteria = new WeixinXcxSprChnlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class WeixinXcxSprChnlCustomCriteria extends WeixinXcxSprChnlExample.Criteria{
		
	}
	
	
	
}

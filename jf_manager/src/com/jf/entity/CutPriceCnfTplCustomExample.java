package com.jf.entity;


public class CutPriceCnfTplCustomExample extends CutPriceCnfTplExample {

	@Override
    public CutPriceCnfTplCriteria createCriteria() {
		CutPriceCnfTplCriteria criteria = new CutPriceCnfTplCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CutPriceCnfTplCriteria extends CutPriceCnfTplExample.Criteria{

		
	}
	
	
}

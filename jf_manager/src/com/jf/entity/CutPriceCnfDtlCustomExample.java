package com.jf.entity;


public class CutPriceCnfDtlCustomExample extends CutPriceCnfDtlExample {

	@Override
    public CutPriceCnfDtlCriteria createCriteria() {
		CutPriceCnfDtlCriteria criteria = new CutPriceCnfDtlCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CutPriceCnfDtlCriteria extends CutPriceCnfDtlExample.Criteria{

		
	}
	
}

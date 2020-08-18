package com.jf.entity;


public class CutPriceCnfTplDtlCustomExample extends CutPriceCnfTplDtlExample {

	@Override
    public CutPriceCnfTplDtlCriteria createCriteria() {
		CutPriceCnfTplDtlCriteria criteria = new CutPriceCnfTplDtlCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CutPriceCnfTplDtlCriteria extends CutPriceCnfTplDtlExample.Criteria{

		
	}
	
}

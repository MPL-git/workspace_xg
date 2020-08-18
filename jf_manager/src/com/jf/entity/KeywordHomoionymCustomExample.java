package com.jf.entity;



public class KeywordHomoionymCustomExample extends KeywordHomoionymExample {
	@Override
    public KeywordHomoionymCustomCriteria createCriteria() {
		KeywordHomoionymCustomCriteria criteria = new KeywordHomoionymCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class KeywordHomoionymCustomCriteria extends KeywordHomoionymCustomExample.Criteria{
		
	}
}
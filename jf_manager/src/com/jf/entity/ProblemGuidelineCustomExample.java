package com.jf.entity;


public class ProblemGuidelineCustomExample extends ProblemGuidelineExample {

	@Override
    public ProblemGuidelineCustomCriteria createCriteria() {
		ProblemGuidelineCustomCriteria criteria = new ProblemGuidelineCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ProblemGuidelineCustomCriteria extends ProblemGuidelineExample.Criteria {
		
	}
	
}

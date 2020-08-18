package com.jf.entity;



public class ApprovalApplicationNodeCustomExample extends ApprovalApplicationNodeExample {
	
	@Override
	public  ApprovalApplicationNodeCustomCriteria createCriteria() {
		 ApprovalApplicationNodeCustomCriteria criteria = new  ApprovalApplicationNodeCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
     public class  ApprovalApplicationNodeCustomCriteria extends  ApprovalApplicationNodeExample.Criteria {
    	 
    	/* public Criteria andprocedureNameEqualTo(Integer procedureId) {
 			addCriterion(" EXISTS(select p.id from bu_procedure p where p.del_flag = '0' and p.id = aa.procedure_id  and p.id = '"+procedureId+"')");
 			return this;
 		}*/
     }
}


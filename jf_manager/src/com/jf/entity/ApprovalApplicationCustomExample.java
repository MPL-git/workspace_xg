package com.jf.entity;



public class ApprovalApplicationCustomExample extends ApprovalApplicationExample {

	
	@Override
	public ApprovalApplicationCustomCriteria createCriteria() {
		ApprovalApplicationCustomCriteria criteria = new ApprovalApplicationCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
     public class ApprovalApplicationCustomCriteria extends ApprovalApplicationExample.Criteria {
    	 
    	 public Criteria andprocedureNameEqualTo(Integer procedureId) {
 			addCriterion(" EXISTS(select p.id from bu_procedure p where p.del_flag = '0' and p.id = aa.procedure_id  and p.id = '"+procedureId+"')");
 			return this;
 		}
    	 
    	 //抄送人是自己的流程
    	 public Criteria andCopiersEqualTo(Integer staffId) {
  			addCriterion("aa.procedure_id in (select p.id from bu_procedure p where p.id = aa.procedure_id and FIND_IN_SET('"+staffId+"',p.copiers) and p.del_flag='0')");
  			return this;
  		}
    	 
    	 //创建人
    	 public Criteria andCreateByLike(String createBy) {
   			addCriterion("aa.create_by in (select si.staff_id from sys_staff_info si where si.staff_id = aa.create_by and si.staff_name like '"+createBy+"')");
   			return this;
   		}
    	 
     }
}

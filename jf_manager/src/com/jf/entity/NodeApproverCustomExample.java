package com.jf.entity;



public class NodeApproverCustomExample extends NodeApproverExample {

	
	@Override
	public NodeApproverCustomCriteria createCriteria() {
		NodeApproverCustomCriteria criteria = new NodeApproverCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
     public class NodeApproverCustomCriteria extends NodeApproverExample.Criteria {
    	 
    	 public Criteria andapplicationNameLike(String  applicationName) {
 			addCriterion(" na.node_id in  (select aan.id  from bu_approval_application aa , bu_approval_application_node aan where na.node_id = aan.id and aan.approval_application_id = aa.id and aa.name like '"+applicationName+"')");
 			return this;
 		}
    	 
    	
    	 public Criteria andcreateByLike(String  createBy) {
  			addCriterion(" na.create_by in (select si.STAFF_ID from sys_staff_info si where si.STAFF_Id = na.create_by and si.STAFF_NAME like '"+createBy+"') ");
  			return this;
  		}
    	 
    	 public Criteria andapplicationIsEnable() {
   			addCriterion(" na.node_id in (select aan.id from bu_approval_application_node aan,bu_approval_application aa where na.node_id = aan.id and aan.approval_application_id = aa.id AND aa. STATUS NOT IN ('0') AND aa.del_flag='0' ) ");
   			return this;
   		}
    	 
    	 
     }
     
}

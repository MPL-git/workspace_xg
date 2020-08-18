package com.jf.entity;


public class BusinessCirclesAppealOrderCustomExample extends BusinessCirclesAppealOrderExample {

	@Override
	public BusinessCirclesAppealOrderCustomCriteria createCriteria() {
		BusinessCirclesAppealOrderCustomCriteria criteria = new BusinessCirclesAppealOrderCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
     public class BusinessCirclesAppealOrderCustomCriteria extends BusinessCirclesAppealOrderExample.Criteria {
    	 
    	 public Criteria andcompanyNameEqualTo(String companyName) {
 			addCriterion(" EXISTS(select bca.mcht_ids from bu_mcht_info m where m.del_flag = '0' and FIND_IN_SET(m.id,bca.mcht_ids) and m.company_name LIKE '"+companyName+"')");
 			return this;
 		}
    	 
    	 public Criteria andmchtCodeEqualTo(String mchtCode) {
  			addCriterion(" EXISTS(select bca.mcht_ids from bu_mcht_info m where m.del_flag = '0' and FIND_IN_SET(m.id,bca.mcht_ids) and m.mcht_code='"+mchtCode+"')");
  			return this;
  		}
    	 
    	 public Criteria andmemberIdEqualTo(String memberId) {
   			addCriterion("(FIND_IN_SET('"+memberId+"',bca.member_ids))");
   			return this;
   		}

	}
}

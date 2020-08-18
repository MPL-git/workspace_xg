package com.jf.entity;




public class MchtUserCustomExample extends MchtUserExample{
	
	@Override
    public MchtUserCustomCriteria createCriteria() {
		MchtUserCustomCriteria criteria = new MchtUserCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	
	
	
	public class MchtUserCustomCriteria extends MchtUserCustomExample.Criteria{
		
	    public Criteria andMchtTotalAuditStatusEqualTo(String totalAuditStatus) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and x.total_audit_status ='"+totalAuditStatus+"')");
	        return this;
        }
	    public Criteria andMchtStatusEqualTo(String mchtStatus) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and x.status ='"+mchtStatus+"')");
	    	return this;
	    }
	    
	    public Criteria andMchtTotalAuditStatusIsNull() {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and x.total_audit_status is null)");
	    	return this;
	    }
	    
	    public Criteria andZsContactEqualTo(Integer platFormContactId) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x,bu_mcht_platform_contact y where x.id=y.mcht_id and x.id= t.mcht_id and y.platform_contact_id="+platFormContactId+")");
	    	return this;
	    }
	    
	    public Criteria andMchtCodeEqualTo(String mchtCode) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and x.mcht_code ='"+mchtCode+"')");
	    	return this;
	    }
	    public Criteria andMchtNameEqualTo(String mchtName) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and x.short_name ='"+mchtName+"')");
	    	return this;
	    }
	    
	    public Criteria andMchtNameLike(String mchtName) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and (x.short_name like'%" + mchtName + "%' or x.shop_name like '%" + mchtName + "%' or x.company_name like '%" + mchtName + "%' ))");
	    	return this;
	    }
	    
	    public Criteria andMchtTotalAuditStatusIn(String totalAuditStatus) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and x.total_audit_status in "+totalAuditStatus+")");
	        return this;
        }
	    
	    public Criteria andMchtZsTotalAuditStatusIn(String zsTotalAuditStatus) {
	    	addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id= t.mcht_id and x.zs_total_audit_status in "+zsTotalAuditStatus+")");
	    	return this;
	    }
	    
	    public Criteria andProductTypeIdIn(String productTypeIds) {
	    	addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id= t.mcht_id AND mpt.is_main='1' AND mpt.`status`!=3 AND mpt.product_type_id IN ("+productTypeIds+"))");
	    	return this;
	    }
		public Criteria andSettledTypeEqualTo(String settledType) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id=t.mcht_id and mi.settled_type="+settledType+")");
	    	return this;
		}

		public Criteria andIsManageSelfEqualTo(String isManageSelf) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id=t.mcht_id and mi.is_manage_self="+isManageSelf+")");
			return this;
		}


	}
	
}

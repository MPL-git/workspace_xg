package com.jf.entity;


public class ImpeachMemberCustomExample extends ImpeachMemberExample {

	@Override
    public ImpeachMemberCustomCriteria createCriteria() {
		ImpeachMemberCustomCriteria criteria = new ImpeachMemberCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ImpeachMemberCustomCriteria extends ImpeachMemberExample.Criteria{
		
		public Criteria andCreateReceiverBy(Integer staffId) {
			addCriterion("(imm.create_by='"+staffId+"' or imm.commissioner_audit_by='"+staffId+"')");
	        return this;
	    }
		
		public Criteria andMchtCode(String mchtCode) {
			addCriterion("imm.mcht_id in(select id from bu_mcht_info mi where mi.id=imm.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" imm.mcht_id in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.short_name like '%"+mchtName+"%' or mi.company_name like '%"+mchtName+"%' or mi.shop_name like '%"+mchtName+"%' ))");
	        return this;
	    }
		
		public Criteria andCommissionerAuditBy(Integer commissionerAuditBy) {
			addCriterion("imm.commissioner_audit_by in(select s.STAFF_ID from sys_staff_info s where s.STAFF_ID=imm.commissioner_audit_by and s.STAFF_ID='"+commissionerAuditBy+"')");
	        return this;
	    }
		public Criteria andFwAuditBy(Integer fwAuditBy) {
			addCriterion("imm.fw_audit_by in(select s.STAFF_ID from sys_staff_info s where s.STAFF_ID=imm.fw_audit_by and s.STAFF_ID='"+fwAuditBy+"')");
	        return this;
	    }
		public Criteria andSubOrderCode(String subOrderCode) {
			addCriterion("EXISTS(select id from bu_sub_order so where FIND_IN_SET(so.id,imm.sub_order_ids) and so.del_flag='0' and so.sub_order_code='"+subOrderCode+"')");
	        return this;
	    }
		
		public Criteria andProductTypeIdIn(String sysStaffProductType) {
			addCriterion(" EXISTS(select mpt.mcht_id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = imm.mcht_id and mpt.product_type_id in("+sysStaffProductType+") )");
			return this;
		}
		
	}
	
}

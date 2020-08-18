package com.jf.entity;



public class MchtDepositDtlCustomExample extends MchtDepositDtlExample{
	
	@Override
    public MchtDepositDtlCustomCriteria createCriteria() {
		MchtDepositDtlCustomCriteria criteria = new MchtDepositDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtDepositDtlCustomCriteria extends MchtDepositDtlExample.Criteria{

		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi,bu_mcht_deposit md where mi.id=md.mcht_id and md.id=t.deposit_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andCompanyNameEqualTo(String companyName) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi,bu_mcht_deposit md where mi.id=md.mcht_id and md.id=t.deposit_id and mi.del_flag='0' and mi.company_name='"+companyName+"')");
			return this;
		}
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS(select mpc.mcht_id from bu_mcht_platform_contact mpc,bu_mcht_deposit a where mpc.del_flag = '0' and a.del_flag='0' and mpc.status = '1' and a.id=t.deposit_id and mpc.mcht_id =a.mcht_id  and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer staffID) {//查看负责类目
			addCriterion(" EXISTS(select s.product_type_id from bu_mcht_product_type mpc,sys_staff_product_type s,bu_mcht_deposit m where s.product_type_id=mpc.product_type_id and m.mcht_id=mpc.mcht_id and m.id=t.deposit_id and mpc.is_main='1' and s.del_flag='0' and m.del_flag='0' and mpc.del_flag = '0' and mpc.status = '1' and s.staff_id ="+staffID+" )");
			return this;
		}
		
	}
}
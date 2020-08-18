package com.jf.entity;



public class MchtCloseApplicationCustomExample extends MchtCloseApplicationExample {

	@Override
    public MchtCloseApplicationCriteria createCriteria() {
		MchtCloseApplicationCriteria criteria = new MchtCloseApplicationCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtCloseApplicationCriteria extends MchtCloseApplicationExample.Criteria{

		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and (mi.short_name like '%" + mchtName + "%' or mi.shop_name like '%" + mchtName + "%' or mi.company_name like '%" + mchtName + "%' ))");
	    	return this;
		}

		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and mi.mcht_code='"+mchtCode+"')");
	    	return this;
		}

		//是否自营
		public Criteria andIsManageSelfEqualTo(String isManageSelf) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and mi.is_manage_self='"+isManageSelf+"')");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = t.mcht_id and mpt.product_type_id="+productTypeId+")");
			return this;
		}

		public Criteria andProductTypeIdsIn(String productTypeIdsStr) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = t.mcht_id and mpt.product_type_id in ("+productTypeIdsStr+"))");
			return this;
		}
		
		//法务对接人
		public Criteria andFwPlatformContractIdEqualTo(Integer platformContractId) {
			addCriterion(" EXISTS (select a.mcht_id from bu_mcht_platform_contact a, bu_platform_contact b where a.platform_contact_id = b.id and a.mcht_id = t.mcht_id and a.`status` = '1' and b.staff_id = '"+platformContractId+"' and b.contact_type = 7  and a.del_flag = '0' and b.del_flag = '0')");
			return this;
		}
		
		//未寄出
		public Criteria andNotSentOut( ) {
			addCriterion("  (express_no = '' or express_no is null)");
			return this;
		}
		
		//未处理
		public Criteria andNotUntreated( ) {
			addCriterion("  (contract_archive_status = '' or contract_archive_status is null or contract_archive_status = '0')");
			return this;
		}

		//未结清
		public Criteria andUnSettled() {
			addCriterion(" EXISTS (select  1  FROM `bu_mcht_settle_order` b WHERE b.mcht_id = t.mcht_id AND b.del_flag = '0' AND b.need_pay_amount > 0)");
			return this;
		}

		//已结清
		public Criteria andSettled() {
			addCriterion(" NOT EXISTS (select  1  FROM `bu_mcht_settle_order` b WHERE b.mcht_id = t.mcht_id AND b.del_flag = '0' AND b.need_pay_amount > 0)");
			return this;
		}

		//已上传协议
		public Criteria andCloseAplicationPicIsNotNull(){
			addCriterion("EXISTS (SELECT 1 FROM bu_mcht_close_application_pic p WHERE p.mcht_close_application_id = t.id AND p.del_flag = '0')");
			return this;
		}

		//未上传协议
		public Criteria andCloseAplicationPicIsNull(){
			addCriterion("NOT EXISTS (SELECT 1 FROM bu_mcht_close_application_pic p WHERE p.mcht_close_application_id = t.id AND p.del_flag = '0')");
			return this;
		}
		//确认挂起状态
		public  Criteria andCloseHangUpStatusEqualTo(String closeHangUpStatus){
			addCriterion("t.id IN ( SELECT id FROM bu_mcht_close_application WHERE kf_close_hang_up_status = "+closeHangUpStatus+"  UNION SELECT id FROM bu_mcht_close_application WHERE fw_close_hang_up_status = "+closeHangUpStatus+" )");
			return this;
		}

	}
	
	
}

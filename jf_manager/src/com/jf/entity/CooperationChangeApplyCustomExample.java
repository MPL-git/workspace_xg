package com.jf.entity;


public class CooperationChangeApplyCustomExample extends CooperationChangeApplyExample{
	
	@Override
    public CooperationChangeApplyCustomCriteria createCriteria() {
		CooperationChangeApplyCustomCriteria criteria = new CooperationChangeApplyCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CooperationChangeApplyCustomCriteria extends CooperationChangeApplyExample.Criteria{

		
		/**
		 * 
		 * @Title andActivityArea   
		 * @Description TODO( 会场类型=推广会场  且  启用状态=启用)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:01:01
		 */
		public Criteria andActivityArea() {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id)");
			return this;
		}

		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" t.mcht_id = (select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.status = '1' and mi.mcht_code='"+mchtCode+"')");
			return this;
		}

		public Criteria andNoStatusMchtCodeEqualTo(String mchtCode) {
			addCriterion(" t.mcht_id = (select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.mcht_code='"+mchtCode+"')");
			return this;
		}

		public Criteria andChangeApplyTypeEqualTo(String changeApplyType) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.status = '1' and mi.id = t.mcht_id and FIND_IN_SET("+changeApplyType+",mi.change_apply_type))");
			return this;
		}

		public Criteria andZsOrFwReject() {
			addCriterion("(t.zs_audit_status=2 or t.fw_audit_status=2)");
			return this;
		}

		public Criteria andMchtProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id = "+productTypeId+")");
			return this;
		}

		public Criteria andPlatContactStaffIdEqualTo(Integer platContactStaffId) {
			addCriterion(" t.mcht_id IN(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' and (pc.staff_id = "+platContactStaffId+" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = "+platContactStaffId+")) and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id )");
			return this;
		}

		public Criteria andUploadStatusIsNullOrEqualToZero() {
			addCriterion("upload_status is null or upload_status = '0' ");
			return this;
		}

		
	}
}
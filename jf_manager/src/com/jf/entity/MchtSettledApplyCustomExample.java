package com.jf.entity;

public class MchtSettledApplyCustomExample extends MchtSettledApplyExample{
	
	@Override
    public MchtSettledApplyCustomCriteria createCriteria() {
		MchtSettledApplyCustomCriteria criteria = new MchtSettledApplyCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtSettledApplyCustomCriteria extends MchtSettledApplyExample.Criteria{

		public Criteria andStaffIdEqualTo(int staffId) {
			addCriterion(" EXISTS(select id from bu_platform_contact mi where mi.id=a.platform_contact_id and mi.staff_id="+staffId+")");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductTypeId   
		 * @Description TODO(主营类目)   
		 * @author pengl
		 * @date 2018年9月30日 下午1:00:08
		 */
		public Criteria andProductTypeId(String productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.is_main = '1' and mpt.mcht_id = a.mcht_id and mpt.product_type_id = "+ productTypeId +" )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtShowScope   
		 * @Description TODO(本人负责类目 和 分配给本人的商家)   
		 * @author pengl
		 * @date 2018年9月30日 下午1:45:17
		 */
		public Criteria andMchtShowScope(Integer staffId) {
			addCriterion(" (a.mcht_id in(select mpt.mcht_id from sys_staff_product_type spt, bu_mcht_product_type mpt where spt.del_flag = '0' and spt.staff_id = "+ staffId +" and mpt.del_flag = '0' and mpt.is_main = '1' and mpt.product_type_id = spt.product_type_id ) "
					+ " or a.mcht_id in(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' "
									+ " and (pc.staff_id = "+ staffId +" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = " + staffId + ")) "
											+ " and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id))");
			return this;
		}
		
		
		
	}
}
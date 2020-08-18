package com.jf.entity;





public class ViolateOrderCustomExample extends ViolateOrderExample{
	
	@Override
    public ViolateOrderCustomCriteria createCriteria() {
		ViolateOrderCustomCriteria criteria = new ViolateOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ViolateOrderCustomCriteria extends ViolateOrderExample.Criteria{
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" t.mcht_id = (select mi.id from bu_mcht_info mi where mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andActivityIdEqualTo(Integer activityId) {
			addCriterion(" EXISTS(SELECT id FROM bu_order_dtl od WHERE od.sub_order_id = t.sub_order_id and od.activity_id='"+activityId+"')");
	        return this;
	    }
		
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion(" EXISTS(select id from bu_sub_order so where so.id=t.sub_order_id and so.del_flag='0' and so.sub_order_code='"+subOrderCode+"')");
	        return this;
	    }
		
		public Criteria andComplainOrderStatusEqualTo(String complainOrderStatus) {
			addCriterion(" EXISTS(select id from bu_violate_complain_order vco where vco.violate_order_id=t.id and vco.del_flag='0' and vco.status='"+complainOrderStatus+"')");
			return this;
		}
		
		public Criteria andNameLikeTo(String name) {
			addCriterion(" t.mcht_id IN (select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.company_name like '%"+name+"%' or mi.shop_name like '%"+name+"%')");
	        return this;
	    }

		public Criteria andProcesByEqualTo(Integer procesBy) {
			addCriterion(" EXISTS(select id from bu_violate_complain_order vco where vco.violate_order_id=t.id and vco.del_flag='0' and vco.proces_by="+procesBy+")");
	        return this;
		}
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS(select mpc.mcht_id from bu_mcht_platform_contact mpc where mpc.del_flag = '0' and mpc.status = '1' and mpc.mcht_id = t.mcht_id and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductTypeIdEqualTo   
		 * @Description TODO(主营类目)   
		 * @author pengl
		 * @date 2018年5月14日 上午9:56:07
		 */
		public Criteria andProductTypeIdEqualTo(String productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id = "+ productTypeId +" )");
			return this;
		}
		
		public Criteria andProductTypeIdIn(String productTypeIds) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id in("+ productTypeIds +") )");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer staffID) {//查看负责类目
			addCriterion(" EXISTS(select s.product_type_id from bu_mcht_product_type mpt,sys_staff_product_type s where mpt.del_flag = '0' and mpt.is_main = '1' and s.product_type_id=mpt.product_type_id and s.del_flag='0' and mpt.mcht_id = t.mcht_id and s.staff_id = "+ staffID +" )");
			return this;
		}
		
		public Criteria andIsShamDeliveryOrder() {
			addCriterion(" EXISTS(select id from bu_order_abnormal_log oal where oal.sub_order_id=t.sub_order_id and oal.del_flag='0' and oal.abnormal_type='4')");
	        return this;
	    }



	}
}
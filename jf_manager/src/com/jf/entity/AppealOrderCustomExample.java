package com.jf.entity;

import com.jf.entity.InterventionOrderExample.Criteria;



public class AppealOrderCustomExample extends AppealOrderExample{
	
	@Override
    public AppealOrderCustomCriteria createCriteria() {
		AppealOrderCustomCriteria criteria = new AppealOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class AppealOrderCustomCriteria extends AppealOrderExample.Criteria{
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select t.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
            addCriterion("EXISTS(select t.id from bu_sub_order so where so.id=t.sub_order_id and so.del_flag='0' and so.sub_order_code='"+subOrderCode+"')");
            return this;
        }
		
		public Criteria andProductTypeIdEqualTo(Integer staffID) {//查看负责类目
			addCriterion(" EXISTS(select s.product_type_id from bu_mcht_product_type mpc,sys_staff_product_type s where s.product_type_id=mpc.product_type_id and t.mcht_id=mpc.mcht_id and mpc.is_main='1' and s.del_flag='0' and mpc.del_flag = '0' and mpc.status = '1' and s.staff_id ="+staffID+" )");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductTypeIdEqualTo   
		 * @Description TODO(类目)   
		 * @author pengl
		 * @date 2018年9月5日 下午2:19:40
		 */
		public Criteria andProductTypeIdEqualTo(String productTypeId) {
            addCriterion("EXISTS(select t.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id = '" + productTypeId + "' )");
            return this;
        }
		public Criteria andProductTypeIdIn(String productTypeIds) {
			addCriterion("EXISTS(select t.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id in(" + productTypeIds + ") )");
            return this;
        }
		
		/**
		 * 
		 * @Title andMemberStatus   
		 * @Description TODO(投诉异常)   
		 * @author pengl
		 * @date 2018年9月29日 下午8:40:29
		 */
		public Criteria andMemberStatus(String memberStatus) {
			if("P".equals(memberStatus)) {
				addCriterion(" EXISTS(select so.id from bu_sub_order so, bu_combine_order co, bu_member_info mi where so.del_flag = '0' and so.id = t.sub_order_id and so.combine_order_id = co.id and co.member_id = mi.id and mi.status = 'P')");
			}else {
				addCriterion(" EXISTS(select so.id from bu_sub_order so, bu_combine_order co, bu_member_info mi where so.del_flag = '0' and so.id = t.sub_order_id and so.combine_order_id = co.id and co.member_id = mi.id and mi.status != 'P')");
			}
			return this;
		}
		
		
		public Criteria andproductId(String productId) {//商品ID
			addCriterion(" EXISTS(select odl.id from bu_order_dtl odl,bu_product p where odl.del_flag='0' and p.del_flag='0' and odl.id = t.order_dtl_id and odl.product_id =p.id and p.code = '"+productId+"')");
	        return this;
		}
		
		
	}
}
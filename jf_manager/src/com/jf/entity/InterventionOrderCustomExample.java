package com.jf.entity;

public class InterventionOrderCustomExample extends InterventionOrderExample {

	@Override
	public InterventionOrderCustomCriteria createCriteria() {
		InterventionOrderCustomCriteria criteria = new InterventionOrderCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	public class InterventionOrderCustomCriteria extends InterventionOrderExample.Criteria{
		
		/**
		 * 
		 * @Title andCustomerServiceOrderCode   
		 * @Description TODO(售后编号)   
		 * @author pengl
		 * @date 2018年4月4日 上午9:36:02
		 */
		public Criteria andCustomerServiceOrderCode(String customerServiceOrderCode) {
			addCriterion(" EXISTS(select cso.id from bu_customer_service_order cso where cso.del_flag = '0' and cso.id = t.service_order_id and cso.order_code = '"+customerServiceOrderCode+"' )");
	        return this;
		}
		
		/**
		 * 
		 * @Title andMchtName   
		 * @Description TODO(商家名称)   
		 * @author pengl
		 * @date 2018年4月4日 上午9:36:42
		 */
		public Criteria andMchtName(String mchtName) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and (mi.shop_name like '%"+mchtName+"%' or mi.company_name like '%"+mchtName+"%' ))");
			return this;
		}
		
		//商家序号
		public Criteria andmchtCode(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and mi.mcht_code= '"+mchtCode+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtPhone   
		 * @Description TODO(商家联系电话)   
		 * @author pengl
		 * @date 2018年4月4日 上午9:37:02
		 */
		public Criteria andMchtPhone(String mchtPhone) {
			addCriterion(" EXISTS(select from bu_mcht_platform_contact mpc, bu_platform_contact pc where mpc.del_flag = '0' and pc.del_flag = '0' mpc.status = '1' "
					+ " mpc.mcht_id = t.mcht_id and mpc.platform_contact_id = pc.id and pc.contact_type in(2, 4, 6) and pc.mobile = '"+ mchtPhone +"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andIsInitiateViolate   
		 * @Description TODO(是否发起违规)   
		 * @author pengl
		 * @date 2018年4月4日 下午2:18:28
		 */
		public Criteria andIsInitiateViolate() {
			addCriterion(" (t.is_initiate_violate = '0' or t.is_initiate_violate is null)");
			return this;
		}
		
		/**
		 * 
		 * @Title andIsComment   
		 * @Description TODO(是否评价)   
		 * @author pengl
		 * @date 2018年4月4日 下午2:19:23
		 */
		public Criteria andIsComment() {
			addCriterion(" (t.is_comment = '0' or t.is_comment is null)");
			return this;
		}
		
		public Criteria andProductTypeid(Integer staffID) {//查看负责类目
			addCriterion(" EXISTS(select s.product_type_id from bu_mcht_product_type mpc,sys_staff_product_type s where s.product_type_id=mpc.product_type_id and t.mcht_id=mpc.mcht_id and mpc.is_main='1' and s.del_flag='0' and mpc.del_flag = '0' and mpc.status = '1' and s.staff_id ="+staffID+" )");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductTypeIdIn   
		 * @Description TODO(商家主营类目)   
		 * @author pengl
		 * @date 2018年9月5日 上午11:10:14
		 */
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id = "+productTypeId+" )");
			return this;
		}
		public Criteria andProductTypeIdIn(String productTypeIds) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id in("+productTypeIds+") )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMemberStatus   
		 * @Description TODO(介入异常)   
		 * @author pengl
		 * @date 2018年9月29日 下午8:03:42
		 */
		public Criteria andMemberStatus(String memberStatus) {
			if("P".equals(memberStatus)) {
				addCriterion(" EXISTS(select cso.id from bu_customer_service_order cso, bu_sub_order so, bu_combine_order co, bu_member_info mi where cso.del_flag = '0' and cso.id = t.service_order_id and cso.sub_order_id = so.id and so.combine_order_id = co.id and co.member_id = mi.id and mi.status = 'P')");
			}else {
				addCriterion(" EXISTS(select cso.id from bu_customer_service_order cso, bu_sub_order so, bu_combine_order co, bu_member_info mi where cso.del_flag = '0' and cso.id = t.service_order_id and cso.sub_order_id = so.id and so.combine_order_id = co.id and co.member_id = mi.id and mi.status != 'P')");
			}
			return this;
		}
		
		public Criteria andproductId(String productId) {//商品ID
			addCriterion(" EXISTS(select cso.id from bu_customer_service_order cso,bu_order_dtl odl,bu_product p where cso.del_flag = '0' and p.del_flag='0' and odl.del_flag='0' and cso.order_dtl_id=odl.id and cso.id = t.service_order_id and odl.product_id =p.id and p.code = '"+productId+"' )");
	        return this;
		}
		
		
	}
	
}

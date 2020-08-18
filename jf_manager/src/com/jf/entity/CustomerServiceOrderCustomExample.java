package com.jf.entity;

public class CustomerServiceOrderCustomExample extends CustomerServiceOrderExample{
	
	@Override
    public CustomerServiceOrderCustomCriteria createCriteria() {
		CustomerServiceOrderCustomCriteria criteria = new CustomerServiceOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CustomerServiceOrderCustomCriteria extends CustomerServiceOrderExample.Criteria{

		public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" a.sub_order_id in (select mp.id from bu_sub_order mp, bu_mcht_info mi where mi.id=mp.mcht_id and mi.del_flag='0' and (  mi.shop_name like '"+mchtName+"' or  mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' ))");
	        return this;
	    }
		
		public Criteria andActivityIdEqualTo(Integer activityId) {
			addCriterion(" a.order_dtl_id in (select mi.id from bu_order_dtl mi where mi.del_flag='0' and mi.activity_id='"+activityId+"')");
			return this;
		}
		
		public Criteria andProductBrandNameLikeTo(String productBrandName) {
			addCriterion(" a.order_dtl_id in (select mi.id from bu_order_dtl mi where mi.del_flag='0' and mi.brand_name like '"+productBrandName+"')");
			return this;
		}
		
		public Criteria andProductIdEqualTo(Integer productId) {
			addCriterion(" a.order_dtl_id in (select mi.id from bu_order_dtl mi where mi.del_flag='0' and mi.product_id='"+productId+"')");
			return this;
		}
		
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi where mi.id=a.sub_order_id and mi.del_flag='0' and mi.sub_order_code='"+subOrderCode+"')");
			return this;
		}
		
		public Criteria andReceiverNameEqualTo(String receiverName) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mp, bu_combine_order mi where mp.id=a.sub_order_id and mi.id=mp.combine_order_id and mi.del_flag='0' and mi.receiver_name='"+receiverName+"')");
			return this;
		}
		
		public Criteria andMchtIdEqualTo(Integer mchtId) {
			addCriterion(" a.sub_order_id in (select mp.id from bu_sub_order mp where mp.del_flag='0' and mp.mcht_id="+mchtId+")");
	        return this;
	    }
		
		public Criteria andRefundDateGreaterThanOrEqualTo(String refundDate) {
			addCriterion(" EXISTS(select so.id from bu_refund_order so where so.service_order_id=a.id and so.del_flag='0' and so.status='1' and so.success_date >='"+refundDate+"')");
			return this;
		}
		
		public Criteria andRefundDateLessThanOrEqualTo(String refundDate) {
			addCriterion(" EXISTS(select so.id from bu_refund_order so where so.service_order_id=a.id and so.del_flag='0' and so.status='1' and so.success_date <='"+refundDate+"')");
			return this;
		}
		
		public Criteria andLogDateGreaterThanOrEqualTo(String logDate) {
			addCriterion(" EXISTS(select csl.id from bu_customer_service_log csl where csl.service_order_id=a.id and csl.del_flag='0' and csl.create_date >='"+logDate+"' order by csl.id desc limit 1)");
			return this;
		}
		
		public Criteria andLogDateLessThanOrEqualTo(String logDate) {
			addCriterion(" EXISTS(select csl.id from bu_customer_service_log csl where csl.service_order_id=a.id and csl.del_flag='0' and csl.create_date <='"+logDate+"' order by csl.id desc limit 1)");
			return this;
		}
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS(select mpc.mcht_id from bu_mcht_platform_contact mpc, bu_sub_order s where mpc.del_flag = '0' and s.del_flag='0' and a.sub_order_id=s.id  and mpc.status = '1' and mpc.mcht_id = s.mcht_id and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		
		public Criteria andSubOrderProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od,bu_product p,bu_product_type pt,bu_product_type fpt where od.id=a.order_dtl_id and od.del_flag='0' and od.product_id = p.id and p.product_type_id = pt.id and pt.parent_id = fpt.id and fpt.parent_id = "+productTypeId+")");
			return this;
		}
		
		public Criteria andSubOrderProductTypeIdIn(String productTypeIds) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od,bu_product p,bu_product_type pt,bu_product_type fpt where od.id=a.order_dtl_id and od.del_flag='0' and od.product_id = p.id and p.product_type_id = pt.id and pt.parent_id = fpt.id and fpt.parent_id in("+productTypeIds+") )");
			return this;
		}
		
		public Criteria andPlatformContactsEqualTo(Integer staffID) {//查看负责类目
			addCriterion("a.sub_order_id in (select bs.id from bu_mcht_product_type mpc,sys_staff_product_type s,bu_sub_order bs where s.product_type_id=mpc.product_type_id and bs.mcht_id=mpc.mcht_id and bs.del_flag='0' and mpc.is_main='1' and s.del_flag='0' and mpc.del_flag = '0' and mpc.status = '1' and s.staff_id = "+staffID+")");
			return this;
		}
		
		public Criteria andMchtCoddeTo(String mchtCode) {
			addCriterion("a.sub_order_id in(select mp.id from bu_mcht_info mi,bu_sub_order mp where mi.id=mp.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		public Criteria andplatContactStaffIdtEqualTo(Integer platContactStaffId) {//对接人的商家
			addCriterion(" EXISTS (select mpc.mcht_id from bu_mcht_platform_contact mpc,bu_platform_contact bc,bu_sub_order s where mpc.del_flag = '0' and mpc.status = '1' and mpc.mcht_id=s.mcht_id and a.sub_order_id=s.id and mpc.platform_contact_id=bc.id and bc.staff_id = "+platContactStaffId+" )");
			return this;
		}
		
		public Criteria andProductNameLikeTo(String productName) {
			addCriterion(" a.order_dtl_id in (select mi.id from bu_order_dtl mi where mi.del_flag='0' and mi.product_name like '"+productName+"')");
			return this;
		}
		
		
		/**
		 * 
		 * @Title andMemberStatus   
		 * @Description TODO(售后异常)   
		 * @author pengl
		 * @date 2018年9月29日 下午6:56:42
		 */
		public Criteria andMemberStatus(String memberStatus) {
			if("P".equals(memberStatus)) {
				addCriterion(" EXISTS(select co.id from bu_sub_order so, bu_combine_order co, bu_member_info mi where so.del_flag = '0' and co.del_flag = '0' and mi.del_flag = '0' and co.id = so.combine_order_id and co.member_id = mi.id and so.id = a.sub_order_id and mi.status = 'P')");
			}else {
				addCriterion(" EXISTS(select co.id from bu_sub_order so, bu_combine_order co, bu_member_info mi where so.del_flag = '0' and co.del_flag = '0' and mi.del_flag = '0' and co.id = so.combine_order_id and co.member_id = mi.id and so.id = a.sub_order_id and mi.status != 'P')");
			}
			return this;
		}

		/**
		 * 判断是否是SVIP
		 */
		public Criteria andIsSvip( ) {
			addCriterion("EXISTS (select a.id from bu_combine_order co,bu_sub_order so,bu_member_info mi where a.sub_order_id = so.id and so.combine_order_id = co.id and co.member_id = mi.id and mi.is_svip = '1' and co.del_flag='0' and so.del_flag='0' and mi.del_flag='0')");
			return this;
		}


		
		
	}
}
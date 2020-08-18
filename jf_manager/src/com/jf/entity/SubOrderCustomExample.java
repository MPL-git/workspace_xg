package com.jf.entity;


import java.util.Date;

public class SubOrderCustomExample extends SubOrderExample{
    protected Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public SubOrderCustomCriteria createCriteria() {
		SubOrderCustomCriteria criteria = new SubOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SubOrderCustomCriteria extends SubOrderExample.Criteria{
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" a.mcht_id in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.short_name like '%"+mchtName+"%' or mi.company_name like '%"+mchtName+"%' or mi.shop_name like '%"+mchtName+"%' ))");
	        return this;
	    }
		
		public Criteria andOrderCreateDateGreaterThanOrEqualTo(String orderCreateDateBegin) {
			addCriterion(" EXISTS(select id from bu_combine_order mi where mi.id=a.combine_order_id and mi.create_date >= '"+orderCreateDateBegin+"')");
			return this;
		}
		
		public Criteria andOrderCreateDateLessThanOrEqualTo(String orderCreateDateEnd) {
			addCriterion(" EXISTS(select id from bu_combine_order mi where mi.id=a.combine_order_id and mi.create_date <= '"+orderCreateDateEnd+"')");
			return this;
		}
		
		public Criteria andOrderPayDateGreaterThanOrEqualTo(String orderPayDateBegin) {
			addCriterion(" a.combine_order_id in (select id from bu_combine_order mi where mi.pay_date >= '"+orderPayDateBegin+"')");
			return this;
		}
		
		public Criteria andOrderPayDateLessThanOrEqualTo(String orderPayDateEnd) {
			addCriterion(" a.combine_order_id in (select id from bu_combine_order mi where mi.pay_date <= '"+orderPayDateEnd+"')");
			return this;
		}
		
		public Criteria andPhoneOrMobileEqualTo(String phone) {
			addCriterion(" a.combine_order_id in (select bco.id from bu_combine_order bco where bco.del_flag = '0'"
					+ " and bco.receiver_phone = '" + phone + "' )");
			return this;
		}
		
		public Criteria andMemberInfoIdEqualTo(String memberInfoId) {
			addCriterion(" a.combine_order_id in (select bco.id from bu_combine_order bco where bco.del_flag = '0'"
					+ " and bco.member_id = " + memberInfoId + " )");
			return this;
		}

		public Criteria andNotExisitWuliuInfo() {
			addCriterion(" (EXISTS(select id from bu_kdn_wuliu_info b where b.express_id=a.express_id and b.logistic_code = a.express_no and b.status = '0' and b.del_flag = '0') or NOT EXISTS(select id from bu_kdn_wuliu_info b where b.express_id=a.express_id and b.logistic_code = a.express_no and b.del_flag = '0'))");
			return this;
		}
		
		public Criteria andShopNameEqualTo(String shopName) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.shop_name='"+shopName+"')");
	        return this;
	    }
		
		public Criteria andBrandIdEqualTo(Integer brandId) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od,bu_product p where od.sub_order_id=a.id and od.product_id = p.id and od.del_flag='0' and p.brand_id="+brandId+")");
	        return this;
	    }
		
		public Criteria andOvertimeFollowStatusIsNullOrNoDeal() {
			addCriterion(" a.overtime_follow_status is null or a.overtime_follow_status = '1' ");
			return this;
		}

		public Criteria andShamFollowStatusIsNullOrNoDeal() {
			addCriterion(" a.sham_follow_status is null or a.sham_follow_status = '1' ");
			return this;
		}
		
		public Criteria andBrandNameEqualTo(String brandName) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.sub_order_id=a.id and od.del_flag='0' and od.brand_name='"+brandName+"')");
	        return this;
	    }

		public Criteria andArtNoLikeTo(String artNo) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.sub_order_id=a.id and od.del_flag='0' and od.art_no like '"+artNo+"')");
	        return this;
		}
		
		public Criteria andMemberNickEqualTo(String memberNick) {
			addCriterion(" a.combine_order_id in (select bco.id from bu_combine_order bco where bco.del_flag = '0'"
					+ " and bco.member_nick = '" + memberNick + "' )");
			return this;
		}

		public Criteria andReceiverNameLikeTo(String receiverName) {
			addCriterion(" a.combine_order_id in (select bco.id from bu_combine_order bco where bco.del_flag = '0'"
					+ " and bco.receiver_name like '" + receiverName + "' )");
			return this;
		}

		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = a.mcht_id and mpt.product_type_id = "+productTypeId+")");
			return this;
		}
		
		public Criteria andProductTypeIdIn(String productTypeIds) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = a.mcht_id and mpt.product_type_id in("+productTypeIds+") )");
			return this;
		}

		public Criteria andPaymentIdEqualTo(Integer paymentId) {
			addCriterion(" a.combine_order_id in (select bco.id from bu_combine_order bco where bco.del_flag = '0'"
					+ " and bco.payment_id = " + paymentId + " )");
			return this;
		}
		
		/**
		 * 
		 * @Title andPlatformContactEqualTo   
		 * @Description TODO(这里用一句话描述这个方法的作用)   
		 * @author pengl
		 * @date 2018年3月10日 下午2:43:38
		 */
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS (select mpc.mcht_id from bu_mcht_platform_contact mpc where mpc.del_flag = '0' and mpc.status = '1' and mpc.mcht_id=a.mcht_id and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		
		/*public Criteria andplatContactStaffIdtEqualTo(Integer platContactStaffId) {//对接人的商家
			addCriterion(" EXISTS (select mpc.mcht_id from bu_mcht_platform_contact mpc,bu_platform_contact bc where mpc.del_flag = '0' and mpc.status = '1' and mpc.mcht_id=a.mcht_id and mpc.platform_contact_id=bc.id and bc.staff_id = "+platContactStaffId+" )");
			return this;
		}*/
		
		/*public Criteria andPlatformContactsEqualTo(Integer staffID) {//查看负责类目
			addCriterion(" EXISTS (select mpc.id from bu_mcht_product_type mpc,sys_staff_product_type s where s.product_type_id=mpc.product_type_id and mpc.is_main='1' and s.del_flag='0' and mpc.del_flag = '0' and mpc.status = '1' and mpc.mcht_id=a.mcht_id and s.staff_id = "+staffID+")");
			return this;
		}*/
		
		/**
		 * 
		 * @Title andMemberInfoOr   
		 * @Description TODO(与当前用户（用户ID同，收货电话相同、用户手机设备信息）其中一个相同的聚合)   
		 * @author pengl
		 * @date 2018年9月29日 下午4:25:44
		 */
		public Criteria andMemberInfoOr(MemberInfo memberInfo) {
			addCriterion(" a.id in (SELECT DISTINCT T.id FROM(SELECT so.id FROM bu_sub_order so WHERE so.del_flag = '0' AND so.combine_order_id IN (SELECT co.id FROM bu_combine_order co WHERE co.del_flag = '0' AND co.`status` = '1' AND co.member_id = "+ memberInfo.getId() +")"
					+ " UNION ALL SELECT so.id FROM bu_sub_order so WHERE so.del_flag = '0' AND so.combine_order_id IN (SELECT co.id FROM bu_combine_order co WHERE co.del_flag = '0' AND co.`status` = '1' AND co.receiver_phone = '"+ memberInfo.getMobile() +"')"
					+ " UNION ALL SELECT so.id FROM bu_sub_order so WHERE so.del_flag = '0' AND so.combine_order_id IN (SELECT co.id FROM bu_combine_order co, bu_member_info mi"
					+ " WHERE co.del_flag = '0' AND co.`status` = '1' AND mi.req_imei IS NOT NULL AND mi.req_imei != '' AND mi.req_imei NOT LIKE '000000%' AND mi.req_imei = '"+ memberInfo.getReqImei() +"' AND mi.id = co.member_id)) T)");
			return this;
		}
		
		/**
		 * 
		 * @Title andMemberStatus   
		 * @Description TODO(订单异常)   
		 * @author pengl
		 * @date 2018年9月29日 下午6:20:40
		 */
		public Criteria andMemberStatus(String memberStatus) {
			if("P".equals(memberStatus)) {
				addCriterion(" a.combine_order_id in (select co.id from bu_combine_order co, bu_member_info mi where co.del_flag = '0' and mi.del_flag = '0' and co.member_id = mi.id and mi.status = 'P')");
			}else {
				addCriterion(" a.combine_order_id in (select co.id from bu_combine_order co, bu_member_info mi where co.del_flag = '0' and mi.del_flag = '0' and co.member_id = mi.id and mi.status != 'P')");
			}
			return this;
		}
		
		public Criteria andProductNameLikeTo(String productName) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.sub_order_id=a.id and od.del_flag='0' and od.product_name like '%"+productName+"%')");
	        return this;
	    }
		
		public Criteria andPromotionTypeTo(String promotionType) {//推广类型
			addCriterion("a.combine_order_id in (select co.id from bu_combine_order co where co.id=a.combine_order_id and co.del_flag='0' and co.promotion_type='"+promotionType+"')");
	        return this;
	    }
	    public Criteria andPicIsUpload() {//推广类型
			addCriterion("a.id IN (SELECT DISTINCT a.id FROM bu_sub_order a,bu_combine_order b,bu_member_college_student_certification m WHERE b.member_id = m.member_id AND b.id=a.combine_order_id)");
	        return this;
	    }
	    public Criteria andPicIsNotUpload() {//推广类型
			addCriterion("a.id NOT IN (SELECT DISTINCT a.id FROM bu_sub_order a,bu_combine_order b,bu_member_college_student_certification m WHERE b.member_id = m.member_id AND b.id=a.combine_order_id)");
	        return this;
	    }

	}
}
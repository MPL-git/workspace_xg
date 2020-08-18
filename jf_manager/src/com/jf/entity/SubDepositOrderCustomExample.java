package com.jf.entity;

import java.util.Date;
import java.util.List;

import com.jf.entity.CustomerServiceOrderExample.Criteria;






public class SubDepositOrderCustomExample extends SubDepositOrderExample{
	@Override
    public SubDepositOrderCustomCriteria createCriteria() {
		SubDepositOrderCustomCriteria criteria = new SubDepositOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SubDepositOrderCustomCriteria extends SubDepositOrderExample.Criteria{
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" t.mcht_id in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.short_name like '%"+mchtName+"%' or mi.company_name like '%"+mchtName+"%' or mi.shop_name like '%"+mchtName+"%' ))");
	        return this;
	    }
		
		public Criteria andCombineDepositOrderCodeEqualTo(String combineDepositOrderCode) {
			addCriterion(" t.combine_deposit_order_id = (select cdo.id from bu_combine_deposit_order cdo where cdo.del_flag='0' and cdo.combine_deposit_order_code='"+combineDepositOrderCode+"')");
	        return this;
	    }
		
		public Criteria andPaymentIdEqualTo(Integer paymentId) {
			addCriterion(" t.combine_deposit_order_id in (select cdo.id from bu_combine_deposit_order cdo where cdo.del_flag = '0'"
					+ " and cdo.payment_id = " + paymentId + " )");
			return this;
		}
		
		public Criteria andMemberNickEqualTo(String memberNick) {
			addCriterion(" t.combine_deposit_order_id in (select cdo.id from bu_combine_deposit_order cdo where cdo.del_flag = '0'"
					+ " and cdo.member_nick = '" + memberNick + "' )");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id = "+productTypeId+")");
			return this;
		}
		
		public Criteria andProductTypeIdIn(String productTypeIds) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = t.mcht_id and mpt.product_type_id in("+productTypeIds+") )");
			return this;
		}
		
		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion(" EXISTS(select id FROM bu_product p WHERE p.id = t.product_id and p.code = '"+productCode+"')");
	        return this;
	    }

		public Criteria andCombineDepositOrderStatusEqualTo(String status) {
			addCriterion(" EXISTS(select cdo.id from bu_combine_deposit_order cdo where cdo.id=t.combine_deposit_order_id and cdo.del_flag='0' and cdo.status = '"+status+"')");
			return this;
		}

		public Criteria andPaymentIdIn(String ids) {
			addCriterion(" EXISTS(select cdo.id from bu_combine_deposit_order cdo where cdo.id=t.combine_deposit_order_id and cdo.del_flag='0' and cdo.payment_id in "+ids+")");
			return this;
		}
		
		public Criteria andPaymentNoEqualTo(String paymentNo) {
			addCriterion(" t.combine_deposit_order_id in (select cdo.id from bu_combine_deposit_order cdo where cdo.del_flag = '0'"
					+ " and cdo.payment_no = " + paymentNo + " )");
			return this;
		}
		
		
		public Criteria andRefundDateGreaterThanOrEqualTo(String refundDate) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.del_flag = '0' and od.id = t.order_dtl_id and od.refund_date >= '" + refundDate + "' limit 1)");
			return this;
		}
		
		public Criteria andRefundDateLessThanOrEqualTo(String refundDate) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.del_flag = '0' and od.id = t.order_dtl_id and od.refund_date <= '" + refundDate + "' limit 1)");
			return this;
		}
		
		public Criteria andRefundDateBetween(String refundDateBegin, String refundDateEnd) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.del_flag = '0' and od.id = t.order_dtl_id and od.refund_date between '" + refundDateBegin + "' and '" + refundDateEnd + "' limit 1)");
			return this;
		}
		public Criteria andplatContactStaffIdtEqualTo(Integer platContactStaffId) {//对接人的商家
			addCriterion(" EXISTS (select mpc.mcht_id from bu_mcht_platform_contact mpc,bu_platform_contact bc where mpc.del_flag = '0' and mpc.status = '1' and mpc.mcht_id=t.mcht_id and mpc.platform_contact_id=bc.id and bc.staff_id = "+platContactStaffId+" )");
			return this;
		}

		public Criteria andRefundDateAndStatusGreaterThanOrEqualTo(String refundDate) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.del_flag = '0' and od.id = t.order_dtl_id and od.refund_date >= '" + refundDate + "' and od.product_status in('2','3') limit 1)");
			return this;
		}

		public Criteria andRefundDateAndStatusLessThanOrEqualTo(String refundDate) {
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.del_flag = '0' and od.id = t.order_dtl_id and od.refund_date <= '" + refundDate + "' and od.product_status in('2','3') limit 1)");
			return this;
		}
		
		public Criteria andCombineDepositOrderPayDateGreaterThanOrEqualTo(String payDate) {
			addCriterion(" EXISTS(select cdo.id from bu_combine_deposit_order cdo where cdo.del_flag = '0' and cdo.id = t.combine_deposit_order_id and cdo.pay_date >= '" + payDate + "')");
			return this;
		}
		
		public Criteria andCombineDepositOrderPayDateLessThanOrEqualTo(String payDate) {
			addCriterion(" EXISTS(select cdo.id from bu_combine_deposit_order cdo where cdo.del_flag = '0' and cdo.id = t.combine_deposit_order_id and cdo.pay_date <= '" + payDate + "')");
			return this;
		}
	}
}
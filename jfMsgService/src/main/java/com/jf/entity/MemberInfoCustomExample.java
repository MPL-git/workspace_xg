package com.jf.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MemberInfoCustomExample extends MemberInfoExample {

	@Override
	public MemberInfoCustomCriteria createCriteria() {
		MemberInfoCustomCriteria criteria = new MemberInfoCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class MemberInfoCustomCriteria extends MemberInfoExample.Criteria {

		public Criteria andlastLoginDateEquals(Date lastLoginDateBegin,Date lastLoginDateEnd) {//最后登入时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String lastLoginDateBegin1=dateFormat.format(lastLoginDateBegin);
			String lastLoginDateEnd1=dateFormat.format(lastLoginDateEnd);
			addCriterion(" mi.id in(select msi.member_id from bu_member_statistical_info msi where msi.del_flag='0' and DATE_FORMAT(msi.last_login_time, '%Y-%m-%d') >='"+lastLoginDateBegin1+"' and DATE_FORMAT(msi.last_login_time, '%Y-%m-%d') <='"+lastLoginDateEnd1+"')");
			return this;
		}
		
		public Criteria andlastExpenseDateEquals(Date lastExpenseDateBegin,Date lastExpenseDateEnd) {//最后消费时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String lastExpenseDateBegin1=dateFormat.format(lastExpenseDateBegin);
			String lastExpenseDateEnd1=dateFormat.format(lastExpenseDateEnd);
			addCriterion(" mi.id in(select msi.member_id from bu_member_statistical_info msi where msi.del_flag='0' and DATE_FORMAT(msi.last_buy_time, '%Y-%m-%d') >='"+lastExpenseDateBegin1+"' and DATE_FORMAT(msi.last_buy_time, '%Y-%m-%d') <='"+lastExpenseDateEnd1+"')");
			return this;
		}

		public Criteria andpayLogCountEquals(Integer payLogCountBegin,Integer payLogCountEnd) {//历史付款次数
			addCriterion(" mi.id in(select me.member_id from bu_member_statistical_info me where me.del_flag='0' and me.total_buy_count>="+payLogCountBegin+" and me.total_buy_count<="+payLogCountEnd+")");
			return this;
		}

		public Criteria andpayLogAmountEquals(BigDecimal payLogAmountBegin,BigDecimal payLogAmountEnd) {//历史付款金额
			addCriterion(" mi.id in(select me.member_id from bu_member_statistical_info me where me.del_flag='0' and me.total_buy_amount>="+payLogAmountBegin+" and me.total_buy_amount<="+payLogAmountEnd+")");
			return this;
		}
		
		public Criteria andloginDateEquals(Date loginDateBegin,Date loginDateEnd) {//登入时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String loginDateBegin1=dateFormat.format(loginDateBegin);
			String loginDateEnd1=dateFormat.format(loginDateEnd);
			addCriterion(" mi.id in(select sal.member_id from sys_app_login_log sal where sal.del_flag='0' and DATE_FORMAT(sal.create_date, '%Y-%m-%d') >='"+loginDateBegin1+"' and DATE_FORMAT(sal.create_date, '%Y-%m-%d') <='"+loginDateEnd1+"')");
			return this;
		}
		
		/*public Criteria andpayAmounEquals(BigDecimal payAmountBegin,BigDecimal payAmountEnd,Date payDateBegin,Date payDateEnd) {//付款金额，付款时间，付款次数
			addCriterion(" EXISTS(SELECT a.member_id, sum(a.total_pay_amount) AS totalPayAmoun, count(1) as payCount FROM bu_combine_order a "
					+ " WHERE a.status = '1' and a.pay_status='1' and a.member_id=mi.id AND DATE_FORMAT(a.pay_date, '%Y-%m-%d') >='"+payDateBegin+"' AND DATE_FORMAT(a.pay_date, '%Y-%m-%d') <='"+payDateEnd+"' GROUP BY a.member_id"
					+ " HAVING totalPayAmount >= '"+payAmountBegin+"' and totalPayAmount <= '"+payAmountBegin+"')");
			return this;
		}*/
		
		public Criteria andpayAmounEquals(Date payDateBegin,Date payDateEnd,Integer payCountBegin,Integer payCountEnd, BigDecimal payAmountBegin,BigDecimal payAmountEnd) {//付款时间，付款次数,付款金额
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String payDateBegin1=dateFormat.format(payDateBegin);
			String payDateEnd1=dateFormat.format(payDateEnd);
			
			StringBuffer sbf = new StringBuffer(" mi.id in");
			sbf.append("(");
			sbf.append("SELECT a.member_id FROM bu_combine_order a WHERE a.status = '1' and a.pay_status='1'");
			sbf.append(" and a.pay_date >='"+payDateBegin1+"' AND a.pay_date <='"+payDateEnd1+"' GROUP BY a.member_id");
			if ((payAmountBegin!=null && payAmountEnd!=null) || (payCountBegin!=null && payCountEnd!=null)) {
				sbf.append(" HAVING 1=1");
				if (payAmountBegin!=null && payAmountEnd!=null){
					sbf.append(" and sum(a.total_pay_amount) >= '"+payAmountBegin+"' and sum(a.total_pay_amount) <= '"+payAmountEnd+"'");
				}
				if (payCountBegin==null && payCountEnd==null){
					sbf.append(" and count(a.id) >= '"+payCountBegin+"' and count(a.id) <= '"+payCountEnd+"'");
				}

			}
			sbf.append(")");
			addCriterion(sbf.toString());
			return this;
		}
	    

		
		/*public Criteria andproductTypeCountBeginEquals(Integer productTypeCountBegin,Integer productTypeCountEnd,Integer productTypeId) {//类目付款次数
			addCriterion(" mi.id in(select co.member_id from bu_product p,bu_order_dtl od,bu_sub_order so,bu_combine_order co where od.product_id=p.id and od.sub_order_id=so.id and so.combine_order_id=co.id and co.status='1' and co.pay_status='1' and p.product_type1_id="+productTypeId+" and co.del_flag='0' and so.del_flag='0' and p.del_flag='0' and od.del_flag='0' GROUP BY co.member_id"
					+ " HAVING count(0) >= '"+productTypeCountBegin+"' and count(0) <= '"+productTypeCountEnd+"')");
			return this;
		}*/
		
		
		public Criteria andproductTypeCountBeginEquals(Integer productTypeCountBegin,Integer productTypeCountEnd,Integer productTypeId) {//类目付款次数
			addCriterion(" mi.id in(select t.member_id from (SELECT distinct co.member_id, co.id as combineId FROM bu_product p,bu_order_dtl od,bu_sub_order so,bu_combine_order co WHERE od.product_id=p.id AND od.sub_order_id=so.id  AND so.combine_order_id=co.id AND co.status='1'  AND co.pay_status='1' AND p.product_type1_id="+productTypeId+" AND co.del_flag='0' AND so.del_flag='0'  AND p.del_flag='0' AND od.del_flag='0')t GROUP BY t.member_id "
					+ " HAVING count(0) >='"+productTypeCountBegin+"' and count(0) <= '"+productTypeCountEnd+"')");
			return this;
		}

		public Criteria andsvipYearEquals(Integer svipYear) {//SVIP年限
			addCriterion(" mi.id in(select sv.member_id from bu_svip_order sv where sv.years_of_purchase="+svipYear+" and sv.del_flag='0' and sv.status='1' and sv.pay_status='1')");
			return this;
		}

		public Criteria andReturnGoodsRateEquals(BigDecimal returnGoodsRateMin, BigDecimal returnGoodsRateMax) {//退货率
			addCriterion("mi.id in(SELECT tab.id FROM (SELECT a.id,IFNULL(((SELECT COUNT(1) FROM bu_combine_order b, bu_sub_order c,bu_customer_service_order d WHERE b.member_id=a.id AND b.status='1' AND b.pay_status='1' AND b.del_flag='0' AND b.id=c.combine_order_id AND c.del_flag='0' AND c.id=d.sub_order_id AND d.service_type = 'B' AND d.status = '1' AND d.del_flag='0')/(SELECT COUNT(1) FROM bu_combine_order b WHERE b.member_id=a.id AND b.status='1' AND b.pay_status='1' AND b.del_flag='0')),0)*100 returnGoodsRate FROM bu_member_info a WHERE del_flag = '0') tab WHERE tab.returnGoodsRate BETWEEN "+ returnGoodsRateMin +" AND " + returnGoodsRateMax + ")");
			return this;
		}

		public Criteria andRefundRateEquals(BigDecimal returnGoodsRateMin, BigDecimal returnGoodsRateMax) {//退款率
			addCriterion("mi.id in(SELECT tab.id FROM (SELECT a.id,IFNULL(((SELECT COUNT(1) FROM bu_combine_order b, bu_sub_order c,bu_customer_service_order d WHERE b.member_id=a.id AND b.status='1' AND b.pay_status='1' AND b.del_flag='0' AND b.id=c.combine_order_id AND c.del_flag='0' AND c.id=d.sub_order_id AND d.service_type = 'A' AND d.status = '1' AND d.del_flag='0')/(SELECT COUNT(1) FROM bu_combine_order b WHERE b.member_id=a.id AND b.status='1' AND b.pay_status='1' AND b.del_flag='0')),0)*100 refundRate FROM bu_member_info a WHERE del_flag = '0') tab WHERE tab.refundRate BETWEEN "+ returnGoodsRateMin +" AND " + returnGoodsRateMax + ")");
			return this;
		}
		
	}
	
}

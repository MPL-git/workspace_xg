package com.jf.entity;

import com.jf.common.utils.StringUtils;


public class TaskSendMemberCustomExample extends TaskSendMemberExample {

	@Override
	public TaskSendMemberCustomCriteria createCriteria() {
		TaskSendMemberCustomCriteria criteria = new TaskSendMemberCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class TaskSendMemberCustomCriteria extends TaskSendMemberExample.Criteria {
		
		public Criteria andMemberSQL(String memberId, String memberNick, String memberMobile, String sendType) {
			StringBuffer sqlBuffer = new StringBuffer();
			StringBuffer sbBuffer = new StringBuffer();
			if(!StringUtils.isEmpty(memberId) ) {
				sbBuffer.append(" and a.id = " + memberId);
			}
			if(!StringUtils.isEmpty(memberNick) ) {
				sbBuffer.append(" and a.nick like concat('%', '" + memberNick + "', '%')");
			}
			if(!StringUtils.isEmpty(memberMobile) ) {
				sbBuffer.append(" and a.mobile like concat('%', '" + memberMobile + "', '%')");
			}
			if("0".equals(sendType) ) {
				sqlBuffer.append(" exists(select a.id from bu_member_info a where a.del_flag = '0' and a.id = t.member_id " + sbBuffer.toString() + " )");
			}else if("1".equals(sendType) ) {
				sqlBuffer.append(" exists(select a.id from bu_member_info a where a.del_flag = '0' and a.mobile = t.mobile " + sbBuffer.toString() + " )");
			}
			addCriterion(sqlBuffer.toString());
			return this;
		}
		
		public Criteria andLoginStatus(String loginStatus, String sendType, String firstSendDate, String newDate) {
			StringBuffer sqlBuffer = new StringBuffer();
			if("0".equals(sendType) || "2".equals(sendType) ) {
				if("1".equals(loginStatus) ) {
					sqlBuffer.append(" t.member_id in(SELECT a.member_id FROM sys_app_login_log a WHERE a.del_flag = '0' and a.create_date >= '" + firstSendDate + "' and a.create_date <= '"+ newDate +"' )");
				}else {
					sqlBuffer.append(" t.member_id not in(SELECT a.member_id FROM sys_app_login_log a WHERE a.del_flag = '0' and a.create_date >= '" + firstSendDate + "' and a.create_date <= '"+ newDate +"' )");
				}
			}else if("1".equals(sendType) ) {
				if("1".equals(loginStatus) ) {
					sqlBuffer.append(" t.mobile in(SELECT a.mobile FROM bu_member_info a, sys_app_login_log b WHERE a.id = b.member_id and b.del_flag = '0' and b.create_date >= '" + firstSendDate + "' and a.create_date <= '"+ newDate +"' )");
				}else {
					sqlBuffer.append(" t.mobile not in(SELECT a.mobile FROM bu_member_info a, sys_app_login_log b WHERE a.id = b.member_id and b.del_flag = '0' and b.create_date <= '" + firstSendDate + "' and a.create_date <= '"+ newDate +"' )");
				}
			}
			addCriterion(sqlBuffer.toString());
			return this;
		}
		
		public Criteria andOrderStatus(String orderStatus, String sendType, String firstSendDate, String newDate) {
			StringBuffer sqlBuffer = new StringBuffer();
			if("0".equals(sendType) || "2".equals(sendType) ) {
				if("1".equals(orderStatus) ) {
					sqlBuffer.append(" exists(select a.id from bu_combine_order a where a.member_id = t.member_id and a.status = '1' and a.del_flag = '0' and a.pay_date BETWEEN '" + firstSendDate + "' and '"+ newDate +"' )");
				}else {
					sqlBuffer.append(" not exists(select a.id from bu_combine_order a where a.member_id = t.member_id and a.status = '1' and a.del_flag = '0' and a.pay_date BETWEEN '" + firstSendDate + "' and '"+ newDate +"' )");
				}
			}else if("1".equals(sendType) ) {
				if("1".equals(orderStatus) ) {
					sqlBuffer.append(" exists(select a.id from bu_member_info a, bu_combine_order b where a.id = b.member_id and a.mobile = t.mobile and b.status = '1' and b.del_flag = '0' and b.pay_date BETWEEN '" + firstSendDate + "' and '"+ newDate +"' )");
				}else {
					sqlBuffer.append(" not exists(select a.id from bu_member_info a, bu_combine_order b where a.id = b.member_id and a.mobile = t.mobile and b.status = '1' and b.del_flag = '0' and b.pay_date BETWEEN '" + firstSendDate + "' and '"+ newDate +"' )");
				}
			}
			addCriterion(sqlBuffer.toString());
			return this;
		}
		


	}
	
}

package com.jf.entity;

import org.apache.commons.lang.StringUtils;



public class WithdrawOrderCustomExample extends WithdrawOrderExample {

	@Override
    public WithdrawOrderCustomCriteria createCriteria() {
		WithdrawOrderCustomCriteria criteria = new WithdrawOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class WithdrawOrderCustomCriteria extends WithdrawOrderExample.Criteria{
		
		/**
		 * 
		 * @Title andWithdrawOrderFlag   
		 * @Description TODO(是否有提现)   
		 * @author pengl
		 * @date 2018年8月8日 下午3:08:57
		 */
		public Criteria andWithdrawOrderFlag(String withdrawOrderFlag) {
			if("1".equals(withdrawOrderFlag)) {
				addCriterion(" EXISTS (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.status = '4' and wo.member_id = t.member_id )");
			}else {
				addCriterion(" NOT EXISTS (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.status = '4' and wo.member_id = t.member_id )");
			}
			return this;
		}
		
		/**
		 * 
		 * @Title andExpenseFlag   
		 * @Description TODO(是否消费)   
		 * @author pengl
		 * @date 2018年8月8日 下午3:06:26
		 */
		public Criteria andExpenseFlag(String expenseFlag) {
			if("1".equals(expenseFlag)) {
				addCriterion(" EXISTS (select co.id from bu_combine_order co where co.del_flag = '0' and co.pay_status = '1' and co.member_id = t.member_id )");
			}else {
				addCriterion(" NOT EXISTS (select co.id from bu_combine_order co where co.del_flag = '0' and co.pay_status = '1' and co.member_id = t.member_id )");
			}
	        return this;
	    }
		
		/**
		 * 
		 * @Title andWithdrawOrderFlag   
		 * @Description TODO(是否有提现)   
		 * @author pengl
		 * @date 2018年8月8日 下午3:08:57
		 */
		public Criteria andNickLikeEqualTo(String withdrawOrderFlag) {
			if("1".equals(withdrawOrderFlag)) {
				addCriterion(" EXISTS (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.status = '4' and wo.member_id = t.member_id )");
			}else {
				addCriterion(" NOT EXISTS (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.status = '4' and wo.member_id = t.member_id )");
			}
			return this;
		}

		//会员昵称
		public void andNickLikeEqualToNewStart(String nick) {
			// TODO Auto-generated method stub
			addCriterion(" member_id in(select b.id from bu_member_info b where b.nick like '"+nick+"')");
		}

		//审核状态
		public void andStatusEqualToNewStart(String status) {
			// TODO Auto-generated method stub
			//申请中(主状态为1)
			if(StringUtils.equals(status, "1")){
				addCriterion(" status = '1' and yy_audit_status is null");
			}
			//待财审(营运审核为通过，财务审核为空的)
			if(StringUtils.equals(status, "2")){
				addCriterion(" status = '1' and yy_audit_status='1' and cw_audit_status is null");
			}
			//转账中(主状态为3) 提现成功(主状态为4) 提现失败(7)
			if(StringUtils.equals(status, "3") || StringUtils.equals(status, "4") ){
				addCriterion(" status = '"+status+"'");
			}
			if(StringUtils.equals(status, "7")){
				addCriterion(" status = '6'");
			}
			//审核不通过(5)
			if(StringUtils.equals(status, "5") ){
				addCriterion(" status ='5' and yy_audit_status = '0' and cw_audit_status is null");
			}
			//财审不通过(营运审核为通过，财务审核为驳回)
			if(StringUtils.equals(status, "6")){
				addCriterion(" status = '5' and yy_audit_status='1' and cw_audit_status= '0'");
			}
			//财审通过(2)
			if(StringUtils.equals(status, "8")){
				addCriterion(" status = '2'");
			}
		}
	}
	
}

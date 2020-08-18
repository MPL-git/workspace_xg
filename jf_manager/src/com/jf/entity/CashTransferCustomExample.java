package com.jf.entity;

import com.gzs.common.utils.StringUtil;



public class CashTransferCustomExample extends CashTransferExample {

	@Override
    public CashTransferCustomCriteria createCriteria() {
		CashTransferCustomCriteria criteria = new CashTransferCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CashTransferCustomCriteria extends CashTransferExample.Criteria{
	
		/**
		 * 
		 * @Title andMemberNickLike   
		 * @Description TODO(会员昵称)   
		 * @author pengl
		 * @date 2018年6月11日 下午5:09:09
		 */
		public Criteria andMemberNickLike(String memberNick) {
			addCriterion(" t.member_id in(select mi.id from bu_member_info mi where mi.del_flag = '0' and mi.nick like '"+ memberNick +"' )");
	        return this;
	    }

		public Criteria andMemberIdEqualTo(int MemberId) {
			addCriterion(" t.withdraw_order_id in (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.member_id = "+ MemberId +" )");
	        return this;
		}
		
		public Criteria andAlipayAccountEqualTo(String alipayAccount) {
			addCriterion(" t.withdraw_order_id in (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.alipay_account = '"+ alipayAccount +"' )");
			return this;
		}
		
		public Criteria andWithdrawOrderCreateDate(String createDateBegin, String createDateEnd) {
			if(!StringUtil.isEmpty(createDateBegin) && !StringUtil.isEmpty(createDateEnd) ) {
				addCriterion(" t.withdraw_order_id in (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.create_date between '"+ createDateBegin +"' and '"+ createDateEnd +"' )");
			}else if(!StringUtil.isEmpty(createDateBegin)) {
				addCriterion(" t.withdraw_order_id in (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.create_date >= '"+ createDateBegin +"' )");
			}else if(!StringUtil.isEmpty(createDateEnd)) {
				addCriterion(" t.withdraw_order_id in (select wo.id from bu_withdraw_order wo where wo.del_flag = '0' and wo.create_date <= '"+ createDateEnd +"' )");
			}
			return this;
		}
		
	}
	
}

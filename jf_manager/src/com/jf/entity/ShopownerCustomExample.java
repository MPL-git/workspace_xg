package com.jf.entity;

import java.util.Date;

public class ShopownerCustomExample extends ShopownerExample{
	
	@Override
    public ShopownerCustomCriteria createCriteria() {
		ShopownerCustomCriteria criteria = new ShopownerCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ShopownerCustomCriteria extends ShopownerExample.Criteria{

		/**
		 * @MethodName andNickLikeEqual
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月12日 下午5:51:52
		 */
		public void andNickLikeEqual(String nick) {
			// TODO Auto-generated method stub
			addCriterion(" a.member_id in(select b.id from bu_member_info b where b.nick like '"+nick+"')");
		}

		/**
		 * @MethodName andDealCompleteDateGreaterThanOrEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月12日 下午5:54:46
		 */
		public void andDealCompleteDateGreaterThanOrEqualTo(String dealCompleteDateBegin) {
			// TODO Auto-generated method stub
			addCriterion(" (select pay_date from bu_shopowner_order b where b.member_id = a.member_id order by create_date desc LIMIT 1) >='"+dealCompleteDateBegin+"'");
		}

		/**
		 * @MethodName andDealCompleteDateLessThanOrEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月12日 下午5:54:51
		 */
		public void andDealCompleteDateLessThanOrEqualTo(String dealCompleteDateEnd) {
			// TODO Auto-generated method stub
			addCriterion(" (select pay_date from bu_shopowner_order b where b.member_id = a.member_id order by create_date desc LIMIT 1) <='"+dealCompleteDateEnd+"'");
		}	
	}
}
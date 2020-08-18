package com.jf.entity;

import com.jf.entity.CutPriceOrderDtlExample.Criteria;


public class BlackListCustomExample extends BlackListExample {

	@Override
    public BlackListCustomCriteria createCriteria() {
		BlackListCustomCriteria criteria = new BlackListCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class BlackListCustomCriteria extends BlackListExample.Criteria{
	
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
		
		/**
		 * 
		 * @Title andMemberMobileLike   
		 * @Description TODO(会员手机号)   
		 * @author pengl
		 * @date 2018年6月11日 下午5:09:13
		 */
		public Criteria andMemberMobileLike(String memberMobile) {
			addCriterion(" t.member_id in(select mi.id from bu_member_info mi where mi.del_flag = '0' and mi.mobile like '"+ memberMobile +"' )");
			return this;
		}
		
		
	}
	
}

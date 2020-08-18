package com.jf.entity;



public class CutPriceOrderDtlCustomExample extends CutPriceOrderDtlExample {

	@Override
    public CutPriceOrderDtlCriteria createCriteria() {
		CutPriceOrderDtlCriteria criteria = new CutPriceOrderDtlCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CutPriceOrderDtlCriteria extends CutPriceOrderDtlExample.Criteria{
		
		/**
		 * 
		 * @Title andMemberNickLike   
		 * @Description TODO(会员昵称)   
		 * @author pengl
		 * @date 2018年6月8日 下午2:26:06
		 */
		public Criteria andMemberNickLike(String memberNick) {
			addCriterion(" t.member_id in(select mi.id from bu_member_info mi where mi.del_flag = '0' and mi.nick like '"+ memberNick +"' )");
	        return this;
	    }
		
		
	}
	
}

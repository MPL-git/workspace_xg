package com.jf.entity;

import com.jf.common.constant.Const;




public class ActivityAreaCustomExample extends ActivityAreaExample{
	
	@Override
    public ActivityAreaCustomCriteria createCriteria() {
		ActivityAreaCustomCriteria criteria = new ActivityAreaCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityAreaCustomCriteria extends ActivityAreaExample.Criteria{
		public Criteria andPlatActivity(Integer mchtId, String mchtType) {
			
			StringBuilder sql = new StringBuilder();
			sql.append("(");
			sql.append("push_mcht_type = '1'");
			sql.append(" or FIND_IN_SET('" + mchtId + "', mcht_id_group)");
			if(mchtType.equals(Const.MCHT_TYPE_UNION)){
				sql.append(" or push_mcht_type = '3'");
			}else{
				sql.append(" or push_mcht_type = '2'");
			}
			sql.append(")");
			addCriterion(sql.toString());
			return this;
		}
		
	}
}
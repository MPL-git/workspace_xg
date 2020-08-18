package com.jf.entity;

public class SalesmanCustomExample extends SalesmanExample{
	
	@Override
    public SalesmanCustomCriteria createCriteria() {
		SalesmanCustomCriteria criteria = new SalesmanCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SalesmanCustomCriteria extends SalesmanExample.Criteria{

		/**
		 * @MethodName andNickLikeEqual
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月12日 下午3:19:40
		 */
		public void andNickLikeEqual(String nick) {
			// TODO Auto-generated method stub
			addCriterion(" a.member_id in(select b.id from bu_member_info b where b.nick like '"+nick+"')");
		}
		
	}
}
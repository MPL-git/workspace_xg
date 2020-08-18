package com.jf.entity;

public class PlatformMsgCustomExample extends PlatformMsgExample{
	
	@Override
    public PlatformMsgCustomCriteria createCriteria() {
		PlatformMsgCustomCriteria criteria = new PlatformMsgCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class PlatformMsgCustomCriteria extends PlatformMsgExample.Criteria{
		/**
		 * 后台页面商家编号、商家简称、商家公司名称搜索
		 * @param value
		 * @return
		 */
		public Criteria andMchtNameOrMchtCodeLike(String value) {
			addCriterion("mcht_id in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.short_name like '"+value+"' or mi.mcht_code like '"+value+"' or mi.company_name like '"+value+"' ))");
			return this;
		}
	}
}
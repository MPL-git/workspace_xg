package com.jf.entity;


public class ImpeachMemberCustomExample extends ImpeachMemberExample {

	@Override
    public ImpeachMemberCustomCriteria createCriteria() {
		ImpeachMemberCustomCriteria criteria = new ImpeachMemberCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	//添加通过订单号查子订单id
	public class ImpeachMemberCustomCriteria extends ImpeachMemberExample.Criteria{
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
//			addCriterion("EXISTS (select id FROM bu_impeach_member bi WHERE FIND_IN_SET((select bs.id from bu_sub_order bs where sub_order_code= '"+subOrderCode+"'),bi.sub_order_ids)");
			addCriterion("find_in_set((select so.id from bu_sub_order so where so.del_flag='0' and so.sub_order_code='"+subOrderCode+"'),bi.sub_order_ids)");
			return this;
		}
		
	}
}

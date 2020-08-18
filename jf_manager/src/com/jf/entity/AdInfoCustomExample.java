package com.jf.entity;

public class AdInfoCustomExample extends AdInfoExample{
	
	@Override
    public AdInfoCustomCriteria createCriteria() {
		AdInfoCustomCriteria criteria = new AdInfoCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class AdInfoCustomCriteria extends AdInfoExample.Criteria{

		public Criteria andActivityNameLikeTo(String activityName) {
			addCriterion(" case a.link_type when '1' then EXISTS(select b.id from bu_activity_area b where b.id=a.link_id and b.name like '"+activityName+"') when '2' then EXISTS(select c.id from bu_activity c where c.id=a.link_id and c.name like '"+activityName+"') else 1=2 end");
			return this;
		}
		
		public Criteria andActivityEndTime() {
			addCriterion(" (CASE a.link_type" 
			      			+ " WHEN '1' THEN (SELECT baa.activity_end_time FROM bu_activity_area baa WHERE baa.id = a.link_id AND baa.del_flag = '0' )"
			      			+ " WHEN '2' THEN (SELECT baa.activity_end_time FROM bu_activity ba, bu_activity_area baa WHERE ba.id = a.link_id AND baa.id = ba.activity_area_id AND ba.del_flag = '0' AND baa.del_flag = '0' )"
					    	+ " WHEN '3' THEN (SELECT baa.activity_end_time FROM bu_activity_product bap LEFT JOIN bu_activity ba ON bap.activity_id = ba.id LEFT JOIN bu_activity_area baa ON baa.id = ba.activity_area_id WHERE bap.product_id = a.link_id AND bap.del_flag = '0' AND ba.del_flag = '0' AND baa.del_flag = '0' ORDER BY bap.id DESC LIMIT 1 )"	
					    	+ " ELSE NOW() END"
					    + " ) > NOW()");
			return this;
		}
		
		public Criteria andActivityStatus(Integer productTypeId) {
			addCriterion(" EXISTS(select ba.id from bu_activity ba where ba.id = a.link_id and ba.del_flag = '0' and ba.status = '6' and ba.product_type_id = "+productTypeId+" )");
			return this;
		}
		
		public Criteria andActivityTime(String activityTime) {
			addCriterion(" EXISTS(select ba.id from bu_activity ba, bu_activity_area baa where ba.del_flag = '0' and baa.del_flag = '0' and ba.coo_audit_status = '2' and ba.activity_area_id = baa.id and ba.id = a.link_id and DATE_FORMAT(baa.activity_begin_time, '%Y-%m-%d') < '" + activityTime + "' and DATE_FORMAT(baa.activity_end_time, '%Y-%m-%d') > '" + activityTime + "' )" );
			return this;
		}

		public Criteria andShowChannelFindInSet(String showChannel) {
			addCriterion(" FIND_IN_SET("+showChannel+",a.show_channel)" );
			return this;
		}
		
	}
	
}
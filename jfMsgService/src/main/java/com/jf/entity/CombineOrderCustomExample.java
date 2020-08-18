package com.jf.entity;

public class CombineOrderCustomExample extends CombineOrderExample{

	@Override
	public CombineOrderCustomCriteria createCriteria() {
		CombineOrderCustomCriteria criteria = new CombineOrderCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class CombineOrderCustomCriteria extends Criteria{

		public Criteria andExistsTrackData() {
//			addCriterion(" exists(select b.id from bu_member_info b where b.id = member_id and exists(select c.id from bu_track_data c where c.idfa = b.req_imei ))");
			addCriterion(" exists(select b.id from bu_member_info b where b.id = member_id and exists(SELECT c.id FROM bu_track_data c WHERE c.idfa = b.req_imei UNION SELECT d.id FROM bu_track_data d WHERE d.imei = b.req_imei))");
			return this;
		}
	}
}

package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-12-04 下午 5:06
 */
public class SpreadActivityGroupDiscountRateCustomExample extends SpreadActivityGroupDiscountRateExample {

	@Override
	public SpreadActivityGroupDiscountRateCustomCriteria createCriteria() {
		SpreadActivityGroupDiscountRateCustomCriteria criteria = new SpreadActivityGroupDiscountRateCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class SpreadActivityGroupDiscountRateCustomCriteria extends SpreadActivityGroupDiscountRateExample.Criteria{

		public SpreadActivityGroupDiscountRateCustomCriteria andDeviceTypeEqualTo(String deviceType){
			addCriterion(" spread_activity_group_id in(select sag.id from bu_spread_activity_group sag where sag.del_flag = '0' and sag.device_type = '" + deviceType + "')");
			return this;
		}

	}


}

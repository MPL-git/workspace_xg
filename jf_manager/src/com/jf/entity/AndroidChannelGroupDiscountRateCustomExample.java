package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-12-04 下午 4:59
 */
public class AndroidChannelGroupDiscountRateCustomExample extends AndroidChannelGroupDiscountRateExample {

	@Override
	public AndroidChannelGroupDiscountRateCustomExample.AndroidChannelGroupDiscountRateCustomCriteria createCriteria() {
		AndroidChannelGroupDiscountRateCustomExample.AndroidChannelGroupDiscountRateCustomCriteria criteria = new AndroidChannelGroupDiscountRateCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class AndroidChannelGroupDiscountRateCustomCriteria extends AndroidChannelGroupDiscountRateExample.Criteria{

	}

}

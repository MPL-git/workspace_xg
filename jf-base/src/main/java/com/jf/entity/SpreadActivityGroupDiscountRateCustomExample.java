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

	public class SpreadActivityGroupDiscountRateCustomCriteria extends Criteria{

	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class IntegralDailyStatisticsExt extends IntegralDailyStatistics {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

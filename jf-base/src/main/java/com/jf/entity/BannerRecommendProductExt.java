package com.jf.entity;

import com.jf.common.constant.Constant;

public class BannerRecommendProductExt extends BannerRecommendProduct {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

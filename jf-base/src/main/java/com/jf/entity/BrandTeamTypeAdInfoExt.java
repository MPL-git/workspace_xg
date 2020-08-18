package com.jf.entity;

import com.jf.common.constant.Constant;

public class BrandTeamTypeAdInfoExt extends BrandTeamTypeAdInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

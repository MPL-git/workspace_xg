package com.jf.entity;

import com.jf.common.constant.Constant;

public class BrandTeamTypeExt extends BrandTeamType {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

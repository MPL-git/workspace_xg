package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBrandAptitudeChgExt extends MchtBrandAptitudeChg {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

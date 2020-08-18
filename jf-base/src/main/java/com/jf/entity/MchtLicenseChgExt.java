package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtLicenseChgExt extends MchtLicenseChg {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

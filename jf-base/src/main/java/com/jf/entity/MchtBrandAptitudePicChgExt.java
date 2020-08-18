package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBrandAptitudePicChgExt extends MchtBrandAptitudePicChg {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBrandAptitudePicExt extends MchtBrandAptitudePic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

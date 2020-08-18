package com.jf.entity;

import com.jf.common.constant.Constant;

public class AdInfoExt extends AdInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

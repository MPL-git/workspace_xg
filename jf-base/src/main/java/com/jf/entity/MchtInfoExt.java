package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtInfoExt extends MchtInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

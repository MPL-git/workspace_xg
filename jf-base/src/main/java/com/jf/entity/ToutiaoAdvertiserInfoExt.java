package com.jf.entity;

import com.jf.common.constant.Constant;

public class ToutiaoAdvertiserInfoExt extends ToutiaoAdvertiserInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

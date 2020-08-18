package com.jf.entity;

import com.jf.common.constant.Constant;

public class ToutiaoAdInfoExt extends ToutiaoAdInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

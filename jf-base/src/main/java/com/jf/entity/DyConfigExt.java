package com.jf.entity;

import com.jf.common.constant.Constant;

public class DyConfigExt extends DyConfig {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

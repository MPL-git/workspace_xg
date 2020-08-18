package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityBrandGroupActivityExt extends ActivityBrandGroupActivity {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

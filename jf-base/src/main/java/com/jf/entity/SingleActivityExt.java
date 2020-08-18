package com.jf.entity;

import com.jf.common.constant.Constant;

public class SingleActivityExt extends SingleActivity {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

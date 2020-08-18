package com.jf.entity;

import com.jf.common.constant.Constant;

public class AppealPlatformRemarksPicExt extends AppealPlatformRemarksPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

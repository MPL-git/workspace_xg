package com.jf.entity;

import com.jf.common.constant.Constant;

public class AppealLogPicExt extends AppealLogPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

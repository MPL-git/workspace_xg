package com.jf.entity;

import com.jf.common.constant.Constant;

public class ComplainOrderPicExt extends ComplainOrderPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBrandOtherPicExt extends MchtBrandOtherPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

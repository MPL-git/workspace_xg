package com.jf.entity;

import com.jf.common.constant.Constant;

public class BrandTmkPicExt extends BrandTmkPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

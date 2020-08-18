package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductPicExt extends ProductPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

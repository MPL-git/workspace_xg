package com.jf.entity;

import com.jf.common.constant.Constant;

public class BrandTmkPicTmpExt extends BrandTmkPicTmp {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

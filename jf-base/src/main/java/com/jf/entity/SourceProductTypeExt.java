package com.jf.entity;

import com.jf.common.constant.Constant;

public class SourceProductTypeExt extends SourceProductType {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

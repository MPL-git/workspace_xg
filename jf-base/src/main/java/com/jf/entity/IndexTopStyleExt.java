package com.jf.entity;

import com.jf.common.constant.Constant;

public class IndexTopStyleExt extends IndexTopStyle {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

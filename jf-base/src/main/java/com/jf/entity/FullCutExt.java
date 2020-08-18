package com.jf.entity;

import com.jf.common.constant.Constant;

public class FullCutExt extends FullCut {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

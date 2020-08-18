package com.jf.entity;

import com.jf.common.constant.Constant;

public class SourceNicheProductExt extends SourceNicheProduct {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

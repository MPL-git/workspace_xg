package com.jf.entity;

import com.jf.common.constant.Constant;

public class SourceNicheExt extends SourceNiche {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class CutLinkClickLogExt extends CutLinkClickLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

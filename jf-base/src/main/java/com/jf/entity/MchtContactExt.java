package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtContactExt extends MchtContact {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

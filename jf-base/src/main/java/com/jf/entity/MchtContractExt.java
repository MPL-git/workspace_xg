package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtContractExt extends MchtContract {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

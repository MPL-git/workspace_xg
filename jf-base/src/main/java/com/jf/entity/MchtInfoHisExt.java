package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtInfoHisExt extends MchtInfoHis {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

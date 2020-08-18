package com.jf.entity;

import com.jf.common.constant.Constant;

public class PvWeightCnfExt extends PvWeightCnf {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

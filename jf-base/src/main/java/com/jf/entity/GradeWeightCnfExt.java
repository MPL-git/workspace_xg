package com.jf.entity;

import com.jf.common.constant.Constant;

public class GradeWeightCnfExt extends GradeWeightCnf {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

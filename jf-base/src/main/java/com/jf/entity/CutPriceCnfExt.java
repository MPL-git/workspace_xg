package com.jf.entity;

import com.jf.common.constant.Constant;

public class CutPriceCnfExt extends CutPriceCnf {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

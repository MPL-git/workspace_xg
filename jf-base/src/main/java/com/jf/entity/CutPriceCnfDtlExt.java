package com.jf.entity;

import com.jf.common.constant.Constant;

public class CutPriceCnfDtlExt extends CutPriceCnfDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

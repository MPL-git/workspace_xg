package com.jf.entity;

import com.jf.common.constant.Constant;

public class CutPriceOrderDtlExt extends CutPriceOrderDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

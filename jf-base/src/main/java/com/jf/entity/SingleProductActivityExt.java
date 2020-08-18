package com.jf.entity;

import com.jf.common.constant.Constant;

public class SingleProductActivityExt extends SingleProductActivity {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class ModuleItemExt extends ModuleItem {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

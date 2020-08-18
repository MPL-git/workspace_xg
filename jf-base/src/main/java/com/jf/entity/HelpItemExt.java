package com.jf.entity;

import com.jf.common.constant.Constant;

public class HelpItemExt extends HelpItem {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

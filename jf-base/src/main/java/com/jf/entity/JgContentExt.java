package com.jf.entity;

import com.jf.common.constant.Constant;

public class JgContentExt extends JgContent {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

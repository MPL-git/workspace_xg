package com.jf.entity;

import com.jf.common.constant.Constant;

public class MsgTplExt extends MsgTpl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

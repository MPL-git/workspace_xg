package com.jf.entity;

import com.jf.common.constant.Constant;

public class DouyinSprChnlExt extends DouyinSprChnl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

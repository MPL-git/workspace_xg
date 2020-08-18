package com.jf.entity;

import com.jf.common.constant.Constant;

public class WeixinXcxSprChnlExt extends WeixinXcxSprChnl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

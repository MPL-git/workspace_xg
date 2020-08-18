package com.jf.entity;

import com.jf.common.constant.Constant;

public class WeixinOauthRedirectUrlExt extends WeixinOauthRedirectUrl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

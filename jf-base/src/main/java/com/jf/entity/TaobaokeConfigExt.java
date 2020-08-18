package com.jf.entity;

import com.jf.common.constant.Constant;

public class TaobaokeConfigExt extends TaobaokeConfig {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

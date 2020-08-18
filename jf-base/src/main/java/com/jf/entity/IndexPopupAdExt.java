package com.jf.entity;

import com.jf.common.constant.Constant;

public class IndexPopupAdExt extends IndexPopupAd {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

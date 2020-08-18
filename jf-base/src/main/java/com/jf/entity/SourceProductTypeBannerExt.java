package com.jf.entity;

import com.jf.common.constant.Constant;

public class SourceProductTypeBannerExt extends SourceProductTypeBanner {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

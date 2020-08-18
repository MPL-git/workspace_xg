package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBrandChangeAgreementPicExt extends MchtBrandChangeAgreementPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

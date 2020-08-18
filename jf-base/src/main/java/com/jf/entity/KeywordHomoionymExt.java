package com.jf.entity;

import com.jf.common.constant.Constant;

public class KeywordHomoionymExt extends KeywordHomoionym {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBlPicHisExt extends MchtBlPicHis {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class TrackDataExt extends TrackData {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

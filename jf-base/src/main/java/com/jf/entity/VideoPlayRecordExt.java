package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoPlayRecordExt extends VideoPlayRecord {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

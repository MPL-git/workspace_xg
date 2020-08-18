package com.jf.entity;

import com.jf.common.constant.Constant;

public class CommentDrawSettingExt extends CommentDrawSetting {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

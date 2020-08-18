package com.jf.entity;

import com.jf.common.constant.Constant;

public class CommentPicExt extends CommentPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

package com.jf.entity;

import com.jf.common.constant.Constant;

public class AttachmentExt extends Attachment {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

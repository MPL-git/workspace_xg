package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityExt extends Activity {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

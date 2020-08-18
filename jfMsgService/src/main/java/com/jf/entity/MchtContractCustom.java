package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.util.Date;

@SuppressWarnings("serial")
public class MchtContractCustom extends MchtContract {
   private String mchtCode;

public String getMchtCode() {
	return mchtCode;
}

public void setMchtCode(String mchtCode) {
	this.mchtCode = mchtCode;
}
   
}
package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtTaxInvoiceInfoChgExt extends MchtTaxInvoiceInfoChg {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}

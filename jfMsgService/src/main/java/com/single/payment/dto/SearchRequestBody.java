package com.single.payment.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 报文实体
 * @author zan.liang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "searchRequestBody", propOrder = {"sealDataType"})  
public class SearchRequestBody {

	
	@XmlElement(name = "sealDataType")  
	private SealDataType sealDataType;

	public SealDataType getSealDataType() {
		return sealDataType;
	}

	public void setSealDataType(SealDataType sealDataType) {
		this.sealDataType = sealDataType;
	}


	
}

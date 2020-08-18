package com.single.payment.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 报文实体
 * @author zhiwei.ma
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "pay2bankSearchResponse", propOrder = {"pay2bankSearchHead","searchResponseBody"})  
public class Pay2bankSearchResponse {

	@XmlElement(name = "pay2bankSearchHead")  
	private Pay2bankSearchHead pay2bankSearchHead;
	
	@XmlElement(name = "searchResponseBody")  
	private SearchResponseBody searchResponseBody;

	public Pay2bankSearchHead getPay2bankSearchHead() {
		return pay2bankSearchHead;
	}

	public void setPay2bankSearchHead(Pay2bankSearchHead pay2bankSearchHead) {
		this.pay2bankSearchHead = pay2bankSearchHead;
	}

	public SearchResponseBody getSearchResponseBody() {
		return searchResponseBody;
	}

	public void setSearchResponseBody(SearchResponseBody searchResponseBody) {
		this.searchResponseBody = searchResponseBody;
	}




	
	
}

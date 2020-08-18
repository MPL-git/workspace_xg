package com.jf.entity;

public class KdnWuliuInfoCustom extends KdnWuliuInfo{
	/**
	 * 快递公司
	 */
    private String expressCompany;
    /**
	 * 快递鸟快递编号
	 */
    private String kdnCode;

	/**  
	 * 快递公司  
	 * @return expressCompany 快递公司  
	 */
	public String getExpressCompany() {
		return expressCompany;
	}
	

	/**  
	 * 快递公司  
	 * @param expressCompany 快递公司  
	 */
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}


	/**  
	 * 快递鸟快递编号  
	 * @return kdnCode 快递鸟快递编号  
	 */
	public String getKdnCode() {
		return kdnCode;
	}
	


	/**  
	 * 快递鸟快递编号  
	 * @param kdnCode 快递鸟快递编号  
	 */
	public void setKdnCode(String kdnCode) {
		this.kdnCode = kdnCode;
	}
	
	

}
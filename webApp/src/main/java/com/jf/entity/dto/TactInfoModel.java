package com.jf.entity.dto;
/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月7日 下午5:28:02 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class TactInfoModel {
	/**
	 * 物流信息
	 */
	private String AcceptStation;
	/**
	 * 物流时间
	 */
	private String AcceptTime;
	/**  
	 * acceptStation  
	 * @return acceptStation acceptStation  
	 */
	public String getAcceptStation() {
		return AcceptStation;
	}
	
	/**  
	 * acceptStation  
	 * @param acceptStation acceptStation  
	 */
	public void setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
	}
	
	/**  
	 * acceptTime  
	 * @return acceptTime acceptTime  
	 */
	public String getAcceptTime() {
		return AcceptTime;
	}
	
	/**  
	 * acceptTime  
	 * @param acceptTime acceptTime  
	 */
	public void setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
	}
	
	
	
}

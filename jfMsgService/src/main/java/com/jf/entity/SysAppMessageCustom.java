package com.jf.entity;

public class SysAppMessageCustom extends SysAppMessage{
	
	private Integer appMessageMemberId;

    private Integer appMessageId;

    private Integer memberId;

	/**  
	 * appMessageMemberId  
	 * @return appMessageMemberId appMessageMemberId  
	 */
	public Integer getAppMessageMemberId() {
		return appMessageMemberId;
	}
	

	/**  
	 * appMessageMemberId  
	 * @param appMessageMemberId appMessageMemberId  
	 */
	public void setAppMessageMemberId(Integer appMessageMemberId) {
		this.appMessageMemberId = appMessageMemberId;
	}
	

	/**  
	 * appMessageId  
	 * @return appMessageId appMessageId  
	 */
	public Integer getAppMessageId() {
		return appMessageId;
	}
	

	/**  
	 * appMessageId  
	 * @param appMessageId appMessageId  
	 */
	public void setAppMessageId(Integer appMessageId) {
		this.appMessageId = appMessageId;
	}
	

	/**  
	 * memberId  
	 * @return memberId memberId  
	 */
	public Integer getMemberId() {
		return memberId;
	}
	

	/**  
	 * memberId  
	 * @param memberId memberId  
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
}
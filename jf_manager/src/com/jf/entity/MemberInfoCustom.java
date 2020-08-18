package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MemberInfoCustom extends MemberInfo{
	private String groupName;
	private String statusDesc;
	private String sexDesc;
	private String sprChnlDesc;
	private String regClientDesc;
	private String provinceName;
	private String cityName;
	private String countyName;
	private Integer integral;
	private Integer gValue;
	private Date lastLoginTime;
	private Integer loginCount;
	private Integer loginDays;
	private Date firstPayTime;
	private Date lastPayTime;
	private Integer payOrderCount;
	private BigDecimal payOrderAmount;
	private Integer payProductCount;
	private Integer returnOrderCount;//退货订单数
	private Integer refundOrderCount;//退款订单数
	private Integer payDays;
	private String spreadname;
	private String channel;
	private String activityname;
	private String blackPictures; // 拉黑图片
	private String logOperateType; // 拉黑日志操作类型
	private Date buySvipPayDate; // 首次购买SVIP时间
	private Date lastAppLoginDate; // 最后登录时间
	private String memberStatuslog;

	private Integer invitationCount;//拉新用户
	private BigDecimal fenrunTotal;//获得分润
	private Integer withdrawAcount;//提现次数
	private BigDecimal cashBalance;//现金余额（含提现中）
	private BigDecimal withdrawalAmount;//提现中金额
	private BigDecimal withdrawalsAmount;//已提现金额
	private Date firstJoinDate;//首次加入新星计划时间
	private Date endLachineDate;//最后拉新时间
	private String currentStatus;//当前新星计划状态

	private Date msLastLoginTime; // 最后登录时间
	private Date msFirstBuyTime; // 首次时间
	private Date msLastBuyTime; // 最后消费时间
	private Integer totalBuyCount;//消费笔数
	private BigDecimal totalBuyAmount;//消费总金额
	private Integer partBuyCount;//期间消费笔数
	private BigDecimal partBuyAmount;//期间消费金额
	
	private Integer customerServiceOrderCount;
	private Integer interventionOrderCount;
	private Integer appealOrderCount;
	private Integer subOrderCustomscuont;
	
	private MemberExtend memberExtend;
	
	private String salesmanTag;//业务员标识
	private String shopownerTag;//店长标识
	private Date lastDate;//最后登录时间
	
	private Integer zsPlatformContactId;//招商对接人id
	private String mchtSettledApplyId;//入驻申请id(group_concat)
	  private String mchtCompany; //公司名字(group_concat)
	  private String  mchtCompanyNotNull;//入驻完成的公司名称
	  private String zsName; //招商对接人名字
	  private String mchtCode;//商家code(group_concat)
	  
	    private String eachDay;
		private Integer memberEverydayAdded;
		private Integer memberEverydayNewlyaddedConsumption;
		private Integer memberEverydayActive;
		private Integer memberEverydayConsumption;
		private Integer memberEverydaySvipcount;
		
		private String  monthDay;
		private Integer memberMonthlydayAdded;
		private Integer memberMonthlydayNewlyaddedConsumption;
		private Integer memberMonthlydayActive;
		private Integer memberMonthlydayConsumption;
		private Integer memberMonthlydaySvipcount;
		
		private String  weeklyDay;
		private Integer memberWeeklyAdded;
		private Integer memberWeeklyNewlyaddedConsumption;
		private Integer memberWeeklyActive;
		private Integer memberWeeklyConsumption;
		private Integer memberWeeklySvipcount;
		private String columnTypeDesc;

	public Integer getReturnOrderCount() {
		return returnOrderCount;
	}

	public void setReturnOrderCount(Integer returnOrderCount) {
		this.returnOrderCount = returnOrderCount;
	}

	public Integer getRefundOrderCount() {
		return refundOrderCount;
	}

	public void setRefundOrderCount(Integer refundOrderCount) {
		this.refundOrderCount = refundOrderCount;
	}

	public String getColumnTypeDesc() {
		return columnTypeDesc;
	}

	public void setColumnTypeDesc(String columnTypeDesc) {
		this.columnTypeDesc = columnTypeDesc;
	}

	public Integer getPartBuyCount() {
		return partBuyCount;
	}

	public void setPartBuyCount(Integer partBuyCount) {
		this.partBuyCount = partBuyCount;
	}

	public BigDecimal getPartBuyAmount() {
		return partBuyAmount;
	}

	public void setPartBuyAmount(BigDecimal partBuyAmount) {
		this.partBuyAmount = partBuyAmount;
	}

	public String getSalesmanTag() {
		return salesmanTag;
	}

	public void setSalesmanTag(String salesmanTag) {
		this.salesmanTag = salesmanTag;
	}

	public String getShopownerTag() {
		return shopownerTag;
	}

	public void setShopownerTag(String shopownerTag) {
		this.shopownerTag = shopownerTag;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Integer getInvitationCount() {
		return invitationCount;
	}

	public void setInvitationCount(Integer invitationCount) {
		this.invitationCount = invitationCount;
	}

	public Integer getWithdrawAcount() {
		return withdrawAcount;
	}

	public void setWithdrawAcount(Integer withdrawAcount) {
		this.withdrawAcount = withdrawAcount;
	}

	public BigDecimal getFenrunTotal() {
		return fenrunTotal;
	}

	public void setFenrunTotal(BigDecimal fenrunTotal) {
		this.fenrunTotal = fenrunTotal;
	}

	public BigDecimal getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(BigDecimal cashBalance) {
		this.cashBalance = cashBalance;
	}

	public BigDecimal getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public BigDecimal getWithdrawalsAmount() {
		return withdrawalsAmount;
	}

	public void setWithdrawalsAmount(BigDecimal withdrawalsAmount) {
		this.withdrawalsAmount = withdrawalsAmount;
	}

	public Date getFirstJoinDate() {
		return firstJoinDate;
	}

	public void setFirstJoinDate(Date firstJoinDate) {
		this.firstJoinDate = firstJoinDate;
	}

	public Date getEndLachineDate() {
		return endLachineDate;
	}

	public void setEndLachineDate(Date endLachineDate) {
		this.endLachineDate = endLachineDate;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getSexDesc() {
		return sexDesc;
	}

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public String getSprChnlDesc() {
		return sprChnlDesc;
	}

	public void setSprChnlDesc(String sprChnlDesc) {
		this.sprChnlDesc = sprChnlDesc;
	}

	public String getRegClientDesc() {
		return regClientDesc;
	}

	public void setRegClientDesc(String regClientDesc) {
		this.regClientDesc = regClientDesc;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getgValue() {
		return gValue;
	}

	public void getgValue(Integer gValue) {
		this.gValue = gValue;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getFirstPayTime() {
		return firstPayTime;
	}

	public void setFirstPayTime(Date firstPayTime) {
		this.firstPayTime = firstPayTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void getLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getLoginDays() {
		return loginDays;
	}

	public void getLoginDays(Integer loginDays) {
		this.loginDays = loginDays;
	}

	public Date getLastPayTime() {
		return lastPayTime;
	}

	public void setLastPayTime(Date lastPayTime) {
		this.lastPayTime = lastPayTime;
	}

	public Integer getPayOrderCount() {
		return payOrderCount;
	}

	public void getPayOrderCount(Integer payOrderCount) {
		this.payOrderCount = payOrderCount;
	}

	public BigDecimal getPayOrderAmount() {
		return payOrderAmount;
	}

	public void setPayOrderAmount(BigDecimal payOrderAmount) {
		this.payOrderAmount = payOrderAmount;
	}

	public Integer getPayProductCount() {
		return payProductCount;
	}

	public void getPayProductCount(Integer payProductCount) {
		this.payProductCount = payProductCount;
	}

	public Integer getPayDays() {
		return payDays;
	}

	public void getPayDays(Integer payDays) {
		this.payDays = payDays;
	}

	public String getSpreadname() {
		return spreadname;
	}

	public void setSpreadname(String spreadname) {
		this.spreadname = spreadname;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setgValue(Integer gValue) {
		this.gValue = gValue;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public void setLoginDays(Integer loginDays) {
		this.loginDays = loginDays;
	}

	public void setPayOrderCount(Integer payOrderCount) {
		this.payOrderCount = payOrderCount;
	}

	public void setPayProductCount(Integer payProductCount) {
		this.payProductCount = payProductCount;
	}

	public void setPayDays(Integer payDays) {
		this.payDays = payDays;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getBlackPictures() {
		return blackPictures;
	}

	public void setBlackPictures(String blackPictures) {
		this.blackPictures = blackPictures;
	}

	public String getLogOperateType() {
		return logOperateType;
	}

	public void setLogOperateType(String logOperateType) {
		this.logOperateType = logOperateType;
	}

	public Date getLastAppLoginDate() {
		return lastAppLoginDate;
	}

	public void setLastAppLoginDate(Date lastAppLoginDate) {
		this.lastAppLoginDate = lastAppLoginDate;
	}

	public Date getBuySvipPayDate() {
		return buySvipPayDate;
	}

	public void setBuySvipPayDate(Date buySvipPayDate) {
		this.buySvipPayDate = buySvipPayDate;
	}

	public String getMemberStatuslog() {
		return memberStatuslog;
	}

	public void setMemberStatuslog(String memberStatuslog) {
		this.memberStatuslog = memberStatuslog;
	}

	public Date getMsLastLoginTime() {
		return msLastLoginTime;
	}

	public void setMsLastLoginTime(Date msLastLoginTime) {
		this.msLastLoginTime = msLastLoginTime;
	}

	public Date getMsFirstBuyTime() {
		return msFirstBuyTime;
	}

	public void setMsFirstBuyTime(Date msFirstBuyTime) {
		this.msFirstBuyTime = msFirstBuyTime;
	}

	public Date getMsLastBuyTime() {
		return msLastBuyTime;
	}

	public void setMsLastBuyTime(Date msLastBuyTime) {
		this.msLastBuyTime = msLastBuyTime;
	}

	public Integer getTotalBuyCount() {
		return totalBuyCount;
	}

	public void setTotalBuyCount(Integer totalBuyCount) {
		this.totalBuyCount = totalBuyCount;
	}

	public BigDecimal getTotalBuyAmount() {
		return totalBuyAmount;
	}

	public void setTotalBuyAmount(BigDecimal totalBuyAmount) {
		this.totalBuyAmount = totalBuyAmount;
	}

	public MemberExtend getMemberExtend() {
		return memberExtend;
	}

	public void setMemberExtend(MemberExtend memberExtend) {
		this.memberExtend = memberExtend;
	}

	public Integer getCustomerServiceOrderCount() {
		return customerServiceOrderCount;
	}

	public void setCustomerServiceOrderCount(Integer customerServiceOrderCount) {
		this.customerServiceOrderCount = customerServiceOrderCount;
	}

	public Integer getInterventionOrderCount() {
		return interventionOrderCount;
	}

	public void setInterventionOrderCount(Integer interventionOrderCount) {
		this.interventionOrderCount = interventionOrderCount;
	}

	public Integer getAppealOrderCount() {
		return appealOrderCount;
	}

	public void setAppealOrderCount(Integer appealOrderCount) {
		this.appealOrderCount = appealOrderCount;
	}

	public Integer getSubOrderCustomscuont() {
		return subOrderCustomscuont;
	}

	public void setSubOrderCustomscuont(Integer subOrderCustomscuont) {
		this.subOrderCustomscuont = subOrderCustomscuont;
	}

	public Integer getZsPlatformContactId() {
		return zsPlatformContactId;
	}

	public void setZsPlatformContactId(Integer zsPlatformContactId) {
		this.zsPlatformContactId = zsPlatformContactId;
	}

	public String getMchtSettledApplyId() {
		return mchtSettledApplyId;
	}

	public void setMchtSettledApplyId(String mchtSettledApplyId) {
		this.mchtSettledApplyId = mchtSettledApplyId;
	}

	public String getMchtCompany() {
		return mchtCompany;
	}

	public void setMchtCompany(String mchtCompany) {
		this.mchtCompany = mchtCompany;
	}

	public String getZsName() {
		return zsName;
	}

	public void setZsName(String zsName) {
		this.zsName = zsName;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getMchtCompanyNotNull() {
		return mchtCompanyNotNull;
	}

	public void setMchtCompanyNotNull(String mchtCompanyNotNull) {
		this.mchtCompanyNotNull = mchtCompanyNotNull;
	}

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}

	public Integer getMemberEverydayAdded() {
		return memberEverydayAdded;
	}

	public void setMemberEverydayAdded(Integer memberEverydayAdded) {
		this.memberEverydayAdded = memberEverydayAdded;
	}

	public Integer getMemberEverydayNewlyaddedConsumption() {
		return memberEverydayNewlyaddedConsumption;
	}

	public void setMemberEverydayNewlyaddedConsumption(
			Integer memberEverydayNewlyaddedConsumption) {
		this.memberEverydayNewlyaddedConsumption = memberEverydayNewlyaddedConsumption;
	}

	public Integer getMemberEverydayActive() {
		return memberEverydayActive;
	}

	public void setMemberEverydayActive(Integer memberEverydayActive) {
		this.memberEverydayActive = memberEverydayActive;
	}

	public Integer getMemberEverydayConsumption() {
		return memberEverydayConsumption;
	}

	public void setMemberEverydayConsumption(Integer memberEverydayConsumption) {
		this.memberEverydayConsumption = memberEverydayConsumption;
	}

	public Integer getMemberEverydaySvipcount() {
		return memberEverydaySvipcount;
	}

	public void setMemberEverydaySvipcount(Integer memberEverydaySvipcount) {
		this.memberEverydaySvipcount = memberEverydaySvipcount;
	}

	public String getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}

	public Integer getMemberMonthlydayAdded() {
		return memberMonthlydayAdded;
	}

	public void setMemberMonthlydayAdded(Integer memberMonthlydayAdded) {
		this.memberMonthlydayAdded = memberMonthlydayAdded;
	}

	public Integer getMemberMonthlydayNewlyaddedConsumption() {
		return memberMonthlydayNewlyaddedConsumption;
	}

	public void setMemberMonthlydayNewlyaddedConsumption(
			Integer memberMonthlydayNewlyaddedConsumption) {
		this.memberMonthlydayNewlyaddedConsumption = memberMonthlydayNewlyaddedConsumption;
	}

	public Integer getMemberMonthlydayActive() {
		return memberMonthlydayActive;
	}

	public void setMemberMonthlydayActive(Integer memberMonthlydayActive) {
		this.memberMonthlydayActive = memberMonthlydayActive;
	}

	public Integer getMemberMonthlydayConsumption() {
		return memberMonthlydayConsumption;
	}

	public void setMemberMonthlydayConsumption(Integer memberMonthlydayConsumption) {
		this.memberMonthlydayConsumption = memberMonthlydayConsumption;
	}

	public Integer getMemberMonthlydaySvipcount() {
		return memberMonthlydaySvipcount;
	}

	public void setMemberMonthlydaySvipcount(Integer memberMonthlydaySvipcount) {
		this.memberMonthlydaySvipcount = memberMonthlydaySvipcount;
	}

	public String getWeeklyDay() {
		return weeklyDay;
	}

	public void setWeeklyDay(String weeklyDay) {
		this.weeklyDay = weeklyDay;
	}

	public Integer getMemberWeeklyAdded() {
		return memberWeeklyAdded;
	}

	public void setMemberWeeklyAdded(Integer memberWeeklyAdded) {
		this.memberWeeklyAdded = memberWeeklyAdded;
	}

	public Integer getMemberWeeklyNewlyaddedConsumption() {
		return memberWeeklyNewlyaddedConsumption;
	}

	public void setMemberWeeklyNewlyaddedConsumption(
			Integer memberWeeklyNewlyaddedConsumption) {
		this.memberWeeklyNewlyaddedConsumption = memberWeeklyNewlyaddedConsumption;
	}

	public Integer getMemberWeeklyActive() {
		return memberWeeklyActive;
	}

	public void setMemberWeeklyActive(Integer memberWeeklyActive) {
		this.memberWeeklyActive = memberWeeklyActive;
	}

	public Integer getMemberWeeklyConsumption() {
		return memberWeeklyConsumption;
	}

	public void setMemberWeeklyConsumption(Integer memberWeeklyConsumption) {
		this.memberWeeklyConsumption = memberWeeklyConsumption;
	}

	public Integer getMemberWeeklySvipcount() {
		return memberWeeklySvipcount;
	}

	public void setMemberWeeklySvipcount(Integer memberWeeklySvipcount) {
		this.memberWeeklySvipcount = memberWeeklySvipcount;
	}
	
}

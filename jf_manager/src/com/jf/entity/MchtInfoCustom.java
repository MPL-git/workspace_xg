package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class MchtInfoCustom extends MchtInfo {

	private String mchtTypeDesc;

	private String isManageSelfDesc;

	private String statusDesc;

	private String isCompanyInfPerfectDesc;

	private String taxInvoiceAuditStatus;

	private String taxInvoiceAuditStatusDesc;

	private String mchtProductType;

	private String mchtProductBrand;

	private String contactProvinceName;

	private String contactCityName;

	private String contactCountyName;

	private String regFeeTypeDesc;

	private Integer mchtblpic;

	private BigDecimal depositTotalAmt; // 保证金余额

	private String totalAuditStatusDesc;

	private String shopTypeDesc;

	private String shopNameAuditStatusDesc;

	private String zsContactName;

	private String yyContactName;

	private String ddContactName;

	private String shContactName;

	private String cwContactName;

	private String kfContactName;

	private String fwContactName;

	private String sjContactName;

	private String dsMchtContactName;

	private String yyMchtContactName;

	private String ddMchtContactName;

	private String shMchtContactName;

	private String cwMchtContactName;

	private String kfMchtContactName;

	private String mchtUserName;

	private String mchtMobile;

	private String mchtEmail;

	private String mchtUserCode;

	private String settledContactName;

	private String settledPhone;

	private String settledEmail;

	private String settledTmallShop;

	private String settledTaobaoShop;

	private String settledJdShop;

	private String settledVipsShop;

	private String settledOtherShop;

	private String totalAuditStatusdesc;

	private String zsMobile;
	private String yyMobile;
	private String ddMobile;
	private String shMobile;
	private String cwMobile;
	private String kfMobile;
	private String fwMobile;
	private String sjMobile;

	private String dsMchtMobile;
	private String yyMchtMobile;
	private String ddMchtMobile;
	private String shMchtMobile;
	private String cwMchtMobile;
	private String kfMchtMobile;
	private String dsMchtQq;
	private String yyMchtQq;
	private String ddMchtQq;
	private String shMchtQq;
	private String cwMchtQq;
	private String kfMchtQq;
	private String dsMchtEmail;
	private String yyMchtEmail;
	private String ddMchtEmail;
	private String shMchtEmail;
	private String cwMchtEmail;
	private String kfMchtEmail;

	private int mchtcount;

	private int settledcount;

	private int commitauditdatecount;

	private int qualificationthroughcount;

	private int cooperatebegindatecount;

	private String eachDay;

	private int settledunsubmitted;

	private int settledaudited;

	private int settledadopt;

	private int shopopen;

	private int contractfile;

	private Integer xiaonengBusId;
	private String xiaonengCode;
	private String xiaonengStatus;
	private String xiaonengMchtName;

	private String productTypeName;

	private String productname;
	private int generalqualification;
	private int opentoopen;
	private int alreadyopened;
	private int contractfiling;
	private int sendback;
	private int shop;
	private int unsubmitted;
	private int submission;
	private int cancellation;

	private Integer sumQuantity; // 销售量
	private String avgProductScore; // 商品评价
	private String avgMchtScore; // 卖家评价
	private String avgWuliuScore; // 物流评价
	private String gradeDesc; // 等级
	private String mchtProductBrandName; // 品牌名与技术服务费
	private String shopStatusDesc; // 店铺状态
	private String activityAuthStatusDesc; // 活动状态
	private String degreeAdaptabilityDesc; // 配合度
	private String mchtOptimizeOperateRemarks; // 运营备注
	private String auditRiskDesc; // 法务风险
	private String resourceGradeDesc; // 站内资源等级
	private String mchtOptimizeDepositRemarks; // 保证金优化
	private String mchtOptimizeGrossProfitRateRemarks; // 毛利率优化
	private String mchtOptimizeProductRemarks; // 商品优化
	private String mchtOptimizeServiceRemarks; // 服务优化
	private String mchtOptimizeWuliuRemarks; // 物流配送
	private String mchtOptimizeSpreadRemarks; // 是否站外推广

	private String mchtContactType;// 限制查看商家联系人类型
	private Integer mchtCloseApplicationId; // 申请关店id

	private String eachMonth;

	private int mchtCount;

	private int settledunSubmitted;

	private int settleDaudited;

	private int settleDadopt;

	private int shopOpen;

	private int contractFile;

	private int mchtcloseApplication;

	private int mchtcloseCount;

	private int settledCount;

	private int popcommitauditCount;

	private int popqualificationthroughCount;

	private int popcooperatebeginCount;

	private int popmchtcloseAppliCation;

	private int popmchtcloseCount;

	private String licenseStatusDesc;

	private String productbrandName;
	private String degreeadaptabilitydDesc;
	private String degreeAuditriskDesc;
	
	private BigDecimal customerServiceOrderRateB; 
	private BigDecimal customerServiceOrderRateC; 
	private BigDecimal customerServiceOrderRateA; 
	private BigDecimal productApplauseRate; 

	private Integer productTypeId;

	private String openProductType;//类目
	private String openProductBrand;//品牌
	private Integer popId;//POP排款计划表ID
	private String typeDesc;//结算类型
	private Integer unpaidCount;//共几期未付
	private BigDecimal unpaidAmount;//未付总金额
	private Integer periods;//押几期
	private Integer exceedPeriods;//超过几期
	private BigDecimal exceedAmount;//超过金额
	private String settleOrderIds;//商家结算表ID集合

	//商家流量报表栏目
	private Integer memberVisitorsNum; //会员访客数
	private Integer unmemberVisitorsNum;//非会员访客数
	private Integer memberPageView;//会员浏览量
	private Integer unmemberPageView;//非会员浏览量
	private Integer paymentAmount;//支付单数
	private Integer addProductNum;//加购数
	private Integer submitOrderNum;//提交单数
	private String addProductRate;//加购转化
	private String submitOrderRate;//提交订单转化
	private String paymentRate;//支付转化
	private BigDecimal payMoney;//实付金额



	
	public String getSettleOrderIds() {
		return settleOrderIds;
	}

	public void setSettleOrderIds(String settleOrderIds) {
		this.settleOrderIds = settleOrderIds;
	}

	public String getOpenProductType() {
		return openProductType;
	}

	public void setOpenProductType(String openProductType) {
		this.openProductType = openProductType;
	}

	public String getOpenProductBrand() {
		return openProductBrand;
	}

	public void setOpenProductBrand(String openProductBrand) {
		this.openProductBrand = openProductBrand;
	}

	public Integer getPopId() {
		return popId;
	}

	public void setPopId(Integer popId) {
		this.popId = popId;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Integer getUnpaidCount() {
		return unpaidCount;
	}

	public void setUnpaidCount(Integer unpaidCount) {
		this.unpaidCount = unpaidCount;
	}

	public BigDecimal getUnpaidAmount() {
		return unpaidAmount;
	}

	public void setUnpaidAmount(BigDecimal unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public Integer getExceedPeriods() {
		return exceedPeriods;
	}

	public void setExceedPeriods(Integer exceedPeriods) {
		this.exceedPeriods = exceedPeriods;
	}

	public BigDecimal getExceedAmount() {
		return exceedAmount;
	}

	public void setExceedAmount(BigDecimal exceedAmount) {
		this.exceedAmount = exceedAmount;
	}

	private Integer days;
	private Date sendDate;
	private String contractCode;
	private Date contractBeginDate;
	private Date contractEndDate;
	
	public Date getContractBeginDate() {
		return contractBeginDate;
	}

	public void setContractBeginDate(Date contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getXiaonengBusId() {
		return xiaonengBusId;
	}

	public void setXiaonengBusId(Integer xiaonengBusId) {
		this.xiaonengBusId = xiaonengBusId;
	}

	public String getXiaonengCode() {
		return xiaonengCode;
	}

	public void setXiaonengCode(String xiaonengCode) {
		this.xiaonengCode = xiaonengCode;
	}

	public String getXiaonengStatus() {
		return xiaonengStatus;
	}

	public void setXiaonengStatus(String xiaonengStatus) {
		this.xiaonengStatus = xiaonengStatus;
	}

	public Integer getMchtblpic() {
		return mchtblpic;
	}

	public void setMchtblpic(Integer mchtblpic) {
		this.mchtblpic = mchtblpic;
	}

	public String getMchtTypeDesc() {
		return mchtTypeDesc;
	}

	public void setMchtTypeDesc(String mchtTypeDesc) {
		this.mchtTypeDesc = mchtTypeDesc == null ? null : mchtTypeDesc.trim();
	}

	public String getIsManageSelfDesc() {
		return isManageSelfDesc;
	}

	public void setIsManageSelfDesc(String isManageSelfDesc) {
		this.isManageSelfDesc = isManageSelfDesc == null ? null
				: isManageSelfDesc.trim();
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc == null ? null : statusDesc.trim();
	}

	public String getIsCompanyInfPerfectDesc() {
		return isCompanyInfPerfectDesc;
	}

	public void setIsCompanyInfPerfectDesc(String isCompanyInfPerfectDesc) {
		this.isCompanyInfPerfectDesc = isCompanyInfPerfectDesc == null ? null
				: isCompanyInfPerfectDesc.trim();
	}

	public String getTaxInvoiceAuditStatus() {
		return taxInvoiceAuditStatus;
	}

	public void setTaxInvoiceAuditStatus(String taxInvoiceAuditStatus) {
		this.taxInvoiceAuditStatus = taxInvoiceAuditStatus == null ? null
				: taxInvoiceAuditStatus.trim();
	}

	public String getTaxInvoiceAuditStatusDesc() {
		return taxInvoiceAuditStatusDesc;
	}

	public void setTaxInvoiceAuditStatusDesc(String taxInvoiceAuditStatusDesc) {
		this.taxInvoiceAuditStatusDesc = taxInvoiceAuditStatusDesc == null ? null
				: taxInvoiceAuditStatusDesc.trim();
	}

	public String getMchtProductType() {
		return mchtProductType;
	}

	public void setMchtProductType(String mchtProductType) {
		this.mchtProductType = mchtProductType == null ? null : mchtProductType
				.trim();
	}

	public String getMchtProductBrand() {
		return mchtProductBrand;
	}

	public void setMchtProductBrand(String mchtProductBrand) {
		this.mchtProductBrand = mchtProductBrand == null ? null
				: mchtProductBrand.trim();
	}

	public String getContactProvinceName() {
		return contactProvinceName;
	}

	public void setContactProvinceName(String contactProvinceName) {
		this.contactProvinceName = contactProvinceName;
	}

	public String getContactCityName() {
		return contactCityName;
	}

	public void setContactCityName(String contactCityName) {
		this.contactCityName = contactCityName;
	}

	public String getContactCountyName() {
		return contactCountyName;
	}

	public void setContactCountyName(String contactCountyName) {
		this.contactCountyName = contactCountyName;
	}

	public String getRegFeeTypeDesc() {
		return regFeeTypeDesc;
	}

	public void setRegFeeTypeDesc(String regFeeTypeDesc) {
		this.regFeeTypeDesc = regFeeTypeDesc;
	}

	public String getTotalAuditStatusDesc() {
		return totalAuditStatusDesc;
	}

	public void setTotalAuditStatusDesc(String totalAuditStatusDesc) {
		this.totalAuditStatusDesc = totalAuditStatusDesc;
	}

	public String getShopTypeDesc() {
		return shopTypeDesc;
	}

	public void setShopTypeDesc(String shopTypeDesc) {
		this.shopTypeDesc = shopTypeDesc;
	}

	public String getShopNameAuditStatusDesc() {
		return shopNameAuditStatusDesc;
	}

	public void setShopNameAuditStatusDesc(String shopNameAuditStatusDesc) {
		this.shopNameAuditStatusDesc = shopNameAuditStatusDesc;
	}

	public BigDecimal getDepositTotalAmt() {
		return depositTotalAmt;
	}

	public void setDepositTotalAmt(BigDecimal depositTotalAmt) {
		this.depositTotalAmt = depositTotalAmt;
	}

	public String getZsContactName() {
		return zsContactName;
	}

	public void setZsContactName(String zsContactName) {
		this.zsContactName = zsContactName;
	}

	public String getYyContactName() {
		return yyContactName;
	}

	public void setYyContactName(String yyContactName) {
		this.yyContactName = yyContactName;
	}

	public String getDdContactName() {
		return ddContactName;
	}

	public void setDdContactName(String ddContactName) {
		this.ddContactName = ddContactName;
	}

	public String getShContactName() {
		return shContactName;
	}

	public void setShContactName(String shContactName) {
		this.shContactName = shContactName;
	}

	public String getCwContactName() {
		return cwContactName;
	}

	public void setCwContactName(String cwContactName) {
		this.cwContactName = cwContactName;
	}

	public String getKfContactName() {
		return kfContactName;
	}

	public void setKfContactName(String kfContactName) {
		this.kfContactName = kfContactName;
	}

	public String getFwContactName() {
		return fwContactName;
	}

	public void setFwContactName(String fwContactName) {
		this.fwContactName = fwContactName;
	}

	public String getSjContactName() {
		return sjContactName;
	}

	public void setSjContactName(String sjContactName) {
		this.sjContactName = sjContactName;
	}

	public String getDsMchtContactName() {
		return dsMchtContactName;
	}

	public void setDsMchtContactName(String dsMchtContactName) {
		this.dsMchtContactName = dsMchtContactName;
	}

	public String getYyMchtContactName() {
		return yyMchtContactName;
	}

	public void setYyMchtContactName(String yyMchtContactName) {
		this.yyMchtContactName = yyMchtContactName;
	}

	public String getDdMchtContactName() {
		return ddMchtContactName;
	}

	public void setDdMchtContactName(String ddMchtContactName) {
		this.ddMchtContactName = ddMchtContactName;
	}

	public String getShMchtContactName() {
		return shMchtContactName;
	}

	public void setShMchtContactName(String shMchtContactName) {
		this.shMchtContactName = shMchtContactName;
	}

	public String getCwMchtContactName() {
		return cwMchtContactName;
	}

	public void setCwMchtContactName(String cwMchtContactName) {
		this.cwMchtContactName = cwMchtContactName;
	}

	public String getKfMchtContactName() {
		return kfMchtContactName;
	}

	public void setKfMchtContactName(String kfMchtContactName) {
		this.kfMchtContactName = kfMchtContactName;
	}

	public String getMchtUserName() {
		return mchtUserName;
	}

	public void setMchtUserName(String mchtUserName) {
		this.mchtUserName = mchtUserName;
	}

	public String getMchtMobile() {
		return mchtMobile;
	}

	public void setMchtMobile(String mchtMobile) {
		this.mchtMobile = mchtMobile;
	}

	public String getMchtEmail() {
		return mchtEmail;
	}

	public void setMchtEmail(String mchtEmail) {
		this.mchtEmail = mchtEmail;
	}

	public String getSettledContactName() {
		return settledContactName;
	}

	public void setSettledContactName(String settledContactName) {
		this.settledContactName = settledContactName;
	}

	public String getSettledPhone() {
		return settledPhone;
	}

	public void setSettledPhone(String settledPhone) {
		this.settledPhone = settledPhone;
	}

	public String getSettledEmail() {
		return settledEmail;
	}

	public void setSettledEmail(String settledEmail) {
		this.settledEmail = settledEmail;
	}

	public String getSettledTmallShop() {
		return settledTmallShop;
	}

	public void setSettledTmallShop(String settledTmallShop) {
		this.settledTmallShop = settledTmallShop;
	}

	public String getSettledTaobaoShop() {
		return settledTaobaoShop;
	}

	public void setSettledTaobaoShop(String settledTaobaoShop) {
		this.settledTaobaoShop = settledTaobaoShop;
	}

	public String getSettledJdShop() {
		return settledJdShop;
	}

	public void setSettledJdShop(String settledJdShop) {
		this.settledJdShop = settledJdShop;
	}

	public String getSettledVipsShop() {
		return settledVipsShop;
	}

	public void setSettledVipsShop(String settledVipsShop) {
		this.settledVipsShop = settledVipsShop;
	}

	public String getSettledOtherShop() {
		return settledOtherShop;
	}

	public void setSettledOtherShop(String settledOtherShop) {
		this.settledOtherShop = settledOtherShop;
	}

	public String getZsMobile() {
		return zsMobile;
	}

	public void setZsMobile(String zsMobile) {
		this.zsMobile = zsMobile;
	}

	public String getYyMobile() {
		return yyMobile;
	}

	public void setYyMobile(String yyMobile) {
		this.yyMobile = yyMobile;
	}

	public String getDdMobile() {
		return ddMobile;
	}

	public void setDdMobile(String ddMobile) {
		this.ddMobile = ddMobile;
	}

	public String getShMobile() {
		return shMobile;
	}

	public void setShMobile(String shMobile) {
		this.shMobile = shMobile;
	}

	public String getCwMobile() {
		return cwMobile;
	}

	public void setCwMobile(String cwMobile) {
		this.cwMobile = cwMobile;
	}

	public String getKfMobile() {
		return kfMobile;
	}

	public void setKfMobile(String kfMobile) {
		this.kfMobile = kfMobile;
	}

	public String getFwMobile() {
		return fwMobile;
	}

	public void setFwMobile(String fwMobile) {
		this.fwMobile = fwMobile;
	}

	public String getSjMobile() {
		return sjMobile;
	}

	public void setSjMobile(String sjMobile) {
		this.sjMobile = sjMobile;
	}

	public String getDsMchtMobile() {
		return dsMchtMobile;
	}

	public void setDsMchtMobile(String dsMchtMobile) {
		this.dsMchtMobile = dsMchtMobile;
	}

	public String getYyMchtMobile() {
		return yyMchtMobile;
	}

	public void setYyMchtMobile(String yyMchtMobile) {
		this.yyMchtMobile = yyMchtMobile;
	}

	public String getDdMchtMobile() {
		return ddMchtMobile;
	}

	public void setDdMchtMobile(String ddMchtMobile) {
		this.ddMchtMobile = ddMchtMobile;
	}

	public String getShMchtMobile() {
		return shMchtMobile;
	}

	public void setShMchtMobile(String shMchtMobile) {
		this.shMchtMobile = shMchtMobile;
	}

	public String getCwMchtMobile() {
		return cwMchtMobile;
	}

	public void setCwMchtMobile(String cwMchtMobile) {
		this.cwMchtMobile = cwMchtMobile;
	}

	public String getKfMchtMobile() {
		return kfMchtMobile;
	}

	public void setKfMchtMobile(String kfMchtMobile) {
		this.kfMchtMobile = kfMchtMobile;
	}

	public String getDsMchtQq() {
		return dsMchtQq;
	}

	public void setDsMchtQq(String dsMchtQq) {
		this.dsMchtQq = dsMchtQq;
	}

	public String getYyMchtQq() {
		return yyMchtQq;
	}

	public void setYyMchtQq(String yyMchtQq) {
		this.yyMchtQq = yyMchtQq;
	}

	public String getDdMchtQq() {
		return ddMchtQq;
	}

	public void setDdMchtQq(String ddMchtQq) {
		this.ddMchtQq = ddMchtQq;
	}

	public String getShMchtQq() {
		return shMchtQq;
	}

	public void setShMchtQq(String shMchtQq) {
		this.shMchtQq = shMchtQq;
	}

	public String getCwMchtQq() {
		return cwMchtQq;
	}

	public void setCwMchtQq(String cwMchtQq) {
		this.cwMchtQq = cwMchtQq;
	}

	public String getKfMchtQq() {
		return kfMchtQq;
	}

	public void setKfMchtQq(String kfMchtQq) {
		this.kfMchtQq = kfMchtQq;
	}

	public String getDsMchtEmail() {
		return dsMchtEmail;
	}

	public void setDsMchtEmail(String dsMchtEmail) {
		this.dsMchtEmail = dsMchtEmail;
	}

	public String getYyMchtEmail() {
		return yyMchtEmail;
	}

	public void setYyMchtEmail(String yyMchtEmail) {
		this.yyMchtEmail = yyMchtEmail;
	}

	public String getDdMchtEmail() {
		return ddMchtEmail;
	}

	public void setDdMchtEmail(String ddMchtEmail) {
		this.ddMchtEmail = ddMchtEmail;
	}

	public String getShMchtEmail() {
		return shMchtEmail;
	}

	public void setShMchtEmail(String shMchtEmail) {
		this.shMchtEmail = shMchtEmail;
	}

	public String getCwMchtEmail() {
		return cwMchtEmail;
	}

	public void setCwMchtEmail(String cwMchtEmail) {
		this.cwMchtEmail = cwMchtEmail;
	}

	public String getKfMchtEmail() {
		return kfMchtEmail;
	}

	public void setKfMchtEmail(String kfMchtEmail) {
		this.kfMchtEmail = kfMchtEmail;
	}

	public String getMchtUserCode() {
		return mchtUserCode;
	}

	public void setMchtUserCode(String mchtUserCode) {
		this.mchtUserCode = mchtUserCode;
	}

	public int getMchtcount() {
		return mchtcount;
	}

	public void setMchtcount(int mchtcount) {
		this.mchtcount = mchtcount;
	}

	public int getSettledcount() {
		return settledcount;
	}

	public void setSettledcount(int settledcount) {
		this.settledcount = settledcount;
	}

	public int getCommitauditdatecount() {
		return commitauditdatecount;
	}

	public void setCommitauditdatecount(int commitauditdatecount) {
		this.commitauditdatecount = commitauditdatecount;
	}

	public int getQualificationthroughcount() {
		return qualificationthroughcount;
	}

	public void setQualificationthroughcount(int qualificationthroughcount) {
		this.qualificationthroughcount = qualificationthroughcount;
	}

	public int getCooperatebegindatecount() {
		return cooperatebegindatecount;
	}

	public void setCooperatebegindatecount(int cooperatebegindatecount) {
		this.cooperatebegindatecount = cooperatebegindatecount;
	}

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}

	public int getSettledunsubmitted() {
		return settledunsubmitted;
	}

	public void setSettledunsubmitted(int settledunsubmitted) {
		this.settledunsubmitted = settledunsubmitted;
	}

	public int getSettledaudited() {
		return settledaudited;
	}

	public void setSettledaudited(int settledaudited) {
		this.settledaudited = settledaudited;
	}

	public int getSettledadopt() {
		return settledadopt;
	}

	public void setSettledadopt(int settledadopt) {
		this.settledadopt = settledadopt;
	}

	public int getShopopen() {
		return shopopen;
	}

	public void setShopopen(int shopopen) {
		this.shopopen = shopopen;
	}

	public int getContractfile() {
		return contractfile;
	}

	public void setContractfile(int contractfile) {
		this.contractfile = contractfile;
	}

	public String getXiaonengMchtName() {
		return xiaonengMchtName;
	}

	public void setXiaonengMchtName(String xiaonengMchtName) {
		this.xiaonengMchtName = xiaonengMchtName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getGeneralqualification() {
		return generalqualification;
	}

	public void setGeneralqualification(int generalqualification) {
		this.generalqualification = generalqualification;
	}

	public int getOpentoopen() {
		return opentoopen;
	}

	public void setOpentoopen(int opentoopen) {
		this.opentoopen = opentoopen;
	}

	public int getAlreadyopened() {
		return alreadyopened;
	}

	public void setAlreadyopened(int alreadyopened) {
		this.alreadyopened = alreadyopened;
	}

	public int getContractfiling() {
		return contractfiling;
	}

	public void setContractfiling(int contractfiling) {
		this.contractfiling = contractfiling;
	}

	public int getSendback() {
		return sendback;
	}

	public void setSendback(int sendback) {
		this.sendback = sendback;
	}

	public int getShop() {
		return shop;
	}

	public void setShop(int shop) {
		this.shop = shop;
	}

	public int getUnsubmitted() {
		return unsubmitted;
	}

	public void setUnsubmitted(int unsubmitted) {
		this.unsubmitted = unsubmitted;
	}

	public int getSubmission() {
		return submission;
	}

	public void setSubmission(int submission) {
		this.submission = submission;
	}

	public int getCancellation() {
		return cancellation;
	}

	public void setCancellation(int cancellation) {
		this.cancellation = cancellation;
	}

	public String getTotalAuditStatusdesc() {
		return totalAuditStatusdesc;
	}

	public void setTotalAuditStatusdesc(String totalAuditStatusdesc) {
		this.totalAuditStatusdesc = totalAuditStatusdesc;
	}

	public Integer getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(Integer sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	public String getAvgProductScore() {
		return avgProductScore;
	}

	public void setAvgProductScore(String avgProductScore) {
		this.avgProductScore = avgProductScore;
	}

	public String getAvgMchtScore() {
		return avgMchtScore;
	}

	public void setAvgMchtScore(String avgMchtScore) {
		this.avgMchtScore = avgMchtScore;
	}

	public String getAvgWuliuScore() {
		return avgWuliuScore;
	}

	public void setAvgWuliuScore(String avgWuliuScore) {
		this.avgWuliuScore = avgWuliuScore;
	}

	public String getMchtContactType() {
		return mchtContactType;
	}

	public void setMchtContactType(String mchtContactType) {
		this.mchtContactType = mchtContactType;
	}

	public String getGradeDesc() {
		return gradeDesc;
	}

	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}

	public Integer getMchtCloseApplicationId() {
		return mchtCloseApplicationId;
	}

	public void setMchtCloseApplicationId(Integer mchtCloseApplicationId) {
		this.mchtCloseApplicationId = mchtCloseApplicationId;
	}

	public String getMchtProductBrandName() {
		return mchtProductBrandName;
	}

	public void setMchtProductBrandName(String mchtProductBrandName) {
		this.mchtProductBrandName = mchtProductBrandName;
	}

	public String getShopStatusDesc() {
		return shopStatusDesc;
	}

	public void setShopStatusDesc(String shopStatusDesc) {
		this.shopStatusDesc = shopStatusDesc;
	}

	public String getActivityAuthStatusDesc() {
		return activityAuthStatusDesc;
	}

	public void setActivityAuthStatusDesc(String activityAuthStatusDesc) {
		this.activityAuthStatusDesc = activityAuthStatusDesc;
	}

	public String getDegreeAdaptabilityDesc() {
		return degreeAdaptabilityDesc;
	}

	public void setDegreeAdaptabilityDesc(String degreeAdaptabilityDesc) {
		this.degreeAdaptabilityDesc = degreeAdaptabilityDesc;
	}

	public String getMchtOptimizeOperateRemarks() {
		return mchtOptimizeOperateRemarks;
	}

	public void setMchtOptimizeOperateRemarks(String mchtOptimizeOperateRemarks) {
		this.mchtOptimizeOperateRemarks = mchtOptimizeOperateRemarks;
	}

	public String getAuditRiskDesc() {
		return auditRiskDesc;
	}

	public void setAuditRiskDesc(String auditRiskDesc) {
		this.auditRiskDesc = auditRiskDesc;
	}

	public String getResourceGradeDesc() {
		return resourceGradeDesc;
	}

	public void setResourceGradeDesc(String resourceGradeDesc) {
		this.resourceGradeDesc = resourceGradeDesc;
	}

	public String getMchtOptimizeDepositRemarks() {
		return mchtOptimizeDepositRemarks;
	}

	public void setMchtOptimizeDepositRemarks(String mchtOptimizeDepositRemarks) {
		this.mchtOptimizeDepositRemarks = mchtOptimizeDepositRemarks;
	}

	public String getMchtOptimizeGrossProfitRateRemarks() {
		return mchtOptimizeGrossProfitRateRemarks;
	}

	public void setMchtOptimizeGrossProfitRateRemarks(
			String mchtOptimizeGrossProfitRateRemarks) {
		this.mchtOptimizeGrossProfitRateRemarks = mchtOptimizeGrossProfitRateRemarks;
	}

	public String getMchtOptimizeProductRemarks() {
		return mchtOptimizeProductRemarks;
	}

	public void setMchtOptimizeProductRemarks(String mchtOptimizeProductRemarks) {
		this.mchtOptimizeProductRemarks = mchtOptimizeProductRemarks;
	}

	public String getMchtOptimizeServiceRemarks() {
		return mchtOptimizeServiceRemarks;
	}

	public void setMchtOptimizeServiceRemarks(String mchtOptimizeServiceRemarks) {
		this.mchtOptimizeServiceRemarks = mchtOptimizeServiceRemarks;
	}

	public String getMchtOptimizeWuliuRemarks() {
		return mchtOptimizeWuliuRemarks;
	}

	public void setMchtOptimizeWuliuRemarks(String mchtOptimizeWuliuRemarks) {
		this.mchtOptimizeWuliuRemarks = mchtOptimizeWuliuRemarks;
	}

	public String getMchtOptimizeSpreadRemarks() {
		return mchtOptimizeSpreadRemarks;
	}

	public void setMchtOptimizeSpreadRemarks(String mchtOptimizeSpreadRemarks) {
		this.mchtOptimizeSpreadRemarks = mchtOptimizeSpreadRemarks;
	}

	public String getEachMonth() {
		return eachMonth;
	}

	public void setEachMonth(String eachMonth) {
		this.eachMonth = eachMonth;
	}

	public int getMchtCount() {
		return mchtCount;
	}

	public void setMchtCount(int mchtCount) {
		this.mchtCount = mchtCount;
	}

	public int getSettledunSubmitted() {
		return settledunSubmitted;
	}

	public void setSettledunSubmitted(int settledunSubmitted) {
		this.settledunSubmitted = settledunSubmitted;
	}

	public int getSettleDaudited() {
		return settleDaudited;
	}

	public void setSettleDaudited(int settleDaudited) {
		this.settleDaudited = settleDaudited;
	}

	public int getSettleDadopt() {
		return settleDadopt;
	}

	public void setSettleDadopt(int settleDadopt) {
		this.settleDadopt = settleDadopt;
	}

	public int getShopOpen() {
		return shopOpen;
	}

	public void setShopOpen(int shopOpen) {
		this.shopOpen = shopOpen;
	}

	public int getContractFile() {
		return contractFile;
	}

	public void setContractFile(int contractFile) {
		this.contractFile = contractFile;
	}

	public int getMchtcloseApplication() {
		return mchtcloseApplication;
	}

	public void setMchtcloseApplication(int mchtcloseApplication) {
		this.mchtcloseApplication = mchtcloseApplication;
	}

	public int getMchtcloseCount() {
		return mchtcloseCount;
	}

	public void setMchtcloseCount(int mchtcloseCount) {
		this.mchtcloseCount = mchtcloseCount;
	}

	public int getSettledCount() {
		return settledCount;
	}

	public void setSettledCount(int settledCount) {
		this.settledCount = settledCount;
	}

	public int getPopcommitauditCount() {
		return popcommitauditCount;
	}

	public void setPopcommitauditCount(int popcommitauditCount) {
		this.popcommitauditCount = popcommitauditCount;
	}

	public int getPopqualificationthroughCount() {
		return popqualificationthroughCount;
	}

	public void setPopqualificationthroughCount(int popqualificationthroughCount) {
		this.popqualificationthroughCount = popqualificationthroughCount;
	}

	public int getPopcooperatebeginCount() {
		return popcooperatebeginCount;
	}

	public void setPopcooperatebeginCount(int popcooperatebeginCount) {
		this.popcooperatebeginCount = popcooperatebeginCount;
	}

	public int getPopmchtcloseAppliCation() {
		return popmchtcloseAppliCation;
	}

	public void setPopmchtcloseAppliCation(int popmchtcloseAppliCation) {
		this.popmchtcloseAppliCation = popmchtcloseAppliCation;
	}

	public int getPopmchtcloseCount() {
		return popmchtcloseCount;
	}

	public void setPopmchtcloseCount(int popmchtcloseCount) {
		this.popmchtcloseCount = popmchtcloseCount;
	}

	public String getLicenseStatusDesc() {
		return licenseStatusDesc;
	}

	public void setLicenseStatusDesc(String licenseStatusDesc) {
		this.licenseStatusDesc = licenseStatusDesc;
	}

	public String getProductbrandName() {
		return productbrandName;
	}

	public void setProductbrandName(String productbrandName) {
		this.productbrandName = productbrandName;
	}

	public String getDegreeadaptabilitydDesc() {
		return degreeadaptabilitydDesc;
	}

	public void setDegreeadaptabilitydDesc(String degreeadaptabilitydDesc) {
		this.degreeadaptabilitydDesc = degreeadaptabilitydDesc;
	}

	public String getDegreeAuditriskDesc() {
		return degreeAuditriskDesc;
	}

	public void setDegreeAuditriskDesc(String degreeAuditriskDesc) {
		this.degreeAuditriskDesc = degreeAuditriskDesc;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public BigDecimal getCustomerServiceOrderRateB() {
		return customerServiceOrderRateB;
	}

	public void setCustomerServiceOrderRateB(BigDecimal customerServiceOrderRateB) {
		this.customerServiceOrderRateB = customerServiceOrderRateB;
	}

	public BigDecimal getCustomerServiceOrderRateC() {
		return customerServiceOrderRateC;
	}

	public void setCustomerServiceOrderRateC(BigDecimal customerServiceOrderRateC) {
		this.customerServiceOrderRateC = customerServiceOrderRateC;
	}

	public BigDecimal getCustomerServiceOrderRateA() {
		return customerServiceOrderRateA;
	}

	public void setCustomerServiceOrderRateA(BigDecimal customerServiceOrderRateA) {
		this.customerServiceOrderRateA = customerServiceOrderRateA;
	}

	public BigDecimal getProductApplauseRate() {
		return productApplauseRate;
	}

	public void setProductApplauseRate(BigDecimal productApplauseRate) {
		this.productApplauseRate = productApplauseRate;
	}

	public Integer getMemberVisitorsNum() {
		return memberVisitorsNum;
	}

	public void setMemberVisitorsNum(Integer memberVisitorsNum) {
		this.memberVisitorsNum = memberVisitorsNum;
	}

	public Integer getUnmemberVisitorsNum() {
		return unmemberVisitorsNum;
	}

	public void setUnmemberVisitorsNum(Integer unmemberVisitorsNum) {
		this.unmemberVisitorsNum = unmemberVisitorsNum;
	}

	public Integer getMemberPageView() {
		return memberPageView;
	}

	public void setMemberPageView(Integer memberPageView) {
		this.memberPageView = memberPageView;
	}

	public Integer getUnmemberPageView() {
		return unmemberPageView;
	}

	public void setUnmemberPageView(Integer unmemberPageView) {
		this.unmemberPageView = unmemberPageView;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Integer getAddProductNum() {
		return addProductNum;
	}

	public void setAddProductNum(Integer addProductNum) {
		this.addProductNum = addProductNum;
	}

	public Integer getSubmitOrderNum() {
		return submitOrderNum;
	}

	public void setSubmitOrderNum(Integer submitOrderNum) {
		this.submitOrderNum = submitOrderNum;
	}

	public String getAddProductRate() {
		return addProductRate;
	}

	public void setAddProductRate(String addProductRate) {
		this.addProductRate = addProductRate;
	}

	public String getSubmitOrderRate() {
		return submitOrderRate;
	}

	public void setSubmitOrderRate(String submitOrderRate) {
		this.submitOrderRate = submitOrderRate;
	}

	public String getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(String paymentRate) {
		this.paymentRate = paymentRate;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
}
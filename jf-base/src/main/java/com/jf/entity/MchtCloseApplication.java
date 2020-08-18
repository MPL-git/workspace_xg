package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtCloseApplication {
    private Integer id;

    private Integer mchtId;

    private String applySource;

    private String applyName;

    private String applyReason;

    private String progressStatus;

    private String zsConfirmStatus;

    private Date zsConfirmDate;

    private Integer zsConfirmBy;

    private String zsRejectReason;

    private String zsRemarks;

    private Date zsRemarksDate;

    private String mchtInfoStatus;

    private String activityStatus;

    private String commodityStatus;

    private String marketingStatus;

    private Date commodityConfirmDate;

    private Integer commodityConfirmBy;

    private String commodityRemarks;

    private Date commodityRemarksDate;

    private String fwCloseHangUpStatus;

    private String mchtContractStatus;

    private String mchtArchiveStatus;

    private String businessInformationStatus;

    private String businessInformationRemarks;

    private String fwHangUpReason;

    private Date fwHangUpDate;

    private Date mchtArchiveConfirmDate;

    private Integer mchtArchiveConfirmBy;

    private String mchtArchiveRemarks;

    private Date mchtArchiveRemarksDate;

    private String kfCloseHangUpStatus;

    private String orderConfirmStatus;

    private String serviceOrderConfirmStatus;

    private String appealOrderConfirmStatus;

    private String interventionOrderConfirmStatus;

    private String threePackagePeriod;

    private String kfHangUpReason;

    private Date kfHangUpDate;

    private Date kfConfirmDate;

    private Integer kfConfirmBy;

    private String kfRemarks;

    private Date kfRemarksDate;

    private String cwCloseHangUpStatus;

    private String paymentOfGoodsConfirm;

    private String cwHangUpReason;

    private Date cwConfirmDate;

    private Integer cwConfirmBy;

    private String cwRemarks;

    private Date cwRemarksDate;

    private String filePath;

    private Integer expressId;

    private String expressNo;

    private String endCooperationAgreement;

    private Date agreementIssueDate;

    private Date depositDate;

    private Date fwConfirmDate;

    private Integer fwConfirmBy;

    private String fwRemarks;

    private Date fwRemarksDate;

    private String directorConfirmStatus;

    private Date directorConfirmDate;

    private Integer directorConfirmBy;

    private String productConfirmStatus;

    private Date productConfirmDate;

    private Integer productConfirmBy;

    private String settlementAuditStatus;

    private Date settlementConfirmDate;

    private Integer settlementConfirmBy;

    private String depositReturnStatus;

    private Date depositReturnDate;

    private String needPayStatus;

    private Date opConfirmDate;

    private Integer opConfirmBy;

    private String closeReason;

    private String isPlatformSend;

    private Date platformSendDate;

    private Integer platformExpressId;

    private String platformExpressNo;

    private String contractAuditStatus;

    private Integer contractAuditBy;

    private Date contractUploadDate;

    private String innerRemarks;

    private String contractAuditRemarks;

    private String contractArchiveStatus;

    private Integer contractArchiveBy;

    private String contractArchiveRemarks;

    private String billsPath;

    private String comfirmBillsStatus;

    private BigDecimal returnedDepositAmount;

    private String isNeedUpload;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getApplySource() {
        return applySource;
    }

    public void setApplySource(String applySource) {
        this.applySource = applySource == null ? null : applySource.trim();
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus == null ? null : progressStatus.trim();
    }

    public String getZsConfirmStatus() {
        return zsConfirmStatus;
    }

    public void setZsConfirmStatus(String zsConfirmStatus) {
        this.zsConfirmStatus = zsConfirmStatus == null ? null : zsConfirmStatus.trim();
    }

    public Date getZsConfirmDate() {
        return zsConfirmDate;
    }

    public void setZsConfirmDate(Date zsConfirmDate) {
        this.zsConfirmDate = zsConfirmDate;
    }

    public Integer getZsConfirmBy() {
        return zsConfirmBy;
    }

    public void setZsConfirmBy(Integer zsConfirmBy) {
        this.zsConfirmBy = zsConfirmBy;
    }

    public String getZsRejectReason() {
        return zsRejectReason;
    }

    public void setZsRejectReason(String zsRejectReason) {
        this.zsRejectReason = zsRejectReason == null ? null : zsRejectReason.trim();
    }

    public String getZsRemarks() {
        return zsRemarks;
    }

    public void setZsRemarks(String zsRemarks) {
        this.zsRemarks = zsRemarks == null ? null : zsRemarks.trim();
    }

    public Date getZsRemarksDate() {
        return zsRemarksDate;
    }

    public void setZsRemarksDate(Date zsRemarksDate) {
        this.zsRemarksDate = zsRemarksDate;
    }

    public String getMchtInfoStatus() {
        return mchtInfoStatus;
    }

    public void setMchtInfoStatus(String mchtInfoStatus) {
        this.mchtInfoStatus = mchtInfoStatus == null ? null : mchtInfoStatus.trim();
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus == null ? null : activityStatus.trim();
    }

    public String getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(String commodityStatus) {
        this.commodityStatus = commodityStatus == null ? null : commodityStatus.trim();
    }

    public String getMarketingStatus() {
        return marketingStatus;
    }

    public void setMarketingStatus(String marketingStatus) {
        this.marketingStatus = marketingStatus == null ? null : marketingStatus.trim();
    }

    public Date getCommodityConfirmDate() {
        return commodityConfirmDate;
    }

    public void setCommodityConfirmDate(Date commodityConfirmDate) {
        this.commodityConfirmDate = commodityConfirmDate;
    }

    public Integer getCommodityConfirmBy() {
        return commodityConfirmBy;
    }

    public void setCommodityConfirmBy(Integer commodityConfirmBy) {
        this.commodityConfirmBy = commodityConfirmBy;
    }

    public String getCommodityRemarks() {
        return commodityRemarks;
    }

    public void setCommodityRemarks(String commodityRemarks) {
        this.commodityRemarks = commodityRemarks == null ? null : commodityRemarks.trim();
    }

    public Date getCommodityRemarksDate() {
        return commodityRemarksDate;
    }

    public void setCommodityRemarksDate(Date commodityRemarksDate) {
        this.commodityRemarksDate = commodityRemarksDate;
    }

    public String getFwCloseHangUpStatus() {
        return fwCloseHangUpStatus;
    }

    public void setFwCloseHangUpStatus(String fwCloseHangUpStatus) {
        this.fwCloseHangUpStatus = fwCloseHangUpStatus == null ? null : fwCloseHangUpStatus.trim();
    }

    public String getMchtContractStatus() {
        return mchtContractStatus;
    }

    public void setMchtContractStatus(String mchtContractStatus) {
        this.mchtContractStatus = mchtContractStatus == null ? null : mchtContractStatus.trim();
    }

    public String getMchtArchiveStatus() {
        return mchtArchiveStatus;
    }

    public void setMchtArchiveStatus(String mchtArchiveStatus) {
        this.mchtArchiveStatus = mchtArchiveStatus == null ? null : mchtArchiveStatus.trim();
    }

    public String getBusinessInformationStatus() {
        return businessInformationStatus;
    }

    public void setBusinessInformationStatus(String businessInformationStatus) {
        this.businessInformationStatus = businessInformationStatus == null ? null : businessInformationStatus.trim();
    }

    public String getBusinessInformationRemarks() {
        return businessInformationRemarks;
    }

    public void setBusinessInformationRemarks(String businessInformationRemarks) {
        this.businessInformationRemarks = businessInformationRemarks == null ? null : businessInformationRemarks.trim();
    }

    public String getFwHangUpReason() {
        return fwHangUpReason;
    }

    public void setFwHangUpReason(String fwHangUpReason) {
        this.fwHangUpReason = fwHangUpReason == null ? null : fwHangUpReason.trim();
    }

    public Date getFwHangUpDate() {
        return fwHangUpDate;
    }

    public void setFwHangUpDate(Date fwHangUpDate) {
        this.fwHangUpDate = fwHangUpDate;
    }

    public Date getMchtArchiveConfirmDate() {
        return mchtArchiveConfirmDate;
    }

    public void setMchtArchiveConfirmDate(Date mchtArchiveConfirmDate) {
        this.mchtArchiveConfirmDate = mchtArchiveConfirmDate;
    }

    public Integer getMchtArchiveConfirmBy() {
        return mchtArchiveConfirmBy;
    }

    public void setMchtArchiveConfirmBy(Integer mchtArchiveConfirmBy) {
        this.mchtArchiveConfirmBy = mchtArchiveConfirmBy;
    }

    public String getMchtArchiveRemarks() {
        return mchtArchiveRemarks;
    }

    public void setMchtArchiveRemarks(String mchtArchiveRemarks) {
        this.mchtArchiveRemarks = mchtArchiveRemarks == null ? null : mchtArchiveRemarks.trim();
    }

    public Date getMchtArchiveRemarksDate() {
        return mchtArchiveRemarksDate;
    }

    public void setMchtArchiveRemarksDate(Date mchtArchiveRemarksDate) {
        this.mchtArchiveRemarksDate = mchtArchiveRemarksDate;
    }

    public String getKfCloseHangUpStatus() {
        return kfCloseHangUpStatus;
    }

    public void setKfCloseHangUpStatus(String kfCloseHangUpStatus) {
        this.kfCloseHangUpStatus = kfCloseHangUpStatus == null ? null : kfCloseHangUpStatus.trim();
    }

    public String getOrderConfirmStatus() {
        return orderConfirmStatus;
    }

    public void setOrderConfirmStatus(String orderConfirmStatus) {
        this.orderConfirmStatus = orderConfirmStatus == null ? null : orderConfirmStatus.trim();
    }

    public String getServiceOrderConfirmStatus() {
        return serviceOrderConfirmStatus;
    }

    public void setServiceOrderConfirmStatus(String serviceOrderConfirmStatus) {
        this.serviceOrderConfirmStatus = serviceOrderConfirmStatus == null ? null : serviceOrderConfirmStatus.trim();
    }

    public String getAppealOrderConfirmStatus() {
        return appealOrderConfirmStatus;
    }

    public void setAppealOrderConfirmStatus(String appealOrderConfirmStatus) {
        this.appealOrderConfirmStatus = appealOrderConfirmStatus == null ? null : appealOrderConfirmStatus.trim();
    }

    public String getInterventionOrderConfirmStatus() {
        return interventionOrderConfirmStatus;
    }

    public void setInterventionOrderConfirmStatus(String interventionOrderConfirmStatus) {
        this.interventionOrderConfirmStatus = interventionOrderConfirmStatus == null ? null : interventionOrderConfirmStatus.trim();
    }

    public String getThreePackagePeriod() {
        return threePackagePeriod;
    }

    public void setThreePackagePeriod(String threePackagePeriod) {
        this.threePackagePeriod = threePackagePeriod == null ? null : threePackagePeriod.trim();
    }

    public String getKfHangUpReason() {
        return kfHangUpReason;
    }

    public void setKfHangUpReason(String kfHangUpReason) {
        this.kfHangUpReason = kfHangUpReason == null ? null : kfHangUpReason.trim();
    }

    public Date getKfHangUpDate() {
        return kfHangUpDate;
    }

    public void setKfHangUpDate(Date kfHangUpDate) {
        this.kfHangUpDate = kfHangUpDate;
    }

    public Date getKfConfirmDate() {
        return kfConfirmDate;
    }

    public void setKfConfirmDate(Date kfConfirmDate) {
        this.kfConfirmDate = kfConfirmDate;
    }

    public Integer getKfConfirmBy() {
        return kfConfirmBy;
    }

    public void setKfConfirmBy(Integer kfConfirmBy) {
        this.kfConfirmBy = kfConfirmBy;
    }

    public String getKfRemarks() {
        return kfRemarks;
    }

    public void setKfRemarks(String kfRemarks) {
        this.kfRemarks = kfRemarks == null ? null : kfRemarks.trim();
    }

    public Date getKfRemarksDate() {
        return kfRemarksDate;
    }

    public void setKfRemarksDate(Date kfRemarksDate) {
        this.kfRemarksDate = kfRemarksDate;
    }

    public String getCwCloseHangUpStatus() {
        return cwCloseHangUpStatus;
    }

    public void setCwCloseHangUpStatus(String cwCloseHangUpStatus) {
        this.cwCloseHangUpStatus = cwCloseHangUpStatus == null ? null : cwCloseHangUpStatus.trim();
    }

    public String getPaymentOfGoodsConfirm() {
        return paymentOfGoodsConfirm;
    }

    public void setPaymentOfGoodsConfirm(String paymentOfGoodsConfirm) {
        this.paymentOfGoodsConfirm = paymentOfGoodsConfirm == null ? null : paymentOfGoodsConfirm.trim();
    }

    public String getCwHangUpReason() {
        return cwHangUpReason;
    }

    public void setCwHangUpReason(String cwHangUpReason) {
        this.cwHangUpReason = cwHangUpReason == null ? null : cwHangUpReason.trim();
    }

    public Date getCwConfirmDate() {
        return cwConfirmDate;
    }

    public void setCwConfirmDate(Date cwConfirmDate) {
        this.cwConfirmDate = cwConfirmDate;
    }

    public Integer getCwConfirmBy() {
        return cwConfirmBy;
    }

    public void setCwConfirmBy(Integer cwConfirmBy) {
        this.cwConfirmBy = cwConfirmBy;
    }

    public String getCwRemarks() {
        return cwRemarks;
    }

    public void setCwRemarks(String cwRemarks) {
        this.cwRemarks = cwRemarks == null ? null : cwRemarks.trim();
    }

    public Date getCwRemarksDate() {
        return cwRemarksDate;
    }

    public void setCwRemarksDate(Date cwRemarksDate) {
        this.cwRemarksDate = cwRemarksDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getEndCooperationAgreement() {
        return endCooperationAgreement;
    }

    public void setEndCooperationAgreement(String endCooperationAgreement) {
        this.endCooperationAgreement = endCooperationAgreement == null ? null : endCooperationAgreement.trim();
    }

    public Date getAgreementIssueDate() {
        return agreementIssueDate;
    }

    public void setAgreementIssueDate(Date agreementIssueDate) {
        this.agreementIssueDate = agreementIssueDate;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public Date getFwConfirmDate() {
        return fwConfirmDate;
    }

    public void setFwConfirmDate(Date fwConfirmDate) {
        this.fwConfirmDate = fwConfirmDate;
    }

    public Integer getFwConfirmBy() {
        return fwConfirmBy;
    }

    public void setFwConfirmBy(Integer fwConfirmBy) {
        this.fwConfirmBy = fwConfirmBy;
    }

    public String getFwRemarks() {
        return fwRemarks;
    }

    public void setFwRemarks(String fwRemarks) {
        this.fwRemarks = fwRemarks == null ? null : fwRemarks.trim();
    }

    public Date getFwRemarksDate() {
        return fwRemarksDate;
    }

    public void setFwRemarksDate(Date fwRemarksDate) {
        this.fwRemarksDate = fwRemarksDate;
    }

    public String getDirectorConfirmStatus() {
        return directorConfirmStatus;
    }

    public void setDirectorConfirmStatus(String directorConfirmStatus) {
        this.directorConfirmStatus = directorConfirmStatus == null ? null : directorConfirmStatus.trim();
    }

    public Date getDirectorConfirmDate() {
        return directorConfirmDate;
    }

    public void setDirectorConfirmDate(Date directorConfirmDate) {
        this.directorConfirmDate = directorConfirmDate;
    }

    public Integer getDirectorConfirmBy() {
        return directorConfirmBy;
    }

    public void setDirectorConfirmBy(Integer directorConfirmBy) {
        this.directorConfirmBy = directorConfirmBy;
    }

    public String getProductConfirmStatus() {
        return productConfirmStatus;
    }

    public void setProductConfirmStatus(String productConfirmStatus) {
        this.productConfirmStatus = productConfirmStatus == null ? null : productConfirmStatus.trim();
    }

    public Date getProductConfirmDate() {
        return productConfirmDate;
    }

    public void setProductConfirmDate(Date productConfirmDate) {
        this.productConfirmDate = productConfirmDate;
    }

    public Integer getProductConfirmBy() {
        return productConfirmBy;
    }

    public void setProductConfirmBy(Integer productConfirmBy) {
        this.productConfirmBy = productConfirmBy;
    }

    public String getSettlementAuditStatus() {
        return settlementAuditStatus;
    }

    public void setSettlementAuditStatus(String settlementAuditStatus) {
        this.settlementAuditStatus = settlementAuditStatus == null ? null : settlementAuditStatus.trim();
    }

    public Date getSettlementConfirmDate() {
        return settlementConfirmDate;
    }

    public void setSettlementConfirmDate(Date settlementConfirmDate) {
        this.settlementConfirmDate = settlementConfirmDate;
    }

    public Integer getSettlementConfirmBy() {
        return settlementConfirmBy;
    }

    public void setSettlementConfirmBy(Integer settlementConfirmBy) {
        this.settlementConfirmBy = settlementConfirmBy;
    }

    public String getDepositReturnStatus() {
        return depositReturnStatus;
    }

    public void setDepositReturnStatus(String depositReturnStatus) {
        this.depositReturnStatus = depositReturnStatus == null ? null : depositReturnStatus.trim();
    }

    public Date getDepositReturnDate() {
        return depositReturnDate;
    }

    public void setDepositReturnDate(Date depositReturnDate) {
        this.depositReturnDate = depositReturnDate;
    }

    public String getNeedPayStatus() {
        return needPayStatus;
    }

    public void setNeedPayStatus(String needPayStatus) {
        this.needPayStatus = needPayStatus == null ? null : needPayStatus.trim();
    }

    public Date getOpConfirmDate() {
        return opConfirmDate;
    }

    public void setOpConfirmDate(Date opConfirmDate) {
        this.opConfirmDate = opConfirmDate;
    }

    public Integer getOpConfirmBy() {
        return opConfirmBy;
    }

    public void setOpConfirmBy(Integer opConfirmBy) {
        this.opConfirmBy = opConfirmBy;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }

    public String getIsPlatformSend() {
        return isPlatformSend;
    }

    public void setIsPlatformSend(String isPlatformSend) {
        this.isPlatformSend = isPlatformSend == null ? null : isPlatformSend.trim();
    }

    public Date getPlatformSendDate() {
        return platformSendDate;
    }

    public void setPlatformSendDate(Date platformSendDate) {
        this.platformSendDate = platformSendDate;
    }

    public Integer getPlatformExpressId() {
        return platformExpressId;
    }

    public void setPlatformExpressId(Integer platformExpressId) {
        this.platformExpressId = platformExpressId;
    }

    public String getPlatformExpressNo() {
        return platformExpressNo;
    }

    public void setPlatformExpressNo(String platformExpressNo) {
        this.platformExpressNo = platformExpressNo == null ? null : platformExpressNo.trim();
    }

    public String getContractAuditStatus() {
        return contractAuditStatus;
    }

    public void setContractAuditStatus(String contractAuditStatus) {
        this.contractAuditStatus = contractAuditStatus == null ? null : contractAuditStatus.trim();
    }

    public Integer getContractAuditBy() {
        return contractAuditBy;
    }

    public void setContractAuditBy(Integer contractAuditBy) {
        this.contractAuditBy = contractAuditBy;
    }

    public Date getContractUploadDate() {
        return contractUploadDate;
    }

    public void setContractUploadDate(Date contractUploadDate) {
        this.contractUploadDate = contractUploadDate;
    }

    public String getInnerRemarks() {
        return innerRemarks;
    }

    public void setInnerRemarks(String innerRemarks) {
        this.innerRemarks = innerRemarks == null ? null : innerRemarks.trim();
    }

    public String getContractAuditRemarks() {
        return contractAuditRemarks;
    }

    public void setContractAuditRemarks(String contractAuditRemarks) {
        this.contractAuditRemarks = contractAuditRemarks == null ? null : contractAuditRemarks.trim();
    }

    public String getContractArchiveStatus() {
        return contractArchiveStatus;
    }

    public void setContractArchiveStatus(String contractArchiveStatus) {
        this.contractArchiveStatus = contractArchiveStatus == null ? null : contractArchiveStatus.trim();
    }

    public Integer getContractArchiveBy() {
        return contractArchiveBy;
    }

    public void setContractArchiveBy(Integer contractArchiveBy) {
        this.contractArchiveBy = contractArchiveBy;
    }

    public String getContractArchiveRemarks() {
        return contractArchiveRemarks;
    }

    public void setContractArchiveRemarks(String contractArchiveRemarks) {
        this.contractArchiveRemarks = contractArchiveRemarks == null ? null : contractArchiveRemarks.trim();
    }

    public String getBillsPath() {
        return billsPath;
    }

    public void setBillsPath(String billsPath) {
        this.billsPath = billsPath == null ? null : billsPath.trim();
    }

    public String getComfirmBillsStatus() {
        return comfirmBillsStatus;
    }

    public void setComfirmBillsStatus(String comfirmBillsStatus) {
        this.comfirmBillsStatus = comfirmBillsStatus == null ? null : comfirmBillsStatus.trim();
    }

    public BigDecimal getReturnedDepositAmount() {
        return returnedDepositAmount;
    }

    public void setReturnedDepositAmount(BigDecimal returnedDepositAmount) {
        this.returnedDepositAmount = returnedDepositAmount;
    }

    public String getIsNeedUpload() {
        return isNeedUpload;
    }

    public void setIsNeedUpload(String isNeedUpload) {
        this.isNeedUpload = isNeedUpload == null ? null : isNeedUpload.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}
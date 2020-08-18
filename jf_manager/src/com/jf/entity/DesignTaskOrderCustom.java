package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DesignTaskOrderCustom extends DesignTaskOrder {


    private String mchtCode;
    private String companyName;
    private String paymentName;
    private String shopName;
    private BigDecimal totalPayment;
    private String payStatusDesc;

    private String designerStaffName;
	private Date refundCreateDate;
	private String refundCreateBy;
	private String designTaskRefundOrderStatus;
	private String payStatusDesc1;
	private String eachDay;
	private Integer zfbCount;
    private BigDecimal zfbAmount;

    public String getEachDay() {
        return eachDay;
    }

    public void setEachDay(String eachDay) {
        this.eachDay = eachDay;
    }

    public Integer getZfbCount() {
        return zfbCount;
    }

    public void setZfbCount(Integer zfbCount) {
        this.zfbCount = zfbCount;
    }

    public BigDecimal getZfbAmount() {
        return zfbAmount;
    }

    public void setZfbAmount(BigDecimal zfbAmount) {
        this.zfbAmount = zfbAmount;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }   
    public String getDesignerStaffName() {
		return designerStaffName;
	}

	public void setDesignerStaffName(String designerStaffName) {
		this.designerStaffName = designerStaffName;
	}

	public Date getRefundCreateDate() {
		return refundCreateDate;
	}

	public void setRefundCreateDate(Date refundCreateDate) {
		this.refundCreateDate = refundCreateDate;
	}

	public String getRefundCreateBy() {
		return refundCreateBy;
	}

	public void setRefundCreateBy(String refundCreateBy) {
		this.refundCreateBy = refundCreateBy;
	}

	public String getDesignTaskRefundOrderStatus() {
		return designTaskRefundOrderStatus;
	}

	public void setDesignTaskRefundOrderStatus(String designTaskRefundOrderStatus) {
		this.designTaskRefundOrderStatus = designTaskRefundOrderStatus;
	}

	public String getPayStatusDesc1() {
		return payStatusDesc1;
	}

	public void setPayStatusDesc1(String payStatusDesc1) {
		this.payStatusDesc1 = payStatusDesc1;
	}

	public String getPaymentName() { return paymentName; }

    public void setPaymentName(String paymentName) { this.paymentName = paymentName; }

    public String getPayStatusDesc() { return payStatusDesc; }

    public void setPayStatusDesc(String payStatusDesc) { this.payStatusDesc = payStatusDesc; }


}

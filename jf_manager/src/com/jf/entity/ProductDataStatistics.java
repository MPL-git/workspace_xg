package com.jf.entity;

@SuppressWarnings("serial")
public class ProductDataStatistics extends ColumnPvHourStatistical{
    private Integer productType1Id;
    private String name;
    private String addProductRate;
    private String submitOrderRate;
    private String paymentRate;

    public Integer getProductType1Id() {
        return productType1Id;
    }

    public void setProductType1Id(Integer productType1Id) {
        this.productType1Id = productType1Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
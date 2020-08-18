package com.jf.vo.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/8/4
 */
public class GetOrderAddressPageInfoResponse {

    private Integer addressId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;

    private final List<OrderProductView> productList = Lists.newArrayList();

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<OrderProductView> getProductList() {
        return productList;
    }

}

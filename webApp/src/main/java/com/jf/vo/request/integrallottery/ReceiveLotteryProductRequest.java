package com.jf.vo.request.integrallottery;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
public class ReceiveLotteryProductRequest {

    @NotNull(message = "用户中奖记录ID不能为空")
    private Integer memberLotteryId;
    @NotNull(message = "请选择收货地址")
    private Integer memberAddressId;
    private Integer productItemId;
    private String sourceClient; //订单来源客户端 1.IOS 2.安卓 3.H5 4.微信（微信公众号）\\r\\n5 小程序 6抖音

    public String getSourceClient() {
        return sourceClient;
    }

    public void setSourceClient(String sourceClient) {
        this.sourceClient = sourceClient;
    }

    public Integer getMemberLotteryId() {
        return memberLotteryId;
    }

    public void setMemberLotteryId(Integer memberLotteryId) {
        this.memberLotteryId = memberLotteryId;
    }

    public Integer getMemberAddressId() {
        return memberAddressId;
    }

    public void setMemberAddressId(Integer memberAddressId) {
        this.memberAddressId = memberAddressId;
    }

    public Integer getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(Integer productItemId) {
        this.productItemId = productItemId;
    }
}

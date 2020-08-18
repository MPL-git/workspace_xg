package com.jf.vo.response;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2020/1/3
 */
public class AssistanceCutCompleteLogView {

    private String nickName;

    private String wxHead;

    private BigDecimal finalPrice; //成交价

    private long updateDate;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWxHead() {
        return wxHead;
    }

    public void setWxHead(String wxHead) {
        this.wxHead = wxHead;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}

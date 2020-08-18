package com.jf.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luoyb
 * Created on 2020/1/3
 */
public class CutPriceOrderDTO {

    private Integer id;
    private BigDecimal payAmount;
    private Integer memberId;
    private String memberNick;
    private String weixinHead;
    private String pic;
    private Date createDate;
    private Date updateDate;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberNick() {
        return memberNick;
    }

    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    public String getWeixinHead() {
        return weixinHead;
    }

    public void setWeixinHead(String weixinHead) {
        this.weixinHead = weixinHead;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

package com.jf.vo.response.integrallottery;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
public class MemberLotteryView {

    private Integer id;
    private String prizeName;
    private String createDate;
    private String type; //类型: 1.积分 2.优惠券 3.商品
    private Integer productId;
    private boolean received;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

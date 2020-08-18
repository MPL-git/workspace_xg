package com.jf.vo.response.integrallottery;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
public class IntegralLotteryDrawResponse {

    private boolean win;
    private Integer position;
    private Integer recordId;
    private String type; //类型: 1.积分 2.优惠券 3.商品 4.谢谢惠顾
    private String prizeTitle;
    private String prizeDesc;
    private String prizeSubDesc;
    private String shareTip;

    private Integer productId;
    private String productPic;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getPrizeTitle() {
        return prizeTitle;
    }

    public void setPrizeTitle(String prizeTitle) {
        this.prizeTitle = prizeTitle;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getPrizeDesc() {
        return prizeDesc;
    }

    public void setPrizeDesc(String prizeDesc) {
        this.prizeDesc = prizeDesc;
    }

    public String getPrizeSubDesc() {
        return prizeSubDesc;
    }

    public void setPrizeSubDesc(String prizeSubDesc) {
        this.prizeSubDesc = prizeSubDesc;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getShareTip() {
        return shareTip;
    }

    public void setShareTip(String shareTip) {
        this.shareTip = shareTip;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.jf.vo.response.couponrain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/26
 */
public class SettleCouponRainResponse {

    private Integer packCount;
    private final List<RainCouponView> couponList = Lists.newArrayList();
    private Integer informationId; //红包雨活动规则ID

    private boolean shareAble = true;
    private String shareContent;
    private String wxPath;
    private String originalId;
    private String xcxShareType;
    private String wxShareContent;
    private String wxSharePic;
    private String shareCodeUrl;
    private String nextRainTipContent;

    public boolean isShareAble() {
        return shareAble;
    }

    public void setShareAble(boolean shareAble) {
        this.shareAble = shareAble;
    }

    public String getWxShareContent() {
        return wxShareContent;
    }

    public void setWxShareContent(String wxShareContent) {
        this.wxShareContent = wxShareContent;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getXcxShareType() {
        return xcxShareType;
    }

    public void setXcxShareType(String xcxShareType) {
        this.xcxShareType = xcxShareType;
    }

    public String getWxSharePic() {
        return wxSharePic;
    }

    public void setWxSharePic(String wxSharePic) {
        this.wxSharePic = wxSharePic;
    }

    public String getWxPath() {
        return wxPath;
    }

    public void setWxPath(String wxPath) {
        this.wxPath = wxPath;
    }

    public String getShareCodeUrl() {
        return shareCodeUrl;
    }

    public void setShareCodeUrl(String shareCodeUrl) {
        this.shareCodeUrl = shareCodeUrl;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getNextRainTipContent() {
        return nextRainTipContent;
    }

    public void setNextRainTipContent(String nextRainTipContent) {
        this.nextRainTipContent = nextRainTipContent;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public Integer getPackCount() {
        return packCount;
    }

    public void setPackCount(Integer packCount) {
        this.packCount = packCount;
    }

    public List<RainCouponView> getCouponList() {
        return couponList;
    }
}

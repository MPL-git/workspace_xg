package com.jf.vo.response.integrallottery;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
public class GetIntegralLotteryInfoResponse {

    private Integer memberIntegral;
    private final List<LotterySettingsView> settingList = Lists.newArrayList();
    private Integer perIntegralSpend;
    private boolean free; //本次是否免费
    private Integer restFreeTimes; //剩余免费次数
    private Integer informationId; //规则ID
    private String privacyPolicyUrl; //协议地址（活动规则地址）
    private final List<String> prizeDescList = Lists.newArrayList(); //中奖名单

    private String wxPath;
    private String originalId;
    private String xcxShareType;
    private String wxShareContent;
    private String wxSharePic;

    public Integer getRestFreeTimes() {
        return restFreeTimes;
    }

    public void setRestFreeTimes(Integer restFreeTimes) {
        this.restFreeTimes = restFreeTimes;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getWxPath() {
        return wxPath;
    }

    public void setWxPath(String wxPath) {
        this.wxPath = wxPath;
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

    public String getWxShareContent() {
        return wxShareContent;
    }

    public void setWxShareContent(String wxShareContent) {
        this.wxShareContent = wxShareContent;
    }

    public String getWxSharePic() {
        return wxSharePic;
    }

    public void setWxSharePic(String wxSharePic) {
        this.wxSharePic = wxSharePic;
    }

    public List<LotterySettingsView> getSettingList() {
        return settingList;
    }

    public Integer getPerIntegralSpend() {
        return perIntegralSpend;
    }

    public void setPerIntegralSpend(Integer perIntegralSpend) {
        this.perIntegralSpend = perIntegralSpend;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public List<String> getPrizeDescList() {
        return prizeDescList;
    }

    public Integer getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(Integer memberIntegral) {
        this.memberIntegral = memberIntegral;
    }
}

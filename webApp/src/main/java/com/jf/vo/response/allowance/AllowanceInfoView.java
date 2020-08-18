package com.jf.vo.response.allowance;

/**
 * @author luoyb
 * Created on 2020/5/27
 */
public class AllowanceInfoView {

    private Integer id;
    private String rule;
    private Long usageBeginTime;
    private Long usageEndTime;
    private Long sysCurrentTime;
    private boolean usable;
    private String useTip;

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getUseTip() {
        return useTip;
    }

    public void setUseTip(String useTip) {
        this.useTip = useTip;
    }

    public Long getSysCurrentTime() {
        return sysCurrentTime;
    }

    public void setSysCurrentTime(Long sysCurrentTime) {
        this.sysCurrentTime = sysCurrentTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Long getUsageBeginTime() {
        return usageBeginTime;
    }

    public void setUsageBeginTime(Long usageBeginTime) {
        this.usageBeginTime = usageBeginTime;
    }

    public Long getUsageEndTime() {
        return usageEndTime;
    }

    public void setUsageEndTime(Long usageEndTime) {
        this.usageEndTime = usageEndTime;
    }
}

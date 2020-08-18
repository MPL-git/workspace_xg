package com.jf.entity;

public class ToutiaoAdInfoWithBLOBs extends ToutiaoAdInfo {
    private String scheduleTime;

    private String audience;

    private String creativeDtl;

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime == null ? null : scheduleTime.trim();
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience == null ? null : audience.trim();
    }

    public String getCreativeDtl() {
        return creativeDtl;
    }

    public void setCreativeDtl(String creativeDtl) {
        this.creativeDtl = creativeDtl == null ? null : creativeDtl.trim();
    }
}
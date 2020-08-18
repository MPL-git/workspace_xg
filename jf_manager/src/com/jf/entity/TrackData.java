package com.jf.entity;

import java.util.Date;

public class TrackData {
    private Integer id;

    private String type;

    private String spreadurl;

    private String spreadname;

    private String activityname;

    private String channel;

    private String firstspreadname;

    private String firstchannel;

    private Date clicktime;

    private String ua;

    private String uip;

    private String appkey;

    private Date activetime;

    private String osversion;

    private String devicetype;

    private String idfa;

    private String mac;

    private String imei;

    private String skey;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSpreadurl() {
        return spreadurl;
    }

    public void setSpreadurl(String spreadurl) {
        this.spreadurl = spreadurl == null ? null : spreadurl.trim();
    }

    public String getSpreadname() {
        return spreadname;
    }

    public void setSpreadname(String spreadname) {
        this.spreadname = spreadname == null ? null : spreadname.trim();
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getFirstspreadname() {
        return firstspreadname;
    }

    public void setFirstspreadname(String firstspreadname) {
        this.firstspreadname = firstspreadname == null ? null : firstspreadname.trim();
    }

    public String getFirstchannel() {
        return firstchannel;
    }

    public void setFirstchannel(String firstchannel) {
        this.firstchannel = firstchannel == null ? null : firstchannel.trim();
    }

    public Date getClicktime() {
        return clicktime;
    }

    public void setClicktime(Date clicktime) {
        this.clicktime = clicktime;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua == null ? null : ua.trim();
    }

    public String getUip() {
        return uip;
    }

    public void setUip(String uip) {
        this.uip = uip == null ? null : uip.trim();
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public Date getActivetime() {
        return activetime;
    }

    public void setActivetime(Date activetime) {
        this.activetime = activetime;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion == null ? null : osversion.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa == null ? null : idfa.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey == null ? null : skey.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}
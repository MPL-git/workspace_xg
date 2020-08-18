package com.jf.vo;

import com.jf.entity.SvipPracticeRecord;

/**
 * @author luoyb
 * Created on 2019/9/30
 */
public class SVipInfo {

    private boolean isSVip; //可能为试用svip
    private boolean isRealSVip;
    private boolean isTrail;

    private SvipPracticeRecord svipPracticeRecord;

    public SVipInfo() {
    }

    public static SVipInfo real() {
        SVipInfo sVipInfo = new SVipInfo();
        sVipInfo.setSVip(true);
        sVipInfo.setRealSVip(true);
        return sVipInfo;
    }

    public static SVipInfo trail(SvipPracticeRecord svipPracticeRecord) {
        SVipInfo sVipInfo = new SVipInfo();
        sVipInfo.setSVip(true);
        sVipInfo.setTrail(true);
        sVipInfo.setSvipPracticeRecord(svipPracticeRecord);
        return sVipInfo;
    }

    public static SVipInfo no() {
        return new SVipInfo();
    }

    public SvipPracticeRecord getSvipPracticeRecord() {
        return svipPracticeRecord;
    }

    public void setSvipPracticeRecord(SvipPracticeRecord svipPracticeRecord) {
        this.svipPracticeRecord = svipPracticeRecord;
    }

    public boolean isSVip() {
        return isSVip;
    }

    public void setSVip(boolean SVip) {
        isSVip = SVip;
    }

    public boolean isTrail() {
        return isTrail;
    }

    public void setTrail(boolean trail) {
        isTrail = trail;
    }

    public boolean isRealSVip() {
        return isRealSVip;
    }

    public void setRealSVip(boolean realSVip) {
        isRealSVip = realSVip;
    }
}

package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-09-29 下午 6:17
 */
public class SvipPracticeRecordCustom extends SvipPracticeRecord {

    private String nick;
    private String statusDesc;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}

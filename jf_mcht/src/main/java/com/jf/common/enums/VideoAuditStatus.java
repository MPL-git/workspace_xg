package com.jf.common.enums;

/**
 * @author luoyb
 * Created on 2019/9/16
 */
public enum VideoAuditStatus {

//    WAIT_COMMIT(1, "待提审"),
//    WAIT_AUDIT(2, "待审核"),
//    AUDIT_PASS(3, "通过"),
//    AUDIT_REJECT(4, "驳回"),

    WAIT_COMMIT(1, "待提审"),
    WAIT_AUDIT(2, "待审核"),
    BUS_AUDIT_PASS(3,"审核中"),
    BUS_AUDIT_REJECT(4, "业务审核驳回"),
    LAW_AUDIT_PASS(5,"通过"),
    LAW_AUDIT_REJECT(6, "法务审核驳回"),

    //
    ;

    private final int value;
    private final String name;

    VideoAuditStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}

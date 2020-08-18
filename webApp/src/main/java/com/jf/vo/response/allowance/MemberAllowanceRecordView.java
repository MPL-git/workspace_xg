package com.jf.vo.response.allowance;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
public class MemberAllowanceRecordView {

    private String name;
    private String createDateDesc;
    private String amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDateDesc() {
        return createDateDesc;
    }

    public void setCreateDateDesc(String createDateDesc) {
        this.createDateDesc = createDateDesc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

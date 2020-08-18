package com.jf.vo.response.allowance;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
public class FindMemberAllowanceResponse {

    private String totalAllowance;

    private List<MemberAllowanceRecordView> list;

    public String getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(String totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public List<MemberAllowanceRecordView> getList() {
        return list;
    }

    public void setList(List<MemberAllowanceRecordView> list) {
        this.list = list;
    }
}

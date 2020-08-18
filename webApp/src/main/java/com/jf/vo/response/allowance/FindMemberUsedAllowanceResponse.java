package com.jf.vo.response.allowance;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
public class FindMemberUsedAllowanceResponse {

    private List<MemberAllowanceRecordView> list;

    public List<MemberAllowanceRecordView> getList() {
        return list;
    }

    public void setList(List<MemberAllowanceRecordView> list) {
        this.list = list;
    }
}

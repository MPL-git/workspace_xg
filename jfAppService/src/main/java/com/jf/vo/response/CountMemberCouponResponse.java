package com.jf.vo.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
public class CountMemberCouponResponse {

    private final List<MemberCouponCountView> dataList= Lists.newArrayList();

    public List<MemberCouponCountView> getDataList() {
        return dataList;
    }

}

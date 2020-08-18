package com.jf.vo.response;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
public class FindMemberCouponResponse {

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private final List<Map<String, Object>> dataList = Lists.newArrayList();

}

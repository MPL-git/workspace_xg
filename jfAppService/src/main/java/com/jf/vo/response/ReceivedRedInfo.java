package com.jf.vo.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/3/17
 */
public class ReceivedRedInfo {

    private boolean received;

    private final List<Integer> receivedCouponIds = Lists.newArrayList(); //已领取的优惠券ID

    private ReceivedRedInfo() {
    }

    public static ReceivedRedInfo yes() {
        ReceivedRedInfo info = new ReceivedRedInfo();
        info.setReceived(true);
        return info;
    }

    public static ReceivedRedInfo no() {
        ReceivedRedInfo info = new ReceivedRedInfo();
        info.setReceived(false);
        return info;
    }

    public ReceivedRedInfo putAllReceived(List<Integer> couponIds) {
        if (couponIds != null && couponIds.size() != 0) {
            this.receivedCouponIds.addAll(couponIds);
        }
        return this;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public List<Integer> getReceivedCouponIds() {
        return receivedCouponIds;
    }
}

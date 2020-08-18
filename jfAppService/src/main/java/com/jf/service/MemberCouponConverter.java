package com.jf.service;

import com.jf.entity.dto.MemberCouponCountDTO;
import com.jf.entity.dto.MemberCouponCountV2DTO;
import com.jf.vo.response.CountMemberCouponResponse;
import com.jf.vo.response.MemberCouponCountView;
import org.springframework.stereotype.Service;


/**
 * @author luoyb
 * Created on 2019/12/7
 */
@Service
public class MemberCouponConverter {

    public CountMemberCouponResponse buildCountMemberCouponResponse(MemberCouponCountDTO dto) {
        CountMemberCouponResponse response = new CountMemberCouponResponse();
        response.getDataList().add(buildMemberCouponCountView(1, "平台优惠券", dto == null ? 0 : dto.getPlatformCouponCount()));
        response.getDataList().add(buildMemberCouponCountView(2, "其他优惠券", dto == null ? 0 : dto.getNotPlatformCouponCount()));
        return response;
    }

    public CountMemberCouponResponse buildCountMemberCouponResponseV2(MemberCouponCountV2DTO dto) {
        CountMemberCouponResponse response = new CountMemberCouponResponse();
        response.getDataList().add(buildMemberCouponCountView(0, "全部", dto == null ? 0 : sumCount(dto)));
        response.getDataList().add(buildMemberCouponCountView(1, "平台券", dto == null ? 0 : dto.getPlatformCouponCount()));
        response.getDataList().add(buildMemberCouponCountView(2, "专场券", dto == null ? 0 : dto.getAreaCouponCount()));
        response.getDataList().add(buildMemberCouponCountView(3, "商家券", dto == null ? 0 : dto.getShopCouponCount()));
        return response;
    }

    private int sumCount(MemberCouponCountV2DTO dto) {
        int sum = 0;
        if (dto.getPlatformCouponCount() != null) {
            sum += dto.getPlatformCouponCount();
        }
        if (dto.getAreaCouponCount() != null) {
            sum += dto.getAreaCouponCount();
        }
        if (dto.getShopCouponCount() != null) {
            sum += dto.getShopCouponCount();
        }
        return sum;
    }

    private MemberCouponCountView buildMemberCouponCountView(Integer type, String name, Integer count) {
        MemberCouponCountView view = new MemberCouponCountView();
        view.setType(type);
        view.setName(name);
        view.setCount(count);
        return view;
    }
}

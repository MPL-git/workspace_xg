package com.jf.service.couponrain;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Coupon;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.dto.CouponRainDTO;
import com.jf.vo.response.couponrain.CouponRainView;
import com.jf.vo.response.couponrain.GetCouponRainResponse;
import com.jf.vo.response.couponrain.RainCouponView;
import com.jf.vo.response.couponrain.SettleCouponRainResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/12/12
 */
@Service
public class CouponRainConverter {

    public GetCouponRainResponse buildCouponRainResponse(CouponRainDTO couponRain, CouponRainSetup couponRainSetup) {
        GetCouponRainResponse response = new GetCouponRainResponse();
        if (couponRain == null || couponRainSetup == null) {
            response.setNoRainTip("该活动已结束，期待下次活动！");
        } else if (couponRain.getJoinCount() == null || (couponRain.getJoinCount() == 1 && couponRain.getShareCount() != null)) {
            response.setRain(true);
            response.setCouponRain(toCouponRainView(couponRain, couponRainSetup.getPic()));
            String rainType = "P";
            if (!StringUtil.isBlank(couponRainSetup.getRedEnvelopeType())) {
                rainType = couponRainSetup.getRedEnvelopeType();
            }
            response.setRainType(rainType);
        } else {
            response.setNoRainTip("已参与过本场红包雨");
        }
        return response;

    }

    private CouponRainView toCouponRainView(CouponRain couponRain, String rainPic) {
        CouponRainView view = new CouponRainView();
        view.setId(couponRain.getId());
        view.setPic(StringUtil.getPic(rainPic, ""));
        view.setStartTime(couponRain.getStartTime().getTime());
        view.setEndTime(couponRain.getEndTime().getTime());
        view.setGameSeconds(couponRain.getGameSeconds());
        view.setBombPercent(String.valueOf(couponRain.getBombPercent()));
        view.setStatus(couponRain.getStatus());
        return view;
    }

    public SettleCouponRainResponse buildSettleCouponRainResponse(CouponRainSettleContext context, CouponRainDTO nextRain, boolean shareAble) {
        SettleCouponRainResponse response = new SettleCouponRainResponse();
        response.setPackCount(context.getRecPackCount());
        fillWithIntegral(response, context.getIntegral());
        fillWithCoupon(response, context);
        response.setInformationId(261); //红包雨活动规则对应的informationId
        response.setPrivacyPolicyUrl(StringUtil.buildMsg("{}/xgbuy/views/index.html?redirect_url=link/information.html?id={}", Config.getProperty("mUrl"), 261)); //协议地址

        response.setShareAble(shareAble);
        response.setShareContent("分享游戏，可再获得一次机会");
        response.setWxPath("page/index/index");
        response.setOriginalId(Config.getProperty("originalId"));
        response.setXcxShareType(Config.getProperty("xcxShareType"));
        response.setWxShareContent("");
        response.setWxSharePic(StringUtil.getPic("/app/201911/hby_share.png", "")); //红包雨分享红包图片
        response.setShareCodeUrl(StringUtil.getPic("/app/201911/hby.png", "")); //红包雨分享二维码地址
        if (nextRain == null) {
            response.setNextRainTipContent("该活动已结束，期待下次活动！");
        } else if (nextRain.getJoinCount() == null || (nextRain.getJoinCount() == 1 && nextRain.getShareCount() != null)) {
            String nextRainBeginTime = DateUtil.getFormatDate(nextRain.getStartTime(), "yyyy.MM.dd HH:mm");
            response.setNextRainTipContent(StringUtil.buildMsg("下一场红包雨开始时间：{}", nextRainBeginTime));
        } else{
            response.setNextRainTipContent("该活动已结束，期待下次活动！");
        }
        return response;
    }

    private void fillWithCoupon(SettleCouponRainResponse response, CouponRainSettleContext context) {
        //商品券
        if (!CollectionUtils.isEmpty(context.getProductCouponList())) {
            response.getCouponList().addAll(FluentIterable.from(context.getProductCouponList()).transform(new Function<Coupon, RainCouponView>() {
                @Override
                public RainCouponView apply(Coupon input) {
                    RainCouponView view = new RainCouponView();
                    view.setType(2);
                    view.setAmountType(2);
                    view.setDenomination(NumberUtil.decorateDenomination(input.getMoney()));
                    view.setDisplayName("红包雨-专属商品券");
                    return view;
                }
            }).toList());
        }

        //其他券
        List<Coupon> list = Lists.newArrayList(context.getPlatformCouponList());
        list.addAll(context.getAreaCouponList());
        Collections.sort(list, sortCoupon()); //排序先后：满减、折扣
        if (!CollectionUtils.isEmpty(list)) {
            response.getCouponList().addAll(FluentIterable.from(list).transform(new Function<Coupon, RainCouponView>() {
                @Override
                public RainCouponView apply(Coupon input) {
                    RainCouponView view = new RainCouponView();
                    String preferentialType = input.getPreferentialType();
                    view.setType(3);
                    if ("1".equals(preferentialType)) { //固定面额
                        view.setAmountType(2);
                        view.setDenomination(NumberUtil.decorateDenomination(input.getMoney()));
                    } else { //折扣
                        view.setAmountType(3);
                        view.setDiscount(NumberUtil.decorateDiscount(input.getDiscount()));
                    }
                    if (StringUtils.hasText(input.getDefinitionPrefix())) {
                        view.setDisplayName(StringUtil.buildMsg("{}-{}", input.getDefinitionPrefix(), input.getName()));
                    } else {
                        view.setDisplayName(input.getName());
                    }
                    return view;
                }
            }).toList());
        }

    }

    private Comparator<Coupon> sortCoupon() {
        return new Comparator<Coupon>() {
            @Override
            public int compare(Coupon o1, Coupon o2) {
                if (o1.getPreferentialType().equals(o2.getPreferentialType())) {
                    return 0;
                }
                if ("1".equals(o1.getPreferentialType())) {
                    return -1;
                }
                return 1;
            }
        };
    }

    private void fillWithIntegral(SettleCouponRainResponse response, int integral) {
        if (integral > 0) {
            RainCouponView view = new RainCouponView();
            view.setType(1);
            view.setAmountType(1);
            view.setIntegral(integral);
            view.setDisplayName(StringUtil.buildMsg("红包雨-{}积分", integral));
            response.getCouponList().add(view);
        }
    }

}

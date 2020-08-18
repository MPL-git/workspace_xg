package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.controller.base.BaseController;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainSetup;
import com.jf.service.couponrain.CouponRainConverter;
import com.jf.service.couponrain.CouponRainService;
import com.jf.service.couponrain.CouponRainSettleContext;
import com.jf.vo.request.couponrain.GetCouponRainRequest;
import com.jf.vo.request.couponrain.LogCouponRainShareRequest;
import com.jf.vo.request.couponrain.ParticipateCouponRainRequest;
import com.jf.vo.request.couponrain.SettleCouponRainRequest;
import com.jf.vo.response.couponrain.GetCouponRainResponse;
import com.jf.vo.response.couponrain.LogCouponRainShareResponse;
import com.jf.vo.response.couponrain.ParticipateResponse;
import com.jf.vo.response.couponrain.SettleCouponRainResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author luoyb
 * Created on 2019/9/26
 */
@Controller
public class CouponRainController extends BaseController {

    @Autowired
    private AppContext appContext;
    @Autowired
    private CouponRainService couponRainService;
    @Autowired
    private CouponRainConverter couponRainConverter;

    /**
     * 红包雨 获取即将开始的红包雨
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/couponRain", method = RequestMethod.POST)
    public ResponseMsg getCouponRain() {
        GetCouponRainRequest request = appContext.getRequestAndValidate(GetCouponRainRequest.class);
        CouponRain rain = couponRainService.getEnableFirstRain(request.getMemberId());
        CouponRainSetup couponRainSetup = null;
        if (rain != null) {
            couponRainSetup = couponRainService.getCouponRainSetup(rain.getCouponRainSetupId());
        }
        GetCouponRainResponse response = couponRainConverter.buildCouponRainResponse(rain, couponRainSetup);
        response.setSystemCurrentTime(System.currentTimeMillis());
        return ResponseMsg.success(response);
    }

    /**
     * 红包雨 用户参与红包雨
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/couponRain/participate", method = RequestMethod.POST)
    public ResponseMsg participate() {
        ParticipateCouponRainRequest request = appContext.getRequestAndValidate(ParticipateCouponRainRequest.class);
        ParticipateResponse response = couponRainService.participate(request.getCouponRainId(), request.getMemberId());
        return ResponseMsg.success(response);
    }

    /**
     * 红包雨 结算
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/couponRain/settle", method = RequestMethod.POST)
    public ResponseMsg couponRainSettle() {
        SettleCouponRainRequest request = appContext.getRequestAndValidate(SettleCouponRainRequest.class);
        CouponRainSettleContext context = couponRainService.settle(request.getMemberId(), request.getRecordId(), request.getScore());
        CouponRain nextRain = couponRainService.getEnableFirstRain(request.getMemberId());
        boolean shareAble = couponRainService.checkMemberShareAble(request.getMemberId(), context.getCouponRain().getId());
        SettleCouponRainResponse response = couponRainConverter.buildSettleCouponRainResponse(context, nextRain, shareAble);
        return ResponseMsg.success(response);
    }

    /**
     * 红包雨 记录分享
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/couponRain/logShare", method = RequestMethod.POST)
    public ResponseMsg logShareRecord() {
        LogCouponRainShareRequest request = appContext.getRequestAndValidate(LogCouponRainShareRequest.class);
        boolean gameAgainAble = couponRainService.logShareRecord(request.getMemberId(), request.getCouponRainId());
        LogCouponRainShareResponse response = new LogCouponRainShareResponse();
        response.setGameAgainAble(gameAgainAble);
        return ResponseMsg.success(response);
    }
}

package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.dto.CouponRainDTO;
import com.jf.service.couponrain.CouponRainConverter;
import com.jf.service.couponrain.CouponRainService;
import com.jf.service.couponrain.CouponRainSettleContext;
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

import javax.servlet.http.HttpServletRequest;

/**
 * @author luoyb
 * Created on 2019/12/12
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
    public ResponseMsg getCouponRain(HttpServletRequest request) {
        String system = appContext.reqPRM().optString("system");
        boolean fromApp = Const.ANDROID.equalsIgnoreCase(system) || Const.IOS.equalsIgnoreCase(system);
        CouponRainDTO rain = couponRainService.getEnableFirstRain(getMemberId(request), fromApp);
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
    public ResponseMsg participate(HttpServletRequest httpServletRequest) {
        ParticipateCouponRainRequest request = appContext.getRequestAndValidate(ParticipateCouponRainRequest.class);
        ParticipateResponse response = couponRainService.participate(request.getCouponRainId(), getMemberId(httpServletRequest));
        return ResponseMsg.success(response);
    }

    /**
     * 红包雨 结算
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/couponRain/settle", method = RequestMethod.POST)
    public ResponseMsg couponRainSettle(HttpServletRequest httpServletRequest) {
        SettleCouponRainRequest request = appContext.getRequestAndValidate(SettleCouponRainRequest.class);
        String system = appContext.reqPRM().optString("system");
        boolean fromApp = Const.ANDROID.equalsIgnoreCase(system) || Const.IOS.equalsIgnoreCase(system);
        Integer memberId = getMemberId(httpServletRequest);

        CouponRainSettleContext context = couponRainService.settle(memberId, request.getRecordId(), request.getScore());
        CouponRainDTO nextRain = couponRainService.getEnableFirstRain(memberId, fromApp);
        boolean shareAble = couponRainService.checkMemberShareAble(memberId, context.getCouponRain().getId());
        SettleCouponRainResponse response = couponRainConverter.buildSettleCouponRainResponse(context, nextRain, shareAble);
        return ResponseMsg.success(response);
    }

    /**
     * 红包雨 记录分享
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/couponRain/logShare", method = RequestMethod.POST)
    public ResponseMsg logShareRecord(HttpServletRequest httpServletRequest) {
        LogCouponRainShareRequest request = appContext.getRequestAndValidate(LogCouponRainShareRequest.class);
        boolean gameAgainAble = couponRainService.logShareRecord(getMemberId(httpServletRequest), request.getCouponRainId());
        LogCouponRainShareResponse response = new LogCouponRainShareResponse();
        response.setGameAgainAble(gameAgainAble);
        return ResponseMsg.success(response);
    }

}

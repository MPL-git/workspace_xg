package com.jf.controller.async;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.CouponRainSetup;
import com.jf.service.async.CouponRainAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author luoyb
 * Created on 2019/11/20
 */
@Controller
public class CouponRainAsyncController extends BaseController {

    @Autowired
    private CouponRainAsyncService couponRainAsyncService;

    /**
     * 添加日期:2019-11-20
     * 来自需求：1779
     * 初始化旧数据：
     * 1、红包雨couponRainSetup图片配置，取自各自第一条couponRain的pic
     * 2、IncludeSale相关配置
     * 3、IncludePlatformCoupon相关配置
     */
    @ResponseBody
    @RequestMapping(value = "/async/couponRain/pic/init", method = RequestMethod.PUT)
    public ResponseMsg asyncOld() {
        List<CouponRainSetup> setupList = couponRainAsyncService.findSetupWithPicIsEmpty();
        if (CollectionUtils.isEmpty(setupList)) {
            return ResponseMsg.success("不存在需要修复的红包雨配置");
        }

        for (CouponRainSetup couponRainSetup : setupList) {
            couponRainAsyncService.initSetupPic(couponRainSetup);
        }
        return ResponseMsg.success(StringUtil.buildMsg("成功修复红包雨配置{}条", setupList.size()));
    }

}

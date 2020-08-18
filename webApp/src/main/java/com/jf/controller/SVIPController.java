package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.service.SVipPracticeInfoService;
import com.jf.vo.request.CheckRecSvipPracticeRequest;
import com.jf.vo.request.RecSvipPracticeRequest;
import com.jf.vo.response.CheckRecSvipPracticeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luoyb
 * Created on 2019/10/8
 */
@Controller
public class SVIPController extends BaseController {

    @Autowired
    private AppContext appContext;
    @Autowired
    private SVipPracticeInfoService sVipPracticeInfoService;

    /**
     *  SVIP体验卡领取
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/svipPractice/rec", method = RequestMethod.POST)
    public ResponseMsg recSvipPractice() {
        RecSvipPracticeRequest request = appContext.getRequestAndValidate(RecSvipPracticeRequest.class);
        sVipPracticeInfoService.recPractice(request.getMemberId());
        return ResponseMsg.success();
    }

    /**
     * 判断用户是否已领取SVIP体验卡
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/svipPractice/checkRec",method = RequestMethod.POST)
    public ResponseMsg checkRecSvipPractice() {
        CheckRecSvipPracticeRequest request = appContext.getRequestAndValidate(CheckRecSvipPracticeRequest.class);
        boolean hadRec = sVipPracticeInfoService.hadRecSvipPractice(request.getMemberId());
        return ResponseMsg.success(new CheckRecSvipPracticeResponse(hadRec));
    }
}

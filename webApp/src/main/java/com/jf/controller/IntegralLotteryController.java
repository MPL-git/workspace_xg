package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.service.integrallottery.DrawContext;
import com.jf.service.integrallottery.IntegralLotteryService;
import com.jf.service.integrallottery.MemberLotteryService;
import com.jf.service.integrallottery.ReceiveLotteryProductService;
import com.jf.vo.request.PageRequest;
import com.jf.vo.request.integrallottery.IntegralLotteryDrawRequest;
import com.jf.vo.request.integrallottery.ReceiveLotteryProductRequest;
import com.jf.vo.response.integrallottery.GetIntegralLotteryInfoResponse;
import com.jf.vo.response.integrallottery.IntegralLotteryDrawResponse;
import com.jf.vo.response.integrallottery.MemberLotteryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 积分转盘（积分抽奖）
 *
 * @author luoyb
 * Created on 2020/7/21
 */
@Controller
public class IntegralLotteryController extends BaseController {

    @Autowired
    private AppContext appContext;
    @Autowired
    private IntegralLotteryService integralLotteryService;
    @Autowired
    private MemberLotteryService memberLotteryService;
    @Autowired
    private ReceiveLotteryProductService receiveLotteryProductService;

    /**
     * 转盘信息
     */
    @ResponseBody
    @RequestMapping(value = "api/n/integralLottery/info", method = RequestMethod.POST)
    public ResponseMsg integralLotteryInfo() {
        GetIntegralLotteryInfoResponse response = integralLotteryService.getIntegralLotteryInfo(getMemberId());
        return ResponseMsg.success(response);
    }

    /**
     * 添加分享记录
     */
    @ResponseBody
    @RequestMapping(value = "api/y/integralLottery/logShare", method = RequestMethod.POST)
    public ResponseMsg logShare() {
        integralLotteryService.logShare(getMemberId());
        return ResponseMsg.success();
    }

    /**
     * 抽奖
     */
    @ResponseBody
    @RequestMapping(value = "api/y/integralLottery/draw", method = RequestMethod.POST)
    public ResponseMsg draw() {
        IntegralLotteryDrawRequest request = appContext.getRequestAndValidate(IntegralLotteryDrawRequest.class);
        DrawContext context = integralLotteryService.validateBeforeDraw(request.getPageSettingsList(), request.getFree() == 1, getMemberId());
        IntegralLotteryDrawResponse response = integralLotteryService.draw(context);
        if ("3".equals(response.getType())) {
            integralLotteryService.reorderLotteryProduct(response.getProductId());
        }
        return ResponseMsg.success(response);
    }

    /**
     * 我的中奖记录 列表查询
     */
    @ResponseBody
    @RequestMapping(value = "api/y/memberLottery/list", method = RequestMethod.POST)
    public ResponseMsg findMemberLottery() {
        PageRequest pageRequest = appContext.getRequestAndValidate(PageRequest.class);
        pageRequest.setPageSize(8);
        List<MemberLotteryView> viewList = memberLotteryService.findMemberLottery(getMemberId(), pageRequest);
        return ResponseMsg.success(viewList, pageRequest.getPageSize());
    }

    /**
     * 领取中奖商品
     */
    @ResponseBody
    @RequestMapping(value = "api/y/memberLottery/receiveProduct", method = RequestMethod.POST)
    public ResponseMsg receiveProduct() {
        ReceiveLotteryProductRequest request = appContext.getRequestAndValidate(ReceiveLotteryProductRequest.class);
        receiveLotteryProductService.receiveProduct(request, getMemberId());
        return ResponseMsg.success();
    }

}

package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.NumberUtil;
import com.jf.service.allowance.AllowanceService;
import com.jf.vo.request.PageRequest;
import com.jf.vo.request.allowance.ExchangeAllowanceRequest;
import com.jf.vo.request.allowance.FindAreaProductListRequest;
import com.jf.vo.request.allowance.GetAllowanceInfoRequest;
import com.jf.vo.request.allowance.GetAllowancePopInfoRequest;
import com.jf.vo.response.allowance.ExchangeAllowanceResponse;
import com.jf.vo.response.allowance.FindMemberAllowanceResponse;
import com.jf.vo.response.allowance.FindMemberUsedAllowanceResponse;
import com.jf.vo.response.allowance.GetAllowanceDetailResponse;
import com.jf.vo.response.allowance.GetAllowanceInfoResponse;
import com.jf.vo.response.allowance.GetAllowancePopInfoResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 购物津贴
 *
 * @author luoyb
 * Created on 2020/5/25
 */
@Controller
public class AllowanceController extends BaseController {

    @Autowired
    private AppContext appContext;
    @Autowired
    private AllowanceService allowanceService;

    /**
     * 会场津贴浮窗信息 查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/n/allowance/popInfo", method = RequestMethod.POST)
    public ResponseMsg getAllowancePopInfo() {
        GetAllowancePopInfoRequest request = appContext.getRequestAndValidate(GetAllowancePopInfoRequest.class);
        GetAllowancePopInfoResponse response = allowanceService.getAllowancePopInfo(request.getActivityAreaId());
        return ResponseMsg.success(response);
    }

    /**
     * 主会场津贴领取页信息 查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/allowance/info", method = RequestMethod.POST)
    public ResponseMsg getAllowanceInfo() {
        GetAllowanceInfoRequest request = appContext.getRequestAndValidate(GetAllowanceInfoRequest.class);
        GetAllowanceInfoResponse response = allowanceService.getAllowanceInfo(request.getActivityAreaId(), getMemberId());
        return ResponseMsg.success(response);
    }

    /**
     * 津贴兑换
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/allowance/exchange", method = RequestMethod.POST)
    public ResponseMsg exchangeAllowance() {
        ExchangeAllowanceRequest request = appContext.getRequestAndValidate(ExchangeAllowanceRequest.class);
        ExchangeAllowanceResponse response = allowanceService.exchangeAllowance(request.getAllowanceInfoId(), request.getId(), getMemberId());
        return ResponseMsg.success(response);
    }

    /**
     * 津贴详情页 查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/allowance/detail", method = RequestMethod.POST)
    public ResponseMsg getAllowanceDetail() {
        GetAllowanceDetailResponse response = allowanceService.getAllowanceDetail(getMemberId());
        return ResponseMsg.success(response);
    }

    /**
     * 用户已领取津贴 查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/memberAllowance/list", method = RequestMethod.POST)
    public ResponseMsg findMemberAllowance() {
        PageRequest request = appContext.getRequestAndValidate(PageRequest.class);

        FindMemberAllowanceResponse response = new FindMemberAllowanceResponse();
        response.setList(allowanceService.findMemberAllowance(getMemberId(), request));
        response.setTotalAllowance(NumberUtil.decorateDenomination(allowanceService.getMemberTotalAllowance(getMemberId())));
        return ResponseMsg.success(response);
    }

    /**
     * 用户已使用津贴 查询
     */
    @ResponseBody
    @RequestMapping(value = "/api/y/memberUsedAllowance/list", method = RequestMethod.POST)
    public ResponseMsg findMemberUsedAllowance() {
        PageRequest request = appContext.getRequestAndValidate(PageRequest.class);

        FindMemberUsedAllowanceResponse response = new FindMemberUsedAllowanceResponse();
        response.setList(allowanceService.findMemberUsedAllowance(getMemberId(), request));
        return ResponseMsg.success(response);
    }

    /**
     * 1、（旧版）津贴会场推荐商品（随机10个商品） 查询
     * 2、（新版）津贴会场推荐商品（按一级类目10个商品） 查询
     */
    @RequestMapping(value = "/api/n/areaProduct/list")
    @ResponseBody
    public ResponseMsg findAreaProductList() {
        FindAreaProductListRequest request = appContext.getRequestAndValidate(FindAreaProductListRequest.class);
        Map<String, Object> returnData = allowanceService.findAreaProductList(request);
        return ResponseMsg.success(returnData);
    }
}

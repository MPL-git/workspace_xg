package com.jf.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.common.AppContext;
import com.jf.service.SVipPracticeInfoService;
import com.jf.vo.request.CheckRecSvipPracticeRequest;
import com.jf.vo.request.RecSvipPracticeRequest;
import com.jf.vo.response.CheckRecSvipPracticeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.controller.base.BaseController;
import com.jf.service.IntegralDtlService;
import com.jf.service.OrderDtlService;
import com.jf.service.SvipService;

import net.sf.json.JSONObject;
/**
 * SVIP超级会员
 * @author chenwf
 *
 */
@Controller
public class SVIPController extends BaseController{
	@Resource
	private SvipService svipService;
	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private IntegralDtlService integralDtlService;
    @Autowired
    private AppContext appContext;
    @Autowired
    private SVipPracticeInfoService sVipPracticeInfoService;

	/**
	 *
	 * 方法描述 ：SVIP开通页面
	 * @author  chenwf:
	 * @date 创建时间：2019年03月05日 下午5:03:20
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getPurchaseSvipPage")
	@ResponseBody
	public ResponseMsg getPurchaseSvipPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			Map<String, Object> map = svipService.getPurchaseSvipPage(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}


	/**
	 *
	 * 方法描述 ：SVIP会员下单节省列表
	 * @author  chenwf:
	 * @date 创建时间：2019年03月05日 下午5:03:20
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getSvipSaveAmountOrderList")
	@ResponseBody
	public ResponseMsg getSvipSaveAmountOrderList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"memberId","currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = orderDtlService.getSvipSaveAmountOrderList(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：SVIP会员领取每月积分
	 * @author  chenwf:
	 * @date 创建时间：2019年03月05日 下午5:03:20
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/svipRecMonthIntegral")
	@ResponseBody
	public ResponseMsg svipRecMonthIntegral(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			integralDtlService.svipRecMonthIntegral(memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}


	/**
	 *
	 * 方法描述 ：SVIP开通页面
	 * @author  chenwf:
	 * @date 创建时间：2019年04月30日 下午5:03:20
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getSvipBuyPage")
	@ResponseBody
	public ResponseMsg getSvipBuyPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			Map<String, Object> map = svipService.getSvipBuyPage(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：SVIP推荐商品列表
	 * @author  yjc:
	 * @date 创建时间：2019年8月14日14:07:08
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getSvipProductRecommendList")
	@ResponseBody
	public ResponseMsg getSvipProductRecommendList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"memberId","currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = svipService.getSvipProductRecommendList(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

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

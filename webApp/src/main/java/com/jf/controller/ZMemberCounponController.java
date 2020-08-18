package com.jf.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.service.CouponService;
import com.jf.service.IntegralDtlService;
import com.jf.service.MemberAccountService;
import com.jf.service.MemberCouponService;
import com.jf.service.MemberInfoService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月12日 下午2:39:16 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ZMemberCounponController extends BaseController{
	@Resource
	private CouponService couponService;
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private MemberAccountService memberAccountService;
	
	@Resource
	private IntegralDtlService integralDtlService;

	@Resource
	private MemberInfoService memberInfoService;
	/**
	 * 
	 * 方法描述 ：领取优惠券(APP领取适用)
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月28日 下午6:24:50 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addReceiveCoupon")
	@ResponseBody
	public ResponseMsg addReceiveCoupon(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String memberId = getMemberIdStr(request);
			reqDataJson.put("memberId", memberId);
			Map<String,Object> map = memberCouponService.addReceiveCoupon(reqPRM,reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
				
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
}

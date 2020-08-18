package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindExample;
import com.jf.service.MemberExtendService;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberRemindService;
import com.jf.service.ShoppingMallService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Controller
public class ZMemberController extends BaseController {

	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private ShoppingMallService shoppingMallService;
	@Resource
	private MemberExtendService memberExtendService;
	@Resource
	private MemberRemindService memberRemindService;
	
	/**
	 * 
	 * @Title visitorsRegisteredMembersH5   
	 * @Description TODO(游客更新信息成为正式会员，砍价与邀请必须绑定手机)   
	 * @author pengl
	 * @date 2018年8月27日 下午3:17:36
	 */
	@RequestMapping(value = "/api/z/visitorsRegisteredMembersH5")
	@ResponseBody
	public ResponseMsg visitorsRegisteredMembersH5(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"mobile", "password", "mobileVerificationCode"};
			this.requiredStr(obj, request);
			Integer memberId = getMemberId(request);
			JSONObject returnData = memberInfoService.visitorsRegisteredMembers(reqPRM, reqDataJson, memberId,request);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	/**
	 * 店铺——商家工商资质信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getMchtGSQualificationsH5")
	@ResponseBody
	public ResponseMsg getMchtGSQualificationsH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtId","validCode"};
			this.requiredStr(obj,request);
			String validCode = reqDataJson.getString("validCode");
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			if((system.equals(Const.IOS) && version > 53) || system.equals(Const.ANDROID)){
				if(request.getSession().getAttribute(BaseDefine.CAPTCHA)==null){
					throw new ArgException("请刷新图形验证码");
				}
				String sessionValidCode = request.getSession().getAttribute(BaseDefine.CAPTCHA).toString();
				if(!validCode.equalsIgnoreCase(sessionValidCode)){
					throw new ArgException("验证码错误");
				}
			}
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = shoppingMallService.getMchtGSQualifications(reqDataJson);
			map.put("mVerfiyFlag", memberInfo.getmVerfiyFlag());
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	@RequestMapping(value = "/api/z/updateUserInfoH5")
	@ResponseBody
	public ResponseMsg updateUserInfoH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Integer memberId = getMemberId(request);
			MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
			String type = "1";
			if(!JsonUtils.isEmpty(reqDataJson, "type")){
				type = reqDataJson.getString("type");
			}
			//修改图片 pic
			if(!JsonUtils.isEmpty(reqDataJson, "pic") && !StringUtil.isBlank(reqDataJson.getString("pic"))){
				String pic = StringUtil.replace(reqDataJson.getString("pic"),"xgbuy.cc");
				memberInfo.setPic(pic);
			}
			
			//修改昵称 nick
			if(!JsonUtils.isEmpty(reqDataJson, "nick") && !StringUtil.isBlank(reqDataJson.getString("nick"))){
				memberInfo.setNick(reqDataJson.getString("nick"));
			}
			
			//修改性别 sexType
			if(!JsonUtils.isEmpty(reqDataJson, "sexType") && !StringUtil.isBlank(reqDataJson.getString("sexType"))){
				memberInfo.setSexType(reqDataJson.getString("sexType"));
			}
			
			//修改出生日期 birthday
			if(!JsonUtils.isEmpty(reqDataJson, "birthday") && !StringUtil.isBlank(reqDataJson.getString("birthday"))){
				Date date = DateUtil.getFormatString(reqDataJson.getString("birthday"), "yyyy-MM-dd");
				memberInfo.setBirthday(date);
			}
			//修改性别 sexType
			if(!JsonUtils.isEmpty(reqDataJson, "isAcceptPush") && !StringUtil.isBlank(reqDataJson.getString("isAcceptPush"))){
				if("1".equals(type)){
					memberInfo.setIsAcceptPush(reqDataJson.getString("isAcceptPush"));
				}else if("2".equals(type)){
					//更新会员扩展表信息
					memberExtendService.updateMemberExtendInfo(memberId); 
				}
			}
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			if(!memberInfo.getIsInfPerfect().equals("1") && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
				memberInfoService.setIsInfPerfect(memberInfo);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：添加开售提醒
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月8日 下午7:40:43 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addRemindSaleH5")
	@ResponseBody
	public ResponseMsg addRemindSaleH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"remindType","remindId"};
			this.requiredStr(obj,request);
			
			Integer memberId = getMemberId(request);//会员id
			//1 活动开售 2 商品开售
			Integer remindId = reqDataJson.getInt("remindId");
			String remindType = reqDataJson.getString("remindType");
			
			MemberRemindExample remindExample = new MemberRemindExample();
			remindExample.createCriteria().andMemberIdEqualTo(memberId).andRemindTypeEqualTo(remindType).andRemindIdEqualTo(remindId).andDelFlagEqualTo("0");
			List<MemberRemind> reminds = memberRemindService.selectByExample(remindExample);
			if(CollectionUtils.isEmpty(reminds)){
				MemberRemind memberRemind = new MemberRemind();
				memberRemind.setMemberId(memberId);
				memberRemind.setCreateBy(memberId);
				memberRemind.setCreateDate(new Date());
				memberRemind.setDelFlag("0");
				memberRemind.setRemindId(remindId);
				memberRemind.setRemindType(remindType);
				memberRemindService.insertSelective(memberRemind);
			}else{
				throw new ArgException("已收藏");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/z/deleteRemindSaleH5")
	@ResponseBody
	public ResponseMsg deleteRemindSaleH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"remindId","type"};
			this.requiredStr(obj,request);
			
			Integer memberId = getMemberId(request);
			Integer remindId = reqDataJson.getInt("remindId");
			String type = "";
			if(reqDataJson.containsKey("type")){
				type = reqDataJson.getString("type");
			}
			MemberRemindExample memberRemindExample = new MemberRemindExample();
			MemberRemindExample.Criteria criteria = memberRemindExample.createCriteria();
			criteria.andMemberIdEqualTo(memberId).andRemindIdEqualTo(remindId);
			if(!StringUtil.isBlank(type)){
				criteria.andRemindTypeEqualTo(type);
			}
			memberRemindService.deleteByExample(memberRemindExample);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}


	/**
	 *
	 * 方法描述 ：h5通过app登录接口
	 */
	@RequestMapping(value = "/api/z/appLogin")
	@ResponseBody
	public ResponseMsg addMemberCutOrderInfo(){
		return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
	}

}

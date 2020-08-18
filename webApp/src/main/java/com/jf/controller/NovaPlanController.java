package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberAttention;
import com.jf.entity.MemberAttentionExample;
import com.jf.entity.MemberDynamic;
import com.jf.entity.MemberDynamicExample;
import com.jf.entity.MemberInfo;
import com.jf.service.MchtShopDynamicService;
import com.jf.service.MemberAttentionService;
import com.jf.service.MemberDynamicService;
import com.jf.service.NovaPlanService;

import net.sf.json.JSONObject;

/**
 * 新星计划，拉新分润
 * @author chenwf
 *
 */
@Controller
public class NovaPlanController extends BaseController{
	@Resource
	private NovaPlanService novaPlanService;
	@Autowired
	private MchtShopDynamicService mchtShopDynamicService;
	@Autowired
	private MemberDynamicService memberDynamicService;
	@Autowired
	private MemberAttentionService memberAttentionService;
	
	/**
	 * 店长权益详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getXgShopwnerEquityDetail")
	@ResponseBody
	public ResponseMsg getXgShopwnerEquityDetail(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			MemberInfo memberInfo = null;
			if(request.getSession().getAttribute(BaseDefine.MEMBER_INFO) != null){
				memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			}
			Map<String, Object> map = novaPlanService.getXgShopwnerEquityDetail(memberInfo,reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"系统超时，请稍后在尝试");
		}
	}
	
	/**
	 * 会员开通新星计划
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberOpenNovaPlan")
	@ResponseBody
	public ResponseMsg addMemberOpenNovaPlan(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = novaPlanService.addMemberOpenNovaPlan(memberInfo,request);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"系统超时，请稍后在尝试");
		}
	}
	
	/**
	 * 新星计划首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getNovaPlanIndex")
	@ResponseBody
	public ResponseMsg getNovaPlanIndex(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = novaPlanService.getNovaPlanIndex(memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 新星计划续签任务进度列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberRenewalTaskProgress")
	@ResponseBody
	public ResponseMsg getMemberRenewalTaskProgress(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = novaPlanService.getMemberRenewalTaskProgress(reqPRM,reqDataJson,memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 新星计划续签合同列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberRenewalProgressLog")
	@ResponseBody
	public ResponseMsg getMemberRenewalProgressLog(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			
			Integer pageSize = Const.RETURN_SIZE_10;
			reqDataJson.put("pageSize", pageSize);
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = novaPlanService.getMemberRenewalProgressLog(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 新星攻略（新星攻略问答列表）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getNovaPlanHelpCenter")
	@ResponseBody
	public ResponseMsg getNovaPlanHelpCenter(HttpServletRequest request){
		try {
			Map<String, Object> map = novaPlanService.getNovaPlanHelpCenter(getMemberId(request));
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * @MethodName: getMchtShopDynamic
	 * @Description: (店铺动态详情)
	 * @author Pengl
	 * @date 2019年5月25日 下午2:02:22
	 */
	@ResponseBody
	@RequestMapping("/api/n/getMchtShopDynamic")
	public ResponseMsg getMchtShopDynamic(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = mchtShopDynamicService.getMchtShopDynamic(reqPRM, reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG, map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * @MethodName: getMemberDynamic
	 * @Description: (好友动态详情)
	 * @author Pengl
	 * @date 2019年5月25日 下午4:15:06
	 */
	@ResponseBody
	@RequestMapping("/api/n/getMemberDynamic")
	public ResponseMsg getMemberDynamic(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = memberDynamicService.getMemberDynamic(reqPRM, reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG, map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 好友关注，粉丝，推荐列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberAttentions")
	@ResponseBody
	public ResponseMsg getMemberAttentions(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Integer memberId = getMemberId(request);
			if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
				memberId = reqDataJson.getInt("memberId");
			}
			reqDataJson.put("pageSize", pageSize);
			reqDataJson.put("memberId", memberId);
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = novaPlanService.getMemberAttentions(reqPRM,reqDataJson,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 关注，取消关注
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateMemberAttention")
	@ResponseBody
	public ResponseMsg updateMemberAttention(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"friendMemberId"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			reqDataJson.put("memberId", memberId);
			Map<String, Object> map = memberAttentionService.updateMemberAttention(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 会员转发动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberDynamicForward")
	@ResponseBody
	public ResponseMsg addMemberDynamicForward(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtShopDynamicId","type"};
			this.requiredStr(obj,request);
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = novaPlanService.addMemberDynamicForward(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 动态点赞
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberDynamicFabulous")
	@ResponseBody
	public ResponseMsg addMemberDynamicFabulous(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtShopDynamicId","type"};
			this.requiredStr(obj,request);
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = novaPlanService.addMemberDynamicFabulous(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * @MethodName: getMchtShopDynamicList
	 * @Description: (个人中心推荐 、关注动态接口)
	 * @author Pengl
	 * @date 2019年5月27日 下午8:08:45
	 */
	@ResponseBody
	@RequestMapping("/api/y/getMchtShopDynamicList")
	public ResponseMsg getMchtShopDynamicList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage","type"};
			this.requiredStr(obj, request);
			Integer pageSize = Const.RETURN_SIZE_10;
			reqDataJson.put("pageSize", pageSize);
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = mchtShopDynamicService.getMchtShopDynamicList(reqPRM, reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG, map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * @MethodName: getMemberDynamicDtl
	 * @Description: (好友信息详情)
	 * @author Pengl
	 * @date 2019年5月27日 下午10:09:52
	 */
	@ResponseBody
	@RequestMapping("/api/y/getMemberDynamicDtl")
	public ResponseMsg getMemberDynamicDtl(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"friendMemberId"};
			this.requiredStr(obj, request);
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = mchtShopDynamicService.getMemberDynamicDtl(reqPRM, reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG, map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * @MethodName: getMemberDynamicList
	 * @Description: (好友动态列表)
	 * @author Pengl
	 * @date 2019年5月27日 下午10:09:52
	 */
	@ResponseBody
	@RequestMapping("/api/y/getMemberDynamicList")
	public ResponseMsg getMemberDynamicList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage","friendMemberId"};
			this.requiredStr(obj, request);
			Integer pageSize = Const.RETURN_SIZE_10;
			reqDataJson.put("pageSize", pageSize);
			reqDataJson.put("memberId", getMemberId(request));
			Map<String, Object> map = mchtShopDynamicService.getMemberDynamicList(reqPRM, reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG, map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 关注--屏蔽/接收动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateShieldingDynamics")
	@ResponseBody
	public ResponseMsg updateShieldingDynamics(HttpServletRequest request) {
		try {
			Map<String,String> map = new HashMap<String,String>();
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"friendMemberId"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			Integer friendMemberId = reqDataJson.getInt("friendMemberId");
			MemberAttentionExample e = new MemberAttentionExample();
			e.createCriteria().andMemberIdEqualTo(memberId).andAttentionMemberIdEqualTo(friendMemberId).andDelFlagEqualTo("0");
			List<MemberAttention> memberAttentionList = memberAttentionService.selectByExample(e);
			if(memberAttentionList!=null && memberAttentionList.size()>0){
				MemberAttention memberAttention = memberAttentionList.get(0);
				String shieldingDynamics = memberAttention.getShieldingDynamics();
				if(shieldingDynamics.equals("0")){
					memberAttention.setShieldingDynamics("1");
				}else{
					memberAttention.setShieldingDynamics("0");
				}
				memberAttention.setUpdateDate(new Date());
				memberAttention.setUpdateBy(memberId);
				memberAttentionService.updateByPrimaryKeySelective(memberAttention);
				map.put("shieldingDynamics", memberAttention.getShieldingDynamics());
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 删除动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateMemberDynamic")
	@ResponseBody
	public ResponseMsg updateMemberDynamic(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtShopDynamicId"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			Integer mchtShopDynamicId = reqDataJson.getInt("mchtShopDynamicId");
			MemberDynamicExample e = new MemberDynamicExample();
			e.createCriteria().andMemberIdEqualTo(memberId).andMchtShopDynamicIdEqualTo(mchtShopDynamicId).andDelFlagEqualTo("0");
			MemberDynamic memberDynamic = new MemberDynamic();
			memberDynamic.setDelFlag("1");
			memberDynamic.setUpdateBy(memberId);
			memberDynamic.setUpdateDate(new Date());
			memberDynamicService.updateByExampleSelective(memberDynamic, e);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 会员是否关注了该好友及屏蔽动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/hasFocusOnFriends")
	@ResponseBody
	public ResponseMsg hasFocusOnFriends(HttpServletRequest request){
		try {
			Map<String,String> map = new HashMap<String,String>();
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","friendMemberId"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");
			Integer friendMemberId = reqDataJson.getInt("friendMemberId");
			MemberAttentionExample memberAttentionExample = new MemberAttentionExample();
			MemberAttentionExample.Criteria criteria = memberAttentionExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andAttentionMemberIdEqualTo(friendMemberId);
			List<MemberAttention> memberAttentionList = memberAttentionService.selectByExample(memberAttentionExample);
			if(memberAttentionList!=null && memberAttentionList.size()>0){
				MemberAttention memberAttention = memberAttentionList.get(0);
				map.put("hasFocusOnFriends", "1");
				map.put("shieldingDynamics", memberAttention.getShieldingDynamics());
			}else{
				map.put("hasFocusOnFriends", "0");
				map.put("shieldingDynamics", "0");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
}

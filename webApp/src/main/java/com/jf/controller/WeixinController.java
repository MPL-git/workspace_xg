package com.jf.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.WeixinUtil;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.WeixinGzhMemberBind;
import com.jf.entity.WeixinGzhMemberBindExample;
import com.jf.entity.WeixinOauthRedirectUrl;
import com.jf.service.MemberAttentionService;
import com.jf.service.MemberExtendService;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberStatusLogService;
import com.jf.service.WeixinGzhMemberBindService;
import com.jf.service.WeixinJsapiTicketService;
import com.jf.service.WeixinOauthRedirectUrlService;

import net.sf.json.JSONObject;

@Controller
public class WeixinController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private WeixinGzhMemberBindService weixinGzhMemberBindService;
	@Resource
	private WeixinJsapiTicketService weixinJsapiTicketService;
	
	@Resource
	private WeixinOauthRedirectUrlService weixinOauthRedirectUrlService;
	@Resource
	private MemberStatusLogService memberStatusLogService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberExtendService memberExtendService;
	
	
	@RequestMapping("/weixin/weixinOauthCallback")
	public String weixinOauthCallback(Model model, HttpServletRequest request) {
		String code = request.getParameter("code");
		 String state = request.getParameter("state"); 
		if (code != null && !"".equals(code)) {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeixinUtil.appId + "&secret=" + WeixinUtil.appSecret + "&code=" + code + "&grant_type=authorization_code";
			String jsonStr;
			try {
				jsonStr = HttpUtil.httpGet(url);
				JSONObject resJson = JSONObject.fromObject(jsonStr);
				if (!resJson.has("errcode")||"0".equals(resJson.get("errcode"))) {
					String openid = resJson.getString("openid");
					request.getSession().setAttribute("openId", openid);
					MemberInfo memberInfo=null;
					//自动登录
					WeixinGzhMemberBindExample weixinGzhMemberBindExample=new WeixinGzhMemberBindExample();
					weixinGzhMemberBindExample.createCriteria().andDelFlagEqualTo("0").andOpenIdEqualTo(openid);
					List<WeixinGzhMemberBind> weixinGzhMemberBinds=weixinGzhMemberBindService.selectByExample(weixinGzhMemberBindExample);
					if(weixinGzhMemberBinds!=null&&weixinGzhMemberBinds.size()>0){
						memberInfo=memberInfoService.selectByPrimaryKey(weixinGzhMemberBinds.get(0).getMemberId());
					}else{
						String accessToken = resJson.getString("access_token");
						url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
						jsonStr = HttpUtil.httpGet(url);
						
						resJson = JSONObject.fromObject(jsonStr);
						if(resJson.has("unionid")){
							String unionid=resJson.getString("unionid");
							MemberInfoExample memberInfoExample=new MemberInfoExample();
							memberInfoExample.createCriteria().andDelFlagEqualTo("0").andWeixinUnionidEqualTo(unionid);
							List<MemberInfo> memberInfos=memberInfoService.selectByExample(memberInfoExample);
							
							if(memberInfos!=null&&memberInfos.size()>0){
								memberInfo=memberInfos.get(0);
							}else{//创建新会员
								String invitationMemberId = "";
								String serverName=request.getServerName();
								System.out.println("serverName:"+serverName+":"+state);
								if("mtest.xgbuy.cc".equals(serverName)||"mtest.xinggoubuy.com".equals(serverName)||"mtest.xinggoumall.com".equals(serverName)||"m.xinggoubuy.com".equals(serverName)||"m.xinggoumall.com".equals(serverName)){
									if(state.split("88888").length >= 3 && !StringUtil.isBlank(state.split("88888")[2])){
										invitationMemberId = state.split("88888")[2];
									}
								}else{
									if(state.split("88888").length >= 2 && !StringUtil.isBlank(state.split("88888")[1])){
										invitationMemberId = state.split("88888")[1];
									}
								}
								
								memberInfo=new MemberInfo();
								memberInfo.setType("2");
								String nick=resJson.getString("nickname");
								nick=StringUtil.filterEmoji(nick);
								memberInfo.setNick(nick);
								memberInfo.setRemarks("微信公众号");
								memberInfo.setWeixinUnionid(unionid);
								if(!StringUtil.isBlank(invitationMemberId)){
									memberInfo.setInvitationMemberId(Integer.parseInt(invitationMemberId));
									memberInfo.setInvitationCodeBindTime(new Date());
								}
								//memberInfo.setRegArea(resJson.getString("country")+resJson.getString("province")+resJson.getString("city"));
								memberInfo.setRegClient("3");
								memberInfo.setRegIp(StringUtil.getIpAddr(request));
								memberInfo.setCreateDate(new Date());
								memberInfo.setDelFlag("0");
								memberInfo.setGroupId(1);//注册会员标识
								memberInfo.setStatus("A");
								String sexType=resJson.getString("sex");
								if("1".equals(sexType)){
									memberInfo.setSexType("1");
								}
								if("2".equals(sexType)){
									memberInfo.setSexType("0");
								}
								if("0".equals(sexType)){
									memberInfo.setSexType("2");
								}
								
								String headimg=FileUtil.getRandomFileName("123.jpg", 1, 0);
								if(!StringUtil.isEmpty(resJson.getString("headimgurl"))){
									String headImgPath=HttpUtil.saveFileFromUrl(resJson.getString("headimgurl"), FileUtil.defaultPath.concat(headimg));
									memberInfo.setWeixinHead(headimg);
									memberInfo.setPic(headimg);
								}
								memberInfoService.createMemberInfo(memberInfo);
								//添加会员注册状态
								memberStatusLogService.addMemberRegisterStatus(memberInfo.getId(), Const.MEMBER_INFO_STATUS_ACTIVATION_A);
								//绑定好友关系(互相关注)
								memberAttentionService.addMemberMutualConcern(memberInfo.getId(),memberInfo.getInvitationMemberId());
								//会员扩展
								memberExtendService.addModel(memberInfo.getId(),"","");
							}
							
							//微信会员做绑定
							WeixinGzhMemberBind weixinGzhMemberBind=new WeixinGzhMemberBind();
							weixinGzhMemberBind.setCreateDate(new Date());
							weixinGzhMemberBind.setDelFlag("0");
							weixinGzhMemberBind.setOpenId(openid);
							weixinGzhMemberBind.setMemberId(memberInfo.getId());
							weixinGzhMemberBindService.insertSelective(weixinGzhMemberBind);
						}
					}
					//登录
					request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
					if (memberInfo != null) {
						memberInfoService.addLoginLog(memberInfo.getId(), StringUtil.getIpAddr(request), "GZH");
					}
				}else{
					request.getSession().setAttribute("openId", "openIdError");
					logger.info("--------------------------获取openId出错---------------");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				e.printStackTrace();
			}// 根据code获取用户openId
		}
		   
		    String redirectUrlKey=state.split("88888")[0];
		    
		    WeixinOauthRedirectUrl weixinOauthRedirectUrl=weixinOauthRedirectUrlService.selectByPrimaryKey(Integer.valueOf(redirectUrlKey));
		    String redirectUrl=weixinOauthRedirectUrl.getRedirectUrl();
		    return "redirect:"+redirectUrl;
	}
	
	
	@RequestMapping("/weixin/inner/weixinOauthCallback")
	public String weixinInnerOauthCallback(Model model, HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String serverUrl=state.split("88888")[1];
		serverUrl=serverUrl.replace("0", ".");//微信state,只能用字母和数字字符
		serverUrl=serverUrl.replace("1", ":");
		return "redirect:"+request.getScheme()+"://"+serverUrl+"/webApp/weixin/weixinOauthCallback?code="+code+"&state="+state;
	}
	
	
	
	@RequestMapping(value = "/api/n/getWecharShareSign")
	@ResponseBody
	public ResponseMsg getWecharShareSign(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String url = reqDataJson.getString("url");
			Map<String,String> signMap = weixinJsapiTicketService.getWxShareSignature(url);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,signMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
}

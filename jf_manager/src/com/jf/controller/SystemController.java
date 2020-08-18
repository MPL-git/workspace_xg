package com.jf.controller;

import com.gzs.common.beans.Menu;
import com.gzs.common.utils.MD5;
import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.CaptchaUtils;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.*;


@SuppressWarnings("serial")
@Controller
public class SystemController extends BaseController {
	
	private static final Log logger = LogFactory.getLog(MchtInfoService.class);
	
	@Resource
	private SystemService systemService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private SysLoginLogService sysLoginLogService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private SysParamCfgService sysParamCfgService;
	
	@RequestMapping(value = "/welcome.shtml")
	public ModelAndView welcome(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/system/login";// 重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	@SuppressWarnings({ "static-access" })
	@RequestMapping(value = "/login.shtml")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/system/login";// 重定向
		String msg = "";
		String code = null;
		boolean checkPassed = true;
		Map<String,Object> resMap = new HashMap<String,Object>();
		String mobilePhoneFlag = "true";
		int loginErrorCount = 0;
		SysLoginLog sysLoginLog = new SysLoginLog();
		sysLoginLog.setCreateDate(new Date());
		sysLoginLog.setLoginResult("2"); //2 登录失败
		sysLoginLog.setClientIp(StringUtils.getIpAddr(request));
		try {
			//参数不能为空验证
			String [] paramStr = {"username:登录账号","password:登录密码"};
			ResultBean checkBean = requiredCheck(paramStr, paramMap);
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())) {
				code = checkBean.getResultCode();
				msg = checkBean.getMessage();
				checkPassed = false;
			}
			String captcha = (String) request.getSession().getAttribute(BaseDefine.CAPTCHA);
			String captchaFlag = request.getParameter("captchaFlag");
			if(checkPassed) {
				//登录随机码验证
				if(!"1".equals(captchaFlag)) {
					if(!StringUtil.isEmpty(StringUtil.valueOf(paramMap.get("validCode")))) {
						if (!captcha.toLowerCase().equals(StringUtil.valueOf(paramMap.get("validCode")).toLowerCase())) {
							code = StateCode.ERR_OTHER.getStateCode();
							msg = "验证码错误";
							checkPassed = false;
						}
					}else {
						code = StateCode.ERR_OTHER.getStateCode();
						msg = "验证码不能为空";
						checkPassed = false;
					}
				}
			}
			//账号密码验证
			paramMap.put("STAFF_CODE", StringUtil.valueOf(paramMap.get("username")));
			sysLoginLog.setInputLoginName(StringUtil.valueOf(paramMap.get("username")));
			List<?> list = systemService.queryUserPwd(paramMap);
			if(checkPassed) {
				if(checkPassed && list.size() <= 0) {
					code = StateCode.ERR_OTHER.getStateCode();
					msg = "账号不存在";
					checkPassed = false;
				}else {
					Map<?, ?> resultMap = (Map<?, ?>)list.get(0);
					Integer staffId = (Integer) resultMap.get("STAFF_ID");
					loginErrorCount = (Integer) resultMap.get("LOGIN_ERROR_COUNT");
					sysLoginLog.setStaffInfoId(staffId);
					sysLoginLog.setCreateBy(staffId);
					if(loginErrorCount > 0 && loginErrorCount <= 5 && "1".equals(captchaFlag)) {
						code = StateCode.ERR_OTHER.getStateCode();
						msg = "请重新登录";
						checkPassed = false;
					}
				}
			}
		    if(checkPassed) {
		    	MD5 md5Obj = new MD5();
		    	Map<?, ?> resultMap = (Map<?, ?>)list.get(0);
		    	String inPwd = StringUtil.valueOf(paramMap.get("password"));
		    	String enPwd = md5Obj.enPasswd(StringUtil.valueOf(resultMap.get("STAFF_ID")),inPwd);
		    	String oldpwd = md5Obj.enPasswd(StringUtil.valueOf(resultMap.get("STAFF_ID")),"123456");
		    	if(!StringUtil.valueOf(resultMap.get("STAFF_PASS")).equals(enPwd)) {
			    	code = StateCode.ERR_OTHER.getStateCode();
			    	msg = "输入的密码有误";
			    	++loginErrorCount;
		    	}else if(StringUtil.valueOf(resultMap.get("STAFF_PASS")).equals(oldpwd)) {
			    	code = StateCode.ERR_OTHER.getStateCode();
			    	rtPage = "redirect:/orgMng/logupdate_pwd.shtml";
			    	resMap.put("STAFF_ID", resultMap.get("STAFF_ID"));
			    	++loginErrorCount;
		    	}else if(StringUtil.isEmpty(StringUtil.valueOf(resultMap.get("MOBILE_PHONE")))) {
		    		code = StateCode.ERR_OTHER.getStateCode();
			    	msg = "用户手机号码为空";
			    	++loginErrorCount;
			    	mobilePhoneFlag = "false";
		    	}else if(loginErrorCount > 5) {
					code = StateCode.ERR_OTHER.getStateCode();
					msg = "用户账号登录错误次数大于5次";
					checkPassed = false;
					request.getSession().setAttribute("staffId", StringUtil.valueOf(resultMap.get("STAFF_ID")));
		    	    request.getSession().setAttribute("mobilePhone", StringUtil.valueOf(resultMap.get("MOBILE_PHONE")));
		    	    request.getSession().setAttribute("staffCode", StringUtil.valueOf(resultMap.get("STAFF_CODE")));
		    	    request.getSession().setAttribute("loginErrorCount", loginErrorCount);
		    	    request.getSession().setAttribute("password", StringUtil.valueOf(paramMap.get("password")));
		    	    request.getSession().setAttribute("username", StringUtil.valueOf(paramMap.get("username")));
		    	    resMap.put("loginErrorCountFlag", loginErrorCount);
				}else {
		    	    // 获取用户信息
		    	    StaffBean staffBean = new StaffBean();
		    	    staffBean.setStaffCode(StringUtil.valueOf(resultMap.get("STAFF_CODE")));
		    	    staffBean.setStaffID(StringUtil.valueOf(resultMap.get("STAFF_ID")));
		    	    staffBean.setStaffName(StringUtil.valueOf(resultMap.get("STAFF_NAME")));
		    	    staffBean.setOrgId(StringUtil.valueOf(resultMap.get("ORG_ID")));
		    	    staffBean.setMobilePhone(StringUtil.valueOf(resultMap.get("MOBILE_PHONE")));
		    	    staffBean.setRoleId(StringUtil.valueOf(resultMap.get("CUR_ROLE_ID")));
		    	    staffBean.setIsManagement(StringUtil.valueOf(resultMap.get("IS_MANAGEMENT")));
	
		    	    // 保存用户信息到Session会话
		    	    this.saveUserInfoToSession(request,staffBean);
		    	    // 是否为钟表运营部状态
		    	    if(!StringUtil.isEmpty(staffBean.getRoleId())) {
		    	    	this.saveParamSession(request, "isCwOrgStatus", systemService.selectCwOrgStatus(Integer.parseInt(staffBean.getOrgId())));
		    	    }
		    	    
		    	    /*ProductTypeExample productTypeExample = new ProductTypeExample();
		    	    ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		    	    productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1")
		    	    	.andTLevelEqualTo(1).andNameEqualTo("钟表");
		    	    List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		    	    if(productTypeList != null && productTypeList.size() > 0) {
		    	    	this.saveParamSession(request, "isCwOrgProductTypeId", productTypeList.get(0).getId().toString());
		    	    }*/
		    	    
		    	    SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		    	    sysParamCfgExample.createCriteria().andParamCodeEqualTo("HOROLOGE_TYPE_ID");
		    	    List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
		    	    if(sysParamCfgList != null && sysParamCfgList.size() > 0) {
		    	    	this.saveParamSession(request, "isCwOrgProductTypeId", sysParamCfgList.get(0).getParamValue());
		    	    }
		    	    
		    	    resMap.put("STAFF_NAME",staffBean.getStaffName());
		    		code = StateCode.SUCCESS.getStateCode();
			    	msg = "登陆成功";
			    	rtPage = "redirect:/system/index.shtml";
			    	sysLoginLog.setLoginResult("1"); //1 登录成功
			    	loginErrorCount = 0;
			    	request.getSession().removeAttribute("password");
			    	request.getSession().removeAttribute("username");
			    	request.getSession().removeAttribute("loginErrorCount");
			    	request.getSession().removeAttribute("staffId");
			    	request.getSession().removeAttribute("mobilePhone");
			    	request.getSession().removeAttribute("staffCode");
			    	request.getSession().removeAttribute("itmp");
				}
		    }
		    sysLoginLog.setRemarks(msg);
			sysLoginLogService.insertSysLoginLog(sysLoginLog, loginErrorCount);
			if("1".equals(sysLoginLog.getLoginResult())) {
				return new ModelAndView(rtPage);
			}
		} catch (Exception e) {
			code = StateCode.ERR_OTHER.getStateCode();
    	    msg = e.getMessage();
		}
		resMap.put("RESULT_CODE",code);
	    resMap.put("RESULT_MESSAGE", msg);
	    resMap.put("mobilePhoneFlag", mobilePhoneFlag);
	    resMap.put("captchaFlag", "");
		return new ModelAndView(rtPage, resMap);
	}
	
	@RequestMapping(value = "/system/index.shtml")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		
		String rtPage = "/system/index";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		List<?> menulist = null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			paramMap.put("STAFF_ID", staffBean.getStaffID());
			List<Menu> menuTree = systemService.getMenuTree(paramMap);
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("MENU_TREE_JSON", objectMapper.writeValueAsString(menuTree));
			//查询权限菜单
			//paramMap.put("STAFF_ID", staffBean.getStaffID());
			//paramMap.put("ROLE_ID", staffBean.getRoleId());
			//menulist = systemFacade.queryUserMenuList(paramMap);
			resMap.put("MENU_TREE_HTML", Menu.buildJUITree(menuTree, request.getContextPath()));

		} catch (Exception e) {
			menulist = new ArrayList<Map<String, Object>>();
		}
		resMap.put("SYS_MENU", menulist);
		//return new ModelAndView("system/index_liger", resMap);
		return new ModelAndView("system/index", resMap);
	}
	@RequestMapping(value = "/system/welcome.shtml")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		
		String rtPage = "/system/welcome";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		return new ModelAndView("system/welcome", resMap);
	}
	@RequestMapping(value = "/logOut.shtml")
	public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String,Object> resMap = new HashMap<String,Object>(); 
		String rtPage=""; 
    	rtPage = "/system/login";
    	request.getSession().invalidate();//销毁用户的session	 
    	resMap.put("captchaFlag", "1");
		return new ModelAndView(rtPage,resMap);
	}
	public static void main(String[] args) {
		MD5 md5Obj = new MD5();
		String enPwd=md5Obj.enPasswd("1","123");
	}
	
	/**
	 * 
	 * @Title getCaptcha   
	 * @Description TODO(生成登录图片验证码)   
	 * @author pengl
	 * @date 2018年5月29日 下午4:46:40
	 */
	@ResponseBody
	@RequestMapping(value = "/system/getCaptcha.shtml", method = RequestMethod.GET, produces = MediaType.IMAGE_GIF_VALUE)
	public byte[] getCaptcha(HttpServletRequest request) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String captcha = CaptchaUtils.getGifCaptcha(90, 28, 4, outputStream, 1000).toLowerCase();
		request.getSession().setAttribute(BaseDefine.CAPTCHA, captcha);
		return outputStream.toByteArray();
	}
	
	/**
	 * 
	 * @Title sendSmsManager   
	 * @Description TODO(发送短信验证)   
	 * @author pengl
	 * @date 2018年5月30日 下午4:19:05
	 */
	@RequestMapping("/system/sendSmsManager.shtml")
	public ModelAndView sendSmsManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/system/sendSms");
		String mobilePhone = (String) request.getSession().getAttribute("mobilePhone");
		mobilePhone = mobilePhone.substring(0, 3)+"***"+mobilePhone.substring(mobilePhone.length()-3, mobilePhone.length());
		m.addObject("mobilePhone", mobilePhone);
		return m;
	}
	
	/**
	 * 
	 * @Title sendSms   
	 * @Description TODO(发送验证码)   
	 * @author pengl
	 * @date 2018年5月31日 上午11:21:56
	 */
	@ResponseBody
	@RequestMapping("/system/sendSms.shtml")
	public Map<String, Object> sendSms(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String mobilePhone = (String) request.getSession().getAttribute("mobilePhone");
			if(!StringUtil.isEmpty(mobilePhone)) {
				String itmp = "";
				for (int i = 0; i < 4; i++) {
					itmp += Math.round(Math.random() * 9);
				}
				//发送短信
				JSONObject param = new JSONObject();
				JSONObject reqData = new JSONObject();
				reqData.put("mobile", mobilePhone);
				reqData.put("content", "醒购后台登录验证码："+itmp+"。为了账号的安全，请勿告诉他人。 ");
				reqData.put("smsType", "1");
				param.put("reqData", reqData);
				JSONObject result = JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				if(!"0000".equals(result.getString("returnCode"))){
					logger.info("短信发送用户失败！！！！！");
					code = "9999";
					msg = "短信发送用户失败！";
				}else {
					request.getSession().setAttribute("itmp", itmp);
				}
			}else {
				code = "9999";
				msg = "用户手机号码为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title clearValidCode   
	 * @Description TODO(清除验证码)   
	 * @author pengl
	 * @date 2018年5月31日 上午11:22:11
	 */
	@ResponseBody
	@RequestMapping("/system/clearValidCode.shtml")
	public Map<String, Object> clearValidCode(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			request.getSession().removeAttribute("itmp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title mobilePhoneVerify   
	 * @Description TODO(手机验证)   
	 * @author pengl
	 * @date 2018年5月31日 下午2:07:20
	 */
	@ResponseBody
	@RequestMapping("/system/mobilePhoneVerify.shtml")
	public Map<String, Object> mobilePhoneVerify(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffId = Integer.parseInt((String) request.getSession().getAttribute("staffId"));
			Integer loginErrorCount = (Integer) request.getSession().getAttribute("loginErrorCount");
			String staffCode = (String) request.getSession().getAttribute("staffCode");
			String validCode = request.getParameter("validCode");
			String itmp = (String) request.getSession().getAttribute("itmp");
			SysLoginLog sysLoginLog = new SysLoginLog();
			sysLoginLog.setStaffInfoId(staffId);
			sysLoginLog.setInputLoginName(staffCode);
			sysLoginLog.setClientIp(StringUtils.getIpAddr(request));
			sysLoginLog.setCreateBy(staffId);
			sysLoginLog.setCreateDate(new Date());
			if(itmp == null) {
				sysLoginLog.setLoginResult("2");
				++loginErrorCount;
				sysLoginLog.setRemarks("验证码失效！");
				map.put("validCodeMsg", "验证码失效！");
				sysLoginLogService.insertSysLoginLog(sysLoginLog, loginErrorCount);
			}else if(validCode != null && validCode.equals(itmp)) {
				SysStaffInfo sysStaffInfo = new SysStaffInfo();
				sysStaffInfo.setStaffId(staffId);
				sysStaffInfo.setLoginErrorCount(0);
				sysStaffInfoService.updateByPrimaryKeySelective(sysStaffInfo);
	    	    map.put("password", request.getSession().getAttribute("password"));
	    	    map.put("username", request.getSession().getAttribute("username"));
			}else {
				sysLoginLog.setLoginResult("2");
				++loginErrorCount;
				sysLoginLog.setRemarks("验证码错误！");
				map.put("validCodeMsg", "验证码错误！");
				sysLoginLogService.insertSysLoginLog(sysLoginLog, loginErrorCount);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
	
	
}

package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.BlackList;
import com.jf.entity.MemberAccountDtlCustom;
import com.jf.entity.MemberAccountDtlCustomExample;
import com.jf.entity.MemberSignInCustom;
import com.jf.entity.MemberSignInCustomExample;
import com.jf.entity.SignSendMsgCnf;
import com.jf.entity.SignSendMsgCnfCustom;
import com.jf.entity.SignSendMsgCnfCustomExample;
import com.jf.entity.StateCode;
import com.jf.service.BlackListService;
import com.jf.service.MemberAccountDtlService;
import com.jf.service.MemberSignInService;
import com.jf.service.SignSendMsgCnfService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MemberSignInController extends BaseController {

	@Autowired
	private MemberSignInService memberSignInService;
	
	@Autowired
	private BlackListService blackListService;
	
	@Autowired
	private SignSendMsgCnfService signSendMsgCnfService;
	
	@Autowired
	private MemberAccountDtlService memberAccountDtlService;
	
	/**
	 * 
	 * @Title memberSignInManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月25日 上午10:02:50
	 */
	@RequestMapping("/memberSignIn/memberSignInManager.shtml")
	public ModelAndView memberSignInManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/memberSignIn/getMemberSignInList");
		return m;
	}
	
	/**
	 * 
	 * @Title getMemberSignInList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月25日 上午10:33:22
	 */
	@ResponseBody
	@RequestMapping("/memberSignIn/getMemberSignInList.shtml")
	public Map<String, Object> getMemberSignInList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MemberSignInCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MemberSignInCustomExample memberSignInCustomExample = new MemberSignInCustomExample();
			MemberSignInCustomExample.MemberSignInCustomCriteria memberSignInCustomCriteria = memberSignInCustomExample.createCriteria();
			memberSignInCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("memberNick"))) {
				memberSignInCustomCriteria.andMemberNickLike("%"+request.getParameter("memberNick")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("beginLastSignInDate"))) {
				memberSignInCustomCriteria.andLastSignInDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginLastSignInDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endLastSignInDate"))) {
				memberSignInCustomCriteria.andLastSignInDateLessThanOrEqualTo(sdf.parse(request.getParameter("endLastSignInDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("lastSignInDay"))) {
				memberSignInCustomCriteria.andLastSignInDayGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("lastSignInDay")));
			}
			if(!StringUtil.isEmpty(request.getParameter("memberMobile"))) {
				memberSignInCustomCriteria.andMemberMobileLike("%"+request.getParameter("memberMobile")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("memberId"))) {
				memberSignInCustomCriteria.andMemberId(Integer.valueOf(request.getParameter("memberId")));
			}
			memberSignInCustomCriteria.andBlackListNull();
			memberSignInCustomExample.setOrderByClause(" t.last_sign_in_date desc");
			memberSignInCustomExample.setLimitStart(page.getLimitStart());
			memberSignInCustomExample.setLimitSize(page.getLimitSize());
			totalCount = memberSignInService.countByCustomExample(memberSignInCustomExample);
			dataList = memberSignInService.selectByCustomExample(memberSignInCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title blackListManager   
	 * @Description TODO(拉黑)   
	 * @author pengl
	 * @date 2018年5月25日 下午1:53:03
	 */
	@RequestMapping("/memberSignIn/addblackListManager.shtml")
	public ModelAndView addblackListManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/memberSignIn/addBlackList");
		m.addObject("memberId", request.getParameter("memberId"));
		m.addObject("supplementaryCard", request.getParameter("supplementaryCard"));
		m.addObject("newSignIn", request.getParameter("newSignIn"));
		return m;
	}
	
	/**
	 * 
	 * @Title addblackList   
	 * @Description TODO(拉黑)   
	 * @author pengl
	 * @date 2018年5月25日 下午2:38:31
	 */
	@RequestMapping("/memberSignIn/addblackList.shtml")
	public ModelAndView addblackList(HttpServletRequest request, BlackList blackList) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			blackList.setBlackDate(date);
			if(!StringUtil.isEmpty(request.getParameter("blackToDate"))) {
				blackList.setBlackToDate(sdf.parse(request.getParameter("blackToDate")));
			}
			blackList.setCreateBy(staffID);
			blackList.setCreateDate(date);
			blackListService.insertSelective(blackList);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @Title signSendMsgCnfManager   
	 * @Description TODO(推送设置)   
	 * @author pengl
	 * @date 2018年5月25日 下午3:04:22
	 */
	@RequestMapping("/memberSignIn/signSendMsgCnfManager.shtml")
	public ModelAndView signSendMsgCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/memberSignIn/getSignSendMsgCnfList");
		m.addObject("sendWayList", DataDicUtil.getTableStatus("BU_SIGN_SEND_MSG_CNF", "SEND_WAY"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSignSendMsgCnfList   
	 * @Description TODO(推送设置)   
	 * @author pengl
	 * @date 2018年5月25日 下午3:05:12
	 */
	@ResponseBody
	@RequestMapping("/memberSignIn/getSignSendMsgCnfList.shtml")
	public Map<String, Object> getSignSendMsgCnfList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SignSendMsgCnfCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SignSendMsgCnfCustomExample sendMsgCnfCustomExample = new SignSendMsgCnfCustomExample();
			SignSendMsgCnfCustomExample.SignSendMsgCnfCustomCriteria sendMsgCnfCustomCriteria = sendMsgCnfCustomExample.createCriteria();
			sendMsgCnfCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("sendWay"))) {
				sendMsgCnfCustomCriteria.andSendWayEqualTo(request.getParameter("sendWay"));
			}
			sendMsgCnfCustomExample.setOrderByClause(" t.id desc");
			sendMsgCnfCustomExample.setLimitStart(page.getLimitStart());
			sendMsgCnfCustomExample.setLimitSize(page.getLimitSize());
			totalCount = signSendMsgCnfService.countByCustomExample(sendMsgCnfCustomExample);
			dataList = signSendMsgCnfService.selectByCustomExample(sendMsgCnfCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title addSignSendMsgCnfManager   
	 * @Description TODO(推送)   
	 * @author pengl
	 * @date 2018年5月25日 下午4:44:24
	 */
	@RequestMapping("/memberSignIn/addOrUpdateSignSendMsgCnfManager.shtml")
	public ModelAndView addOrUpdateSignSendMsgCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/memberSignIn/addOrUpdateSignSendMsgCnf");
		if(!StringUtil.isEmpty(request.getParameter("signSendMsgCnfId"))) {
			SignSendMsgCnfCustom sendMsgCnfCustom = signSendMsgCnfService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("signSendMsgCnfId")));
			m.addObject("sendMsgCnfCustom", sendMsgCnfCustom);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateSignSendMsgCnf   
	 * @Description TODO(新增或修改推送)   
	 * @author pengl
	 * @date 2018年5月25日 下午4:50:13
	 */
	@RequestMapping("/memberSignIn/addOrUpdateSignSendMsgCnf.shtml")
	public ModelAndView addOrUpdateSignSendMsgCnf(HttpServletRequest request, SignSendMsgCnf signSendMsgCnf) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(signSendMsgCnf.getId() == null) {
				signSendMsgCnf.setCreateBy(staffID);
				signSendMsgCnf.setCreateDate(date);
				signSendMsgCnfService.insertSelective(signSendMsgCnf); //新增
			}else {
				signSendMsgCnf.setUpdateBy(staffID);
				signSendMsgCnf.setUpdateDate(date);
				signSendMsgCnfService.updateByPrimaryKeySelective(signSendMsgCnf); //修改
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @Title delSignSendMsgCnf   
	 * @Description TODO(推送删除)   
	 * @author pengl
	 * @date 2018年5月25日 下午7:21:42
	 */
	@ResponseBody
	@RequestMapping("/memberSignIn/delSignSendMsgCnf.shtml")
	public Map<String, Object> delSignSendMsgCnf(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String signSendMsgCnfId = request.getParameter("signSendMsgCnfId");
			if(!StringUtil.isEmpty(signSendMsgCnfId)) {
				SignSendMsgCnf signSendMsgCnf = new SignSendMsgCnf();
				signSendMsgCnf.setId(Integer.parseInt(signSendMsgCnfId));
				signSendMsgCnf.setDelFlag("1");
				signSendMsgCnf.setUpdateBy(Integer.parseInt(staffID));
				signSendMsgCnf.setUpdateDate(date);
				signSendMsgCnfService.updateByPrimaryKeySelective(signSendMsgCnf);
			}else {
				code = "9999";
				msg = "ID为空！";
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
	 * @Title memberAccountDtlManager   
	 * @Description TODO(签到记录)   
	 * @author pengl
	 * @date 2018年5月25日 下午3:50:44
	 */
	@RequestMapping("/memberSignIn/memberAccountDtlManager.shtml")
	public ModelAndView memberAccountDtlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/memberSignIn/getMemberAccountDtlList");
		m.addObject("bizTypeList", DataDicUtil.getTableStatus("BU_MEMBER_ACCOUNT_DTL", "BIZ_TYPE"));
		m.addObject("accId", request.getParameter("accId"));
		return m;
	}
	
	/**
	 * 
	 * @Title getMemberAccountDtlList   
	 * @Description TODO(签到记录)   
	 * @author pengl
	 * @date 2018年5月25日 下午3:51:39
	 */
	@ResponseBody
	@RequestMapping("/memberSignIn/getMemberAccountDtlList.shtml")
	public Map<String, Object> getMemberAccountDtlList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MemberAccountDtlCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MemberAccountDtlCustomExample memberAccountDtlCustomExample = new MemberAccountDtlCustomExample();
			MemberAccountDtlCustomExample.MemberAccountDtlCustomCriteria memberAccountDtlCustomCriteria = memberAccountDtlCustomExample.createCriteria();
			memberAccountDtlCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("accId"))) {
				memberAccountDtlCustomCriteria.andAccIdEqualTo(Integer.parseInt(request.getParameter("accId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("memberNick"))) {
				memberAccountDtlCustomCriteria.andMemberNickLike("%"+request.getParameter("memberNick")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				memberAccountDtlCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				memberAccountDtlCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("bizType"))) {
				memberAccountDtlCustomCriteria.andBizTypeEqualTo(request.getParameter("bizType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("memberMobile"))) {
				memberAccountDtlCustomCriteria.andMemberMobileLike("%"+request.getParameter("memberMobile")+"%");
			}
			memberAccountDtlCustomExample.setOrderByClause(" t.id desc");
			memberAccountDtlCustomExample.setLimitStart(page.getLimitStart());
			memberAccountDtlCustomExample.setLimitSize(page.getLimitSize());
			totalCount = memberAccountDtlService.countByCustomExample(memberAccountDtlCustomExample);
			dataList = memberAccountDtlService.selectByCustomExample(memberAccountDtlCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
}

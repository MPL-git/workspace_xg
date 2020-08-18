package com.jf.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.MemberAccountDtlExample;
import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfCustom;
import com.jf.entity.SignInCnfCustomExample;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.SignInCnfDtlExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysStatus;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfExample;
import com.jf.service.MemberAccountDtlService;
import com.jf.service.SignInCnfDtlService;
import com.jf.service.SignInCnfService;
import com.jf.service.WithdrawCnfService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SignInCnfController extends BaseController {

	@Autowired
	private SignInCnfService signInCnfService;
	
	@Autowired
	private SignInCnfDtlService signInCnfDtlService;
	
	@Autowired
	private MemberAccountDtlService memberAccountDtlService;
	
	@Autowired
	private WithdrawCnfService withdrawCnfService;
	
	/**
	 * 
	 * @Title signInCnfManager   
	 * @Description TODO(签到设置)   
	 * @author pengl
	 * @date 2018年5月17日 下午4:05:03
	 */
	@RequestMapping("/signInCnf/signInCnfManager.shtml")
	public ModelAndView signInCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/signInCnf/getSignInCnfList");
		m.addObject("amountSum", signInCnfService.selectAmountSum(Arrays.asList("2", "3")));
		m.addObject("amountSumThree", signInCnfService.selectAmountSum(Arrays.asList("3")));
		m.addObject("amountSumTwo", signInCnfService.selectAmountSum(Arrays.asList("2")));
		m.addObject("memberCount", signInCnfService.selectMemberCount());
		MemberAccountDtlExample memberAccountDtlExample = new MemberAccountDtlExample();
		memberAccountDtlExample.createCriteria().andDelFlagEqualTo("0").andBizTypeIn(Arrays.asList("2", "3"));
		m.addObject("withdrawCount", memberAccountDtlService.countByExample(memberAccountDtlExample));
		return m;
	}
	
	/**
	 * 
	 * @Title getSignInCnfList   
	 * @Description TODO(签到设置)   
	 * @author pengl
	 * @date 2018年5月17日 下午4:04:55
	 */
	@ResponseBody
	@RequestMapping("/signInCnf/getSignInCnfList.shtml")
	public Map<String, Object> getSignInCnfList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SignInCnfCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SignInCnfCustomExample signInCnfCustomExample = new SignInCnfCustomExample();
			SignInCnfCustomExample.SignInCnfCustomCriteria signInCnfCustomCriteria = signInCnfCustomExample.createCriteria();
			signInCnfCustomCriteria.andDelFlagEqualTo("0");
			signInCnfCustomExample.setOrderByClause(" t.id desc");
			signInCnfCustomExample.setLimitStart(page.getLimitStart());
			signInCnfCustomExample.setLimitSize(page.getLimitSize());
			totalCount = signInCnfService.countByCustomExample(signInCnfCustomExample);
			dataList = signInCnfService.selectByCustomExample(signInCnfCustomExample);
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
	 * @Title updateIsEffect   
	 * @Description TODO(启用)   
	 * @author pengl
	 * @date 2018年5月17日 下午5:11:25
	 */
	@ResponseBody
	@RequestMapping("/signInCnf/updateIsEffect.shtml")
	public Map<String, Object> updateIsEffect(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String signInCnfId = request.getParameter("signInCnfId");
			if(!StringUtil.isEmpty(signInCnfId)) {
				SignInCnf signInCnf = new SignInCnf();
				signInCnf.setId(Integer.parseInt(signInCnfId));
				signInCnf.setIsEffect("1");
				signInCnfService.updateIsEffect(signInCnf);
			}else {
				code = "9999";
				msg = "签到方案ID为空！";
			}
		} catch (NumberFormatException e) {
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
	 * @Title addSignInCnfManager   
	 * @Description TODO(新增签到方案)   
	 * @author pengl
	 * @date 2018年5月17日 下午5:56:14
	 */
	@RequestMapping("/signInCnf/addSignInCnfManager.shtml")
	public ModelAndView addSignInCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/signInCnf/addSignInCnf");
		WithdrawCnfExample withdrawCnfExample = new WithdrawCnfExample();
		withdrawCnfExample.createCriteria().andDelFlagEqualTo("0")
			.andIsEffectEqualTo("1");
		withdrawCnfExample.setOrderByClause(" amount asc");
		List<WithdrawCnf> withdrawCnfList = withdrawCnfService.selectByExample(withdrawCnfExample);
		List<String> strList = new ArrayList<String>();
		for(WithdrawCnf withdrawCnf : withdrawCnfList) {
			strList.add(withdrawCnf.getAmount().stripTrailingZeros().toPlainString());
		}
		m.addObject("strList", strList);
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateSignInCnf   
	 * @Description TODO(新增或修改签到方案)   
	 * @author pengl
	 * @date 2018年5月18日 下午4:37:53
	 */
	@RequestMapping("/signInCnf/addOrUpdateSignInCnf.shtml")
	public ModelAndView addOrUpdateSignInCnf(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			String signInCnfId = request.getParameter("signInCnfId"); //签到设置ID
			String sqId = request.getParameter("sqId"); //首签ID
			String ljId = request.getParameter("ljId"); //连续签ID
			String oneId = request.getParameter("oneId"); //oneID
			String twoId = request.getParameter("twoId"); //twoID
			String threeId = request.getParameter("threeId"); //threeID
			String fourId = request.getParameter("fourId"); //fourID
			String qtId = request.getParameter("qtId"); //其他签ID
			String syqId = request.getParameter("syqId"); //首次好友签ID
			String xyqId = request.getParameter("xyqId"); //邀请新用户签ID
			String lyqId = request.getParameter("lyqId"); //邀请老用户签ID
			String name = request.getParameter("name");
			String baseAmount = request.getParameter("baseAmount");
			String sqBeginRate = request.getParameter("sqBeginRate");
			String sqEndRate = request.getParameter("sqEndRate");
			String ljBeginRate = request.getParameter("ljBeginRate");
			String ljEndRate = request.getParameter("ljEndRate");
			String oneBeginAmount = request.getParameter("oneBeginAmount");
			String oneEndAmount = request.getParameter("oneEndAmount");
			String oneBeginRate = request.getParameter("oneBeginRate");
			String oneEndRate = request.getParameter("oneEndRate");
			String twoEndAmount = request.getParameter("twoEndAmount");
			String twoBeginRate = request.getParameter("twoBeginRate");
			String twoEndRate = request.getParameter("twoEndRate");
			String threeEndAmount = request.getParameter("threeEndAmount");
			String threeBeginRate = request.getParameter("threeBeginRate");
			String threeEndRate = request.getParameter("threeEndRate");
			String fourEndAmount = request.getParameter("fourEndAmount");
			String fourBeginRate = request.getParameter("fourBeginRate");
			String fourEndRate = request.getParameter("fourEndRate");
			String inviteLimit = request.getParameter("inviteLimit");
			String qtBeginRate = request.getParameter("qtBeginRate");
			String qtEndRate = request.getParameter("qtEndRate");
			String syqBeginRate = request.getParameter("syqBeginRate");
			String syqEndRate = request.getParameter("syqEndRate");
			String xyqBeginRate = request.getParameter("xyqBeginRate");
			String xyqEndRate = request.getParameter("xyqEndRate");
			String lyqBeginRate = request.getParameter("lyqBeginRate");
			String lyqEndRate = request.getParameter("lyqEndRate");
			//签到方案设置
			SignInCnf signInCnf = new SignInCnf();
			if(!StringUtil.isEmpty(signInCnfId)) {
				signInCnf.setId(Integer.parseInt(signInCnfId));
			}
			signInCnf.setName(name);
			if(!StringUtil.isEmpty(baseAmount)) {
				signInCnf.setBaseAmount(new BigDecimal(baseAmount));
			}
			if(!StringUtil.isEmpty(inviteLimit)) {
				signInCnf.setInviteLimit(Integer.parseInt(inviteLimit));
			}
			signInCnf.setPopupLimit(0); //弹窗提醒次数
			signInCnf.setIsEffect("0"); //是否启用
			signInCnf.setCreateBy(staffId);
			signInCnf.setCreateDate(date);
			signInCnf.setDelFlag("0");
			//方案设置明细
			//2 首次签到
			SignInCnfDtl sqSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(sqId)) {
				sqSignInCnfDtl.setId(Integer.parseInt(sqId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				sqSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			sqSignInCnfDtl.setRateType("2");
			if(!StringUtil.isEmpty(sqBeginRate)) {
				sqSignInCnfDtl.setBeginRate(new BigDecimal(sqBeginRate));
			}
			if(!StringUtil.isEmpty(sqEndRate)) {
				sqSignInCnfDtl.setEndRate(new BigDecimal(sqEndRate));
			}
			sqSignInCnfDtl.setCreateBy(staffId);
			sqSignInCnfDtl.setCreateDate(date);
			sqSignInCnfDtl.setDelFlag("0");
			//3 累计签到n天额外比例
			SignInCnfDtl ljSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(ljId)) {
				ljSignInCnfDtl.setId(Integer.parseInt(ljId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				ljSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			ljSignInCnfDtl.setRateType("3");
			ljSignInCnfDtl.setAccumulationDay(7); //累计天数（rate_type 为3时有值且必填）
			if(!StringUtil.isEmpty(ljBeginRate)) {
				ljSignInCnfDtl.setBeginRate(new BigDecimal(ljBeginRate));
			}
			if(!StringUtil.isEmpty(ljEndRate)) {
				ljSignInCnfDtl.setEndRate(new BigDecimal(ljEndRate));
			}
			ljSignInCnfDtl.setCreateBy(staffId);
			ljSignInCnfDtl.setCreateDate(date);
			ljSignInCnfDtl.setDelFlag("0");
			//4金额区间比例
			//one
			SignInCnfDtl oneSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(oneId)) {
				oneSignInCnfDtl.setId(Integer.parseInt(oneId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				oneSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			oneSignInCnfDtl.setRateType("4");
			if(!StringUtil.isEmpty(oneBeginAmount)) {
				oneSignInCnfDtl.setBeginAmount(new BigDecimal(oneBeginAmount));
			}
			if(!StringUtil.isEmpty(oneEndAmount)) {
				oneSignInCnfDtl.setEndAmount(new BigDecimal(oneEndAmount));
			}
			if(!StringUtil.isEmpty(oneBeginRate)) {
				oneSignInCnfDtl.setBeginRate(new BigDecimal(oneBeginRate));
			}
			if(!StringUtil.isEmpty(oneEndRate)) {
				oneSignInCnfDtl.setEndRate(new BigDecimal(oneEndRate));
			}
			oneSignInCnfDtl.setCreateBy(staffId);
			oneSignInCnfDtl.setCreateDate(date);
			oneSignInCnfDtl.setDelFlag("0");
			//two
			SignInCnfDtl twoSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(twoId)) {
				twoSignInCnfDtl.setId(Integer.parseInt(twoId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				twoSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			twoSignInCnfDtl.setRateType("4");
			if(!StringUtil.isEmpty(oneEndAmount)) {
				twoSignInCnfDtl.setBeginAmount(new BigDecimal(oneEndAmount));
			}
			if(!StringUtil.isEmpty(twoEndAmount)) {
				twoSignInCnfDtl.setEndAmount(new BigDecimal(twoEndAmount));
			}
			if(!StringUtil.isEmpty(twoBeginRate)) {
				twoSignInCnfDtl.setBeginRate(new BigDecimal(twoBeginRate));
			}
			if(!StringUtil.isEmpty(twoEndRate)) {
				twoSignInCnfDtl.setEndRate(new BigDecimal(twoEndRate));
			}
			twoSignInCnfDtl.setCreateBy(staffId);
			twoSignInCnfDtl.setCreateDate(date);
			twoSignInCnfDtl.setDelFlag("0");
			//three
			SignInCnfDtl threeSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(threeId)) {
				threeSignInCnfDtl.setId(Integer.parseInt(threeId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				threeSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			threeSignInCnfDtl.setRateType("4");
			if(!StringUtil.isEmpty(twoEndAmount)) {
				threeSignInCnfDtl.setBeginAmount(new BigDecimal(twoEndAmount));
			}
			if(!StringUtil.isEmpty(threeEndAmount)) {
				threeSignInCnfDtl.setEndAmount(new BigDecimal(threeEndAmount));
			}
			if(!StringUtil.isEmpty(threeBeginRate)) {
				threeSignInCnfDtl.setBeginRate(new BigDecimal(threeBeginRate));
			}
			if(!StringUtil.isEmpty(threeEndRate)) {
				threeSignInCnfDtl.setEndRate(new BigDecimal(threeEndRate));
			}
			threeSignInCnfDtl.setCreateBy(staffId);
			threeSignInCnfDtl.setCreateDate(date);
			threeSignInCnfDtl.setDelFlag("0");
			//four
			SignInCnfDtl fourSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(fourId)) {
				fourSignInCnfDtl.setId(Integer.parseInt(fourId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				fourSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			fourSignInCnfDtl.setRateType("4");
			if(!StringUtil.isEmpty(threeEndAmount)) {
				fourSignInCnfDtl.setBeginAmount(new BigDecimal(threeEndAmount));
			}
			if(!StringUtil.isEmpty(fourEndAmount)) {
				fourSignInCnfDtl.setEndAmount(new BigDecimal(fourEndAmount));
			}
			if(!StringUtil.isEmpty(fourBeginRate)) {
				fourSignInCnfDtl.setBeginRate(new BigDecimal(fourBeginRate));
			}
			if(!StringUtil.isEmpty(fourEndRate)) {
				fourSignInCnfDtl.setEndRate(new BigDecimal(fourEndRate));
			}
			fourSignInCnfDtl.setCreateBy(staffId);
			fourSignInCnfDtl.setCreateDate(date);
			fourSignInCnfDtl.setDelFlag("0");
			//1 基础比例
			SignInCnfDtl qtSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(qtId)) {
				qtSignInCnfDtl.setId(Integer.parseInt(qtId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				qtSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			qtSignInCnfDtl.setRateType("1");
			if(!StringUtil.isEmpty(qtBeginRate)) {
				qtSignInCnfDtl.setBeginRate(new BigDecimal(qtBeginRate));
			}
			if(!StringUtil.isEmpty(qtEndRate)) {
				qtSignInCnfDtl.setEndRate(new BigDecimal(qtEndRate));
			}
			qtSignInCnfDtl.setCreateBy(staffId);
			qtSignInCnfDtl.setCreateDate(date);
			qtSignInCnfDtl.setDelFlag("0");
			//5首次好友签
			SignInCnfDtl syqSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(syqId)) {
				syqSignInCnfDtl.setId(Integer.parseInt(syqId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				syqSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			syqSignInCnfDtl.setRateType("5");
			if(!StringUtil.isEmpty(syqBeginRate)) {
				syqSignInCnfDtl.setBeginRate(new BigDecimal(syqBeginRate));
			}
			if(!StringUtil.isEmpty(syqEndRate)) {
				syqSignInCnfDtl.setEndRate(new BigDecimal(syqEndRate));
			}
			syqSignInCnfDtl.setCreateBy(staffId);
			syqSignInCnfDtl.setCreateDate(date);
			syqSignInCnfDtl.setDelFlag("0");
			//6邀请新用户签到比例
			SignInCnfDtl xyqSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(xyqId)) {
				xyqSignInCnfDtl.setId(Integer.parseInt(xyqId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				xyqSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			xyqSignInCnfDtl.setRateType("6");
			if(!StringUtil.isEmpty(xyqBeginRate)) {
				xyqSignInCnfDtl.setBeginRate(new BigDecimal(xyqBeginRate));
			}
			if(!StringUtil.isEmpty(xyqEndRate)) {
				xyqSignInCnfDtl.setEndRate(new BigDecimal(xyqEndRate));
			}
			xyqSignInCnfDtl.setCreateBy(staffId);
			xyqSignInCnfDtl.setCreateDate(date);
			xyqSignInCnfDtl.setDelFlag("0");
			//7邀请老用户签比例
			SignInCnfDtl lyqSignInCnfDtl = new SignInCnfDtl();
			if(!StringUtil.isEmpty(lyqId)) {
				lyqSignInCnfDtl.setId(Integer.parseInt(lyqId));
			}
			if(!StringUtil.isEmpty(signInCnfId)) {
				lyqSignInCnfDtl.setSignInCnfId(Integer.parseInt(signInCnfId));
			}
			lyqSignInCnfDtl.setRateType("7");
			if(!StringUtil.isEmpty(lyqBeginRate)) {
				lyqSignInCnfDtl.setBeginRate(new BigDecimal(lyqBeginRate));
			}
			if(!StringUtil.isEmpty(lyqEndRate)) {
				lyqSignInCnfDtl.setEndRate(new BigDecimal(lyqEndRate));
			}
			lyqSignInCnfDtl.setCreateBy(staffId);
			lyqSignInCnfDtl.setCreateDate(date);
			lyqSignInCnfDtl.setDelFlag("0");
			
			signInCnfService.addOrUpdateSignInCnf(signInCnf, sqSignInCnfDtl, ljSignInCnfDtl, oneSignInCnfDtl, twoSignInCnfDtl, 
					threeSignInCnfDtl, fourSignInCnfDtl, qtSignInCnfDtl, syqSignInCnfDtl, xyqSignInCnfDtl, lyqSignInCnfDtl);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
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
	 * @Title showResult   
	 * @Description TODO(新增OR修改时查看计算结果)   
	 * @author pengl
	 * @date 2018年5月19日 上午10:14:08
	 */
	@RequestMapping("/signInCnf/showResult.shtml")
	public ModelAndView showResult(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/signInCnf/showResult");
		String baseAmount = request.getParameter("baseAmount");
		String sqBeginRate = request.getParameter("sqBeginRate");
		String sqEndRate = request.getParameter("sqEndRate");
		String ljBeginRate = request.getParameter("ljBeginRate");
		String ljEndRate = request.getParameter("ljEndRate");
		String oneBeginAmount = request.getParameter("oneBeginAmount");
		String oneEndAmount = request.getParameter("oneEndAmount");
		String oneBeginRate = request.getParameter("oneBeginRate");
		String oneEndRate = request.getParameter("oneEndRate");
		String twoEndAmount = request.getParameter("twoEndAmount");
		String twoBeginRate = request.getParameter("twoBeginRate");
		String twoEndRate = request.getParameter("twoEndRate");
		String threeEndAmount = request.getParameter("threeEndAmount");
		String threeBeginRate = request.getParameter("threeBeginRate");
		String threeEndRate = request.getParameter("threeEndRate");
		String fourEndAmount = request.getParameter("fourEndAmount");
		String fourBeginRate = request.getParameter("fourBeginRate");
		String fourEndRate = request.getParameter("fourEndRate");
		String inviteLimit = request.getParameter("inviteLimit");
		String qtBeginRate = request.getParameter("qtBeginRate");
		String qtEndRate = request.getParameter("qtEndRate");
		String syqBeginRate = request.getParameter("syqBeginRate");
		String syqEndRate = request.getParameter("syqEndRate");
		String xyqBeginRate = request.getParameter("xyqBeginRate");
		String xyqEndRate = request.getParameter("xyqEndRate");
		String lyqBeginRate = request.getParameter("lyqBeginRate");
		String lyqEndRate = request.getParameter("lyqEndRate");
		
		List<SignInCnfDtl> signInCnfDtls = new ArrayList<SignInCnfDtl>();
		
		//签到方案设置
		SignInCnf signInCnf = new SignInCnf();
		if(!StringUtil.isEmpty(baseAmount)) {
			signInCnf.setBaseAmount(new BigDecimal(baseAmount));
		}
		if(!StringUtil.isEmpty(inviteLimit)) {
			signInCnf.setInviteLimit(Integer.parseInt(inviteLimit));
		}
		//方案设置明细
		//2 首次签到
		SignInCnfDtl sqSignInCnfDtl = new SignInCnfDtl();
		sqSignInCnfDtl.setRateType("2");
		if(!StringUtil.isEmpty(sqBeginRate)) {
			sqSignInCnfDtl.setBeginRate(new BigDecimal(sqBeginRate));
		}
		if(!StringUtil.isEmpty(sqEndRate)) {
			sqSignInCnfDtl.setEndRate(new BigDecimal(sqEndRate));
		}
		//3 累计签到n天额外比例
		SignInCnfDtl ljSignInCnfDtl = new SignInCnfDtl();
		ljSignInCnfDtl.setRateType("3");
		ljSignInCnfDtl.setAccumulationDay(7); //累计天数（rate_type 为3时有值且必填）
		if(!StringUtil.isEmpty(ljBeginRate)) {
			ljSignInCnfDtl.setBeginRate(new BigDecimal(ljBeginRate));
		}
		if(!StringUtil.isEmpty(ljEndRate)) {
			ljSignInCnfDtl.setEndRate(new BigDecimal(ljEndRate));
		}
		//4金额区间比例
		//one
		SignInCnfDtl oneSignInCnfDtl = new SignInCnfDtl();
		oneSignInCnfDtl.setRateType("4");
		if(!StringUtil.isEmpty(oneBeginAmount)) {
			oneSignInCnfDtl.setBeginAmount(new BigDecimal(oneBeginAmount));
		}
		if(!StringUtil.isEmpty(oneEndAmount)) {
			oneSignInCnfDtl.setEndAmount(new BigDecimal(oneEndAmount));
		}
		if(!StringUtil.isEmpty(oneBeginRate)) {
			oneSignInCnfDtl.setBeginRate(new BigDecimal(oneBeginRate));
		}
		if(!StringUtil.isEmpty(oneEndRate)) {
			oneSignInCnfDtl.setEndRate(new BigDecimal(oneEndRate));
		}
		signInCnfDtls.add(oneSignInCnfDtl);
		//two
		SignInCnfDtl twoSignInCnfDtl = new SignInCnfDtl();
		twoSignInCnfDtl.setRateType("4");
		if(!StringUtil.isEmpty(oneEndAmount)) {
			twoSignInCnfDtl.setBeginAmount(new BigDecimal(oneEndAmount));
		}
		if(!StringUtil.isEmpty(twoEndAmount)) {
			twoSignInCnfDtl.setEndAmount(new BigDecimal(twoEndAmount));
		}
		if(!StringUtil.isEmpty(twoBeginRate)) {
			twoSignInCnfDtl.setBeginRate(new BigDecimal(twoBeginRate));
		}
		if(!StringUtil.isEmpty(twoEndRate)) {
			twoSignInCnfDtl.setEndRate(new BigDecimal(twoEndRate));
		}
		signInCnfDtls.add(twoSignInCnfDtl);
		//three
		SignInCnfDtl threeSignInCnfDtl = new SignInCnfDtl();
		threeSignInCnfDtl.setRateType("4");
		if(!StringUtil.isEmpty(twoEndAmount)) {
			threeSignInCnfDtl.setBeginAmount(new BigDecimal(twoEndAmount));
		}
		if(!StringUtil.isEmpty(threeEndAmount)) {
			threeSignInCnfDtl.setEndAmount(new BigDecimal(threeEndAmount));
		}
		if(!StringUtil.isEmpty(threeBeginRate)) {
			threeSignInCnfDtl.setBeginRate(new BigDecimal(threeBeginRate));
		}
		if(!StringUtil.isEmpty(threeEndRate)) {
			threeSignInCnfDtl.setEndRate(new BigDecimal(threeEndRate));
		}
		signInCnfDtls.add(threeSignInCnfDtl);
		//four
		SignInCnfDtl fourSignInCnfDtl = new SignInCnfDtl();
		fourSignInCnfDtl.setRateType("4");
		if(!StringUtil.isEmpty(threeEndAmount)) {
			fourSignInCnfDtl.setBeginAmount(new BigDecimal(threeEndAmount));
		}
		if(!StringUtil.isEmpty(fourEndAmount)) {
			fourSignInCnfDtl.setEndAmount(new BigDecimal(fourEndAmount));
		}
		if(!StringUtil.isEmpty(fourBeginRate)) {
			fourSignInCnfDtl.setBeginRate(new BigDecimal(fourBeginRate));
		}
		if(!StringUtil.isEmpty(fourEndRate)) {
			fourSignInCnfDtl.setEndRate(new BigDecimal(fourEndRate));
		}
		signInCnfDtls.add(fourSignInCnfDtl);
		//1 基础比例
		SignInCnfDtl qtSignInCnfDtl = new SignInCnfDtl();
		qtSignInCnfDtl.setRateType("1");
		if(!StringUtil.isEmpty(qtBeginRate)) {
			qtSignInCnfDtl.setBeginRate(new BigDecimal(qtBeginRate));
		}
		if(!StringUtil.isEmpty(qtEndRate)) {
			qtSignInCnfDtl.setEndRate(new BigDecimal(qtEndRate));
		}
		//5首次好友签
		SignInCnfDtl syqSignInCnfDtl = new SignInCnfDtl();
		syqSignInCnfDtl.setRateType("5");
		if(!StringUtil.isEmpty(syqBeginRate)) {
			syqSignInCnfDtl.setBeginRate(new BigDecimal(syqBeginRate));
		}
		if(!StringUtil.isEmpty(syqEndRate)) {
			syqSignInCnfDtl.setEndRate(new BigDecimal(syqEndRate));
		}
		//6邀请新用户签到比例
		SignInCnfDtl xyqSignInCnfDtl = new SignInCnfDtl();
		xyqSignInCnfDtl.setRateType("6");
		if(!StringUtil.isEmpty(xyqBeginRate)) {
			xyqSignInCnfDtl.setBeginRate(new BigDecimal(xyqBeginRate));
		}
		if(!StringUtil.isEmpty(xyqEndRate)) {
			xyqSignInCnfDtl.setEndRate(new BigDecimal(xyqEndRate));
		}
		//7邀请老用户签比例
		SignInCnfDtl lyqSignInCnfDtl = new SignInCnfDtl();
		lyqSignInCnfDtl.setRateType("7");
		if(!StringUtil.isEmpty(lyqBeginRate)) {
			lyqSignInCnfDtl.setBeginRate(new BigDecimal(lyqBeginRate));
		}
		if(!StringUtil.isEmpty(lyqEndRate)) {
			lyqSignInCnfDtl.setEndRate(new BigDecimal(lyqEndRate));
		}
		
		float maxAmount = signInCnf.getBaseAmount().multiply(sqSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).floatValue();
		float minAmount = signInCnf.getBaseAmount().multiply(sqSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).floatValue();
		m.addObject("maxQdDay", qdDay(signInCnf, signInCnfDtls, qtSignInCnfDtl, minAmount, "begin", 1));
		m.addObject("minQdDay", qdDay(signInCnf, signInCnfDtls, qtSignInCnfDtl, maxAmount, "end", 1));
		m.addObject("maxEveryDay", signInCnf.getBaseAmount().multiply(xyqSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(signInCnf.getInviteLimit())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		m.addObject("maxQdyDay", qdyDay(signInCnf, lyqSignInCnfDtl, signInCnfDtls, qtSignInCnfDtl, minAmount, "begin", 1));
		m.addObject("minEveryDay", signInCnf.getBaseAmount().multiply(lyqSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(signInCnf.getInviteLimit())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		m.addObject("minQdyDay", qdyDay(signInCnf, xyqSignInCnfDtl, signInCnfDtls, qtSignInCnfDtl, maxAmount, "end", 1));
		
		return m;
	}
	
	/**
	 * 
	 * @Title getShowResult   
	 * @Description TODO(列表查看计算结果)   
	 * @author pengl
	 * @date 2018年5月19日 下午12:42:17
	 */
	@RequestMapping("/signInCnf/getShowResult.shtml")
	public ModelAndView getShowResult(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/signInCnf/showResult");
		String signInCnfId = request.getParameter("signInCnfId");
		if(!StringUtil.isEmpty(signInCnfId)) {
			SignInCnf signInCnf = signInCnfService.selectByPrimaryKey(Integer.parseInt(signInCnfId));
			SignInCnfDtlExample signInCnfDtlExample = new SignInCnfDtlExample();
			SignInCnfDtlExample.Criteria signInCnfDtlCriteria = signInCnfDtlExample.createCriteria();
			signInCnfDtlCriteria.andDelFlagEqualTo("0").andSignInCnfIdEqualTo(signInCnf.getId());
			List<SignInCnfDtl> signInCnfDtlList = signInCnfDtlService.selectByExample(signInCnfDtlExample);
			Map<String, SignInCnfDtl> map = new HashMap<String, SignInCnfDtl>();
			List<SignInCnfDtl> signInCnfDtls = new ArrayList<SignInCnfDtl>();
			for(SignInCnfDtl signInCnfDtl : signInCnfDtlList) {
				if("4".equals(signInCnfDtl.getRateType())) {
					signInCnfDtls.add(signInCnfDtl);
				}{
					map.put(signInCnfDtl.getRateType(), signInCnfDtl);
				}
			}
			SignInCnfDtl qtSignInCnfDtl = map.get("1");
			SignInCnfDtl sqSignInCnfDtl = map.get("2");
			SignInCnfDtl xyqSignInCnfDtl = map.get("6");
			SignInCnfDtl lyqSignInCnfDtl = map.get("7");
			if(signInCnf != null) {
				float maxAmount = signInCnf.getBaseAmount().multiply(sqSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).floatValue();
				float minAmount = signInCnf.getBaseAmount().multiply(sqSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).floatValue();
				if(sqSignInCnfDtl != null) {
					m.addObject("maxQdDay", qdDay(signInCnf, signInCnfDtls, qtSignInCnfDtl, minAmount, "begin", 1));
					m.addObject("minQdDay", qdDay(signInCnf, signInCnfDtls, qtSignInCnfDtl, maxAmount, "end", 1));
				}
				if(lyqSignInCnfDtl != null) {
					m.addObject("maxEveryDay", signInCnf.getBaseAmount().multiply(xyqSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(signInCnf.getInviteLimit())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
					m.addObject("maxQdyDay", qdyDay(signInCnf, lyqSignInCnfDtl, signInCnfDtls, qtSignInCnfDtl, minAmount, "begin", 1));
				}		
				if(xyqSignInCnfDtl != null) {
					m.addObject("minEveryDay", signInCnf.getBaseAmount().multiply(lyqSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(signInCnf.getInviteLimit())).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
					m.addObject("minQdyDay", qdyDay(signInCnf, xyqSignInCnfDtl, signInCnfDtls, qtSignInCnfDtl, maxAmount, "end", 1));
				}		
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title qdDay   
	 * @Description TODO(只签到不邀请好递归)   
	 * @author pengl
	 * @date 2018年5月19日 上午11:44:01
	 */
	public Integer qdDay(SignInCnf signInCnf, List<SignInCnfDtl> signInCnfDtls, SignInCnfDtl qtSignInCnfDtl, float amount, String flag, int day) {
		if(signInCnfDtls.size() > 0) {
			BigDecimal baseAmount = signInCnf.getBaseAmount();
			if(baseAmount != null && amount < baseAmount.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
				boolean bool = true;
				for(SignInCnfDtl signInCnfDtl : signInCnfDtls) {
					if(amount >= signInCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < signInCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
						if("begin".equals(flag)) {
							amount = baseAmount.multiply(signInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							bool = false;
							break;
						}else if("end".equals(flag)) {
							amount = baseAmount.multiply(signInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							bool = false;
							break;
						}
					}
				}
				if(bool) {
					if("begin".equals(flag)) {
						amount = baseAmount.multiply(qtSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = baseAmount.multiply(qtSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
				}
				++day;
				return qdDay(signInCnf, signInCnfDtls, qtSignInCnfDtl, amount, flag, day);
			}else {
				return day;
			}
		}
		return 1;
	}
	
	/**
	 * 
	 * @Title qdyDay   
	 * @Description TODO(签到并邀请好友递归)   
	 * @author pengl
	 * @date 2018年5月19日 上午11:44:01
	 */
	public Integer qdyDay(SignInCnf signInCnf, SignInCnfDtl yqSignInCnfDtl, List<SignInCnfDtl> signInCnfDtls, SignInCnfDtl qtSignInCnfDtl, float amount, String flag, int day) {
		if(signInCnfDtls.size() > 0) {
			BigDecimal baseAmount = signInCnf.getBaseAmount();
			Integer inviteLimit  = signInCnf.getInviteLimit();
			if(baseAmount != null && inviteLimit != null && amount < baseAmount.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
				boolean bool = true;
				for(SignInCnfDtl signInCnfDtl : signInCnfDtls) {
					if(amount >= signInCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < signInCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
						if("begin".equals(flag)) {
							amount = baseAmount.multiply(signInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount))
									.add(baseAmount.multiply(yqSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(inviteLimit))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							bool = false;
							break;
						}else if("end".equals(flag)) {
							amount = baseAmount.multiply(signInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount))
									.add(baseAmount.multiply(yqSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(inviteLimit))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							bool = false;
							break;
						}
					}
				}
				if(bool) {
					if("begin".equals(flag)) {
						amount = baseAmount.multiply(qtSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount))
								.add(baseAmount.multiply(yqSignInCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(inviteLimit))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = baseAmount.multiply(qtSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount))
								.add(baseAmount.multiply(yqSignInCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(inviteLimit))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
				}
				++day;
				return qdyDay(signInCnf, yqSignInCnfDtl, signInCnfDtls, qtSignInCnfDtl, amount, flag, day);
			}else {
				return day;
			}
		}
		return 1;
	} 
	
	/**
	 * 
	 * @Title updateSignInCnf   
	 * @Description TODO(编辑)   
	 * @author pengl
	 * @date 2018年5月19日 下午2:27:39
	 */
	@RequestMapping("/signInCnf/updateSignInCnfManager.shtml")
	public ModelAndView updateSignInCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/signInCnf/updateSignInCnf");
		String signInCnfId = request.getParameter("signInCnfId");
		if(!StringUtil.isEmpty(signInCnfId)) {
			SignInCnf signInCnf = signInCnfService.selectByPrimaryKey(Integer.parseInt(signInCnfId));
			SignInCnfDtlExample signInCnfDtlExample = new SignInCnfDtlExample();
			SignInCnfDtlExample.Criteria signInCnfDtlCriteria = signInCnfDtlExample.createCriteria();
			signInCnfDtlCriteria.andDelFlagEqualTo("0").andSignInCnfIdEqualTo(signInCnf.getId());
			List<SignInCnfDtl> signInCnfDtlList = signInCnfDtlService.selectByExample(signInCnfDtlExample);
			Map<String, SignInCnfDtl> map = new HashMap<String, SignInCnfDtl>();
			List<SignInCnfDtl> signInCnfDtls = new ArrayList<SignInCnfDtl>();
			for(SignInCnfDtl signInCnfDtl : signInCnfDtlList) {
				if("4".equals(signInCnfDtl.getRateType())) {
					signInCnfDtls.add(signInCnfDtl);
				}else {
					map.put(signInCnfDtl.getRateType(), signInCnfDtl);
				}
			}
			Collections.sort(signInCnfDtls, new Comparator<SignInCnfDtl>() {  
	            @Override  
	            public int compare(SignInCnfDtl o1, SignInCnfDtl o2) {  
	                int i = o1.getEndAmount().intValue() - o2.getEndAmount().intValue();  
	                if(i == 0){  
	                    return o1.getEndAmount().intValue() - o2.getEndAmount().intValue();  
	                }  
	                return i;  
	            }  
	        }); 
			m.addObject("oneSignInCnfDtl", signInCnfDtls.get(0));
			m.addObject("twoSignInCnfDtl", signInCnfDtls.get(1));
			m.addObject("threeSignInCnfDtl", signInCnfDtls.get(2));
			m.addObject("fourSignInCnfDtl", signInCnfDtls.get(3));
			
			m.addObject("signInCnf", signInCnf);
			m.addObject("baseAmount", signInCnf.getBaseAmount().stripTrailingZeros().toPlainString());
			m.addObject("qtSignInCnfDtl", map.get("1"));
			m.addObject("sqSignInCnfDtl", map.get("2"));
			m.addObject("ljSignInCnfDtl", map.get("3"));
			m.addObject("syqSignInCnfDtl", map.get("5"));
			m.addObject("xyqSignInCnfDtl", map.get("6"));
			m.addObject("lyqSignInCnfDtl", map.get("7"));
			
			WithdrawCnfExample withdrawCnfExample = new WithdrawCnfExample();
			withdrawCnfExample.createCriteria().andDelFlagEqualTo("0")
				.andIsEffectEqualTo("1");
			withdrawCnfExample.setOrderByClause(" amount asc");
			List<WithdrawCnf> withdrawCnfList = withdrawCnfService.selectByExample(withdrawCnfExample);
			List<String> strList = new ArrayList<String>();
			for(WithdrawCnf withdrawCnf : withdrawCnfList) {
				strList.add(withdrawCnf.getAmount().stripTrailingZeros().toPlainString());
			}
			m.addObject("strList", strList);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title signInCnfStatisticsManager   
	 * @Description TODO(现金签到数据统计)   
	 * @author pengl
	 * @date 2018年6月12日 下午2:40:45
	 */
	@RequestMapping("/signInCnf/signInCnfStatisticsManager.shtml")
	public ModelAndView signInCnfStatisticsManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/signInCnf/signInCnfStatisticsList");
		m.addObject("dateFlag", "day");
		return m;
	}
	
	/**
	 * 
	 * @Title signInCnfStatisticsManager   
	 * @Description TODO(现金签到数据统计)   
	 * @author pengl
	 * @date 2018年6月12日 下午2:40:49
	 */
	@ResponseBody
	@RequestMapping("/signInCnf/signInCnfStatisticsList.shtml")
	public Map<String, Object> signInCnfStatisticsList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			List<String> dateList = new ArrayList<String>();
			Date date = new Date();
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			String dateFlag = "";
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			if(!StringUtil.isEmpty(beginDate) || !StringUtil.isEmpty(endDate)) {
				if(!StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						if(sdfDay.parse(endDate).getTime() > date.getTime()) {
							endDate = sdfDay.format(date);
						}
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(endDate), sdfDay, "day");
					}
				}else if(!StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
					}
				}else if(StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(endDate).getTime() > date.getTime()) {
						endDate = sdfDay.format(date);
					}
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(endDate), sdfDay, "day");
				}
				dateFlag = "day";
			}else {
				if("day".equals(request.getParameter("dateFlag"))) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
				}else if("month".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.MONTH, 0);
					dateList = DateUtil.getBetweenDates(sdf.parse(sdf.format(cal.getTime())), sdf.parse(sdf.format(date)), sdf, "month");
				}else if("year".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -1);
					String beginYear = sdf.format(cal.getTime());
					String endYear = sdf.format(date);
					dateList.add(beginYear);
					dateList.add(endYear);
				}
				dateFlag = request.getParameter("dateFlag");
			}
			totalCount = dateList.size();
			int limitStart = page.getLimitStart();
			int limitSize = page.getLimitSize();
			int endIndex = limitStart + limitSize;
			if(endIndex > totalCount.intValue()) {
				endIndex = totalCount.intValue();
			}
			List<String> dates = new ArrayList<String>();
			for(int i=limitStart;i<endIndex;i++) {
				dates.add(dateList.get(i));
			}
			dataList = signInCnfService.selectSignInCnfStatisticsList(dates, dateFlag);
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
	 * @Title exportSignInCnfStatistics   
	 * @Description TODO(导出)   
	 * @author pengl
	 * @date 2018年6月13日 下午3:35:25
	 */
	@RequestMapping("/signInCnf/exportSignInCnfStatistics.shtml")
	public void exportSignInCnfStatistics(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> dateList = new ArrayList<String>();
			Date date = new Date();
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			String dateFlag = "";
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			if(!StringUtil.isEmpty(beginDate) || !StringUtil.isEmpty(endDate)) {
				if(!StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						if(sdfDay.parse(endDate).getTime() > date.getTime()) {
							endDate = sdfDay.format(date);
						}
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(endDate), sdfDay, "day");
					}
				}else if(!StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
					}
				}else if(StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(endDate).getTime() > date.getTime()) {
						endDate = sdfDay.format(date);
					}
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(endDate), sdfDay, "day");
				}
				dateFlag = "day";
			}else {
				if("day".equals(request.getParameter("dateFlag"))) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
				}else if("month".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.MONTH, 0);
					dateList = DateUtil.getBetweenDates(sdf.parse(sdf.format(cal.getTime())), sdf.parse(sdf.format(date)), sdf, "month");
				}else if("year".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -1);
					String beginYear = sdf.format(cal.getTime());
					String endYear = sdf.format(date);
					dateList.add(beginYear);
					dateList.add(endYear);
				}
				dateFlag = request.getParameter("dateFlag");
			}
			List<Map<String, Object>> dataList = signInCnfService.selectSignInCnfStatisticsList(dateList, dateFlag);
			String[] titles = {"时间","签到人数","其中新用户人数","累计签到金额","提现金额","购买人数","其中新用户购买人数","订单数","其中新用户产生订单数","预估收益","其中新用户产生预估收益","消费转化率%"};
			ExcelBean excelBean = new ExcelBean("现金签到数据统计.xls", "现金签到数据统计", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String, Object> map : dataList) {
				String[] data = {
						map.get("date").toString(),
						map.get("qd_count").toString(),
						map.get("new_member_count").toString(),
						map.get("lj_amount_sum").toString(),
						map.get("tx_amount_sum").toString(),
						map.get("gm_count").toString(),
						map.get("xy_gm_count").toString(),
						map.get("dd_count").toString(),
						map.get("xy_dd_count").toString(),
						map.get("yg_commission_amount_sum").toString(),
						map.get("xy_yg_commission_amount_sum").toString(),
						map.get("con_percent").toString()
					};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

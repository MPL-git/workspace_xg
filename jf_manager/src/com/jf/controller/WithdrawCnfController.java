package com.jf.controller;

import java.math.BigDecimal;
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
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfCustom;
import com.jf.entity.WithdrawCnfCustomExample;
import com.jf.service.CouponService;
import com.jf.service.WithdrawCnfService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class WithdrawCnfController extends BaseController {

	@Autowired
	private WithdrawCnfService withdrawCnfService;
	
	@Autowired
	private CouponService couponService;
	
	/**
	 * 
	 * @Title withdrawCnfManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月21日 上午10:46:43
	 */
	@RequestMapping("/withdrawCnf/withdrawCnfManager.shtml")
	public ModelAndView withdrawCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/withdrawCnf/getWithdrawCnfList");
		m.addObject("withdrawTypeList", DataDicUtil.getTableStatus("BU_WITHDRAW_CNF", "WITHDRAW_TYPE"));
		return m;
	}
	
	/**
	 * 
	 * @Title getWithdrawCnfList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月21日 上午10:46:40
	 */
	@ResponseBody
	@RequestMapping("/withdrawCnf/getWithdrawCnfList.shtml")
	public Map<String, Object> getWithdrawCnfList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<WithdrawCnfCustom> dataList = null;
		Integer totalCount = 0;
		try {
			WithdrawCnfCustomExample withdrawCnfCustomExample = new WithdrawCnfCustomExample();
			WithdrawCnfCustomExample.Criteria withdrawCnfCustomCriteria = withdrawCnfCustomExample.createCriteria();
			withdrawCnfCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("name"))) {
				withdrawCnfCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawType"))) {
				withdrawCnfCustomCriteria.andWithdrawTypeEqualTo(request.getParameter("withdrawType"));
			}
			withdrawCnfCustomExample.setLimitStart(page.getLimitStart());
			withdrawCnfCustomExample.setLimitSize(page.getLimitSize());
			totalCount = withdrawCnfService.countByCustomExample(withdrawCnfCustomExample);
			dataList = withdrawCnfService.selectByCustomExample(withdrawCnfCustomExample);
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
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月21日 下午12:02:51
	 */
	@ResponseBody
	@RequestMapping("/withdrawCnf/updateIsEffect.shtml")
	public Map<String, Object> updateIsEffect(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String withdrawCnfId = request.getParameter("withdrawCnfId");
			if(!StringUtil.isEmpty(withdrawCnfId)) {
				WithdrawCnf withdrawCnf = new WithdrawCnf();
				withdrawCnf.setId(Integer.parseInt(withdrawCnfId));
				if(!StringUtil.isEmpty(request.getParameter("isEffect"))) {
					if("0".equals(request.getParameter("isEffect"))) { //0 未启用
						withdrawCnf.setIsEffect("1");
					}else if("1".equals(request.getParameter("isEffect"))) { //1启用
						withdrawCnf.setIsEffect("0");
					}
				}
				withdrawCnfService.updateByPrimaryKeySelective(withdrawCnf);
			}else {
				code = "9999";
				msg = "提现ID为空！";
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
	 * @Title addWithdrawCnfManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月21日 下午12:49:22
	 */
	@RequestMapping("/withdrawCnf/addWithdrawCnfManager.shtml")
	public ModelAndView addWithdrawCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/signIn/withdrawCnf/addOrUpdateWithdrawCnf");
		if(!StringUtil.isEmpty(request.getParameter("withdrawCnfId"))) {
			WithdrawCnf withdrawCnf = withdrawCnfService.selectByPrimaryKey(Integer.parseInt(request.getParameter("withdrawCnfId")));
			m.addObject("withdrawCnf", withdrawCnf);
			m.addObject("amount", withdrawCnf.getAmount().intValue());
			if("1".equals(withdrawCnf.getWithdrawType()) && withdrawCnf.getCouponId() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Coupon coupon = couponService.selectByPrimaryKey(withdrawCnf.getCouponId());
				m.addObject("coupon", coupon);
				if(!StringUtil.isEmpty(coupon.getExpiryType()) && "1".equals(coupon.getExpiryType())) {
					String expiryDate = "";
					if(coupon.getExpiryBeginDate() != null) {
						expiryDate = "起 "+sdf.format(coupon.getExpiryBeginDate());
					}
					if(coupon.getExpiryEndDate() != null) {
						if(!"".equals(expiryDate)) {
							expiryDate += "\n止 "+sdf.format(coupon.getExpiryEndDate());
						}else {
							expiryDate = "止 "+sdf.format(coupon.getExpiryEndDate());
						}
					}
					m.addObject("expiryDate", expiryDate);
				}else if(!StringUtil.isEmpty(coupon.getExpiryType()) && "2".equals(coupon.getExpiryType())) {
					m.addObject("expiryDate", coupon.getExpiryDays()+"天");
				}
				if(coupon.getMoney() != null) {
					String couponMoney = "";
					if(coupon.getMinimum() == null) {
						couponMoney = coupon.getMoney()+"元";
					}else {
						couponMoney = coupon.getMoney()+"元 / 满"+coupon.getMinimum()+"元";
					}
					m.addObject("couponMoney", couponMoney);
				}
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title choiceCouponId   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月21日 下午4:12:59
	 */
	@ResponseBody
	@RequestMapping("/withdrawCnf/choiceCouponId.shtml")
	public Map<String, Object> choiceCouponId(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String couponId = request.getParameter("couponId");
			if(!StringUtil.isEmpty(couponId)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				CouponExample couponExample = new CouponExample();
				CouponExample.Criteria couponCriteria = couponExample.createCriteria();
				couponCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1")
//优惠券类型（签到）					.andRangEqualTo("4")
					.andIdEqualTo(Integer.parseInt(couponId));
				List<Coupon> couponList = couponService.selectByExample(couponExample);
				if(couponList != null && couponList.size() > 0) {
					Coupon coupon = couponList.get(0);
					map.put("coupon", coupon);
					if(!StringUtil.isEmpty(coupon.getExpiryType()) && "1".equals(coupon.getExpiryType())) {
						String expiryDate = "";
						if(coupon.getExpiryBeginDate() != null) {
							expiryDate = "起 "+sdf.format(coupon.getExpiryBeginDate());
						}
						if(coupon.getExpiryEndDate() != null) {
							if(!"".equals(expiryDate)) {
								expiryDate += "\n止 "+sdf.format(coupon.getExpiryEndDate());
							}else {
								expiryDate = "止 "+sdf.format(coupon.getExpiryEndDate());
							}
						}
						map.put("expiryDate", expiryDate);
					}else if(!StringUtil.isEmpty(coupon.getExpiryType()) && "2".equals(coupon.getExpiryType())) {
						map.put("expiryDate", coupon.getExpiryDays()+"天");
					}
				}
			}else {
				code = "9999";
				msg = "提现ID为空！";
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
	 * @Title addOrUpdateWithdrawCnf   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月21日 下午4:13:35
	 */
	@RequestMapping("/withdrawCnf/addOrUpdateWithdrawCnf.shtml")
	public ModelAndView addOrUpdateWithdrawCnf(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			WithdrawCnf withdrawCnf = new WithdrawCnf();
			withdrawCnf.setWithdrawType(request.getParameter("withdrawType"));
			withdrawCnf.setName(request.getParameter("name"));
			if(!StringUtil.isEmpty(request.getParameter("couponId"))) {
				withdrawCnf.setCouponId(Integer.parseInt(request.getParameter("couponId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("amount"))) {
				withdrawCnf.setAmount(new BigDecimal(request.getParameter("amount")));
			}
			withdrawCnf.setIsEffect("0");
			withdrawCnf.setCreateBy(staffId);
			withdrawCnf.setCreateDate(date);
			withdrawCnf.setDelFlag("0");
			if(!StringUtil.isEmpty(request.getParameter("remarks"))) {
				withdrawCnf.setRemarks(request.getParameter("remarks"));
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawCnfId"))) {
				withdrawCnf.setId(Integer.parseInt(request.getParameter("withdrawCnfId")));
				withdrawCnfService.updateByPrimaryKeySelective(withdrawCnf); //保存
			}else {
				withdrawCnfService.insertSelective(withdrawCnf); //修改
			}
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
	
	
}

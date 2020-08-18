package com.jf.controller;

import com.jf.entity.ActivityAuth;
import com.jf.entity.ActivityCustom;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 活动商品权限控制
 * @author Administrator
 *
 */
@Controller
public class ActivityAuthController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2300893054575152740L;
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private FullCutService fullCutService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private FullGiveService fullGiveService;
	
	@Autowired
	private ActivityAuthService activityAuthService;

	
	/**
	 * 权限控制页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/activityAuthInfo.shtml")
	public String activityAuthList(Model model,HttpServletRequest request){
//		审核专员类型
		Integer type=Integer.valueOf(request.getParameter("type"));
		model.addAttribute("type", type);
//		活动id
		Integer activityId=Integer.valueOf(request.getParameter("activityId"));
		model.addAttribute("activityId", activityId);
		ActivityCustom activityCustom=activityService.selectActivityCustomByPrimaryKey(activityId);
		model.addAttribute("activityCustom", activityCustom);
		
		ActivityAuth activityAuth=activityAuthService.selectByActivityAuthCustomExample(activityId);
		model.addAttribute("activityAuth", activityAuth);
		Integer select=activityAuthService.selectByActivityAuthSelect(activityId);
		if(select==null||select==0){
			model.addAttribute("selectAll", 0);
		}else{
			model.addAttribute("selectAll", 1);
		}
		Integer productAll=activityAuthService.selectByActivityAuthProductAll(activityId);
		if(productAll==null||productAll==0){
			model.addAttribute("productAll", 0);
		}else{
			model.addAttribute("productAuthAll", 1);
		}
		return "/activity/activityAuthInfo";
	}
	
	/**
	 * 控制权限保存
	 * @param request
	 * @param response
	 * @param activityAuth
	 * @return
	 */
	@RequestMapping(value="/activity/addActivityAuth.shtml")
	public ModelAndView addActivityAuth(HttpServletRequest request,HttpServletResponse response, ActivityAuth activityAuth){
		
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
//			Integer type=Integer.valueOf(request.getParameter("type"));
			Integer activityId=Integer.valueOf(request.getParameter("activityId"));
			if(activityAuth.getId()==null){
				activityAuth.setCreateBy(staffId);
				activityAuth.setCreateDate(new Date());
				activityAuth.setUpdateBy(staffId);
				activityAuth.setUpdateDate(new Date());
				activityAuthService.insertSelective(activityAuth);
			}else{
				ActivityAuth activity=activityAuthService.selectByActivityAuthCustomExample(activityId);
				activity.setUpdateBy(staffId);
				activity.setUpdateDate(new Date());
				activity.setActivityNameFlag(activityAuth.getActivityNameFlag());
				activity.setActivityTypeFlag(activityAuth.getActivityTypeFlag());
				activity.setActivityBrandFlag(activityAuth.getActivityBrandFlag());
				activity.setActivityBenefitFlag(activityAuth.getActivityBenefitFlag());
				activity.setActivityEntryFlag(activityAuth.getActivityEntryFlag());
				activity.setActivityPosterFlag(activityAuth.getActivityPosterFlag());
				activity.setActivityPreferentialFlag(activityAuth.getActivityPreferentialFlag());
				activity.setActivityModifyFlag(activityAuth.getActivityModifyFlag());
				activity.setProductTypeBrandFlag(activityAuth.getProductTypeBrandFlag());
				activity.setProductNamePropFlag(activityAuth.getProductNamePropFlag());
				activity.setProductPicFlag(activityAuth.getProductPicFlag());
				activity.setProductDescPicFlag(activityAuth.getProductDescPicFlag());
				activity.setProductPropNumFlag(activityAuth.getProductPropNumFlag());
				activity.setProductPropPriceFlag(activityAuth.getProductPropPriceFlag());
				activity.setProductPropStockFlag(activityAuth.getProductPropStockFlag());
				activity.setProductOtherFlag(activityAuth.getProductOtherFlag());
				activityAuthService.updateByPrimaryKeySelective(activity);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 单品权限控制保存入口
	 * @param request
	 * @param response
	 * @param activityAuth
	 * @return
	 */
	@RequestMapping(value="/activity/singleActivityAuth.shtml")
	public ModelAndView singleActivityAuth(HttpServletRequest request,HttpServletResponse response,@Valid ActivityAuth activityAuth){
		
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			Integer activityId=Integer.valueOf(request.getParameter("activityId"));
			if(activityAuth.getId()==null){
				activityAuth.setCreateBy(staffId);
				activityAuth.setCreateDate(new Date());
				activityAuth.setUpdateBy(staffId);
				activityAuth.setUpdateDate(new Date());
				activityAuthService.insertSelective(activityAuth);
			}else{
				ActivityAuth activity=activityAuthService.selectByActivityAuthCustomExample(activityId);
				activity.setUpdateBy(staffId);
				activity.setUpdateDate(new Date());
				if(activityAuth.getActivityModifyFlag()==null){
					activity.setActivityModifyFlag("0");
				}else{
					activity.setActivityModifyFlag(activityAuth.getActivityModifyFlag());
				}
				if(activityAuth.getProductTypeBrandFlag()==null){
					activity.setProductTypeBrandFlag("0");
				}else{
					activity.setProductTypeBrandFlag(activityAuth.getProductTypeBrandFlag());
				}
				if(activityAuth.getProductNamePropFlag()==null){
					activity.setProductNamePropFlag("0");
				}else{
					activity.setProductNamePropFlag(activityAuth.getProductNamePropFlag());
				}
				if(activityAuth.getProductPicFlag()==null){
					activity.setProductPicFlag("0");
				}else{
					activity.setProductPicFlag(activityAuth.getProductPicFlag());
				}
				if(activityAuth.getProductDescPicFlag()==null){
					activity.setProductDescPicFlag("0");
				}else{
					activity.setProductDescPicFlag(activityAuth.getProductDescPicFlag());
				}
				if(activityAuth.getProductPropNumFlag()==null){
					activity.setProductPropNumFlag("0");
				}else{
					activity.setProductPropNumFlag(activityAuth.getProductPropNumFlag());
				}
				if(activityAuth.getProductPropPriceFlag()==null){
					activity.setProductPropPriceFlag("0");
				}else{
					activity.setProductPropPriceFlag(activityAuth.getProductPropPriceFlag());
				}
				if(activityAuth.getProductPropStockFlag()==null){
					activity.setProductPropStockFlag("0");
				}else{
					activity.setProductPropStockFlag(activityAuth.getProductPropStockFlag());
				}
				if(activityAuth.getProductOtherFlag()==null){
					activity.setProductOtherFlag("0");
				}else{
					activity.setProductOtherFlag(activityAuth.getProductOtherFlag());
				}
				activityAuthService.updateByPrimaryKeySelective(activity);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 官方会场管理权限设置入口
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/activity/venueActivityOperateAndCooAuth.shtml")
	public String venueActivityOperateAndCooAuth(Model model,HttpServletRequest request){
		Integer activityId=Integer.valueOf(request.getParameter("activityId"));
		model.addAttribute("activityId", activityId);
//		Integer type=Integer.valueOf(request.getParameter("type"));
//		model.addAttribute("type", type);
		ActivityAuth activityAuth= activityAuthService.selectByActivityAuthCustomExample(activityId);
		model.addAttribute("activityAuth", activityAuth);
		Integer productAll=activityAuthService.selectByActivityAuthProductAll(activityId);
		if(productAll==null||productAll==0){
			model.addAttribute("productAuthAll", 0);
		}else{
			model.addAttribute("productAuthAll", 1);
		}
		return "/activity/venueActivityAuth";
	}

}

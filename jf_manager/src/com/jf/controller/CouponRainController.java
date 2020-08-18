package com.jf.controller;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainCustom;
import com.jf.entity.CouponRainCustomExample;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.CouponRainSetupCustom;
import com.jf.entity.CouponRainSetupCustomExample;
import com.jf.entity.CouponRainSetupExample;
import com.jf.entity.StaffBean;
import com.jf.service.CouponRainService;
import com.jf.service.CouponRainSetupService;
import com.jf.service.CouponService;
import com.jf.vo.Page;

@Controller
public class CouponRainController extends BaseController {
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private CouponRainService couponRainService;
	
	@Resource
	private CouponRainSetupService couponRainSetupService;
	
	@Resource
	private CouponService couponService;
	
	//红包雨管理
	@RequestMapping("/couponRain/couponRainSetup.shtml")
	public ModelAndView couponRainSetup(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/couponRain/couponRainSetup");
		return m;
	}
	
	//红包雨管理数据
	@ResponseBody
	@RequestMapping("/couponRain/couponRainSetupData.shtml")
	public Map<String, Object> couponRainSetupData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CouponRainSetupCustom> couponRainSetupCustoms=null;
		Integer totalCount = 0;	
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CouponRainSetupCustomExample couponRainSetupCustomExample=new CouponRainSetupCustomExample();
			CouponRainSetupCustomExample.CouponRainSetupCustomCriteria couponRainSetupCustomCriteria=couponRainSetupCustomExample.createCriteria();
			couponRainSetupCustomCriteria.andDelFlagEqualTo("0");
			couponRainSetupCustomExample.setOrderByClause("create_date desc");
			
			if (!StringUtil.isEmpty(request.getParameter("title"))) {
				couponRainSetupCustomCriteria.andTitleLike("%"+request.getParameter("title")+"%");
			 }
			if (!StringUtil.isEmpty(request.getParameter("includeProductCoupon"))) {
				couponRainSetupCustomCriteria.andIncludeProductCouponEqualTo(request.getParameter("includeProductCoupon"));
			 }
			totalCount = couponRainSetupService.countByCouponRainSetupCustomExample(couponRainSetupCustomExample);
			couponRainSetupCustomExample.setLimitStart(page.getLimitStart());
			couponRainSetupCustomExample.setLimitSize(page.getLimitSize());
			couponRainSetupCustoms=couponRainSetupService.selectByCouponRainSetupCustomExample(couponRainSetupCustomExample);
			
			
			Date dateCurrent=new Date();//当前时间
			
			for (CouponRainSetupCustom couponRainSetupCustom : couponRainSetupCustoms) {
				
				HashMap<String,Object> map = new HashMap<String,Object>();
				String recbeginDate=df.format(couponRainSetupCustom.getRecBeginDate());//有效开始时间
				String recendDate=df.format(couponRainSetupCustom.getRecEndDate());//有效结束时间
				
				map.put("includeProductCoupon", couponRainSetupCustom.getIncludeProductCoupon());//是否支持商品劵
				map.put("includePlatformCoupon", couponRainSetupCustom.getIncludePlatformCoupon());//是否支持平台劵
				
			 if (!couponRainSetupCustom.getRecBeginDate().after(dateCurrent) && !dateCurrent.after(couponRainSetupCustom.getRecEndDate())){
					String dateCurrent1=df.format(dateCurrent);
					map.put("recbeginDate", dateCurrent1);
					map.put("recendDate", recendDate);

					if (couponRainSetupCustom.getIncludePlatformCoupon().equals("1")) {
						Integer platformSum=couponService.platformSum(map);//平台可领取数量
						couponRainSetupCustom.setPlatformSum(platformSum);	
					}
					 
					Integer effectiveCouponCount=couponService.effectiveCouponCount(map);//有效优惠券个数
					couponRainSetupCustom.setEffectiveCouponCount(effectiveCouponCount);
					
					if (couponRainSetupCustom.getIncludeProductCoupon().equals("1")) {
						 Integer commoditySum=couponService.commoditySum(map);//商品劵可领取数量
						 couponRainSetupCustom.setCommoditySum(commoditySum);
					}
					
				}else if (dateCurrent.before(couponRainSetupCustom.getRecBeginDate())) {
					
					map.put("recbeginDate", recbeginDate);
					map.put("recendDate", recendDate);
				
					
					Integer effectiveCouponCount=couponService.effectiveCouponCount(map);//有效优惠券个数
					couponRainSetupCustom.setEffectiveCouponCount(effectiveCouponCount);
					
					if (couponRainSetupCustom.getIncludePlatformCoupon().equals("1")) {
						Integer platformSum=couponService.platformSum(map);//平台可领取数量
						couponRainSetupCustom.setPlatformSum(platformSum);	
					}
					 
					if (couponRainSetupCustom.getIncludeProductCoupon().equals("1")) {
						 Integer commoditySum=couponService.commoditySum(map);//商品劵可领取数量
						 couponRainSetupCustom.setCommoditySum(commoditySum);
					}
				}
								 	  
			}
			
			resMap.put("Rows", couponRainSetupCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	    //新增红包雨管理
		@RequestMapping(value = "/couponRain/addCouponRainSetup.shtml")
		public ModelAndView addCouponRainSetup(HttpServletRequest request) {
			String rtPage = "/couponRain/addcouponRainSetup";
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			String couponRainSetupid=request.getParameter("id");
			if (!StringUtil.isEmpty(couponRainSetupid)) {
				CouponRainSetup couponRainSetup=couponRainSetupService.selectByPrimaryKey(Integer.valueOf(couponRainSetupid));
				resMap.put("couponRainSetup", couponRainSetup);
				
				if (couponRainSetup.getIncludeIntegralPercent()!=null) {
					String IncludeIntegralPercent1=couponRainSetup.getIncludeIntegralPercent().toString();
					BigDecimal IncludeIntegralPercent = new BigDecimal(IncludeIntegralPercent1).multiply(new BigDecimal(100)).setScale(2);
					resMap.put("includeIntegralPercent", IncludeIntegralPercent);
					
				}
				if (couponRainSetup.getIncludeProductCouponPercent()!=null) {
					String IncludeProductCouponPercent1=couponRainSetup.getIncludeProductCouponPercent().toString();
					BigDecimal IncludeProductCouponPercent = new BigDecimal(IncludeProductCouponPercent1).multiply(new BigDecimal(100)).setScale(2);
					resMap.put("includeProductCouponPercent", IncludeProductCouponPercent);
				}
				if (couponRainSetup.getIncludeSalePercent()!=null) {
					String IncludeSalePercent1=couponRainSetup.getIncludeSalePercent().toString();
					BigDecimal IncludeSalePercent = new BigDecimal(IncludeSalePercent1).multiply(new BigDecimal(100)).setScale(2);
					resMap.put("includeSalePercent", IncludeSalePercent);
				}
				if (couponRainSetup.getIncludePlatformCoupon()!=null && couponRainSetup.getIncludePlatformCouponPercent()!=null) {
					String IncludePlatformCouponPercent1=couponRainSetup.getIncludePlatformCouponPercent().toString();
					BigDecimal IncludePlatformCouponPercent = new BigDecimal(IncludePlatformCouponPercent1).multiply(new BigDecimal(100)).setScale(2);
					resMap.put("includePlatformCouponPercent", IncludePlatformCouponPercent);
				}
			}
			
			return new ModelAndView(rtPage, resMap);
	  }
		
		//保存,编辑红包雨管理
		@RequestMapping(value = "/couponRain/addcouponRainSetup.shtml")
		@ResponseBody
	 	public Map<String, Object> addcouponRainSetup(HttpServletRequest request,CouponRainSetup couponRainSetup,String includeProductCouponPercent,String includeIntegralPercent,String includeSalePercent,String includePlatformCouponPercent){
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			String code = null;
			String msg =null;
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 String recEndDate=request.getParameter("recEndDate");
				 String recBeginDate=request.getParameter("recBeginDate");
		         if (couponRainSetup.getId()==null) {//添加
		             couponRainSetup.setCreateBy(staffId);
		             couponRainSetup.setCreateDate(new Date());
		             if (!StringUtils.isEmpty(includeProductCouponPercent) && couponRainSetup.getIncludeProductCoupon().equals("1")) {
		            	 BigDecimal includeProductCouponPercents = new BigDecimal(includeProductCouponPercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup.setIncludeProductCouponPercent(includeProductCouponPercents);
		 			 }
		             if (!StringUtils.isEmpty(includeIntegralPercent) && couponRainSetup.getIncludeIntegral().equals("1")) {
		            	 BigDecimal includeIntegralPercents = new BigDecimal(includeIntegralPercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup.setIncludeIntegralPercent(includeIntegralPercents);
		 			 }
		             if (!StringUtils.isEmpty(includeSalePercent) && couponRainSetup.getIncludeSale().equals("1")) {
		            	 BigDecimal includeSalePercents = new BigDecimal(includeSalePercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup.setIncludeSalePercent(includeSalePercents);
		 			 }
		             if (!StringUtils.isEmpty(includePlatformCouponPercent) && couponRainSetup.getIncludePlatformCoupon().equals("1")) {
		            	 BigDecimal includePlatformCouponPercents = new BigDecimal(includePlatformCouponPercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup.setIncludePlatformCouponPercent(includePlatformCouponPercents);
		 			 }
		  
		             couponRainSetup.setRecBeginDate(sdf.parse(recBeginDate+":00"));
		             couponRainSetup.setRecEndDate(sdf.parse(recEndDate+":59"));
		             couponRainSetup.setDelFlag("0");
		             couponRainSetupService.insert(couponRainSetup);
		             
					
				}else {//更新
					 
					String couponRainSetupid=request.getParameter("id");
					String includeProductCoupon=request.getParameter("includeProductCoupon");
					String includeIntegral=request.getParameter("includeIntegral");
					String limitRecCount=request.getParameter("limitRecCount");
					String includeIntegralMin=request.getParameter("includeIntegralMin");
					String includeIntegralMax=request.getParameter("includeIntegralMax");
					String pic=request.getParameter("pic");
					String includeSale=request.getParameter("includeSale");
					String includePlatformCoupon=request.getParameter("includePlatformCoupon");
					String redEnvelopeType=request.getParameter("redEnvelopeType");
					CouponRainSetup couponRainSetup2=couponRainSetupService.selectByPrimaryKey(Integer.valueOf(couponRainSetupid));
					couponRainSetup2.setUpdateBy(staffId);
					couponRainSetup2.setUpdateDate(new Date());
					couponRainSetup2.setPic(pic);
					couponRainSetup2.setIncludeSale(includeSale);
					couponRainSetup2.setIncludePlatformCoupon(includePlatformCoupon);
					couponRainSetup2.setRecBeginDate(sdf.parse(recBeginDate+":00"));
					couponRainSetup2.setRecEndDate(sdf.parse(recEndDate+":59")); 
					couponRainSetup2.setIncludeProductCoupon(includeProductCoupon);
					couponRainSetup2.setIncludeIntegral(includeIntegral);
					couponRainSetup2.setLimitRecCount(Integer.valueOf(limitRecCount));
					if (redEnvelopeType.equals("P")) {
						couponRainSetup2.setRedEnvelopeType("P");//普通红包
					}else {
						couponRainSetup2.setRedEnvelopeType("S");//圣诞红包
					}
					if (!StringUtils.isEmpty(includeIntegralMin) && !StringUtils.isEmpty(includeIntegralMax)) {
						couponRainSetup2.setIncludeIntegralMin(Integer.valueOf(includeIntegralMin));
						couponRainSetup2.setIncludeIntegralMax(Integer.valueOf(includeIntegralMax));	
					}else {
						couponRainSetup2.setIncludeIntegralMin(null);
						couponRainSetup2.setIncludeIntegralMax(null); 
					}

					if (!StringUtils.isEmpty(includeProductCouponPercent) && includeProductCoupon.equals("1")) {
		            	 BigDecimal includeProductCouponPercents = new BigDecimal(includeProductCouponPercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup2.setIncludeProductCouponPercent(includeProductCouponPercents);
		 			 }else if (StringUtils.isEmpty(includeProductCouponPercent)) {
		 				 couponRainSetup2.setIncludeProductCouponPercent(new BigDecimal(0));
					}
					
		            if (!StringUtils.isEmpty(includeIntegralPercent) && includeIntegral.equals("1")) {
		            	 BigDecimal includeIntegralPercents = new BigDecimal(includeIntegralPercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup2.setIncludeIntegralPercent(includeIntegralPercents);
		 			}else if (StringUtils.isEmpty(includeIntegralPercent)) {
		 				couponRainSetup2.setIncludeIntegralPercent(new BigDecimal(0));
					}
		            
		            if (!StringUtils.isEmpty(includeSalePercent) && includeSale.equals("1")) {
		            	 BigDecimal includeSalePercents = new BigDecimal(includeSalePercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup2.setIncludeSalePercent(includeSalePercents);
		 			}else if (StringUtils.isEmpty(includeSalePercent)) {
		 				couponRainSetup2.setIncludeSalePercent(new BigDecimal(0));
					}
		            if (!StringUtils.isEmpty(includePlatformCouponPercent) && includePlatformCoupon.equals("1")) {
		            	 BigDecimal includePlatformCouponPercents = new BigDecimal(includePlatformCouponPercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
		            	 couponRainSetup2.setIncludePlatformCouponPercent(includePlatformCouponPercents);
		 			}else if (StringUtils.isEmpty(includePlatformCouponPercent)) {
		 				couponRainSetup2.setIncludePlatformCouponPercent(new BigDecimal(0));
					}
		             couponRainSetupService.updateByPrimaryKeySelective(couponRainSetup2);
		             
				}
																							
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "保存失败，请稍后重试");
				return resMap;
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return resMap;
		}
		

		//红包雨入口
		@RequestMapping("/couponRain/couponRainActivity.shtml")
		public ModelAndView couponRainActivity(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/couponRain/couponRainActivity");
			return m;
		}
		
		//红包雨入口数据
		@ResponseBody
		@RequestMapping("/couponRain/couponRainActivitydata.shtml")
		public Map<String, Object> couponRainActivitydata(HttpServletRequest request, Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			List<CouponRainCustom> couponRainCustoms=null;
			Integer totalCount = 0;	
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				CouponRainCustomExample couponRainCustomExample=new CouponRainCustomExample();
				CouponRainCustomExample.CouponRainCustomCriteria couponRainCustomCriteria=couponRainCustomExample.createCriteria();
				couponRainCustomCriteria.andDelFlagEqualTo("0");
				couponRainCustomExample.setOrderByClause("create_date desc");
				
				if (!StringUtil.isEmpty(request.getParameter("status"))) {
					couponRainCustomCriteria.andStatusEqualTo(request.getParameter("status"));
				}
				
				if (!StringUtil.isEmpty(request.getParameter("title"))) {
					couponRainCustomCriteria.andtitleLike(request.getParameter("title"));
				}
				if (!StringUtil.isEmpty(request.getParameter("starDate"))) {
					couponRainCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("starDate")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("endateDate"))) {
					couponRainCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("endateDate")+" 23:59:59"));
				}
				totalCount = couponRainService.countByCouponRainCustomExample(couponRainCustomExample);
				couponRainCustomExample.setLimitStart(page.getLimitStart());
				couponRainCustomExample.setLimitSize(page.getLimitSize());
				couponRainCustoms=couponRainService.selectByCouponRainCustomExample(couponRainCustomExample);
				
				resMap.put("Rows", couponRainCustoms);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resMap;
		}
		
		
		        //新增红包雨入口
				@RequestMapping(value = "/couponRain/addcouponRain.shtml")
				public ModelAndView addcouponRain(HttpServletRequest request) {
					String rtPage = "/couponRain/addcouponRain";
					Map<String, Object> resMap = new HashMap<String, Object>();
					
					String couponRainid=request.getParameter("id");
					CouponRainSetupExample couponRainSetupExample=new CouponRainSetupExample();
					couponRainSetupExample.createCriteria().andDelFlagEqualTo("0");
					List<CouponRainSetup> couponRainSetups=couponRainSetupService.selectByExample(couponRainSetupExample);
					resMap.put("couponRainSetups", couponRainSetups);
					
					if (!StringUtil.isEmpty(couponRainid)) {
						CouponRain couponRain=couponRainService.selectByPrimaryKey(Integer.valueOf(couponRainid));
						resMap.put("couponRain", couponRain);
						
						String startTime1 = couponRain.getStartTime().toString().substring(11, 13);
						resMap.put("startTime1", startTime1);
						
						String startTime2 = couponRain.getStartTime().toString().substring(14, 16);
						resMap.put("startTime2", startTime2);
						
						String endTime1 = couponRain.getEndTime().toString().substring(11, 13);
						resMap.put("endTime1", endTime1);
						
						String endTime2 = couponRain.getEndTime().toString().substring(14, 16);
						resMap.put("endTime2", endTime2);
						
						if (couponRain.getBombPercent()!=null) {
							String BombPercent=couponRain.getBombPercent().toString();
							BigDecimal bombPercent1 = new BigDecimal(BombPercent).multiply(new BigDecimal(100)).setScale(2);
							resMap.put("bombPercent", bombPercent1);
							
						}
					}
					
					return new ModelAndView(rtPage, resMap);
			  }
				
				
				//保存,编辑红包雨入口
				@RequestMapping(value = "/couponRain/editcouponRain.shtml")
				@ResponseBody
			 	public Map<String, Object> editcouponRain(HttpServletRequest request,CouponRain couponRain){
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					String code = null;
					String msg =null;
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						 
						 String startTime=request.getParameter("startTime");
						 String startDate=request.getParameter("startDate");
						 String startDate1=request.getParameter("startDate1");
						 
						 String endDate=request.getParameter("endDate");
						 String endDate1=request.getParameter("endDate1");
						 String bombPercent=request.getParameter("bombPercent");
						 String couponRainSetupId1=request.getParameter("couponRainSetupId");
						 
						 Date stardate=sdf.parse(startTime+" "+startDate+":"+startDate1);
						 Date enddate=sdf.parse(startTime+" "+endDate+":"+endDate1);
						 
						 CouponRainSetup couponRainSetup=couponRainSetupService.selectByPrimaryKey(Integer.valueOf(couponRainSetupId1));
						 if (stardate.after(couponRainSetup.getRecBeginDate()) && stardate.before(couponRainSetup.getRecEndDate()) && enddate.after(couponRainSetup.getRecBeginDate()) && enddate.before(couponRainSetup.getRecEndDate())) {
							 						 
							 if (couponRain.getId()==null) {//添加
								 couponRain.setCreateBy(staffId);
								 couponRain.setCreateDate(new Date());
								 BigDecimal bombPercents = new BigDecimal(bombPercent).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
								 couponRain.setBombPercent(bombPercents);
								 couponRain.setStartTime(sdf.parse(startTime+" "+startDate+":"+startDate1));
								 couponRain.setEndTime(sdf.parse(startTime+" "+endDate+":"+endDate1));
								 couponRain.setStatus("1");
								 couponRain.setMemberCount(0);
								 couponRain.setDelFlag("0");
								 couponRainService.insert(couponRain);
								 
							 }else {//更新
								 
								 String id=request.getParameter("id");
								 String startTime1=request.getParameter("startTime");
								 String startDate2=request.getParameter("startDate");
								 String startDate3=request.getParameter("startDate1");
								 
								 String endDate2=request.getParameter("endDate");
								 String endDate3=request.getParameter("endDate1");
								 
								 String bombPercent1=request.getParameter("bombPercent");
								/* String pic=request.getParameter("pic");*/
								 String gameSeconds=request.getParameter("gameSeconds");
								 String couponRainSetupId=request.getParameter("couponRainSetupId");
								 
								 CouponRain couponRain1=couponRainService.selectByPrimaryKey(Integer.valueOf(id));
								 couponRain1.setUpdateBy(staffId);
								 couponRain1.setUpdateDate(new Date());
								 /*couponRain1.setPic(pic);*/
								 couponRain1.setCouponRainSetupId(Integer.valueOf(couponRainSetupId));
								 couponRain1.setStartTime(sdf.parse(startTime1+" "+startDate2+":"+startDate3));
								 couponRain1.setEndTime(sdf.parse(startTime1+" "+endDate2+":"+endDate3));
								 couponRain1.setGameSeconds(Integer.valueOf(gameSeconds));
								 if (!StringUtils.isEmpty(bombPercent1)) {
									 BigDecimal bombPercents= new BigDecimal(bombPercent1).divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_DOWN);
									 couponRain1.setBombPercent(bombPercents);	
								 }
								 couponRainService.updateByPrimaryKeySelective(couponRain1);
								 
							 }
							 
						}else {
							resMap.put("returnCode", "9999");
							resMap.put("returnMsg", "开始日期和时间必须在红包雨有效期内;");
							return resMap;
						}
						 
						 
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return resMap;
				}
				
				
	
				//变更红包雨入口上下架
				@RequestMapping(value = "/couponRain/changeCouponRainStatus.shtml")
				@ResponseBody
				public Map<String, Object> changeCouponRainStatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					try {
						String id = request.getParameter("id");
						CouponRain  couponRain=couponRainService.selectByPrimaryKey(Integer.valueOf(id));
						if(couponRain.getStatus().equals("0")){
							couponRain.setStatus("1");
						}else{
							couponRain.setStatus("0");
						}
						couponRain.setUpdateDate(new Date());
						couponRain.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						couponRainService.updateByPrimaryKeySelective(couponRain);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}
		
				
}

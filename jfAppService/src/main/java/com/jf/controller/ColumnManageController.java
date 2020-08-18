package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.controller.base.BaseController;
import com.jf.dao.CouponCenterTimeMapper;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCenterTime;
import com.jf.entity.CouponExample;
import com.jf.service.CouponService;
import com.jf.service.MemberCouponService;
import com.jf.service.SourceNicheService;
import com.jf.service.SourceProductTypeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * app栏目管理，品牌团
 * (资源位管理)
 * @author chenwf
 *
 */
@Controller
public class ColumnManageController extends BaseController{
	@Resource
	private SourceProductTypeService sourceProductTypeService;
	@Resource
	private SourceNicheService sourceNicheService;
	@Resource
	private CouponCenterTimeMapper couponCenterTimeMapper;
	@Resource
	private CouponService couponService;
	@Resource
	private MemberCouponService memberCouponService;
	/**
	 * 
	 * 方法描述 ：获取每日好货，好店顶部栏目管理 (资源位通用接口：顶部栏目查询)
	 *
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductCategoryManage")
	@ResponseBody
	public ResponseMsg getCanCommentOrderDtl(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			
			List<Map<String, Object>> dataList = sourceProductTypeService.getProductCategoryManage(reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取每日好货，好店广告管理 (资源位通用接口：广告查询)
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductCategoryAdManage")
	@ResponseBody
	public ResponseMsg getProductCategoryAdManage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			
			Map<String, Object> map = sourceProductTypeService.getProductCategoryAdManage(reqPRM,reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取每日好货商品列表 (资源位通用接口)
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getGoodEveryDayProductList")
	@ResponseBody
	public ResponseMsg getGoodEveryDayProductList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = sourceProductTypeService.getGoodEveryDayProductList(reqPRM,reqDataJson,pageSize);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取每日好店列表
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getGoodEveryDayShopList")
	@ResponseBody
	public ResponseMsg getGoodEveryDayShopList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = sourceProductTypeService.getGoodEveryDayShopList(reqPRM,reqDataJson,pageSize);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}

	/**
	 * 方法描述 ：获取爆款推荐商品列表 type = 12
	 */
	@RequestMapping(value = "/api/n/findHotRecommendProduct")
	@ResponseBody
	public ResponseMsg findHotRecommendProduct(HttpServletRequest request) {
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");

		Object[] obj = {"currentPage","sourceProductTypeId"};
		this.requiredStr(obj, request);
		Integer pageSize = Const.RETURN_SIZE_10;
		Map<String, Object> map = sourceProductTypeService.findHotRecommendProduct(reqDataJson, pageSize);
		return ResponseMsg.success(map);
	}
	
	/**
	 * 
	 * 方法描述 ：更新点赞数量
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateSourceNicheSupportQuantity")
	@ResponseBody
	public ResponseMsg updateSourceNicheSupportQuantity(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"sourceNicheId"};
			this.requiredStr(obj,request);
			
			sourceNicheService.updateSourceNicheSupportQuantity(reqPRM,reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"您赞过啦，请勿重复操作哦~");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取品牌团top类目
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getBrandGroupCategoryTop")
	@ResponseBody
	public ResponseMsg getBrandGroupCategoryTop(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			List<Map<String, Object>> dataList = sourceProductTypeService.getBrandGroupCategoryTop("2");
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取品牌团广告+装修
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getBrandGroupCategoryAds")
	@ResponseBody
	public ResponseMsg getBrandGroupCategoryAds(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"brandTeamTypeId"};
			this.requiredStr(obj,request);
			
			Map<String, Object> map = sourceProductTypeService.getBrandGroupCategoryAds(reqPRM,reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取领券中心优惠券列表
	 * @author  yjc: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getCouponCenterDataList")
	@ResponseBody
	public ResponseMsg getCouponCenterDataList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Map<String, Object> map = sourceProductTypeService.getCouponCenterDataList(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取领券中心优惠券列表
	 * @author  yjc: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getCouponListByRecBeginDate")
	@ResponseBody
	public ResponseMsg getCouponListByRecBeginDate(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"couponCenterTimeId"};
			this.requiredStr(obj,request);
			String couponCenterTimeId = reqDataJson.getString("couponCenterTimeId");
			String memberId = "";
			if(reqDataJson.containsKey("memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			CouponCenterTime couponCenterTime = couponCenterTimeMapper.selectByPrimaryKey(Integer.parseInt(couponCenterTimeId));
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String eachTime = couponCenterTime.getStartHours()+":"+couponCenterTime.getStartMin();
			String eachTodayTime = df.format(now)+" "+eachTime+":00";
			List<String> couponTypeList = new ArrayList<String>();
			couponTypeList.add("1");
			couponTypeList.add("2");
			CouponExample e = new CouponExample();
			e.setLimitStart(0);
			e.setLimitSize(3);
			e.createCriteria().andDelFlagEqualTo("0").andRangEqualTo("1")
			.andBelongEqualTo("1").andCouponTypeIn(couponTypeList).andIsIntegralTurntableEqualTo("0")
			.andStatusEqualTo("1").andRecBeginDateEqualTo(sdf.parse(eachTodayTime))
			.andRecTypeEqualTo("1").andRecEndDateGreaterThanOrEqualTo(new Date());
			List<Coupon> couponList = couponService.selectByExample(e);
			DecimalFormat decimalFormat = new DecimalFormat();
			decimalFormat.applyPattern("#.##");
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> couponMapList = new ArrayList<Map<String, Object>>();
			for(Coupon coupon:couponList){
				Map<String,Object> couponMap = new HashMap<String,Object>();
				Map<String, Object> msgMap = memberCouponService.getMemberIsReceiveCoupon(coupon, memberId);
				String msgType = "0";
				msgType = msgMap.get("msgType").toString();
				if(!"1".equals(msgType) && !"2".equals(msgType)){
					if(coupon.getRecBeginDate().compareTo(new Date()) > 0){
						msgType = "3";
						couponMap.put("recBeginDate", coupon.getRecBeginDate().getTime());
					}else{
						msgType = "0";
					}
				}
				couponMap.put("couponType", coupon.getCouponType());
				couponMap.put("couponTypeDesc", coupon.getCouponType().equals("1")?"平台券":"品类券");
				couponMap.put("recType", coupon.getRecType());
				couponMap.put("msgType", msgType);
				couponMap.put("couponId", coupon.getId().toString());
				couponMap.put("preferentialType", coupon.getPreferentialType());
				if(coupon.getPreferentialType().equals("1")){//1.固定面额
					couponMap.put("preferentialInfo", decimalFormat.format(coupon.getMoney()));
					couponMap.put("couponInfoStr", "满"+decimalFormat.format(coupon.getMinimum())+"元可用");
				}else{//2.折扣
					couponMap.put("preferentialInfo", decimalFormat.format(coupon.getDiscount().multiply(new BigDecimal(10))));
					if("3".equals(coupon.getConditionType())){//3.满几件可用
						if(coupon.getMinicount()!=null){//正常数据都是Minicount不会空
							couponMap.put("couponInfoStr", "满"+decimalFormat.format(coupon.getMinicount())+"件可用");
						}else if(coupon.getMinimum()!=null){
							couponMap.put("couponInfoStr", "满"+decimalFormat.format(coupon.getMinimum())+"件可用");
						}
					}else if("2".equals(coupon.getConditionType())){//2.满多少可用
						if(coupon.getMinimum()!=null){//正常数据都是Minimum不会空
							couponMap.put("couponInfoStr", "满"+decimalFormat.format(coupon.getMinimum())+"元可用");
						}else if(coupon.getMinicount()!=null){
							couponMap.put("couponInfoStr", "满"+decimalFormat.format(coupon.getMinicount())+"元可用");
						}
					}else{//1.无条件
						couponMap.put("couponInfoStr", "");
					}
				}
				couponMapList.add(couponMap);
			}
			map.put("couponMapList", couponMapList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取商品券列表
	 * @author  yjc: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductCouponList")
	@ResponseBody
	public ResponseMsg getProductCouponList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = sourceProductTypeService.getProductCouponList(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取特卖优惠券列表
	 * @author  yjc: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityAreaCouponList")
	@ResponseBody
	public ResponseMsg getActivityAreaCouponList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = sourceProductTypeService.getActivityAreaCouponList(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
}

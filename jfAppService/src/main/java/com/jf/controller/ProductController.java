package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.dao.ProductExtendMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.DeliveryOvertimeCnf;
import com.jf.entity.DeliveryOvertimeCnfExample;
import com.jf.entity.FreightTemplate;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.Product;
import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;
import com.jf.entity.ProductExtend;
import com.jf.entity.ProductExtendExample;
import com.jf.entity.SysParamCfg;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import com.jf.service.CouponService;
import com.jf.service.DeliveryOvertimeCnfService;
import com.jf.service.FreightTemplateService;
import com.jf.service.IntegralDtlService;
import com.jf.service.MchtBlPicService;
import com.jf.service.MchtInfoService;
import com.jf.service.MemberAccountService;
import com.jf.service.MemberCouponService;
import com.jf.service.MemberFootprintService;
import com.jf.service.ProductDescPicService;
import com.jf.service.ProductItemService;
import com.jf.service.ProductPicService;
import com.jf.service.ProductPropValueService;
import com.jf.service.ProductService;
import com.jf.service.ProductVideoService;
import com.jf.service.SingleProductActivityCnfService;
import com.jf.service.SingleProductActivityService;
import com.jf.service.SobotCustomerServiceService;
import com.jf.service.WeixinService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月27日 下午4:58:17 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ProductController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private ProductPicService productPicService;
	
	@Resource
	private ProductDescPicService productDescPicService;
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private MemberAccountService memberAccountService;
	
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private ProductPropValueService productPropValueService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private SingleProductActivityCnfService singleProductActivityCnfService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource 
	private SobotCustomerServiceService sobotCustomerServiceService;
	
	@Resource
	private FreightTemplateService freightTemplateService;
	
	@Resource
	private DeliveryOvertimeCnfService deliveryOvertimeCnfService;
	@Resource
	private MchtBlPicService mchtBlPicService;
	@Resource
	private ProductVideoService productVideoService;
	@Resource
	private WeixinService weiXinService;
	@Resource
	private MemberFootprintService memberFootprintService;
	@Resource
	private ProductExtendMapper productExtendMapper;
	
	/**
	 * 
	 * 方法描述 ：获取优惠券By活动会场id
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月28日 下午6:13:32 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getCouponByActivityAreaId")
	@ResponseBody
	public ResponseMsg getCouponByActivityAreaId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			String system = "";
			if(reqPRM.containsKey("system")){
				system = reqPRM.getString("system");
			}
			if(system.equalsIgnoreCase(Const.ANDROID)){
				if(StringUtil.isBlank(reqDataJson.getString("activityAreaId")) || "0".equals(reqDataJson.getString("activityAreaId"))){
					return new ResponseMsg();
				}
			}
			
			String memberId = reqDataJson.getString("memberId");
			String activityAreaId = reqDataJson.getString("activityAreaId");
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			//依次表示会场id,删除标记,启用状态,优惠券类型,领取方式
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.valueOf(activityAreaId));
			List<Integer> couponIds = new ArrayList<Integer>();
			if(activityArea != null){
				String preferentialType = activityArea.getPreferentialType();
				if(!StringUtil.isBlank(preferentialType) && preferentialType.equals("1")){
					String preferentialIdGroup = activityArea.getPreferentialIdGroup();
					if(!StringUtil.isBlank(preferentialIdGroup)){
						String[] preferentialIdGroups = preferentialIdGroup.split(",");
						for (String str : preferentialIdGroups) {
							couponIds.add(Integer.valueOf(str));
						}
					}
				}
				if(CollectionUtils.isNotEmpty(couponIds)){
					CouponExample couponExample = new CouponExample();
					couponExample.createCriteria().andActivityAreaIdEqualTo(Integer.valueOf(activityAreaId))
					.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIds).andRecTypeNotEqualTo("3");
					couponExample.setOrderByClause("money desc");
					couponExample.setLimitStart(0);
					couponExample.setLimitSize(3);
					List<Coupon> couponList = couponService.selectByExample(couponExample);
					if(couponList != null && couponList.size() >0){
						for (Coupon coupon : couponList) {
							//优惠券发行数量
							Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
							//优惠券已领数量
							Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
							//优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
							String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
							//优惠券 限领数量
							Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
							if(grantQuantity > 0 && grantQuantity > recQuantity){
								Map<String,Object> couponMap = new HashMap<String,Object>();
								couponMap.put("couponId", coupon.getId());
								couponMap.put("couponName", coupon.getName());
								couponMap.put("money", coupon.getMoney());
								couponMap.put("minimum", coupon.getMinimum());
								couponMap.put("expiryBeginDate", coupon.getExpiryBeginDate());
								couponMap.put("expiryEndDate", coupon.getExpiryEndDate());
								couponMap.put("recType", coupon.getRecType());
								couponMap.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
								couponMap.put("recLimitType", coupon.getRecLimitType());
								couponMap.put("recEach", coupon.getRecEach());
								
								//查询该优惠券是否背会员领取
								MemberCouponExample memberCouponExample = new MemberCouponExample();
								memberCouponExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
								.andCouponIdEqualTo(coupon.getId()).andDelFlagEqualTo("0");
								memberCouponExample.setOrderByClause("rec_date desc");
								List<MemberCoupon> memberCoupons = memberCouponService.selectByExample(memberCouponExample);
								Integer memberCouponCount = 0;
								boolean isReceive = true;
								if(CollectionUtils.isNotEmpty(memberCoupons)){
									memberCouponCount = memberCoupons.size();
									String recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMMdd");
									String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
									if(recLimitType.equals("1")){
										if(recDate.equals(currentDate)){
											isReceive = false;
										}else{
											isReceive = true;
										}
									}else if(recLimitType.equals("2")){
										if(memberCoupons.size() >= recEach){
											isReceive = false;
										}
									}else{
										isReceive = true;
									}
								}
								couponMap.put("memberCouponCount", memberCouponCount);
								couponMap.put("isReceive", isReceive);
								returnData.add(couponMap);
							}
						}
					}
				}
			}
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	
	
	/**
	 * 
	 * 方法描述 ：获取商品详情下拉详情图
	 * @author  chenwf: 
	 * @date 创建时间：2017年10月13日 下午3:07:44 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductDetail")
	@ResponseBody
	public ResponseMsg getProductDetail(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String system = reqPRM.getString("system");
			Integer version = reqPRM.getInt("version");
			Object[] obj = {"id"};
			this.requiredStr(obj,request);
			
			//商品id
			Integer id = reqDataJson.getInt("id");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", id);

			//公告图，由于ios前端图片高度写死，所以做ios兼容，将公告图放详情里面
			Date date = new Date();
			Product product=productService.selectByPrimaryKey(id);
			Integer freightTemplateId=product.getFreightTemplateId();
			String bulletinBoardPic = "";
			if(system.equals(Const.IOS)&& version <= 51){
					//优先显示商家运费公告，没有就发货超时设置公告，没有就显示技术上传公告
					if(freightTemplateId != null){
						FreightTemplate freightTemplate = freightTemplateService.findModelById(freightTemplateId);
						if(freightTemplate != null){
							bulletinBoardPic = StringUtil.getPic(freightTemplate.getProductNoticePic(), "");
						}
					}
					if(StringUtil.isBlank(bulletinBoardPic)){
						DeliveryOvertimeCnfExample overtimeCnfExample = new DeliveryOvertimeCnfExample();
						overtimeCnfExample.createCriteria().andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThanOrEqualTo(date).andDelFlagEqualTo("0");
						List<DeliveryOvertimeCnf> overtimeCnfs = deliveryOvertimeCnfService.selectByExample(overtimeCnfExample);
						if(CollectionUtils.isNotEmpty(overtimeCnfs)){
							bulletinBoardPic = StringUtil.getPic(overtimeCnfs.get(0).getProductNoticePic(), "");
						}
						
					}
					if(StringUtil.isBlank(bulletinBoardPic)){
						List<SysParamCfg> bulletinBoardPics = DataDicUtil.getSysParamCfg("APP_PRODUCT_BULEETIN_BOARD_PIC");
						if(CollectionUtils.isNotEmpty(bulletinBoardPics)){
							bulletinBoardPic = StringUtil.getPic(bulletinBoardPics.get(0).getParamValue(), "");
						}
					}
			}
			
			
			//商家营业执照
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdEqualTo(product.getMchtId()).andDelFlagEqualTo("0").andLicenseIsMustEqualTo("1").andBusinessLicensePicIsNotNull().andLicenseStatusEqualTo("2");
			String blPic = "";
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
			if(CollectionUtils.isNotEmpty(mchtInfos)){
				blPic = StringUtil.getPic( mchtInfos.get(0).getBusinessLicensePic(), "");
			}
			List<String> productDetailPics = new ArrayList<>();
			//封装详情图
			ProductDescPicExample productDescPicExample = new ProductDescPicExample();
			productDescPicExample.createCriteria().andProductIdEqualTo(id).andDelFlagEqualTo("0");
			productDescPicExample.setOrderByClause("seq_no");
			List<ProductDescPic> productDescPicList = productDescPicService.selectByExample(productDescPicExample);
			StringBuffer sbf = new StringBuffer();
			sbf.append("<HTML>");
			sbf.append("<BODY>");
			sbf.append("<style>");
			sbf.append("img {display: block;width: 100%;border: none;}");
			sbf.append("</style>");
			sbf.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			sbf.append("<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'>");
			
			if(!StringUtil.isEmpty(bulletinBoardPic)){
				sbf.append("<img src="+bulletinBoardPic+">");
				productDetailPics.add(bulletinBoardPic);
			}
			
			if(productDescPicList != null && productDescPicList.size() > 0){
				for (ProductDescPic productDescPic : productDescPicList) {
					sbf.append("<img src="+StringUtil.getPic(productDescPic.getPic(), "70Q")+">");
					productDetailPics.add(StringUtil.getPic(productDescPic.getPic(), "70Q"));
				}
			}
			
			//食品标签(图片)food_label_pic
			ProductExtendExample pee = new ProductExtendExample();
			pee.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(id);
			List<ProductExtend> productExtendList = productExtendMapper.selectByExample(pee);
			if(productExtendList!=null && productExtendList.size()>0){
				String foodLabelPic = productExtendList.get(0).getFoodLabelPic();
				if(!StringUtil.isEmpty(foodLabelPic)){
					foodLabelPic = StringUtil.getPic(foodLabelPic,"");
					sbf.append("<img src="+foodLabelPic+">");
					productDetailPics.add(foodLabelPic);
				}
			}
			
			if(!StringUtil.isBlank(blPic)){
				sbf.append("<img src="+blPic+">");
				productDetailPics.add(blPic);
			}
			SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_PRICE_DESC_PIC","");
			if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
				sbf.append("<img src="+StringUtil.getPic(paramCfg.getParamValue(), "")+">");
				productDetailPics.add(StringUtil.getPic(paramCfg.getParamValue(), ""));
			}
			sbf.append("</BODY>");
			sbf.append("</HTML>");
			if((system.equals(Const.ANDROID)&& version <= 50) || (system.equals(Const.IOS)&& version <= 53)){
				return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,sbf.toString());
			}else{

				Map<String, Object> map = new HashMap<>();
				map.put("productDetailPics", productDetailPics);
				map.put("productVideos", productVideoService.getProductVideos(id, "2"));
				return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
			}
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	
	
//	/**
//	 * 
//	 * 方法描述 ：获取有效sku（弃用）
//	 * @author  chenwf: 
//	 * @date 创建时间：2017年7月5日 下午5:55:22 
//	 * @version
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/api/y/getEffectiveSku")
//	@ResponseBody
//	public ResponseMsg getEffectiveSku(HttpServletRequest request) {
//		try {
//			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
//			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
//
//			Object[] obj = { "memberId","productItemId","productId","quantity"};
//			this.requiredStr(obj, request);
//
//			Integer memberId = reqDataJson.getInt("memberId");
//			Integer productId = reqDataJson.getInt("productId");
//			Integer quantity = reqDataJson.getInt("quantity");
//			Integer productItemId = reqDataJson.getInt("productItemId");
//			boolean b = true;
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("memberId", memberId);
//			params.put("productId", productId);
//			ProductCustom productCustom = productService.getUserBuyCount(params);
//			Integer limitBuy = 0;
//			Integer productBuyNum = 0;
//			if(productCustom != null){
//				limitBuy = productCustom.getLimitBuy() == null ? 0 : productCustom.getLimitBuy();
//				productBuyNum = productCustom.getProductBuyNum() == null ? 0 : productCustom.getProductBuyNum();
//			}
//			//limitBuy = 0 不做限购判断
//			if(productBuyNum+quantity > limitBuy && limitBuy != 0){
//				b = false;
//			}else{
//				ProductItem productItem = productItemService.selectByPrimaryKey(productItemId);
//				Integer stock = productItem.getStock();//库存
//				Integer lockedAmount = productItem.getLockedAmount();//冻结
//				stock = stock - lockedAmount;
//				if(quantity > stock){
//					b = false;
//				}
//			}
//			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,b);
//		} catch (ArgException arge) {
//			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
//		}
//
//	}
	
	/**
	 * 
	 * 方法描述 ：生成小程序二维码图片地址
	 * @author  yjc: 
	 * @date 创建时间：2019年9月23日13:50:54
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductXcxQrcode")
	@ResponseBody
	public ResponseMsg getProductXcxQrcode(HttpServletRequest request){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = { "id","memberId"};
			this.requiredStr(obj, request);
			String id = reqDataJson.getString("id");
			String memberId = reqDataJson.getString("memberId");
			String page = "page/product/details/index";
			String scene = "id="+id+"&memberId="+memberId;
			String pic = weiXinService.createAppletUnlimitQrcode(page, scene);
			map.put("pic", StringUtil.getPic(pic, ""));
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
	 * 方法描述 ：1698 【新增】【APP、小程序】购物车、消息页新增为你推荐模块
	 * @author  yjc: 
	 * @date 创建时间：2019年11月6日17:54:47
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getRecommendProductList")
	@ResponseBody
	public ResponseMsg getRecommendProductList(HttpServletRequest request){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage"};
			this.requiredStr(obj, request);
			int memberId = 0;
			String productIds = "";
			if(reqDataJson.has("memberId")){
				memberId = reqDataJson.optInt("memberId");
			}
			if(reqDataJson.has("productIds")){
				productIds = reqDataJson.getString("productIds");
			}
			Integer pageSize = Const.RETURN_SIZE_20;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			int currentPage = reqDataJson.getInt("currentPage");
			paramMap.put("currentPage", 0);//这边固定是limit 0,20，因为前端会被已加载的id传过来，然后这些id被排除掉，本身已经确保是不会重复了，所以固定limit 0,20可以取出不重复的最新的前20条
			paramMap.put("pageSize", pageSize);
			List<Integer> productIdList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(productIds)){
				String[] productIdsArray = productIds.split(",");
				for(String productId:productIdsArray){
					productIdList.add(Integer.parseInt(productId));
				}
			}
			if (CollectionUtil.isNotEmpty(productIdList)) {
				paramMap.put("productIdList", productIdList);
			}
			if (memberId == 0) {
				map = productService.getNotLoginRecommendProductListMap(paramMap);
			} else {
				paramMap.put("memberId", memberId);
				if (!memberFootprintService.hasMemberFootprint(memberId)) {
					map = productService.getNotLoginRecommendProductListMap(paramMap);
				} else {
					List<Integer> productType3IdList = memberFootprintService.findMemberRecentlyScanProductType3(memberId);
					map = productService.getLoginRecommendProductListMap(paramMap, productType3IdList);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, pageSize, map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}

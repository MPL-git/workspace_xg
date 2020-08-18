package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.BgModuleCategory;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import com.jf.entity.SysParamCfg;
import com.jf.service.BgModuleCategoryService;
import com.jf.service.MchtInfoService;
import com.jf.service.MemberFootprintService;
import com.jf.service.ProductDescPicService;
import com.jf.service.ProductPicService;
import com.jf.service.ProductService;
import com.jf.service.ProductVideoService;
import com.jf.service.WeixinService;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
	
	@Resource
	private ProductService productService;

	@Resource
	private ProductPicService productPicService;
	
	@Resource
	private ProductDescPicService productDescPicService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private BgModuleCategoryService bgModuleCategoryService;

	@Resource
	private ProductVideoService productVideoService;
	@Resource
	private WeixinService weiXinService;
	@Resource
	private MemberFootprintService memberFootprintService;
	/**
	 * H5(分享页面调用)
	 * 方法描述 ：获取商品信息by
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月16日 上午9:24:30 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductInfoListByShare")
	@ResponseBody
	public ResponseMsg getProductInfoListByShare(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"id"};
			this.requiredStr(obj,request);
			String activityAreaId = reqDataJson.getString("activityAreaId");
			//商品编号
			String code = reqDataJson.getString("id");
			String memberId = getMemberIdStr(request);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("code", code);
			
			Map<String,Object> returnDataMap = new HashMap<String,Object>();
			List<ProductCustom> productCustomList = productService.getProductInfoListByShare(params);
			if(productCustomList !=null && productCustomList.size() > 0){
				//封装图片集合
				List<Map<String,Object>> picMapList = new ArrayList<Map<String,Object>>();
				//封装规格详情集合
				List<Map<String,Object>> standardMapList = new ArrayList<Map<String,Object>>();
				//封装优惠券Map
				Map<String,Object> couponMap = new HashMap<String,Object>();
				String artNo = productCustomList.get(0).getArtNo();
				String suitSex = productCustomList.get(0).getSuitSex();
				String suitGroup = productCustomList.get(0).getSuitGroup();
				String season = productCustomList.get(0).getSeason();
				String brandName = productCustomList.get(0).getBrandName();
				String propValId = "";
				Integer stock = 0;
				Integer lockedAmount = 0;
				for (ProductCustom productCustom : productCustomList) {
					propValId += productCustom.getPropValId()+",";
					stock = stock+productCustom.getStock();
					lockedAmount = lockedAmount+productCustom.getLockedAmount();
				}
				Integer productId = productCustomList.get(0).getId();
				Map<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("id", productId);
				dataMap.put("name", productCustomList.get(0).getName());
				dataMap.put("productTypeId", productCustomList.get(0).getProductTypeId());
				dataMap.put("mchtId", productCustomList.get(0).getMchtId());
				dataMap.put("limitBuy", productCustomList.get(0).getLimitBuy());
				
				if(stock-lockedAmount > 0 && productCustomList.get(0).getStatus().equals("1")){
					dataMap.put("stock", stock);
				}else{
					dataMap.put("stock", 0);
				}
				dataMap.put("lockedAmount", productCustomList.get(0).getLockedAmount());
				double tagPrice = productCustomList.get(0).getTagPrice().doubleValue();
				double salePrice = productCustomList.get(0).getSalePrice().doubleValue();
				double discount = new BigDecimal((float)salePrice/tagPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("salePrice", salePrice);
				dataMap.put("discount", discount);
				dataMap.put("skuPic", StringUtil.getPic(productCustomList.get(0).getPic(), ""));
				dataMap.put("skuPic_S", StringUtil.getPic(productCustomList.get(0).getPic(), "S"));
				
				//获取商品所有的规格id
				//是否单规格 0 否 1 是
				String isSingleProp = productCustomList.get(0).getIsSingleProp() == null ? "" : productCustomList.get(0).getIsSingleProp();
				if(!isSingleProp.equals("1")){
					
					if(!StringUtil.isBlank(propValId)){
						List<String> propValIdList = new ArrayList<>();
						String propVal = propValId.substring(0, propValId.length()-1);
						String[] propValStrs = propVal.split(",");
						//商品所有规格的id存入集合中
						for (String str : propValStrs) {
							propValIdList.add(str);
						}
						//去重
						HashSet<String> h = new HashSet<String>(propValIdList);
						propValIdList.clear();
						propValIdList.addAll(h);
						params.put("propValIdList", propValIdList);
						params.put("id", productId);
						//查询出过滤所有有库存的规格
						Map<String,Object> hasStockMap = new HashMap<String,Object>();
						String hasStockPropValueId = "";
						List<ProductItem> hasStockList = productService.getHasStockList(params);
						if(CollectionUtils.isNotEmpty(hasStockList)){
							for (ProductItem item : hasStockList) {
								hasStockPropValueId += item.getPropValId()+",";
							}
							hasStockPropValueId = hasStockPropValueId.substring(0, hasStockPropValueId.length()-1);
							for (String propValueId : hasStockPropValueId.split(",")) {
								hasStockMap.put(propValueId, propValueId);
							}
						}
						params.put("sorfType", "1");
						List<ProductCustom> standardList = productService.getStandardById(params);
						//把相同的规格类型存list
						List<Object> propNameList = new ArrayList<Object>();
						for (ProductCustom props : standardList) {
							if(!propNameList.contains(props.getPropName())){
								Map<String,Object> propMap = new HashMap<String,Object>();
								propNameList.add(props.getPropName());
								propMap.put("propName", props.getPropName());
								propMap.put("propId", props.getPropId());
								params.put("propId", props.getPropId());
								params.put("sorfType", "2");
								List<ProductCustom> propValueList = productService.getStandardById(params);
								List<Map<String,Object>> propValueMapList = new ArrayList<Map<String,Object>>();
								for (ProductCustom propValues : propValueList) {
									Map<String,Object> propValueMap = new HashMap<String,Object>();
									propValueMap.put("propValue", propValues.getPropValue());
									propValueMap.put("propValId", propValues.getPropValueId());
									if(!hasStockMap.isEmpty()){
										if(hasStockMap.containsKey(propValues.getPropValueId().toString())){
											propValueMap.put("status", true);
											propValueMapList.add(propValueMap);
										}
									}
									
								}
								if(CollectionUtils.isNotEmpty(propValueMapList)){
									propMap.put("propValueMapList", propValueMapList);
									standardMapList.add(propMap);
								}
							}
						}
						
					}
				}
				//封装主图
				ProductPicExample productPicExample = new ProductPicExample();
				productPicExample.createCriteria().andProductIdEqualTo(productId).andDelFlagEqualTo("0");
				productPicExample.setOrderByClause("seq_no");
				List<ProductPic> productPicList = productPicService.selectByExample(productPicExample);
				if(productPicList != null && productPicList.size() > 0){
					for (ProductPic productPic : productPicList) {
						Map<String,Object> picMap = new HashMap<String,Object>();
						picMap.put("pic", StringUtil.getPic(productPic.getPic(), "70Q"));
						picMapList.add(picMap);
					}
				}
				
				//封装服务说明
				Map<String, Object> productServiceMap = productService.getProductService(null,productId,null);
				@SuppressWarnings("unchecked")
				List<String> serviceList = (List<String>) productServiceMap.get("serviceList");
//				//封装产品参数
//				StringBuffer productParams = new StringBuffer();
//				productParams.append("<HTML>");
//				productParams.append("<BODY>");
//				productParams.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
//				productParams.append("<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'>");
//				if(!StringUtil.isBlank(brandName)){
//					productParams.append("品牌:");
//					productParams.append(brandName);
//					productParams.append("<br>");
//				}
//				if(!StringUtil.isBlank(artNo)){
//					productParams.append("商品货号:");
//					productParams.append(artNo);
//					productParams.append("<br>");
//				}
//				if(!StringUtil.isBlank(suitSex)){
//					SysStatus sysStatus = DataDicUtil.getStatus("BU_PRODUCT", "SUIT_SEX", suitSex);
//					if(sysStatus != null){
//						productParams.append(sysStatus.getRemark()+":");
//						productParams.append(sysStatus.getStatusDesc());
//						productParams.append("<br>");
//					}
//				}
//				if(!StringUtil.isBlank(suitGroup)){
//					SysStatus sysStatus = DataDicUtil.getStatus("BU_PRODUCT", "SUIT_GROUP", suitGroup);
//					if(sysStatus != null){
//						productParams.append(sysStatus.getRemark()+":");
//						productParams.append(sysStatus.getStatusDesc());
//						productParams.append("<br>");
//					}
//				}
//				if(!StringUtil.isBlank(season)){
//					SysStatus sysStatus = DataDicUtil.getStatus("BU_PRODUCT", "SEASON", season);
//					if(sysStatus != null){
//						productParams.append(sysStatus.getRemark()+":");
//						productParams.append(sysStatus.getStatusDesc());
//						productParams.append("<br>");
//					}
//				}
//				productParams.append("</BODY>");
//				productParams.append("</HTML>");
				//封装详情图
				ProductDescPicExample productDescPicExample = new ProductDescPicExample();
				productDescPicExample.createCriteria().andProductIdEqualTo(productId).andDelFlagEqualTo("0");
				productDescPicExample.setOrderByClause("seq_no");
				List<ProductDescPic> productDescPicList = productDescPicService.selectByExample(productDescPicExample);
				String productDescStr = productCustomList.get(0).getProductDesc();
				StringBuffer sbf = new StringBuffer();
				sbf.append("<HTML>");
				sbf.append("<BODY>");
				sbf.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
				sbf.append("<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'>");
				if(!StringUtil.isBlank(productDescStr)){
					String[] productDescs = productDescStr.split("&&&");
					for (String productDesc : productDescs) {
						sbf.append(productDesc);
						sbf.append("<br>");
					}
				}

				List<Map<String, Object>> productDescVideos = productVideoService.getProductVideos(productId, "2");
				for (Map<String, Object> video : productDescVideos) {
					sbf.append("<video src='"+StringUtil.getVideo((String)video.get("videoUrl"))+"' controls=\"controls\" style=\"width:100%;\"></video>");
				}
				if(productDescPicList != null && productDescPicList.size() > 0){
					for (ProductDescPic productDescPic : productDescPicList) {
						sbf.append("<img src="+StringUtil.getPic(productDescPic.getPic(), "70Q")+">");
					}
				}
				sbf.append("</BODY>");
				sbf.append("</HTML>");
				//封装优惠券
				//============================start============================
				long activityBeginTime = 0;
				long activityEndTime = 0;
				Integer activityId = null;
//				Map<String,Object> activityParams = new HashMap<String,Object>();
//				activityParams.put("productId", productId);
//				if(!StringUtil.isBlank(activityAreaId)){
//					//依次表示会场id,删除标记,启用状态,优惠券类型,领取方式
//					activityParams.put("activityAreaId", activityAreaId);
//					ActivityCustom activityCustom = activityService.selectActivityAreaInfo(activityParams);
//					activityBeginTime = activityCustom.getActivityBeginTime().getTime()/1000;
//					activityEndTime = activityCustom.getActivityEndTime().getTime()/1000;
//					activityId = activityCustom.getId();
//					List<Integer> couponIds = new ArrayList<Integer>();
//					String preferentialType = activityCustom.getPreferentialType();
//					if(!StringUtil.isBlank(preferentialType) && preferentialType.equals("1")){
//						String preferentialIdGroup = activityCustom.getPreferentialIdGroup();
//						if(!StringUtil.isBlank(preferentialIdGroup)){
//							String[] preferentialIdGroups = preferentialIdGroup.split(",");
//							for (String str : preferentialIdGroups) {
//								couponIds.add(Integer.valueOf(str));
//							}
//						}
//					}
//					Date date = new Date();
//					if(CollectionUtils.isNotEmpty(couponIds)){
//						CouponExample couponExample = new CouponExample();
//						couponExample.createCriteria().andActivityAreaIdEqualTo(Integer.valueOf(activityAreaId))
//								.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIds)
//								.andRecBeginDateLessThanOrEqualTo(date).andRecEndDateGreaterThanOrEqualTo(date);
//						couponExample.setOrderByClause("money desc");
//						List<Coupon> couponList = couponService.selectByExample(couponExample);
//						if(couponList != null && couponList.size() > 0){
//							couponMap = new HashMap<String,Object>();
//							Coupon coupon = couponList.get(0);
//							couponMap.put("couponId", coupon.getId());
//							couponMap.put("couponName", coupon.getName());
//							couponMap.put("recType", coupon.getRecType());
//							couponMap.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
//						}
//					}
//				}else{
//					//依次表示会场id,删除标记,启用状态,优惠券类型,领取方式
//					//根据商品id寻找会场
//					ActivityCustom activityCustom = activityService.selectActivityAreaInfo(activityParams);
//					if(activityCustom != null){
//						activityBeginTime = activityCustom.getActivityBeginTime().getTime()/1000;
//						activityEndTime = activityCustom.getActivityEndTime().getTime()/1000;
//						activityAreaId = activityCustom.getActivityAreaId().toString();
//						activityId = activityCustom.getId();
//						List<Integer> couponIds = new ArrayList<Integer>();
//						String preferentialType = activityCustom.getPreferentialType();
//						if(!StringUtil.isBlank(preferentialType) && preferentialType.equals("1")){
//							String preferentialIdGroup = activityCustom.getPreferentialIdGroup();
//							if(!StringUtil.isBlank(preferentialIdGroup)){
//								String[] preferentialIdGroups = preferentialIdGroup.split(",");
//								for (String str : preferentialIdGroups) {
//									couponIds.add(Integer.valueOf(str));
//								}
//							}
//						}
//						if(CollectionUtils.isNotEmpty(couponIds)){
//							CouponExample couponExample = new CouponExample();
//							couponExample.createCriteria().andActivityAreaIdEqualTo(Integer.valueOf(activityAreaId))
//							.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIds);
//							couponExample.setOrderByClause("money desc");
//							List<Coupon> couponList = couponService.selectByExample(couponExample);
//							if(couponList != null && couponList.size() > 0){
//								couponMap = new HashMap<String,Object>();
//								Coupon coupon = couponList.get(0);
//								couponMap.put("couponId", coupon.getId());
//								couponMap.put("couponName", coupon.getName());
//							}
//						}
//						
//					}
//				}
				//============================end============================
				//开售提醒是否点亮
				if(StringUtil.isBlank(memberId)){
					//未登入都置灰
					returnDataMap.put("isExist", false);
				}else{
					//判断是否添加开售提醒 true 存在  false 不存在
					boolean isExist = DataDicUtil.getRemindExists(productId,Integer.valueOf(memberId),"2");
					returnDataMap.put("isExist", isExist);
				}
				if(activityAreaId.equals("")){
					returnDataMap.put("activityBeginTime", "");
					returnDataMap.put("activityEndTime", "");
				}else{
					returnDataMap.put("activityBeginTime", activityBeginTime);
					returnDataMap.put("activityEndTime", activityEndTime);
				}
				returnDataMap.put("dataMap", dataMap);
				returnDataMap.put("standardMap", standardMapList);
				returnDataMap.put("picMapList", picMapList);
				returnDataMap.put("htmlDesc", sbf.toString());
				returnDataMap.put("shareUrl", Config.getProperty("mUrl")+"/share_buy.html?id="+code);
				returnDataMap.put("serviceList", serviceList);
				returnDataMap.put("couponMap", couponMap);
				returnDataMap.put("activityAreaId", activityAreaId);
				returnDataMap.put("activityId", activityId);
				returnDataMap.put("shareTitle", "唤醒潮流  开心购物");
				returnDataMap.put("currentTime", new Date().getTime());
				returnDataMap.put("productVideos", productVideoService.getProductVideos(productId, "1"));
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnDataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	
	/**
	 * 
	 * 方法描述 ：获取用户选择商品SKU规格
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月20日 下午1:35:55 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductItemsById")
	@ResponseBody
	public ResponseMsg getProductItemsById(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"id"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			if(memberId != null){
				reqDataJson.put("memberId", memberId);
			}
			Map<String,Object> map = productService.getProductItemsById(reqPRM,reqDataJson);
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

			Object[] obj = {"id"};
			this.requiredStr(obj,request);

			//商品id
			Integer id = reqDataJson.getInt("id");
			Product product=productService.selectByPrimaryKey(id);
			//商家营业执照
			String blPic = "";
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdEqualTo(product.getMchtId()).andDelFlagEqualTo("0").andLicenseIsMustEqualTo("1").andBusinessLicensePicIsNotNull().andLicenseStatusEqualTo("2");
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
			if(CollectionUtils.isNotEmpty(mchtInfos)){
				blPic = StringUtil.getPic( mchtInfos.get(0).getBusinessLicensePic(), "");
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", id);
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
			if(productDescPicList != null && productDescPicList.size() > 0){
				for (ProductDescPic productDescPic : productDescPicList) {
					sbf.append("<img src="+StringUtil.getPic(productDescPic.getPic(), "70Q")+">");
				}
			}
			if(!StringUtil.isBlank(blPic)){
				sbf.append("<img src="+blPic+">");
			}
			SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_PRICE_DESC_PIC","");
			if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
				sbf.append("<img src="+StringUtil.getPic(paramCfg.getParamValue(), "")+">");
			}
			sbf.append("</BODY>");
			sbf.append("</HTML>");
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,sbf.toString());
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
	@RequestMapping(value = "/api/n/getProductDetailTemp")
	@ResponseBody
	public ResponseMsg getProductDetailTemp(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"id"};
			this.requiredStr(obj,request);
			
			//商品id
			Integer id = reqDataJson.getInt("id");
			Product product=productService.selectByPrimaryKey(id);

			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", id);
			//封装详情图
			ProductDescPicExample productDescPicExample = new ProductDescPicExample();
			productDescPicExample.createCriteria().andProductIdEqualTo(id).andDelFlagEqualTo("0");
			productDescPicExample.setOrderByClause("seq_no");
			List<ProductDescPic> productDescPicList = productDescPicService.selectByExample(productDescPicExample);

			List<String> productDetailPics = new ArrayList<>();
			for (ProductDescPic productDescPic : productDescPicList) {
				productDetailPics.add(StringUtil.getPic(productDescPic.getPic(), "70Q"));
			}
			//商家营业执照
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdEqualTo(product.getMchtId()).andDelFlagEqualTo("0").andLicenseIsMustEqualTo("1").andBusinessLicensePicIsNotNull().andLicenseStatusEqualTo("2");
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
			if(CollectionUtils.isNotEmpty(mchtInfos)){
				productDetailPics.add(StringUtil.getPic( mchtInfos.get(0).getBusinessLicensePic(), ""));
			}
			SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_PRICE_DESC_PIC","");
			if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
				productDetailPics.add(StringUtil.getPic(paramCfg.getParamValue(), ""));
			}

			Map<String, Object> data = new HashMap<>();

			data.put("productDetailPics", productDetailPics);
			data.put("productVideos", productVideoService.getProductVideos(id, "2"));
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,data);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/n/getBgProductInfo")
	@ResponseBody
	public ResponseMsg getBgProductInfo(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "bgModuleId"};
			this.requiredStr(obj, request);

			Integer bgModuleId = reqDataJson.getInt("bgModuleId");
			BgModuleCategory bg = bgModuleCategoryService.selectByPrimaryKey(bgModuleId);
			List<Map<String,Object>> productInfoList = bgModuleCategoryService.getBgProductInfo(bg.getProductIds());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("productInfoList", productInfoList);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
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
			}else{
				paramMap.put("memberId", memberId);
				if (!memberFootprintService.hasMemberFootprint(memberId)) {
					map = productService.getNotLoginRecommendProductListMap(paramMap);
				} else {
					List<Integer> productType3IdList = memberFootprintService.findMemberRecentlyScanProductType3(memberId);
					map = productService.getLoginRecommendProductListMap(paramMap, productType3IdList);
				}
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

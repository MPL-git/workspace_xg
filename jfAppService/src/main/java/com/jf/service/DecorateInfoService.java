package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.DecorateInfoMapper;
import com.jf.entity.ActivityCustom;
import com.jf.entity.Coupon;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;
import com.jf.entity.ModuleNavigation;
import com.jf.entity.ModuleNavigationExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.SysParamCfg;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 上午11:07:42 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class DecorateInfoService extends BaseService<DecorateInfo, DecorateInfoExample> {
	
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	@Resource
	private DecorateAreaService decorateAreaService;
	@Resource
	private DecorateModuleService decorateModuleService;
	@Resource
	private ModuleMapService moduleMapService;
	@Resource
	private ModuleItemService moduleItemService;
	@Resource
	private MallCategoryMapService mallCategoryMapService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private CouponService couponService;
	@Resource
	private CommonService commonService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private ModuleNavigationService moduleNavigationService;
	@Resource
	private ProductService productService;
	@Resource
	private ActivityService activityService;

	@Autowired
	public void setDecorateInfoMapper(DecorateInfoMapper decorateInfoMapper) {
		this.setDao(decorateInfoMapper);
		this.decorateInfoMapper = decorateInfoMapper;
	}
	
	public Map<String, Object> getHomePageDecorateInfo(Integer linkId, JSONObject reqPRM, Integer memberId) {
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		Integer version = reqPRM.getInt("version");
		String system = reqPRM.getString("system");

		List<Map<String,Object>> decorateAreaList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		boolean isEmpty = true;
		if(linkId != null){
			DecorateInfoExample decorateInfoExample = new DecorateInfoExample();
			decorateInfoExample.createCriteria().andIdEqualTo(linkId).andDelFlagEqualTo("0");
			List<DecorateInfo> decorateInfos = selectByExample(decorateInfoExample);
			if(CollectionUtils.isNotEmpty(decorateInfos)){
				DecorateInfo decorateInfo = decorateInfos.get(0);
				map.put("decorateInfoName", decorateInfo.getDecorateName());
				map.put("decorateInfoId", decorateInfo.getId());
				DecorateAreaExample decorateAreaExample = new DecorateAreaExample();
				decorateAreaExample.createCriteria().andDecorateInfoIdEqualTo(decorateInfo.getId()).andDelFlagEqualTo("0");
				decorateAreaExample.setOrderByClause("ifnull(seq_no,99999),id");
				List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(decorateAreaExample);
				if(CollectionUtils.isNotEmpty(decorateAreas)){
					for (DecorateArea decorateArea : decorateAreas) {
						List<Map<String,Object>> decorateModuleList = new ArrayList<Map<String,Object>>();
						Map<String,Object> decorateAreaMap = new HashMap<String,Object>();
						decorateAreaMap.put("decorateAreaName", decorateArea.getAreaName());
						decorateAreaMap.put("decorateAreaId", decorateArea.getId());
						DecorateModuleExample decorateModuleExample = new DecorateModuleExample();
						decorateModuleExample.createCriteria().andDecorateAreaIdEqualTo(decorateArea.getId()).andDelFlagEqualTo("0");
						decorateModuleExample.setOrderByClause("ifnull(seq_no,99999) ASC,id desc");
						List<DecorateModule> decorateModules = decorateModuleService.selectByExampleWithBLOBs(decorateModuleExample);
						if(CollectionUtils.isNotEmpty(decorateModules)){
							for (DecorateModule decorateModule : decorateModules) {
								// 最新版本隐藏掉 story 1684
								if(decorateModule.getId()==3785 && version >=60){
									continue;
								}

								isEmpty = false;
								Map<String,Object> paramsMap = new HashMap<String,Object>();
								Map<String,Object> decorateModuleMap = new HashMap<String,Object>();
								List<Map<String,Object>> moduleNavigationList1 = new ArrayList<Map<String,Object>>();
								List<Map<String,Object>> moduleNavigationList2 = new ArrayList<Map<String,Object>>();
								List<Map<String,Object>> moduleNavigationList3 = new ArrayList<Map<String,Object>>();
								List<Map<String,Object>> moduleMapList = new ArrayList<Map<String,Object>>();
								List<Map<String,Object>> productBlockList = new ArrayList<Map<String,Object>>();
								List<Map<String,Object>> activityBlockList = new ArrayList<Map<String,Object>>();
								List<Map<String,Object>> productList = new ArrayList<Map<String,Object>>();
								List<Map<String,Object>> activityList = new ArrayList<Map<String,Object>>();
								Map<String, Object> secKillMap = new HashMap<String,Object>(); //秒杀：  会场秒杀  限时秒杀
								Map<String, Object> bgMap = new HashMap<String,Object>();
								Map<String, Object> topMap = new HashMap<String,Object>();
								//1图片 2商品 3特卖 4商品列表 5特卖列表
								String moduleType = decorateModule.getModuleType();
								Integer decorateModuleId = decorateModule.getId();
								String decorateModulePic = decorateModule.getPic();
								Integer showNum = decorateModule.getShowNum();
								String productType1Id = decorateModule.getProductType1Ids();
								String productType2Id = decorateModule.getProductType2Ids();
								String productBrandId = decorateModule.getProductBrandIds();
								String videoPath = decorateModule.getVideoPath();
								List<Integer> productType1IdList = new ArrayList<Integer>();
								List<Integer> productType2IdList = new ArrayList<Integer>();
								List<Integer> productBrandIdList = new ArrayList<Integer>();
								if(!StringUtil.isBlank(productType1Id)){
									String[] productType1Ids = productType1Id.split(",");
									for (String str : productType1Ids) {
										productType1IdList.add(Integer.valueOf(str));
									}
									paramsMap.put("productType1IdList", productType1IdList);
								}
								if(!StringUtil.isBlank(productType2Id)){
									String[] productType2Ids = productType2Id.split(",");
									for (String str : productType2Ids) {
										productType2IdList.add(Integer.valueOf(str));
									}
									paramsMap.put("productType2IdList", productType2IdList);
								}
								if(!StringUtil.isBlank(productBrandId)){
									String[] productBrandIds = productBrandId.split(",");
									for (String str : productBrandIds) {
										productBrandIdList.add(Integer.valueOf(str));
									}
									paramsMap.put("productBrandIdList", productBrandIdList);
								}
								decorateModuleMap.put("decorateModuleId", decorateModuleId);
								if(moduleType.equals("1") || moduleType.equals("8") || moduleType.equals("9")){
									List<Integer> mallCategoryList = new ArrayList<Integer>();
									if(!StringUtil.isBlank(decorateModulePic)){
										decorateModulePic = StringUtil.getPic(decorateModule.getPic(), "");
									}
									decorateModuleMap.put("decorateModulePic", decorateModulePic);
									ModuleMapExample moduleMapExample = new ModuleMapExample();
									moduleMapExample.createCriteria().andDecorateModuleIdEqualTo(decorateModule.getId()).andDelFlagEqualTo("0");
									List<ModuleMap> moduleMaps = moduleMapService.selectByExample(moduleMapExample);
									for (ModuleMap moduleMap : moduleMaps) {
										//找出类型为12商城一级类目
										if("12".equals(moduleMap.getLinkType()) && moduleMap.getLinkValue() != null){
											mallCategoryList.add(moduleMap.getLinkValue());
										}
									}
									//集体转化成特卖一级类目id
//									Map<String,String> categoryMap = mallCategoryMapService.getCategoryConvert(null,mallCategoryList) ;
									for (ModuleMap moduleMap : moduleMaps) {
										Map<String,Object> moduleMapMap = new HashMap<String,Object>();
										String corrds = moduleMap.getCoords();
										//0 不做任何处理（开发自己定义的）1会场 2特卖 3商品 4自建页面 5栏目 6一级分类 7商城一级页面 8商城二级页面 
										//9url链接 10分区锚点 11优惠券 12商城一级类目 13商家店铺 14 关键字 15新品牌团 16二级分类 17 微淘频道
										//18 有好货二级分类 19 有好货品牌ID 20 潮鞋上新二级分类 21 潮鞋上新品牌ID 22 潮流男装二级分类 23 潮流男装品牌ID
										//24 断码特惠二级分类 25 断码特惠品牌ID 26 运动鞋服二级分类 27 运动鞋服品牌ID 28 潮流美妆二级分类 29 潮流美妆品牌ID
										//30 食品超市二级分类 31 食品超市品牌ID
										String linkType = moduleMap.getLinkType();
										Integer linkValue = moduleMap.getLinkValue();
										Integer moduleMapId = moduleMap.getId();
										String linkUrl = moduleMap.getLinkUrl();
										String adCatalog = "";
										String adCatalogName = "";
										String msgType = "0";
										if(linkType.equals("4") && linkValue != null){
											linkType = "9";
											linkUrl = commonService.getCloumnLinkUrl(linkValue+"", "4");
										}else if(linkType.equals("5") && linkValue != null){
											if(linkValue.toString().equals("7")){
												linkType = "9";
												linkUrl = commonService.getCloumnLinkUrl(linkValue+"", "1");
											}else if(linkValue.toString().equals("8")){
												linkType = "9";
												linkUrl = commonService.getCloumnLinkUrl(linkValue+"", "2");
											}else if(linkValue.toString().equals("9")){
												linkType = "9";
												linkUrl = commonService.getCloumnLinkUrl(linkValue+"", "3");
											}else if(linkValue.toString().equals("25")){
												//STORY #1773 25.主会场
												linkType = "9";
												linkUrl = commonService.getCloumnLinkUrl(linkValue+"", "12");
											}else if(linkValue.toString().equals("28")) { //积分转盘
												linkType = "9";
												linkUrl = commonService.getCloumnLinkUrl(linkValue + "", "13");
											}
										}else if(linkType.equals("6") && linkValue != null){
											SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", linkValue.toString());
											if(cfg != null){
												adCatalog = cfg.getParamOrder();
												adCatalogName = cfg.getParamName();
											}
										}else if(linkType.equals("11") && linkValue != null){
											//优惠券，判断是否已抢光
											Coupon coupon = couponService.selectByPrimaryKey(linkValue);
											moduleMapMap.put("recType", coupon.getRecType());

											Map<String, Object> msgMap = memberCouponService.getMemberIsReceiveCoupon(coupon, memberId == null ? "" : memberId.toString());
											msgType = msgMap.get("msgType").toString();
											if(!"1".equals(msgType) && !"2".equals(msgType)){
												if (coupon.getRecBeginDate() != null && coupon.getRecBeginDate().compareTo(new Date()) > 0) {
													msgType = "3";
													moduleMapMap.put("recBeginDate", coupon.getRecBeginDate().getTime());
												} else {
													msgType = "0";
												}
											}

										}
										moduleMapMap.put("msgType", msgType);//0可以领取 1已领取 2已抢光 3时间还未开始
										moduleMapMap.put("moduleMapId", moduleMapId);
										moduleMapMap.put("corrds", corrds);
										moduleMapMap.put("linkType", linkType);
										moduleMapMap.put("linkValue", linkValue);
										moduleMapMap.put("linkUrl", linkUrl);
										moduleMapMap.put("adCatalog", adCatalog);
										moduleMapMap.put("adCatalogName", adCatalogName);
										moduleMapMap.put("currentDate", new Date());
										moduleMapList.add(moduleMapMap);
									}
								}else if(moduleType.equals("2")){
									//商品模块
									Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//活动中
									Map<String,Object> productParamsMap = new HashMap<String,Object>();
									productParamsMap.put("decorateModuleId", decorateModuleId);
									productParamsMap.put("pageSize", showNum);
									List<ProductCustom> products = productService.getProductByModuleItem(productParamsMap);
									if(CollectionUtils.isNotEmpty(products)){
										List<Integer> pIdList = new ArrayList<Integer>();
										for (ProductCustom productCustom : products) {
											pIdList.add(productCustom.getId());
										}
										Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
										paramsActivityMap.put("productIdList", pIdList);
										List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
										if(CollectionUtils.isNotEmpty(activityCustoms)){
											for (ActivityCustom activityCustom : activityCustoms) {
												activityMap.put(activityCustom.getProductId().toString(), activityCustom);
											}
										}
										for (ProductCustom productCustom : products) {
											Map<String,Object> productMap = new HashMap<String,Object>();
											Integer productId = productCustom.getId();
											String productName = productCustom.getName();
											String pic = StringUtil.getPic(productCustom.getPic(), "M");
											Integer stockSum = productCustom.getStockSum();
											BigDecimal salePrice = productCustom.getMinMallPrice();
											BigDecimal tagPrice = productCustom.getMinTagPrice();
											BigDecimal svipDiscount = productCustom.getSvipDiscount();
											BigDecimal svipSalePrice = new BigDecimal("0");
											if(activityMap.containsKey(productId.toString())){
												//活动的展示活动价格
												ActivityCustom activityCustom = activityMap.get(productId.toString());
												salePrice = new BigDecimal(activityCustom.getSalePrice());
												tagPrice = new BigDecimal(activityCustom.getTagPrice());
											}
											if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
												svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, Const.PRODUCT_ACTIVITY_TYPE_AREA);
											}
											productMap.put("productId", productId);
											productMap.put("productName", productName);
											productMap.put("salePrice", salePrice);
											productMap.put("tagPrice", tagPrice);
											productMap.put("discount", 0);
											productMap.put("stockSum", stockSum);
											productMap.put("pic", pic);
											productMap.put("svipSalePrice", svipSalePrice);
											productBlockList.add(productMap);
										}
									}
								}else if(moduleType.equals("3")){
									//特卖模块
									Map<String,Object> activityParamsMap = new HashMap<String,Object>();
									activityParamsMap.put("decorateModuleId", decorateModuleId);
									activityParamsMap.put("pageSize", showNum);
									List<ActivityCustom> activityCustoms = activityService.getActivityByModuleItem(activityParamsMap);
									if(CollectionUtils.isNotEmpty(activityCustoms)){
										for (ActivityCustom activityCustom : activityCustoms) {
											Map<String,Object> activityMap = new HashMap<String,Object>();
											String areaEntryPic = StringUtil.getPic(activityCustom.getAreaEntryPic(), "");
											String activityRemainingTime = DateUtil.getActivityRemainingTime(activityCustom.getActivityEndTime());
											activityMap.put("activityAreaId", activityCustom.getActivityAreaId());
											activityMap.put("activityId", activityCustom.getId());
											activityMap.put("activityAreaName", activityCustom.getName());
											activityMap.put("benefitPoint", activityCustom.getBenefitPoint());
											activityMap.put("areaEntryPic", areaEntryPic);
											activityMap.put("activityRemainingTime", activityRemainingTime);
											activityBlockList.add(activityMap);
										}
									}
								}else if(moduleType.equals("4")){
									//商品列表
									//先查出1，2级品类所对应的3极品类id。
									productList = getProductList(decorateModule,Const.RETURN_SIZE_10,0);
								}else if(moduleType.equals("5")){
									//特卖列表
									activityList = getActivityList(decorateModule,Const.RETURN_SIZE_10,0);
								}else if(moduleType.equals("11")){
									//11限时秒杀
									secKillMap = singleProductActivityService.selectSeckillTimeProdocutTab(Const.RETURN_SIZE_10, 0, "1", 4);
								}else if(moduleType.equals("12")){
									//12五粒导航,链接跳转方式与图片模块一模一样
									ModuleNavigationExample moduleNavigationExample = new ModuleNavigationExample();
									moduleNavigationExample.setOrderByClause("row asc,col asc");
									moduleNavigationExample.createCriteria().andDecorateModuleIdEqualTo(decorateModule.getId()).andDelFlagEqualTo("0");
									List<ModuleNavigation> moduleNavigations = moduleNavigationService.selectByExample(moduleNavigationExample);
									for(ModuleNavigation moduleNavigation:moduleNavigations){
										Map<String,Object> moduleNavigationMap = new HashMap<String,Object>();
										moduleNavigationMap.put("moduleNavigationId", moduleNavigation.getId());
										moduleNavigationMap.put("pic", StringUtil.getPic(moduleNavigation.getPic(), ""));
									 	String linkType = moduleNavigation.getLinkType();
									 	String linkValue = moduleNavigation.getLinkValue();
									 	String linkUrl = "";
										String adCatalog = "";
										String adCatalogName = "";
										String msgType = "0";
										if(linkType.equals("6") && linkValue != null){
											SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_CATALOG", linkValue);
											if(cfg != null){
												adCatalog = cfg.getParamOrder();
												adCatalogName = cfg.getParamName();
											}
										}else if(linkType.equals("4") && linkValue != null){
											linkType = "9";
											linkUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?pageid="+linkValue;
										}else if(linkType.equals("5")){
											if(linkValue.equals("7")){
												linkType = "9";
												linkUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/newsign/index.html";
											}else if(linkValue.equals("8")){
												linkType = "9";
												linkUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/cutprice/index.html";
											}else if(linkValue.equals("9")){
												linkType = "9";
												linkUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/freeprice/index.html";
											}
										}else if(linkType.equals("11") && linkValue != null){
											//优惠券，判断是否已抢光
											Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(linkValue));
											moduleNavigationMap.put("recType", coupon.getRecType());
											Map<String, Object> msgMap = memberCouponService.getMemberIsReceiveCoupon(coupon, memberId == null ? "" : memberId.toString());
											msgType = msgMap.get("msgType").toString();
											if(!"1".equals(msgType) && !"2".equals(msgType)){
												if(coupon.getRecBeginDate().compareTo(new Date()) > 0){
													msgType = "3";
													moduleNavigationMap.put("recBeginDate", coupon.getRecBeginDate().getTime());
												}else{
													msgType = "0";
												}
											}
										}else if(linkType.equals("14") || linkType.equals("16") || (Integer.parseInt(linkType)>=18 && Integer.parseInt(linkType)<=31)){
											if(!StringUtil.isEmpty(linkValue)){
												linkUrl = linkValue;
											}
										}
										moduleNavigationMap.put("msgType", msgType);//0可以领取 1已领取 2已抢光 3时间还未开始
										moduleNavigationMap.put("moduleNavigationId", moduleNavigation.getId());
										moduleNavigationMap.put("linkType", linkType);
										moduleNavigationMap.put("linkValue", linkValue);
										moduleNavigationMap.put("linkUrl", linkUrl);
										moduleNavigationMap.put("adCatalog", adCatalog);
										moduleNavigationMap.put("adCatalogName", adCatalogName);
										moduleNavigationMap.put("currentDate", new Date());
										moduleNavigationMap.put("row", moduleNavigation.getRow());
										moduleNavigationMap.put("col", moduleNavigation.getCol());
										int row = moduleNavigation.getRow();
										if(row == 1){//第一行
											moduleNavigationList1.add(moduleNavigationMap);
										}else if(row == 2){//第二行
											moduleNavigationList2.add(moduleNavigationMap);
										}else if(row == 3){//第三行
											moduleNavigationList3.add(moduleNavigationMap);
										}
									}
								}
								decorateModuleMap.put("moduleType", moduleType);//类型
								decorateModuleMap.put("moduleNavigationList1", moduleNavigationList1);//五粒导航模块
								decorateModuleMap.put("moduleNavigationList2", moduleNavigationList2);//五粒导航模块
								decorateModuleMap.put("moduleNavigationList3", moduleNavigationList3);//五粒导航模块
								decorateModuleMap.put("moduleMapList", moduleMapList);//图片模块
								decorateModuleMap.put("productBlockList", productBlockList);//商品模块
								decorateModuleMap.put("activityBlockList", activityBlockList);//特卖模块
								decorateModuleMap.put("productList", productList);//商品列表
								decorateModuleMap.put("activityList", activityList);//特卖列表
								decorateModuleMap.put("secKillMap", secKillMap);//秒杀：  会场秒杀  限时秒杀
								decorateModuleMap.put("bgMap", bgMap);//必购
								decorateModuleMap.put("topMap", topMap);//固定顶部栏
								decorateModuleMap.put("videoPath", StringUtil.getPic(videoPath, ""));//视频模块
								decorateModuleMap.put("graphicContent", decorateModule.getGraphicContent());//图文模块
								//sdfd
								decorateModuleList.add(decorateModuleMap);
							}
						}
						decorateAreaMap.put("decorateModuleList", decorateModuleList);
						decorateAreaList.add(decorateAreaMap);
					}
				}
			}
		}else{
			map.put("decorateInfoName", "");
			map.put("decorateInfoId", "");
		}
		if(isEmpty){
			decorateAreaList.clear();
		}
		map.put("decorateAreaList", decorateAreaList);
		map.put("currentTime", new Date());
		return map;
	}
	
	/**
	 * 
	 * 方法描述 ：商品列表
	 * @author  chenwf: 
	 * @date 创建时间：2018年3月21日 上午10:40:30 
	 * @version
	 * @param decorateModule
	 * @param currentPage 
	 * @param pageSize 
	 * @return
	 */
	public List<Map<String, Object>> getProductList(DecorateModule decorateModule, Integer pageSize, Integer currentPage) {
		List<Map<String,Object>> productList = new ArrayList<Map<String,Object>>();
		if(decorateModule != null && decorateModule.getModuleType().equals("4")){
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			String productType1Id = decorateModule.getProductType1Ids();
			String productType2Id = decorateModule.getProductType2Ids();
			String productBrandId = decorateModule.getProductBrandIds();
			List<Integer> productType1IdList = new ArrayList<Integer>();
			List<Integer> productType2IdList = new ArrayList<Integer>();
			List<Integer> productBrandIdList = new ArrayList<Integer>();
			if(!StringUtil.isBlank(productType1Id)){
				String[] productType1Ids = productType1Id.split(",");
				for (String str : productType1Ids) {
					productType1IdList.add(Integer.valueOf(str));
				}
				paramsMap.put("productType1IdList", productType1IdList);
			}
			if(!StringUtil.isBlank(productType2Id)){
				String[] productType2Ids = productType2Id.split(",");
				for (String str : productType2Ids) {
					productType2IdList.add(Integer.valueOf(str));
				}
				if(CollectionUtils.isNotEmpty(productType2IdList)){
					//二级类目有值，就不需要一级类目
					paramsMap.remove("productType1IdList");
				}
				paramsMap.put("productType2IdList", productType2IdList);
			}
			if(!StringUtil.isBlank(productBrandId)){
				String[] productBrandIds = productBrandId.split(",");
				for (String str : productBrandIds) {
					productBrandIdList.add(Integer.valueOf(str));
				}
				paramsMap.put("productBrandIdList", productBrandIdList);
			}
			paramsMap.put("pageSize", pageSize);
			paramsMap.put("currentPage", currentPage*pageSize);
			List<ProductCustom> products = productService.getProductByParamsMap(paramsMap);
			if(CollectionUtils.isNotEmpty(products)){
				Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//活动中
				List<Integer> pIdList = new ArrayList<Integer>();
				for (ProductCustom productCustom : products) {
					pIdList.add(productCustom.getId());
				}
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productIdList", pIdList);
				List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
				if(CollectionUtils.isNotEmpty(activityCustoms)){
					for (ActivityCustom activityCustom : activityCustoms) {
						activityMap.put(activityCustom.getProductId().toString(), activityCustom);
					}
				}
				for (ProductCustom productCustom : products) {
					Map<String,Object> productMap = new HashMap<String,Object>();
					Integer productId = productCustom.getId();
					String productName = productCustom.getName();
					Integer stockSum = productCustom.getStockSum();
					String pic = StringUtil.getPic(productCustom.getPic(), "M");
					BigDecimal salePrice = productCustom.getMinMallPrice();
					BigDecimal tagPrice = productCustom.getMinTagPrice();
					BigDecimal svipDiscount = productCustom.getSvipDiscount();
					BigDecimal svipSalePrice = new BigDecimal("0");
					if(activityMap.containsKey(productId.toString())){
						//活动的展示活动价格
						ActivityCustom activityCustom = activityMap.get(productId.toString());
						salePrice = new BigDecimal(activityCustom.getSalePrice());
						tagPrice = new BigDecimal(activityCustom.getTagPrice());
					}
					if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
						svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, Const.PRODUCT_ACTIVITY_TYPE_AREA);
					}
					productMap.put("productId", productId);
					productMap.put("productName", productName);
					productMap.put("salePrice", salePrice);
					productMap.put("tagPrice", tagPrice);
					productMap.put("discount", 0);
					productMap.put("stockSum", stockSum);
					productMap.put("pic", pic);
					productMap.put("svipSalePrice", svipSalePrice);
					productList.add(productMap);
				}
			}
		}
		return productList;
	}
	
	/**
	 * 
	 * 方法描述 ：活动列表
	 * @author  chenwf: 
	 * @date 创建时间：2018年3月21日 上午10:40:20 
	 * @version
	 * @param decorateModule
	 * @return
	 */
	public List<Map<String, Object>> getActivityList(DecorateModule decorateModule, Integer pageSize, Integer currentPage) {
		List<Map<String,Object>> activityList = new ArrayList<Map<String,Object>>();
		if(decorateModule != null && decorateModule.getModuleType().equals("5")){
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			String productType1Id = decorateModule.getProductType1Ids();
			String productType2Id = decorateModule.getProductType2Ids();
			String productBrandId = decorateModule.getProductBrandIds();
			List<Integer> productType1IdList = new ArrayList<Integer>();
			List<Integer> productType2IdList = new ArrayList<Integer>();
			List<Integer> productBrandIdList = new ArrayList<Integer>();
			if(!StringUtil.isBlank(productType1Id)){
				String[] productType1Ids = productType1Id.split(",");
				for (String str : productType1Ids) {
					productType1IdList.add(Integer.valueOf(str));
				}
				paramsMap.put("productType1IdList", productType1IdList);
			}
			if(!StringUtil.isBlank(productType2Id)){
				String[] productType2Ids = productType2Id.split(",");
				for (String str : productType2Ids) {
					productType2IdList.add(Integer.valueOf(str));
				}
				paramsMap.put("productType2IdList", productType2IdList);
			}
			if(!StringUtil.isBlank(productBrandId)){
				String[] productBrandIds = productBrandId.split(",");
				for (String str : productBrandIds) {
					productBrandIdList.add(Integer.valueOf(str));
				}
				paramsMap.put("productBrandIdList", productBrandIdList);
			}
			paramsMap.put("pageSize", pageSize);
			paramsMap.put("currentPage", currentPage*pageSize);
			List<ActivityCustom> activityCustoms = activityService.getActivityByIds(paramsMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom activityCustom : activityCustoms) {
					Map<String,Object> activityMap = new HashMap<String,Object>();
					String areaEntryPic = StringUtil.getPic(activityCustom.getAreaEntryPic(), "");
					String activityRemainingTime = DateUtil.getActivityRemainingTime(activityCustom.getActivityEndTime());
					activityMap.put("activityAreaId", activityCustom.getActivityAreaId());
					activityMap.put("activityId", activityCustom.getId());
					activityMap.put("activityAreaName", activityCustom.getName());
					activityMap.put("benefitPoint", activityCustom.getBenefitPoint());
					activityMap.put("areaEntryPic", areaEntryPic);
					activityMap.put("activityRemainingTime", activityRemainingTime);
					activityList.add(activityMap);
				}
			}
		}
		return activityList;
	}
}

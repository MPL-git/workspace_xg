package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.*;
import com.jf.dao.CouponModuleTimeMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.dao.SeckillModuleColorSettingMapper;
import com.jf.entity.*;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
	private AdInfoService adInfoService;
	@Resource
	private ProductService productService;
	@Resource
	private ActivityService activityService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ProductItemService productItemService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private BgModuleCategoryService bgModuleCategoryService;
	@Resource
	private MallCategoryMapService mallCategoryMapService;
	@Resource
	private TopFieldModuleFieldService topFieldModuleFieldService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private CouponService couponService;
	@Resource
	private BrandTeamTypeService brandTeamTypeService;
	@Resource
	private ModuleNavigationService moduleNavigationService;
	@Resource
	private CouponModuleTimeMapper couponModuleTimeMapper;
	@Resource
	private SeckillModuleColorSettingMapper seckillModuleColorSettingMapper;
	@Resource
	private ProductModuleTypeService productModuleTypeService;

	@Autowired
	public void setDecorateInfoMapper(DecorateInfoMapper decorateInfoMapper) {
		this.setDao(decorateInfoMapper);
		this.decorateInfoMapper = decorateInfoMapper;
	}

	public Map<String, Object> getHomePageDecorateInfo(Integer linkId, JSONObject reqPRM, Integer memberId) {
		Integer version = reqPRM.getInt("version");
		String system = reqPRM.getString("system");
		Date currentDate = new Date();
		List<Map<String,Object>> decorateAreaList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
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
								List<Map<String,Object>> productModuleTypeList = new ArrayList<Map<String,Object>>();
								Map<String, Object> secKillMap = new HashMap<String,Object>(); //秒杀：  会场秒杀  限时秒杀
								Map<String, Object> bgMap = new HashMap<String,Object>();
								Map<String, Object> topMap = new HashMap<String,Object>();
								//STORY #1780 领券秒杀模块
								List<Map<String, Object>> timeCouponList = new ArrayList<Map<String, Object>>();
								List<Map<String, Object>> finnalTimeCouponList = new ArrayList<Map<String, Object>>();
								String totalBgColor = "";
								String selectedFontColor = "";
								String timeColBgColor = "";
								String selectedBgColor = "";
								String btnDefaultBgColor = "";
								String btnSelectedBgColor = "";
								String couponBgColor = "";
								String defaultFontColor = "";
								//STORY1967 【优化】【平台端/APP端/小程序】会场装修商品模块优化
								String bgColor = "";
								String fieldFontColor = "";
								String fieldSelectFontColor = "";
								String fieldBgColor = "";
								//1图片 2商品 3特卖 4商品列表 5特卖列表
								String videoPath = decorateModule.getVideoPath();
								String moduleType = decorateModule.getModuleType();
								Integer decorateModuleId = decorateModule.getId();
								String decorateModulePic = decorateModule.getPic();
								Integer showNum = decorateModule.getShowNum();
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
								decorateModuleMap.put("decorateModuleId", decorateModuleId);
								if(moduleType.equals("1") || moduleType.equals("8") || moduleType.equals("9")){
									List<Integer> mallCategoryList = new ArrayList<Integer>();
									if(!StringUtil.isBlank(decorateModulePic)){
										decorateModulePic = FileUtil.getImageServiceUrl()+decorateModule.getPic();
									}
									decorateModuleMap.put("decorateModulePic", decorateModulePic);
									ModuleMapExample moduleMapExample = new ModuleMapExample();
									moduleMapExample.createCriteria().andDecorateModuleIdEqualTo(decorateModule.getId()).andDelFlagEqualTo("0");
									List<ModuleMap> moduleMaps = moduleMapService.selectByExample(moduleMapExample);
									if((version >= 36 && version <= 39 && Const.ANDROID.equals(system)) || (version >= 37 && version <= 42 && Const.IOS.equals(system))){
										for (ModuleMap moduleMap : moduleMaps) {
											//找出类型为12商城一级类目
											if("12".equals(moduleMap.getLinkType()) && moduleMap.getLinkValue() != null){
												mallCategoryList.add(moduleMap.getLinkValue());
											}
										}
									}
									//集体转化成特卖一级类目id
									Map<String,String> categoryMap = mallCategoryMapService.getCategoryConvert(null,mallCategoryList) ;
									for (ModuleMap moduleMap : moduleMaps) {
										Map<String,Object> moduleMapMap = new HashMap<String,Object>();
										String corrds = moduleMap.getCoords();
										//0 不做任何处理（开发自己定义的）1会场 2特卖 3商品 4自建页面 5栏目 6一级分类 7商城一级页面 8商城二级页面 9url链接 10分区锚点11优惠券 12新版本商城类目 13商家店铺
										String linkType = moduleMap.getLinkType();
										Integer linkValue = moduleMap.getLinkValue();
										Integer moduleMapId = moduleMap.getId();
										String linkUrl = moduleMap.getLinkUrl();
										String adCatalog = "";
										String adCatalogName = "";
										String msgType = "0";
										int fontSize = 0;
										long countDownBeginDate = 0;
										long countDownEndDate = 0;
										String fontColor = "";
										if(linkType.equals("6") && linkValue != null){
											SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", linkValue.toString());
											if(cfg != null){
												adCatalog = cfg.getParamOrder();
												adCatalogName = cfg.getParamName();
											}
										}else if(linkType.equals("12") && linkValue != null){
											//Android >= 36,Ios >= 37 有存在旧版本的商城
											//Android >= 40,Ios >= 43 有存在新版本的商城
											//Android >= 36,Ios >= 37 有给H5提供获取版本号的接口
											if(Const.ANDROID.equals(system) || Const.IOS.equals(system)){
												//只针对移动端的才进行版本处理
												if(version != null && !StringUtil.isBlank(system)){
													if((version < 36 && Const.ANDROID.equals(system)) || (version < 37 && Const.IOS.equals(system))){
														//版本太旧没有商城的新旧版本，不做跳转功能
														linkType = "0";
													}else if((version >= 36 && version <= 39 && Const.ANDROID.equals(system)) || (version >= 37 && version <= 42 && Const.IOS.equals(system))){
														//存在旧版本的商城，商城类目id要转化成特卖一级类目id
														if(categoryMap.containsKey(linkValue.toString())){
															//找到所对应的特卖一级类目id
															linkValue = Integer.valueOf(categoryMap.get(linkValue.toString()));
															linkType = "7"; 
														}else{
															linkType = "0";
														}
													}else{
														//这边就是最新版本的商城，不做处理
													}
												}else{
													//不存在版本号，前端不做跳转功能
													linkType = "0";
												}
											}
										}else if(linkType.equals("11") && linkValue != null){
											//优惠券，判断是否已抢光
											Coupon coupon = couponService.selectByPrimaryKey(linkValue);
											moduleMapMap.put("recType", coupon.getRecType());
											if(memberId != null){
												Map<String, Object> msgMap = memberCouponService.isReceiveCoupon(memberId, coupon);
												msgType = msgMap.get("msgType").toString();
												if(!"1".equals(msgType) && !"2".equals(msgType)){
													if(coupon.getRecBeginDate().compareTo(currentDate) > 0){
														msgType = "3";
														moduleMapMap.put("recBeginDate", coupon.getRecBeginDate().getTime());
													}else{
														msgType = "0";
													}
												}
												if(msgMap.containsKey("isSvip")){
													moduleMapMap.put("isSvip", msgMap.get("isSvip"));
												}else{
													moduleMapMap.put("isSvip", "-1");
												}
											}
										}else if(linkType.equals("15") && linkValue != null){
											if(!system.equals(Const.IOS) && !system.equals(Const.ANDROID) && !system.equals(Const.WX_XCX)){
												//15新品牌团（跳转到对应一级类目）
												//版本兼容，H5暂未开发新品牌团，设置为跳转到一级类目
												linkType = "6";
												BrandTeamType brandTeamType = brandTeamTypeService.selectByPrimaryKey(linkValue);
												if(brandTeamType != null){
													linkValue = brandTeamType.getProductTypeId();
													SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", linkValue.toString());
													if(cfg != null){
														adCatalog = cfg.getParamOrder();
														adCatalogName = cfg.getParamName();
													}
												}else{
													linkType = "";
												}
												
											}
										}else if(linkType.equals("33")){//33.倒计时 STORY #1708
											countDownBeginDate = moduleMap.getCountDownBeginDate().getTime();
											countDownEndDate = moduleMap.getCountDownEndDate().getTime();
											fontSize = moduleMap.getFontSize();
											fontColor = moduleMap.getFontColor();
										}
										moduleMapMap.put("msgType", msgType);//0可以领取 1已领取 2已抢光 3时间还未开始
										moduleMapMap.put("moduleMapId", moduleMapId);
										moduleMapMap.put("corrds", corrds);
										moduleMapMap.put("linkType", linkType);
										moduleMapMap.put("linkValue", linkValue);
										moduleMapMap.put("linkUrl", linkUrl);
										moduleMapMap.put("adCatalog", adCatalog);
										moduleMapMap.put("adCatalogName", adCatalogName);
										moduleMapMap.put("fontSize", fontSize);
										moduleMapMap.put("fontColor", fontColor);
										moduleMapMap.put("countDownBeginDate", countDownBeginDate);
										moduleMapMap.put("countDownEndDate", countDownEndDate);
										moduleMapList.add(moduleMapMap);
									}
								}else if(moduleType.equals("2")){
									//商品模块
									//STORY #1967
									Map<String,Object> paramMap = new HashMap<String,Object>();
									paramMap.put("decorateModuleId",decorateModuleId);
									List<ProductModuleTypeCustom> productModuleTypeCustoms = productModuleTypeService.getProductModuleTypeCustomList(paramMap);
									Integer productModuleTypeId = null;
									if(productModuleTypeCustoms!=null && productModuleTypeCustoms.size()>0){
										bgColor = decorateModule.getBgColor();
										fieldBgColor = decorateModule.getFieldBgColor();
										fieldFontColor = decorateModule.getFieldFontColor();
										fieldSelectFontColor = decorateModule.getFieldSelectFontColor();
										for(ProductModuleTypeCustom productModuleTypeCustom:productModuleTypeCustoms){
											if(productModuleTypeCustom.getProductCount() > 0){
												Map<String,Object> productModuleTypeMap = new HashMap<String,Object>();
												productModuleTypeMap.put("productModuleTypeId",productModuleTypeCustom.getId());
												productModuleTypeMap.put("name",productModuleTypeCustom.getName());
												productModuleTypeMap.put("seqNo",productModuleTypeCustom.getSeqNo());
												productModuleTypeList.add(productModuleTypeMap);
												if(productModuleTypeId == null){
													productModuleTypeId = productModuleTypeCustom.getId();
												}
											}
										}
									}
									productBlockList = getProductModuleList(productBlockList, decorateModuleId, showNum, productModuleTypeId);
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
								}else if(moduleType.equals("6")){
									//6 会场/限时秒杀
									//STORY #1780
									String seckillType = "1";//1.限时秒杀
									SeckillModuleColorSettingExample smcse = new SeckillModuleColorSettingExample();
									smcse.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(decorateModuleId);
									List<SeckillModuleColorSetting> seckillModuleColorSettingList = seckillModuleColorSettingMapper.selectByExample(smcse);
									if(seckillModuleColorSettingList!=null && seckillModuleColorSettingList.size()>0){
										SeckillModuleColorSetting seckillModuleColorSetting = seckillModuleColorSettingList.get(0);
										totalBgColor = seckillModuleColorSetting.getTotalBgColor();
										timeColBgColor = seckillModuleColorSetting.getTimeColBgColor();
										selectedBgColor = seckillModuleColorSetting.getSelectedBgColor();
										btnDefaultBgColor = seckillModuleColorSetting.getBtnDefaultBgColor();
										btnSelectedBgColor = seckillModuleColorSetting.getBtnSelectedBgColor();
										couponBgColor = seckillModuleColorSetting.getCouponBgColor();
										defaultFontColor = seckillModuleColorSetting.getDefaultFontColor();
										selectedFontColor = seckillModuleColorSetting.getSelectedFontColor();
										seckillType = seckillModuleColorSetting.getDataSource();
									}
									secKillMap = singleProductActivityService.getActivityAreaSecKillProdocut(Const.RETURN_SIZE_10,0,seckillType);
								}else if(moduleType.equals("7")){
									//7 必购
									bgMap = bgModuleCategoryService.getBgModules(decorateModule.getId());
								}else if(moduleType.equals("10")) {
									//10 固定顶部栏
									topMap = topFieldModuleFieldService.getBgModules(decorateModule);
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
										}else if(linkType.equals("11") && linkValue != null){
											//优惠券，判断是否已抢光
											Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(linkValue));
											moduleNavigationMap.put("recType", coupon.getRecType());
											if(memberId != null){
												Map<String, Object> msgMap = memberCouponService.isReceiveCoupon(memberId, coupon);
												msgType = msgMap.get("msgType").toString();
												if(!"1".equals(msgType) && !"2".equals(msgType)){
													if(coupon.getRecBeginDate().compareTo(currentDate) > 0){
														msgType = "3";
														moduleNavigationMap.put("recBeginDate", coupon.getRecBeginDate().getTime());
													}else{
														msgType = "0";
													}
												}
												if(msgMap.containsKey("isSvip")){
													moduleNavigationMap.put("isSvip", msgMap.get("isSvip"));
												}else{
													moduleNavigationMap.put("isSvip", "-1");
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
								}else if(moduleType.equals("15")){//15.领券秒杀模块
									try {
										CouponModuleTimeExample example = new CouponModuleTimeExample();
										example.setOrderByClause("start_hours desc,start_min desc");//倒序排
										example.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(decorateModuleId);
										List<CouponModuleTime> couponModuleTimeList = couponModuleTimeMapper.selectByExample(example);
										if(couponModuleTimeList!=null && couponModuleTimeList.size()>0){
											String minTodayTime = "";
											String maxDayTime = "";
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
											final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											String today = sdf.format(currentDate);
											String tomorrow = sdf.format(DateUtil.addDay(currentDate, 1));
											String afterTomorrow = sdf.format(DateUtil.addDay(currentDate, 2));
											minTodayTime = today+" "+couponModuleTimeList.get(couponModuleTimeList.size()-1).getStartHours()+":"+couponModuleTimeList.get(couponModuleTimeList.size()-1).getStartMin()+":00";
											maxDayTime = afterTomorrow+" "+couponModuleTimeList.get(0).getStartHours()+":"+couponModuleTimeList.get(0).getStartMin()+":00";
											List<String> totalTimeStrList = new ArrayList<String>();
											for(CouponModuleTime couponModuleTime:couponModuleTimeList){
												String eachTodayTime = today+" "+couponModuleTime.getStartHours()+":"+couponModuleTime.getStartMin()+":00";
												String eachTomorrowTime = tomorrow+" "+couponModuleTime.getStartHours()+":"+couponModuleTime.getStartMin()+":00";
												String eachAfterTomorrowTime = afterTomorrow+" "+couponModuleTime.getStartHours()+":"+couponModuleTime.getStartMin()+":00";
												totalTimeStrList.add(eachAfterTomorrowTime);
												totalTimeStrList.add(eachTomorrowTime);
												totalTimeStrList.add(eachTodayTime);
											}
											Map<String,Object> paramMap = new HashMap<String,Object>();
											paramMap.put("memberId", memberId==null?0:memberId);
											paramMap.put("activityType", "2");
											paramMap.put("status", "1");
											paramMap.put("minTodayTime", minTodayTime);
											paramMap.put("maxDayTime", maxDayTime);
											List<CouponCustom> couponCustomList = couponService.getCouponListByModuleTime(paramMap);
											Collections.sort(totalTimeStrList,new Comparator<String>(){
												public int compare(String o1,String o2){
													try {
														if(df.parse(o1).after(df.parse(o2))){
															return 1;
														}else{
															return -1;
														}
													} catch (ParseException e) {
														e.printStackTrace();
														return 0;
													}
												}
											});
											for(String eachTimeStr:totalTimeStrList){
												if(df.parse(eachTimeStr).before(df.parse(minTodayTime))){//比要展示的最小时间还小则跳过
													continue;
												}
												List<CouponCustom> eachTimeCouponCustomList = new ArrayList<CouponCustom>();
												for(CouponCustom couponCustom:couponCustomList){
													if(df.format(couponCustom.getRecBeginDate()).equals(eachTimeStr)){
														eachTimeCouponCustomList.add(couponCustom);
													}
												}
												Map<String,Object> timeCouponMap = new HashMap<String,Object>();
												if(eachTimeCouponCustomList.size()>0){
													String timeDesc = "即将开始";
													if(df.parse(eachTimeStr).before(currentDate)){
														timeDesc = "开抢中";
													}
													String dayStr = eachTimeStr.split(" ")[0].split("-")[2];
													dayStr = this.dealWithDayStr(dayStr);
													String hourMinute = eachTimeStr.split(" ")[1].substring(0, 5);
													timeCouponMap.put("currentTime", currentDate.getTime());
													timeCouponMap.put("recBeginDate", eachTimeStr);
													timeCouponMap.put("dayStr", dayStr);
													timeCouponMap.put("hourMinute", hourMinute);
													timeCouponMap.put("timeDesc", timeDesc);
													List<Map<String,Object>> couponCustomMapList = new ArrayList<Map<String,Object>>();
													for(CouponCustom couponCustom:eachTimeCouponCustomList){
														Map<String,Object> couponCustomMap = new HashMap<String,Object>();
														couponCustomMap.put("couponId", couponCustom.getId().toString());
														couponCustomMap.put("name", couponCustom.getName());
														if("4".equals(couponCustom.getRecType())){
															couponCustomMap.put("needSvip", "1");
														}else{
															couponCustomMap.put("needSvip", "0");
														}
														String couponType = couponCustom.getCouponType();
														String couponPreferentialType = couponCustom.getPreferentialType();//1优惠券满减 2优惠券折扣
														String conditionType = couponCustom.getConditionType();
														BigDecimal minimum = couponCustom.getMinimum();
														BigDecimal money = couponCustom.getMoney();
														BigDecimal maxDiscountMoney = couponCustom.getMaxDiscountMoney();
														Integer minicount = couponCustom.getMinicount();//最低几件
														String preferentialInfo = "";
														if(couponType.equals("4")){
															preferentialInfo = "无门槛优惠券";
														}else{
															if("1".equals(couponPreferentialType)){
																if(minimum.subtract(money).compareTo(new BigDecimal("0.01")) == 0){
																	preferentialInfo = "无门槛优惠券";
																}else{
																	preferentialInfo = "满"+minimum.stripTrailingZeros().toPlainString()+"元可用";
																}
																
															}else if("2".equals(couponPreferentialType)){
																if("1".equals(conditionType)){
																	preferentialInfo = "无门槛优惠券";
																}else if("2".equals(conditionType)){
																	preferentialInfo = "满"+minimum.stripTrailingZeros().toPlainString()+"元可用";
																}else if("3".equals(conditionType)){
																	preferentialInfo = "满"+minicount+"件可用";
																}
																if(maxDiscountMoney != null){
																	preferentialInfo += " 最多优惠"+maxDiscountMoney.stripTrailingZeros().toPlainString()+"元";
																}
															}
														}
														couponCustomMap.put("preferentialInfo", preferentialInfo);
														
														String rang = couponCustom.getRang();
														String useDescription = "";
														if(rang.equals("1")){//全平台
															if("2".equals(couponType)){//品类券
																useDescription = "";
																if(!StringUtil.isEmpty(couponCustom.getTypeIds())){
																	useDescription = "限"+couponCustom.getProductTypeNames()+"品类可用";
																}else{
																	useDescription = "全品类可用";
																}
															}else if("4".equals(couponType)){
																useDescription = "指定商品可用";
															}
														}else if(rang.equals("2")){
															useDescription = "指定会场可用";
														}else if(rang.equals("3")){
															useDescription = "指定会场可用";
														}else if(rang.equals("4")){
															if("4".equals(couponType)){
																useDescription = "指定商品可用";
															}else{
																useDescription = "指定店铺可用";
															}
														}
														couponCustomMap.put("useDescription", useDescription);
														
														DecimalFormat decimalFormat = new DecimalFormat("#.##");
														if(couponCustom.getDiscount()!=null){
															couponCustomMap.put("discount", decimalFormat.format(couponCustom.getDiscount().multiply(new BigDecimal(10)))+"折");
														}else{
															couponCustomMap.put("discount", "");
														}
														if(couponCustom.getMoney()!=null){
															couponCustomMap.put("money", decimalFormat.format(couponCustom.getMoney())+"元");
														}else{
															couponCustomMap.put("money", "");
														}
														couponCustomMap.put("recBeginDate", df.format(couponCustom.getRecBeginDate()));
														if(couponCustom.getRecBeginDate().after(currentDate)){
															couponCustomMap.put("msgType", "-1");//即将开始
															couponCustomMap.put("msg", "即将开始");
														}else{
															// 优惠券发行数量
															Integer grantQuantity = couponCustom.getGrantQuantity() == null ? 0 : couponCustom.getGrantQuantity();
															// 优惠券已领数量
															Integer recQuantity = couponCustom.getRecQuantity() == null ? 0 : couponCustom.getRecQuantity();
															// 优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
															String recLimitType = couponCustom.getRecLimitType() == null ? "" : couponCustom.getRecLimitType();
															// 优惠券 限领数量
															Integer recEach = couponCustom.getRecEach() == null ? 0 : couponCustom.getRecEach();
															String msgType = "0";//0.可领取 1已领取 2.领光
															String msg = "可领取";
															if (grantQuantity > 0 && grantQuantity > recQuantity) {
																if (couponCustom.getRecDate()!=null) {
																	String recDate = DateUtil.getFormatDate(couponCustom.getRecDate(), "yyyyMMdd");
																	String currentDateStr = DateUtil.getFormatDate(currentDate, "yyyyMMdd");
																	// 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
																	if (recLimitType.equals("1")) {
																		if (recDate.equals(currentDateStr)) {
																			msgType = "1";
																			msg = "每人每天限领1张 ";
																		}
																	} else if (recLimitType.equals("2")) {
																		if (couponCustom.getMemberCouponIds().split(",").length >= recEach) {
																			msgType = "1";
																			msg = "每人限领 " + recEach + "张";
																		}
																	} else if (recLimitType.equals("3")) {//3.不限
																		
																	} else {
																		msgType = "2";
																		msg = "优惠券已抢光";
																	}
																}
															} else {
																msgType = "2";
																msg = "优惠券已抢光";
															}
															couponCustomMap.put("msgType", msgType);
															couponCustomMap.put("msg", msg);
														}
														couponCustomMapList.add(couponCustomMap);
													}
													timeCouponMap.put("couponList", couponCustomMapList);
												}
												if(timeCouponMap.size()>0){
													timeCouponList.add(timeCouponMap);
												}
											}
											Collections.reverse(timeCouponList); // 倒序排列 ,从大到小
											String beginMinDateStr = "";//最小的开始时间（最接近当前时间（小于当前时间的优先）的，显示在APP端的）
											for(Map<String, Object> eachMap:timeCouponList){
												String eachDateStr = (String)eachMap.get("recBeginDate");
												if(df.parse(eachDateStr).before(currentDate)){
													beginMinDateStr  = eachDateStr;
													break;
												}
											}
											Collections.reverse(timeCouponList);// 再倒序，从小到大
											if(StringUtil.isEmpty(beginMinDateStr)){
												if(timeCouponList!=null && timeCouponList.size()>0){
													beginMinDateStr = (String)timeCouponList.get(0).get("recBeginDate");
												}
											}
											for(Map<String, Object> eachMap:timeCouponList){
												String eachDateStr = (String)eachMap.get("recBeginDate");
												if(df.parse(eachDateStr).before(df.parse(beginMinDateStr))){
													continue;
												}else{
													if(finnalTimeCouponList.size()>=5){
														break;
													}else{
														finnalTimeCouponList.add(eachMap);
													}
												}
											}
											if(finnalTimeCouponList.size()>0){
												SeckillModuleColorSettingExample e = new SeckillModuleColorSettingExample();
												e.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(decorateModuleId);
												List<SeckillModuleColorSetting> seckillModuleColorSettingList = seckillModuleColorSettingMapper.selectByExample(e);
												if(seckillModuleColorSettingList!=null && seckillModuleColorSettingList.size()>0){
													SeckillModuleColorSetting seckillModuleColorSetting = seckillModuleColorSettingList.get(0);
													totalBgColor = seckillModuleColorSetting.getTotalBgColor();
													timeColBgColor = seckillModuleColorSetting.getTimeColBgColor();
													selectedBgColor = seckillModuleColorSetting.getSelectedBgColor();
													btnDefaultBgColor = seckillModuleColorSetting.getBtnDefaultBgColor();
													btnSelectedBgColor = seckillModuleColorSetting.getBtnSelectedBgColor();
													couponBgColor = seckillModuleColorSetting.getCouponBgColor();
													defaultFontColor = seckillModuleColorSetting.getDefaultFontColor();
													selectedFontColor = seckillModuleColorSetting.getSelectedFontColor();
												}
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
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
								decorateModuleMap.put("videoPath", StringUtil.getPic(videoPath, ""));//13视频
								decorateModuleMap.put("videoPic", StringUtil.getPic(decorateModulePic, ""));//13视频封面图
								decorateModuleMap.put("graphicContent", decorateModule.getGraphicContent());//图文模块
								//STORY #1780
								decorateModuleMap.put("totalBgColor", totalBgColor);
								decorateModuleMap.put("selectedFontColor", selectedFontColor);
								decorateModuleMap.put("timeColBgColor", timeColBgColor);
								decorateModuleMap.put("selectedBgColor", selectedBgColor);
								decorateModuleMap.put("btnDefaultBgColor", btnDefaultBgColor);
								decorateModuleMap.put("btnSelectedBgColor", btnSelectedBgColor);
								decorateModuleMap.put("couponBgColor", couponBgColor);
								decorateModuleMap.put("defaultFontColor", defaultFontColor);
								decorateModuleMap.put("timeCouponList", finnalTimeCouponList);
								//STORY #1967
								decorateModuleMap.put("showModel",decorateModule.getShowModel());
								decorateModuleMap.put("bgColor", bgColor);
								decorateModuleMap.put("fieldFontColor", fieldFontColor);
								decorateModuleMap.put("fieldSelectFontColor", fieldSelectFontColor);
								decorateModuleMap.put("fieldBgColor", fieldBgColor);
								decorateModuleMap.put("productModuleTypeList", productModuleTypeList);
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
		map.put("decorateAreaList", decorateAreaList);
		map.put("currentTime", new Date());
		return map;
	}

	public List<Map<String, Object>> getProductModuleList(List<Map<String, Object>> productBlockList, Integer decorateModuleId, Integer showNum, Integer productModuleTypeId) {
		//商品模块
		Map<String, ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//活动中
		Map<String,Object> productParamsMap = new HashMap<String,Object>();
		productParamsMap.put("decorateModuleId", decorateModuleId);
		productParamsMap.put("pageSize", showNum);
		if(productModuleTypeId!=null){
			productParamsMap.put("productModuleTypeId", productModuleTypeId);
		}
		List<ProductCustom> products = productService.getProductByModuleItem(productParamsMap);
		if(CollectionUtils.isNotEmpty(products)){
			List<Integer> pIdList = new ArrayList<Integer>();
			for (ProductCustom productCustom : products) {
				pIdList.add(productCustom.getId());
			}
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productIdList", pIdList);
			paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
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
				BigDecimal arrivalPrice = new BigDecimal("0");
				BigDecimal deposit = new BigDecimal("0");
				BigDecimal deductAmount = new BigDecimal("0");
				if(activityMap.containsKey(productId.toString())){
					//活动的展示活动价格
					ActivityCustom activityCustom = activityMap.get(productId.toString());
					salePrice = new BigDecimal(activityCustom.getSalePrice());
					tagPrice = new BigDecimal(activityCustom.getTagPrice());
					if(activityCustom.getActivityEndTime().after(new Date()) && activityCustom.getDeposit()!=null && activityCustom.getDeductAmount()!=null){
						deposit = activityCustom.getDeposit();
						deductAmount = activityCustom.getDeductAmount();
						arrivalPrice = salePrice.subtract(deductAmount).add(deposit);
					}
					if(activityCustom.getMaxSalePrice().compareTo(salePrice) == 0){
						productMap.put("hasDifferentPrice", "");
					}else{
						productMap.put("hasDifferentPrice", "起");
					}
				}
				if ("2".equals(productCustom.getSaleType())) { //兼容装修页中品牌款商品改单品款时，列表中单品款商品显示活动价
					salePrice = productCustom.getMinSalePrice();
				}
				if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
					svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, Const.PRODUCT_ACTIVITY_TYPE_AREA);
				}
				DecimalFormat df = new DecimalFormat();
				df.applyPattern("#.##");
				productMap.put("productId", productId);
				productMap.put("productName", productName);
				productMap.put("salePrice", df.format(salePrice));
				productMap.put("tagPrice", df.format(tagPrice));
				productMap.put("discount", 0);
				productMap.put("stockSum", stockSum);
				productMap.put("pic", pic);
				productMap.put("svipSalePrice", df.format(svipSalePrice));
				productMap.put("arrivalPrice", df.format(arrivalPrice));
				productMap.put("deposit", df.format(deposit));
				productMap.put("deductAmount", df.format(deductAmount));
				productMap.put("manageSelf", productCustom.getIsManageSelf()); //是否自营
				productBlockList.add(productMap);
			}
		}
		return productBlockList;
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
				paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
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
					BigDecimal arrivalPrice = new BigDecimal("0");
					BigDecimal deposit = new BigDecimal("0");
					BigDecimal deductAmount = new BigDecimal("0");
					if(activityMap.containsKey(productId.toString())){
						//活动的展示活动价格
						ActivityCustom activityCustom = activityMap.get(productId.toString());
						salePrice = new BigDecimal(activityCustom.getSalePrice());
						tagPrice = new BigDecimal(activityCustom.getTagPrice());
						if(activityCustom.getActivityEndTime().after(new Date()) && activityCustom.getDeposit()!=null && activityCustom.getDeductAmount()!=null){
							deposit = activityCustom.getDeposit();
							deductAmount = activityCustom.getDeductAmount();
							arrivalPrice = salePrice.subtract(deductAmount).add(deposit);
						}
						if(activityCustom.getMaxSalePrice().compareTo(salePrice) == 0){
							productMap.put("hasDifferentPrice", "");
						}else{
							productMap.put("hasDifferentPrice", "起");
						}
					}
					if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
						svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, Const.PRODUCT_ACTIVITY_TYPE_AREA);
					}
					DecimalFormat df = new DecimalFormat();
					df.applyPattern("#.##");
					productMap.put("productId", productId);
					productMap.put("productName", productName);
					productMap.put("salePrice", df.format(salePrice));
					productMap.put("tagPrice", df.format(tagPrice));
					productMap.put("discount", 0);
					productMap.put("stockSum", stockSum);
					productMap.put("pic", pic);
					productMap.put("svipSalePrice", df.format(svipSalePrice));
					productMap.put("arrivalPrice", df.format(arrivalPrice));
					productMap.put("deposit", df.format(deposit));
					productMap.put("deductAmount", df.format(deductAmount));
					productList.add(productMap);
				}
			}
		}
		return productList;
	}
	
	private String dealWithDayStr(String dayStr){
		if(dayStr.equals("01")){
			dayStr = "1";
		}else if(dayStr.equals("02")){
			dayStr = "2";
		}else if(dayStr.equals("03")){
			dayStr = "3";
		}else if(dayStr.equals("04")){
			dayStr = "4";
		}else if(dayStr.equals("05")){
			dayStr = "5";
		}else if(dayStr.equals("06")){
			dayStr = "6";
		}else if(dayStr.equals("07")){
			dayStr = "7";
		}else if(dayStr.equals("08")){
			dayStr = "8";
		}else if(dayStr.equals("09")){
			dayStr = "9";
		}
		return dayStr+"日";
	}
}

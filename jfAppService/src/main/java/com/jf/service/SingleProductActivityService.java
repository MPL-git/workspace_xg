package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SingleProductActivityCustomMapper;
import com.jf.dao.SingleProductActivityMapper;
import com.jf.entity.IntegralType;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ProductType;
import com.jf.entity.SeckillBrandGroup;
import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityExample;
import com.jf.entity.SysParamCfg;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月29日 下午2:58:10 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SingleProductActivityService extends BaseService<SingleProductActivity, SingleProductActivityExample> {
	@Autowired
	private SingleProductActivityMapper singleProductActivityMapper;
	@Autowired
	private SingleProductActivityCustomMapper singleProductActivityCustomMapper;
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private SingleProductActivityCnfService singleProductActivityCnfService;
	@Autowired
	private IntegralTypeService integralTypeService;
	@Autowired
	private SeckillBrandGroupService seckillBrandGroupService;
	@Autowired
	private SeckillTimeService seckillTimeService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	public void setSingleProductActivityMapper(SingleProductActivityMapper singleProductActivityMapper) {
		this.setDao(singleProductActivityMapper);
		this.singleProductActivityMapper = singleProductActivityMapper;
	}
	
	/**获取秒杀tab*/
	public List<Map<String, Object>> getNewSeckillTimeTab() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Date date = new Date();
		String bTime = "";//开始时间
		Integer totalContinueMin = 0;//获取总时长（分钟）
		SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
		seckillTimeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andSeckillTypeEqualTo("1");
		seckillTimeExample.setOrderByClause("start_hours desc,start_min desc");
		List<SeckillTime> seckillTimes = seckillTimeService.selectByExample(seckillTimeExample);
		if(CollectionUtils.isNotEmpty(seckillTimes)){
			SeckillTime seckillTime = null;
			for (SeckillTime skill : seckillTimes) {
				String startHours = skill.getStartHours();
				String startMin= skill.getStartMin();
				Integer continueHours = Integer.valueOf(skill.getContinueHours());
				Integer continueMin = Integer.valueOf(skill.getContinueMin());
				bTime = startHours+":"+startMin;
				totalContinueMin = continueHours*60+continueMin;
				String begin = DateUtil.getFormatDate(date, "yyyy-MM-dd")+" "+bTime+":00";
				Date beginDate = DateUtil.getDate(begin);
				Date endDate = DateUtil.addMinute(beginDate, totalContinueMin);
				if(beginDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0){
					seckillTime = skill;
					break;
				}
			}
			if(seckillTime == null){
				seckillTime = seckillTimes.get(0);
			}
			String startHours = seckillTime.getStartHours();
			String startMin= seckillTime.getStartMin();
			Integer continueHours = Integer.valueOf(seckillTime.getContinueHours());
			Integer continueMin = Integer.valueOf(seckillTime.getContinueMin());
			bTime = startHours+":"+startMin;
			totalContinueMin = continueHours*60+continueMin;//
			for (int i = 0; i < 3; i++) {
				String beginTimeStr = "";
				boolean selected = false;
				map = new HashMap<String,Object>();
				String b = DateUtil.getFormatDate(date, "yyyy-MM-dd")+" "+bTime+":00";
				Date beginDate = DateUtil.getDate(b);//获取开始时间
				Date endDate = null;
				String type = "";
				String content = "";
				if(i == 0){
					type = "1";
					beginDate = DateUtil.addDay(beginDate, -1);
					endDate = DateUtil.addMinute(beginDate, totalContinueMin);
					beginTimeStr = "持续疯抢";
					content = "即将结束";
					if(endDate.compareTo(date) < 0){
						type = "5";
						beginTimeStr = "抢购结束";
						content = "抢购结束";
					}
				}else if(i == 1){
					selected = true;
					endDate = DateUtil.addMinute(beginDate, totalContinueMin);
					if(beginDate.compareTo(date) > 0){
						type = "2";
						beginTimeStr = "即将开始";
						content = bTime+"即将开抢";
					}else if(beginDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0){
						type = "3";
						beginTimeStr = "正在疯抢";
						content = bTime+"正在疯抢";
					}else{
						type = "5";
						beginTimeStr = "抢购结束";
						content = "抢购结束";
					}
				}else{
					type = "4";
					beginDate = DateUtil.addDay(beginDate, 1);
					endDate = DateUtil.addMinute(beginDate, totalContinueMin);
					beginTimeStr = "明日预告";
					content = "明日"+bTime+"即将开抢";
				}
				map.put("beginTime", beginDate.getTime());
				map.put("endTime", endDate.getTime());
				map.put("currentTime", date.getTime());
				map.put("beginTimeStr", beginTimeStr);
				map.put("selected", selected);
				map.put("type", type);
				map.put("content", content);
				list.add(map);
			}
		}
		return list;
	}
	
//	/**获取秒杀tab(废弃，2018-07-17，需求676)*/
//	public List<Map<String, Object>> getSeckillTimeTab() {
//		long current=System.currentTimeMillis();//当前时间毫秒数
//        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
//        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
//		Map<String,Object> map = new HashMap<String,Object>();
//		Map<Object,Object> selectedMap = new HashMap<Object,Object>();
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		//当天，当前时间 >= 开始时间的 集合
//		List<Long> dateList = new ArrayList<Long>();
//		dateList.add(zero);
//		Date date = new Date();
//		String dayeYearStr = DateUtil.getFormatDate(date, "yyyyMMdd");
//		String beginTimeStr = "";
//		String beginTimeYearStr = "";
//		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSeckillTimeTab();
//		map.put("beginTimeStr", "精选好货,低价狂欢");
//		map.put("selected", false);
//		map.put("beginTime", null);
//		map.put("endTime", null);
//		map.put("currentTime", null);
//		list.add(map);
//		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
//			for (SingleProductActivityCustom singleProductActivity : singleProductActivitys) {
//				boolean selected = false;
//				map = new HashMap<String,Object>();
//				Date beginTime = singleProductActivity.getBeginTime();
//				Date endTime = singleProductActivity.getEndTime();
//				beginTimeYearStr = DateUtil.getFormatDate(beginTime, "yyyyMMdd");
//				beginTimeStr = DateUtil.getFormatDate(beginTime, "HH:mm");
//				
//				//当天
//				if(dayeYearStr.equals(beginTimeYearStr)){
//					dateList.add(beginTime.getTime());
//					if(date.getTime() >= beginTime.getTime()){
//						selected = true;
//						if (CollectionUtil.isNotEmpty(list)) {
//							for (Map<String,Object> Maps : list) {
//								if((boolean) Maps.get("selected")){
//									Maps.put("selected", false);
//								}
//							}
//						}
//						selectedMap.put("choose", true);
//					}
//				}
//				map.put("beginTime", beginTime.getTime());
//				map.put("endTime", endTime.getTime());
//				map.put("currentTime", date.getTime());
//				map.put("beginTimeStr", beginTimeStr);
//				map.put("selected", selected);
//				list.add(map);
//			}
//		}
//		dateList.add(twelve);
//		map = new HashMap<String,Object>();
//		map.put("beginTimeStr", "持续上新,敬请期待");
//		map.put("selected", false);
//		map.put("beginTime", null);
//		map.put("endTime", null);
//		map.put("currentTime", null);
//		list.add(map);
//		int i = 0;
//		if(!selectedMap.containsKey("choose")){
//			for (Map<String,Object> dataMap : list) {
//				if(dataMap.get("beginTime") != null){
//					long l = (long) dataMap.get("beginTime");
//					beginTimeYearStr = DateUtil.getFormatDate(new Date(l), "yyyyMMdd");
//					if(dayeYearStr.equals(beginTimeYearStr)){
//						if(date.getTime() >= dateList.get(i) && date.getTime() < dateList.get(i+1)){
//							dataMap.put("selected", true);
//							break;
//						}
//						i++;
//					}
//				}
//			}
//		}
//		return list;
//	}

	public List<Map<String, Object>> getSeckillTimeList(Map<String, Object> params) {
		Date date = new Date();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSeckillTimeList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = getProductInfoMap(singleProductActivityCustom, date);
				list.add(map);
			}
		}
		return list;
	}
	
	
	public List<Map<String, Object>> getNewUserSeckillTimeList(Map<String, Object> params) {
		Date currentDate = new Date();
		params.put("currentDate", currentDate);
		List<SysParamCfg> sysParamCfgList = DataDicUtil.getSysParamCfg("COLLEGE_STUDENT_CERTIFICATION_PRODUCT_ID");
		List<String> productIdList = new ArrayList<String>();
		for(SysParamCfg sysParamCfg : sysParamCfgList) {
			if(!StringUtil.isEmpty(sysParamCfg.getParamValue())) {
				productIdList.add(sysParamCfg.getParamValue());
			}
		}
		params.put("productIdList", productIdList);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getNewUserSeckillTimeList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = new HashMap<String,Object>();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				BigDecimal platformPreferential = singleProductActivityCustom.getPlatformPreferential();
				Date beginTime = singleProductActivityCustom.getBeginTime();
				if(beginTime.after(currentDate) && stockSum < 1){
					stockSum = 1;
				}
				if(platformPreferential != null){
					salePrice = salePrice.subtract(platformPreferential);
				}
				map.put("productId", singleProductActivityCustom.getProductId());
				map.put("productPic", StringUtil.getPic(singleProductActivityCustom.getProductPic(), "M"));
				map.put("productName", singleProductActivityCustom.getProductName());
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", singleProductActivityCustom.getType());
				map.put("stock", stockSum);
				map.put("discount", tagPrice.doubleValue()==0?1:singleProductActivityCustom.getSalePrice().divide(salePrice,2, BigDecimal.ROUND_HALF_UP));
				map.put("beginTime", singleProductActivityCustom.getBeginTime().getTime()/1000);
				map.put("endTime", singleProductActivityCustom.getEndTime().getTime()/1000);
				map.put("currentTime", currentDate.getTime()/1000);
				map.put("manageSelf", singleProductActivityCustom.getIsManageSelf()); //是否自营
				list.add(map);
			}
		}
		return list;
	}
	
	
	public List<Map<String, Object>> getSingleExplosionActivityList(Map<String, Object> params, Integer version, String system) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSingleExplosionActivityList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = new HashMap<String,Object>();
				Integer productId = singleProductActivityCustom.getProductId();
				String productPic = singleProductActivityCustom.getProductPic();
				String productName = singleProductActivityCustom.getProductName();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				BigDecimal discount = singleProductActivityCustom.getDiscount().setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
				if (version != null && !StringUtil.isBlank(system) 
						&& (version == 19 || version == 20 || version == 21)
						&& system.equals(Const.IOS)) {
					discount = discount.multiply(new BigDecimal(10)).stripTrailingZeros();
				}
				map.put("productId", productId);
				map.put("productPic", StringUtil.getPic(productPic, "M"));
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", params.get("type"));
				map.put("discount", discount);
				map.put("stockSum", stockSum);
				list.add(map);
			}
		}
		return list;
	}
	
	public List<SingleProductActivityCustom> getSingleActivityScreen(String type) {
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSingleActivityScreen(type);
		return singleProductActivitys;
	}

	public List<Map<String, Object>> getSingleNewEnjoyActivityList(Map<String, Object> params) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSingleNewEnjoyActivityList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			long currentMs = System.currentTimeMillis();
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = new HashMap<String,Object>();
				Integer productId = singleProductActivityCustom.getProductId();
				String productPic = singleProductActivityCustom.getProductPic();
				String productName = singleProductActivityCustom.getProductName();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				BigDecimal discount = singleProductActivityCustom.getDiscount().setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
				map.put("productId", productId);
				map.put("productPic", StringUtil.getPic(productPic, "M"));
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", "5");
				map.put("discount", discount);
				map.put("stockSum", stockSum);
				map.put("expiredMs", singleProductActivityCustom.getEndTime().getTime() - currentMs);
				map.put("manageSelf", singleProductActivityCustom.getIsManageSelf()); //是否自营
				list.add(map);
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> getSingleIntegralMallActivityList(Map<String, Object> params, Integer integral, String memberId) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSingleNewEnjoyActivityList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = new HashMap<String,Object>();
				Integer productId = singleProductActivityCustom.getProductId();
				String productPic = singleProductActivityCustom.getProductPic();
				String productName = singleProductActivityCustom.getProductName();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				String deductibleAmountContext = "";
				Integer deductibleIntegral = 0;//可抵扣积分
				double deductibleAmount = 0.00;//可抵扣金额
				BigDecimal deductibleSalePrice = new BigDecimal("0");//抵后价
				if(StringUtil.isBlank(memberId)){
					deductibleAmountContext = "请登入查看可抵扣积分额度";
					deductibleSalePrice = new BigDecimal("0.01");
				}else{
					//积分金额换算比率
					QueryObject integralTypeQuery = new QueryObject();
					integralTypeQuery.addQuery("id", Integer.valueOf(Const.INTEGRAL_TYPE_ORDER_PAYMENT));
					integralTypeQuery.addQuery("status", "1");
					List<IntegralType> integralTypes = integralTypeService.findListQuery(integralTypeQuery);
					Integer iCharge = 0;
					if(CollectionUtil.isNotEmpty(integralTypes)){
						IntegralType integralType = integralTypes.get(0);
						if(integralType.getIntType().equals("2")){
							iCharge = integralType.getiCharge() == null ? 0 : integralType.getiCharge();
						}
					}
					//新用户秒杀 限购配置查询
					QueryObject activityCnfQuery = new QueryObject();
					activityCnfQuery.addQuery("activityType", "5");
					List<SingleProductActivityCnf> singleProductActivityCnfs = singleProductActivityCnfService.findListQuery(activityCnfQuery);
					BigDecimal integralExchangeRate = null;//积分兑换比率
					if(CollectionUtils.isNotEmpty(singleProductActivityCnfs)){
						SingleProductActivityCnf singleProductActivityCnf = singleProductActivityCnfs.get(0);
						integralExchangeRate = singleProductActivityCnf.getIntegralExchangeRate();
					}else{
						integralExchangeRate = new BigDecimal(OrderService.INTEGERAL_DISOUNT);
					}
					if(iCharge != 0){
						//计算可以抵扣多少积分  销售价*积分兑换比率
						deductibleIntegral = salePrice.multiply(new BigDecimal(iCharge)).intValue();
						//如果 可抵扣积分 >= 用户本身拥有的积分，就使用所有的积分
						//可使用的积分 = 可用积分 * 最高积分兑换比例
						Integer exchangeIntegral = (int) (deductibleIntegral * integralExchangeRate.doubleValue());
						if(exchangeIntegral > integral){
							exchangeIntegral = integral;
						}
						//可抵扣金额 = 最高积分兑换比例 / 积分兑换比率
						//换算成金额
						deductibleAmount = exchangeIntegral / (double)iCharge;
						if(salePrice.doubleValue() <= deductibleAmount){
							deductibleSalePrice = new BigDecimal("0.01");
							deductibleAmountContext = "您的积分可抵扣"+(new BigDecimal(deductibleAmount).subtract(deductibleSalePrice).setScale(2, BigDecimal.ROUND_HALF_UP))+"元";
						}else{
							deductibleSalePrice = salePrice.subtract(new BigDecimal(deductibleAmount)).setScale(2, BigDecimal.ROUND_HALF_UP);
							if(deductibleAmount < 0){
								deductibleAmountContext = "";
							}else{
								deductibleAmountContext = "您的积分可抵扣"+deductibleAmount+"元";
							}
						}
					}
				}
				map.put("productId", productId);
				map.put("productPic", StringUtil.getPic(productPic, "M"));
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("type", "5");
				map.put("stockSum", stockSum);
				map.put("deductibleSalePrice", deductibleSalePrice);
				map.put("deductibleAmountContext", deductibleAmountContext);
				list.add(map);
			}
		}
		return list;
	}

	public SingleProductActivity findModelByProductId(Integer productId) {
		Date date = new Date();
		SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
		singleProductActivityExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId)
		.andIsCloseEqualTo("0").andAuditStatusEqualTo("3").andEndTimeGreaterThan(date);
		singleProductActivityExample.setOrderByClause("create_date desc");
		List<SingleProductActivity> singleProductActivities = singleProductActivityMapper.selectByExample(singleProductActivityExample);
		if(CollectionUtil.isNotEmpty(singleProductActivities)){
			return singleProductActivities.get(0);
		}
		return null;
	}

	public List<SingleProductActivityCustom> getSingleScreeningConditions6(Map<String, Object> paramsMap) {
		
		return singleProductActivityCustomMapper.getSingleScreeningConditions6(paramsMap);
	}

	public List<SingleProductActivityCustom> getTwoCategoryProductSize(Map<String, Object> paramsMap) {
		
		return singleProductActivityCustomMapper.getTwoCategoryProductSize(paramsMap);
	}

	public List<Map<String, Object>> getSingleBrokenCodeClearingActivityList(Map<String, Object> paramsMap) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSingleBrokenCodeClearingActivityList(paramsMap);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = new HashMap<String,Object>();
				Integer productId = singleProductActivityCustom.getProductId();
				String productPic = singleProductActivityCustom.getProductPic();
				String productName = singleProductActivityCustom.getProductName();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				BigDecimal svipDiscount = singleProductActivityCustom.getSvipDiscount();
				BigDecimal svipSalePrice = productService.getProductSvipSalePrice(salePrice,svipDiscount,Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING);
				map.put("productId", productId);
				map.put("productPic", StringUtil.getPic(productPic, "M"));
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", paramsMap.get("type"));
				map.put("discount", 0);
				map.put("stockSum", 1);
				map.put("svipSalePrice", svipSalePrice);
				map.put("manageSelf", singleProductActivityCustom.getIsManageSelf()); //是否自营
				list.add(map);
			}
		}
		return list;
	}

	public List<Map<String, Object>> getSingleNewExplosionActivityList(Map<String, Object> params) {
		Date now = new Date();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSingleNewExplosionActivityList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = new HashMap<String,Object>();
				Date beginTime = singleProductActivityCustom.getBeginTime();
				Date endTime = singleProductActivityCustom.getEndTime();
				Integer productId = singleProductActivityCustom.getProductId();
				String productPic = singleProductActivityCustom.getProductPic();
				String productName = singleProductActivityCustom.getProductName();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				BigDecimal discount = singleProductActivityCustom.getDiscount().setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
				BigDecimal svipDiscount = singleProductActivityCustom.getSvipDiscount();
				BigDecimal svipSalePrice = productService.getProductSvipSalePrice(salePrice,svipDiscount,Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION);
				map.put("productId", productId);
//				if("1".equals(singleProductActivityCustom.getIsWatermark())){
//					map.put("productPic", StringUtil.getActivityMkPic(productPic, "M_WM", null));
//				}else{
					map.put("productPic", StringUtil.getPic(productPic, "M"));
//				}
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", params.get("type"));
				map.put("discount", discount);
				map.put("stockSum", stockSum);
				map.put("currentTime", now.getTime());
				map.put("beginTime", beginTime.getTime());
				map.put("endTime", endTime.getTime());
				map.put("svipSalePrice", svipSalePrice);
				list.add(map);
			}
		}
		return list;
	}

	
	public List<Map<String, Object>> getNewSeckillTimeList(JSONObject reqDataJson) {
		//要修改品牌团间隔数据：需改一下数据
		//1：brandProductNum = 5
		//2：pageSize 是 brandProductNum的整数倍
		//3：返回给前端的的页数为 returnSize = pageSize/brandProductNum
		Date date = new Date();
		long beginTime = reqDataJson.getLong("beginTime");
		Integer pageSize = 12;//页数
		//品牌团
		int brandProductNum = 6;
		int returnSize = pageSize/brandProductNum;
		Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("beginTime", beginTime/1000);
		params.put("seckillType", "1");//1限时秒杀\r\n2会场秒杀
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getNewSeckillTimeList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			List<SeckillBrandGroup> seckillBrandGroups = new ArrayList<SeckillBrandGroup>();
			Integer size = singleProductActivitys.size();
			Integer limitStart = currentPage*2;
			Integer limitSize = 2;
			if(limitSize > 0){
				Map<String,Object> brandGroupParams = new HashMap<String,Object>();
				brandGroupParams.put("beginTime", beginTime/1000);
				brandGroupParams.put("seckillType", "1");//1限时秒杀\r\n2会场秒杀
				brandGroupParams.put("currentPage", limitStart);
				brandGroupParams.put("pageSize", limitSize);
				brandGroupParams.put("havingBy", 3);
				seckillBrandGroups = seckillBrandGroupService.getSeckillBrandGroup(brandGroupParams);
			}
			for (int i = 0; i < returnSize ; i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
				int j = 0;//用来判断秒杀的商品是否有3个
				for (int k = i * brandProductNum; k < brandProductNum*(i+1); k++) {
					if(size > k){
						j = k + 1;
						SingleProductActivityCustom singleProductActivityCustom = singleProductActivitys.get(k);
						Map<String,Object> productMap = getProductInfoMap(singleProductActivityCustom,date);
						productInfoList.add(productMap);
					}
				}
				List<Map<String,Object>> brandGroupList = new ArrayList<Map<String,Object>>();
				if(j % brandProductNum == 0){
					//没有余数,表示是有3个秒杀商品，每3个秒杀商品，中间隔着一个品牌团
					if(CollectionUtils.isNotEmpty(seckillBrandGroups)){
						//查找到对应的品牌团
						if(i < seckillBrandGroups.size()){
							SeckillBrandGroup seckillBrandGroup = seckillBrandGroups.get(i);
							String entryPic = StringUtil.getPic(seckillBrandGroup.getEntryPic(),"");
							params.clear();
							params.put("brandGroupId", seckillBrandGroup.getId());
							params.put("seckillType", "1");//1限时秒杀\r\n2会场秒杀
							List<SingleProductActivityCustom> SingleProductActivityCustoms = singleProductActivityCustomMapper.getSeckillBrandGroupList(params);
							if(CollectionUtils.isNotEmpty(SingleProductActivityCustoms) && SingleProductActivityCustoms.size() >= 3){
								Map<String,Object> brandGroupMap = new HashMap<String,Object>();
								List<Map<String,Object>> brandGroupProductInfoList = new ArrayList<Map<String,Object>>();
								brandGroupMap.put("entryPic", entryPic);
								brandGroupMap.put("brandGroupId", seckillBrandGroup.getId());
								for (SingleProductActivityCustom singleProductActivityCustom : SingleProductActivityCustoms) {
									Map<String,Object> brandGroupProductInfoMap = new HashMap<String,Object>();
									String productName = singleProductActivityCustom.getProductName();
									ProductItemExample productItemExample = new ProductItemExample();
									productItemExample.createCriteria().andProductIdEqualTo(singleProductActivityCustom.getProductId())
									.andDelFlagEqualTo("0");
									productItemExample.setOrderByClause("sale_price,tag_price desc");
									productItemExample.setLimitStart(0);
									productItemExample.setLimitSize(1);
									List<ProductItem> productItems = productItemService.selectByExample(productItemExample);
									if(CollectionUtils.isNotEmpty(productItems)){
										ProductItem productItem = productItems.get(0);
										brandGroupProductInfoMap.put("salePrice", productItem.getSalePrice());
										brandGroupProductInfoMap.put("tagPrice", productItem.getTagPrice());
										brandGroupProductInfoMap.put("productName", productName);
										brandGroupProductInfoMap.put("productPic", StringUtil.getPic(singleProductActivityCustom.getProductPic(), "M"));
										brandGroupProductInfoList.add(brandGroupProductInfoMap);
									}
								}
								brandGroupMap.put("brandGroupProductInfoList", brandGroupProductInfoList);
								brandGroupList.add(brandGroupMap);
							}
						}
					}
				}
				if(CollectionUtils.isNotEmpty(productInfoList)){
					map.put("productInfoList", productInfoList);
					map.put("brandGroupList", brandGroupList);
					list.add(map);
				}
			}
		}
		return list;
	}
	
	
	public Map<String, Object> getBrandGroupProductList(JSONObject reqDataJson) {
		Date date = new Date();
		Integer brandGroupId = reqDataJson.getInt("brandGroupId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = Const.RETURN_SIZE_10;
		List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("brandGroupId", brandGroupId);
		params.put("seckillType", "1");//1限时秒杀\r\n2会场秒杀
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<SingleProductActivityCustom> singleProductActivityCustoms = singleProductActivityCustomMapper.getSeckillBrandGroupList(params);
		if(CollectionUtils.isNotEmpty(singleProductActivityCustoms)){
			SingleProductActivityCustom custom = singleProductActivityCustoms.get(0);
			String posterPic = StringUtil.getPic(custom.getPosterPic(),"");
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivityCustoms) {
				Map<String, Object> productInfoMap = getProductInfoMap(singleProductActivityCustom,date);
				productInfoList.add(productInfoMap);
			}
			map.put("brandGroupName", custom.getBrandGroupName());
			map.put("posterPic", posterPic);
			map.put("currentTime", date);
			map.put("beginTime", custom.getBeginTime().getTime());
			map.put("endTime", custom.getEndTime().getTime());
		}
		map.put("productInfoList", productInfoList);
		return map;
	}

	private Map<String, Object> getProductInfoMap(SingleProductActivityCustom singleProductActivityCustom, Date date) {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer productId = singleProductActivityCustom.getProductId();
		String productPic = singleProductActivityCustom.getProductPic();
		String productName = singleProductActivityCustom.getProductName();
		String type = singleProductActivityCustom.getType();
		Integer unrealityNum = singleProductActivityCustom.getUnrealityNum();
		BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
		BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
		Date endDate = singleProductActivityCustom.getEndTime();
		Date beginDate = singleProductActivityCustom.getBeginTime();
		long beginTime = beginDate.getTime()/1000;
		Integer payQuantity = singleProductActivityCustom.getProductSaleQuantity() == null ? 0 : singleProductActivityCustom.getProductSaleQuantity();
		Integer unpaidQuantity = singleProductActivityCustom.getLockedAmount();
		Integer stockSum = singleProductActivityCustom.getStock()-singleProductActivityCustom.getLockedAmount();
		//剩余库存数e
		int e = stockSum;
		//已售数
		int a = payQuantity;
		//原库存 已售数a+剩余库存数e
		int b = a + e;
		//虚库存c=就是这新增的“虚拟数”
		int c = unrealityNum;
		long t = date.getTime()/1000-beginTime;
		if(t < 0){
			t = 0;
		}
		long T = 1 *60 *60;
		int d = (int) (c * t / T);
		if(d > c){
			d = c;
		}
		//百分比
		Double salesRatio = 1.00;
		if(b+c+unpaidQuantity > 0){
			salesRatio = (a+d+unpaidQuantity)/(double)(b+c+unpaidQuantity);
		}
		//剩余件数=剩余库存数e + 虚拟库存c - 虚已售d
		Integer surplusStock = e + c - d;
		//已抢数量
		String alreadyRobQuantity = "";
		//限购数量
		String limitButQuantity = "";
		if(date.compareTo(beginDate) >= 0 && date.compareTo(endDate) <= 0){
			alreadyRobQuantity = "已抢"+payQuantity+"件";
		}else if(date.compareTo(beginDate) < 0){
			//未开始的 
			limitButQuantity = "限量"+(c+b)+"件 | ";
			if(DateUtil.getFormatDate(date, "yyyyMMdd").equals(DateUtil.getFormatDate(beginDate, "yyyyMMdd"))){
				limitButQuantity += "今日"+DateUtil.getFormatDate(beginDate, "HH:mm")+"开抢";
			}else{
				limitButQuantity += "明日"+DateUtil.getFormatDate(beginDate, "HH:mm")+"开抢";
			}
		}
		map.put("productId", productId);
		map.put("productPic", StringUtil.getPic(productPic, "M"));
		map.put("productName", productName);
		// #：位置上无数字不显示
		DecimalFormat df = new DecimalFormat();
        df.applyPattern("#.##");
        String salePriceStr = df.format(salePrice);
        String tagPriceStr = df.format(tagPrice);
		map.put("salePrice", salePriceStr);
		map.put("tagPrice", tagPriceStr);
		map.put("type", type);
		map.put("salesRatio", salesRatio);
		map.put("unpaidQuantity", unpaidQuantity);
		map.put("stock", surplusStock);
		map.put("alreadyRobQuantity", alreadyRobQuantity);
		map.put("limitButQuantity", limitButQuantity);
		map.put("manageSelf", singleProductActivityCustom.getIsManageSelf()); //是否自营
		return map;
	}

	/**
	 * 
	 * @Title selectSeckillTimeTab   
	 * @Description TODO(第三版限时秒杀 STORY#1079)   
	 * @author pengl
	 * @date 2018年11月21日 上午9:15:19
	 */
	public List<Map<String, Object>> selectSeckillTimeTab() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> todayList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tomorrowList = new ArrayList<Map<String, Object>>();
		Date date = new Date();
		SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
		seckillTimeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andSeckillTypeEqualTo("1");
		seckillTimeExample.setOrderByClause(" start_hours asc, start_min asc");
		List<SeckillTime> seckillTimes = seckillTimeService.selectByExample(seckillTimeExample);
		for(SeckillTime seckillTime : seckillTimes) {
			Map<String, Object> map = new HashMap<String,Object>();
			String content = "";//文本
			String bTime = "";//开始时间
			Integer totalContinueMin = 0;//获取总时长（分钟）
			String beginTimeStr = "";//秒杀时间文本
			String type = "";//类型
			boolean selected = false;//选中标识
			String startHours = seckillTime.getStartHours();
			String startMin= seckillTime.getStartMin();
			Integer continueHours = Integer.valueOf(seckillTime.getContinueHours());
			Integer continueMin = Integer.valueOf(seckillTime.getContinueMin());
			totalContinueMin = continueHours*60+continueMin;
			bTime = startHours+":"+startMin;
			Date beginDate = DateUtil.getDate(DateUtil.getFormatDate(date, "yyyy-MM-dd")+" "+bTime+":00");//获取开始时间
			Date endDate = DateUtil.addMinute(beginDate, totalContinueMin);//获取结束时间
			boolean tomorrowStatus = false;//判断秒杀是否为明天开始
			if(DateUtil.addDay(beginDate, -1).compareTo(date) <= 0 && DateUtil.addMinute(DateUtil.addDay(beginDate, -1), totalContinueMin).compareTo(date) > 0){
				//判断昨天的是否已结束
				beginDate = DateUtil.addDay(beginDate, -1);
				endDate = DateUtil.addMinute(beginDate, totalContinueMin);
			}
			if(beginDate.compareTo(date) > 0) { //今日即将开抢
				type = "2";
				content = "今日"+bTime+"即将开抢";
				beginTimeStr = "即将开始";
			}else if(beginDate.compareTo(date) <= 0 && endDate.compareTo(date) > 0) { //今日抢购中
				type = "3";
				content = "今日"+bTime+"正在疯抢";
				beginTimeStr = "抢购中";
				selected = true;
			}else { //明日即将开抢
				type = "2";
				content = "明日"+bTime+"即将开抢";
				beginTimeStr = "即将开始";
				beginDate = DateUtil.addDay(beginDate, 1);
				endDate = DateUtil.addMinute(beginDate, totalContinueMin);
				tomorrowStatus = true;
			}
			map.put("beginTime", beginDate.getTime());
			map.put("endTime", endDate.getTime());
			map.put("currentTime", date.getTime());
			map.put("startHoursMin", bTime);
			map.put("beginTimeStr", beginTimeStr);
			map.put("selected", selected);
			map.put("type", type);
			map.put("content", content);
			if(tomorrowStatus) {
				tomorrowList.add(map);
			}else {
				todayList.add(map);
			}
		}
		if(todayList.size() >= Const.TIME_INTERVAL) {
			for (int i = 0; i < Const.TIME_INTERVAL; i++) {
				list.add(todayList.get(i));
			}
		}else if(todayList.size() + tomorrowList.size() >= Const.TIME_INTERVAL) {
			for(Map<String, Object> map : todayList) {
				list.add(map);
			}
			for(Map<String, Object> map : tomorrowList) {
				list.add(map);
				if(list.size() == Const.TIME_INTERVAL) {
					break;
				}
			}
		}else {
			for(Map<String, Object> map : todayList) {
				list.add(map);
			}
			int day = 0;
			for(;;) {
				day++;
				for(SeckillTime seckillTime : seckillTimes) {
					Map<String, Object> map = new HashMap<String,Object>();
					String content = "";//文本
					String bTime = "";//开始时间
					Integer totalContinueMin = 0;//获取总时长（分钟）
					String beginTimeStr = "";//秒杀时间文本
					String type = "";//类型
					boolean selected = false;//选中标识
					String startHours = seckillTime.getStartHours();
					String startMin= seckillTime.getStartMin();
					Integer continueHours = Integer.valueOf(seckillTime.getContinueHours());
					Integer continueMin = Integer.valueOf(seckillTime.getContinueMin());
					totalContinueMin = continueHours*60+continueMin;
					bTime = startHours+":"+startMin;
					Date beginDate = DateUtil.getDate(DateUtil.getFormatDate(date, "yyyy-MM-dd")+" "+bTime+":00");//获取开始时间
					Date endDate = DateUtil.addMinute(beginDate, totalContinueMin);//获取结束时间
					beginDate = DateUtil.addDay(beginDate, day);
					endDate = DateUtil.addMinute(beginDate, totalContinueMin);
					if(day == 1) { //明天
						content = "明日"+bTime+"即将开抢";
					}else { //明天以后显示日期
						content = DateUtil.getFormatDate(beginDate, "MM月dd日")+bTime+"即将开抢";
					}
					type = "2";
					beginTimeStr = "即将开始";
					map.put("beginTime", beginDate.getTime());
					map.put("endTime", endDate.getTime());
					map.put("currentTime", date.getTime());
					map.put("startHoursMin", bTime);
					map.put("beginTimeStr", beginTimeStr);
					map.put("selected", selected);
					map.put("type", type);
					map.put("content", content);
					list.add(map);
					if(list.size() == Const.TIME_INTERVAL) {
						day = Const.TIME_INTERVAL;
						break;
					}
				}
				if(day == Const.TIME_INTERVAL) {
					break;
				}
			}
		}
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return (int)(Long.parseLong(o1.get("beginTime").toString())-Long.parseLong(o2.get("beginTime").toString()));
				
			}
		});
		// 选中抢购中最新开始的时间段
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if((boolean)map.get("selected")) {
				index = i;
			}
			map.put("selected", false);
		}
		if(index != -1) {
			list.get(index).put("selected", true);
		}else {
			list.get(0).put("selected", true);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title selectSeckillTimeProdocutTab   
	 * @Description TODO(首页限时秒杀)   
	 * @author pengl
	 * @date 2018年11月29日 上午9:48:45
	 */
	public Map<String, Object> selectSeckillTimeProdocutTab(Integer pageSize, Integer currentPage, String seckillType, int timeInterval) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Map<String,Object>> seckillList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> todayList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> tomorrowList = new ArrayList<Map<String,Object>>();
		Date date = new Date();
		SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
		seckillTimeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andSeckillTypeEqualTo(seckillType);
		seckillTimeExample.setOrderByClause(" start_hours asc, start_min asc");
		List<SeckillTime> seckillTimes = seckillTimeService.selectByExample(seckillTimeExample);
		if(CollectionUtils.isNotEmpty(seckillTimes)) {
			for(SeckillTime seckillTime : seckillTimes) {
				Map<String,Object> map = new HashMap<String,Object>();
				String context = "";//文本
				String bTime = "";//开始时间
				Integer totalContinueMin = 0;//获取总时长（分钟）
				String startHours = seckillTime.getStartHours();
				String startMin= seckillTime.getStartMin();
				Integer continueHours = Integer.valueOf(seckillTime.getContinueHours());
				Integer continueMin = Integer.valueOf(seckillTime.getContinueMin());
				totalContinueMin = continueHours*60+continueMin;
				bTime = startHours+":"+startMin;
				Date beginDate = DateUtil.getDate(DateUtil.getFormatDate(date, "yyyy-MM-dd")+" "+bTime+":00");//获取开始时间
				Date endDate = DateUtil.addMinute(beginDate, totalContinueMin);//获取结束时间
				if(DateUtil.addDay(beginDate, -1).compareTo(date) <= 0 && DateUtil.addMinute(DateUtil.addDay(beginDate, -1), totalContinueMin).compareTo(date) > 0){
					//判断昨天的是否已结束
					beginDate = DateUtil.addDay(beginDate, -1);
					endDate = DateUtil.addMinute(beginDate, totalContinueMin);
				}
				boolean tomorrowStatus = false;//判断秒杀是否为明天开始
				if(beginDate.compareTo(date) > 0) { //今日即将开抢
					context = "即将开始";
				}else if(beginDate.compareTo(date) <= 0 && endDate.compareTo(date) > 0) { //今日抢购中
					context = "抢购中";
				}else { //明日即将开抢
					context = "即将开始";
					beginDate = DateUtil.addDay(beginDate, 1);
					endDate = DateUtil.addMinute(beginDate, totalContinueMin);
					tomorrowStatus = true;
				}
				map.put("beginTime", beginDate.getTime());
				map.put("endTime", endDate.getTime());
				map.put("currentTime", date.getTime());
				map.put("beginTimeStr", bTime);
				map.put("context", context);
				if(tomorrowStatus) {
					tomorrowList.add(map);
				}else {
					todayList.add(map);
				}
			}
			if(todayList.size() >= timeInterval) {
				for (int i = 0; i < timeInterval; i++) {
					seckillList.add(todayList.get(i));
				}
			}else if(todayList.size() + tomorrowList.size() >= timeInterval) {
				for(Map<String,Object> map : todayList) {
					seckillList.add(map);
				}
				for(Map<String, Object> map : tomorrowList) {
					seckillList.add(map);
					if(seckillList.size() == timeInterval) {
						break;
					}
				}
			}else {
				for(Map<String, Object> map : todayList) {
					seckillList.add(map);
				}
				int day = 0;
				for(;;) {
					day++;
					for(SeckillTime seckillTime : seckillTimes) {
						Map<String,Object> map = new HashMap<String,Object>();
						String context = "";//文本
						String bTime = "";//开始时间
						Integer totalContinueMin = 0;//获取总时长（分钟）
						String startHours = seckillTime.getStartHours();
						String startMin= seckillTime.getStartMin();
						Integer continueHours = Integer.valueOf(seckillTime.getContinueHours());
						Integer continueMin = Integer.valueOf(seckillTime.getContinueMin());
						totalContinueMin = continueHours*60+continueMin;
						bTime = startHours+":"+startMin;
						Date beginDate = DateUtil.getDate(DateUtil.getFormatDate(date, "yyyy-MM-dd")+" "+bTime+":00");//获取开始时间
						Date endDate = DateUtil.addMinute(beginDate, totalContinueMin);//获取结束时间
						beginDate = DateUtil.addDay(beginDate, day);
						endDate = DateUtil.addMinute(beginDate, totalContinueMin);
						context = "即将开始";
						map.put("beginTime", beginDate.getTime());
						map.put("endTime", endDate.getTime());
						map.put("currentTime", date.getTime());
						map.put("beginTimeStr", bTime);
						map.put("context", context);
						seckillList.add(map);
						if(seckillList.size() == timeInterval) {
							day = timeInterval;
							break;
						}
					}
					if(day == timeInterval) {
						break;
					}
				}
			}
			Collections.sort(seckillList, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return (int)(Long.parseLong(o1.get("beginTime").toString())-Long.parseLong(o2.get("beginTime").toString()));
					
				}
			});
			//获取进入顶级秒杀页面的默认开始时间
			long beginTime = (long) seckillList.get(0).get("beginTime");
			productInfoList = getTopSecKillProductList(beginTime, currentPage, pageSize, seckillType);
		}
		returnMap.put("seckillList", seckillList);
		returnMap.put("productInfoList", productInfoList);
		return returnMap;
	}
	
	public List<Map<String, Object>> getTopSecKillProductList(long beginTime, Integer currentPage, Integer pageSize, String seckillType) {
		seckillType = "1";//变成只读取 普通限时抢购类型
		Date currentDate = new Date();
		List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("beginTime", beginTime/1000);
		params.put("seckillType", seckillType);//限时抢购子类型 1 普通限时抢购 2 会场限时抢购
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSeckillTimeList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> productMap = getProductInfoMap(singleProductActivityCustom,currentDate);
				productInfoList.add(productMap);
			}
		}
		return productInfoList;
	}

	public List<ProductType> getProductTypeList() {
		return singleProductActivityCustomMapper.getProductTypeList();
	}

	public List<SingleProductActivityCustom> getSingleProductActivityCustomsByProductTypeId(Map<String, Object> params) {
		return singleProductActivityCustomMapper.getSingleProductActivityCustomsByProductTypeId(params);
	}

	public String getPropValIds(Map<String, Object> param) {
		return singleProductActivityCustomMapper.getPropValIds(param);
	}

}

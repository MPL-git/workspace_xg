package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SingleProductActivityCustomMapper;
import com.jf.dao.SingleProductActivityMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderCustom;
import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlCustom;
import com.jf.entity.CutPriceOrderDtlExample;
import com.jf.entity.CutPriceOrderExample;
import com.jf.entity.IntegralType;
import com.jf.entity.MemberInfo;
import com.jf.entity.OrderDtlExample;
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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

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
	private OrderDtlService orderDtlService;
	
	@Autowired
	private ProductItemService productItemService;
	
	@Autowired
	private IntegralTypeService integralTypeService;
	@Autowired
	private SingleProductActivityCnfService singleProductActivityCnfService;
	@Autowired
	private SeckillBrandGroupService seckillBrandGroupService;
	@Autowired
	private CutPriceOrderService cutPriceOrderService;
	@Autowired
	private CutPriceOrderDtlService cutPriceOrderDtlService;
	@Autowired
	private CutPriceCnfService cutPriceCnfService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private BlackListService blackListService;
	@Autowired
	private ProductService productService;
	@Resource
	private CustomAdPageService customAdPageService;
	@Resource
	private SeckillTimeService seckillTimeService;
	@Resource
	private MemberControlService memberControlService;
	@Resource
	private CutPriceOrderLogService cutPriceOrderLogService;
	@Autowired
	private CommonService commonService;
	

	@Autowired
	public void setSingleProductActivityMapper(SingleProductActivityMapper singleProductActivityMapper) {
		this.setDao(singleProductActivityMapper);
		this.singleProductActivityMapper = singleProductActivityMapper;
	}
	
	public List<SingleProductActivityCustom> getSingleCutProductInfo(Map<String, Object> paramsMap) {
		
		return singleProductActivityCustomMapper.getSingleCutProductInfo(paramsMap);
	}
	
	/**获取秒杀tab*/
	public List<Map<String, Object>> getSeckillTimeTab() {
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
		
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return (int)(Long.parseLong(o1.get("beginTime").toString())-Long.parseLong(o2.get("beginTime").toString()));
				
			}
		});
		if (CollectionUtil.isNotEmpty(list)) {
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
		params.put("seckillType", "1");
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSeckillTimeList(params);
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			List<SeckillBrandGroup> seckillBrandGroups = new ArrayList<SeckillBrandGroup>();
			Integer size = singleProductActivitys.size();
			Integer limitStart = currentPage*2;
			Integer limitSize = 2;
			if(limitSize > 0){
				Map<String,Object> brandGroupParams = new HashMap<String,Object>();
				brandGroupParams.put("beginTime", beginTime/1000);
				brandGroupParams.put("seckillType", "1");//限时抢购子类型 1 普通限时抢购 2 会场限时抢购
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
							params.put("seckillType", "1");
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
		params.put("seckillType", "1");//限时抢购子类型 1 普通限时抢购 2 会场限时抢购
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
			map.put("productInfoList", productInfoList);
			map.put("currentTime", date);
			map.put("beginTime", custom.getBeginTime().getTime());
			map.put("endTime", custom.getEndTime().getTime());
		}
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
		OrderDtlExample orderDtlExample = new OrderDtlExample();
		orderDtlExample.createCriteria().andProductIdEqualTo(productId).andDelFlagEqualTo("0");
		Integer payQuantity = orderDtlService.countByExample(orderDtlExample);
		Integer unpaidQuantity = singleProductActivityCustom.getLockedAmount();
		Integer stockSum = singleProductActivityCustom.getStock()-singleProductActivityCustom.getLockedAmount();
		Double salesRatio = 1.00;//百分比
		Integer surplusStock = stockSum;//剩余件数=剩余库存数e + 虚拟库存c - 虚已售d
		String limitButQuantity = "";//限购数量
		String alreadyRobQuantity = "";//已抢数量
		if("1".equals(singleProductActivityCustom.getSeckillType())){
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
			if(b+c+unpaidQuantity > 0){
				salesRatio = (a+d+unpaidQuantity)/(double)(b+c+unpaidQuantity);
			}
			surplusStock = e + c - d;
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
		}
		map.put("productId", productId);
		map.put("productPic", StringUtil.getPic(productPic, "M"));
		map.put("productName", productName);
		map.put("salePrice", salePrice);
		map.put("tagPrice", tagPrice);
		map.put("type", type);
		map.put("salesRatio", salesRatio);
		map.put("unpaidQuantity", unpaidQuantity);
		map.put("stock", surplusStock);
		map.put("alreadyRobQuantity", alreadyRobQuantity);
		map.put("limitButQuantity", limitButQuantity);
		map.put("manageSelf", singleProductActivityCustom.getIsManageSelf()); //是否自营
		return map;
	}
	
	
	public List<Map<String, Object>> getSingleExplosionActivityList(Map<String, Object> params) {
		Date now = new Date();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getSingleExplosionActivityList(params);
		if(CollectionUtils.isNotEmpty(singleProductActivitys)){
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
				String discountStr = "";
				if(discount.compareTo(new BigDecimal(0)) == 0){
					discountStr = "";
				}else if(discount.compareTo(new BigDecimal(1)) == 0){
					discountStr = "";
				}else{
					discountStr = discount.multiply(new BigDecimal(10)).toString();
				}
				map.put("productId", productId);
//				if("1".equals(singleProductActivityCustom.getIsWatermark())){
//					map.put("productPic", StringUtil.getActivityMkPic(productPic,"M_WM",null));
//				}else{
					map.put("productPic", StringUtil.getPic(productPic, "M"));
//				}
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", params.get("type"));
				map.put("discount", discountStr);
				map.put("stockSum", stockSum);
				map.put("currentTime", now.getTime());
				map.put("beginTime", beginTime.getTime());
				map.put("endTime", endTime.getTime());
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
		if(CollectionUtils.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				long currentMs = System.currentTimeMillis();
				Map<String,Object> map = new HashMap<String,Object>();
				Integer productId = singleProductActivityCustom.getProductId();
				String productPic = singleProductActivityCustom.getProductPic();
				String productName = singleProductActivityCustom.getProductName();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				BigDecimal discount = singleProductActivityCustom.getDiscount().setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
				String discountStr = "";
				if(discount.compareTo(new BigDecimal(0)) == 0){
					discountStr = "";
				}else if(discount.compareTo(new BigDecimal(1)) == 0){
					discountStr = "";
				}else{
					discountStr = discount.multiply(new BigDecimal(10)).toString();
				}
				map.put("productId", productId);
				map.put("productPic", StringUtil.getPic(productPic, "M"));
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", "1");
				map.put("discount", discountStr);
				map.put("stockSum", stockSum);
				map.put("expiredMs", singleProductActivityCustom.getEndTime().getTime() - currentMs);
				map.put("manageSelf", singleProductActivityCustom.getIsManageSelf()); //是否自营
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
		if(CollectionUtils.isNotEmpty(singleProductActivities)){
			return singleProductActivities.get(0);
		}
		return null;
	}

	public List<Map<String, Object>> getNewUserSeckillTimeList(Map<String, Object> params) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<SysParamCfg> sysParamCfgList = DataDicUtil.getSysParamCfg("COLLEGE_STUDENT_CERTIFICATION_PRODUCT_ID");
		List<String> productIdList = new ArrayList<String>();
		for(SysParamCfg sysParamCfg : sysParamCfgList) {
			if(!StringUtil.isEmpty(sysParamCfg.getParamValue())) {
				productIdList.add(sysParamCfg.getParamValue());
			}
		}
		params.put("productIdList", productIdList);
		List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityCustomMapper.getNewUserSeckillTimeList(params);
		Date date = new Date();
		long currenTime=date.getTime()/1000;
		if(CollectionUtil.isNotEmpty(singleProductActivitys)){
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
				Map<String,Object> map = new HashMap<String,Object>();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				Date beginTime = singleProductActivityCustom.getBeginTime();
				if(beginTime.after(date) && stockSum < 1){
					stockSum = 1;
				}
				map.put("productId", singleProductActivityCustom.getProductId());
				map.put("productPic", StringUtil.getPic(singleProductActivityCustom.getProductPic(), "M"));
				map.put("productName", singleProductActivityCustom.getProductName());
				map.put("salePrice", singleProductActivityCustom.getSalePrice());
				map.put("tagPrice", singleProductActivityCustom.getTagPrice());
				map.put("type", singleProductActivityCustom.getType());
				map.put("stock", stockSum);
				map.put("discount", singleProductActivityCustom.getTagPrice().doubleValue()==0?1:singleProductActivityCustom.getSalePrice().divide(singleProductActivityCustom.getTagPrice(),2, BigDecimal.ROUND_HALF_UP));
				map.put("beginTime", singleProductActivityCustom.getBeginTime().getTime()/1000);
				map.put("endTime", singleProductActivityCustom.getEndTime().getTime()/1000);
				map.put("currentTime", currenTime);
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
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				map.put("productId", productId);
				map.put("productPic", StringUtil.getPic(productPic, "M"));
				map.put("productName", productName);
				map.put("salePrice", salePrice);
				map.put("tagPrice", tagPrice);
				map.put("type", paramsMap.get("type"));
				map.put("discount", 0);
				map.put("stockSum", 1);
				map.put("manageSelf", singleProductActivityCustom.getIsManageSelf()); //是否自营
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 砍价商品列表
	 * @param reqDataJson 
	 * @param pageSize 
	 * @param memberId 
	 * @return
	 */
	public Map<String, Object> getBargainGoodsList(JSONObject reqDataJson, Integer pageSize, Integer memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		String type = reqDataJson.getString("type");
		String balckType = "";
		if(type.equals("7")){
			balckType = "2";
		}else if(type.equals("8")){
			balckType = "3";
		}
		map = blackListService.getIsBlack(memberId,balckType);
		Integer currentPage = reqDataJson.getInt("currentPage");
		String sourceMemberId = "";
		if(reqDataJson.containsKey("sourceMemberId")){
			sourceMemberId = reqDataJson.getString("sourceMemberId");
		}
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("type", type);//7砍价免费拿 8 邀请享免单 10 助力减价
		List<SingleProductActivityCustom> productActivityCustoms = singleProductActivityCustomMapper.getSingleBargainGoodsList(paramsMap);
		if(CollectionUtil.isNotEmpty(productActivityCustoms)){
			for (SingleProductActivityCustom custom : productActivityCustoms) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer productId = custom.getProductId();
				Integer maxInviteTimes = custom.getMaxInviteTimes();
				BigDecimal fixedAmount = custom.getFixedAmount();
				//1正常状态还可以强 2名额已满 3已抢光
				String status = "1";
				Integer stockSum = 0;
				Integer stock = 0;
				Integer lockedAmount = 0;
				BigDecimal tagPrice = new BigDecimal("0");
				BigDecimal salePrice = new BigDecimal("0");
				BigDecimal minPayAmount = new BigDecimal("0");
				//查找sku最高吊牌价
				ProductItemExample itemExample = new ProductItemExample();
				itemExample.createCriteria().andProductIdEqualTo(productId).andDelFlagEqualTo("0");
				itemExample.setOrderByClause("tag_price desc");
				List<ProductItem> items = productItemService.selectByExample(itemExample);
				if(CollectionUtil.isNotEmpty(items)){
					tagPrice = items.get(0).getTagPrice();
					salePrice = items.get(0).getSalePrice();
					for (ProductItem productItem : items) {
						stock += productItem.getStock();
						lockedAmount += productItem.getLockedAmount();
						stockSum += productItem.getStock()-productItem.getLockedAmount();
					}
				}
				if(stock == lockedAmount && lockedAmount > 0){
					status = "2";
				}else if(stockSum <= 0){
					status = "3";
				}
				//查找已成功砍价免费拿人数
				int memberReceiveNum = 0;
				if(!type.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
					memberReceiveNum = cutPriceOrderService.getcutOrderSuccessNum("", type, "4", custom, null);
				} 
				if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
					int needInviteNum = custom.getInviteTimes() == null ? 0 : custom.getInviteTimes();//需要请人数
					dataMap.put("needInviteNum", needInviteNum);
				}else if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
					minPayAmount = salePrice.subtract(fixedAmount.multiply(new BigDecimal(maxInviteTimes.toString())));
				}
				dataMap.put("memberReceiveNum", memberReceiveNum);
				dataMap.put("productId", productId);
				dataMap.put("productName", custom.getProductName());
				dataMap.put("productPic", StringUtil.getPic(custom.getProductPic(), ""));
				dataMap.put("status", status);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("salePrice", salePrice);
				dataMap.put("fixedAmount", fixedAmount);
				dataMap.put("minPayAmount", minPayAmount);
				dataMap.put("maxInviteTimes", maxInviteTimes);
				dataList.add(dataMap);
			}
		}
		//判断是否是
		String auditStatus = "0";//0不提示信息 1已邀请过 2未邀请过
		if(!StringUtil.isBlank(sourceMemberId) && type.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
			auditStatus = memberInfoService.getMemberAuditStatus(memberId,type);
		}
		map.put("dataList", dataList);
		map.put("auditStatus", auditStatus);
		map.put("memberId", memberId);
		map.put("sourceMemberId", sourceMemberId);
		return map;
	}

	public Map<String, Object> getBargainGoodsTopInfo() {
		Map<String,Object> map = new HashMap<String,Object>();
		//轮播
		List<String> memberReceiveInfoList = DataDicUtil.getMemberCutSuccessInfo();
		map.put("memberReceiveInfoList", memberReceiveInfoList);
		return map;
	}

	public Map<String, Object> getMyCutGoodsInfo(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		Date date = new Date();
		Integer currentPage = reqDataJson.getInt("currentPage");
		String type = reqDataJson.getString("type");
		BigDecimal zero = new BigDecimal("0");
		Map<String,Object> map = new HashMap<String,Object>();
		String balckType = "";
		if(type.equals("7")){
			balckType = "2";
		}else{
			balckType = "3";
		}
		map = blackListService.getIsBlack(memberId,balckType);
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object> >();
		CutPriceOrderExample orderExample = new CutPriceOrderExample();
		orderExample.createCriteria().andMemberIdEqualTo(memberId).andActivityTypeEqualTo(type).andAuditStatusNotEqualTo("2").andDelFlagEqualTo("0");
		orderExample.setLimitStart(currentPage*pageSize);
		orderExample.setLimitSize(pageSize);
		orderExample.setOrderByClause("id desc");
		List<CutPriceOrder> orders = cutPriceOrderService.selectByExample(orderExample);
		if(CollectionUtils.isNotEmpty(orders)){
			for (CutPriceOrder cutPriceOrder : orders) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer id = cutPriceOrder.getId();
				String skuPic = cutPriceOrder.getSkuPic();
				String status = cutPriceOrder.getStatus();
				String productName = cutPriceOrder.getProductName();
				Integer productId = cutPriceOrder.getProductId();
				BigDecimal tagPrice = new BigDecimal("0");
				if(status.equals("1")){//砍价中
					if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN)){
						BigDecimal surplusCutAmount = new BigDecimal("0");//剩余砍价金额
						BigDecimal cutAmount = new BigDecimal("0");
						ProductItem item = productItemService.selectByPrimaryKey(cutPriceOrder.getProductItemId());
						tagPrice = item.getTagPrice();
						CutPriceOrderDtlExample orderDtlExample = new CutPriceOrderDtlExample();
						orderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(id).andDelFlagEqualTo("0");
						List<CutPriceOrderDtl> orderDtls = cutPriceOrderDtlService.selectByExample(orderDtlExample);
						if(CollectionUtils.isNotEmpty(orderDtls)){
							for (CutPriceOrderDtl cutPriceOrderDtl : orderDtls) {
								cutAmount = cutAmount.add(cutPriceOrderDtl.getAmount());
							}
						}
						surplusCutAmount = tagPrice.subtract(cutAmount);
						if(surplusCutAmount.compareTo(zero) <= 0){
							surplusCutAmount = zero;
						}
						dataMap.put("surplusCutAmount", surplusCutAmount);
					}else if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
						Integer needInviteCount = 0;//需邀请人数
						Integer alreadyInvitedCount = 0;//已邀请人数
						tagPrice = zero;
						CutPriceOrderDtlExample orderDtlExample = new CutPriceOrderDtlExample();
						orderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(id).andDelFlagEqualTo("0").andStatusEqualTo("1");
						List<CutPriceOrderDtl> orderDtls = cutPriceOrderDtlService.selectByExample(orderDtlExample);
						CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
						cutPriceCnfExample.createCriteria().andSingleProductActivityIdEqualTo(cutPriceOrder.getSingleProductActivityId()).andDelFlagEqualTo("0");
						List<CutPriceCnf> cutPriceCnfs = cutPriceCnfService.selectByExample(cutPriceCnfExample);
						if(CollectionUtils.isNotEmpty(cutPriceCnfs)){
							needInviteCount = cutPriceCnfs.get(0).getInviteTimes();
							alreadyInvitedCount = orderDtls.size();
							if(alreadyInvitedCount > needInviteCount){
								alreadyInvitedCount = needInviteCount;
							}
						}
						dataMap.put("needInviteCount", needInviteCount);
						dataMap.put("alreadyInvitedCount", alreadyInvitedCount);
					}
				}
				//查找已成功砍价免费拿人数
				int memberReceiveNum = cutPriceOrderService.getcutOrderSuccessNum("", type, "4", null, cutPriceOrder.getSingleProductActivityId());
				dataMap.put("memberReceiveNum", memberReceiveNum);
				dataMap.put("cutOrderId", id);
				dataMap.put("skuPic", StringUtil.getPic(skuPic, "S"));
				dataMap.put("status", status);
				dataMap.put("productName", productName);
				dataMap.put("productId", productId);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("createDate", cutPriceOrder.getCreateDate());
				dataMap.put("auditStatus", cutPriceOrder.getAuditStatus());
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		map.put("currentDate", date);
		return map;
	}

	public Map<String, Object> getMyCutShareInfo(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		Date date = new Date();
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer cutOrderId = reqDataJson.getInt("cutOrderId");
		BigDecimal zero = new BigDecimal("0");
		boolean userIsHelpCut = false;
		Map<String,Object> map = new HashMap<String,Object>();
		map = blackListService.getIsBlack(memberId,"2");
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object> >();
		CutPriceOrderExample orderExample = new CutPriceOrderExample();
		orderExample.createCriteria().andIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
		List<CutPriceOrder> orders = cutPriceOrderService.selectByExample(orderExample);
		CutPriceOrder cutPriceOrder = orders.get(0);
		Integer id = cutPriceOrder.getId();
		String skuPic = cutPriceOrder.getSkuPic();
		String status = cutPriceOrder.getStatus();
		String productName = cutPriceOrder.getProductName();
		Integer productId = cutPriceOrder.getProductId();
		BigDecimal surplusCutAmount = new BigDecimal("0");//剩余砍价金额
		BigDecimal cutAmount = new BigDecimal("0");//砍价金额
		Integer sourceMemberId = cutPriceOrder.getMemberId();
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(sourceMemberId);
		String sourceHeadPic = memberInfo.getPic();
		String sourceNick = memberInfo.getNick();
		ProductItem item = productItemService.selectByPrimaryKey(cutPriceOrder.getProductItemId());
		List<String> contentList = new ArrayList<String>();
		contentList.add("红包犒劳一下！");
		contentList.add("砍价是技术活！");
		contentList.add("感情深砍一砍");
		contentList.add("已砍，我要留名！");
		contentList.add("光明正大的砍你！");
		contentList.add("我叫深情一刀！");
		contentList.add("生活处处有惊喜");
		contentList.add("专业砍手值得拥有");
		contentList.add("不谢、我就是这么低调");
		contentList.add("砍过、不谢~");
		BigDecimal tagPrice = item.getTagPrice();//原价
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("cutOrderId", id);
		List<CutPriceOrderDtlCustom> orderDtls = cutPriceOrderDtlService.getMemberCutOrderDtlList(paramsMap);
		if(CollectionUtils.isNotEmpty(orderDtls)){
			for (CutPriceOrderDtlCustom cutPriceOrderDtl : orderDtls) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				String cutPic = cutPriceOrderDtl.getWeixinHead();
				if(StringUtil.isBlank(cutPic)){
					cutPic = cutPriceOrderDtl.getPic();
				}
				String nick = cutPriceOrderDtl.getNick();
				if(!StringUtil.isBlank(nick) && nick.length() > 7){
					nick = nick.replace(nick.substring(7), "...");
				}
				dataMap.put("amount", cutPriceOrderDtl.getAmount());
				dataMap.put("nick", nick);
				dataMap.put("headPic", StringUtil.getPic(cutPic, ""));
				dataMap.put("content", contentList.get(new Random().nextInt(contentList.size())));
				dataList.add(dataMap);
			}
		}
		//获取用户砍价的总金额
		CutPriceOrderDtlExample priceOrderDtlExample = new CutPriceOrderDtlExample();
		priceOrderDtlExample.createCriteria().andDelFlagEqualTo("0").andCutPriceOrderIdEqualTo(cutOrderId);
		List<CutPriceOrderDtl> dtls = cutPriceOrderDtlService.selectByExample(priceOrderDtlExample);
		if(CollectionUtils.isNotEmpty(dtls)){
			for (CutPriceOrderDtl cutPriceOrderDtl : dtls) {
				if(memberId.equals(cutPriceOrderDtl.getMemberId())){
					userIsHelpCut = true;
				}
				cutAmount = cutAmount.add(cutPriceOrderDtl.getAmount());
			}
		}
		surplusCutAmount = tagPrice.subtract(cutAmount);
		if(surplusCutAmount.compareTo(zero) <= 0){
			surplusCutAmount = zero;
		}
		//查找已成功砍价免费拿人数
		int memberReceiveNum = cutPriceOrderService.getcutOrderSuccessNum("", cutPriceOrder.getActivityType(), "4", null, cutPriceOrder.getSingleProductActivityId());
		//memberStatus 该字段用来判断是谁进来分享页面
		//1表示本人进来如分享页面，只有分享按钮
		//2表示不是本人进来，还未帮忙砍价过，要显示砍价按钮
		//3表示不是本人进来，但是已经帮忙砍价过了，不要显示砍价按钮，要显示分享的按钮
		String memberStatus = "";
		if(memberId.equals(cutPriceOrder.getMemberId())){
			memberStatus = "1";
		}else if(userIsHelpCut){
			memberStatus = "3";
		}else{
			memberStatus = "2";
		}
		String decorateInfoId = "";
		String showType = "1";//1展示砍价列表 2展示装修页面
		if(!"3".equals(memberStatus)){
			//类型 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
			Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_5,null);
			decorateInfoId = adPageMap.get("decorateInfoId").toString();
			showType = "2";
		}
		//用户是否绑定手机号
		boolean mobileVerfiyFlag = true;
		if("0".equals(memberInfoService.selectByPrimaryKey(memberId).getmVerfiyFlag()) ) {
			mobileVerfiyFlag = false;
		}
		map.put("showType", showType);
		map.put("memberStatus", memberStatus);
		map.put("decorateInfoId", decorateInfoId);
		map.put("cutOrderId", id);
		map.put("skuPic", StringUtil.getPic(skuPic, "S"));
		map.put("status", status);
		map.put("productName", productName);
		map.put("productId", productId);
		map.put("tagPrice", tagPrice);
		map.put("surplusCutAmount", surplusCutAmount);
		map.put("cutAmount", cutAmount);
		map.put("createDate", cutPriceOrder.getCreateDate());
		map.put("dataList", dataList);
		map.put("currentDate", date);
		map.put("sourceMemberId", sourceMemberId);
		map.put("sourceHeadPic", StringUtil.getPic(sourceHeadPic, ""));
		map.put("sourceNick", sourceNick);
		map.put("memberId", memberId);
		map.put("memberReceiveNum", memberReceiveNum);
		map.put("mobileVerfiyFlag", mobileVerfiyFlag);
		return map;
	}

	public Map<String, Object> addMemberCutOrderInfo(Integer memberId, JSONObject reqDataJson) {
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> black = blackListService.getIsBlack(memberId,"2");
		if((boolean) black.get("isBlack")){
			throw new ArgException(black.get("blackReason").toString());
		}
		Date date = new Date();
		Date beginDate = DateUtil.getDateAfterAndBeginTime(date, 0);
		Date endDate = DateUtil.getDateAfterAndEndTime(date, 0);
		String cutOrderId = "";
		if(reqDataJson.containsKey("cutOrderId")){
			cutOrderId = reqDataJson.getString("cutOrderId");
		}
		Integer productId = reqDataJson.getInt("productId");
		Integer singleProductActivityId = null;
		List<String> rateTypeList = new ArrayList<String>();
		ProductItem item = null;
		BigDecimal amount = new BigDecimal("0");//砍价金额
		if(!StringUtil.isBlank(cutOrderId)){
			memberControlService.findMemberIsBlack(memberId, "3", 2);//每个用户每天最多只能砍2次
			CutPriceOrderExample orderExample = new CutPriceOrderExample();
			orderExample.createCriteria().andIdEqualTo(Integer.valueOf(cutOrderId)).andProductIdEqualTo(productId).andDelFlagEqualTo("0");
			List<CutPriceOrder> cutPriceOrders = cutPriceOrderService.selectByExample(orderExample);
			if(CollectionUtils.isNotEmpty(cutPriceOrders)){
				//来源用户是否被屏蔽
				black = blackListService.getIsBlack(cutPriceOrders.get(0).getMemberId(),"2");
				if((boolean) black.get("isBlack")){
					throw new ArgException("对方已被屏蔽");
				}
				//查询用户当天砍过几次
				CutPriceOrderDtlExample orderDtlExample = new CutPriceOrderDtlExample();
				orderDtlExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0").andCreateDateBetween(beginDate, endDate);
				int memberCutCount = cutPriceOrderDtlService.countByExample(orderDtlExample);
				rateTypeList.add("1");
				rateTypeList.add("3");
				amount = cutPriceOrderDtlService.addCutPriceOrderDtl(cutPriceOrders.get(0),memberId,rateTypeList,date,memberCutCount);
			}else{
				throw new ArgException("砍价失败");
			}
		}else{
			String type = reqDataJson.getString("type");
			Integer productItemId = null;
			Integer addressId = null;
			String receiverName = "";
			String receiverPhone = "";
			String receiverAddress = "";
			if(!type.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
				if(!reqDataJson.containsKey("addressId") && StringUtil.isBlank(reqDataJson.getString("addressId"))){
					throw new ArgException("请选择收货地址");
				}else{
					addressId = reqDataJson.getInt("addressId");
				}
				if(!reqDataJson.containsKey("receiverName") && StringUtil.isBlank(reqDataJson.getString("receiverName"))){
					throw new ArgException("请选择收货地址");
				}else{
					receiverName = reqDataJson.getString("receiverName");
				}
				if(!reqDataJson.containsKey("receiverPhone") && StringUtil.isBlank(reqDataJson.getString("receiverPhone"))){
					throw new ArgException("请选择收货地址");
				}else{
					receiverPhone = reqDataJson.getString("receiverPhone");
				}
				if(!reqDataJson.containsKey("receiverAddress") && StringUtil.isBlank(reqDataJson.getString("receiverAddress"))){
					throw new ArgException("请选择收货地址");
				}else{
					receiverAddress = reqDataJson.getString("receiverAddress");
				}
			}
			String sourceClient = "3";//H5
			if(reqDataJson.containsKey("sourceClient")){
				sourceClient = reqDataJson.getString("sourceClient");
			}
			Integer maxCount = 1;//每个用户每天最多只能砍1次
			if("7".equals(type)){
				int count = memberControlService.findMemberIsBlack(memberId, "4", maxCount);
				if(count <= 0){
					throw new ArgException("每人每天只能发起一次砍价免费拿哦！");
				}
			}else if("8".equals(type)){
				int count = memberControlService.findMemberIsBlack(memberId, "5", maxCount);
				if(count <= 0){
					throw new ArgException("每人每天只能发起一次邀请享免单哦！");
				}
			}
			CutPriceOrderExample orderExample = new CutPriceOrderExample();
			orderExample.createCriteria().andProductIdEqualTo(productId).andActivityTypeEqualTo(type).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
			orderExample.setOrderByClause("id desc");
			List<CutPriceOrder> cutPriceOrders = cutPriceOrderService.selectByExample(orderExample);
			if(CollectionUtils.isNotEmpty(cutPriceOrders)){
				String status = cutPriceOrders.get(0).getStatus();
				if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN)){
					if(status.equals("1")){
						throw new ArgException("商品正在砍价中，快去分享给好友吧！");
					}else if(status.equals("2") || status.equals("4")){
						throw new ArgException("砍价成功的商品不可再次砍价！");
					}
				}else if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE) || type.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
					Map<String,String> taskStatusMap = cutPriceOrderService.getAssistanceTaskStatus(cutPriceOrders.get(0),null);
					String taskStatus = taskStatusMap.get("taskStatus");
					if(taskStatus.equals("1") || taskStatus.equals("2") || taskStatus.equals("3")){
						throw new ArgException("任务中存在相同商品，不可重复发起！");
					}
				}else{
					throw new ArgException("砍价类型出错");
				}
			}
			com.jf.entity.Product p = productService.selectByPrimaryKey(productId);
			String isSingleProp = p.getIsSingleProp() == null ? "" : p.getIsSingleProp();
			if(reqDataJson.containsKey("productItemId")&&!StringUtil.isEmpty(reqDataJson.getString("productItemId"))){
				productItemId=Integer.valueOf(reqDataJson.getString("productItemId"));
			}else{
				if(!"1".equals(isSingleProp)){
					throw new ArgException("商品库存不足");
				}
				ProductItemExample productItemExample=new ProductItemExample();
				productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
				List<ProductItem> productItems=productItemService.selectByExample(productItemExample);
				productItemId=productItems.get(0).getId();
			}
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("productId", productId);
			paramsMap.put("productItemId", productItemId);
			paramsMap.put("type", type);
			List<SingleProductActivityCustom> activityCustoms = singleProductActivityCustomMapper.getCutOrderProductInfo(paramsMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				//冻结数量
				String canOrder = "0";
				String status = "1";
				String content = "申请中";
				if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){//助力大减价不锁定库存，等用户有分享在锁定库存
					canOrder = "1";
					status = "0";
					content = "申请未激活";
				}else{
					productItemService.updateSkuLockedAmount(productItemId,1,p.getMchtId(),"1");
				}
				MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
				rateTypeList.add("2");
				SingleProductActivityCustom custom = activityCustoms.get(0);
				singleProductActivityId = custom.getId();
				CutPriceOrder cutPriceOrder  = new CutPriceOrder();
				String orderCode = "CUT"+CommonUtil.getOrderCode();
				cutPriceOrder.setOrderCode(orderCode);
				cutPriceOrder.setSingleProductActivityId(singleProductActivityId);
				cutPriceOrder.setMemberId(memberId);
				cutPriceOrder.setProductId(productId);
				cutPriceOrder.setProductItemId(productItemId);
				cutPriceOrder.setSkuPic(custom.getSkuPic());
				cutPriceOrder.setProductName(custom.getProductName());
				cutPriceOrder.setArtNo(custom.getArtNo());
				cutPriceOrder.setBrandName(custom.getBrandName());
				cutPriceOrder.setProductPropDesc(custom.getProductPropDesc());
				cutPriceOrder.setQuantity(1);
				cutPriceOrder.setSourceClient(sourceClient);
				cutPriceOrder.setTagPrice(custom.getTagPrice());
				cutPriceOrder.setSalePrice(custom.getSalePrice());
				cutPriceOrder.setStatus(status);
				cutPriceOrder.setCanOrder(canOrder);
				cutPriceOrder.setMemberNick(memberInfo.getNick());
				cutPriceOrder.setAddressId(addressId);
				cutPriceOrder.setReceiverName(receiverName);
				cutPriceOrder.setReceiverPhone(receiverPhone);
				cutPriceOrder.setReceiverAddress(receiverAddress);
				cutPriceOrder.setCreateBy(memberId);
				cutPriceOrder.setCreateDate(date);
				cutPriceOrder.setActivityType(type);
				cutPriceOrder.setDelFlag("0");
				cutPriceOrderService.insertSelective(cutPriceOrder);
				cutOrderId = cutPriceOrder.getId().toString();
				//日志
				cutPriceOrderLogService.addCutPriceOrderLog(memberId, cutPriceOrder.getId(), status, content);
				
				if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN)){
					item = new ProductItem();
					item.setId(productItemId);
					item.setSalePrice(custom.getSalePrice());
					item.setTagPrice(custom.getTagPrice());
					amount = cutPriceOrderDtlService.addCutPriceOrderDtl(cutPriceOrder,memberId,rateTypeList,date,0);
				}else if(type.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){ //助力减价创建订单后分享
					amount = cutPriceOrderDtlService.getAssistancePerCutAmount(cutPriceOrder.getSingleProductActivityId());
					// 小程序分享
					commonService.fillWithXCXShareInfo(map, "page/activity/reducePrice/share/index?cutOrderId={}&sourceMemberId={}", cutOrderId, memberId);
					map.put("shareTitle", StringUtil.buildMsg("我喜欢这款，快来助我省钱，点击立省{}元", amount));
					map.put("shareDesc", "醒购助力减价拿");
				}
			}else{
				throw new ArgException("您已来晚了，该商品已下架");
			}
		}

		map.put("amount", amount);
		map.put("cutOrderId", cutOrderId);
		return map;
	}

	public Map<String, Object> getMyInviteShareInfo(Integer memberId, JSONObject reqDataJson) {
		Integer cutOrderId = reqDataJson.getInt("cutOrderId");
		CutPriceOrder cutPriceOrder = cutPriceOrderService.selectByPrimaryKey(cutOrderId);
		String showType = "2";//1展示砍价列表 2展示装修页面
		String status = cutPriceOrder.getStatus();
		String auditStatus = "0";//0不提示信息 1已邀请过 2未邀请过
		String decorateInfoId = "";//装修id
		if(!cutPriceOrder.getMemberId().equals(memberId)){
			showType = "1";
			auditStatus = memberInfoService.getMemberAuditStatus(memberId,cutPriceOrder.getActivityType());
		}else{
			//类型 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
			Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_9,null);
			decorateInfoId = adPageMap.get("decorateInfoId").toString();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map = blackListService.getIsBlack(memberId,"3");
		int memberRecNum = cutPriceOrderService.getcutOrderSuccessNum("", cutPriceOrder.getActivityType(), "4", null, cutPriceOrder.getSingleProductActivityId());//查询总领取人数
		int lastNeedInviteNum = 99;//剩余邀请人数,设置99是预防找不到砍价配置表
		int alreadyInviteNum = 0;//查询已邀请人数
		if("1".equals(status)){
			
			lastNeedInviteNum = 3;
			CutPriceOrderDtlExample orderDtlExample = new CutPriceOrderDtlExample();
			orderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(cutOrderId).andDelFlagEqualTo("0").andStatusEqualTo("1");
			alreadyInviteNum = cutPriceOrderDtlService.countByExample(orderDtlExample);
			
			//查询需邀请人数
			CutPriceCnfExample cnfExample = new CutPriceCnfExample();
			cnfExample.createCriteria().andSingleProductActivityIdEqualTo(cutPriceOrder.getSingleProductActivityId()).andDelFlagEqualTo("0");
			cnfExample.setLimitStart(0);
			cnfExample.setLimitSize(1);
			cnfExample.setOrderByClause("id desc");
			List<CutPriceCnf> cnfs = cutPriceCnfService.selectByExample(cnfExample);
			if(CollectionUtils.isNotEmpty(cnfs)){
				int needInviteNum = cnfs.get(0).getInviteTimes();
				lastNeedInviteNum = needInviteNum - alreadyInviteNum;
				if(lastNeedInviteNum < 0){
					lastNeedInviteNum = 0;
				}
			}
		}
		//用户是否绑定手机号
		boolean mobileVerfiyFlag = true;
		if("0".equals(memberInfoService.selectByPrimaryKey(memberId).getmVerfiyFlag()) ) {
			mobileVerfiyFlag = false;
		}
		map.put("productId", cutPriceOrder.getProductId());
		map.put("decorateInfoId", decorateInfoId);
		map.put("status", status);
		map.put("showType", showType);
		map.put("memberRecNum", memberRecNum);
		map.put("cutOrderId", cutOrderId);
		map.put("skuPic", StringUtil.getPic(cutPriceOrder.getSkuPic(), "S"));
		map.put("productName", cutPriceOrder.getProductName());
		map.put("tagPrice", cutPriceOrder.getTagPrice());
		map.put("auditStatus", auditStatus);
		map.put("createDate", cutPriceOrder.getCreateDate());
		map.put("currentDate", new Date());
		map.put("lastNeedInviteNum", lastNeedInviteNum);
		map.put("alreadyInviteNum", alreadyInviteNum);
		map.put("mobileVerfiyFlag", mobileVerfiyFlag);
		return map;
	}

	public List<Map<String, Object>> getInviteDetail(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		Integer cutOrderId = reqDataJson.getInt("cutOrderId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		String activityType = Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE;
		if(reqDataJson.containsKey("activityType") && !StringUtil.isBlank(reqDataJson.getString("activityType"))){
			activityType = reqDataJson.getString("activityType");
		}
		List<Map<String, Object>> dataList = getCommenInviteDetail(currentPage,pageSize,cutOrderId,memberId,activityType);
		
		return dataList;
	}
	
	public List<Map<String, Object>> getCommenInviteDetail(Integer currentPage, Integer pageSize, Integer cutOrderId, Integer memberId,String activityType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("cutOrderId", cutOrderId);
//		paramsMap.put("memberId", memberId);
		paramsMap.put("activityType", activityType);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("currentPage", currentPage*pageSize);
		List<CutPriceOrderDtlCustom> customs = cutPriceOrderDtlService.getInviteDetail(paramsMap);
		if(CollectionUtils.isNotEmpty(customs)){
			for (CutPriceOrderDtlCustom cutPriceOrderDtlCustom : customs) {
				Map<String, Object> map = new HashMap<String, Object>();
				String status = cutPriceOrderDtlCustom.getStatus();
				String weixinHead = cutPriceOrderDtlCustom.getWeixinHead();
				if (!StringUtils.hasText(weixinHead)) {
					weixinHead = cutPriceOrderDtlCustom.getPic();
				}
				String content = "";
				if(status.equals("1")){
					map = new HashMap<String, Object>();
					content = "助力成功";
				}else{
					content = "等待下载APP助力";
				}
				map.put("status", status);
				map.put("content", content);
				map.put("createDateStr", DateUtil.getFormatDate(cutPriceOrderDtlCustom.getCreateDate(), "yyyy年MM月dd日 HH:mm"));
				map.put("createDate", cutPriceOrderDtlCustom.getCreateDate());
				map.put("weixinHead", StringUtil.getPic(weixinHead, ""));
				map.put("memberNick", cutPriceOrderDtlCustom.getNick());
				map.put("memberId", cutPriceOrderDtlCustom.getMemberId());
				map.put("amount", cutPriceOrderDtlCustom.getAmount()); //助力减价才一定有值
				dataList.add(map);
			}
		}
		return dataList;
	}

	/**
	 * 方法描述 ：(会场自定义页面获取秒杀页面数据)
	 * @param currentPage 
	 * @param pageSize 
	 */
	public Map<String, Object> getActivityAreaSecKillProdocut(Integer pageSize, Integer currentPage, String seckillType) {
		//seckillType限时抢购子类型 1 普通限时抢购 2 会场限时抢购
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> seckillList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
		Date currentDate = new Date();
		SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
		seckillTimeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andSeckillTypeEqualTo(seckillType);
		seckillTimeExample.setOrderByClause("start_hours,start_min");
		List<SeckillTime> seckillTimes = seckillTimeService.selectByExample(seckillTimeExample);
		if(CollectionUtils.isNotEmpty(seckillTimes)){
			List<Map<String,Object>> seckillEndList = new ArrayList<Map<String,Object>>();//已结束的
			for (SeckillTime seckillTime : seckillTimes) {
				Map<String, Object> seckillMap = new HashMap<String, Object>();
				String startHours = seckillTime.getStartHours();
				String startMin= seckillTime.getStartMin();
				Integer continueHours = Integer.valueOf(seckillTime.getContinueHours());
				Integer continueMin = Integer.valueOf(seckillTime.getContinueMin());
				String context = "即将开始";
				String seckill = startHours+":"+startMin;
				String sec = "00";
				//是否是秒杀结束
				boolean isEndActivity = false;
				//获取总时长（分钟）
				int totalContinueMin = continueHours*60+continueMin;
				//拼接成yyyy-MM-dd HH:mm:ss格式
				Date beginDate = DateUtil.getDate(DateUtil.getFormatDate(currentDate, "yyyy-MM-dd")+" "+seckill+":"+sec);
				Date endDate = DateUtil.addMinute(beginDate, totalContinueMin);
				if(DateUtil.addDay(beginDate, -1).compareTo(currentDate) <= 0 && DateUtil.addMinute(DateUtil.addDay(beginDate, -1), totalContinueMin).compareTo(currentDate) > 0){
					//判断昨天的是否已结束
					beginDate = DateUtil.addDay(beginDate, -1);
					endDate = DateUtil.addMinute(beginDate, totalContinueMin);
				}

				if(beginDate.compareTo(currentDate) <= 0 && endDate.compareTo(currentDate) >= 0){
					//已开始，抢购中
					context = "抢购中";
				}else if(beginDate.compareTo(currentDate) > 0){
					//未开始，即将开始
					context = "即将开始";
				}else if(endDate.compareTo(currentDate) < 0){
					//已结束的放入已结束集合
					isEndActivity = true;
					context = "即将开始";
					beginDate = DateUtil.addDay(beginDate, 1);
					endDate = DateUtil.addDay(endDate, 1);
				}
				seckillMap.put("beginTime", beginDate.getTime());
				seckillMap.put("endTime", endDate.getTime());
				seckillMap.put("currentTime", currentDate.getTime());
				seckillMap.put("beginTimeStr", seckill);
				seckillMap.put("context", context);
				if(isEndActivity){
					seckillEndList.add(seckillMap);
				}else{
					seckillList.add(seckillMap);
				}
			}
			//把已结束的放在 seckillList 后面
			if(CollectionUtils.isNotEmpty(seckillEndList)){
				for (Map<String, Object> m : seckillEndList) {
					seckillList.add(m);
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
			productInfoList = getTopSecKillProductList(beginTime,currentPage,pageSize,seckillType);
		}
		map.put("seckillList", seckillList);
		map.put("productInfoList", productInfoList);
		return map;
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


	public Map<String, Object> getMyAssistanceGoodsTaskList(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		Date currentDate = new Date();
		Integer currentPage = reqDataJson.getInt("currentPage");
		String type = reqDataJson.getString("type");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object> >();
		String balckType = "";
		if(type.equals("7")){
			balckType = "2";
		}else if(type.equals("8")){
			balckType = "3";
		}
		map = blackListService.getIsBlack(memberId,balckType);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("memberId", memberId);
		paramsMap.put("type", Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
		List<CutPriceOrderCustom> orders = cutPriceOrderService.getMyAssistanceGoodsTaskList(paramsMap);
		if(CollectionUtils.isNotEmpty(orders)){
			for (CutPriceOrderCustom cutPriceOrder : orders) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer id = cutPriceOrder.getId();
				String skuPic = cutPriceOrder.getSkuPic();
				String status = cutPriceOrder.getStatus();//状态1砍价中 2砍价成功 4已下单 3砍价失败
				String productName = cutPriceOrder.getProductName();
				Integer productId = cutPriceOrder.getProductId();
				Integer assistanceNum = cutPriceOrder.getAssistanceNum();//已成功邀请人数
				Integer unAssistanceNum = cutPriceOrder.getUnAssistanceNum();//未完全助力人数
				Integer maxInviteTimes = cutPriceOrder.getMaxInviteTimes();//最大邀请人数
				Integer activeTime = cutPriceOrder.getActiveTime();
				BigDecimal salePrice = cutPriceOrder.getSalePrice();
				BigDecimal tagPrice = cutPriceOrder.getTagPrice();
				BigDecimal fixedAmount = cutPriceOrder.getFixedAmount();//每个人助力固定金额
				Date beginDate = cutPriceOrder.getCreateDate();
				Date endDate = DateUtil.addHour(beginDate, activeTime);
				Map<String,String> taskStatusMap = cutPriceOrderService.getAssistanceTaskStatus(cutPriceOrder,activeTime);
				String taskStatus = taskStatusMap.get("taskStatus");
				if(assistanceNum > maxInviteTimes){
					assistanceNum = maxInviteTimes;
				}
				dataMap.put("taskStatus", taskStatus);
				dataMap.put("skuPic", StringUtil.getPic(skuPic, "S"));
				dataMap.put("productName", productName);
				dataMap.put("productId", productId);
				dataMap.put("productItemId", cutPriceOrder.getProductItemId());
				dataMap.put("quantity", cutPriceOrder.getQuantity());
				dataMap.put("singleProductActivityId", cutPriceOrder.getSingleProductActivityId());
				dataMap.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("salePrice", salePrice);
				dataMap.put("curOrderId", id);
				dataMap.put("beginDate", beginDate);
				dataMap.put("endDate", endDate);
				if (Objects.equals(taskStatus, "3")) { //邀请完成，完成时开始购买倒计时
					dataMap.put("buyEndDate", DateUtil.addHour(cutPriceOrder.getUpdateDate(), Const.ASSISTANCE_ACTIVITY_COUNT_DOWN_HOURS)); //购买限时
				} else if (Objects.equals(taskStatus, "2")) { //任务结束，结束时开始购买倒计时
					dataMap.put("buyEndDate", DateUtil.addHour(endDate, Const.ASSISTANCE_ACTIVITY_COUNT_DOWN_HOURS)); //购买限时
				}

				dataMap.put("maxInviteTimes", maxInviteTimes);
				dataMap.put("assistanceNum", assistanceNum);
				dataMap.put("unAssistanceNum", unAssistanceNum);
				dataMap.put("fixedAmount", fixedAmount);
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		map.put("currentDate", currentDate);
		map.put("memberId", memberId);
		return map;
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

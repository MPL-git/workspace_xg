package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ActivityAreaCustomMapper;
import com.jf.dao.ActivityAreaMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaCustom;
import com.jf.entity.ActivityAreaDecorate;
import com.jf.entity.ActivityAreaDecorateExample;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityGroup;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月5日 上午11:13:28 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ActivityAreaService extends BaseService<ActivityArea, ActivityAreaExample> {
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	@Autowired
	private ActivityAreaCustomMapper activityAreaCustomMapper;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ActivityAreaDecorateService activityAreaDecorateService;
	@Autowired
	private DecorateInfoService decorateInfoService;
	@Resource
	private ActivityGroupService activityGroupService;
	@Resource
	private ProductService productService;


	@Autowired
	public void setActivityAreaMapper(ActivityAreaMapper activityAreaMapper) {
		this.setDao(activityAreaMapper);
		this.activityAreaMapper = activityAreaMapper;
	}

	public Map<String, Object> getActivityByAreaId(Integer activityAreaId, String memberId, Integer pageSize, Integer currentPage) {
		List<Map<String,Object>> dataMapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("activityAreaId", activityAreaId);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("currentPage", currentPage*pageSize);
		List<ActivityAreaCustom> activityAreas = activityAreaCustomMapper.getActivity(paramsMap);
		String topPic = "";
		String activityAreaName = "";
		Date activityBeginTime = null;
		Date activityEndTime = null;
		if(CollectionUtils.isNotEmpty(activityAreas)){
			activityBeginTime = activityAreas.get(0).getActivityBeginTime();
			activityEndTime = activityAreas.get(0).getActivityEndTime();
			activityAreaName = activityAreas.get(0).getName();
			if(!StringUtil.isBlank(activityAreas.get(0).getTopPic())){
				topPic = StringUtil.getPic(activityAreas.get(0).getTopPic(), "");
			}
			for (ActivityAreaCustom activityAreaCustom : activityAreas) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer activityId = activityAreaCustom.getActivityId();
				String activityName = activityAreaCustom.getActivityName();
				String activityEntryPic = activityAreaCustom.getActivityEntryPic();
				String logo = activityAreaCustom.getLogo();
				activityEntryPic = StringUtil.getPic(activityEntryPic, "");
				logo = StringUtil.getPic(logo, "");
				dataMap.put("activityId", activityId);
				dataMap.put("activityName", activityName);
				dataMap.put("activityEntryPic", activityEntryPic);
				dataMap.put("logo", logo);
				dataMapList.add(dataMap);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataMapList", dataMapList);
		map.put("topPic", topPic);
		map.put("activityBeginTime", activityBeginTime == null ? 0 : activityBeginTime.getTime());
		map.put("activityEndTime", activityEndTime == null ? 0 : activityEndTime.getTime());
		map.put("currentTime", new Date().getTime());
		map.put("activityAreaName", activityAreaName);
		//map.put("totalPage", page.getTotalPage());
		return map;
	}

	public Map<String, Object> getIndividualActivityByAreaId(Integer activityAreaId, String memberId, Integer pageSize,
			Integer currentPage, String suitSex, String suitGroup, String productTypeId, String salePriceMin, String salePriceMax, String salePriceSort, String discountSort, String stockMark, List<String> productTypeIdList, ActivityArea activityArea) {
		List<Map<String,Object>> produtMapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> returnData = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		String activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
		params.put("activityAreaId", activityAreaId);
		params.put("suitSex", suitSex);
		params.put("suitGroup", suitGroup);
		params.put("productTypeIdList", productTypeIdList);
		params.put("salePriceMin", salePriceMin);
		params.put("salePriceMax", salePriceMax);
		params.put("salePriceSort", salePriceSort);
		params.put("discountSort", discountSort);
		params.put("stockMark", stockMark);
		params.put("activityAreaType", activityArea.getType());
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<ActivityAreaCustom> activityAreaCustoms = getIndividualActivity(params);
		if(CollectionUtils.isNotEmpty(activityAreaCustoms)){
			String activityAreaName = activityAreaCustoms.get(0).getName();
			String topPic = StringUtil.getPic(activityAreaCustoms.get(0).getTopPic(), "");
			Date activityBeginTime = activityAreaCustoms.get(0).getActivityBeginTime();
			Date activityEndTime = activityAreaCustoms.get(0).getActivityEndTime();
			String preferentialType = activityAreaCustoms.get(0).getPreferentialType();
			String preferentialIdGroup = activityAreaCustoms.get(0).getPreferentialIdGroup();
			for (ActivityAreaCustom activityAreaCustom : activityAreaCustoms) {
				Map<String,Object> produtMap = new HashMap<String,Object>();
				Integer productId = activityAreaCustom.getProductId();
				String productName = activityAreaCustom.getProductName();
				String pic = activityAreaCustom.getPic();
				Integer activityId = activityAreaCustom.getActivityId();
				String activityName = activityAreaCustom.getActivityName();
				BigDecimal salePrice = activityAreaCustom.getSalePrice();
				BigDecimal tagPrice = activityAreaCustom.getTagPrice();
				BigDecimal svipDiscount = activityAreaCustom.getSvipDiscount() == null ? new BigDecimal("0") : activityAreaCustom.getSvipDiscount();
				BigDecimal dsicount = activityAreaCustom.getDiscount().setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal svipSalePrice = new BigDecimal("0");
				BigDecimal arrivalPrice = new BigDecimal("0");
				BigDecimal deductAmount = new BigDecimal("0");
				BigDecimal deposit = new BigDecimal("0");
				if(activityAreaCustom.getActivityBeginTime().after(new Date())&&activityAreaCustom.getDeductAmount()!=null && activityAreaCustom.getDeposit()!=null){
					deductAmount = activityAreaCustom.getDeductAmount();
					deposit = activityAreaCustom.getDeposit();
					arrivalPrice = salePrice.subtract(deductAmount).add(deposit);
				}
				if(activityAreaCustom.getMaxSalePrice().compareTo(salePrice)==0){
					produtMap.put("hasDifferentPrice", "");
				}else{
					produtMap.put("hasDifferentPrice", "起");
				}
				String dsicountStr = "";
				if(dsicount.compareTo(new BigDecimal(0)) == 0){
					dsicountStr = "";
				}else if(dsicount.compareTo(new BigDecimal(1)) == 0){
					dsicountStr = "";
				}else{
					dsicountStr = dsicount.multiply(new BigDecimal(10)).toString();
					dsicountStr = dsicountStr.replaceAll("0*$","");
				}
				Integer stockSum = activityAreaCustom.getStockSum() == null ? 0 : activityAreaCustom.getStockSum();
				if(activityAreaCustom.getIsWatermark().equals("1")){
					pic = StringUtil.getActivityMkPic(pic, "M_WM", activityAreaId);
				}else{
					pic = StringUtil.getPic(pic, "M");
				}
				if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
					svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, activityType);
				}
				produtMap.put("productId", productId);
				produtMap.put("productName", productName);
				produtMap.put("pic", pic);
				produtMap.put("activityId", activityId);
				produtMap.put("activityName", activityName);
				produtMap.put("salePrice", salePrice);
				produtMap.put("tagPrice", tagPrice);
				produtMap.put("dsicount", dsicountStr);
				produtMap.put("stockSum", stockSum);
				produtMap.put("svipSalePrice", svipSalePrice);
				produtMap.put("arrivalPrice", arrivalPrice);
				produtMap.put("deductAmount", deductAmount);
				produtMap.put("deposit", deposit);
				produtMapList.add(produtMap);
			}
			//商品彩标图
			String productWkPicM = "";
			String productWkPosition = "";//商品彩标位置1 左上 2 右上 3 右下 4 左下 
			if(activityArea.getActivityGroupId() != null){
				ActivityGroup activityGroup = activityGroupService.getActivityGroupModelByGroupId(activityArea.getActivityGroupId());
				if(activityGroup != null){
					productWkPicM = activityGroup.getProductWkPicM();
					productWkPosition = activityGroup.getProductWkPosition();
				}
			}
			returnData.put("productWkPicM", StringUtil.getPic(productWkPicM, ""));
			returnData.put("productWkPosition", productWkPosition);
			returnData.put("activityAreaName", activityAreaName);
			returnData.put("topPic", topPic);
			returnData.put("activityBeginTime", activityBeginTime.getTime());
			returnData.put("activityEndTime", activityEndTime.getTime());
			returnData.put("currentTime", new Date().getTime());
			returnData.put("activityAreaId", activityAreaId);
		}
		returnData.put("produtMapList", produtMapList);
		return returnData;
	}

	public List<ActivityAreaCustom> getIndividualActivity(Map<String, Object> params) {
		 
		return activityAreaCustomMapper.getIndividualActivity(params);
	}

	public ActivityArea findModel(Integer activityAreaId) {
		
		return activityAreaMapper.selectByPrimaryKey(activityAreaId);
	}

	public Map<String, Object> getH5ProductInfoList(String sportProducts, String clothingProducts,
			String shoesProducts, String lifeProducts) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<String> sportProductsList = StringUtil.getArrays(sportProducts);
		List<String> clothingProductsList = StringUtil.getArrays(clothingProducts);
		List<String> shoesProductsList = StringUtil.getArrays(shoesProducts);
		List<String> lifeProductsList = StringUtil.getArrays(lifeProducts);

		List<Map<String,Object>> sportList = getProductInfoList(sportProductsList);
		List<Map<String,Object>> clothingList = getProductInfoList(clothingProductsList);
		List<Map<String,Object>> shoesList = getProductInfoList(shoesProductsList);
		List<Map<String,Object>> lifeList = getProductInfoList(lifeProductsList);
		map.put("sportList", sportList);
		map.put("clothingList", clothingList);
		map.put("shoesList", shoesList);
		map.put("lifeList", lifeList);
		map.put("currentTime", new Date());
		return map;
	}

	private List<Map<String, Object>> getProductInfoList(List<String> productList) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		if(CollectionUtils.isNotEmpty(productList)){
			params.put("list", productList);
			List<ActivityAreaCustom> activityAreas = activityAreaCustomMapper.getH5ProductInfoList(productList);
			if(CollectionUtils.isNotEmpty(activityAreas)){
				for (ActivityAreaCustom activityAreaCustom : activityAreas) {
					Map<String,Object> productmap = new HashMap<String,Object>();
					BigDecimal dsicount = activityAreaCustom.getDiscount().setScale(2, BigDecimal.ROUND_HALF_UP);
					String dsicountStr = "";
					if(dsicount.compareTo(new BigDecimal(0)) == 0){
						dsicountStr = "";
					}else if(dsicount.compareTo(new BigDecimal(1)) == 0){
						dsicountStr = "";
					}else{
						dsicountStr = dsicount.multiply(new BigDecimal(10)).toString();
						dsicountStr = dsicountStr.replaceAll("0*$","");
					}
					productmap.put("activityAreaId", activityAreaCustom.getId());
					productmap.put("activityAreaName", activityAreaCustom.getName());
					productmap.put("activityId", activityAreaCustom.getActivityId());
					productmap.put("productId", activityAreaCustom.getProductId());
					productmap.put("productName", activityAreaCustom.getProductName());
					productmap.put("stockSum", activityAreaCustom.getStockSum());
					productmap.put("salePrice", activityAreaCustom.getSalePrice());
					productmap.put("tagPrice", activityAreaCustom.getTagPrice());
					productmap.put("discount", dsicountStr);
					productmap.put("pic", StringUtil.getPic(activityAreaCustom.getPic(), "M"));
					map.put(activityAreaCustom.getProductId().toString(), productmap);
				}
				//排序操作
				for (String str : productList) {
					if(map.containsKey(str)){
						list.add((Map)map.get(str));
					}
				}
			}
		}
		return list;
	}
	
	public Map<String, Object> getDecorationMeeting(Integer activityAreaId, JSONObject reqPRM, Integer memberId) {
		Integer decorateInfoId = null;
		ActivityAreaDecorateExample activityAreaDecorateExample = new ActivityAreaDecorateExample();
		activityAreaDecorateExample.createCriteria().andActivityAreaIdEqualTo(activityAreaId).andDelFlagEqualTo("0");
		List<ActivityAreaDecorate> activityAreaDecorates = activityAreaDecorateService.selectByExample(activityAreaDecorateExample);
		if(CollectionUtils.isNotEmpty(activityAreaDecorates)){
			decorateInfoId = activityAreaDecorates.get(0).getDecorateInfoId();
		}
		Map<String,Object> map = decorateInfoService.getHomePageDecorateInfo(decorateInfoId,reqPRM,memberId);
		return map;
	}


	
}

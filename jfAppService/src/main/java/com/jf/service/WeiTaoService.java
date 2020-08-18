package com.jf.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.taobaoke.TaobaoUtil;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoCustom;
import com.jf.entity.ThirdPlatformProductInfoExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WeiTaoService{
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductPicService productPicService;
	@Autowired
	private ThirdPlatformProductInfoService thirdPlatformProductInfoService;
	@Autowired
	private KeywordMomoionymService keywordMomoionymService;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	/**
	 * 第三方平台商品详情页面
	 * @param reqPRM
	 * @param reqDataJson
	 * @return
	 */
	public Map<String, Object> getThirdPartyProductBaseInfo(JSONObject reqPRM, JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProductExample productExample = new ProductExample();
		ProductExample.Criteria criteria = productExample.createCriteria();
		criteria.andStatusEqualTo("1").andSourceNotEqualTo("0").andDelFlagEqualTo("0");
		if(reqDataJson.containsKey("id") && !StringUtil.isBlank(reqDataJson.getString("id"))){
			criteria.andIdEqualTo(reqDataJson.getInt("id"));
		}else if(reqDataJson.containsKey("code") && !StringUtil.isBlank(reqDataJson.getString("code"))){
			criteria.andCodeEqualTo(reqDataJson.getString("code"));
		}else if(reqDataJson.containsKey("refId") && !StringUtil.isBlank(reqDataJson.getString("refId"))){
			criteria.andRefIdEqualTo(reqDataJson.getString("refId"));
		}else{
			throw new ArgException("商品id不能为空");
		}
		List<Product> products = productService.selectByExample(productExample);
		if(CollectionUtils.isNotEmpty(products)){
			Product p = products.get(0);
			String source = p.getSource();
			String refId = p.getRefId();
			Integer productId = p.getId();
			BigDecimal mallPrice =  p.getMinMallPrice();
			BigDecimal tagPrice =  p.getMinTagPrice();
			String saleTypeName = "";
			String isCoupon = "0";
			String content = "";
			Integer saleNum = 0;
			String saleNumContent = "";
			Date currentDate = new Date();
			if("1".equals(source)){
				saleTypeName = "淘宝价";
			}else if("2".equals(source)){
				saleTypeName = "天猫价";
			}
			//封装主图
			List<Map<String,Object>> picMapList = productPicService.getProductDefaultPic(p.getId(),"","0",null);
			//优惠券
			List<Map<String, Object>> couponList = new ArrayList<Map<String, Object>>();
			ThirdPlatformProductInfoExample thirdPlatformProductInfoExample = new ThirdPlatformProductInfoExample();
			thirdPlatformProductInfoExample.createCriteria().andProductIdEqualTo(productId).andRefIdEqualTo(refId).andDelFlagEqualTo("0");
			List<ThirdPlatformProductInfo> thirdPlatformProductInfos = thirdPlatformProductInfoService.selectByExample(thirdPlatformProductInfoExample);
			if(CollectionUtils.isNotEmpty(thirdPlatformProductInfos)){
				ThirdPlatformProductInfo thirdPlatformProductInfo = thirdPlatformProductInfos.get(0);
				Map<String, Object> couponMap = new HashMap<String, Object>();
				isCoupon = thirdPlatformProductInfo.getIsCoupon();
				saleNum = thirdPlatformProductInfo.getVolume();
				BigDecimal couponAmount = thirdPlatformProductInfo.getCouponAmount() == null ? new BigDecimal("0") : thirdPlatformProductInfo.getCouponAmount();
				tagPrice = thirdPlatformProductInfo.getZkFinalPrice();
				mallPrice = tagPrice;
				String couponInfo = thirdPlatformProductInfo.getCouponInfo();
				if("1".equals(thirdPlatformProductInfo.getIsCoupon()) && !StringUtil.isBlank(couponInfo)){
//					long couponExpirationDay = 0;
//					String couponEndTime = thirdPlatformProductInfo.getCouponEndTime();
//					String expirStr = "";
//					if((DateUtil.getFormatString(couponEndTime, "yyyy-MM-dd").getTime()-currentDate.getTime())/(double)(24*60*60*1000) <= 0){
//						expirStr = "已过期";
//					}else{
//						couponExpirationDay = (DateUtil.getFormatString(couponEndTime, "yyyy-MM-dd").getTime()-currentDate.getTime())/(24*60*60*1000);
//						if(couponExpirationDay >= 1){
//							expirStr = couponExpirationDay+"天后过期";
//						}else{
//							expirStr = "1天后过期";
//						}
//					}
					couponInfo = couponInfo.substring(couponInfo.lastIndexOf("减")+1, couponInfo.length()-1);
					mallPrice = tagPrice.subtract(new BigDecimal(couponInfo));
					couponMap.put("name", couponInfo+"元优惠券");
					couponMap.put("money", couponAmount);
					couponMap.put("couponUrl", thirdPlatformProductInfo.getCouponClickUrl());
					couponMap.put("couponExpirationDateStr", "");
					couponList.add(couponMap);
				}
			}
			if(saleNum > 0){
				saleNumContent = "月销："+saleNum;
			}
			if("1".equals(isCoupon)){
				content = "券后价";
			}else{
				content = "折扣价";
			}
			map.put("isCoupon", isCoupon);
			map.put("productName", p.getName());
			map.put("mallPrice", mallPrice);
			map.put("tagPrice", tagPrice);
			map.put("source", source);
			map.put("content", content);
			map.put("saleNum", saleNumContent);
			map.put("saleTypeName", saleTypeName);
			map.put("refId", p.getRefId());
			map.put("id", p.getId());
			map.put("code", p.getCode());
			map.put("picMapList", picMapList);
			map.put("couponList", couponList);
		}else{
			throw new ArgException("商品已下架");
		}
		return map;
	}
	
	/**
	 * 微淘商品列表
	 * @param reqDataJson
	 * @param pageSize 
	 * @return
	 */
	public Map<String, Object> getWeiTaoProductList(JSONObject reqDataJson, Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		String sortType = reqDataJson.optString("sortType");//1综合排序 2销量降序 3价格升序 4价格降序 5有券
		Integer productType1Id = null;
		List<String> wordList = splitKeyWord(reqDataJson.optString("searchName"));
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer channelId = null;
		if(!JsonUtils.isEmpty(reqDataJson, "channelId")){
			channelId = reqDataJson.getInt("channelId");
		}
		if(!JsonUtils.isEmpty(reqDataJson, "channerlId")){
			channelId = reqDataJson.getInt("channerlId");
		}
		if(!JsonUtils.isEmpty(reqDataJson, "productType1Id")){
			productType1Id = reqDataJson.getInt("productType1Id");
		}

		Map<String, Object> paramsMap = Maps.newHashMap();
		paramsMap.put("channelId", channelId);
		paramsMap.put("wordList", wordList);
		paramsMap.put("productType1Id", productType1Id);
		paramsMap.put("sortType", sortType);
		paramsMap.put("currentPage", currentPage * pageSize);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("currentDate", DateUtil.getStandardDate(new Date()));
		List<ThirdPlatformProductInfoCustom> thirdPlatformProductInfos;
		if(channelId == null){
			thirdPlatformProductInfos = thirdPlatformProductInfoService.getThirdProductInfoList(paramsMap);
		}else{
			thirdPlatformProductInfos = thirdPlatformProductInfoService.getChannelTaoBaoProductList(paramsMap);
		}
		if(CollectionUtils.isNotEmpty(thirdPlatformProductInfos)){
			for (ThirdPlatformProductInfoCustom tp : thirdPlatformProductInfos) {
				Integer productId = tp.getProductId();
				String refId = tp.getRefId();
				String productName = tp.getTitle();
				String source = tp.getSource();
				String productPic = tp.getPic();
				Integer monthlySales = tp.getVolume() == null ? 0 : tp.getVolume();
				String couponInfo = tp.getCouponInfo() == null ? "" : tp.getCouponInfo();
				String monthlySalesStr = "";
				BigDecimal tagPrice = tp.getZkFinalPrice();
				BigDecimal couponAmount = new BigDecimal("0");
				BigDecimal mallPrice = tagPrice;
				String channelName = tp.getChannelName() == null ? "" : tp.getChannelName();
				String content = "";
				String saleTypeName = "";
				if(!StringUtil.isBlank(couponInfo)){
					couponInfo = couponInfo.substring(couponInfo.lastIndexOf("减")+1, couponInfo.length()-1);
					couponAmount = new BigDecimal(couponInfo);
					mallPrice = tagPrice.subtract(couponAmount);
					content = "券后";
				}
				if("1".equals(source)){
					saleTypeName = "淘宝价";
				}else if ("2".equals(source)) {
					saleTypeName = "天猫价";
				}
				if(monthlySales > 0){
					if(monthlySales >= 10000){
						monthlySalesStr = new BigDecimal(monthlySales + "")
								.divide(new BigDecimal("10000").setScale(2, BigDecimal.ROUND_DOWN))
								.setScale(1, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "万人已买";
					}else{
						monthlySalesStr = monthlySales + "人已买";
					}
				}
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("productId", productId);
				dataMap.put("refId", refId);
				dataMap.put("productName", productName);
				dataMap.put("source", source);
				dataMap.put("productPic", productPic);
				dataMap.put("saleNum", monthlySalesStr);
				dataMap.put("mallPrice", mallPrice);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("couponAmount", couponAmount);
				dataMap.put("content", content);
				dataMap.put("saleTypeName", saleTypeName);
				dataMap.put("channelName", channelName);
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	private List<String> splitKeyWord(String searchName) {
		if (StringUtil.isBlank(searchName)) {
			return Collections.emptyList();
		}

		List<String> wordList = Lists.newArrayList();
		String searchWord = searchName.replace(";", "").trim();
		String homoionym = keywordMomoionymService.getKeywordHomoionym(searchWord);
		if(StringUtil.isBlank(homoionym)){
			wordList = StringUtil.seg(searchWord);//数据库的商品搜索域用;号作为各个字段的分隔符，所以过滤掉;
		}else{
			wordList.add(homoionym);
		}
		if(CollectionUtils.isNotEmpty(wordList)){
			if(wordList.size() > 4){
				wordList = wordList.subList(0, 4);//只取前面4个
			}
		}
		return wordList;
	}


	public Map<String, Object> getWeiTaoProductSearchList(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		try {
			Integer pageNo = reqDataJson.getInt("pageNo"); //第几页
			String searchName = ""; //商品筛选-查询词
			String sortType = ""; //1综合排序(累计推广量) 2销量降序 3价格升序 4价格降序 5有券
			Boolean hasCoupon = null; //优惠券筛选-是否有优惠券。true表示该商品有优惠券，false或不设置表示不限
			if(!JsonUtils.isEmpty(reqDataJson, "sortType")){
				if("1".equals(reqDataJson.getString("sortType")) ) {
					sortType = "tk_total_sales_des";
				}else if("2".equals(reqDataJson.getString("sortType"))) {
					sortType = "total_sales_des";
				}else if("3".equals(reqDataJson.getString("sortType"))) {
					sortType = "price_asc";
				}else if("4".equals(reqDataJson.getString("sortType"))) {
					sortType = "price_des";
				}else if("5".equals(reqDataJson.getString("sortType"))) {
					hasCoupon = true;
				}
			}
			if(!JsonUtils.isEmpty(reqDataJson, "searchName")){
				searchName = reqDataJson.getString("searchName").trim();
				SysParamCfg sysParamCfg = sysParamCfgService.findByCode("FORBIDDEN_SEARCH_KEYWORD");
				if(sysParamCfg!=null){
					if(!StringUtil.isEmpty(sysParamCfg.getParamValue())){
						String[] keywordArray = sysParamCfg.getParamValue().split(",");
						for(String keyword:keywordArray){
							if(keyword.equals(searchName)){
								map.put("dataList", dataList);
								map.put("forbidden", "1");
								return map;
							}
						}
					}
				}
			}
			JSONArray tbkProductJSONArray = TaobaoUtil.getMaterialOptional(pageNo, pageSize, sortType, searchName, hasCoupon);
			for (int i = 0; i < tbkProductJSONArray.size(); i++) {
				JSONObject tbkProductJSON = (JSONObject) tbkProductJSONArray.get(i);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				BigDecimal zkFinalPrice = tbkProductJSON.has("zk_final_price")?new BigDecimal(tbkProductJSON.getString("zk_final_price")):new BigDecimal(0);
				BigDecimal couponAmount = tbkProductJSON.has("coupon_amount")?new BigDecimal(tbkProductJSON.getString("coupon_amount")):new BigDecimal(0);
				dataMap.put("refId", tbkProductJSON.getString("item_id"));
				dataMap.put("mallPrice", zkFinalPrice.subtract(couponAmount));
				dataMap.put("tagPrice", zkFinalPrice);
				dataMap.put("productPic", tbkProductJSON.has("pict_url")?tbkProductJSON.getString("pict_url"):"");
				dataMap.put("source", tbkProductJSON.getString("user_type").equals("0")?"1":"2"); //0淘宝 1天猫
				dataMap.put("productName", tbkProductJSON.has("title")?tbkProductJSON.getString("title"):"");
				String content = "";
				if(couponAmount != null && couponAmount.compareTo(new BigDecimal(0)) > 0) {
					content = "券后";
				}
				dataMap.put("content", content);
				String saleNum = "";
				int volume = tbkProductJSON.has("volume")?tbkProductJSON.getInt("volume"):0;
				if(volume > 0){
					if(volume >= 10000){
						saleNum = new BigDecimal(volume + "")
						.divide(new BigDecimal("10000").setScale(2, BigDecimal.ROUND_DOWN))
						.setScale(1, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + "万人已买";
					}else{
						saleNum = volume + "人已买";
					}
				}
				dataMap.put("saleNum", saleNum);
				dataMap.put("saleTypeName", "0".equals(tbkProductJSON.getString("user_type"))?"淘宝价":"天猫价");
				dataMap.put("couponAmount", couponAmount);
				
				//商品详情页面数据
				Map<String, Object> productDtlMap = new HashMap<String, Object>();
				productDtlMap.put("refId", tbkProductJSON.getString("item_id"));
				productDtlMap.put("mallPrice", tbkProductJSON.getString("zk_final_price"));
				productDtlMap.put("tagPrice", tbkProductJSON.getString("reserve_price"));
				productDtlMap.put("source", tbkProductJSON.getString("user_type").equals("0")?"1":"2"); //0淘宝 1天猫
				productDtlMap.put("productName", tbkProductJSON.has("title")?tbkProductJSON.getString("title"):"");
				String couponShareUrl = tbkProductJSON.has("coupon_share_url")?tbkProductJSON.getString("coupon_share_url"):"";
				productDtlMap.put("content", !StringUtil.isEmpty(couponShareUrl)?"券后价":"折扣价");
				productDtlMap.put("saleNum", tbkProductJSON.getInt("volume")>0?"月销："+tbkProductJSON.getInt("volume"):"");
				productDtlMap.put("saleTypeName", "0".equals(tbkProductJSON.getString("user_type"))?"淘宝价":"天猫价");
				productDtlMap.put("isCoupon", !StringUtil.isEmpty(couponShareUrl)?"1":"0");
				//优惠券
				List<Map<String, Object>> couponList = new ArrayList<Map<String, Object>>();
				if(!StringUtil.isEmpty(couponShareUrl) ) {
					Map<String, Object> couponMap = new HashMap<String, Object>();
					couponMap.put("name", tbkProductJSON.getString("coupon_amount")+"元优惠券");
					couponMap.put("money", couponAmount);
					if(!couponShareUrl.contains("http")){
						couponMap.put("couponUrl", "https:"+tbkProductJSON.getString("coupon_share_url"));
					}else{
						couponMap.put("couponUrl", tbkProductJSON.getString("coupon_share_url"));
					}
					couponMap.put("couponExpirationDateStr", "");
					couponList.add(couponMap);
				}
				productDtlMap.put("couponList", couponList);
				//封装主图
				List<Map<String, Object>> picList  = new ArrayList<Map<String, Object>>();
				Map<String, Object> imageMap = new HashMap<String, Object>();
				imageMap.put("pic", tbkProductJSON.getString("pict_url"));
				picList.add(imageMap);
				if(tbkProductJSON.has("small_images") ) {
					JSONObject smallImagesObject = tbkProductJSON.getJSONObject("small_images");
					JSONArray smallImagesArray = smallImagesObject.getJSONArray("string");
					for (int j = 0; j < smallImagesArray.size(); j++) {
						Map<String, Object> imgMap = new HashMap<String, Object>();
						imgMap.put("pic", smallImagesArray.get(j).toString());
						picList.add(imgMap);
					}
				}
				productDtlMap.put("picMapList", picList);
				List<String> productDetailPics = new ArrayList<String>();
				SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_PRICE_DESC_PIC","");
				if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue()) ) {
					productDetailPics.add(StringUtil.getPic(paramCfg.getParamValue(), ""));
				}
				productDtlMap.put("productDetailPics", productDetailPics);
				dataMap.put("productDtlMap", productDtlMap);
				dataList.add(dataMap);
			}
			map.put("dataList", dataList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ArgException("获取商品失败");
		}
		return map;
	}
	
}

package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.MchtShopDynamicCustomMapper;
import com.jf.dao.MchtShopDynamicMapper;
import com.jf.dao.MemberAttentionMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.dao.ProductMapper;
import com.jf.dao.ProductPicMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicCustomExample;
import com.jf.entity.MchtShopDynamicExample;
import com.jf.entity.MemberAttentionExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.Product;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import com.jf.entity.SysParamCfg;

@Service
@Transactional
public class MchtShopDynamicService extends BaseService<MchtShopDynamic, MchtShopDynamicExample> {

	@Autowired
	private MchtShopDynamicMapper mchtShopDynamicMapper;
	
	@Autowired
	private MchtShopDynamicCustomMapper mchtShopDynamicCustomMapper;
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductPicMapper productPicMapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private MemberAttentionMapper memberAttentionMapper;
	
	@Autowired
	public void setMchtShopDynamicMapper(MchtShopDynamicMapper mchtShopDynamicMapper) {
		this.setDao(mchtShopDynamicMapper);
		this.mchtShopDynamicMapper = mchtShopDynamicMapper;
	}
	
	public int countByCustomExample(MchtShopDynamicCustomExample example) {
		return mchtShopDynamicCustomMapper.countByCustomExample(example);
	}

	public List<MchtShopDynamicCustom> selectByCustomExample(MchtShopDynamicCustomExample example) {
		return mchtShopDynamicCustomMapper.selectByCustomExample(example);
	}

	public MchtShopDynamicCustom selectByCustomPrimaryKey(Integer id) {
		return mchtShopDynamicCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") MchtShopDynamic record, @Param("example") MchtShopDynamicCustomExample example) {
		return mchtShopDynamicCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public int updateReadCount(Integer id) {
		return mchtShopDynamicCustomMapper.updateReadCount(id);
	}
	
	public Map<String, Object> getMchtShopDynamic(JSONObject reqPRM, JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object memberId = reqDataJson.get("memberId");
		Integer mchtShopDynamicId = reqDataJson.getInt("mchtShopDynamicId");
		mchtShopDynamicCustomMapper.updateReadCount(mchtShopDynamicId); //阅读数+1
		MchtShopDynamicExample mchtShopDynamicExample = new MchtShopDynamicExample();
		MchtShopDynamicExample.Criteria mchtShopDynamicCriteria = mchtShopDynamicExample.createCriteria();
		mchtShopDynamicCriteria.andDelFlagEqualTo("0").andIdEqualTo(mchtShopDynamicId);
		if(memberId != null ) {
			mchtShopDynamicCriteria.andAuditStatusEqualTo("1");
		}
		List<MchtShopDynamic> mchtShopDynamicList = mchtShopDynamicMapper.selectByExample(mchtShopDynamicExample);
		List<Map<String, Object>> mShopDynamicListMap = new ArrayList<Map<String, Object>>();
		if(CollectionUtils.isNotEmpty(mchtShopDynamicList) ) {
			MchtShopDynamic mchtShopDynamic = mchtShopDynamicList.get(0);
			map.put("mchtShopDynamicId", mchtShopDynamic.getId());
			map.put("mchtId", mchtShopDynamic.getMchtId());
			map.put("topCover", mchtShopDynamic.getTopCover()==null?"":StringUtil.getPic(mchtShopDynamic.getTopCover(), ""));
			map.put("content", mchtShopDynamic.getContent());
			map.put("readCount", mchtShopDynamic.getReadCount()==null?"0":mchtShopDynamic.getReadCount());
			MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(mchtShopDynamic.getMchtId());
			map.put("shopName", mchtInfo.getShopName());
			String shopLogo = mchtInfo.getShopLogo();
			if(StringUtil.isBlank(shopLogo) ) {
				SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MCHT_SHOP_DEFULT_LOGO", "");
				if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
					shopLogo = StringUtil.getPic(paramCfg.getParamValue(), "");
				}
			}else{
				shopLogo = StringUtil.getPic(shopLogo, "");
			}
			map.put("shopLogo", shopLogo);
			//店铺是否收藏
			boolean isCollectuonShop = false;
			if(memberId != null ) {
				isCollectuonShop = DataDicUtil.getRemindExists(mchtShopDynamic.getMchtId(), Integer.parseInt(memberId.toString()), "3");
			}
			map.put("isCollectuonShop", isCollectuonShop);
			//绑定的商品信息
			List<Map<String, Object>> productListMap = new ArrayList<Map<String, Object>>();
			if(!StringUtil.isEmpty(mchtShopDynamic.getProductIds()) ) {
				List<Integer> productIdList = new ArrayList<Integer>();
				String productIds = mchtShopDynamic.getProductIds();
				String[] pIdStr = productIds.split(",");
				for(String productId : pIdStr) {
					productIdList.add(Integer.parseInt(productId));
				}
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("productIdList", productIdList);
				paramMap.put("orderByClause", " FIND_IN_SET(p.id, '" + productIds + "') asc");
				List<Map<String, Object>> listMap = productService.getProductDynamicList(paramMap);
				for(Map<String, Object> m : listMap) {
					Map<String, Object> productMap = new HashMap<String, Object>();
					productMap.put("productId", m.get("id"));
					productMap.put("productName", m.get("name"));
					productMap.put("productPic", m.get("pic")==null?"":StringUtil.getPic(m.get("pic").toString(), ""));	
					productMap.put("stockSum", m.get("stock_sum"));
					Product product = new Product();
					product.setId(Integer.parseInt(m.get("id").toString()));
					product.setSaleType(m.get("sale_type").toString());
					product.setMinSalePriceItemId(m.get("min_sale_price_item_id")==null?null:Integer.parseInt(m.get("min_sale_price_item_id").toString()));
					product.setMinMallPrice(m.get("min_mall_price")==null?null:new BigDecimal(m.get("min_mall_price").toString()));
					product.setMinTagPrice(m.get("min_tag_price")==null?null:new BigDecimal(m.get("min_tag_price").toString()));
					Map<String, Object> activityTypeMap = productService.getProductActivityType(product, product.getId());
					productMap.put("salePrice", activityTypeMap.get("salePrice").toString());
					productMap.put("tagPrice", activityTypeMap.get("tagPrice").toString());
					productListMap.add(productMap);
				}
			}
			map.put("productList", productListMap);
			//店铺的其他动态
			if(!"0".equals(mchtShopDynamic.getCreateBy()) && !"定时任务创建".equals(mchtShopDynamic.getRemarks())) {
				MchtShopDynamicExample mShopDynamicExample = new MchtShopDynamicExample();
				mShopDynamicExample.createCriteria().andDelFlagEqualTo("0").andAuditStatusEqualTo("1")
					.andMchtIdEqualTo(mchtShopDynamic.getMchtId()).andIdNotEqualTo(mchtShopDynamicId);
				mShopDynamicExample.setOrderByClause(" read_count desc, audit_date desc");
				mShopDynamicExample.setLimitStart(0);
				mShopDynamicExample.setLimitSize(5);
				List<MchtShopDynamic> mShopDynamicList = mchtShopDynamicMapper.selectByExample(mShopDynamicExample);
				for(MchtShopDynamic mShopDynamic : mShopDynamicList) {
					Map<String, Object> mShopDynamicMap = new HashMap<String, Object>();
					mShopDynamicMap.put("msdId", mShopDynamic.getId());
					mShopDynamicMap.put("msdContent", mShopDynamic.getContent()==null?"":mShopDynamic.getContent());
					mShopDynamicMap.put("msdReadCount", mShopDynamic.getReadCount()==null?"0":mShopDynamic.getReadCount());
					List<Integer> pIdList = new ArrayList<Integer>();
					String pIds = mShopDynamic.getProductIds();
					if(!StringUtil.isEmpty(pIds) ) {
						String[] pIdStr = pIds.split(",");
						for(String productId : pIdStr) {
							pIdList.add(Integer.parseInt(productId));
						}
						ProductPicExample pPicExample = new ProductPicExample();
						pPicExample.createCriteria().andDelFlagEqualTo("0").andIsDefaultEqualTo("1").andProductIdIn(pIdList);
						pPicExample.setOrderByClause(" FIND_IN_SET(product_id, '" + pIds + "') asc");
						pPicExample.setLimitStart(0);
						pPicExample.setLimitSize(1);
						List<ProductPic> pPicList = productPicMapper.selectByExample(pPicExample);
						if(CollectionUtils.isNotEmpty(pPicList) ) {
							mShopDynamicMap.put("msdPic", StringUtil.getPic(pPicList.get(0).getPic(), ""));
						}else {
							MchtInfo mInfo = mchtInfoMapper.selectByPrimaryKey(mchtShopDynamic.getMchtId());
							String pic = mInfo.getShopLogo();
							if(StringUtil.isBlank(pic) ) {
								SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MCHT_SHOP_DEFULT_LOGO", "");
								if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
									pic = StringUtil.getPic(paramCfg.getParamValue(), "");
								}
							}else{
								pic = StringUtil.getPic(shopLogo, "");
							}
							mShopDynamicMap.put("msdPic", StringUtil.getPic(pic, ""));
						}
					}else {
						MchtInfo mInfo = mchtInfoMapper.selectByPrimaryKey(mchtShopDynamic.getMchtId());
						String pic = mInfo.getShopLogo();
						if(StringUtil.isBlank(pic) ) {
							SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MCHT_SHOP_DEFULT_LOGO", "");
							if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
								pic = StringUtil.getPic(paramCfg.getParamValue(), "");
							}
						}else{
							pic = StringUtil.getPic(shopLogo, "");
						}
						mShopDynamicMap.put("msdPic", StringUtil.getPic(pic, ""));
					}
					mShopDynamicListMap.add(mShopDynamicMap);
				}
			}
		}
		map.put("mchtShopDynamicList", mShopDynamicListMap);
		return map;
	}
	
	public List<MchtShopDynamic> findAuditsStatusModels(Integer dynamicId) {
		MchtShopDynamicExample mchtShopDynamicExample = new MchtShopDynamicExample();
		mchtShopDynamicExample.createCriteria().andIdEqualTo(dynamicId).andAuditStatusEqualTo("1").andDelFlagEqualTo("0");
		return selectByExample(mchtShopDynamicExample);
	}
	
	public Map<String, Object> getMchtShopDynamicList(JSONObject reqPRM, JSONObject reqDataJson) {
		String type = reqDataJson.getString("type");// 1推荐  2关注
		Integer memberId = reqDataJson.getInt("memberId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = reqDataJson.getInt("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("memberId", memberId);
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		if("1".equals(type) ) {
			resultList = mchtShopDynamicCustomMapper.getRecommendList(paramsMap);
		}else {
			resultList = mchtShopDynamicCustomMapper.getAttentionList(paramsMap);
		}
		for(Map<String, Object> m : resultList) {
			Map<String, Object> objectMap = new HashMap<String, Object>();
			//STORY #1886
			String coverPic = "";
			objectMap.put("type", m.get("type"));// 1店铺动态 2好友动态
			objectMap.put("id", m.get("id"));
			objectMap.put("mchtShopDynamicId", m.get("mcht_shop_dynamic_id"));
			objectMap.put("content", m.get("content")==null?"":m.get("content"));
			objectMap.put("publishDate", m.get("publish_date")==null?"":DateUtil.getFormatDate((Date)m.get("publish_date"), "yyyy-MM-dd HH:mm"));
			String forwardCount = "转发";
			if(m.get("forward_count") != null && !m.get("forward_count").toString().equals("0") ) {
				int fCount = Integer.parseInt(m.get("forward_count").toString());
				forwardCount = formatNumber(fCount);
			}
			String likeCount = "赞";
			if(m.get("forward_count") != null && !m.get("like_count").toString().equals("0") ) {
				int fColikeCountunt = Integer.parseInt(m.get("like_count").toString());
				likeCount = formatNumber(fColikeCountunt);
			}
			objectMap.put("forwardCount", forwardCount);
			objectMap.put("likeCount", likeCount);
			objectMap.put("mchtId", m.get("mcht_id"));
			objectMap.put("shopName", m.get("shop_name")==null?"":m.get("shop_name"));
			objectMap.put("nick", m.get("nick")==null?"":StringUtil.getNickRule(m.get("nick").toString(), ""));
			String pic = m.get("pic")==null?"":StringUtil.getPic(m.get("pic").toString(), "");
			String productIds = m.get("product_ids")==null?"":m.get("product_ids").toString();
			List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
			if(!StringUtil.isEmpty(productIds) ) {
				List<Integer> productIdList = new ArrayList<Integer>();
				String[] pIdStr = productIds.split(",");
				for(String productId : pIdStr) {
					productIdList.add(Integer.parseInt(productId));
				}
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("productIdList", productIdList);
				paramMap.put("orderByClause", " FIND_IN_SET(p.id, '" + productIds + "') asc");
				List<Map<String, Object>> listMap = productService.getProductDynamicList(paramMap);
				if(CollectionUtils.isNotEmpty(listMap) ) {
					for (int i = 0; i < listMap.size(); i++) {
						Map<String, Object> productMap = new HashMap<String, Object>();
						productMap.put("productId", listMap.get(i).get("id"));
						productMap.put("productPic", listMap.get(i).get("pic")==null?"":StringUtil.getPic(listMap.get(i).get("pic").toString(), ""));	
						productList.add(productMap);
						//STORY #1886 
						if(i == 0){
							coverPic = productMap.get("productPic").toString();
						}
					}
				}
			}
			objectMap.put("productList", productList);
			if(StringUtil.isEmpty(pic) && "1".equals(m.get("type")) ) {
				SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MCHT_SHOP_DEFULT_LOGO", "");
				if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
					pic = StringUtil.getPic(paramCfg.getParamValue(), "");
				}
			}
			objectMap.put("pic", pic);
			objectMap.put("likeStatus", m.get("like_status"));
			objectMap.put("remindId", m.get("remind_id"));
			int readCount = 0;
			if(m.get("read_count") != null) {
				readCount = Integer.parseInt(m.get("read_count").toString());
			}
			objectMap.put("readCount",readCount);
			String source = "";
			if(m.get("source")!=null){
				source = m.get("source").toString();
			}
			objectMap.put("source",source);
			String attentionMemberId = "";
			if(m.get("attention_member_id")!=null){
				attentionMemberId = m.get("attention_member_id").toString();
			}
			objectMap.put("friendMemberId",attentionMemberId);
			if(StringUtil.isEmpty(coverPic)){
				coverPic = StringUtil.getPic(m.get("top_cover")==null?"":m.get("top_cover").toString(), "");
			}
			objectMap.put("coverPic", coverPic);
			dataList.add(objectMap);
		}
		map.put("dataList", dataList);
		return map;
	}
	
	public static String formatNumber(int param) {
		String str = "";
		if(param > 9999 && param <= 99999 ) {
			if(param%10000 == 0) {
				str = param/10000+"W+";
			}else {
				BigDecimal bd = new BigDecimal(param);
				str = bd.divide(new BigDecimal(10000)).setScale(1, BigDecimal.ROUND_DOWN)+"W+";
			}
		}else if(param > 99999 ){
			str = 10+"W+";
		}else {
			str = param+"";
		}
		return str;
	}
	
	public Map<String, Object> getMemberDynamicDtl(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		Integer friendMemberId = reqDataJson.getInt("friendMemberId");
		Map<String, Object> map = new HashMap<String, Object>();
		MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(friendMemberId);
		map.put("memberPic", memberInfo.getPic()==null?"":StringUtil.getPic(memberInfo.getPic(), ""));
		map.put("memberNick", StringUtil.getNickRule(memberInfo.getNick(), ""));
		map.put("sexType", memberInfo.getSexType()==null?"":memberInfo.getSexType());
		map.put("isSvip", memberInfoService.getIsSvip(memberInfo, friendMemberId));
		MemberAttentionExample memberAttentionExample = new MemberAttentionExample();
		memberAttentionExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(friendMemberId);
		int attentionCount = memberAttentionMapper.countByExample(memberAttentionExample);
		map.put("attentionCount", formatNumber(attentionCount)); //关注数
		MemberAttentionExample memberFansExample = new MemberAttentionExample();
		memberFansExample.createCriteria().andDelFlagEqualTo("0").andAttentionMemberIdEqualTo(friendMemberId);
		int fansCount = memberAttentionMapper.countByExample(memberFansExample);
		map.put("fansCount", formatNumber(fansCount)); //粉丝数
		String attentionButton = "0";
		if(memberId.intValue() != friendMemberId.intValue() ) {
			attentionButton = "1";
		}
		map.put("attentionButton", attentionButton); //0.隐藏  1.展示
		String attentionStatus = "0";
		MemberAttentionExample mAttentionExample = new MemberAttentionExample();
		mAttentionExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andAttentionMemberIdEqualTo(friendMemberId);
		int mAttentionCount = memberAttentionMapper.countByExample(mAttentionExample);
		if(mAttentionCount > 0) {
			attentionStatus = "1";
		}
		map.put("attentionStatus", attentionStatus); //0.未关注  1.已关注
		return map;
	}
	
	public Map<String, Object> getMemberDynamicList(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		Integer friendMemberId = reqDataJson.getInt("friendMemberId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = reqDataJson.getInt("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("memberId", memberId);
		paramsMap.put("friendMemberId", friendMemberId);
		resultList = mchtShopDynamicCustomMapper.getMemberDynamicList(paramsMap);
		for(Map<String, Object> m : resultList) {
			Map<String, Object> objectMap = new HashMap<String, Object>();
			//STORY #1886
			String coverPic = "";
			objectMap.put("id", m.get("member_dynamic_id"));
			objectMap.put("mchtShopDynamicId", m.get("mcht_shop_dynamic_id"));
			objectMap.put("content", m.get("content")==null?"":m.get("content"));
			objectMap.put("publishDate", m.get("publish_date")==null?"":DateUtil.getFormatDate((Date)m.get("publish_date"), "yyyy-MM-dd HH:mm"));
			String forwardCount = "转发";
			if(m.get("forward_count") != null && !m.get("forward_count").toString().equals("0") ) {
				int fCount = Integer.parseInt(m.get("forward_count").toString());
				forwardCount = formatNumber(fCount);
			}
			String likeCount = "赞";
			if(m.get("forward_count") != null && !m.get("like_count").toString().equals("0") ) {
				int fColikeCountunt = Integer.parseInt(m.get("like_count").toString());
				likeCount = formatNumber(fColikeCountunt);
			}
			objectMap.put("forwardCount", forwardCount);
			objectMap.put("likeCount", likeCount);
			objectMap.put("mchtId", m.get("mcht_id"));
			objectMap.put("shopName", m.get("shop_name")==null?"":m.get("shop_name"));
			objectMap.put("nick", m.get("nick")==null?"":StringUtil.getNickRule(m.get("nick").toString(), ""));
			String pic = m.get("pic")==null?"":m.get("pic").toString();
			String productIds = m.get("product_ids")==null?"":m.get("product_ids").toString();
			List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
			if(!StringUtil.isEmpty(productIds) ) {
				List<Integer> productIdList = new ArrayList<Integer>();
				String[] pIdStr = productIds.split(",");
				for(String productId : pIdStr) {
					productIdList.add(Integer.parseInt(productId));
				}
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("productIdList", productIdList);
				paramMap.put("orderByClause", " FIND_IN_SET(p.id, '" + productIds + "') asc");
				List<Map<String, Object>> listMap = productService.getProductDynamicList(paramMap);
				if(CollectionUtils.isNotEmpty(listMap) ) {
					for (int i = 0; i < listMap.size(); i++) {
						Map<String, Object> productMap = new HashMap<String, Object>();
						productMap.put("productId", listMap.get(i).get("id"));
						productMap.put("productPic", listMap.get(i).get("pic")==null?"":StringUtil.getPic(listMap.get(i).get("pic").toString(), ""));	
						productList.add(productMap);
						//STORY #1886 
						if(i == 0){
							coverPic = productMap.get("productPic").toString();
						}
					}
				}
			}
			objectMap.put("productList", productList);
			if(StringUtil.isEmpty(pic) && "1".equals(m.get("type")) ) {
				SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MCHT_SHOP_DEFULT_LOGO", "");
				if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
					pic = paramCfg.getParamValue();
				}
			}
			objectMap.put("pic", StringUtil.getPic(pic, ""));
			objectMap.put("likeStatus", m.get("like_status"));
			int readCount = 0;
			if(m.get("read_count") != null) {
				readCount = Integer.parseInt(m.get("read_count").toString());
			}
			objectMap.put("readCount",readCount);
			if(StringUtil.isEmpty(coverPic)){
				coverPic = StringUtil.getPic(m.get("top_cover").toString(), "");
			}
			objectMap.put("coverPic", coverPic);
			dataList.add(objectMap);
		}
		map.put("dataList", dataList);
		return map;
	}
	
	
}

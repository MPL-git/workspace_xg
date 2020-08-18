package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberAttentionMapper;
import com.jf.dao.MemberDynamicCustomMapper;
import com.jf.dao.MemberDynamicMapper;
import com.jf.dao.ProductPicMapper;
import com.jf.entity.MemberAttention;
import com.jf.entity.MemberAttentionExample;
import com.jf.entity.MemberDynamic;
import com.jf.entity.MemberDynamicExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.Product;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;

import net.sf.json.JSONObject;

@Service
@Transactional
public class MemberDynamicService extends BaseService<MemberDynamic, MemberDynamicExample> {
	@Autowired
	private MemberDynamicMapper memberDynamicMapper;
	@Autowired
	private MemberDynamicCustomMapper memberDynamicCustomMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductPicMapper productPicMapper;
	
	@Autowired
	private MemberAttentionMapper memberAttentionMapper;
	@Autowired
	private MemberNovaRecordService memberNovaRecordService;
	
	@Autowired
	public void setMemberDynamicMapper(MemberDynamicMapper memberDynamicMapper) {
		this.setDao(memberDynamicMapper);
		this.memberDynamicMapper = memberDynamicMapper;
	}
	/**
	 * 签约期发布的动态数量(当天多条也算一条)
	 * @param openType 
	 */
	public int getMemberReleaseDynamicsCount(MemberInfo memberInfo, Integer memberId, String openType) {
		Integer count = 0;
		if(memberInfo == null){
			memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		}
		MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberInfo.getId(),openType);
		if(memberNovaRecord != null){
			Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
			Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("beginDate", novaProjectBeginDate);
			paramsMap.put("endDate", new Date());
			paramsMap.put("memberId", memberInfo.getId());
			count = memberDynamicCustomMapper.getMemberReleaseDynamicsCount(paramsMap);
		}
		return count;
	}
	
	public int getSameDayDynamicsCount(Integer memberId) {
		Date currentDate = new Date();
		Date beginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date endDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		MemberDynamicExample memberDynamicExample = new MemberDynamicExample();
		memberDynamicExample.createCriteria().andMemberIdEqualTo(memberId).andCreateDateLessThanOrEqualTo(endDate).andCreateDateGreaterThanOrEqualTo(beginDate).andDelFlagEqualTo("0");
		return countByExample(memberDynamicExample);
	}
	
	public Map<String, Object> getMemberDynamic(JSONObject reqPRM, JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object memberId = reqDataJson.get("memberId");
		Integer memberDynamicId = reqDataJson.getInt("memberDynamicId");
		memberDynamicCustomMapper.updateReadCount(memberDynamicId); //阅读数+1
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("id", memberDynamicId);
		if(memberId != null ) {
			p.put("auditStatus", "1");
		}
		Map<String, Object> memberDynamicMap = memberDynamicCustomMapper.getMemberDynamic(p);
		List<Map<String, Object>> memberDynamicListMap = new ArrayList<Map<String, Object>>();
		if(memberDynamicMap != null ) {
			map.put("friendMemberId", memberDynamicMap.get("member_id"));
			map.put("mchtShopDynamicId", memberDynamicMap.get("mcht_shop_dynamic_id"));
			map.put("readCount", memberDynamicMap.get("read_count"));
			map.put("pic", memberDynamicMap.get("pic")!=null?StringUtil.getPic(memberDynamicMap.get("pic").toString(), ""):"");
			map.put("topCover", memberDynamicMap.get("top_cover")!=null?StringUtil.getPic(memberDynamicMap.get("top_cover").toString(), ""):"");
			map.put("nick", StringUtil.getNickRule(memberDynamicMap.get("nick")==null ?"":memberDynamicMap.get("nick").toString(), ""));
			map.put("shopName", memberDynamicMap.get("shop_name"));
			map.put("mchtId", memberDynamicMap.get("mcht_id"));
			map.put("content", memberDynamicMap.get("content"));
			//关注信息
			String attentionButton = "0";
			String attentionStatus = "0";
			if(memberId != null ) {
				if(!memberId.toString().equals(memberDynamicMap.get("member_id").toString()) ) {
					attentionButton = "1";
				}
				MemberAttentionExample memberAttentionExample = new MemberAttentionExample();
				memberAttentionExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(Integer.parseInt(memberId.toString())).andAttentionMemberIdEqualTo(Integer.parseInt(memberDynamicMap.get("member_id").toString()));
				List<MemberAttention> mchtShopDynamicList = memberAttentionMapper.selectByExample(memberAttentionExample);
				if(CollectionUtils.isNotEmpty(mchtShopDynamicList) ) {
					attentionStatus = "1";
				}
			}
			map.put("attentionButton", attentionButton);
			map.put("attentionStatus", attentionStatus);
			//绑定的商品信息
			List<Map<String, Object>> productListMap = new ArrayList<Map<String, Object>>();
			if(memberDynamicMap.get("product_ids") != null ) {
				List<Integer> productIdList = new ArrayList<Integer>();
				String productIds = memberDynamicMap.get("product_ids").toString();
				String[] pIdStr = productIds.split(",");
				for(String productId : pIdStr) {
					productIdList.add(Integer.parseInt(productId));
				}
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("productIdList", productIdList);
				paramMap.put("orderByClause", " FIND_IN_SET(p.id, '" + memberDynamicMap.get("product_ids") + "') asc");
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
			//好友的其他动态
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("memberId", memberDynamicMap.get("member_id"));
			pMap.put("memberDynamicId", memberDynamicId);
			pMap.put("orderByClause", " md.read_count desc, md.create_date desc");
			pMap.put("limitStart", 0);
			pMap.put("limitSize", 5);
			List<Map<String, Object>> memberDynamicCustomList = memberDynamicCustomMapper.getMchtShopDynamic(pMap);
			for(Map<String, Object> m : memberDynamicCustomList) {
				Map<String, Object> mDynamicMap = new HashMap<String, Object>();
				mDynamicMap.put("mdId", m.get("member_dynamic_id"));
				mDynamicMap.put("msdContent", m.get("content")==null?"":m.get("content"));
				mDynamicMap.put("msdReadCount", m.get("read_count")==null?"0":m.get("read_count"));
				List<Integer> pIdList = new ArrayList<Integer>();
				String pIds = m.get("product_ids")==null?"":m.get("product_ids").toString();
				if(!StringUtil.isEmpty(pIds) ) {
					String[] pIdStr = pIds.split(",");
					for(String productId : pIdStr) {
						pIdList.add(Integer.parseInt(productId));
					}
					ProductPicExample pPicExample = new ProductPicExample();
					pPicExample.createCriteria().andDelFlagEqualTo("0").andIsDefaultEqualTo("1").andProductIdIn(pIdList);
					pPicExample.setOrderByClause(" FIND_IN_SET(product_id, '" + pIdList + "') asc");
					pPicExample.setLimitStart(0);
					pPicExample.setLimitSize(1);
					List<ProductPic> pPicList = productPicMapper.selectByExample(pPicExample);
					if(CollectionUtils.isEmpty(pPicList) ) {
						mDynamicMap.put("msdPic", memberDynamicMap.get("pic")==null?"":StringUtil.getPic(memberDynamicMap.get("pic").toString(), ""));
					}else {
						mDynamicMap.put("msdPic", StringUtil.getPic(pPicList.get(0).getPic(), ""));
					}
				}else {
					mDynamicMap.put("msdPic", memberDynamicMap.get("pic")==null?"":StringUtil.getPic(memberDynamicMap.get("pic").toString(), ""));
				}
				memberDynamicListMap.add(mDynamicMap);
			}
		}
		map.put("memberDynamicList", memberDynamicListMap);
		return map;
	}
	
}

package com.jf.service;

import com.google.common.collect.Lists;
import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.dao.IndexPopupAdCustomMapper;
import com.jf.dao.IndexPopupAdMapper;
import com.jf.entity.IndexPopupAd;
import com.jf.entity.IndexPopupAdExample;
import com.jf.entity.dto.AdDTO;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IndexPopupAdService extends BaseService<IndexPopupAd, IndexPopupAdExample> {
	@Autowired
	private IndexPopupAdMapper indexPopupAdMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired
	private IndexPopupAdCustomMapper indexPopupAdCustomMapper;

	@Autowired
	public void setIndexPopupAdMapper(IndexPopupAdMapper indexPopupAdMapper) {
		this.setDao(indexPopupAdMapper);
		this.indexPopupAdMapper = indexPopupAdMapper;
	}
	

	/**
	 * 首页弹窗广告
	 * @param reqPRM
	 * @param reqDataJson
	 * @return
	 */
	public Map<String, Object> getIndexPopupAds(JSONObject reqPRM, JSONObject reqDataJson) {
		//是否领取过红包
		boolean isReceivedRed = reqDataJson.getBoolean("isReceivedRed");
		int memberId = reqDataJson.optInt("memberId");
		List<Map<String, Object>> dataList = new ArrayList<>();
		if(isReceivedRed){
			List<IndexPopupAd> popupAds = findMemberIndexPopAd(memberId);
			if(CollectionUtils.isNotEmpty(popupAds)){
				dataList = buildProupAd(memberId, popupAds);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("adList", dataList);
		map.put("currentDate", new Date().getTime());
		return map;
	}
	
	/**
	 * 组装首页弹窗广告内容
	 */
	public List<Map<String, Object>> buildProupAd(int memberId, List<IndexPopupAd> popupAds) {
		List<Map<String, Object>> dataList = new ArrayList<>();

		if(CollectionUtils.isNotEmpty(popupAds)){
			for (IndexPopupAd model : popupAds) {
				if(memberId > 0 && model.getLinkType().equals("29")){
					// 优惠券如果该用户已领取则不弹窗
					String[] couponIds = model.getLinkContent().split(",");
					boolean isReceived = false;
					for(String couponId : couponIds){
						if(memberCouponService.findMemberCouponByCouponId(memberId, Integer.valueOf(couponId)).size() > 0){
							isReceived = true;
							break;
						}
					}

					if(isReceived)	continue;
				}
				AdDTO adDTO = new AdDTO();
				adDTO.setAdId(model.getId()+"");
				adDTO.setLinkType(model.getLinkType());	//1.会场ID 2.特卖ID 3.商品ID 4..自建页面ID 5.栏目 6.一级分类 7.URL链接
				adDTO.setLinkValue(model.getLinkContent());
				adDTO.setPic(model.getPic());

				Map<String, Object> dataMap = new HashMap<>();
				dataMap = commonService.buildAdMap(adDTO);
				dataMap.put("prpupCountType", model.getPopupCount());
				dataMap.put("count", model.getDay());
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	public List<IndexPopupAd> getModes() {
		Date currentDate = new Date();
		IndexPopupAdExample indexPopupAdExample = new IndexPopupAdExample();
		indexPopupAdExample.createCriteria().andStatusEqualTo("1").andUpDateLessThanOrEqualTo(currentDate).andDownDateGreaterThanOrEqualTo(currentDate).andDelFlagEqualTo("0");
		indexPopupAdExample.setLimitStart(0);
		indexPopupAdExample.setLimitSize(1);
		indexPopupAdExample.setOrderByClause("up_date desc,id desc");
		return selectByExample(indexPopupAdExample);
	}

	public List<IndexPopupAd> findMemberIndexPopAd(int memberId) {
		List<IndexPopupAd> list = Lists.newArrayList();
		if (memberId > 0) {
			list = indexPopupAdCustomMapper.findMemberIndexPopupAd(memberId);
		}
		if (CollectionUtil.isEmpty(list)) {
			Date now = new Date();
			IndexPopupAdExample example = new IndexPopupAdExample();
			example.createCriteria().andStatusEqualTo(StateConst.ONLINE).andDelFlagEqualTo(StateConst.FALSE)
					.andUpDateLessThanOrEqualTo(now)
					.andDownDateGreaterThanOrEqualTo(now)
					.andSelectGroupIsNull();
			example.setLimitSize(1);
			example.setOrderByClause("up_date desc,id desc");
			list = selectByExample(example);
		}
		return list;
	}

}

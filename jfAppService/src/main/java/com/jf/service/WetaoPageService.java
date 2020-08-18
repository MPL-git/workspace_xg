package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.WetaoPageMapper;
import com.jf.entity.WetaoPage;
import com.jf.entity.WetaoPageExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WetaoPageService extends BaseService<WetaoPage, WetaoPageExample> {
	@Autowired
	private WetaoPageMapper wetaoPageMapper;
	@Autowired
	public void setWetaoPageMapper(WetaoPageMapper wetaoPageMapper) {
		this.setDao(wetaoPageMapper);
		this.wetaoPageMapper = wetaoPageMapper;
	}
	
	public Map<String, Object> getWeiTaoTopCategory(Integer productType1Id) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<WetaoPage> wetaoPages = findModels(productType1Id,null);
		if(CollectionUtils.isNotEmpty(wetaoPages)){
			for (WetaoPage wetaoPage : wetaoPages) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("wetaoPageId", wetaoPage.getId());
				dataMap.put("productType1Id", wetaoPage.getProductTypeId() == null ? "" : wetaoPage.getProductTypeId());
				dataMap.put("name", wetaoPage.getName());
				dataMap.put("decorateInfoId", wetaoPage.getDecorateInfoId() == null ? "" : wetaoPage.getDecorateInfoId());
				dataList.add(dataMap);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		return map;
	}

	public List<WetaoPage> findModels(Integer productType1Id,Integer id) {
		WetaoPageExample wetaoPageExample = new WetaoPageExample();
		WetaoPageExample.Criteria criteria = wetaoPageExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
		if(productType1Id != null){
			criteria.andProductTypeIdEqualTo(productType1Id);
		}
		if(id != null){
			criteria.andIdEqualTo(id);
		}
		wetaoPageExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		return selectByExample(wetaoPageExample);
	}
}

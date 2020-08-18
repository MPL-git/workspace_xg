package com.jf.service;

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

import com.jf.common.utils.Config;
import com.jf.common.utils.StringUtil;
import com.jf.dao.BrandTeamTypeAdInfoMapper;
import com.jf.entity.BrandTeamTypeAdInfo;
import com.jf.entity.BrandTeamTypeAdInfoExample;

@Service
@Transactional
public class BrandTeamTypeAdInfoService extends BaseService<BrandTeamTypeAdInfo, BrandTeamTypeAdInfoExample> {
	@Autowired
	private BrandTeamTypeAdInfoMapper brandTeamTypeAdInfoMapper;
	@Autowired
	public void setBrandTeamTypeAdInfoMapper(BrandTeamTypeAdInfoMapper brandTeamTypeAdInfoMapper) {
		this.setDao(brandTeamTypeAdInfoMapper);
		this.brandTeamTypeAdInfoMapper = brandTeamTypeAdInfoMapper;
	}
	public List<BrandTeamTypeAdInfo> findModelsByBrandTypeId(Integer brandTeamTypeId) {
		Date currentDate = new Date();
		BrandTeamTypeAdInfoExample brandTeamTypeAdInfoExample = new BrandTeamTypeAdInfoExample();
		brandTeamTypeAdInfoExample.createCriteria().andStatusEqualTo("1").andBrandTeamTypeIdEqualTo(brandTeamTypeId).andDelFlagEqualTo("0")
		.andUpDateLessThanOrEqualTo(currentDate).andDownDateGreaterThanOrEqualTo(currentDate);
		brandTeamTypeAdInfoExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		return selectByExample(brandTeamTypeAdInfoExample);
	}
	public List<Map<String, Object>> getBrandAdList(Integer brandTeamTypeId) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<BrandTeamTypeAdInfo> typeAdInfos = findModelsByBrandTypeId(brandTeamTypeId);
		if(CollectionUtils.isNotEmpty(typeAdInfos)){
			for (BrandTeamTypeAdInfo model : typeAdInfos) {
				Map<String, Object> dataMap = new HashMap<>();
				String linkType = model.getLinkType();
				String linkValue = model.getLinkValue();
				if("7".equals(linkType)){
					linkType = "4";
					linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?pageid="+linkValue;
				}
				dataMap.put("id", model.getId());
				dataMap.put("pic", StringUtil.getPic(model.getPic(), ""));
				dataMap.put("linkType", linkType);
				dataMap.put("linkValue", linkValue);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	
}

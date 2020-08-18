package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.BrandTeamTypeAdInfoMapper;
import com.jf.entity.BrandTeamTypeAdInfo;
import com.jf.entity.BrandTeamTypeAdInfoExample;
import com.jf.entity.dto.AdDTO;
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
public class BrandTeamTypeAdInfoService extends BaseService<BrandTeamTypeAdInfo, BrandTeamTypeAdInfoExample> {
	@Autowired
	private BrandTeamTypeAdInfoMapper brandTeamTypeAdInfoMapper;
	@Autowired
	private CommonService commonService;
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
				AdDTO adDTO = new AdDTO();
				adDTO.setAdId(model.getId()+"");
				adDTO.setLinkType(linkType);
				adDTO.setLinkValue(linkValue);
				adDTO.setPic(model.getPic());
				dataMap = commonService.buildAdMap(adDTO);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	
}

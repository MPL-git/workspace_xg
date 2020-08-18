package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SourceProductTypeBannerMapper;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeBanner;
import com.jf.entity.SourceProductTypeBannerExample;
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
public class SourceProductTypeBannerService extends BaseService<SourceProductTypeBanner, SourceProductTypeBannerExample> {
	@Autowired
	private SourceProductTypeBannerMapper sourceProductTypeBannerMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private BannerRecommendProductService bannerRecommendProductService;
	@Autowired
	public void setSourceProductTypeBannerMapper(SourceProductTypeBannerMapper sourceProductTypeBannerMapper) {
		this.setDao(sourceProductTypeBannerMapper);
		this.sourceProductTypeBannerMapper = sourceProductTypeBannerMapper;
	}
	
	public Map<String, Object> getBannerAdList(SourceProductType sourceProductType) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> productList = new ArrayList<>();
		List<SourceProductTypeBanner> sourceProductTypeBanners = findModelBySourceTypeId(sourceProductType.getId());
		if(CollectionUtils.isNotEmpty(sourceProductTypeBanners)){
			for (SourceProductTypeBanner sourceProductTypeBanner : sourceProductTypeBanners) {
				AdDTO adDTO = new AdDTO();
				adDTO.setAdId(sourceProductTypeBanner.getId()+"");
				adDTO.setLinkType(sourceProductTypeBanner.getLinkType());
				adDTO.setLinkValue(sourceProductTypeBanner.getLinkValue());
				adDTO.setPic(sourceProductTypeBanner.getPic());
				Map<String, Object> dataMap = commonService.buildAdMap(adDTO);
				list.add(dataMap);
				productList = bannerRecommendProductService.getBannerProducts(sourceProductTypeBanner.getId(),sourceProductType);
				break;
			}
		}
		map.put("bannerList", list);
		map.put("productList", productList);
		return map;
	}

	public List<SourceProductTypeBanner> findModelBySourceTypeId(Integer sourceTypeId) {
		SourceProductTypeBannerExample sourceProductTypeBannerExample = new SourceProductTypeBannerExample();
		sourceProductTypeBannerExample.createCriteria().andSourceProductTypeIdEqualTo(sourceTypeId).andStatusEqualTo("1").andUpDateLessThanOrEqualTo(new Date()).andDelFlagEqualTo("0");
		sourceProductTypeBannerExample.setOrderByClause("up_date desc,id desc");
		sourceProductTypeBannerExample.setLimitStart(0);
		sourceProductTypeBannerExample.setLimitSize(1);
		return selectByExample(sourceProductTypeBannerExample);
	}
}

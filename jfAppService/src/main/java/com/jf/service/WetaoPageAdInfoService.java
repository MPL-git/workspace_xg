package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.Config;
import com.jf.dao.WetaoPageAdInfoMapper;
import com.jf.entity.WetaoPage;
import com.jf.entity.WetaoPageAdInfo;
import com.jf.entity.WetaoPageAdInfoExample;
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
public class WetaoPageAdInfoService extends BaseService<WetaoPageAdInfo, WetaoPageAdInfoExample> {
	@Autowired
	private WetaoPageAdInfoMapper wetaoPageAdInfoMapper;
	@Autowired
	private WetaoPageService wetaoPageService;
	@Autowired
	private CommonService commonService;
	@Autowired
	public void setWetaoPageAdInfoMapper(WetaoPageAdInfoMapper wetaoPageAdInfoMapper) {
		this.setDao(wetaoPageAdInfoMapper);
		this.wetaoPageAdInfoMapper = wetaoPageAdInfoMapper;
	}
	public Map<String, Object> getWeiTaoAdInfo(JSONObject reqPRM, JSONObject reqDataJson) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		Integer wetaoPageId = reqDataJson.getInt("wetaoPageId");
		String wetaoDecorateUrl = "";
		List<WetaoPage> wetaoPages = wetaoPageService.findModels(null, wetaoPageId);
		if(CollectionUtils.isNotEmpty(wetaoPages)){
			if(wetaoPages.get(0).getDecorateInfoId() != null){
				wetaoDecorateUrl = Config.getProperty("mUrl")+"/xgbuy/views/activity/nest/decorate/brand_decorate.html?infoId="+wetaoPages.get(0).getDecorateInfoId();
			}
		}
		List<WetaoPageAdInfo> adInfos = findModelByWeTaoId(wetaoPageId);
		if(CollectionUtils.isNotEmpty(wetaoPages)){
			for (WetaoPageAdInfo wetaoPageAdInfo : adInfos) {
				Map<String, Object> dataMap = new HashMap<>();
				String linkType = wetaoPageAdInfo.getLinkType();
				String linkValue = wetaoPageAdInfo.getLinkValue();
				AdDTO adDTO = new AdDTO();
				adDTO.setAdId(wetaoPageAdInfo.getId()+"");
				adDTO.setLinkType(linkType);
				adDTO.setLinkValue(linkValue);
				adDTO.setPic(wetaoPageAdInfo.getPic());
				dataMap = commonService.buildAdMap(adDTO);
				dataMap.put("lingValue", dataMap.get("linkValue"));//兼容旧版本处理
				dataList.add(dataMap);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		map.put("wetaoDecorateUrl", wetaoDecorateUrl);
		return map;
	}
	
	public List<WetaoPageAdInfo> findModelByWeTaoId(Integer wetaoPageId) {
		Date currentDate = new Date();
		WetaoPageAdInfoExample wetaoPageAdInfoExample = new WetaoPageAdInfoExample();
		wetaoPageAdInfoExample.createCriteria().andWetaoPageIdEqualTo(wetaoPageId).andStatusEqualTo("1")
								.andUpDateLessThanOrEqualTo(currentDate).andDownDateGreaterThanOrEqualTo(currentDate)
								.andDelFlagEqualTo("0");
		wetaoPageAdInfoExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		return selectByExample(wetaoPageAdInfoExample);
	}
	
	
}

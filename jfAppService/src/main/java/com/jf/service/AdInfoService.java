package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.Config;
import com.jf.common.utils.StringUtil;
import com.jf.dao.AdInfoCustomMapper;
import com.jf.dao.AdInfoMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoExample;
import com.jf.entity.dto.AdDTO;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月25日 上午9:09:55 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class AdInfoService extends BaseService<AdInfo, AdInfoExample> {
	
	@Autowired
	private AdInfoMapper adInfoMapper;
	@Autowired
	private AdInfoCustomMapper adInfoCustomMapper;
	@Autowired
	private CommonService commonService;

	@Autowired
	public void setAdInfoMapper(AdInfoMapper adInfoMapper) {
		super.setDao(adInfoMapper);
		this.adInfoMapper = adInfoMapper;
	}
	
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	

	public Map<String, Object> getAdLocation(AdInfo adInfo, JSONObject reqPRM) {
		String system = reqPRM.getString("system");
		Integer version = reqPRM.getInt("version");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Integer id = adInfo.getId();
		String pic = adInfo.getPic();
		String linkType = adInfo.getLinkType();
		Integer linkId = adInfo.getLinkId();
		String linkUrl = adInfo.getLinkUrl();
		String areaUrl = "";
		String activityAreaName = "";
		String activityAreaType = "";
		if(Const.AD_ACTIVITY_AREA.equals(linkType)){
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(linkId);
			if(!StringUtil.isBlank(activityArea.getTempletSuffix())){
				areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&version=1.0&memberId=";
			}
			if(!StringUtil.isBlank(activityArea.getName())){
				activityAreaName = activityArea.getName();
			}
			if(!StringUtil.isBlank(activityArea.getType())){
				activityAreaType = activityArea.getType();
			}
		}else if(Const.AD_CUSTOMER_PAGE.equals(linkType)){
			linkType = Const.AD_LINKURL;
			linkUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?pageid="+linkId;
		}
		
		AdDTO adDTO = new AdDTO();
		adDTO.setAdId(id+"");
		adDTO.setLinkType(linkType);
		adDTO.setLinkId(linkId);
		adDTO.setLinkUrl(linkUrl);
		adDTO.setPic(pic);
		adDTO.setType("2");
		dataMap = commonService.buildAdMap(adDTO);
		
		
		if((Const.ANDROID.equals(system) && version <= 57) || (Const.IOS.equals(system) && version <= 58)){
			//android<=57,ios<=58(废弃)
			dataMap.put("id", id);
			dataMap.put("pic", StringUtil.getPic(pic, ""));
			dataMap.put("linkType", linkType);
			dataMap.put("linkId", linkId);
			dataMap.put("linkUrl", linkUrl);
			dataMap.put("areaUrl", areaUrl);
			dataMap.put("activityAreaName", activityAreaName);
			dataMap.put("activityAreaType", activityAreaType);
		}
		return dataMap;
	}

	public List<AdInfo> findListQuery(QueryObject queryObject) {
		return adInfoMapper.selectByExample(builderQuery(queryObject));
	}

	public AdInfoExample builderQuery(QueryObject queryObject) {
		AdInfoExample example = new AdInfoExample();
		AdInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("type") != null){
			criteria.andTypeEqualTo(queryObject.getQueryToStr("type"));
		}
		if(queryObject.getQuery("catalog") != null){
			criteria.andCatalogEqualTo(queryObject.getQueryToStr("catalog"));
		}
		if(queryObject.getQuery("position") != null){
			criteria.andPositionEqualTo(queryObject.getQueryToStr("position"));
		}
		if(queryObject.getQuery("positionList") != null){
			List<String> positionList = queryObject.getQuery("positionList");
			criteria.andPositionIn(positionList);
		}
		if(queryObject.getQuery("autoUpDateLessThanOrEqual") != null){
			criteria.andAutoUpDateLessThanOrEqualTo(queryObject.getQueryToDate("autoUpDateLessThanOrEqual"));
		}
		if(queryObject.getQuery("autoUpDateGreaterThanOrEqual") != null){
			criteria.andAutoUpDateGreaterThanOrEqualTo(queryObject.getQueryToDate("autoUpDateGreaterThanOrEqual"));
		}
		if(queryObject.getQuery("autoDownDateLessThanOrEqual") != null){
			criteria.andAutoDownDateLessThanOrEqualTo(queryObject.getQueryToDate("autoDownDateLessThanOrEqual"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		if(queryObject.getLimitSize() != 0){
			example.setLimitSize(queryObject.getLimitSize());
		}
		return example;
	}

	public List<AdInfo> findAdInfoList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return adInfoCustomMapper.findAdInfoList(params);
	}

	public List<Map<String, Object>> getAdList(String status,String type,String catalog,String position,Integer sourceTypeId, JSONObject reqPRM) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		Date currentDate = new Date();
		AdInfoExample adInfoExample = new AdInfoExample();
		AdInfoExample.Criteria criteria = adInfoExample.createCriteria();
		criteria.andStatusEqualTo(status).andTypeEqualTo(type).andCatalogEqualTo(catalog)
		.andAutoUpDateLessThanOrEqualTo(currentDate).andAutoDownDateGreaterThanOrEqualTo(currentDate).andDelFlagEqualTo("0");
		if(sourceTypeId != null){
			if(!catalog.equals(Const.AD_CATALOG_COUPON_CENTER)){//28.领券中心
				criteria.andSourceProductTypeIdEqualTo(sourceTypeId);
			}
		}
		if(!StringUtil.isBlank(position)){
			criteria.andPositionEqualTo(position);
		}
		adInfoExample.setLimitStart(0);
		adInfoExample.setLimitSize(5);
		adInfoExample.setOrderByClause("ifnull(seq_no,99999),auto_up_date");
		List<AdInfo> adInfoList = selectByExample(adInfoExample);
		if(CollectionUtils.isNotEmpty(adInfoList)){
			for (AdInfo adInfo : adInfoList) {
				Map<String,Object> dataMap = getAdLocation(adInfo,reqPRM);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
}

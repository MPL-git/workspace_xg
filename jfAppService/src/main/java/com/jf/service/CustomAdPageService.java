package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CustomAdPageMapper;
import com.jf.entity.CustomAdPage;
import com.jf.entity.CustomAdPageExample;
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
public class CustomAdPageService extends BaseService<CustomAdPage, CustomAdPageExample> {
	@Autowired
	private CustomAdPageMapper customAdPageMapper;

	@Autowired
	public void setCustomAdPageMapper(CustomAdPageMapper customAdPageMapper) {
		this.setDao(customAdPageMapper);
		this.customAdPageMapper = customAdPageMapper;
	}
	
	/**
	 * 自定义广告页
	 * @param sourceTypeId 
	 * @param linkId
	 * @return
	 */
	public Map<String,Object> getCustomAdPage(String pageType, Integer sourceTypeId) {
		//类型 1 首页主题管 2 首页栏目装修 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
		Map<String,Object> map = new HashMap<String,Object>();
		String customAdPageUrl = "";
		String name = "";
		String decorateInfoId = "";
		Date date = new Date();
		if(!StringUtil.isBlank(pageType)){
			List<CustomAdPage> adPages = new ArrayList<>();
			if(pageType.equals(Const.PAGE_TYPE_2) && sourceTypeId != null){
				//兼容ios55版本以下，Android版本53以下
				//sourceTypeId 定义为主键id
				CustomAdPageExample adPageExample = new CustomAdPageExample();
				CustomAdPageExample.Criteria criteria = adPageExample.createCriteria();
				criteria.andIdEqualTo(sourceTypeId).andDelFlagEqualTo("0");
				adPages = selectByExample(adPageExample);
			}else{
				CustomAdPageExample adPageExample = new CustomAdPageExample();
				CustomAdPageExample.Criteria criteria = adPageExample.createCriteria();
				criteria.andPageTypeEqualTo(pageType).andIsEffectEqualTo("1")
				.andAutoUpDateLessThanOrEqualTo(date).andAutoDownDateGreaterThanOrEqualTo(date).andDelFlagEqualTo("0");
				if(sourceTypeId != null){
					if(!pageType.equals(Const.PAGE_TYPE_24)){//24.领券中心
						criteria.andSourceProductTypeIdEqualTo(sourceTypeId);
					}
				}
				adPageExample.setLimitStart(0);
				adPageExample.setLimitSize(1);;
				adPageExample.setOrderByClause("id desc");
				adPages = selectByExample(adPageExample);
			}
			if(CollectionUtils.isNotEmpty(adPages)){
				decorateInfoId = adPages.get(0).getDecorateInfoId().toString();
				name = adPages.get(0).getPageName();
				customAdPageUrl = Config.getProperty("mUrl")+"/xgbuy/views/activity/nest/decorate/brand_decorate.html?infoId="+decorateInfoId+"&version=1";
			}
		}
		map.put("customAdPageUrl", customAdPageUrl);
		map.put("name", name);
		map.put("decorateInfoId", decorateInfoId);
		return map;
	}
}

package com.jf.entity;

import com.jf.entity.ToutiaoCampaignInfoExample.Criteria;


public class ToutiaoAdInfoCustomExample extends ToutiaoAdInfoExample{

	@Override
	public ToutiaoAdInfoCustomCriteria createCriteria() {
		ToutiaoAdInfoCustomCriteria criteria = new ToutiaoAdInfoCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class ToutiaoAdInfoCustomCriteria extends ToutiaoAdInfoExample.Criteria {
		
		/**
		 * 
		 * @Title andAdvertiserNameLike   
		 * @Description TODO(账户名称)   
		 * @author pengl
		 * @date 2018年8月17日 下午1:56:21
		 */
		public Criteria andAdvertiserNameLike(String advertiserName) {
			addCriterion(" t.advertiser_id in(select a.advertiser_id from toutiao_advertiser_info a where a.del_flag = '0' and a.name like '"+ advertiserName +"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andCampaignNameLike   
		 * @Description TODO(广告组名称)   
		 * @author pengl
		 * @date 2018年8月17日 下午2:46:36
		 */
		public Criteria andCampaignNameLike(String campaignName) {
			addCriterion(" t.campaign_id in(select a.campaign_id from toutiao_campaign_info a where a.del_flag = '0' and a.name like '"+ campaignName +"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andLandingTypeEqualTo   
		 * @Description TODO(广告组推广目的)   
		 * @author pengl
		 * @date 2018年8月17日 下午2:50:48
		 */
		public Criteria andLandingTypeEqualTo(String landingType) {
			addCriterion(" t.campaign_id in(select a.campaign_id from toutiao_campaign_info a where a.del_flag = '0' and a.landing_type = '"+ landingType +"' )");
			return this;
		}
		
		
	}
	
}

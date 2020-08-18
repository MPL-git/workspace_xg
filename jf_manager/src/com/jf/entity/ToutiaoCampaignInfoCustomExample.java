package com.jf.entity;



public class ToutiaoCampaignInfoCustomExample extends ToutiaoCampaignInfoExample {

	@Override
	public ToutiaoCampaignInfoCustomCriteria createCriteria() {
		ToutiaoCampaignInfoCustomCriteria criteria = new ToutiaoCampaignInfoCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class ToutiaoCampaignInfoCustomCriteria extends ToutiaoCampaignInfoExample.Criteria {
		
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
		
	}
	
}

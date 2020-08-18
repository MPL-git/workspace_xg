package com.jf.entity;



public class ToutiaoAdvertiserInfoCustomExample extends ToutiaoAdvertiserInfoExample {

	@Override
	public ToutiaoAdvertiserInfoCustomCriteria createCriteria() {
		ToutiaoAdvertiserInfoCustomCriteria criteria = new ToutiaoAdvertiserInfoCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class ToutiaoAdvertiserInfoCustomCriteria extends ToutiaoAdvertiserInfoExample.Criteria {
		
		public Criteria andTokenId(Integer tokenId) {
			addCriterion(" EXISTS(select ta.id from toutiao_token_advertiser_info ta where ta.del_flag = '0' and ta.advertiser_id = t.advertiser_id and ta.token_id = "+ tokenId +")");
	        return this;
	    }
		
		
	}
	
}

package com.jf.entity;


public class ThirdPlatformProductInfoCustomExample extends ThirdPlatformProductInfoExample {

	@Override
    public ThirdPlatformProductInfoCustomCriteria createCriteria() {
		ThirdPlatformProductInfoCustomCriteria criteria = new ThirdPlatformProductInfoCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ThirdPlatformProductInfoCustomCriteria extends ThirdPlatformProductInfoCustomExample.Criteria {

		public Criteria andProductTypeIdEqualTo(int productTypeId) {
			addCriterion("EXISTS(select id from bu_product p where p.id = t.product_id and p.product_type_id="+productTypeId+" and p.del_flag='0')");
			return this;
		}

		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion("t.product_id in (select p.id from bu_product p where p.code='"+productCode+"' and p.del_flag='0')");
			return this;
		}

		/*public Criteria andStatusEqualTo(String status) {
			addCriterion("EXISTS(select id from bu_product p where p.id=t.product_id and p.status='"+status+"' and p.del_flag='0')");
			return this;
		}*/

		public Criteria andProductType2IdEqualTo(int productType2Id) {
			addCriterion("EXISTS(select p.id from bu_product p,bu_product_type pt where p.id = t.product_id and p.product_type_id = pt.id and pt.parent_id="+productType2Id+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andProductType1IdEqualTo(int productType1Id) {
			addCriterion("EXISTS(select p.id from bu_product p,bu_product_type pt,bu_product_type fpt where p.id = t.product_id and p.product_type_id = pt.id and pt.parent_id = fpt.id and fpt.parent_id="+productType1Id+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andCommissionRate1GreaterThanOrEqualTo(Integer commissionRateMin) {
			addCriterion("CONVERT(t.commission_rate,SIGNED)>="+commissionRateMin);
			return this;
		}
		
		public Criteria andCommissionRate1LessThanOrEqualTo(Integer commissionRateMax) {
			addCriterion("CONVERT(t.commission_rate,SIGNED)<="+commissionRateMax);
			return this;
		}
		
		//频道
		public Criteria andWetaoChannelIdEqualTo(Integer wetaoChannelId) {
			addCriterion("t.id in(select t.id from bu_wetao_channel_product wcp  where wcp.product_id=t.product_id and wcp.channel_id="+wetaoChannelId+" and t.del_flag='0') ");
			return this;
		}
	}
	
}

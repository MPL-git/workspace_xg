package com.jf.entity;


import com.gzs.common.utils.StringUtil;

public class AdPvStatisticalCustomExample extends AdPvStatisticalExample{
	@Override
    public AdPvStatisticalCustomCriteria createCriteria() {
		AdPvStatisticalCustomCriteria criteria = new AdPvStatisticalCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class AdPvStatisticalCustomCriteria extends AdPvStatisticalExample.Criteria{

		//广告类目
		public void andCatalogEqualTo(String catalog) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select id from bu_ad_info b where b.id = a.ad_info_id and b.del_flag ='0' and b.catalog='"+catalog+"')");
		}

		public Criteria andAdStatusEqualTo(String adSourceType, String adStatus) {
			if(StringUtil.isEmpty(adSourceType) ) {
				adSourceType = "3";
			}
			if("1".equals(adSourceType) ) {
				addCriterion(" EXISTS(select bad.id from bu_brand_team_type_ad_info bad where bad.del_flag='0' and bad.id = a.ad_info_id and bad.status = '" + adStatus + "' )");
			}else if("2".equals(adSourceType) ) {
				addCriterion(" EXISTS(select wad.id from bu_wetao_page_ad_info wad where wad.del_flag='0' and wad.id = a.ad_info_id and wad.status = '" + adStatus + "' )");
			}else if("3".equals(adSourceType) ) {
				addCriterion(" EXISTS(select aad.id from bu_ad_info aad where aad.del_flag='0' and aad.id = a.ad_info_id and aad.status = '" + adStatus + "' )");
			}
			return this;
		}
		
	}
}
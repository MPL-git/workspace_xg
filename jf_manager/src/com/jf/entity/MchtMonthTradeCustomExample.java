package com.jf.entity;

import com.jf.entity.MchtSettleOrderExample.Criteria;



public class MchtMonthTradeCustomExample extends MchtMonthTradeExample {

	@Override
    public MchtMonthTradeCustomCriteria createCriteria() {
		MchtMonthTradeCustomCriteria criteria = new MchtMonthTradeCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtMonthTradeCustomCriteria extends MchtMonthTradeExample.Criteria {
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id = mmt.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id = mmt.mcht_id and mi.del_flag='0' and (mi.company_name like '%"+mchtName+"%' or mi.shop_name like '%"+mchtName+"%'))");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(String productTypeId) {//类目
			addCriterion(" mmt.mcht_id IN (select mpt.mcht_id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = mmt.mcht_id and mpt.status = '1' and mpt.product_type_id = " + productTypeId + ")" );
			return this;
		}
		
		public Criteria andProductBrandNameEqualTo(String productBrandName) {//模糊搜索品牌
			addCriterion(" mmt.mcht_id in (select mp.mcht_id from bu_product_brand pb,bu_mcht_product_brand mp where mp.product_brand_id=pb.id and mp.mcht_id = mmt.mcht_id and mp.audit_status = '3' and pb.del_flag='0' and mp.del_flag='0' and (pb.name like '"+productBrandName+"' or pb.name_zh like '"+productBrandName+"' or pb.name_en like '"+productBrandName+"'))");
			return this;
		}
		
	}
	
}

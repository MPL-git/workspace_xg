package com.jf.entity;

import com.jf.entity.PayToMchtLogExample.Criteria;



public class MchtSettleSituationCustomExample extends MchtSettleSituationExample{
	
	@Override
    public MchtSettleSituationCustomCriteria createCriteria() {
		MchtSettleSituationCustomCriteria criteria = new MchtSettleSituationCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtSettleSituationCustomCriteria extends MchtSettleSituationExample.Criteria{

		public Criteria andMchtTypeEqualTo(String mchtType) {
			addCriterion("EXISTS(select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.mcht_type='"+mchtType+"')");
			return this;
		}
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion("EXISTS(select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andNameLikeTo(String name) {
			addCriterion("t.mcht_id IN (select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.company_name like '%"+name+"%' or mi.shop_name like '%"+name+"%')");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(String productTypeId) {//类目
			addCriterion(" t.mcht_id IN (select mpt.mcht_id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = t.mcht_id and mpt.status = '1' and mpt.product_type_id = " + productTypeId + ")" );
			return this;
		}
		public Criteria andProductBrandNameEqualTo(String productBrandName) {//模糊搜索品牌
			addCriterion(" t.mcht_id in (select mp.mcht_id from bu_product_brand pb,bu_mcht_product_brand mp where mp.product_brand_id=pb.id and mp.mcht_id = t.mcht_id and mp.audit_status = '3' and pb.del_flag='0' and mp.del_flag='0' and (pb.name like '"+productBrandName+"' or pb.name_zh like '"+productBrandName+"' or pb.name_en like '"+productBrandName+"'))");
			return this;
		}
	}
}
package com.jf.entity;

public class MchtSingleActivityCnfCustomExample extends MchtSingleActivityCnfExample {

	@Override
	public MchtSingleActivityCnfCustomCriteria createCriteria() {
		MchtSingleActivityCnfCustomCriteria criteria = new MchtSingleActivityCnfCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class MchtSingleActivityCnfCustomCriteria extends MchtSingleActivityCnfExample.Criteria{
		
		//商家编码
		public Criteria andMchtCodeByEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.status = '1' and mi.id = mcht_id and mi.mcht_code = '" + mchtCode + "')");
			return this;
		}
		
		//商家合作状态=正常
		public Criteria andMchtStatusByEqualTo(String status) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = mcht_id and mi.status = '" + status + "')");
			return this;
		}
		
		//查询一级类目
		public Criteria andproductTypeIdEqualTo(String productTypeId) {
			addCriterion(" t.mcht_id in (select mpt.mcht_id from bu_mcht_product_type mpt where t.mcht_id=mpt.mcht_id and mpt.status='1' and mpt.is_main='1' and mpt.product_type_id = '" + productTypeId + "')");
			return this;
		}
		
	}
	
}

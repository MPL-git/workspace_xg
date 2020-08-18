package com.jf.entity;



public class MchtSettleOrderCustomExample extends MchtSettleOrderExample{
	
	@Override
    public MchtSettleOrderCustomCriteria createCriteria() {
		MchtSettleOrderCustomCriteria criteria = new MchtSettleOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtSettleOrderCustomCriteria extends MchtSettleOrderExample.Criteria{

		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion("EXISTS(select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion("EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.mcht_id=t.mcht_id and mpt.is_main='1' and mpt.status='1' and mpt.del_flag='0' and mpt.product_type_id="+productTypeId+" limit 1)");
			return this;
		}
		public Criteria andMchtNameLikeTo(String mchtName) {//模糊搜索名称
			addCriterion("t.mcht_id IN (select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.company_name like '%"+mchtName+"%' or mi.shop_name like '%"+mchtName+"%')");
			return this;
		}
		public Criteria andProductBrandNameEqualTo(String productBrandName) {//模糊搜索品牌
			addCriterion(" t.mcht_id in (select mp.mcht_id from bu_product_brand pb,bu_mcht_product_brand mp where mp.product_brand_id=pb.id and mp.mcht_id = t.mcht_id and mp.audit_status = '3' and pb.del_flag='0' and mp.del_flag='0' and (pb.name like '"+productBrandName+"' or pb.name_zh like '"+productBrandName+"' or pb.name_en like '"+productBrandName+"'))");
			return this;
		}
	
		public Criteria andMchtDepositUnpayAmtGreaterThanZero() {
			addCriterion("EXISTS(select md.id from bu_mcht_deposit md where md.mcht_id=t.mcht_id and md.unpay_amt>0)");
	        return this;
	    }
		
		public Criteria andMchtDepositUnpayAmtLessThanZero() {
			addCriterion("EXISTS(select md.id from bu_mcht_deposit md where md.mcht_id=t.mcht_id and md.unpay_amt<0)");
			return this;
		}
		
		public Criteria andMchtDepositUnpayAmtEqualTo() {
			addCriterion("EXISTS(select md.id from bu_mcht_deposit md where md.mcht_id=t.mcht_id and md.unpay_amt=0)");
			return this;
		}
	}
}
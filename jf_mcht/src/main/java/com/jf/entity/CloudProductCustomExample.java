package com.jf.entity;



public class CloudProductCustomExample extends CloudProductExample{
	
	@Override
    public CloudProductCustomCriteria createCriteria() {
		CloudProductCustomCriteria criteria = new CloudProductCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CloudProductCustomCriteria extends CloudProductExample.Criteria{

		public Criteria andSupplierUserStatusEqualTo(String status) {
			addCriterion("EXISTS(select id FROM jybao.sp_office a WHERE a.id = t.sp_office_id and a.status = '"+status+"')");
			return this;
		}

		public Criteria andMchtSupplierUserStatus(Integer mchtId) {
			addCriterion("t.sp_office_id in (select a.sp_office_id FROM bu_mcht_supplier_user a WHERE a.mcht_id = "+mchtId+" and a.status = '1' and a.del_flag='0')");
			return this;
		}

		public Criteria andProductBrandIdEqualTo(Integer productBrandId) {
			addCriterion("EXISTS(select id FROM jybao.bu_supplier_product_brand spb where t.supplier_product_brand_id = spb.id and spb.product_brand_id="+productBrandId+" and spb.status='1' and spb.del_flag='0')");
			return this;
		}
		
		
	}
}
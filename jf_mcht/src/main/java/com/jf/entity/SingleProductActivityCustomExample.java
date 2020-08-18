package com.jf.entity;




public class SingleProductActivityCustomExample extends SingleProductActivityExample{
	@Override
    public SingleProductActivityCustomCriteria createCriteria() {
		SingleProductActivityCustomCriteria criteria = new SingleProductActivityCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SingleProductActivityCustomCriteria extends SingleProductActivityExample.Criteria{
		public Criteria andProductCodeEqualTo(String code) {
			addCriterion("EXISTS(select id FROM bu_product p WHERE p.id = t.product_id and p.code = '"+code+"')");
	        return this;
	    }
		
		public Criteria andSaleTypeEqualTo(String saleType) {
			addCriterion("EXISTS(select id FROM bu_product p WHERE p.id = t.product_id and p.sale_type = '"+saleType+"')");
			return this;
		}
		
	}
}
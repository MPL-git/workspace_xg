package com.jf.entity;

public class ProductTypeCustomExample extends ProductTypeExample{
	
	@Override
    public ProductTypeCustomExampleCriteria createCriteria() {
		ProductTypeCustomExampleCriteria criteria = new ProductTypeCustomExampleCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ProductTypeCustomExampleCriteria extends ProductTypeExample.Criteria{
		
		public Criteria andProduct(Integer ids) {
			addCriterion("a.id in (SELECT a.id FROM bu_product_type b WHERE a.id=b.parent_id AND b.status='1' AND b.del_flag='0' AND b.id='"+ids+"')");
			return this;
		}
		
		public Criteria andGParentId(Integer ids) {
			addCriterion("a.id in (SELECT a.id FROM bu_product_type b WHERE a.id=b.parent_id AND b.status='1' AND b.del_flag='0' AND b.id='"+ids+"')");
			return this;
		}
	}
}
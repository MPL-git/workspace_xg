package com.jf.entity;

public class ProductBrandTmpCustomExample extends ProductBrandTmpExample{
	@Override
    public ProductBrandTmpExampleCriteria createCriteria() {
		ProductBrandTmpExampleCriteria criteria = new ProductBrandTmpExampleCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ProductBrandTmpExampleCriteria extends ProductBrandTmpExample.Criteria{
		public Criteria andProductTypeGroupLikeOrClause(String productTypeGroup) {
			addCriterion( "("+" product_type_group like CONCAT('"+productTypeGroup+"', '%')" + " OR "+" product_type_group like CONCAT('%,', '"+productTypeGroup+"', ',%')"+ " OR "+" product_type_group like CONCAT('%,', '"+productTypeGroup+"')"+" )");
	        return this;
	    }
		
		public Criteria andTmkTypeGroupLikeOrClause(String tmkTypeGroup) {
			addCriterion( "("+" tmk_type_group like CONCAT('"+tmkTypeGroup+"', '%')" + " OR "+" tmk_type_group like CONCAT('%,', '"+tmkTypeGroup+"', ',%')"+ " OR "+" tmk_type_group like CONCAT('%,', '"+tmkTypeGroup+"')"+" )");
			return this;
		}
		public Criteria andSearchKeyWordLikeOrClause(String searchKeyWord) {
			addCriterion( "("+ "name like '%"+searchKeyWord+"%'" +" or name_en like '%"+searchKeyWord+"%'"+" or name_zh like '%"+searchKeyWord+"%'"+" )");
			return this;
		}
	}
	
}
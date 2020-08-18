package com.jf.entity;




public class ProductBrandCustomExample extends ProductBrandExample{
	
	@Override
    public ProductBrandExampleCriteria createCriteria() {
		ProductBrandExampleCriteria criteria = new ProductBrandExampleCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	
	
	
	public class ProductBrandExampleCriteria extends ProductBrandExample.Criteria{
		//搜索功能
	    public Criteria andSearchKeywordLike(String value) {
	    	String search = " (name like CONCAT('%', '"+value+"', '%')" + "OR" + " name_zh like CONCAT('%', '"+value+"', '%')" + "OR" + " name_en like CONCAT('%', '"+value+"', '%'))";
	    	addCriterion(search);
	        return this;
        }
	    
	    
	    //品类
	    public Criteria andProductTypeGroupLikeOrClause(String productTypeGroup) {
			addCriterion( "("+" product_type_group like CONCAT('"+productTypeGroup+"', '%')" + " OR "+" product_type_group like CONCAT('%', '"+productTypeGroup+"', '%')"+ " OR "+" product_type_group like CONCAT('%', '"+productTypeGroup+"')"+" )");
	        return this;
	    }
	    
	    //商标类别
		public Criteria andTmkTypeGroupLikeOrClause(String tmkTypeGroup) {
			addCriterion( "("+" tmk_type_group like CONCAT('"+tmkTypeGroup+"', '%')" + " OR "+" tmk_type_group like CONCAT('%', '"+tmkTypeGroup+"', '%')"+ " OR "+" tmk_type_group like CONCAT('%', '"+tmkTypeGroup+"')"+" )");
			return this;
		}
		
	    
	}
	
}

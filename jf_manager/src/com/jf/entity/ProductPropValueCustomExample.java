package com.jf.entity;

public class ProductPropValueCustomExample extends ProductPropValueExample{
	@Override
	public ProductPropValueCustomCriteria createCriteria() {
		ProductPropValueCustomCriteria criteria = new ProductPropValueCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class ProductPropValueCustomCriteria extends ProductPropValueCustomExample.Criteria{
		/**
		 * 创建者商家序号搜索
		 * @param value
		 * @return
		 */
		public Criteria andMchtCodeEqualTo(String createbymchtcode){
			addCriterion("EXISTS(SELECT b.id FROM bu_mcht_info b WHERE b.id=t.create_by AND b.del_flag='0' and b.mcht_code = '"+createbymchtcode+"')");
			return this;
		}
		
		/**
		 * 规格值模糊查询
		 * @param
		 * @return
		 */
		public Criteria andPropValueLike(String propValue) {
			addCriterion("("+" prop_value like CONCAT('"+propValue+"', '%')" + " OR "+" prop_value like CONCAT('%', '"+propValue+"', '%')"+ " OR "+" prop_value like CONCAT('%', '"+propValue+"')"+" )");
			return this;
		}
		
		/**
		 * 同义词模糊查询
		 * @param 
		 * @return
		 */
		public Criteria andaliasLike(String alias) {
			addCriterion("("+" alias like CONCAT('"+alias+"', '%')" + " OR "+" alias like CONCAT('%', '"+alias+"', '%')"+ " OR "+" alias like CONCAT('%', '"+alias+"')"+" )");
			return this;
		}
		
		/**
		 * 一级类目查询
		 * @param 
		 * @return
		 */		
		public Criteria andProductTypeEqualTo(String productTypeId) {
			addCriterion(" EXISTS(SELECT mpt.id FROM bu_mcht_product_type mpt, bu_product_type m WHERE mpt.del_flag = '0' AND m.del_flag='0' AND mpt.product_type_id=m.id  AND mpt.mcht_id=t.create_by "
					+ "AND mpt.product_type_id = " + productTypeId + ")" );			
			return this;
		}
		
		/**
		 * 规格名称查询
		 * @param 
		 * @return
		 */	
		public Criteria andproductPropTypeEqualTo(String productPropId) {
			addCriterion(" EXISTS(SELECT p.id FROM bu_product_prop p WHERE p.del_flag = '0' AND p.id=t.prop_id AND t.prop_id = '" + productPropId + "')");			
			return this;
		}
		
	}

}
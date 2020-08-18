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
	    public Criteria andSearchNameECLike(String value) {
	    	value = value.replace("'", "''");
	    	String search = " ( name like CONCAT('%', '"+value+"', '%')" + "OR" + " name_zh like CONCAT('%', '"+value+"', '%')" + "OR" + " name_en like CONCAT('%', '"+value+"', '%'))";
	    	addCriterion(search);
	        return this;
        }
	    
	    //品类
	    public Criteria andProductTypeGroupLikeOrClause(String productTypeGroup) {
			addCriterion( "( product_type_group like CONCAT('"+productTypeGroup+"', '%')" + " OR "+" product_type_group like CONCAT('%', '"+productTypeGroup+"', '%')"+ " OR "+" product_type_group like CONCAT('%', '"+productTypeGroup+"')"+" )");
	        return this;
	    }
	    
	    //商标类别
		public Criteria andTmkTypeGroupLikeOrClause(String tmkTypeGroup) {
			addCriterion( "( tmk_type_group like CONCAT('"+tmkTypeGroup+"', '%')" + " OR "+" tmk_type_group like CONCAT('%', '"+tmkTypeGroup+"', '%')"+ " OR "+" tmk_type_group like CONCAT('%', '"+tmkTypeGroup+"')"+" )");
			return this;
		}
		
		/**
		 * 商标类别（bu_product_brand_trademark_info）
		 */
		public Criteria andTmkTypeGroupEqualTo(String tmkTypeGroup) {
			addCriterion("( id in(select pbti.product_brand_id from bu_product_brand_trademark_info pbti where pbti.del_flag = '0' and pbti.tmk_type = '"+ tmkTypeGroup +"' ))");
			return this;
		}
		
		/**
		 * 商标注册号（bu_product_brand_trademark_info）
		 */
		public Criteria andTmkCodeEqualTo(String tmkCode) {
			addCriterion("( id in(select pbti.product_brand_id from bu_product_brand_trademark_info pbti where pbti.del_flag = '0' and pbti.tmk_code = '"+ tmkCode +"' ))");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductIdEqualTo   
		 * @Description TODO(判断该品牌下是否存在上架商品)   
		 * @author pengl
		 * @date 2018年7月23日 上午10:57:42
		 */
		public Criteria andProductIdEqualTo() {
			addCriterion(" t.id IN(select p.brand_id from bu_product p where p.del_flag = '0' and p.status = '1' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andLetterIndexIsNullEqualTo   
		 * @Description TODO(这里用一句话描述这个方法的作用)   
		 * @author pengl
		 * @date 2018年8月9日 下午4:21:27
		 */
		public Criteria andLetterIndexIsNullEqualTo() {
			addCriterion(" (t.letter_index = '' or t.letter_index is null) ");
			return this;
		}

		/**
		 *
		 * @Title 查询该商家的品牌
		 */
		public Criteria andSearchBrandInMchtIdEqualTo(Integer mchtId) {
			addCriterion( "t.id in (select mpb.product_brand_id  from bu_mcht_product_brand mpb where mpb.del_flag='0' and mpb.mcht_id ='"+mchtId+"')");
			return this;
		}

		/**
		 *
		 * @Title 查询该类目的品牌
		 */
		public Criteria andSearchBrandInProductType1IdEqualTo(Integer productType1Id) {
			addCriterion( "t.id in (select t.brand_id from bu_product t where t.del_flag='0' and t.status ='1'  and t.product_type1_id = '"+productType1Id+"')");
			return this;
		}
	    
	}
	
}

package com.jf.entity;


public class ProductPriceChangeLogCustomExample extends ProductPriceChangeLogExample {

	@Override
    public ProductPriceChangeLogCustomCriteria createCriteria() {
		ProductPriceChangeLogCustomCriteria criteria = new ProductPriceChangeLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ProductPriceChangeLogCustomCriteria extends ProductPriceChangeLogExample.Criteria{
		
		/**
		 * 
		 * @Title andMchtCodeEqualTo   
		 * @Description TODO(商家序号)   
		 * @author pengl
		 * @date 2018年9月8日 下午2:48:23
		 */
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" t.product_id IN (select bp.id from bu_product bp, bu_mcht_info mi where bp.del_flag = '0' and mi.del_flag = '0' and bp.mcht_id = mi.id and mi.mcht_code = '"+ mchtCode +"')");
	        return this;
	    }
		
		/**
		 * 
		 * @Title andMchtNameLike   
		 * @Description TODO(商家名称)   
		 * @author pengl
		 * @date 2018年9月8日 下午2:00:56
		 */
		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" t.product_id IN (select bp.id from bu_product bp, bu_mcht_info mi where bp.del_flag = '0' and mi.del_flag = '0' and bp.mcht_id = mi.id and (mi.shop_name like '"+ mchtName +"' or mi.company_name like '"+ mchtName +"'))");
	        return this;
	    }
		
		/**
		 * 
		 * @Title andProductBandIdEqualTo   
		 * @Description TODO(品牌ID)   
		 * @author pengl
		 * @date 2018年9月8日 下午2:59:10
		 */
		public Criteria andProductBandIdEqualTo(Integer productBandId) {
			addCriterion(" t.product_id IN (select bp.id from bu_product bp where bp.del_flag = '0' and bp.brand_id = "+ productBandId +")");
			return this;
		}
		
		/**
		 * 
		 * @Title andArtNoEqualTo   
		 * @Description TODO(货号)   
		 * @author pengl
		 * @date 2018年9月8日 下午2:59:10
		 */
		public Criteria andArtNoEqualTo(String artNo) {
			addCriterion(" t.product_id IN (select bp.id from bu_product bp where bp.del_flag = '0' and bp.art_no like '"+ artNo +"')");
			return this;
		}
	
		/**
		 * yjc
		 */
		public Criteria andProductCodeEqualTo(String productCode) { 
			addCriterion(" t.product_id = (select p.id from bu_product p where p.del_flag = '0' and p.code = '"+productCode+"')");
			return this;
		}
		
	}
	
}

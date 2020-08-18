package com.jf.entity;



public class CutPriceOrderCustomExample extends CutPriceOrderExample {

	@Override
    public CutPriceOrderCriteria createCriteria() {
		CutPriceOrderCriteria criteria = new CutPriceOrderCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CutPriceOrderCriteria extends CutPriceOrderExample.Criteria{

		/**
		 * 
		 * @Title andBlackListNull   
		 * @Description TODO(砍价没有被拉黑)   
		 * @author pengl
		 * @date 2018年6月8日 下午3:43:07
		 */
		public Criteria andBlackListNull() {
			addCriterion(" NOT EXISTS(select bl.id from bu_black_list bl where bl.del_flag = '0' and bl.black_type = '2' and bl.member_id = t.member_id and bl.black_to_date >= now() )");
			return this;
		}
		
		/**
		 * 
		 * @Title andCutPrice   
		 * @Description TODO(砍价)   
		 * @author pengl
		 * @date 2018年6月11日 下午3:01:51
		 */
		public Criteria andCutPrice() {
			addCriterion(" EXISTS(select spa.id from bu_single_product_activity spa where spa.del_flag = '0' and spa.type = '7' and spa.id = t.single_product_activity_id )");
			return this;
		}
		
		/**
		 * 
		 * @Title andSuperCutPrice   
		 * @Description TODO(超级砍价)   
		 * @author pengl
		 * @date 2018年6月11日 下午3:02:06
		 */
		public Criteria andSuperCutPrice() {
			addCriterion(" EXISTS(select spa.id from bu_single_product_activity spa where spa.del_flag = '0' and spa.type = '8' and spa.id = t.single_product_activity_id )");
			return this;
		}
		
		/**
		 * 
		 * @Title andSuperBlackListNull   
		 * @Description TODO(超级砍价没有被拉黑)   
		 * @author pengl
		 * @date 2018年6月11日 下午4:08:18
		 */
		public Criteria andSuperBlackListNull() {
			addCriterion(" NOT EXISTS(select bl.id from bu_black_list bl where bl.del_flag = '0' and bl.black_type = '3' and bl.member_id = t.member_id and bl.black_to_date >= now() )");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductCodeEqualTo   
		 * @Description TODO(根据商品编码精确查询)   
		 * @author pengl
		 * @date 2019年2月14日 上午11:43:21
		 */
		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion(" EXISTS(select p.id from bu_product p where p.del_flag = '0' and p.id = t.product_id and p.code = '" + productCode + "')");
			return this;
		}
		
		/**
		 * 
		 * @Title andShopNameLike   
		 * @Description TODO(根据店铺名称模糊查询)   
		 * @author pengl
		 * @date 2019年2月14日 下午1:46:48
		 */
		public Criteria andShopNameLike(String shopName) {
			addCriterion(" EXISTS(select m.id from bu_product p, bu_mcht_info m where p.del_flag = '0' and m.del_flag = '0' and p.mcht_id = m.id and p.id = t.product_id and m.shop_name like concat('%', '" + shopName + "', '%') )");
			return this;
		}
		
		/**
		 * 
		 * @Title andPayStatus   
		 * @Description TODO(是否付款)   
		 * @author pengl
		 * @date 2019年2月14日 下午4:06:05
		 */
		public Criteria andPayStatus(String payStatus) {
			if("1".equals(payStatus)) {
				addCriterion(" EXISTS(select co.id from bu_combine_order co where co.del_flag = '0' and co.status = '1' and co.id = t.combine_order_id )");
			}else {
				addCriterion(" NOT EXISTS(select co.id from bu_combine_order co where co.del_flag = '0' and co.status = '1' and co.id = t.combine_order_id )");
			}
			return this;
		}
		
	}
	
	
}

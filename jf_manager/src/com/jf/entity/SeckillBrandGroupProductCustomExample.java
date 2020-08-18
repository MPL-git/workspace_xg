package com.jf.entity;


public class SeckillBrandGroupProductCustomExample extends SeckillBrandGroupProductExample {
	@Override
    public SeckillBrandGroupProductCustomCriteria createCriteria() {
		SeckillBrandGroupProductCustomCriteria criteria = new SeckillBrandGroupProductCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SeckillBrandGroupProductCustomCriteria extends SeckillBrandGroupProductCustomExample.Criteria{
		
		/**
		 * 
		 * @Title andProductIdEqualTo   
		 * @Description TODO(商品ID)   
		 * @author pengl
		 * @date 2018年5月11日 下午4:52:28
		 */
		public Criteria andProductCodeEqualTo(String productCode){
			addCriterion(" EXISTS(select p.id from bu_single_product_activity spa, bu_product p where p.del_flag = '0' and spa.del_flag = '0' and spa.type = '3' and spa.is_close = '0' and spa.audit_status = '3' and spa.product_id = p.id and spa.id = t.single_product_activity_id and p.code = '"+ productCode +"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductBandNameLike   
		 * @Description TODO(品牌)   
		 * @author pengl
		 * @date 2018年5月11日 下午4:57:34
		 */
		public Criteria andProductBandNameLike(String productBandName){
			addCriterion(" EXISTS(select pb.id from bu_single_product_activity spa, bu_product p, bu_product_brand pb where pb.del_flag = '0' and p.del_flag = '0' and spa.del_flag = '0' and spa.type = '3' and spa.is_close = '0' and spa.audit_status = '3' and spa.product_id = p.id and p.brand_id = pb.id and spa.id = t.single_product_activity_id and pb.name like '"+ productBandName +"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductArtEqualTo   
		 * @Description TODO(货号)   
		 * @author pengl
		 * @date 2018年5月11日 下午5:01:31
		 */
		public Criteria andProductArtLike(String productArtNo){
			addCriterion(" EXISTS(select p.id from bu_single_product_activity spa, bu_product p where p.del_flag = '0' and spa.del_flag = '0' and spa.type = '3' and spa.is_close = '0' and spa.audit_status = '3' and spa.product_id = p.id and spa.id = t.single_product_activity_id and p.art_no like '"+ productArtNo +"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andShopNameLike   
		 * @Description TODO(店铺名)   
		 * @author pengl
		 * @date 2018年5月11日 下午5:04:34
		 */
		public Criteria andShopNameLike(String shopName){
			addCriterion(" EXISTS(select mi.id from bu_single_product_activity spa, bu_mcht_info mi where mi.del_flag = '0' and spa.del_flag = '0' and spa.type = '3' and spa.is_close = '0' and spa.audit_status = '3' and spa.mcht_id = mi.id and spa.id = t.single_product_activity_id and mi.shop_name like '"+ shopName +"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductActivityPriceGreaterThanOrEqualTo   
		 * @Description TODO(价格区间)   
		 * @author pengl
		 * @date 2018年5月11日 下午5:10:26
		 */
		public Criteria andProductActivityPriceGreaterThanOrEqualTo(String startProductActivityPrice){
			addCriterion(" EXISTS(select spa.id from bu_single_product_activity spa where spa.del_flag = '0' and spa.type = '3' and spa.is_close = '0' and spa.audit_status = '3' and spa.id = t.single_product_activity_id and spa.activity_price >= "+ startProductActivityPrice +")");
			return this;
		}
		public Criteria andProductActivityPriceLessThanOrEqualTo(String endProductActivityPrice){
			addCriterion(" EXISTS(select spa.id from bu_single_product_activity spa where spa.del_flag = '0' and spa.type = '3' and spa.is_close = '0' and spa.audit_status = '3' and spa.id = t.single_product_activity_id and spa.activity_price <= "+ endProductActivityPrice +")");
			return this;
		}
		
		/**
		 * 
		 * @Title andSeckillBrandGroupBeginTimeNotEqualTo   
		 * @Description TODO(不相等开始时间)   
		 * @author pengl
		 * @date 2018年5月11日 下午7:08:41
		 */
		public Criteria andSeckillBrandGroupBeginTimeNotEqualTo(String beginTime){
			addCriterion(" EXISTS(select sbg.id from bu_seckill_brand_group sbg where sbg.del_flag = '0' and sbg.id = t.seckill_brand_group_id and sbg.begin_time != '"+ beginTime +"' )");
			return this;
		}
		
		
		
	}
}
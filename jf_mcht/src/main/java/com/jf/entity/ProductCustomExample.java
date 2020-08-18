package com.jf.entity;

import java.math.BigDecimal;
import java.util.List;

import com.jf.common.utils.StringUtil;


public class ProductCustomExample extends ProductExample{
	
	@Override
    public ProductCustomCriteria createCriteria() {
		ProductCustomCriteria criteria = new ProductCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ProductCustomCriteria extends ProductExample.Criteria{
		public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" t.mcht_id in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' ))");
	        return this;
	    }
		
		public Criteria andProductBrandNameEqualTo(String productBrandName) {
			addCriterion(" t.brand_id in (select id from bu_product_brand pb where pb.del_flag='0' and (pb.name = '"+productBrandName+"' or pb.name_zh = '"+productBrandName+"'))");
			return this;
		}
		
		public Criteria andSalePriceLessThanOrEqualTo(BigDecimal salePrice) {
			addCriterion(" (select max(pi.sale_price) from bu_product_item pi where pi.product_id=t.id)<="+salePrice);
			return this;
		}
		
		public Criteria andSalePriceGreaterThanOrEqualTo(BigDecimal salePrice) {
			addCriterion(" (select min(pi.sale_price) from bu_product_item pi where pi.product_id=t.id)>="+salePrice);
			return this;
		}
		
		public Criteria andProductTypeAll(Integer productType) {
			addCriterion( "FIND_IN_SET(product_type_id,FUN_GET_PRODUCT_TYPE_All_CHILD("+productType+"))" );
			return this;
		}
		
		public Criteria andMchtTypeEqualTo(String mchtType) {
			addCriterion(" EXISTS (select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.mcht_type='"+mchtType+"')");
			return this;
		}
		
		public Criteria andSingleProductActivityIdEqualTo(Integer id) {
			addCriterion(" t.id in (select spa.product_id from bu_single_product_activity spa where spa.id="+id+")");
			return this;
		}
		
		public Criteria andActivityIdEqualTo(Integer activityId) {
			addCriterion(" t.id in (select bap.product_id from bu_activity_product bap where bap.activity_id="+activityId+")");
			return this;
		}

//		public Criteria andProductActivityStatusEqualTo(String productActivityStatus) {
//			addCriterion(" FUN_GET_PRODUCT_ACTIVITY_STATUS(id)='"+productActivityStatus+"'");
//			return this;
//		}
//		public Criteria andProductActivityStatusNotEqualTo(String productActivityStatus) {
//			addCriterion(" FUN_GET_PRODUCT_ACTIVITY_STATUS(id)<>'"+productActivityStatus+"'");
//			return this;
//		}

		public Criteria andProductTypeIdsIn(String firstProductTypeIds) {
			addCriterion("product_type_id in (select pt.id from bu_product_type pt,bu_product_type fpt where pt.parent_id = fpt.id and fpt.parent_id in ("+firstProductTypeIds+"))");
			return this;
		}

		public Criteria andShopProductCustomTypeIdEqualTo(String shopProductCustomTypeId) {
			addCriterion(" FIND_IN_SET("+shopProductCustomTypeId+",t.shop_product_custom_type_id_group)");
			return this;
		}
		
		public Criteria andProductBrandIdIn(Integer mchtId) {
			addCriterion("brand_id in (select pb.id from bu_mcht_product_brand mpb,bu_product_brand pb where pb.id=mpb.product_brand_id and mpb.del_flag='0' and pb.del_flag='0' and pb.`status`='1' and mpb.`status`='1' and mpb.audit_status='3' and mpb.mcht_id="+mchtId+")");
			return this;
		}

		public Criteria andShopProductCustomTypeIdEqualNull() {
			addCriterion("(t.shop_product_custom_type_id_group IS NULL or t.shop_product_custom_type_id_group='')");
			return this;
		}
		
		public Criteria andShopProductActivityStatusEqualTo(String productActivityStatus) {
			addCriterion(" FUN_GET_SHOP_PRODUCT_ACTIVITY_STATUS(id)='"+productActivityStatus+"'");
			return this;
		}
		
		public Criteria andShopProductActivityStatusNotEqualTo(String productActivityStatus) {
			addCriterion(" FUN_GET_SHOP_PRODUCT_ACTIVITY_STATUS(id)<>'"+productActivityStatus+"'");
			return this;
		}
		public Criteria andSingleProductActivityStatusEqualTo(String productActivityStatus) {
			addCriterion(" FUN_GET_SINGLE_PRODUCT_ACTIVITY_STATUS(id)='"+productActivityStatus+"'");
			return this;
		}
		
		public Criteria andSingleProductActivityStatusNotEqualTo(String productActivityStatus) {
			addCriterion(" FUN_GET_SINGLE_PRODUCT_ACTIVITY_STATUS(id)<>'"+productActivityStatus+"'");
			return this;
		}
		
		//商品品牌有效，商家品牌的法务状态=通过，商家品牌的运营状态 =正常，商品品牌状态等于正常
		public Criteria andBrandIsEffect() {
			addCriterion(" EXISTS (select pb.id from bu_mcht_product_brand mpb,bu_product_brand pb where pb.id=mpb.product_brand_id and mpb.del_flag='0' and pb.del_flag='0' and pb.`status`='1' and mpb.`status`='1' and mpb.audit_status='3' and pb.id=t.brand_id and mpb.mcht_id=t.mcht_id)");
			return this;
		}
		
		public Criteria andSaleActivityIdEqualTo(Integer activityId) {
			addCriterion(" id in (select bap.product_id from bu_activity_product bap where bap.activity_id="+activityId+")");
			return this;
		}

		public Criteria andProductInActivityEqualTo(Integer activityId) {
			addCriterion("1=1 or (id in (select bap.product_id from bu_activity_product bap where bap.activity_id="+activityId+"))");
			return this;
		}
		
		public Criteria andSingleProductActivityOnLine(Integer mchtId,String singleName,String singleArtNo,String singleCode) {
			StringBuffer stringBuffer = new StringBuffer(/*" 1=1 OR t.id IN (SELECT spa.product_id FROM bu_single_product_activity spa WHERE spa.mcht_id = "*/);
			if (mchtId!=null) {
				stringBuffer.append(mchtId);
			}
			if (!StringUtil.isEmpty(singleName)) {
				stringBuffer.append(" AND name like '"+singleName+"'");
			}
			if (!StringUtil.isEmpty(singleArtNo)) {
				stringBuffer.append(" AND art_no = '"+singleArtNo+"'");
			}
			if (!StringUtil.isEmpty(singleCode)) {
				stringBuffer.append(" AND code = '"+singleCode+"'");
			}
			
			/*stringBuffer.append(" AND begin_time <= NOW() AND spa.end_time >= NOW() AND spa.audit_status = '3' AND spa.`status` = '1' AND spa.is_close = '0' AND spa.del_flag = '0' )");*/
			addCriterion(stringBuffer.toString());
	        return this;
	    }

		
		//1530添加价格添加       
		public Criteria andProductChooseMinActivityPriceMoreThan(Integer priceMin) {
			addCriterion("t.id in (	SELECT	pi.product_id	FROM	bu_product_item pi	WHERE	pi.product_id = t.id and pi.del_flag='0' AND pi.sale_price >= "+priceMin+")");
			return this;
		}
		
		public Criteria andProductChooseMaxActivityPriceLessThan(Integer priceMax) {
			addCriterion("t.id in (	SELECT	pi.product_id	FROM	bu_product_item pi	WHERE	pi.product_id = t.id and pi.del_flag='0' AND pi.sale_price <= "+priceMax+")");
			return this;
		}
		


		public void andMinActivityPriceMoreThan(Double minPrice) {
			// TODO Auto-generated method stub
			addCriterion(" (select min(a.sale_price) from bu_product_item a where a.product_id=t.id and a.del_flag='0') >="+minPrice);
		}

		public void andMaxActivityPriceLessThan(Double maxPrice) {
			// TODO Auto-generated method stub
			addCriterion(" (select max(a.sale_price) from bu_product_item a where a.product_id=t.id and a.del_flag='0') <="+maxPrice);
		}

		/**
		 * @MethodName andtwoActivity
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月5日 下午8:44:12
		 */
		public void andtwoActivity() {
			// TODO Auto-generated method stub
			addCriterion(" (select count(b.link_id) from bu_source_niche b where b.link_id = t.id and b.del_flag='0' and b.type in('1','4','5','6','7','8','9')) < 2");
		}

		/**
		 * @MethodName andsignUpEd
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月5日 下午8:44:15
		 */
		public void andsignUpEd(String type) {
			// TODO Auto-generated method stub
			addCriterion(" NOT EXISTS (select id from bu_source_niche b where b.link_id = t.id and b.del_flag='0' and b.status = '0' and b.type = '"+type+"')");
		}

        public void andCustomIdIn(String idsList) {
			addCriterion(" bp.id in("+idsList+") and bp.del_flag = '0' and bpi.del_flag = '0' and bp.id = bpi.product_id");
        }
    }
}
package com.jf.entity;

import java.math.BigDecimal;


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
		public Criteria andProductActivityStatusEqualTo(String productActivityStatus) {
			addCriterion(" FUN_GET_PRODUCT_ACTIVITY_STATUS(t.id)='"+productActivityStatus+"'");
	        return this;
	    }
		
		public Criteria discountMonitoring(Double discount) {
			addCriterion(" (t.min_sale_price/t.min_tag_price) <= '"+discount+"'");
			return this;
		}
		
		public Criteria betweenDiscountMonitoring(Double start,Double end) {
			addCriterion(" (t.min_sale_price/t.min_tag_price) > '"+start+"' and (t.min_sale_price/t.min_tag_price) <= '"+end+"'");
			return this;
		}
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" t.mcht_id = (select mi.id from bu_mcht_info mi where mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
			return this;
		}
		
		public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" t.mcht_id in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' or mi.shop_name like '"+mchtName+"'))");
	        return this;
	    }
		
		public Criteria andSingleActivityOnLine(String acType) {
			if("".equals(acType)){
				addCriterion(" t.id IN (SELECT spa.product_id FROM bu_single_product_activity spa WHERE begin_time <= NOW( ) AND spa.end_time >= NOW( )  AND spa.audit_status = '3'  AND spa.`status` = '1' AND spa.is_close = '0' AND spa.del_flag = '0' )");
			}else {
				addCriterion(" t.id IN (SELECT spa.product_id FROM bu_single_product_activity spa WHERE begin_time <= NOW( ) AND spa.end_time >= NOW( )  AND spa.audit_status = '3'  AND spa.`status` = '1' AND spa.is_close = '0' AND spa.del_flag = '0' AND spa.type = '"+acType+"' )");
			}
			return this;
		}
		
		public Criteria andShopProductMonitoring(Integer status) {
			addCriterion(" t.mcht_id in (select id from bu_mcht_info where status =  '"+status+"' )");
			return this;
		}
		
		public Criteria andYyPlatformContactIdEqualTo(Integer yyPlatformContactId) {
			addCriterion(" EXISTS( SELECT c.staff_id FROM bu_platform_contact c,bu_mcht_platform_contact b WHERE c.id = b.platform_contact_id AND b.mcht_id = t.mcht_id AND c.contact_type = '2' AND b.del_flag = '0' AND c.del_flag = '0' AND b.STATUS = '1' AND c.STATUS = '0' AND c.staff_id = "+yyPlatformContactId+" LIMIT 1 )");
			return this;
		}
		
		/*public Criteria andProductBrandNameEqualTo(String productBrandName) {
			addCriterion(" t.brand_id in (select id from bu_product_brand pb where pb.del_flag='0' and (pb.name = '"+productBrandName+"' or pb.name_zh = '"+productBrandName+"'))");
			return this;
		}*/
		public Criteria andProductBrandNameEqualTo(String productBrandName) {
			addCriterion(" t.brand_id in (select id from bu_product_brand pb where pb.del_flag='0' and (pb.name like '"+productBrandName+"' or pb.name_zh like '"+productBrandName+"' or pb.name_en like '"+productBrandName+"'))");
			return this;
		}
		
		public Criteria andProductBrandName2EqualTo(String productBrandName) {
			addCriterion(" t.brand_id in (select id from bu_product_brand pb where pb.del_flag='0' and (pb.name='"+productBrandName+"' or pb.name_zh='"+productBrandName+"' or pb.name_en='"+productBrandName+"'))");
			return this;
		}
		
		public Criteria andSalePriceLessThanOrEqualTo(BigDecimal salePriceEnd) {
			addCriterion(" (select max(pi.sale_price) from bu_product_item pi where pi.del_flag='0' and pi.product_id=t.id)<="+salePriceEnd);
			return this;
		}
		
		public Criteria andSalePriceGreaterThanOrEqualTo(BigDecimal salePrice) {
			addCriterion(" (select min(pi.sale_price) from bu_product_item pi where pi.del_flag='0' and pi.product_id=t.id)>="+salePrice);
			return this;
		}

		public Criteria andCostPriceLessThanOrEqualTo(BigDecimal costPriceEnd) {
			addCriterion(" (select max(pi.cost_price) from bu_product_item pi where pi.del_flag='0' and pi.product_id=t.id) <= "+costPriceEnd);
			return this;
		}

		public Criteria andCostPriceGreaterThanOrEqualTo(BigDecimal costPriceBegin) {
			addCriterion(" (select min(pi.cost_price) from bu_product_item pi where pi.del_flag='0' and pi.product_id=t.id) >= "+costPriceBegin);
			return this;
		}
		
		public Criteria andProductTypeAll(String productTypeIds) {
			addCriterion( " product_type_id in ("+productTypeIds+")" );
			return this;
		}
		
		public Criteria andMchtTypeEqualTo(String mchtType) {
			addCriterion(" EXISTS (select id from bu_mcht_info mi where mi.id = mcht_id and mi.del_flag='0' and mi.mcht_type='"+mchtType+"')");
			return this;
		}
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS (select mpc.mcht_id from bu_mcht_platform_contact mpc where mpc.mcht_id=t.mcht_id and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		
	   //更新和创建时间
	   public Criteria andStartUpdatedateBeginDate(String value) {
			 addCriterion("(IFNULL(t.update_date,t.create_date) >='"+value+"')");
			 return this;
	    }
				
	  public Criteria andEndUpdatedateBeginDate(String value) {
			 addCriterion("(IFNULL(t.update_date,t.create_date) <='"+value+"')");
			 return this;
	   }
		
		/**
		 * 
		 * @Title andActivityProduct   
		 * @Description TODO(这里用一句话描述这个方法的作用)   
		 * @author pengl
		 * @date 2018年1月25日 下午3:56:07
		 */
		public Criteria andActivityProduct(Integer activityId, String statusAudit) {
			if("1".equals(statusAudit)) { //专员
				addCriterion(" t.id in (select ap.product_id from bu_activity_product ap where ap.refuse_flag = '0' and ap.del_flag = '0' and ap.operate_audit_status = '2' and ap.activity_id = "+ activityId +" )");
			}else if("2".equals(statusAudit)) { //排期
				addCriterion(" t.id in (select ap.product_id from bu_activity_product ap where ap.refuse_flag = '0' and ap.del_flag = '0' and ap.coo_audit_status = '2' and ap.activity_id = "+ activityId +" )");
			}
			return this;
		}

		public Criteria andIdsNotIn(Integer decorateModuleId) {
			addCriterion(" t.id NOT IN (select mi.item_id from bu_module_item mi where mi.del_flag='0' and mi.item_type='1' and mi.decorate_module_id = "+decorateModuleId+")");
			return this;
		}
		
		public Criteria andShopStatusEqualTo(String shopStatus) {
			addCriterion("EXISTS (select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.shop_status='"+shopStatus+"')");
	        return this;
	    }
		
		public Criteria andMchtInfoStatusEqualTo(String status) {
			addCriterion("EXISTS (select mi.id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.status='"+status+"')");
	        return this;
	    }

		public Criteria andMallPriceGreaterThanOrEqualTo(BigDecimal mallPriceMin) {
			addCriterion("EXISTS (select pi.id from bu_product_item pi where pi.product_id=t.id and pi.del_flag='0' and pi.mall_price>="+mallPriceMin+")");
	        return this;
		}
		
		public Criteria andMallPriceLessThanOrEqualTo(BigDecimal mallPriceMax) {
			addCriterion("EXISTS (select pi.id from bu_product_item pi where pi.product_id=t.id and pi.del_flag='0' and pi.mall_price<="+mallPriceMax+")");
			return this;
		}

		public Criteria andBrandNameEqualTo(String brandName) {
			addCriterion("EXISTS (select pb.id from bu_product_brand pb where t.brand_id=pb.id and pb.del_flag='0' and (pb.name='"+brandName+"' or pb.name_zh='"+brandName+"' or pb.name_en='"+brandName+"'))");
			return this;
		}

		public Criteria andSeasonWeightEqualTo(int seasonWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.season_weight="+seasonWeight+")");
			return this;
		}
		
		public Criteria andSaleWeightEqualTo(int saleWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.sale_weight="+saleWeight+")");
			return this;
		}
		
		public Criteria andPvWeightEqualTo(int pvWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.pv_weight="+pvWeight+")");
			return this;
		}
		
		public Criteria andMchtGradeWeightEqualTo(int mchtGradeWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.mcht_grade_weight="+mchtGradeWeight+")");
			return this;
		}
		
		public Criteria andBrandGradeWeightEqualTo(int brandGradeWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.brand_grade_weight="+brandGradeWeight+")");
			return this;
		}
		
		public Criteria andManualWeightEqualTo(int manualWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.manual_weight="+manualWeight+")");
			return this;
		}
		public Criteria andSaleAmountWeightEqualTo(int saleAmountWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.sale_amount_weight="+saleAmountWeight+")");
			return this;
		}
		public Criteria andCommentWeightEqualTo(int commentWeight) {
			addCriterion("EXISTS (select pw.id from bu_product_weight pw where pw.product_id=t.id and pw.del_flag='0' and pw.comment_weight="+commentWeight+")");
			return this;
		}
		
		public Criteria andSuitGroupLikeTo(String suitGroup) {
			if(suitGroup.equals("010")){
				addCriterion("(t.suit_group = '010' or t.suit_group = '011' or t.suit_group = '110' or t.suit_group = '111')");
			}else if(suitGroup.equals("001")){
				addCriterion("(t.suit_group = '001' or t.suit_group = '011' or t.suit_group = '101' or t.suit_group = '111')");
			}
	        return this;
	    }
		
		public Criteria andSuitSexEqualToManOrWomen(String suitSex) {
			addCriterion("(t.suit_sex = '"+suitSex+"' or t.suit_sex = '11')");
			return this;
		}
		
		public Criteria andSourceNicheNotEqualTo(){//是否存在好货
			addCriterion(" NOT EXISTS(select sn.id from bu_source_niche sn where sn.link_id=t.id and sn.del_flag='0' and sn.status='0' and sn.type='1')");
			return this;
		}
		
		public Criteria andLaxinSourceNicheNotEqualTo(){//是否存在拉新好货
			addCriterion(" t.id NOT IN(select sn.link_id from bu_source_niche sn where sn.del_flag='0' and sn.status='0' and sn.type='3')");
			return this;
		}
		
		public Criteria andAuditStatusEqualTo(){//是否参加了活动
			addCriterion(" t.id in (select sn.link_id from bu_source_niche sn where sn.link_id=t.id and sn.del_flag='0'  and sn.audit_status='0')");
			return this;
		}
		
		public Criteria andSourceTypeEqualTo(String sourceType){//活动的类型
			addCriterion(" t.id in (select sn.link_id from bu_source_niche sn where sn.link_id=t.id and sn.del_flag='0' and sn.status='0' and sn.audit_status='0' and sn.type='"+sourceType+"')");
			return this;
		}

		public Criteria andReportColumnIdEqualTo(Integer columnTypeId){//流量统计报表栏目
			addCriterion("EXISTS (select bmp.value_id from bu_member_pv bmp where bmp.value_id = t.id and bmp.page_classify = '10' and bmp.column_type = '"+columnTypeId+"')");
			return this;
		}

		public Criteria andMchtIdInRecommendedModels() {
			addCriterion("mcht_id in(select m.`id`from bu_mcht_info m, bu_mcht_product_brand pb where m.id = pb.`mcht_id` and  m.status = '1' and m.del_flag = '0' and pb.`status` = '1' and pb.`audit_status`='3' and pb.del_flag = '0')");
			return this;
		}

		public Criteria andIdNotInRecommendedModels() {
			addCriterion("id not in(select link_id from bu_source_niche where type = '12' and status = '0' and del_flag = '0')");
			return this;
		}
	}
}
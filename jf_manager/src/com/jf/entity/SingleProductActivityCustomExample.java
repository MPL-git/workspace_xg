package com.jf.entity;

import com.gzs.common.utils.StringUtil;

public class SingleProductActivityCustomExample extends SingleProductActivityExample {

	@Override
	public SingleProductActivityCustomCriteria createCriteria() {
		SingleProductActivityCustomCriteria criteria = new SingleProductActivityCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class SingleProductActivityCustomCriteria extends SingleProductActivityExample.Criteria{
		
		//品牌模糊查询
		public Criteria andBrandNameLike(String brandName) {
			addCriterion(" EXISTS(select bb.id from bu_product_brand bb where bb.del_flag = '0' and bb.status = '1'"
					+ " and bb.id = (select bp.brand_id from bu_product bp where t.product_id = bp.id"
					+ " and bp.del_flag = '0') and bb.name like CONCAT('%', '" + brandName + "', '%'))");
			return this;
		}
		
		//货号模糊查询
		public Criteria andArtNoLike(String artNo) {
			addCriterion(" EXISTS(select bp.id from bu_product bp where t.product_id = bp.id"
					+ " and bp.del_flag = '0' and bp.art_no like CONCAT('%', '" + artNo + "', '%'))");
			return this;
		}
		
		//商品编号精确查询
		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion(" EXISTS(select bp.id from bu_product bp where t.product_id = bp.id"
					+ " and bp.del_flag = '0' and bp.code = '" + productCode + "')");
			return this;
		}
		
		//货号模糊查询 和 商品编号精确查询
		public Criteria andartNoAndProductCode(String artNo, String productCode) {
			addCriterion(" EXISTS(select bp.id from bu_product bp where t.product_id = bp.id"
					+ " and bp.del_flag = '0' and bp.art_no like CONCAT('%', '" + artNo + "', '%') and bp.code = '" + productCode + "')");
			return this;
		}
		
		//店铺名模糊查询
		public Criteria andShopNameLike(String shopName) {
			addCriterion(" EXISTS(select bm.id from bu_mcht_info bm where t.mcht_id = bm.id"
					+ " and bm.del_flag = '0' and bm.shop_name like CONCAT('%', '" + shopName + "', '%'))");
			return this;
		}

		//合作类型查询
		public Criteria andMchtTypeEqualTo(String value) {
			addCriterion("EXISTS(select bm.id from bu_mcht_info bm where bm.id = t.mcht_id and bm.del_flag = '0' and bm.mcht_type = '"+value+"')");
			return  this;
		}

		//是否自营查询
		public Criteria andIsManageSelfEqualTo(String value) {
			addCriterion("EXISTS(select bm.id from bu_mcht_info bm where bm.id = t.mcht_id and bm.del_flag = '0' and bm.is_manage_self = '"+value+"')");
			return  this;
		}


		
		//商家序号精准查询
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select bm.id from bu_mcht_info bm where t.mcht_id = bm.id"
					+ " and bm.del_flag = '0' and bm.mcht_code = '" + mchtCode + "' )");
			return this;
		}
		
		//店铺名模糊查询 和 商家序号精准查询
		public Criteria andShopNameAndMchtCode(String shopName, String mchtCode) {
			addCriterion(" EXISTS(select bm.id from bu_mcht_info bm where t.mcht_id = bm.id"
					+ " and bm.del_flag = '0' and bm.shop_name like CONCAT('%', '" + shopName + "', '%') and bm.mcht_code = '" + mchtCode + "' )");
			return this;
		}
		
		//一级类目查询
		public Criteria andProductTypeEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select bp.id from bu_product bp where t.product_id = bp.id"
					+ " and bp.del_flag = '0' and bp.product_type_id in "
					+ " (select bpt.id from bu_product_type bpt where bpt.del_flag = '0'"
					+ " and bpt.status = '1' and bpt.parent_id in"
					+ " (select bt.id from bu_product_type bt where bt.del_flag = '0'"
					+ " and bt.status = '1' and bt.parent_id = " + productTypeId + ")))");
			return this;
		}
		
		//选择待审时间
		public Criteria andUpdateDateBetwwenOrBefore(String updateDateFlag) {
			if(updateDateFlag.equals("between"))
				addCriterion(" (DATE_FORMAT(t.update_date, '%Y-%m-%d %T') >= TIMESTAMPADD(DAY, -7, NOW()))");
			if(updateDateFlag.equals("before"))
				addCriterion(" (DATE_FORMAT(t.update_date, '%Y-%m-%d %T') < TIMESTAMPADD(DAY, -7, NOW()))");
			return this;
		} 
		
		//期望日期
		public Criteria andExpectedDateByEqualTo(String expectedDate) {
			addCriterion(" (DATE_FORMAT(t.expected_date, '%Y-%m-%d') = '" + expectedDate + "')");
			return this;
		}
		
		//开始时间
		public Criteria andBeginTimeByEqualTo(String beginTime, String startTime) {
			if(StringUtil.isEmpty(startTime)) {
				addCriterion(" (DATE_FORMAT(t.begin_time, '%Y-%m-%d') = '" + beginTime + "')");
			}
			if(!StringUtil.isEmpty(beginTime) && !StringUtil.isEmpty(startTime)){
				addCriterion(" (DATE_FORMAT(t.begin_time, '%Y-%m-%d %H:%i') = '" + beginTime + " " + startTime + "')");
			}
			return this;
		}
		
		//活动结束时间
		public Criteria andEndTimeByEqualTo() {
			addCriterion(" (DATE_FORMAT(t.end_time, '%Y-%m-%d %T') >= NOW() OR t.end_time is NULL)");
			return this;
		}
		
		public Criteria andCodeByEqualTo(String code) {
			addCriterion(" EXISTS(select bp.id from bu_product bp where t.product_id = bp.id and bp.del_flag = '0'"
					+ " and bp.code = '" + code + "')");
			return this;
		}
		
		//秒杀品牌团商品表
		public Criteria andNotExistsSeckillBrandGroupProduct() {
			addCriterion(" NOT EXISTS(select sbgp.id from bu_seckill_brand_group_product sbgp where sbgp.del_flag = '0' and sbgp.single_product_activity_id = t.id)");
			return this;
		}
		
		//品牌团
		public Criteria andSeckillBrandGroupIdEqualTo(String seckillBrandGroupId) {
			addCriterion(" EXISTS(select sbgp.id from bu_seckill_brand_group_product sbgp where sbgp.del_flag = '0' and sbgp.single_product_activity_id = t.id and sbgp.seckill_brand_group_id = "+ seckillBrandGroupId +")");
			return this;
		}
		
		//品牌团
		public Criteria andSeckillBrandGroupIn() {
			addCriterion(" EXISTS(select sbgp.id from bu_seckill_brand_group_product sbgp, bu_seckill_brand_group sbg where sbgp.del_flag = '0' and sbg.del_flag = '0' and sbg.status = '1' and sbgp.single_product_activity_id = t.id and sbg.begin_time >= DATE_SUB(NOW(), INTERVAL 1 DAY) )");
			return this;
		}
		
		/**
		 * 
		 * @Title andFirstAuditNameLike   
		 * @Description TODO(初审专员)   
		 * @author pengl
		 * @date 2018年6月5日 下午4:11:07
		 */
		public Criteria andFirstAuditNameLike(String firstAuditName) {
			addCriterion(" EXISTS(select st.staff_id from sys_staff_info st where t.first_audit_by = st.staff_id and st.staff_name like '"+ firstAuditName +"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductNameLike   
		 * @Description TODO(商品名模糊查询)   
		 * @author pengl
		 * @date 2018年6月5日 下午6:38:28
		 */
		public Criteria andProductNameLike(String productName) {
			addCriterion(" t.product_id in(select bp.id from bu_product bp where bp.del_flag = '0' and bp.name like '" + productName + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andSingleProductActivityProduct   
		 * @Description TODO(取同商家同品牌同货号商品中历史活动价格的最低值（不包含当前活动价格）)   
		 * @author pengl
		 * @date 2018年7月26日 上午9:47:31
		 */
		public Criteria andSingleProductActivityProduct() {
			addCriterion(" t.del_flag = '0' and p.del_flag = '0' and t.product_id = p.id ");
			return this;
		}
		public Criteria andAuditStatus(String auditStatus) {
			addCriterion(" t.audit_status = '"+auditStatus+"'");
			return this;
		}
		public Criteria andAuditStatusIn(String auditStatus) {
			addCriterion(" t.audit_status in("+auditStatus+")");
			return this;
		}
		public Criteria andCodeProductByEqualTo(String code) {
			addCriterion(" p.code = '" + code + "'");
			return this;
		}
		//品牌模糊查询
		public Criteria andBrandNameProductLike(String brandName) {
			addCriterion(" EXISTS(select bb.id from bu_product_brand bb where bb.del_flag = '0' and bb.status = '1'"
					+ " and bb.id = p.brand_id and bb.name like CONCAT('%', '" + brandName + "', '%'))");
			return this;
		}
		//货号模糊查询
		public Criteria andArtNoProductLike(String artNo) {
			addCriterion(" p.art_no like CONCAT('%', '" + artNo + "', '%')");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductBrandIdEqualTo   
		 * @Description TODO(品牌ID查询)   
		 * @author pengl
		 * @date 2018年9月7日 下午4:17:09
		 */
		public Criteria andProductBrandIdEqualTo(String productBrandId) {
			addCriterion(" EXISTS(select bp.id from bu_product bp where bp.del_flag = '0' and bp.id = t.product_id and bp.brand_id = "+ productBrandId +" )");
			return this;
		}
		
		/**
		 * 
		 * @Title andEndTimeGreaterThanOrEqualToOrIsNull   
		 * @Description TODO(单品审核状态=待审、初审通过、排期通过 且活动!=结束)   
		 * @author pengl
		 * @date 2018年10月26日 下午4:22:34
		 */
		public Criteria andEndTimeGreaterThanOrEqualToOrIsNull() {
			addCriterion(" (t.end_time is null or t.end_time >= now())");
			return this;
		}
		
		/**
		 * 比较报名价格 用double比较取到正确值
		 */
		public Criteria andOriginalPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }
		
		/**
		 * 比较报名价格 用double比较取到正确值
		 */
		public Criteria andOriginalPriceLessThanOrEqualTo(Double value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

		public Criteria andPlatContactStaffIdEqualTo(Integer platContactStaffId) {
			addCriterion(" t.mcht_id IN(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' and (pc.staff_id = "+platContactStaffId+" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = "+platContactStaffId+")) and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id )");
			return this;
		}
		
	}
	
}

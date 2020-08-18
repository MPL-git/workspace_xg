package com.jf.entity;

import com.jf.entity.ActivityExample.Criteria;



public class ActivityProductCustomExample extends ActivityProductExample {
	@Override
    public ActivityProductCustomCriteria createCriteria() {
		ActivityProductCustomCriteria criteria = new ActivityProductCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityProductCustomCriteria extends ActivityProductCustomExample.Criteria{
		/**
		 * 后台页面商家简称搜索
		 * @param value
		 * @return
		 */
		public Criteria andMchtNameLike(String value) {
			addCriterion("EXISTS(select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and mi.short_name like '"+value+"')");
			return this;
		}
		/**
		 * 商家编号搜索
		 * @param value
		 * @return
		 */
		public Criteria andMchtCodeLike(String value){
			addCriterion("select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and mi.mcht_code like '"+value+"'");
			return this;
		}
		
		/**
		 * 单品商家名称搜索
		 * @param value
		 * @return
		 */
		public Criteria andSingleMchtNameLike(String value) {
			addCriterion("EXISTS(SELECT p.id FROM bu_product p,bu_mcht_info m WHERE p.mcht_id=m.id and p.id=ap.product_id AND m.short_name LIKE '"+value+"')");
			return this;
		}
		/**
		 * 单品商品编号搜索
		 * @param value
		 * @return
		 */
		public Criteria andSingleMchtCodeLike(String value) {
			addCriterion("EXISTS(SELECT p.id FROM bu_product p,bu_mcht_info m WHERE p.mcht_id=m.id and p.id=ap.product_id AND m.mcht_code LIKE '"+value+"')");
			return this;
		}
		
		/**
		 * 商品ID/名称/货号
		 * @param value
		 * @return
		 */
		public Criteria andMchtNameOrCodeOrProductId(String value) {
			addCriterion("EXISTS(SELECT p.id FROM bu_product p WHERE p.id=product_id AND (p.code LIKE '"+value+"' or p.name like '"+value+"' or p.art_no like '"+value+"'))");
			return this;
		}
		
		/**
		 * 商家对接运营专员
		 * @param staffId
		 * @return
		 */
		public Criteria andStaffIdEqualTo(Integer staffId) {
			addCriterion("EXISTS(SELECT cc.staff_id FROM bu_product aa,bu_mcht_platform_contact bb,bu_platform_contact cc WHERE aa.id = ap.product_id AND aa.mcht_id = bb.mcht_id AND bb.del_flag = '0' AND bb.status='1' AND bb.platform_contact_id = cc.id AND cc.contact_type = '2' AND cc.del_flag = '0' AND cc.status='0' AND cc.staff_id='"+staffId+"')");
			return this;
		}
		
		/**
		 * 商家对接运营专员
		 * @param staffId
		 * @return
		 */
		public Criteria andActivityAreaIdByEqualTo(String activityType) {
			addCriterion("EXISTS(SELECT aa.id FROM bu_activity aa, bu_activity_area bb WHERE aa.id=ap.activity_id AND aa.activity_area_id=bb.id AND bb.type='"+activityType+"')");
			return this;
		}
		
		/**
		 * 会场id
		 * @param staffId
		 * @return
		 */
		public Criteria andActivityAreaIdEqualTo(Integer activityAreaId) {
			addCriterion("EXISTS(SELECT aa.id FROM bu_activity aa, bu_activity_area bb WHERE aa.id=ap.activity_id AND aa.activity_area_id=bb.id AND bb.id='"+activityAreaId+"')");
			return this;
		}
		/**
		 * 单品列表过滤6、7
		 * @return
		 */
		public Criteria andActivityAreaTypeEqualToSixOrSeven() {
			addCriterion("EXISTS(SELECT aa.id FROM bu_activity_area aa,bu_activity bb WHERE aa.id=bb.activity_area_id AND (aa.type='6' OR aa.type='7') AND bb.id=activity_id)");
			return this;
		}
		
		/**
		 * 单品活动屏蔽待提报的
		 * @param staffId
		 * @return
		 */
		public Criteria andActivityStatusNotEqualTo(String activityStatus) {
			addCriterion("EXISTS(SELECT aa.id FROM bu_activity aa WHERE aa.id=ap.activity_id AND aa.status!='"+activityStatus+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductCodeByEqualTo   
		 * @Description TODO(商品编号)   
		 * @author pengl
		 * @date 2018年1月15日 下午4:17:33
		 */
		public Criteria andProductCodeByEqualTo(String productCode) {
			addCriterion("EXISTS(SELECT p.id FROM bu_product p WHERE p.del_flag = '0' and p.id = ap.product_id and p.code = '"+productCode+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductNameLike   
		 * @Description TODO(商品名称)   
		 * @author pengl
		 * @date 2018年1月15日 下午4:19:56
		 */
		public Criteria andProductNameLike(String productName) {
			addCriterion("EXISTS(SELECT p.id FROM bu_product p WHERE p.del_flag = '0' and p.id = ap.product_id and p.name like '"+productName+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andOperateAuditStatus   
		 * @Description TODO(这里用一句话描述这个方法的作用)   
		 * @author pengl
		 * @date 2018年1月25日 下午4:57:30
		 */
		public Criteria andOperateAuditStatus() {
			addCriterion(" (ap.operate_audit_status = 1 or ap.operate_audit_status is null or ap.operate_audit_status = '')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaId   
		 * @Description TODO(根据活动会场ID)   
		 * @author pengl
		 * @date 2018年2月8日 下午6:47:33
		 */
		public Criteria andActivityAreaId(String activityAreaId) {
			addCriterion(" ap.activity_id in(select a.id from bu_activity a where a.del_flag = '0' and a.activity_area_id = "+activityAreaId+" )");
			return this;
		}
		
		
		/**
		 * 
		 * @Title andActivityProductDelFlagEqualTo   
		 * @Description TODO(取同商家同品牌同货号商品中历史静态活动价的最低值（不包含当前静态活动价）)   
		 * @author pengl
		 * @date 2018年7月27日 下午2:13:40
		 */
		public Criteria andActivityProductDelFlagEqualTo(String activityProductDelFlag, String productDelFlag) {
			addCriterion(" ap.del_flag = '"+ activityProductDelFlag +"' and bp.del_flag = '"+ productDelFlag +"' and ap.product_id = bp.id ");
			return this;
		}
		public Criteria andActivityProductCodeByEqualTo(String productCode) {
			addCriterion(" bp.code = '"+productCode+"'");
			return this;
		}
		
		/**
		 * 
		 * @Title andPlatformContactEqualTo   
		 * @Description TODO(对接人)   
		 * @author pengl
		 * @date 2019年2月19日 上午10:20:36
		 */
		public Criteria andPlatContactStaffIdEqualTo(Integer latContactStaffId) {
			addCriterion("EXISTS(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' "
					+ "and (pc.staff_id = "+ latContactStaffId +" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = "+ latContactStaffId +") ) "
							+ "and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id and mpc.mcht_id = bp.mcht_id)");
			return this;
		}
		
		//一级类目
		public Criteria andproductTypeIdByEqualTo(String productTypeId) {
			addCriterion(" bp.product_type1_id = '"+productTypeId+"'");
			return this;
		}
		
		//品牌
		public Criteria andproductBrandIdByEqualTo(String productBrandId) {
			addCriterion(" bp.brand_id = '"+productBrandId+"'");
			return this;
		}
		
	}
}
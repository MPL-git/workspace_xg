package com.jf.entity;

public class CouponCustomExample extends CouponExample{
	
	@Override
    public CouponCustomCriteria createCriteria() {
		CouponCustomCriteria criteria = new CouponCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CouponCustomCriteria extends CouponExample.Criteria{

		public Criteria andWhetherExpiry(String expiryFlag) {
			addCriterion(" case expiry_type when '1' then unix_timestamp(now())-unix_timestamp(expiry_end_date) "+expiryFlag+"0  else unix_timestamp(now())-unix_timestamp(rec_end_date)-expiry_days*86400 "+expiryFlag+"0 END");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityArea   
		 * @Description TODO( 会场类型=推广会场  且  启用状态=启用)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:01:01
		 */
		public Criteria andActivityArea() {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id)");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaNameLike   
		 * @Description TODO(会场名称)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:04:10
		 */
		public Criteria andActivityAreaNameLike(String activityAreaname) {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id and ba.name like '"+activityAreaname+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaPreferentialTypeEqualTo   
		 * @Description TODO(促销方式)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:05:43
		 */
		public Criteria andActivityAreaPreferentialTypeEqualTo(String preferentialType) {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id and ba.preferential_type = '"+preferentialType+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityBeginTimeBetween   
		 * @Description TODO(活动开始日期)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:27:55
		 */
		public Criteria andActivityBeginTimeBetween(String value1, String value2) {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id and ba.activity_begin_time between '"+value1+"' and '"+value2+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityEndTimeBetween   
		 * @Description TODO(活动结束日期)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:27:58
		 */
		public Criteria andActivityEndTimeBetween(String value1, String value2) {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id and ba.activity_end_time between '"+value1+"' and '"+value2+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaCreateByEqualTo   
		 * @Description TODO(会场创建人)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:31:19
		 */
		public Criteria andActivityAreaCreateByEqualTo(Integer createBy) {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id and ba.create_by = "+createBy+")");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaStatusEqualTo   
		 * @Description TODO(启用状态)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:32:37
		 */
		public Criteria andActivityAreaStatusEqualTo(String status) {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id and ba.status = '"+status+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaStatusEqualTo   
		 * @Description TODO(类目)   
		 * @author pengl
		 * @date 2018年2月9日 上午11:33:12
		 */
		public Criteria andProductTypeGroupFindInSet(String productTypeId) {
			addCriterion(" exists(select ba.name from bu_activity_area ba where ba.del_flag = '0' and ba.source = '1' and ba.type = '3' and ba.status = '1' and ba.id = activity_area_id and find_in_set('"+productTypeId+"', ba.product_type_group))");
			return this;
		}
		
		/**
		 * 	and DATE_FORMAT( rec_begin_date, '%Y-%m-%d' ) = '2019-10-01'
		 * @Title andActivityAreaStatusEqualTo   
		 * @Description TODO(类目)   
		 * @author yinrd
		 * @date 2018年2月9日 上午11:33:12
		 */
		public Criteria andBeginDateCenterTimeEqualTo(String centerTime ) {
			addCriterion(" DATE_FORMAT( rec_begin_date, '%H:%i' ) = '"+centerTime+"'");
			return this;
		}
		
		/**
		 * 	and DATE_FORMAT( rec_begin_date, '%Y-%m-%d' ) = '2019-10-01'
		 * @Title andActivityAreaStatusEqualTo   
		 * @Description TODO(类目)   
		 * @author yinrd
		 * @date 2018年2月9日 上午11:33:12
		 */
		public Criteria andBeginDateYMDEqualTo(String recBeginDate ) {
			addCriterion(" DATE_FORMAT( rec_begin_date, '%Y-%m-%d' ) = '"+recBeginDate+"'");
			return this;
		}


		public Criteria andMchtCodeEqualTo(String mchtCode ) {
			addCriterion(" EXISTS( SELECT mi.id FROM bu_mcht_info mi WHERE mcht_id = mi.id AND mi.mcht_code = '"+mchtCode+"' AND mi.del_flag = '0' )");
			return this;
		}


		public Criteria andMchtNameLike(String mchtName ) {
			addCriterion(" EXISTS( SELECT mi.id FROM bu_mcht_info mi WHERE mcht_id = mi.id AND ( mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' or mi.shop_name like '"+mchtName+"') AND mi.del_flag = '0' )");
			return this;
		}


		public Criteria andProductBrandNameEqualTo(String productBrandName ) {
			addCriterion(" EXISTS( SELECT pb.id FROM bu_product_brand pb,bu_product p WHERE p.id = type_ids AND p.brand_id = pb.id AND pb.NAME = '"+productBrandName+"' AND pb.del_flag = '0' AND p.del_flag = '0')");
			return this;
		}
		
	}
}
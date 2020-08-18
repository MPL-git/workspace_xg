package com.jf.entity;

import java.text.SimpleDateFormat;
import java.util.Date;



public class ActivityAreaCustomExample extends ActivityAreaExample {
	@Override
    public ActivityAreaCustomCriteria createCriteria() {
		ActivityAreaCustomCriteria criteria = new ActivityAreaCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityAreaCustomCriteria extends ActivityAreaCustomExample.Criteria{
		/**
		 * 活动起止时间
		 * @param value
		 * @return
		 */
		public Criteria andActivityBeginTimeByEqualTo(Date value){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sdf=dateFormat.format(value);
			addCriterion("(DATEDIFF(DATE_FORMAT(activity_begin_time, '%Y-%m-%d'),'"+sdf+"') =0)");
			return this;
		}
		/**
		 * 活动结束时间 
		 * @param value
		 * @return
		 */
		public Criteria andActivityEndTimeByEqualTo(Date value){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sdf=dateFormat.format(value);
			addCriterion("(DATEDIFF(DATE_FORMAT(activity_end_time, '%Y-%m-%d'),'"+sdf+"') =0)");
			return this;
		}
		
		public Criteria andActivityBeginTimeAndActivityEndTimeIsNotNull(Date valueOne,Date valueTwo){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sdfOne=dateFormat.format(valueOne);
			String sdfTwo=dateFormat.format(valueTwo);
			addCriterion("(DATE_FORMAT(activity_begin_time, '%Y-%m-%d') >='"+sdfOne+"'  and DATE_FORMAT(activity_end_time, '%Y-%m-%d') <='"+sdfTwo+"')");
			return this;
		}
		
		public Criteria andActivityIdEqualTo(Integer ActivityId){
			addCriterion("EXISTS(select a.id from bu_activity a where a.activity_area_id=ba.id and a.id ="+ActivityId+")");
			return this;
		}
		
		/**
		 * 后台页面商家简称搜索
		 * @param value
		 * @return
		 */
		public Criteria andMchtNameLike(String value) {
			addCriterion("EXISTS(select mi.id from bu_activity mp, bu_mcht_info mi where mp.activity_area_id=ba.id and mi.id=mp.mcht_id and mi.del_flag='0' and mi.short_name like '"+value+"')");
			return this;
		}
		/**
		 * 商家编号搜索
		 * @param value
		 * @return
		 */
		public Criteria andMchtCodeEqualTo(String value){
			addCriterion("EXISTS(select mi.id from bu_activity mp, bu_mcht_info mi where mp.activity_area_id=ba.id and mi.id=mp.mcht_id and mi.del_flag='0' and mi.mcht_code = '"+value+"')");
			return this;
		}
		
		public Criteria andProductTypeGroupFindInSet(String productTypeId){
			addCriterion("EXISTS(select aa.id from bu_activity_area aa where aa.del_flag = '0' and aa.id = ba.id and find_in_set('"+productTypeId+"', aa.product_type_group) )");
			return this;
		}
		//对接人
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {
			addCriterion("EXISTS(select mpc.mcht_id from bu_mcht_platform_contact mpc where mpc.mcht_id=ba.mcht_id and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityStatus   
		 * @Description TODO(总监审核状态=通过 且 未结束的品牌特卖 的 要处理：结束时间设置成当前时间)   
		 * @author pengl
		 * @date 2018年11月2日 上午11:44:55
		 */
		public Criteria andActivityStatus(String cooAuditStatus, Integer mchtId, Integer productBrandId) {
			if(productBrandId != 0) {
				addCriterion("EXISTS(select a.id from bu_activity a where a.del_flag = '0' and a.activity_area_id = ba.id and a.coo_audit_status = '"+ cooAuditStatus
						+"' and a.mcht_id = "+ mchtId +" and a.product_brand_id = "+ productBrandId +")");
			}else {
				addCriterion("EXISTS(select a.id from bu_activity a where a.del_flag = '0' and a.activity_area_id = ba.id and a.coo_audit_status = '"+ cooAuditStatus
						+"' and a.mcht_id = "+ mchtId +")");
			}
			return this;
		}
		
		/**
		 * 审核状态未通过的
		 * @param value
		 * @return
		 */
		public Criteria andCooAuditStatusEqualToPass( ){
			addCriterion("EXISTS(select a.id from bu_activity a where a.del_flag = '0' and a.activity_area_id = ba.id and a.coo_audit_status = '2')");
			return this;
		}

		/**
		 * 查询不是预付会场
		 * @param value
		 * @return
		 */
		public Criteria andNotIsPreSellActivityArea(String isPreSell){
			addCriterion(" (ba.is_pre_sell = '"+isPreSell+"' or ba.is_pre_sell is NULL) ");
			return this;
		}
		
		
		
	}
}
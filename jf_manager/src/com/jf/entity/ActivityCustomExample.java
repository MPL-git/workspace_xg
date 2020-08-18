package com.jf.entity;

import com.jf.common.enums.ActivityStatusEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityCustomExample extends ActivityExample {
	@Override
    public ActivityCustomCriteria createCriteria() {
		ActivityCustomCriteria criteria = new ActivityCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityCustomCriteria extends ActivityCustomExample.Criteria{
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
			addCriterion("EXISTS(select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and mi.mcht_code like '"+value+"')");
			return this;
		}
		
		/**
		 * 开始日期大于等于活动开始时间
		 * @param value
		 * @return
		 */		
        public Criteria andActivityBeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXISTS(select * from bu_activity_area a where a.id=activity_area_id and a.del_flag='0' and activity_begin_time <='"+value+"')");
            return this;
        }
        
		/**
		 * 截止日期小于等于活动结束时间
		 * @param value
		 * @return
		 */
        public Criteria andActivityEndTimeLessThanOrEqualTo(String value) {
        	addCriterion("EXISTS(select * from bu_activity_area a where a.id=activity_area_id and a.del_flag='0' and activity_end_time >='"+value+"')");
            return this;
        }
      
		public Criteria andActivityBeginTimeAndActivityEndTimeIsNotNull(Date valueOne,Date valueTwo){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sdfOne=dateFormat.format(valueOne);
			String sdfTwo=dateFormat.format(valueTwo);
			addCriterion("EXISTS(select * from bu_activity_area a where a.id=activity_area_id and a.del_flag='0' and DATE_FORMAT(a.activity_begin_time, '%Y-%m-%d') >='"+sdfOne+"'  and DATE_FORMAT(a.activity_end_time, '%Y-%m-%d') <='"+sdfTwo+"')");
			return this;
		}
		
		public Criteria andActivityAreaNameLike(String value){
			addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=activity_area_id and a.name like '"+value+"')");
			return this;
		}
		
		public Criteria andActivityBeginTimeGreaterThanOrEqualToNow(){
			addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=activity_area_id and a.activity_begin_time >= now())");
			return this;
		}
		
		
		/**
		 * 搜索品牌特卖/会场
		 * @param value
		 * @return
		 */
		public Criteria andActivityAreaBySourceEqualTo(String value){
			addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=activity_area_id and a.source = '"+value+"')");
			return this;
		}
		public Criteria andActivityAreaByTypeEqualOneAndTwo(Integer value,Integer type){
			addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=activity_area_id and (a.type = '"+value+"' or a.type='"+type+"'))");
			return this;
		}
		
		/*
		 * 活动时间搜索
		 */
		public Criteria andActivityAreaStatusEqualTo(Integer actStatus){
			if(actStatus.intValue()==1){//待开始
				addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=ba.activity_area_id and a.preheat_time > NOW())");
			}
			if(actStatus.intValue()==2){//预热中
				addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=ba.activity_area_id and a.preheat_time <= NOW() and a.activity_begin_time> NOW())");
			}
			if(actStatus.intValue()==3){//活动中
				addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=ba.activity_area_id and a.activity_begin_time <= NOW() and a.activity_end_time>=NOW())");
			}
			if(actStatus.intValue()==4){//活动结束
				addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=ba.activity_area_id and a.activity_end_time < NOW())");
			}
			return this;
		}
		
		/**
		 * 预览时间搜索
		 * @param dateTime
		 * @return
		 */
		public Criteria andActivityBeginTimeLike(Integer dateTime){
			if(dateTime.intValue()==1){//今天包括0点
				addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=activity_area_id and a.activity_begin_time BETWEEN CURDATE() AND NOW())");
			}else{
				if(dateTime.intValue()==2){//明天
					dateTime=1;
				}else if(dateTime.intValue()==3){//后天
					dateTime=2;
				}else{//大后天
					dateTime=3;
				}
				addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=activity_area_id and a.activity_begin_time BETWEEN DATE_ADD(curdate(), INTERVAL '"+dateTime+"' DAY) AND DATE_ADD(NOW(), INTERVAL '"+dateTime+"' DAY))");
			}
			return this;
		}
		/**
		 * 设计审核专员、法务审核专员名称搜索
		 * @param value
		 * @param type
		 * @return
		 */
		public Criteria andActivityAuditNameLike(String value,Integer type){
			if(type==3){
				addCriterion("EXISTS(select s.id from sys_staff_info s where s.id=design_audit_by and s.name like '"+value+"')");
			}
			if(type==4){
				addCriterion("EXISTS(select s.id from sys_staff_info s where s.id=law_audit_by and s.name like '"+value+"')");
			}
			return this;
		}
		
		/**
		 * 商家对接运营专员
		 * @param staffId
		 * @return
		 */
		public Criteria andStaffIdEqualTo(Integer staffId) {
			addCriterion("EXISTS(SELECT cc.staff_id FROM bu_mcht_platform_contact bb,bu_platform_contact cc WHERE bb.mcht_id = ba.mcht_id AND bb.del_flag = '0' AND bb.status='1' AND bb.platform_contact_id = cc.id AND cc.contact_type = '2' AND cc.del_flag = '0' AND cc.status='0' AND cc.staff_id="+staffId+")");
			return this;
		}
		
		/**
		 * 商家对接平台运营专员
		 * @param platformContactId
		 * @return
		 */
		public Criteria andPlatformContactIdEqualTo(Integer platformContactId) {
			addCriterion("EXISTS(SELECT bb.mcht_id FROM bu_mcht_platform_contact bb WHERE bb.mcht_id = ba.mcht_id AND bb.del_flag = '0' AND bb.status='1' AND bb.platform_contact_id="+platformContactId+")");
			return this;
		}
		/**
		 * 活动专区（source=2）品牌特卖活动
		 * @param staffId
		 * @return
		 */
//		public Criteria andSourceEqualToTwo(String value) {
//			addCriterion("EXISTS(SELECT aa.id FROM bu_activity_area aa WHERE aa.id = activity_area_id and aa.source='"+value+"')");
//			return this;
//		}
		
		public Criteria andDesignAuditStatusIsNullOrLawAuditStatusOrStaffId(Integer type) {
			if(type.intValue()==3){
				addCriterion("(design_audit_status != '0' and design_audit_status is not null)");
			}else{
				addCriterion("(law_audit_status != '0' and law_audit_status is not null)");
			}
			return this;
		}
		public Criteria andStatusForPass() {
			addCriterion("(status in ('5','6'))");
			return this;
		}
		
		
		/**
		 * 设计部、法务部审核人名称搜索
		 * @param auditName
		 * @param type
		 * @return
		 */
		public Criteria andAuditNameEqualTo(String auditName,Integer type) {
			String value="";
			if(type.intValue()==3){
				value="sy.STAFF_ID=design_audit_by";
			}else{
				value="sy.STAFF_ID=aa.law_audit_by";
			}
			addCriterion("EXISTS(SELECT sy.STAFF_ID FROM sys_staff_info sy WHERE sy.STAFF_NAME='"+auditName+"' AND "+value+")");
			return this;
		}
		
		/** 法务、设计、总监审核未审核状态 */
		public Criteria andDesignAuditNotStart() {
			addCriterion("(ba.design_audit_status is null or ba.design_audit_status='0')");
			return this;
		}
		
		public Criteria andLawAuditNotStart() {
			addCriterion("(ba.law_audit_status is null or ba.law_audit_status='0')");
			return this;
		}
		
		public Criteria andCooAuditNotStart() {
			addCriterion("(ba.coo_audit_status is null or ba.coo_audit_status='0')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityEndTimeGreaterThanNow   
		 * @Description TODO(活动结束时间大于现在时间)   
		 * @author pengl
		 * @date 2018年1月11日 上午11:07:29
		 */
		public Criteria andActivityEndTimeGreaterThanNow() {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and (baa.activity_end_time > NOW() or baa.activity_end_time is null))");
			return this;
		}
		
		/**
		 * 
		 * @Title andPreferentialTypeEqualTo   
		 * @Description TODO(促销方式)   
		 * @author pengl
		 * @date 2018年1月11日 上午11:18:57
		 */
		public Criteria andPreferentialTypeEqualTo(String preferentialType) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.preferential_type = '" + preferentialType + "')");
			return this;
		}
		
		/**
		 * 
		 * @Title andShopNameOrCompanyNameLike   
		 * @Description TODO(商家名称  模糊于：公司名称、店铺名称)   
		 * @author pengl
		 * @date 2018年1月11日 上午11:24:01
		 */
		public Criteria andShopNameOrCompanyNameLike(String value) {
			addCriterion("EXISTS(select bmi.id from bu_mcht_info bmi where bmi.id = ba.mcht_id and bmi.del_flag = '0' and (bmi.shop_name like '%"+value+"%' or bmi.company_name like '%"+value+"%'))");
			return this;
		}

		public Criteria andMchtTypeEqualTo(String value) {
			addCriterion("EXISTS(select bmi.id from bu_mcht_info bmi where bmi.id = ba.mcht_id and bmi.del_flag = '0' and bmi.mcht_type = '"+value+"')");
			return this;
		}

		//品牌特卖排期是否自营
		public Criteria andIsManageSelfEqualTo(String value) {
			addCriterion("EXISTS(select bmi.id from bu_mcht_info bmi where bmi.id = ba.mcht_id and bmi.del_flag = '0' and bmi.is_manage_self = '"+value+"')");
			return this;
		}

		
		/**
		 * 
		 * @Title andMchtCodeEqualTo   
		 * @Description TODO(商家序号  精确于：商家序号)   
		 * @author pengl
		 * @date 2018年1月11日 上午11:26:43
		 */
		public Criteria andMchtCodeEqualTo(String value) {
			addCriterion("EXISTS(select bmi.id from bu_mcht_info bmi where bmi.id = ba.mcht_id and bmi.del_flag = '0' and bmi.mcht_code = '"+value+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityBeginTimeBetween   
		 * @Description TODO(活动开始日期)   
		 * @author pengl
		 * @date 2018年1月11日 下午2:03:13
		 */
		public Criteria andActivityBeginTimeBetween(String activityBeginTimeA, String activityBeginTimeB) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_begin_time between '"+activityBeginTimeA+"' and '"+activityBeginTimeB+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityEndTimeBetween   
		 * @Description TODO(活动结束日期)   
		 * @author pengl
		 * @date 2018年1月11日 下午2:05:15
		 */
		public Criteria andActivityEndTimeBetween(String activityEndTimeA, String activityEndTimeB) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_end_time between '"+activityEndTimeA+"' and '"+activityEndTimeB+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityBeginEndTime   
		 * @Description TODO(活动开始日期<=当天 且 活动结束日期>=当天)   
		 * @author pengl
		 * @date 2018年1月23日 上午10:26:33
		 */
		public Criteria andActivityBeginEndTime(String seeDateTimeA, String seeDateTimeB) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_begin_time <= '"+seeDateTimeB+"' and baa.activity_end_time >= '"+seeDateTimeA+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andStatusCustom   
		 * @Description TODO(11.待开始  12.预热中  13.活动中  14.已结束)   
		 * @author pengl
		 * @date 2018年1月24日 下午9:26:36
		 */
		public Criteria andStatusCustom(Integer status) {
			if(status == ActivityStatusEnum.PREPARING.getValue()) { //待开始
				addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.preheat_time > now() )");
			}else if(status == ActivityStatusEnum.PREHEAT.getValue()) { //预热中
				addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.preheat_time <= now() and baa.activity_begin_time > now() )");
			}else if(status == ActivityStatusEnum.PROCESSING.getValue()) { //活动中
				addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_begin_time <= now() and baa.activity_end_time >= now() )");
			}else if(status == ActivityStatusEnum.FINISHED.getValue()) { //已结束
				addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_end_time < now() )");
			}
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityGroupIdEqualTo   
		 * @Description TODO(分组)   
		 * @author pengl
		 * @date 2018年1月29日 下午5:53:43
		 */
		public Criteria andActivityGroupIdEqualTo(Integer activityGroupId) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_group_id = "+activityGroupId+")");
			return this;
		}
		
		/**
		 * 
		 * @Title andDataSource   
		 * @Description TODO(品牌特卖   和   品牌会场)   
		 * @author pengl
		 * @date 2018年3月2日 下午2:51:04
		 */
		public Criteria andDataSource() {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.source = '2' "
					+ "or (baa.source = '1' and baa.type = '1'))");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaIsSignEqualTo   
		 * @Description TODO(是否标志)   
		 * @author pengl
		 * @date 2018年3月6日 上午10:48:59
		 */
		public Criteria andActivityAreaIsSignEqualTo(String isSign) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.is_sign = '"+ isSign +"')");
			return this;
		}
		public Criteria andPlatformContactEqualTo(Integer platformContactId) {//获取对接人
			addCriterion("EXISTS(select mpc.mcht_id from bu_mcht_platform_contact mpc where mpc.mcht_id=ba.mcht_id and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = "+platformContactId+" )");
			return this;
		}
		
		
		/**
		 * 搜索活动会场来源
		 * @param value
		 * @return
		 */
		public Criteria andActivityAreaBysourceEqualTo(String value){
			addCriterion("EXISTS(select a.id from bu_activity_area a where a.id=activity_area_id and a.activity_end_time >= now() and a.source = '"+value+"')");
			return this;
		}
		public Criteria andActivityAreaBysourceNotEqualTo(){//是否存在特卖
			addCriterion(" NOT EXISTS(select ab.id from bu_activity_brand_group_activity ab where ab.activity_area_id=ba.activity_area_id and ab.del_flag='0')");
			return this;
		}
		//活动开始时间
		public Criteria andActivityBeginTimeBetweens(String value, String value1) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_begin_time between '"+value+"' and '"+value1+"')");
			return this;
		}
		//活动结束时间
		public Criteria andActivityEndTimeBetweens(String value, String value1) {
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = ba.activity_area_id and baa.del_flag = '0' and baa.activity_end_time between '"+value+"' and '"+value1+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityBrandGroupNotNull   
		 * @Description TODO(特卖品牌团)   
		 * @author pengl
		 * @date 2018年8月30日 下午6:45:32
		 */
		//全部品牌团
		public Criteria andActivityBrandGroupNotNull() {
			addCriterion("EXISTS(select abga.id from bu_activity_brand_group_activity abga where abga.del_flag = '0' and abga.activity_area_id = ba.activity_area_id)");
			return this;
		}
		//未加入
		public Criteria andActivityBrandGroupNull() {
			addCriterion("NOT EXISTS(select abga.id from bu_activity_brand_group_activity abga where abga.del_flag = '0' and abga.activity_area_id = ba.activity_area_id)");
			return this;
		}
		//特卖品牌团ID
		public Criteria andActivityBrandGroupEqualTo(String activityBrandGroupId) {
			addCriterion("EXISTS(select abga.id from bu_activity_brand_group_activity abga, bu_activity_brand_group abg where abga.del_flag = '0' and abg.del_flag = '0' and abga.activity_brand_group_id = abg.id and abga.activity_area_id = ba.activity_area_id and abg.id = "+ activityBrandGroupId +")");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityAreaBysourceEqualTo   
		 * @Description TODO(活动特卖与会场特卖)   
		 * @author pengl
		 * @date 2018年9月8日 上午9:42:11
		 */
		public Criteria andActivityAreaBysourceEqualTo(){
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.del_flag = '0' and baa.id = activity_area_id and (baa.source = '2' or baa.source = '1' and baa.type = '1'))");
			return this;
		}
		
		/**
		 * 
		 * @Title andActivityEndTimeGreaterThanOrEqualToNow   
		 * @Description TODO(会场结束时间大于现在时间)   
		 * @author pengl
		 * @date 2018年9月8日 上午9:43:44
		 */
		public Criteria andActivityEndTimeGreaterThanOrEqualToNow(){
			addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = activity_area_id and (baa.activity_end_time >= now() or baa.activity_end_time is null))");
			return this;
		}
		
		/**
		 * 
		 * @Title andCooAuditStatusIsNullOrNotEqualTo   
		 * @Description TODO(总监审核状态！=通过)   
		 * @author pengl
		 * @date 2018年11月2日 下午1:58:39
		 */
		public Criteria andCooAuditStatusIsNullOrNotEqualTo(String cooAuditStatus){
			addCriterion(" IFNULL(coo_audit_status, '')  != '"+ cooAuditStatus +"'");
			return this;
		}
		
	}
}
package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.CombineOrderExample.Criteria;


public class MemberInfoCustomExample extends MemberInfoExample{
	
	@Override
    public MemberInfoCustomCriteria createCriteria() {
		MemberInfoCustomCriteria criteria = new MemberInfoCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberInfoCustomCriteria extends MemberInfoExample.Criteria{
		
		public Criteria andNickLike(String nick) {
			addCriterion(" EXISTS(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.nick like CONCAT('%', '"+nick+"', '%'))");
			return this;
		}

		public Criteria andLoginDateGreaterThanOrEqualTo(String loginDateBegin) {
			addCriterion(" EXISTS(select id from sys_app_login_log mi where mi.member_id=a.id and mi.create_date >= '"+loginDateBegin+"')");
			return this;
		}
		
		public Criteria andLoginDateLessThanOrEqualTo(String loginDateEnd) {
			addCriterion(" EXISTS(select id from sys_app_login_log mi where mi.member_id=a.id and mi.create_date <= '"+loginDateEnd+"')");
			return this;
		}
		
		/**
		 * 
		 * @MethodName: andLoginDateBetween
		 * @Description: (最后登录时间)
		 * @author Pengl
		 * @date 2019年4月23日 下午2:03:56
		 */
		/*public Criteria andLoginDateBetween(String loginDateBegin, String loginDateEnd) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(loginDateBegin) ) {
				stringBuffer.append(" and al.create_date >= '"+ loginDateBegin +" 00:00:00'");
			}
			if(!StringUtil.isEmpty(loginDateEnd) ) {
				stringBuffer.append(" and al.create_date <= '"+ loginDateEnd +" 23:59:59'");
			}
			addCriterion(" EXISTS(select id from sys_app_login_log al where al.del_flag = '0' and al.member_id = a.id "+ stringBuffer.toString() +")");
			return this;
		}*/
		
		
		public Criteria andLoginDateBetween(String loginDateBegin, String loginDateEnd) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(loginDateBegin) && !StringUtil.isEmpty(loginDateEnd) ) {
				stringBuffer.append(" between '"+ loginDateBegin +" 00:00:00' and '"+ loginDateEnd +" 23:59:59'");
			}else if(!StringUtil.isEmpty(loginDateBegin) ) {
				stringBuffer.append(" >= '"+ loginDateBegin +" 00:00:00'");
			}else if(!StringUtil.isEmpty(loginDateEnd) ) {
				stringBuffer.append(" <= '"+ loginDateEnd +" 23:59:59'");
			}
			addCriterion(" (select al.create_date from sys_app_login_log al where al.del_flag = '0' and al.member_id = a.id order by al.create_date desc limit 1)"+ stringBuffer.toString());
			return this;
		}
		
		/**
		 * 
		 * @MethodName: andFirstPayDateBetween
		 * @Description: (首次消费时间)
		 * @author Pengl
		 * @date 2019年4月23日 下午2:18:53
		 */
		public Criteria andFirstPayDateBetween(String firstPayDateBegin, String firstPayDateEnd) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(firstPayDateBegin) && !StringUtil.isEmpty(firstPayDateEnd) ) {
				stringBuffer.append(" between '"+ firstPayDateBegin +" 00:00:00' and '"+ firstPayDateEnd +" 23:59:59'");
			}else if(!StringUtil.isEmpty(firstPayDateBegin) ) {
				stringBuffer.append(" >= '"+ firstPayDateBegin +" 00:00:00'");
			}else if(!StringUtil.isEmpty(firstPayDateEnd) ) {
				stringBuffer.append(" <= '"+ firstPayDateEnd +" 23:59:59'");
			}
			addCriterion(" (select co.pay_date from bu_combine_order co where co.del_flag = '0' and co.status = '1' and co.member_id = a.id order by co.pay_date limit 1)"+ stringBuffer.toString());
			return this;
		}
		
		/**
		 * 
		 * @MethodName: andLastPayDateBetween
		 * @Description: (最后消费时间)
		 * @author Pengl
		 * @date 2019年4月23日 下午2:21:35
		 */
		public Criteria andLastPayDateBetween(String lastPayDateBegin, String lastPayDateEnd) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(lastPayDateBegin) && !StringUtil.isEmpty(lastPayDateEnd) ) {
				stringBuffer.append(" between '"+ lastPayDateBegin +" 00:00:00' and '"+ lastPayDateEnd +" 23:59:59'");
			}else if(!StringUtil.isEmpty(lastPayDateBegin) ) {
				stringBuffer.append(" >= '"+ lastPayDateBegin +" 00:00:00'");
			}else if(!StringUtil.isEmpty(lastPayDateEnd) ) {
				stringBuffer.append(" <= '"+ lastPayDateEnd +" 23:59:59'");
			}
			addCriterion(" (select co.pay_date from bu_combine_order co where co.del_flag = '0' and co.status = '1' and co.member_id = a.id order by co.pay_date desc limit 1)"+ stringBuffer.toString());
			return this;
		}
		
		
		//最后登入时间
		public Criteria andLastLoginTimeBetween(String loginDateBegin, String loginDateEnd) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(loginDateBegin) && !StringUtil.isEmpty(loginDateEnd) ) {
				stringBuffer.append(" between '"+ loginDateBegin +" 00:00:00' and '"+ loginDateEnd +" 23:59:59'");
			}else if(!StringUtil.isEmpty(loginDateBegin) ) {
				stringBuffer.append(" >= '"+ loginDateBegin +" 00:00:00'");
			}else if(!StringUtil.isEmpty(loginDateEnd) ) {
				stringBuffer.append(" <= '"+ loginDateEnd +" 23:59:59'");
			}
			addCriterion(" (select ms.last_login_time from bu_member_statistical_info ms where ms.del_flag = '0' and ms.member_id = a.id order by ms.last_login_time desc limit 1)"+ stringBuffer.toString());
			return this;
		}
		
		//首次消费时间
		public Criteria andFirstBuyTimeBetween(String firstPayDateBegin, String firstPayDateEnd) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(firstPayDateBegin) && !StringUtil.isEmpty(firstPayDateEnd) ) {
				stringBuffer.append(" between '"+ firstPayDateBegin +" 00:00:00' and '"+ firstPayDateEnd +" 23:59:59'");
			}else if(!StringUtil.isEmpty(firstPayDateBegin) ) {
				stringBuffer.append(" >= '"+ firstPayDateBegin +" 00:00:00'");
			}else if(!StringUtil.isEmpty(firstPayDateEnd) ) {
				stringBuffer.append(" <= '"+ firstPayDateEnd +" 23:59:59'");
			}
			addCriterion(" (select ms.first_buy_time from bu_member_statistical_info ms where ms.del_flag = '0' and ms.member_id = a.id order by ms.first_buy_time limit 1)"+ stringBuffer.toString());
			return this;
		}
		
		//最后消费时间
		public Criteria andLastBuyTimeBetween(String lastPayDateBegin, String lastPayDateEnd) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(lastPayDateBegin) && !StringUtil.isEmpty(lastPayDateEnd) ) {
				stringBuffer.append(" between '"+ lastPayDateBegin +" 00:00:00' and '"+ lastPayDateEnd +" 23:59:59'");
			}else if(!StringUtil.isEmpty(lastPayDateBegin) ) {
				stringBuffer.append(" >= '"+ lastPayDateBegin +" 00:00:00'");
			}else if(!StringUtil.isEmpty(lastPayDateEnd) ) {
				stringBuffer.append(" <= '"+ lastPayDateEnd +" 23:59:59'");
			}
			addCriterion(" (select ms.last_buy_time from bu_member_statistical_info ms where ms.del_flag = '0' and ms.member_id = a.id order by ms.last_buy_time desc limit 1)"+ stringBuffer.toString());
			return this;
		}
		
		//消费笔数
		public Criteria andtotalBuyCountGreaterThanOrEqualTo(Integer payOrderCountMin) {
			addCriterion(" (SELECT ms.total_buy_count FROM bu_member_statistical_info ms WHERE ms.member_id = a.id and ms.del_flag = '0') >= "+payOrderCountMin);
			return this;
		}
		
		public Criteria andtotalBuyCountLessThanOrEqualTo(Integer payOrderCountMax) {
			addCriterion(" (SELECT ms.total_buy_count FROM bu_member_statistical_info ms WHERE ms.member_id = a.id and ms.del_flag = '0') <= "+payOrderCountMax);
			return this;
		}
		
		//消费总金额
		public Criteria andtotalBuyAmountGreaterThanOrEqualTo(BigDecimal payOrderAmountMin) {
			addCriterion(" (SELECT sum(ms.total_buy_amount) FROM bu_member_statistical_info ms WHERE ms.member_id = a.id AND ms.del_flag = '0') >= "+payOrderAmountMin);
			return this;
		}
		
		public Criteria andtotalBuyAmountLessThanOrEqualTo(BigDecimal payOrderAmountMax) {
			addCriterion(" (SELECT sum(ms.total_buy_amount) FROM bu_member_statistical_info ms WHERE ms.member_id = a.id AND ms.del_flag = '0') <= "+payOrderAmountMax);
			return this;
		}
		
		
		
		/**
		 * @param selectType 1 为查询，2 为统计， 由于性能问题采用不同的语句
		 * 
		 * @MethodName: andComPayDateBetween
		 * @Description: (消费时间)
		 * @author Pengl
		 * @date 2019年4月23日 下午2:27:34
		 */
		public Criteria andComPayDateBetween(String comPayDateBegin, String comPayDateEnd,String selectType) {
			StringBuffer stringBuffer = new StringBuffer();
			if(!StringUtil.isEmpty(comPayDateBegin) ) {
				stringBuffer.append(" and co.pay_date >= '"+ comPayDateBegin +" 00:00:00'");
			}
			if(!StringUtil.isEmpty(comPayDateEnd) ) {
				stringBuffer.append(" and co.pay_date <= '"+ comPayDateEnd +" 23:59:59'");
			}
			
			if("1".equals(selectType)){
				addCriterion(" EXISTS(select co.id from bu_combine_order co where co.del_flag = '0' and co.status = '1' and co.member_id = a.id "+ stringBuffer.toString() +")");
			}else{
				addCriterion(" a.id in (select co.member_id from bu_combine_order co where co.del_flag = '0' and co.status = '1' "+ stringBuffer.toString() +")");
			}
			
			return this;
		}
		
		public Criteria andFirstPayDateDateGreaterThanOrEqualTo(String firstPayDateBegin) {
			addCriterion(" (select b.pay_date from bu_combine_order b where b.member_id=a.id and b.status='1' and b.pay_status='1' and b.del_flag='0' order by b.pay_date limit 1) >= '"+firstPayDateBegin+"'");
			return this;
		}
		
		public Criteria andFirstPayDateDateLessThanOrEqualTo(String firstPayDateEnd) {
			addCriterion(" (select b.pay_date from bu_combine_order b where b.member_id=a.id and b.status='1' and b.pay_status='1' and b.del_flag='0' order by b.pay_date limit 1) <= '"+firstPayDateEnd+"'");
			return this;
		}
		
		public Criteria andLastPayDateGreaterThanOrEqualTo(String lastPayDateBegin) {
			addCriterion(" (select b.pay_date from bu_combine_order b where b.member_id=a.id and b.status='1' and b.pay_status='1' and b.del_flag='0' order by b.pay_date desc limit 1) >= '"+lastPayDateBegin+"'");
			return this;
		}
		
		public Criteria andLastPayDateLessThanOrEqualTo(String lastPayDateEnd) {
			addCriterion(" (select b.pay_date from bu_combine_order b where b.member_id=a.id and b.status='1' and b.pay_status='1' and b.del_flag='0' order by b.pay_date desc limit 1) <= '"+lastPayDateEnd+"'");
			return this;
		}
		
		public Criteria andSpreadnameEqualTo(String spreadname, String regClient) {
			if(regClient.equals("1")) //IOS
				addCriterion(" EXISTS(select btd.id from bu_track_data btd where a.reg_client='1' and a.req_imei is not null and a.req_imei not like '000000%' and a.req_imei = btd.idfa and btd.del_flag = '0' and btd.spreadname = '" + spreadname + "' )");
			else if(regClient.equals("2")) //Android
				addCriterion(" EXISTS(select btd.id from bu_track_data btd where a.reg_client='2' and a.req_imei is not null and a.req_imei not like '000000%' and a.req_imei = btd.imei and btd.del_flag = '0' and btd.spreadname = '" + spreadname + "' )");
			return this;
		}
		
		public Criteria andActivitynameEqualTo(String activityname, String regClient) {
			if(regClient.equals("1")) //IOS
				addCriterion(" EXISTS(select btd.id from bu_track_data btd where a.reg_client='1' and a.req_imei is not null and a.req_imei not like '000000%' and a.req_imei = btd.idfa and btd.del_flag = '0' and btd.activityname = '" + activityname + "' )");
			else if(regClient.equals("2")) //Android
				addCriterion(" EXISTS(select btd.id from bu_track_data btd where a.reg_client='2' and a.req_imei is not null and a.req_imei not like '000000%' and a.req_imei = btd.imei and btd.del_flag = '0' and btd.activityname = '" + activityname + "' )");
			return this;
		}
		
		public Criteria andChannelEqualTo(String channel, String regClient, String sprChnl) {
			if(!StringUtil.isEmpty(sprChnl) && "20000".equals(sprChnl)) { //微信小程序
				addCriterion(" a.reg_client = '5' and a.weixin_xcx_spr_dtl_id in(select wxd.id from bu_weixin_xcx_spr_dtl wxd, bu_weixin_xcx_spr_plan wxp where wxd.del_flag = '0' and wxp.del_flag = '0' and wxd.spr_plan_id = wxp.id and wxp.spr_plan_name like concat('%', '" + channel + "', '%') )");
			}else {
				if(regClient.equals("1")) //IOS
					addCriterion(" EXISTS(select btd.id from bu_track_data btd where a.reg_client='1' and a.req_imei is not null and a.req_imei not like '000000%' and a.req_imei = btd.idfa and btd.del_flag = '0' and btd.channel = '" + channel + "' )");
				else if(regClient.equals("2")) //Android
					addCriterion(" EXISTS(select btd.id from bu_track_data btd where a.reg_client='2' and a.req_imei is not null and a.req_imei not like '000000%' and a.req_imei = btd.imei and btd.del_flag = '0' and btd.channel = '" + channel + "' )");
			}
			return this;
		}
		
		/**
		 * 
		 * @MethodName: andTrackDataEqualTo
		 * @Description: (推广设备类型 为 IOS 时，推广渠道、活动名称、活动组)
		 * @author Pengl
		 * @date 2019年3月29日 上午10:52:58
		 */
		public Criteria andTrackDataEqualTo(String channel, String spreadname, String activityname) {
			StringBuffer sbf = new StringBuffer(" EXISTS(select btd.id from bu_track_data btd where a.reg_client = '1' and a.req_imei is not null and a.req_imei not like '000000%' and a.req_imei = btd.idfa and btd.del_flag = '0' ");
			if(!StringUtil.isEmpty(channel) ) {
				sbf.append(" and btd.channel like concat('%', '" + channel + "', '%')");
			}
			if(!StringUtil.isEmpty(spreadname) ) {
				sbf.append(" and btd.spreadname like concat('%', '" + spreadname + "', '%')");
			}
			if(!StringUtil.isEmpty(activityname) ) {
				sbf.append(" and btd.activityname like concat('%', '" + activityname + "', '%')");
			}
			sbf.append(")");
			addCriterion(sbf.toString());
			return this;
		}
		
		/**
		 * 
		 * @MethodName: andWeixinChannelEqualTo
		 * @Description: (来源渠道--->微信小程序)
		 * @author Pengl
		 * @date 2019年3月29日 上午10:36:53
		 */
		public Criteria andWeixinChannelEqualTo(String channel) {
			addCriterion(" a.reg_client = '5' and a.weixin_xcx_spr_dtl_id in(select wxd.id from bu_weixin_xcx_spr_dtl wxd, bu_weixin_xcx_spr_plan wxp where wxd.del_flag = '0' and wxp.del_flag = '0' and wxd.spr_plan_id = wxp.id and wxp.spr_plan_name like concat('%', '" + channel + "', '%') )");
			return this;
		}
		
		public Criteria andDouyinSprChnl(String sprPlanSite, String sprChnlName) {
			StringBuffer sbu = new StringBuffer("");
			if(!StringUtil.isEmpty(sprPlanSite) || !StringUtil.isEmpty(sprChnlName) ) {
				sbu.append(" EXISTS(select mb.id from douyin_member_bind mb, douyin_spr_plan sp");
				if(!StringUtil.isEmpty(sprChnlName) ) {
					sbu.append(" , douyin_spr_chnl sc");
				}
				sbu.append(" where mb.del_flag = '0' and sp.del_flag = '0' and mb.member_id = a.id and mb.first_spr_plan_id = sp.id");
				if(!StringUtil.isEmpty(sprPlanSite) ) {
					sbu.append(" and sp.spr_plan_site like concat('%', '"+ sprPlanSite +"', '%')");
				}
				if(!StringUtil.isEmpty(sprChnlName) ) {
					sbu.append(" and sc.del_flag = '0' and sp.spr_chnl_id = sc.id and sc.spr_chnl_name like concat('%', '"+ sprChnlName +"', '%')");
				}
				sbu.append(")");
				addCriterion(sbu.toString());
			}
			return this;
		}
		
		
		public Criteria andPayOrderCountGreaterThanOrEqualTo(Integer payOrderCountMin) {
			addCriterion(" (SELECT count(1) FROM bu_combine_order b WHERE b.member_id = a.id AND b. STATUS = '1' AND b.pay_status = '1' AND b.del_flag = '0') >= "+payOrderCountMin);
			return this;
		}
		
		public Criteria andPayOrderCountLessThanOrEqualTo(Integer payOrderCountMax) {
			addCriterion(" (SELECT count(1) FROM bu_combine_order b WHERE b.member_id = a.id AND b. STATUS = '1' AND b.pay_status = '1' AND b.del_flag = '0') <= "+payOrderCountMax);
			return this;
		}
		
		public Criteria andPayOrderAmountGreaterThanOrEqualTo(BigDecimal payOrderAmountMin) {
			addCriterion(" (SELECT sum(b.total_pay_amount) FROM	bu_combine_order b WHERE b.member_id = a.id	AND b. STATUS = '1' AND b.pay_status = '1' AND b.del_flag = '0') >= "+payOrderAmountMin);
			return this;
		}
		
		public Criteria andPayOrderAmountLessThanOrEqualTo(BigDecimal payOrderAmountMax) {
			addCriterion(" (SELECT sum(b.total_pay_amount) FROM	bu_combine_order b WHERE b.member_id = a.id	AND b. STATUS = '1' AND b.pay_status = '1' AND b.del_flag = '0') <= "+payOrderAmountMax);
			return this;
		}
		
		/**
		 * 
		 * @Title andPayDateBetween   
		 * @Description TODO(按会员付款时间查询)   
		 * @author pengl
		 * @date 2019年3月5日 下午2:11:52
		 */
		public Criteria andPayDateBetween(String payDateBegin, String payDateEnd ) {
			addCriterion(" EXISTS(select so.member_id from bu_svip_order so where so.del_flag = '0' and so.buy_type = '1' and so.status = '1' and so.member_id = a.id and so.pay_date between '" + payDateBegin + "' and '" + payDateEnd + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andPayDateGreaterThanOrEqualTo   
		 * @Description TODO(按会员付款时间查询)   
		 * @author pengl
		 * @date 2019年3月5日 下午2:13:44
		 */
		public Criteria andPayDateGreaterThanOrEqualTo(String payDateBegin ) {
			addCriterion(" EXISTS(select so.id from bu_svip_order so where so.del_flag = '0' and so.buy_type = '1' and so.status = '1' and so.pay_date >= '" + payDateBegin + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andPayDateLessThanOrEqualTo   
		 * @Description TODO(按会员付款时间查询)   
		 * @author pengl
		 * @date 2019年3月5日 下午2:14:14
		 */
		public Criteria andPayDateLessThanOrEqualTo(String payDateEnd ) {
			addCriterion(" EXISTS(select so.id from bu_svip_order so where so.del_flag = '0' and so.buy_type = '1' and so.status = '1' and so.pay_date <= '" + payDateEnd + "' )");
			return this;
		}

		/**
		 * @MethodName andNickLikeEqual
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月15日 下午2:43:27
		 */
		public void andNickLikeEqual(String nick) {
			// TODO Auto-generated method stub
			addCriterion(" a.id in(select b.id from bu_member_info b where b.nick like '"+nick+"')");
		}
		
		
		//招商对接人ID
		public void andZsPlatformContactIdEqualTo(Integer zsPlatformContactId) {
			// TODO Auto-generated method stub
			addCriterion(" a.id in (select me.member_id from bu_member_extend me where a.id = me.member_id and me.zs_platform_contact_id = '"+zsPlatformContactId+"' )");
		}
		
		//已注册的商家
		
		public void andMchtSettledApplyIdIsNotNull( ) {
			// TODO Auto-generated method stub
			addCriterion(" a.id in (select me.member_id from bu_member_extend me where a.id = me.member_id and me.mcht_settled_apply_id is not null )");
		}
		
		//未注册的商家
		public void andMchtSettledApplyIdIsNull( ) {
			// TODO Auto-generated method stub
			addCriterion("a.id not in (select me.member_id from bu_member_extend me where a.id = me.member_id and me.mcht_settled_apply_id is not null )");
		}
		
		//没有对接人的不显示
		public void andDontShowZsPlatformContactIdIsNull( ) {
			addCriterion("a.id in (select me.member_id from bu_member_extend me where a.id = me.member_id and me.zs_platform_contact_id is not null and me.del_flag='0')");
		}

		//任务黑名单（站内黑名单）
		public Criteria andNotBlack() {
			addCriterion("NOT EXISTS(select bl.id from bu_black_list bl where bl.del_flag = '0' and bl.black_type = '4' and bl.member_id = a.id)");
			return this;
		}

		//任务黑名单（站外黑名单）
		public Criteria andNotPaichuMobile() {
			addCriterion("NOT EXISTS(select pm.mobile from tmp_paichu_mobile pm where pm.mobile = a.mobile)");
			return this;
		}




	}
}
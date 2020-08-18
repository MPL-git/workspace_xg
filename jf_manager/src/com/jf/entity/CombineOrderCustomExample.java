package com.jf.entity;

import com.gzs.common.utils.StringUtil;



public class CombineOrderCustomExample extends CombineOrderExample{

	@Override
    public CombineOrderCustomCriteria createCriteria() {
		CombineOrderCustomCriteria criteria = new CombineOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	public class CombineOrderCustomCriteria extends Criteria{
		
		public Criteria andRepeatOrder() {
			addCriterion(" exists(select count(0) as num from bu_combine_order b where b.status = 1 and b.pay_status=1 and b.member_id = a.member_id having num > 2)");
			return this;
		}
		
		public Criteria andSprChnlEqualTo(String sprChnl) {
			addCriterion(" exists(select b.id from bu_member_info b where b.del_flag='0' and b.id=a.member_id and b.spr_chnl='"+sprChnl+"')");
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
			StringBuffer sbf = new StringBuffer(" EXISTS(select c.id from bu_member_info b, bu_track_data c where b.id = a.member_id and b.reg_client = '1' and b.req_imei is not null and b.req_imei not like '000000%' and b.req_imei = c.idfa and b.del_flag = '0' and c.del_flag = '0'");
			if(!StringUtil.isEmpty(channel) ) {
				sbf.append(" and c.channel like concat('%', '" + channel + "', '%')");
			}
			if(!StringUtil.isEmpty(spreadname) ) {
				sbf.append(" and c.spreadname like concat('%', '" + spreadname + "', '%')");
			}
			if(!StringUtil.isEmpty(activityname) ) {
				sbf.append(" and c.activityname like concat('%', '" + activityname + "', '%')");
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
			addCriterion(" a.member_id in(select mi.id from bu_member_info mi, bu_weixin_xcx_spr_dtl wxd, bu_weixin_xcx_spr_plan wxp where mi.del_flag = '0' and wxd.del_flag = '0' and wxp.del_flag = '0' and mi.reg_client = '5' and mi.weixin_xcx_spr_dtl_id = wxd.id and wxd.spr_plan_id = wxp.id and wxp.spr_plan_name like concat('%', '" + channel + "', '%') )");
			return this;
		}
		
		public Criteria andRegClientEqualTo(String regClient) {
			addCriterion(" EXISTS(select b.id from bu_member_info b where b.id = a.member_id and b.del_flag='0' and b.reg_client = '" + regClient + "' )");
			return this;
		}
		
		public Criteria andSprChnlNameLike(String sprChnlName) {
			addCriterion(" exists(select b.id from bu_member_info b, sys_status c where b.del_flag='0' and b.id=a.member_id and c.table_name='BU_MEMBER_INFO' and c.col_name='SPR_CHNL' and b.spr_chnl=c.status_value and c.status_desc like '"+sprChnlName+"')");
			return this;
		}
		
		public Criteria andReceivedAmountNotEqual() {
			addCriterion(" EXISTS(select b.id from bu_payment_platform_receivable_dtl b where b.payment_no = a.payment_no and b.del_flag='0' and b.amount != a.total_pay_amount )");
			return this;
		}
		
		public Criteria andRightDayReceived() {
			addCriterion(" EXISTS(select b.id from bu_payment_platform_receivable_dtl b where b.payment_no = a.payment_no and b.del_flag='0' and b.received_date = DATE_FORMAT(a.pay_date, '%Y-%m-%d'))");
			return this;
		}
		
		public Criteria andNotRightDayReceived() {
			addCriterion(" EXISTS(select b.id from bu_payment_platform_receivable_dtl b where b.payment_no = a.payment_no and b.del_flag='0' and b.received_date != DATE_FORMAT(a.pay_date, '%Y-%m-%d'))");
			return this;
		}
		
		public Criteria andNotReceived() {
			addCriterion("a.payment_no not in (select b.payment_no from bu_payment_platform_receivable_dtl b where b.del_flag='0')");
			return this;
		}
		
		public Criteria andDouyinEqualTo(String channel) {//抖音
			addCriterion(" a.member_id in (SELECT dm.member_id FROM douyin_member_bind dm,douyin_spr_dtl dsd,douyin_spr_plan dsp,douyin_spr_chnl dsc WHERE dm.del_flag = '0' AND dsd.del_flag = '0' AND dsp.del_flag = '0' AND dsc.del_flag = '0' AND dm.member_id=dsd.member_id AND dsd.spr_plan_id=dsp.id AND dsp.spr_chnl_id=dsc.id AND dsc.spr_chnl_name like concat('%', '" + channel + "', '%'))");
			return this;
		}
		
		public Criteria andNotMchtCodeEqualTo(String notMchtid) {//指定商家的相关数据不需要统计在内。
			addCriterion(" Not EXISTS(select so.combine_order_id from bu_sub_order so where so.combine_order_id=a.id and so.mcht_id in ('"+notMchtid+"'))");
			return this;
		}
	}
}
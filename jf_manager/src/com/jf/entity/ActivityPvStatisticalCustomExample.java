package com.jf.entity;


import com.gzs.common.utils.StringUtil;

public class ActivityPvStatisticalCustomExample extends ActivityPvStatisticalExample{
	@Override
    public ActivityPvStatisticalCustomCriteria createCriteria() {
		ActivityPvStatisticalCustomCriteria criteria = new ActivityPvStatisticalCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityPvStatisticalCustomCriteria extends ActivityPvStatisticalExample.Criteria{
		
		//特卖名称
		public void andNameEqualTo(String name) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select b.id from bu_activity b where b.del_flag='0' and b.id = a.activity_id and b.name like'%"+name+"%')");
		}
		
		//一级类目
		public void andProductTypeNameEqualTo(String productTypeName) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select b.id from bu_activity b where a.activity_id = b.id and b.del_flag = '0' and b.product_type_id = '"+productTypeName+"')");
		}

		//活动状态
		public void andActivityStatus(String activityStatus) {
			if("1".equals(activityStatus) ) { //预热中
				addCriterion(" EXISTS(select b.id from bu_activity b, bu_activity_area ba where b.activity_area_id = ba.id and b.del_flag = '0' and ba.del_flag = '0' and ba.preheat_time <= now() and ba.activity_begin_time > now() and b.id = a.activity_id)");
			}else if("2".equals(activityStatus) ) { //活动中
				addCriterion(" EXISTS(select b.id from bu_activity b, bu_activity_area ba where b.activity_area_id = ba.id and b.del_flag = '0' and ba.del_flag = '0' and ba.activity_begin_time <= now() and ba.activity_end_time >= now() and b.id = a.activity_id)");
			}else if("3".equals(activityStatus)) { //已结束
				addCriterion(" EXISTS(select b.id from bu_activity b, bu_activity_area ba where b.activity_area_id = ba.id and b.del_flag = '0' and ba.del_flag = '0' and ba.activity_end_time < now() and b.id = a.activity_id)");
			}
		}

		//商家序号 或 商家名称
		public void andMchtCodeOrMchtName(String mchtCode, String mchtName) {
			StringBuffer stringBuffer = new StringBuffer("EXISTS(select m.id from bu_activity b, bu_mcht_info m where b.mcht_id = m.id and b.del_flag = '0' and m.del_flag = '0' and b.id = a.activity_id");
			if(!StringUtil.isEmpty(mchtCode) ) {
				stringBuffer.append(" and m.mcht_code = '" + mchtCode + "'");
			}
			if(!StringUtil.isEmpty(mchtName) ) {
				stringBuffer.append(" and (m.company_name like concat('%','" + mchtName + "','%') or m.shop_name like concat('%','" + mchtName + "','%') )" );
			}
			stringBuffer.append(")");
			addCriterion(stringBuffer.toString());
		}



		
	}
}
package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jf.entity.ActivityPvStatisticalCustomExample.ActivityPvStatisticalCustomCriteria;

public class ActivityAreaPvStatisticalCustomExample extends ActivityAreaPvStatisticalExample{
	@Override
    public ActivityAreaPvStatisticalCustomCriteria createCriteria() {
		ActivityAreaPvStatisticalCustomCriteria criteria = new ActivityAreaPvStatisticalCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityAreaPvStatisticalCustomCriteria extends ActivityAreaPvStatisticalExample.Criteria{

		//会场类型
		public void andTypeEqualTo(String type) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select b.id from bu_activity_area b where b.id = a.activity_area_id and b.del_flag = '0' and b.type = '"+type+"')");
		}
		
		//会场名称
		public void andNameEqualTo(String name) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select b.id from bu_activity_area b where b.id = a.activity_area_id and b.del_flag = '0' and b.name like '%"+name+"%')");
		}

		//会场形式：会场  会场类型：1，2，3
		public void andSource() {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select b.id from bu_activity_area b where b.id = a.activity_area_id and b.source = '1' and b.type in('1','2','3') and b.del_flag = '0')");
		}
		
	}
}
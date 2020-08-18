package com.jf.entity;

import com.jf.common.enums.ActivityStatusEnum;

public class ActivityBrandGroupActivityCustomExample extends ActivityBrandGroupActivityExample {
	@Override
    public ActivityBrandGroupActivityCustomCriteria createCriteria() {
		ActivityBrandGroupActivityCustomCriteria criteria = new ActivityBrandGroupActivityCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityBrandGroupActivityCustomCriteria extends ActivityBrandGroupActivityCustomExample.Criteria{
		
		public Criteria andactivityAreaBysourceEqualTo(String value){//搜索会场是特卖且特卖状态=通过
			addCriterion("EXISTS(select ba.id from bu_activity_area ba,bu_activity b where ba.id=a.activity_area_id and b.activity_area_id=ba.id and ba.del_flag='0' and b.del_flag='0' and ba.source = '"+value+"')");
			return this;
		}
		
		public Criteria andactivityIdEqualTo(Integer value){//搜索特卖ID
			addCriterion("EXISTS(select b.id from bu_activity_area ba,bu_activity b where ba.id=a.activity_area_id and b.activity_area_id=ba.id and ba.del_flag='0' and b.del_flag='0' and b.id = '"+value+"')");
			return this;
		}
		
		public Criteria andactivityNameEqualTo(String value){//模糊搜索特卖名称
			addCriterion("EXISTS(select b.id from bu_activity_area ba,bu_activity b where ba.id=a.activity_area_id and b.activity_area_id=ba.id and ba.del_flag='0' and b.del_flag='0' and b.name like '%"+value+"%')");
			return this;
		}
		
		public Criteria andShopNameOrCompanyNameLike(String value) {//模糊搜索商家,店铺
			addCriterion("EXISTS(select bmi.id from bu_mcht_info bmi,bu_activity_area ba,bu_activity b where bmi.id = b.mcht_id and ba.id=a.activity_area_id and b.activity_area_id=ba.id and ba.del_flag='0' and b.del_flag='0' and bmi.del_flag = '0' and (bmi.shop_name like '%"+value+"%' or bmi.company_name like '%"+value+"%'))");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer value) {//搜索品类
			addCriterion("EXISTS(select p.id from bu_product_type p,bu_activity_area ba,bu_activity b where p.id = b.product_type_id and ba.id=a.activity_area_id and b.activity_area_id=ba.id and ba.del_flag='0' and b.del_flag='0' and p.del_flag = '0' and p.id = '"+value+"')");
			return this;
		}
		
		public Criteria andactivityStatusCustom(Integer status) {
			if(status == ActivityStatusEnum.PREHEAT.getValue()) { //预热中
				addCriterion("EXISTS(select baa.id from bu_activity_area baa,bu_activity ba where baa.id = ba.activity_area_id and baa.id=a.activity_area_id and ba.del_flag = '0' and baa.del_flag = '0' and baa.preheat_time <= now() and baa.activity_begin_time > now() )");
			}else if(status == ActivityStatusEnum.PROCESSING.getValue()) { //活动中
				addCriterion("EXISTS(select baa.id from bu_activity_area baa,bu_activity ba where baa.id = ba.activity_area_id and baa.id=a.activity_area_id and ba.del_flag = '0' and baa.del_flag = '0' and baa.activity_begin_time <= now() and baa.activity_end_time >= now() )");
			}else if(status == ActivityStatusEnum.FINISHED.getValue()) { //已结束
				addCriterion("EXISTS(select baa.id from bu_activity_area baa,bu_activity ba where baa.id = ba.activity_area_id and baa.id=a.activity_area_id and ba.del_flag = '0' and baa.del_flag = '0' and baa.activity_end_time < now() )");
			}
			return this;
		}
		
		//活动开始时间
		public Criteria andActivityBeginTimeBetweens(String value, String value1) {
			  addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = a.activity_area_id and baa.del_flag = '0' and baa.activity_begin_time between '"+value+"' and '"+value1+"')");
			   return this;
			}
		//活动结束时间
		public Criteria andActivityEndTimeBetweens(String value, String value1) {
			  addCriterion("EXISTS(select baa.id from bu_activity_area baa where baa.id = a.activity_area_id and baa.del_flag = '0' and baa.activity_end_time between '"+value+"' and '"+value1+"')");
			  return this;
		}
		
	}
}
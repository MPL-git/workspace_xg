package com.jf.entity;

import com.jf.entity.InformationExample.Criteria;


public class HotSearchWordCustomExample extends HotSearchWordExample {

	@Override
    public HotSearchWordCustomCriteria createCriteria() {
		HotSearchWordCustomCriteria criteria = new HotSearchWordCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class HotSearchWordCustomCriteria extends HotSearchWordExample.Criteria{
		
		/**
		 * 
		 * @Title andSearchLogCountGreaterThanOrEqualTo   
		 * @Description TODO(搜索次数)   
		 * @author pengl
		 * @date 2018年7月24日 下午2:37:17
		 */
		public Criteria andSearchLogCountGreaterThanOrEqualTo(Integer beginSearchLogCount) {
			addCriterion(" (select count(1) from bu_search_log sl where sl.del_flag = '0' and sl.hot_search_word_id = t.id ) >= " + beginSearchLogCount);
			return this;
		}
		public Criteria andSearchLogCountLessThanOrEqualTo(Integer endSearchLogCount) {
			addCriterion(" (select count(1) from bu_search_log sl where sl.del_flag = '0' and sl.hot_search_word_id = t.id ) <= " + endSearchLogCount);
			return this;
		}
		
		/**
		 * 
		 * @Title andSearchDate   
		 * @Description TODO(搜索日期)   
		 * @author pengl
		 * @date 2018年7月24日 下午2:52:47
		 */
		public Criteria andSearchDate(String beginSearchDate, String endSearchDate) {
			addCriterion(" t.id in(select sl.hot_search_word_id from bu_search_log sl where sl.del_flag = '0' and sl.create_date >= '"+beginSearchDate+"' and sl.create_date <= '"+endSearchDate+"' )");
			return this;
		}
		public Criteria andSearchDateGreaterThanOrEqualTo(String beginSearchDate) {
			addCriterion(" t.id in(select sl.hot_search_word_id from bu_search_log sl where sl.del_flag = '0' and sl.create_date >= '"+beginSearchDate+"' )");
			return this;
		}
		public Criteria andSearchDateLessThanOrEqualTo(String endSearchDate) {
			addCriterion(" t.id in(select sl.hot_search_word_id from bu_search_log sl where sl.del_flag = '0' and sl.create_date <= '"+endSearchDate+"' )");
			return this;
		}
		
		
		
	}
	
}

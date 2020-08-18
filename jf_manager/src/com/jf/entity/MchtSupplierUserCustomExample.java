package com.jf.entity;



public class MchtSupplierUserCustomExample extends MchtSupplierUserExample {

	@Override
	public MchtSupplierUserCustomCriteria createCriteria() {
		MchtSupplierUserCustomCriteria criteria = new MchtSupplierUserCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class MchtSupplierUserCustomCriteria extends MchtSupplierUserExample.Criteria {
		
		//队伍名称
		public Criteria andSportTeamNameLike(String sportTeamName) {
			addCriterion(" (t.home_team in(select st.id from bu_sport_team st where st.del_flag = '0' and st.name like '"+ sportTeamName +"')"
					+ " or t.away_team in(select st.id from bu_sport_team st where st.del_flag = '0' and st.name like '"+ sportTeamName +"'))");
			return this;
		}
		
	}
	
}

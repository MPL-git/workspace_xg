package com.jf.entity;


public class SportGuessCustomExample extends SportGuessExample {

	@Override
	public SportGuessCustomCriteria createCriteria() {
		SportGuessCustomCriteria criteria = new SportGuessCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class SportGuessCustomCriteria extends SportGuessExample.Criteria {
		
		//队伍名称
		public Criteria andSportTeamNameLike(String sportTeamName) {
			addCriterion(" t.sport_id in(select s.id from bu_sport s, bu_sport_team st where s.del_flag = '0' and st.del_flag = '0' and (s.home_team = st.id or s.away_team = st.id ) and st.name like '"+ sportTeamName +"')");
			return this;
		}
		
		//会员名称
		public Criteria andMemberNameLike(String memberName) {
			addCriterion(" t.member_id in(select m.id from bu_member_info m where m.del_flag = '0' and m.nick like '"+ memberName +"')");
	        return this;
		}
		
		
	}
	
}

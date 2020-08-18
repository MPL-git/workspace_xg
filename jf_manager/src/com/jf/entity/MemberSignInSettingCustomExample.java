package com.jf.entity;


public class MemberSignInSettingCustomExample extends MemberSignInSettingExample{
	
	@Override
    public MemberSignInSettingCustomCriteria createCriteria() {
		MemberSignInSettingCustomCriteria criteria = new MemberSignInSettingCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberSignInSettingCustomCriteria extends MemberSignInSettingExample.Criteria{
		public Criteria andRewardTypeEqualTo(String rewardType) {
			addCriterion("EXISTS(select id from bu_sign_in_setting sis where sis.id = t.sign_in_setting_id and sis.reward_type='"+rewardType+"' and sis.del_flag='0')");
	        return this;
		}
	}
}
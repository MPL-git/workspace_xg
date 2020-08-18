package com.jf.entity;

public class MemberLotteryCustomExample extends MemberLotteryExample{
	
	@Override
    public MemberLotteryCustomCriteria createCriteria() {
		MemberLotteryCustomCriteria criteria = new MemberLotteryCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberLotteryCustomCriteria extends Criteria{
		public Criteria andMemberMobileEqualTo(String memberMobile) {
			addCriterion(" EXISTS(SELECT mi.id FROM bu_member_info mi WHERE mi.id = ml.member_id AND mi.mobile = '"+memberMobile+"' AND mi.del_flag = '0')");
			return this;
		}

		public Criteria andMemberNickEqualTo(String memberNick) {
			addCriterion(" EXISTS(SELECT mi.id FROM bu_member_info mi WHERE mi.id = ml.member_id AND mi.nick = '"+memberNick+"' AND mi.del_flag = '0')");
			return this;
		}

		public Criteria andReceiverNameEqualTo(String receiverName) {
			addCriterion(" EXISTS(SELECT co.id FROM bu_combine_order co WHERE co.id = ml.relevant_id AND co.receiver_name = '"+receiverName+"' AND co.del_flag = '0')");
			return this;
		}

		public Criteria andUseStatusEqualTo(String useStatus) {
			addCriterion(" EXISTS( SELECT mc.id FROM bu_member_coupon mc WHERE mc.id = ml.relevant_id AND mc.status = "+useStatus+" AND mc.del_flag = '0')");
			return this;
		}

	}
}
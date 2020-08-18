package com.jf.entity;

public class MemberAccountDtlCustomExample extends MemberAccountDtlExample {

	@Override
    public MemberAccountDtlCustomCriteria createCriteria() {
		MemberAccountDtlCustomCriteria criteria = new MemberAccountDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberAccountDtlCustomCriteria extends MemberAccountDtlExample.Criteria{

		public Criteria andMemberNickLike(String memberNick) {
			addCriterion(" EXISTS(select mi.id from bu_member_account ma, bu_member_info mi where ma.del_flag = '0' and mi.del_flag = '0' and ma.member_id = mi.id and ma.id = t.acc_id and mi.nick like '"+ memberNick +"')");
			return this;
		}
		
		public Criteria andMemberMobileLike(String memberMobile) {
			addCriterion(" EXISTS(select mi.id from bu_member_account ma, bu_member_info mi where ma.del_flag = '0' and mi.del_flag = '0' and ma.member_id = mi.id and ma.id = t.acc_id and mi.mobile like '"+ memberMobile +"')");
			return this;
		}
		
		//会员ID
		public void andMemberIdEqualTo(String memberId) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select b.id from bu_member_account b where a.acc_id = b.id and b.del_flag='0' and a.biz_type='9' and b.member_id ='"+memberId+"')");
		}
		
		//会员昵称
		public void andNickEqualTo(String nick) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select c.id from bu_member_info c,bu_member_account d where a.acc_id = d.id and d.member_id = c.id and c.del_flag='0' and d.del_flag='0' and a.biz_type='9' and c.nick like '%"+nick+"%')");
		}

		public void andProductIdEqualTo(Integer memberId) {
			// TODO Auto-generated method stub
			addCriterion(" a.del_flag = '0' and z.id = a.acc_id and z.member_id ="+memberId +" and a.biz_type in('8','9','12')");
		}

		//变动日期
		public void andCreateDateGreaterThanOrEqualToFenRun(String createDateOrderBegin) {
			// TODO Auto-generated method stub
			addCriterion(" a.create_date >='"+createDateOrderBegin+"'");
		}

		public void andCreateDateLessThanOrEqualToFenRun(String createDateOrderEnd) {
			// TODO Auto-generated method stub
			addCriterion(" a.create_date <='"+createDateOrderEnd+"'");
		}	
	}
}
